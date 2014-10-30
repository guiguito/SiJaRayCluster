package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class Thread to listen incoming connections and pass the opened socket to the
 * observers.
 * 
 * @author guiguito
 */
public class ThreadServerSocket extends Thread implements
		ThreadServerSocketObservable {

	private ServerSocket socket;
	private boolean iterate;
	private List<ThreadServerSocketObserver> observers;

	/**
	 * Constructor of the thread.
	 * 
	 * @param port
	 *            port to listen.
	 * @throws IOException
	 * @throws IOException
	 *             exceptions if we can't listen the given port.
	 */
	public ThreadServerSocket(int port) throws IOException {
		super();
		observers = Collections
				.synchronizedList(new ArrayList<ThreadServerSocketObserver>());
		iterate = true;
		socket = new ServerSocket(port);

	}

	/**
	 * Constructor of the thread.
	 * 
	 * @param port
	 *            port to listen.
	 * @param obs
	 *            initial observer.
	 * @throws IOException
	 *             exceptions if we can't listen the given port.
	 */
	public ThreadServerSocket(int port, ThreadServerSocketObserver obs) {
		super();
		observers = Collections
				.synchronizedList(new ArrayList<ThreadServerSocketObserver>());
		registerObserver(obs);
		iterate = true;
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			notifyObserversConnectionIsDead(e.getMessage());
		}
	}

	/**
	 * Close the serversocket and stop the thread.
	 * 
	 * @throws IOException
	 */
	public synchronized void close() throws IOException {
		if (!socket.isClosed() && socket != null) {
			socket.close();
		}
		iterate = false;
	}

	/**
	 * Listen the incoming connections.
	 */
	public void run() {
		while (iterate) {
			Socket soc = null;
			try {
				soc = socket.accept();
				notifyObserversConnectionReceived(soc);
			} catch (IOException e) {
				if (!(e.getMessage().equalsIgnoreCase("socket closed") || e
						.getMessage().equalsIgnoreCase("Socket is closed"))) {
					e.printStackTrace();
					try {
						close();
						notifyObserversConnectionIsDead(e.getMessage());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		// System.out.println("thread DynamicServerSocket left");
	}

	private void notifyObserversConnectionReceived(Socket obj) {
		Iterator<ThreadServerSocketObserver> it = observers.iterator();
		while (it.hasNext()) {
			ThreadServerSocketObserver obs = it.next();
			obs.updateObserverConnectionIsReceived(obj);
		}
	}

	private void notifyObserversConnectionIsDead(String mess) {
		Iterator<ThreadServerSocketObserver> it = observers.iterator();
		while (it.hasNext()) {
			ThreadServerSocketObserver obs = it.next();
			obs.updateServerSocketIsDead(this, mess);
		}
	}

	public void registerObserver(ThreadServerSocketObserver obs) {
		synchronized (observers) {
			observers.add(obs);
		}
	}

	public void removeObserver(ThreadServerSocketObserver obs) {
		synchronized (observers) {
			int i = observers.indexOf(obs);
			if (i >= 0) {
				observers.remove(obs);
			}
		}
	}
}
