package network;

/**
 * Interface to make ThreadSocket observable by other classes.
 * 
 * @author guiguito
 */
public interface ThreadSocketObservable {

	/**
	 * Add an observer to the ThreadSocketObservable object.
	 * 
	 * @param obs
	 *            the observer.
	 */
	public void registerObserver(ThreadSocketObserver obs);

	/**
	 * Remove an observer to the ThreadSocketObservable object.
	 * 
	 * @param obs
	 *            the observer.
	 */
	public void removeObserver(ThreadSocketObserver obs);

}
