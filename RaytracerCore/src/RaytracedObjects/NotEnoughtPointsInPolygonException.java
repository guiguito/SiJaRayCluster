/*
 * NotEnoughtPointsInPolygonException.java
 */

package RaytracedObjects;

/**
 * Exception launched if there is not enough points to define the polygon.
 * 
 * @author Guilhem Duch�
 */
public class NotEnoughtPointsInPolygonException extends Exception {

	/**
	 * Creates a new instance of NotEnoughtPointsInPolygonException
	 */
	public NotEnoughtPointsInPolygonException(int mess) {
		super("Not enough points to define a polygon. Only " + mess
				+ " points.");
	}

}
