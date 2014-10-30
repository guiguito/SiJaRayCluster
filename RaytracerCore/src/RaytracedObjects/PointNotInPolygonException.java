/*
 * PointNotInPolygon.java
 */

package RaytracedObjects;

import javax.vecmath.Point3d;

/**
 * Exception launched when we create a polygon with a point outside the plane
 * defined by the first threee points.
 * 
 * @author Guilhem Duché
 */
public class PointNotInPolygonException extends Exception {

	private Point3d point;

	/**
	 * Creates a new instance of PointNotInPolygon.
	 * 
	 * @param pt
	 *            the point outside a the polygon.
	 */
	public PointNotInPolygonException(Point3d pt) {
		super();
		point = pt;
	}

	/**
	 * Get the message associated with the exception.
	 */
	public String getMessage() {
		return "This point : " + point.toString() + " is outside a polygon.";
	}

}
