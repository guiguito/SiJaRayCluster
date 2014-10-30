/*
 * BoundingObject.java
 *
 * Created on 1 février 2006, 23:38
 */

package Utils;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * Mother class of all bounding objects.
 * 
 * @author guiguito
 */
public abstract class BoundingObject {

	/**
	 * Creates a new instance of BoundingObject
	 */
	public BoundingObject() {

	}

	/**
	 * Tell us if the bounding box is touched by the given ray.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @return true if touched false otherwise.
	 */
	public abstract boolean isTouched(Point3d eyePoint, Vector3d direction);

}
