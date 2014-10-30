/*
 * NoCameraException.java
 *
 * Created on 10 février 2006, 15:02
 */

package Raytracer;

/**
 * Exception launched when no camera is found.
 * 
 * @author Guilhem Duche
 */
public class NoCameraException extends Exception {

	/**
	 * Creates a new instance of NoCameraException
	 * 
	 * @param message
	 *            the error message.
	 */
	public NoCameraException(String message) {
		super(message);
	}

}
