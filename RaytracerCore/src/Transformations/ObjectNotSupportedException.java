/*
 * ObjectNotSupportedException.java
 *
 * Created on 6 février 2006, 17:51
 */

package Transformations;

/**
 * Exception launched if a transformation can't modify the object passed to it.
 * 
 * @author Guilhem Duche.
 */
public class ObjectNotSupportedException extends Exception {

	/** Creates a new instance of ObjectNotSupportedException */
	public ObjectNotSupportedException(String mess) {
		super("Can not apply a transformation to the object of instance "
				+ mess + " !");
	}

}
