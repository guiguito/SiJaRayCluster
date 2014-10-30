package network;

/**
 * Interface to make ThreadServerSocket observable by other classes.
 * 
 * @author guiguito
 */
public interface ThreadServerSocketObservable {

	/**
	 * Add an observer to the ThreadServerSocketObservable object.
	 * 
	 * @param obs
	 *            the observer.
	 */
	public void registerObserver(ThreadServerSocketObserver obs);

	/**
	 * Remove an observer to the ThreadServerSocketObservable object.
	 * 
	 * @param obs
	 *            the observer.
	 */
	public void removeObserver(ThreadServerSocketObserver obs);

}
