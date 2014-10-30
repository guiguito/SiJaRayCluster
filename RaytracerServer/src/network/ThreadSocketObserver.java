package network;

/**
 * Interface for observers of ThreadSocketObservable objects.
 * 
 * @author guiguito
 */
public interface ThreadSocketObserver {

	/**
	 * Method called when a ThreadSocketObservable has received an object.
	 * 
	 * @param obj
	 *            the object received.
	 */
	public void updateObserverObjectReceived(Object obj);

	/**
	 * Method called when a ThreadSocketObservable has received an object.
	 * 
	 * @param src
	 *            the ThreadSocketObservable source of the event.
	 * @param obj
	 *            the object received.
	 */
	public void updateObserverObjectReceived(ThreadSocketObservable src,
			Object obj);

	/**
	 * Method called when the connection of a ThreadSocketObservable is dead.
	 * 
	 * @param src
	 *            the ThreadSocketObservable with the dead connection.
	 * @param mess
	 *            error message.
	 */
	public void updateConnectiondead(ThreadSocketObservable src, String mess);

}
