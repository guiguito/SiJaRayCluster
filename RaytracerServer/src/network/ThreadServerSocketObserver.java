package network;

import java.net.Socket;

/**
 * Interface for observers of ThreadServerSocketObservable objects.
 * 
 * @author guiguito
 */
public interface ThreadServerSocketObserver {

	/**
	 * Method called when a ThreadServerSocketObservable has received a
	 * connection.
	 * 
	 * @param obj
	 *            the connection.
	 */
	public void updateObserverConnectionIsReceived(Socket obj);

	/**
	 * Method called when when the server socket is dead.
	 * 
	 * @param obj
	 *            the connection.
	 */
	public void updateServerSocketIsDead(ThreadServerSocket th, String mess);

}
