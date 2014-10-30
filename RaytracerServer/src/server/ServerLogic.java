package server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Semaphore;

import network.EndOfImageComputation;
import network.ErrorForClient;
import network.IncomingConnection;
import network.MessageConf;
import network.PointsToCompute;
import network.ResultsComputation;
import network.ThreadServerSocket;
import network.ThreadServerSocketObserver;
import network.ThreadSocket;
import network.ThreadSocketObservable;
import network.ThreadSocketObserver;

/**
 * This class contains the behavior of the java Raytracer Server.
 * 
 * @author guiguito
 */
public class ServerLogic implements ThreadServerSocketObserver,
		ThreadSocketObserver {

	private ConcurrentList<ThreadSocket> availableConnections;// connections
																// available for
																// the current
																// computation
	private Semaphore availableSemaphore;
	private ConcurrentList<ThreadSocket> usedConnections;// connections used for
															// computations
	private ConcurrentList<ThreadSocket> connectingConnections;// connections
																// trying to
																// connect
	private ConcurrentList<ThreadSocket> waitingConnections;// connections
															// waiting to
															// participate to a
															// computation they
															// can do
	private ConcurrentList<PointsToCompute> jobs;
	private Semaphore availableJobsSemaphore;
	private ConcurrentList<ResultsComputation> results;
	private Semaphore resultsSemaphore;
	private HashMap<ThreadSocket, Client> clients;
	private ConcurrentHashMap<ThreadSocket, PointsToCompute> jobsSent;

	private ThreadServerSocket serverSocket;
	private boolean serverStarted;
	private boolean renderingStarted;

	private JobMaker jobsMaker;
	private String strategie;
	private int chunksize;

	private GuiServer gui;

	private MessageConf messageConf;
	private String filename;
	private RenderingThread renderingThread;
	private GatheringThread gatheringThread;
	private long time;

	private static ServerLogic instance;

	/**
	 * Private constructor, this class is a singleton.
	 * 
	 * @param g
	 *            the GUI to update it according to the events.
	 */
	private ServerLogic(GuiServer g) {
		availableConnections = new ConcurrentList<ThreadSocket>(
				new ArrayList<ThreadSocket>());
		usedConnections = new ConcurrentList<ThreadSocket>(
				new ArrayList<ThreadSocket>());
		connectingConnections = new ConcurrentList<ThreadSocket>(
				new ArrayList<ThreadSocket>());
		waitingConnections = new ConcurrentList<ThreadSocket>(
				new ArrayList<ThreadSocket>());
		jobs = new ConcurrentList<PointsToCompute>(
				new ArrayList<PointsToCompute>());
		availableJobsSemaphore = null;
		clients = new HashMap<ThreadSocket, Client>();
		jobsSent = new ConcurrentHashMap<ThreadSocket, PointsToCompute>();
		results = new ConcurrentList<ResultsComputation>(
				new ArrayList<ResultsComputation>());
		availableSemaphore = null;
		resultsSemaphore = null;
		serverSocket = null;
		gui = g;
		serverStarted = false;
		renderingStarted = false;
	}

	/**
	 * Accessor of this singleton.
	 * 
	 * @param g
	 *            the gui linked to this server.
	 * @return the instance of the singleton.
	 */
	public static synchronized ServerLogic getInstance(GuiServer g) {
		if (instance == null) {
			instance = new ServerLogic(g);
		}
		return instance;
	}

	/**
	 * Method called to start the server.
	 * 
	 * @param port
	 *            port used by the server.
	 */
	public void startServer(int port) {
		serverStarted = true;
		serverSocket = new ThreadServerSocket(port, this);
		serverSocket.start();
		Date date = new Date();
		gui.addLog("Server started on port : " + port + " at "
				+ date.getHours() + ":" + date.getMinutes() + ":"
				+ date.getSeconds());
	}

	/**
	 * Method called to stop the server.
	 */
	public void stopServer() {
		if (serverStarted) {
			// stop threadserver Socket, clean lists, close sockets, stop
			// rendering if necessary
			// if rendering started stop it
			if (renderingStarted) {
				stopRendering();
			}
			serverStarted = false;

			// stop server socket
			try {
				serverSocket.close();
			} catch (IOException e) {
				gui.addLog("Troubles closing the server Socket : "
						+ e.getMessage());
				e.printStackTrace();
			}
			serverSocket = null;

			// clear hashmap for clients
			synchronized (clients) {
				clients.clear();
			}
			// close all sockets
			List<ThreadSocket> liste = availableConnections.getRefOnList();
			synchronized (liste) {
				Iterator<ThreadSocket> it = liste.iterator();
				while (it.hasNext()) {
					ThreadSocket sock = it.next();
					sock.stopThread();
				}
				liste.clear();
			}
			liste = usedConnections.getRefOnList();
			synchronized (liste) {
				Iterator<ThreadSocket> it = liste.iterator();
				while (it.hasNext()) {
					ThreadSocket sock = it.next();
					sock.stopThread();
				}
				liste.clear();
			}
			liste = connectingConnections.getRefOnList();
			synchronized (liste) {
				Iterator<ThreadSocket> it = liste.iterator();
				while (it.hasNext()) {
					ThreadSocket sock = it.next();
					sock.stopThread();
				}
				liste.clear();
			}
			liste = waitingConnections.getRefOnList();
			synchronized (liste) {
				Iterator<ThreadSocket> it = liste.iterator();
				while (it.hasNext()) {
					ThreadSocket sock = it.next();
					sock.stopThread();
				}
				liste.clear();
			}
			gui.updateGuiForClients(clients);
		}
		System.gc();
		Date date = new Date();
		gui.addLog("Server stopped at : " + date.getHours() + ":"
				+ date.getMinutes() + ":" + date.getSeconds());
	}

	/**
	 * Method called to start rendering.
	 */
	public void goRender(String strat, int chunk, String file, MessageConf mess) {
		if (waitingConnections.size() > 0 && serverStarted && !renderingStarted) {
			renderingStarted = true;
			time = System.currentTimeMillis();

			messageConf = mess;
			strategie = strat;
			chunksize = chunk;
			filename = file;

			// Send message with raytracer's configuration to clients who have
			// the scene and put them in availableConnections
			List<ThreadSocket> liste = waitingConnections.getRefOnList();
			synchronized (liste) {
				ThreadSocket[] arr = liste.toArray(new ThreadSocket[1]);
				for (int i = 0; i < arr.length; i++) {
					ThreadSocket sock = arr[i];
					Client cl = clients.get(sock);
					if (clientHasTheScene(cl, messageConf.getScene())) {
						sock.send(messageConf);
						waitingConnections.remove(sock);
						availableConnections.add(sock);
					}
				}
			}
			int size = availableConnections.size();
			if (size == 0) {
				gui.addLog("No client is able to render this scene, connect another client capable of rendering this scene or stop rendering");
			}
			// divide image
			jobsMaker = new ChunksJobMaker(messageConf.getXRes(),
					messageConf.getYRes(), chunksize);
			jobsMaker.fillJobs(jobs);
			int nbJobs = jobs.size();

			// start semaphores
			resultsSemaphore = new Semaphore(0);
			availableSemaphore = new Semaphore(availableConnections.size());

			// start gathering thread
			gatheringThread = new GatheringThread(results, jobs.size(),
					resultsSemaphore, filename, messageConf, this);
			gatheringThread.start();

			// start rendering thread
			availableJobsSemaphore = new Semaphore(jobs.size());
			renderingThread = new RenderingThread(availableConnections, jobs,
					usedConnections, availableSemaphore, jobsSent,
					availableJobsSemaphore);
			renderingThread.start();

			Date date = new Date();
			gui.addLog("Rendering started with " + size + " clients at : "
					+ date.getHours() + ":" + date.getMinutes() + ":"
					+ date.getSeconds() + ". " + "There is " + nbJobs
					+ " to do to render "
					+ (messageConf.getXRes() * messageConf.getYRes())
					+ " pixels.");
		}
	}

	/**
	 * Method called to stop the rendering.
	 */
	public void stopRendering() {
		// stop rendering
		if (renderingStarted && serverStarted) {
			renderingStarted = false;

			// Stop rendering thread
			renderingThread.stopThread();
			availableSemaphore = null;
			availableJobsSemaphore = null;
			renderingThread = null;

			// Stop Gathering thread
			gatheringThread.stopThread();
			resultsSemaphore = null;
			gatheringThread = null;

			jobs.clear();
			jobsSent.clear();
			results.clear();

			// put all available connections in waiting connections
			waitingConnections.transferData(availableConnections);
			waitingConnections.transferData(usedConnections);

			System.gc();

			Date date = new Date();
			gui.addLog("Rendering stopped at : " + date.getHours() + ":"
					+ date.getMinutes() + ":" + date.getSeconds() + ".");
		}
	}

	/**
	 * Method called when a connection is received.
	 */
	public void updateObserverConnectionIsReceived(Socket obj) {
		// create threadSocket object
		ThreadSocket th = new ThreadSocket(obj);
		th.registerObserver(this);
		th.start();
		connectingConnections.add(th);
		gui.addLog("New connection arrived.");
	}

	/**
	 * Method called when the server socket is dead.
	 */
	public void updateServerSocketIsDead(ThreadServerSocket th, String mess) {
		gui.addLog("Troubles with server Socket, server stopped : " + mess);
		gui.pushStartServerButton();
	}

	/**
	 * Method called when a socket connection dies.
	 */
	public void updateConnectiondead(ThreadSocketObservable src, String mess) {
		ThreadSocket thsock = (ThreadSocket) src;
		// remove connection
		gui.addLog("Troubles with a socket : " + mess);
		Client cl = clients.get(thsock);
		if (cl != null) {
			gui.addLog("Client " + cl.getId() + " is disconnected.");
		}
		removeConnection(thsock);
		gui.updateGuiForClients(clients);
	}

	/**
	 * Method used to remove a connection from this server.
	 * 
	 * @param thsock
	 *            the threadSocket to remove.
	 */
	public void removeConnection(ThreadSocket thsock) {
		if (availableConnections.contains(thsock)) {// connection was in
													// available connections

			try {
				availableSemaphore.acquire();
			} catch (InterruptedException e) {
				System.out.println("Trouble with availableSemaphore : "
						+ e.getMessage());
				e.printStackTrace();
			}
			availableConnections.remove(thsock);
			synchronized (clients) {
				clients.remove(thsock);
			}

			gui.addLog("Connection removed from available connections.");
		} else if (usedConnections.contains(thsock)) {// connection was in used
														// connections

			usedConnections.remove(thsock);
			synchronized (clients) {
				clients.remove(thsock);
			}

			// restore points the client had to compute in the points to compute
			jobs.add(jobsSent.getValueAndRemoveIt(thsock));
			availableJobsSemaphore.release();
			gui.addLog("Connection removed from used connections.");
		} else if (waitingConnections.contains(thsock)) {// connection was in
															// connecting
															// connections

			waitingConnections.remove(thsock);
			synchronized (clients) {
				clients.remove(thsock);
			}
			gui.addLog("Connection removed from waiting connections.");
		} else if (connectingConnections.contains(thsock)) {// connection was in
															// connecting
															// connections

			connectingConnections.remove(thsock);
			gui.addLog("Connection removed from incoming connections.");
		}

		if (renderingStarted && usedConnections.size() == 0
				&& availableConnections.size() == 0) {
			// not enough connections to finish rendering
			// stopRendering();
			gui.pushGoRenderButton();
			gui.addLog("Not enough connections to render the image, rendering stopped.");
		}
	}

	/**
	 * Method called when image is rendered.
	 *
	 */
	public void tellRenderingIsFinished() {
		time = System.currentTimeMillis() - time;

		gui.addLog("Image rendered in + " + time + " ms !");
		// send results to clients

		EndOfImageComputation mess = new EndOfImageComputation(time);
		ConcurrentList<ThreadSocket> liste = new ConcurrentList<ThreadSocket>(
				new ArrayList<ThreadSocket>());
		while (availableConnections.size() > 0) {
			ThreadSocket thsock = availableConnections.getNextAndRemove();
			thsock.send(mess);
			liste.add(thsock);
		}
		availableConnections.transferData(liste);
		gui.pushGoRenderButton();
	}

	/**
	 * Tells if a client has the given scene.
	 * 
	 * @param cl
	 *            client.
	 * @param scn
	 *            the scene name.
	 * @return true if the client has the list, false otherwise.
	 */
	private boolean clientHasTheScene(Client cl, String scn) {
		// check if the client has the scene we want to render
		if (cl != null) {
			String[] scenes = cl.getScenes();
			for (int i = 0; i < scenes.length; i++) {
				if (scenes[i].equals(scn)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Method called when a message is a received in one of the socket.
	 */
	public void updateObserverObjectReceived(Object obj) {
		// do nothing
	}

	/**
	 * Method called when a message is a received in one of the socket.
	 */
	public void updateObserverObjectReceived(ThreadSocketObservable src,
			Object obj) {
		ThreadSocket thsock = (ThreadSocket) src;
		// gui.addLog("message received\n");

		// analyze message and acts accordingly
		if ((obj instanceof IncomingConnection) && serverStarted) {// message
																	// for an
																	// incoming
																	// connection

			// message connection with infos about the client
			IncomingConnection mess = (IncomingConnection) obj;

			boolean errors = false;
			String problems = "";

			// check if the client doens't have an id already used
			String id = mess.getId();
			Client client = null;
			synchronized (clients) {
				Collection<Client> clientsSet = clients.values();
				Iterator<Client> it = clientsSet.iterator();
				while (it.hasNext()) {
					Client cl = it.next();
					if (cl.getId().equals(id)) {
						errors = true;
						problems += "A client with this Id is already connected !";
					}
				}

				if (!errors) {// no errors add it to the client list
					client = new Client(thsock, id, mess.getScenes());
					clients.put(thsock, client);
				}
			}

			if (errors) {
				// send error message
				connectingConnections.remove(thsock);
				thsock.send(new ErrorForClient(problems));
				thsock.stopThread();
				gui.addLog("Connection refused for client : " + id + ". "
						+ problems + "\n");
			} else {
				connectingConnections.remove(thsock);
				if (renderingStarted
						&& clientHasTheScene(client, messageConf.getScene())) {// if
																				// rendering
																				// started
																				// add
																				// it
																				// to
																				// the
																				// available
																				// connections
																				// if
																				// it
																				// has
																				// the
																				// scene
					thsock.send(messageConf);
					availableConnections.add(thsock);
					availableSemaphore.release();
					gui.addLog("Connection accepted for client : " + id
							+ ". Put directly in calculation.");
				} else {// otherwise add it to witing connections
					waitingConnections.add(thsock);
					gui.addLog("Connection accepted for client : " + id
							+ ". Put in waiting connections.");
				}
				gui.updateGuiForClients(clients);
			}

		} else if ((obj instanceof ResultsComputation) && renderingStarted) {// message
																				// with
																				// the
																				// result
																				// of
																				// a
																				// computation
			if (renderingStarted) {// rendering started gather results
				// message results of computation
				ResultsComputation mess = (ResultsComputation) obj;
				// remove it from jobs sent.
				PointsToCompute pts = jobsSent.remove(thsock);
				mess.setXCoordinates(pts.getXCoordinates());
				mess.setYCoordinates(pts.getYCoordinates());
				// put it in the final image
				results.add(mess);
				resultsSemaphore.release();

				// move the thread in availableConnections
				usedConnections.remove(thsock);
				availableConnections.add(thsock);
				availableSemaphore.release();
				Client cl = clients.get(thsock);
				cl.setJobsDone(cl.getJobsDone() + 1);
				cl.setPointsComputed(cl.getPointsComputed()
						+ mess.getRed().length);
				cl.setTimeComputing(cl.getTimeComputing() + mess.getDuration());
				// gui.addLog("Data from client's computation received.");
				// gui.addLog("Available connections size : "+availableConnections.size()+" Used connections : "+usedConnections.size());

			}
			/*
			 * //this part is used if we don't transfer used connections to
			 * available connections when we stop rendering else{//rendering not
			 * started this connection should be available and no data sent now
			 * usedConnections.remove(thsock); waitingConnections.add(thsock); }
			 */
			gui.updateGuiForClients(clients);
		} else {
			// message not known : it's an error, remove the connection and
			Client cl = clients.get(thsock);
			String add = "";
			if (cl != null) {
				add = " with client " + cl.getId();
			}
			thsock.send(new ErrorForClient("Error connection" + add
					+ " : message unknown !"));
			gui.addLog("Unknown message received" + add
					+ ". Removing Connection");
			removeConnection(thsock);
			thsock.stopThread();
			gui.updateGuiForClients(clients);
		}
	}

}
