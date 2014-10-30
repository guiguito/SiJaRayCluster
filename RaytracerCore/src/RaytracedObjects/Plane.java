/*
 * Plane.java
 * Created on 11 décembre 2005, 20:04
 */

package RaytracedObjects;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * A Plane object to put in a scene to be raytraced.
 * 
 * @author Guilhem Duché
 */
public class Plane extends RaytracedObject {

	private Vector3d normal;
	private double shortestDistanceToOrigin;
	private Point3d point;

	/**
	 * Creates a new instance of Plane.
	 * 
	 * @param norm
	 *            the normal vector.
	 * @param pt
	 *            a point on the plane.
	 * @param col
	 *            the material of the object.
	 */
	public Plane(Vector3d norm, Point3d pt, Material col) {
		super(col, false);
		normal = norm;
		normal.normalize();
		point = pt;
		shortestDistanceToOrigin = -(norm.x * pt.x + norm.y * pt.y + norm.z
				* pt.z);
	}

	/**
	 * Creates a new instance of Plane.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param norm
	 *            the normal vector.
	 * @param pt
	 *            a point on the plane.
	 * @param col
	 *            the material of the object.
	 */
	public Plane(long begin, long end, Vector3d norm, Point3d pt, Material col) {
		super(begin, end, col, false);
		normal = norm;
		normal.normalize();
		point = pt;
		shortestDistanceToOrigin = -(norm.x * pt.x + norm.y * pt.y + norm.z
				* pt.z);
	}

	/**
	 * Set a new point of the plane.
	 * 
	 * @param pt
	 *            set a new point of the plane.
	 */
	public void setPointOnThePlane(Point3d pt) {
		point = pt;
		shortestDistanceToOrigin = -(normal.x * pt.x + normal.y * pt.y + normal.z
				* pt.z);
	}

	/**
	 * Get the point on the plane used to calculate the plane.
	 * 
	 * @return the point on the plane used to calculate the plane.
	 */
	public Point3d getPoint() {
		return point;
	}

	/**
	 * Get the normal of the plane.
	 * 
	 * @return the normal of the plane.
	 */
	public Vector3d getNormal() {
		return normal;
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
		double pnd = normal.dot(direction);
		if (pnd == 0) {// parallel, no intersection
			return null;
		}
		double w = -(normal
				.dot(new Vector3d(eyePoint.x, eyePoint.y, eyePoint.z)) + shortestDistanceToOrigin)
				/ pnd;
		if (w > 0 && obj != this) {// ok intersection
			Point3d intersection = new Point3d(eyePoint.x + w * direction.x,
					eyePoint.y + w * direction.y, eyePoint.z + w * direction.z);
			return new IntersectionResult(intersection, material, normal, w,
					this);
		} else {
			return null;
		}
	}

}// end class Plane
