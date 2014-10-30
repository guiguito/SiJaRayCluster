package network;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Class that stores a socket in a thread and notifies its observers when an
 * object is received.
 * 
 * @author guiguito
 */
public class ThreadSocket extends Thread implements ThreadSocketObservable {

	private Channel chan;
	private List<ThreadSocketObserver> observers;
	private boolean running = false;

	/**
	 * Constructor.
	 * 
	 * @param soc
	 *            the socket watch by the thread.
	 */
	public ThreadSocket(Socket soc) {
		super();
		try {
			chan = new Channel(soc);
		} catch (IOException e) {
			System.out.println("Troubles, we can't create data channel : "
					+ e.getMessage());
			e.printStackTrace();
		}
		observers = Collections
				.synchronizedList(new ArrayList<ThreadSocketObserver>());
		running = true;
	}

	/**
	 * Send an object through the socket stored.
	 * 
	 * @param o
	 *            the object to send.
	 */
	public synchronized void send(Object o) {
		if (chan.getSocket().isConnected()) {
			ObjectOutput out = chan.getOutput();
			try {
				out.writeObject(o);
				out.flush();
			} catch (IOException e) {
				System.out
						.println("Troubles sending an object in the channel : "
								+ e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close the channel, wich will cause the end of the thread.
	 * 
	 * @TODO synchronized or not ??? think about troubles of conccurrent access
	 *       on sockets or streams with operations (close/send/read)
	 */
	public synchronized void stopThread() {
		if (!chan.getSocket().isClosed()) {
			try {
				chan.getOutput().close();
				chan.getInput().close();
				chan.getSocket().close();
			} catch (IOException e) {
				System.out.println("Troubles closing streams and sockets : "
						+ e.getMessage());
				e.printStackTrace();
			}
		}
		running = false;
	}

	/**
	 * Code of the thread. Watch if something is received.
	 */
	public void run() {
		Socket sock = chan.getSocket();
		ObjectInput in = chan.getInput();
		while (running) {
			if (sock.isConnected()) {// well connected use it
				try {
					// System.out.println("lecture objet "+observers.size());
					Object obj = in.readObject();
					// System.out.println("objet lu");
					if (obj != null) {
						notifyObserversObjectReceived(obj);
						notifyObserversObjectReceived(this, obj);
					}
				} catch (IOException e) {
					notifyObserversConnectionIsdead(this, e.getMessage());
					stopThread();
				} catch (ClassNotFoundException e) {
					System.out
							.println("Can't find the class of the read object data ignored : "
									+ e.getMessage());
					e.printStackTrace();
				}
			} else {// not connected remove the socket
				notifyObserversConnectionIsdead(this, "Socket not connected.");
				stopThread();
			}
		}
	}

	private void notifyObserversConnectionIsdead(ThreadSocketObservable src,
			String mess) {
		Iterator<ThreadSocketObserver> it = observers.iterator();
		while (it.hasNext()) {
			ThreadSocketObserver obs = it.next();
			obs.updateConnectiondead(src, mess);
		}
	}

	private void notifyObserversObjectReceived(Object obj) {
		Iterator<ThreadSocketObserver> it = observers.iterator();
		while (it.hasNext()) {
			ThreadSocketObserver obs = it.next();
			obs.updateObserverObjectReceived(obj);
		}
	}

	private void notifyObserversObjectReceived(ThreadSocketObservable src,
			Object obj) {
		Iterator<ThreadSocketObserver> it = observers.iterator();
		while (it.hasNext()) {
			ThreadSocketObserver obs = it.next();
			obs.updateObserverObjectReceived(src, obj);
		}
	}

	public void registerObserver(ThreadSocketObserver obs) {
		synchronized (observers) {
			observers.add(obs);
		}
	}

	public void removeObserver(ThreadSocketObserver obs) {
		synchronized (observers) {
			int i = observers.indexOf(obs);
			if (i >= 0) {
				observers.remove(obs);
			}
		}
	}

}
