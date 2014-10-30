/*
 * IntersectionResult.java
 *
 * Created on 6 janvier 2006, 12:58
 */

package RaytracedObjects;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * Result of an intersection with an object.
 * 
 * @author Guilhem Duche
 */
public class IntersectionResult {

	private Vector3d normal;
	private Point3d interSectionPoint;
	private Material material;
	private double distance;
	private RaytracedObject object;

	/**
	 * Creates a new instance of IntersectionResult .
	 * 
	 * @param point
	 *            the intersection point.
	 * @param mat
	 *            the material touched.
	 * @param norm
	 *            the normal of the surface at the intersection point.
	 * @param dist
	 *            distance between the eyepoint and and the intersection point.
	 * @param obj
	 *            the object touched by the ray.
	 */
	public IntersectionResult(Point3d point, Material mat, Vector3d norm,
			double dist, RaytracedObject obj) {
		normal = norm;
		material = mat;
		interSectionPoint = point;
		distance = dist;
		object = obj;
	}

	/**
	 * Get the intersection point between the ray and the object.
	 * 
	 * @return the intersection point
	 */
	public Point3d getIntersectionPoint() {
		return interSectionPoint;
	}

	/**
	 * Get the material of the object touched by the ray.
	 * 
	 * @return the material of the object touched
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Get the normal of the object at the intersection point.
	 * 
	 * @return the normal
	 */
	public Vector3d getNormal() {
		return normal;
	}

	/**
	 * Get distance between the eyepoint and and the intersection point.
	 * 
	 * @return distance between the eyepoint and and the intersection point.
	 */
	public double getDistance() {
		return distance;
	}

	/**
	 * Get the object touched by the ray.
	 * 
	 * @return the object touched.
	 */
	public RaytracedObject getObjectTouched() {
		return object;
	}

}// end class IntersectionResult
