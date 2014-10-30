/*
 * RaytracedObject.java
 *
 */

package RaytracedObjects;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;
import Raytracer.RaytracerObject;

/**
 * Base class for every objects we can put in a scene.
 * 
 * @author Guilhem Duche
 */
public abstract class RaytracedObject extends RaytracerObject {

	protected Material material;
	protected boolean interior;

	/**
	 * Creates a new instance of RaytracedObject.
	 */
	public RaytracedObject() {
		super();
		material = null;
		interior = false;
	}

	/**
	 * Creates a new instance of RaytracedObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public RaytracedObject(long begin, long end, boolean inter) {
		super(begin, end);
		material = null;
		interior = inter;
	}

	/**
	 * Creates a new instance of RaytracedObject.
	 * 
	 * @param mat
	 *            the material of the object.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public RaytracedObject(Material mat, boolean inter) {
		super();
		material = mat;
		interior = inter;
	}

	/**
	 * Creates a new instance of RaytracedObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object's life.
	 * @param mat
	 *            the material of the object.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public RaytracedObject(long begin, long end, Material mat, boolean inter) {
		super(begin, end);
		material = mat;
		interior = inter;
	}

	/**
	 * Return the material of the object.
	 * 
	 * @return material of the object.
	 */
	public Material getMaterial() {
		return material;
	}

	/**
	 * Kow if we consider that the object has an interior.
	 * 
	 * @return true if the object has an interior. False otherwise.
	 */
	public boolean hasInterior() {
		return interior;
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
	public abstract IntersectionResult getNearestIntersection(Point3d eyePoint,
			Vector3d direction, RaytracedObject obj);

}
