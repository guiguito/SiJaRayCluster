/*
 * ComplexObject.java
 *
 * Created on 29 janvier 2006, 20:55
 *
 */

package RaytracedObjects;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;
import Utils.BoundingObject;

/**
 * A complex object is made of several polygons. So many polygons that it's
 * worth using a bounding box.
 * 
 * @author Guilhem Duche
 */
public abstract class ComplexObject extends PolygonListObject {

	BoundingObject boundingObject;

	/**
	 * Creates a new instance of ComplexObject.
	 * 
	 * @param orig
	 *            the origin point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public ComplexObject(Point3d orig, Material mat, boolean inter) {
		super(orig, mat, inter);
	}

	/**
	 * Creates a new instance of ComplexObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public ComplexObject(long begin, long end, Point3d orig, Material mat,
			boolean inter) {
		super(begin, end, orig, mat, inter);
	}

	/**
	 * give the distance with the nearest intersection of the object or -1.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @param obj
	 *            the object from where the ray is launched, null if from
	 *            camera.
	 * @return information on the intersection of the object or null if no
	 *         intersection.
	 */
	public IntersectionResult getNearestIntersection(Point3d eyePoint,
			Vector3d direction, RaytracedObject obj) {
		if (!isBoundingObjectTouched(eyePoint, direction)) {
			return null;
		}
		return super.getNearestIntersection(eyePoint, direction, obj);
	}

	/**
	 * tell if the ray goes in the bounding box.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @return true if the ray goes in the bounding box.
	 */
	private boolean isBoundingObjectTouched(Point3d eyePoint, Vector3d direction) {
		if (boundingObject == null) {
			return true;
		} else {
			return boundingObject.isTouched(eyePoint, direction);
		}
	}

}
