/*
 * Translation.java
 
 * Created on 6 février 2006, 18:03
 *
 */

package Transformations;

import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Matrix3d;
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
public class Rotation extends Transformation {

	private Point3d centerOfRotation;
	private Matrix3d rotation;
	private Translation toOrigin;
	private Translation toCenter;

	/**
	 * Creates a new instance of Translation.
	 * 
	 * @param center
	 * @param xAngle
	 * @param yAngle
	 * @param zAngle
	 */
	public Rotation(Point3d center, double xAngle, double yAngle, double zAngle) {
		super();
		centerOfRotation = center;
		double x = Math.toRadians(xAngle);
		double y = Math.toRadians(yAngle);
		double z = Math.toRadians(zAngle);
		Matrix3d xrot = new Matrix3d();
		xrot.rotX(x);
		Matrix3d yrot = new Matrix3d();
		yrot.rotY(y);
		Matrix3d zrot = new Matrix3d();
		zrot.rotZ(z);
		yrot.mul(zrot);
		xrot.mul(yrot);
		rotation = xrot;
		toOrigin = new Translation(-centerOfRotation.x, -centerOfRotation.y,
				-centerOfRotation.z);
		toCenter = new Translation(centerOfRotation.x, centerOfRotation.y,
				centerOfRotation.z);
	}

	/*
	 * Creates a new instance of Translation.
	 * 
	 * @param beg date of beginning of transformation.
	 * 
	 * @param en date of end of transformation.
	 * 
	 * @param center
	 * 
	 * @param xAngle
	 * 
	 * @param yAngle
	 * 
	 * @param zAngle
	 */
	public Rotation(long beg, long en, Point3d center, double xAngle,
			double yAngle, double zAngle) {
		super(beg, en);
		centerOfRotation = center;
		double x = Math.toRadians(xAngle);
		double y = Math.toRadians(yAngle);
		double z = Math.toRadians(zAngle);
		Matrix3d xrot = new Matrix3d();
		xrot.rotX(x);
		Matrix3d yrot = new Matrix3d();
		yrot.rotY(y);
		Matrix3d zrot = new Matrix3d();
		zrot.rotZ(z);
		yrot.mul(zrot);
		xrot.mul(yrot);
		rotation = xrot;
		toOrigin = new Translation(-centerOfRotation.x, -centerOfRotation.y,
				-centerOfRotation.z);
		toCenter = new Translation(centerOfRotation.x, centerOfRotation.y,
				centerOfRotation.z);
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
		toOrigin.apply(pol, time);
		ArrayList<Point3d> points = pol.getPointsList();
		Iterator<Point3d> it = points.iterator();
		while (it.hasNext()) {
			Point3d pt = it.next();
			rotation.transform(pt);
		}
		try {
			pol.updateParameters();
		} catch (NotEnoughtPointsInPolygonException e) {
			// this exception can't happen with rotation
		} catch (PointNotInPolygonException e) {
			// this exception can't happen with rotation
		}
		toCenter.apply(pol, time);
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
		toOrigin.apply(sph, time);
		Point3d center = sph.getCenter();
		rotation.transform(center);
		toCenter.apply(sph, time);
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
		toOrigin.apply(tri, time);
		ArrayList<Point3d> points = tri.getPointsList();
		Iterator<Point3d> it = points.iterator();
		while (it.hasNext()) {
			Point3d pt = it.next();
			rotation.transform(pt);
		}
		tri.updateParameters();
		toCenter.apply(tri, time);
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
		toOrigin.apply(obj, time);
		ArrayList<Polygon> faces = obj.getPolygons();
		Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Polygon face = it.next();
			apply(face, time);
		}
		Point3d origin = obj.getOrigin();
		rotation.transform(origin);
		toCenter.apply(obj, time);
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
		toOrigin.apply(pla, time);
		Point3d pt = pla.getPoint();
		rotation.transform(pt);
		pla.setPointOnThePlane(pt);
		toCenter.apply(pla, time);
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
		toOrigin.apply(cam, time);
		if (!cam.isEyeFixed()) {
			Point3d eye = cam.getEyePoint();
			rotation.transform(eye);
		}
		if (!cam.isLookAtFixed()) {
			Point3d lookAt = cam.getLookAtPoint();
			rotation.transform(lookAt);
		}
		toCenter.apply(cam, time);
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
		toOrigin.apply(light, time);
		Point3d pt = light.getPointLight();
		rotation.transform(pt);
		toCenter.apply(light, time);
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
		toOrigin.apply(light, time);
		if (!light.isLightPointFixed()) {
			Point3d pt = light.getPointLight();
			rotation.transform(pt);
			light.setPointLight(pt);
		}
		if (!light.isLookAtPointFixed()) {
			Point3d pt = light.getLookAt();
			rotation.transform(pt);
			light.setLookAt(pt);
		}
		toCenter.apply(light, time);
	}
}
