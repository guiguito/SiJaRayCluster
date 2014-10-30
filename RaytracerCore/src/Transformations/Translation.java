/*
 * Translation.java
 *
 * Created on 6 février 2006, 18:03
 *
 */

package Transformations;

import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Point3d;

import Lights.PointLight;
import Lights.SpotLight;
import RaytracedObjects.NotEnoughtPointsInPolygonException;
import RaytracedObjects.Plane;
import RaytracedObjects.PointNotInPolygonException;
import RaytracedObjects.Polygon;
import RaytracedObjects.PolygonListObject;
import RaytracedObjects.Sphere;
import RaytracedObjects.Triangle;
import Raytracer.Camera;

/**
 * A class to do translation on the RaytracedObjects.
 * 
 * @author Guilhem Duche
 */
public class Translation extends Transformation {

	private double x;
	private double y;
	private double z;

	/**
	 * Creates a new instance of Translation.
	 * 
	 * @param xx
	 * @param yy
	 * @param zz
	 */
	public Translation(double xx, double yy, double zz) {
		super();
		x = xx;
		y = yy;
		z = zz;
	}

	/*
	 * Creates a new instance of Translation.
	 * 
	 * @param beg date of beginning of transformation.
	 * 
	 * @param en date of end of transformation.
	 * 
	 * @param xx
	 * 
	 * @param yy
	 * 
	 * @param zz
	 */
	public Translation(long beg, long en, double xx, double yy, double zz) {
		super(beg, en);
		x = xx;
		y = yy;
		z = zz;
	}

	/**
	 * Apply the current transformation to a polygon.
	 * 
	 * @param pol
	 *            the polygon to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(Polygon pol, long time) {
		ArrayList<Point3d> points = pol.getPointsList();
		Iterator<Point3d> it = points.iterator();
		while (it.hasNext()) {
			Point3d pt = it.next();
			pt.set(pt.x + x, pt.y + y, pt.z + z);
		}
		try {
			pol.updateParameters();// probably useless here
		} catch (NotEnoughtPointsInPolygonException e) {
			// this exception can't happen with translation
		} catch (PointNotInPolygonException e) {
			// this exception can't happen with translation
		}
	}

	/**
	 * Apply the current transformation to a triangle.
	 * 
	 * @param tri
	 *            the triangle to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(Triangle tri, long time) {
		ArrayList<Point3d> points = tri.getPointsList();
		Iterator<Point3d> it = points.iterator();
		while (it.hasNext()) {
			Point3d pt = it.next();
			pt.set(pt.x + x, pt.y + y, pt.z + z);
		}
		tri.updateParameters();// probably useless here
	}

	/**
	 * Apply the current transformation to a sphere.
	 * 
	 * @param sph
	 *            the sphere to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(Sphere sph, long time) {
		Point3d center = sph.getCenter();
		center.set(center.x + x, center.y + y, center.z + z);
	}

	/**
	 * Apply the current transformation to a PolygonListObject instance.
	 * 
	 * @param obj
	 *            the PolygonListObject instance to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(PolygonListObject obj, long time) {
		ArrayList<Polygon> faces = obj.getPolygons();
		Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Polygon face = it.next();
			apply(face, time);
		}
		Point3d origin = obj.getOrigin();
		origin.set(origin.x + x, origin.y + y, origin.z + z);
	}

	/**
	 * Apply the current transformation to a plane.
	 * 
	 * @param pla
	 *            the plane to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(Plane pla, long time) {
		Point3d pt = pla.getPoint();
		pt.set(pt.x + x, pt.y + y, pt.z + z);
		pla.setPointOnThePlane(pt);
	}

	/**
	 * Apply the current transformation to a camera.
	 * 
	 * @param cam
	 *            the camera to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(Camera cam, long time) {
		if (!cam.isEyeFixed()) {
			Point3d eye = cam.getEyePoint();
			eye.set(eye.x + x, eye.y + y, eye.z + z);
		}
		if (!cam.isLookAtFixed()) {
			Point3d lookAt = cam.getLookAtPoint();
			lookAt.set(lookAt.x + x, lookAt.y + y, lookAt.z + z);
		}
	}

	/**
	 * Apply the current transformation to a PointLight.
	 * 
	 * @param light
	 *            the PointLight to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(PointLight light, long time) {
		Point3d pt = light.getPointLight();
		pt.set(pt.x + x, pt.y + y, pt.z + z);
	}

	/**
	 * Apply the current transformation to a SpotLight.
	 * 
	 * @param light
	 *            the PointLight to modify.
	 * @param time
	 *            the time when we want to apply the transformation.
	 */
	public void apply(SpotLight light, long time) {
		if (!light.isLightPointFixed()) {
			Point3d pt = light.getPointLight();
			pt.set(pt.x + x, pt.y + y, pt.z + z);
			light.setPointLight(pt);
		}
		if (!light.isLookAtPointFixed()) {
			Point3d pt = light.getLookAt();
			pt.set(pt.x + x, pt.y + y, pt.z + z);
			light.setLookAt(pt);
		}
	}
}
