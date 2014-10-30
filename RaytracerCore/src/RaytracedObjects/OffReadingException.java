/*
 * OffReadingException.java
 *
 * Created on 29 janvier 2006, 22:20
 */

package RaytracedObjects;

/**
 * Exception when we have an error reading an off file.
 * 
 * @author Guilhem Duche
 */
public class OffReadingException extends Exception {

	/**
	 * Creates a new instance of OffReadingException
	 */
	public OffReadingException(String mess) {
		super(mess);
	}

}
