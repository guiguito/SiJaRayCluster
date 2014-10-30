/*
 * CameraConflictException.java
 *
 * Created on 9 février 2006, 15:39
 *
 */

package Raytracer;

/**
 * Exception launched if there is a conflict between two cameras.<
 * 
 * @author Guilhem Duche
 */
public class CameraConflictException extends Exception {

	/**
	 * Creates a new instance of CameraConflictException
	 * 
	 * @param message
	 *            the error message.
	 */
	public CameraConflictException(String message) {
		super(message);
	}

}
