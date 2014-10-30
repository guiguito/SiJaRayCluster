/*
 * Transformation.java
 *
 * Created on 6 février 2006, 17:27
 *
 */

package Transformations;

import Lights.PointLight;
import Lights.SpotLight;
import RaytracedObjects.Plane;
import RaytracedObjects.Polygon;
import RaytracedObjects.PolygonListObject;
import RaytracedObjects.Sphere;
import RaytracedObjects.Triangle;
import Raytracer.Camera;
import Raytracer.RaytracerObject;

/**
 * Mother class for all transformations.
 * 
 * @author Guilhem Duche
 */
public abstract class Transformation {

	private long begin;
	private long end;

	/**
	 * Creates a new instance of Transformation.
	 */
	public Transformation() {
		begin = 1;
		end = Long.MAX_VALUE;
	}

	/**
	 * Creates a new instance of Transformation.
	 * 
	 * @param beg
	 *            date of beginning of transformation.
	 * @param en
	 *            date of end of transformation.
	 */
	public Transformation(long beg, long en) {
		begin = beg;
		end = en;
	}

	/**
	 * Get the date of beginning of the transormation.
	 * 
	 * @return the begin date.
	 */
	public long getBegin() {
		return begin;
	}

	/**
	 * Get the date of end of the transformation.
	 * 
	 * @return the end date.
	 */
	public long getEnd() {
		return end;
	}

	/**
	 * Get the date of beginning of the transformation.
	 * 
	 * @param beg
	 *            the date of beginning.
	 */
	public void setBegin(long beg) {
		begin = beg;
	}

	/**
	 * Get the date of beginning of the transformation.
	 * 
	 * @param en
	 *            the date of end.
	 */
	public void setEnd(long en) {
		end = en;
	}

	/**
	 * Apply a transformation to an object according to the time.
	 * 
	 * @param obj
	 *            tje object to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(RaytracerObject obj, long time)
			throws ObjectNotSupportedException {
		if (time <= end && time >= begin) {
			if (obj instanceof Polygon) {
				Polygon pol = (Polygon) obj;
				apply(pol, time);
			} else if (obj instanceof Sphere) {
				Sphere sph = (Sphere) obj;
				apply(sph, time);
			} else if (obj instanceof Triangle) {
				Triangle tri = (Triangle) obj;
				apply(tri, time);
			} else if (obj instanceof PolygonListObject) {
				PolygonListObject pol = (PolygonListObject) obj;
				apply(pol, time);
			} else if (obj instanceof Plane) {
				Plane pla = (Plane) obj;
				apply(pla, time);
			} else if (obj instanceof SpotLight) {
				SpotLight pla = (SpotLight) obj;
				apply(pla, time);
			} else if (obj instanceof PointLight) {
				PointLight pla = (PointLight) obj;
				apply(pla, time);
			} else if (obj instanceof Camera) {
				Camera cam = (Camera) obj;
				apply(cam, time);
			} else {
				throw new ObjectNotSupportedException(obj.getClass().getName());
			}
		} else {
			// launch an exception or not ?
		}
	}

	/**
	 * Apply the current transformation to a polygon.
	 * 
	 * @param pol
	 *            the polygon to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(Polygon pol, long time);

	/**
	 * Apply the current transformation to a triangle.
	 * 
	 * @param pol
	 *            the triangle to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(Triangle pol, long time);

	/**
	 * Apply the current transformation to a sphere.
	 * 
	 * @param sph
	 *            the sphere to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(Sphere sph, long time);

	/**
	 * Apply the current transformation to a PolygonListObject instance.
	 * 
	 * @param obj
	 *            the PolygonListObject instance to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(PolygonListObject obj, long time);

	/**
	 * Apply the current transformation to a plane.
	 * 
	 * @param pla
	 *            the plane to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(Plane pla, long time);

	/**
	 * Apply the current transformation to a camera.
	 * 
	 * @param cam
	 *            the camera to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(Camera cam, long time);

	/**
	 * Apply the current transformation to a PointLight.
	 * 
	 * @param cam
	 *            the PointLight to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(PointLight cam, long time);

	/**
	 * Apply the current transformation to a SpotLight.
	 * 
	 * @param cam
	 *            the PointLight to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public abstract void apply(SpotLight cam, long time);

}
