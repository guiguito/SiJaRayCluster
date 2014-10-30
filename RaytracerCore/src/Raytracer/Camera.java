/*
 * Camera.java
 *
 * Created on 11 décembre 2005, 17:03
 */

package Raytracer;

import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * Stores information of a camera.
 * 
 * @author Guilhem Duché
 */
public class Camera extends RaytracerObject {

	private Point3d eye;
	private Point3d lookAt;
	private Vector3d up;
	private Vector3d u;
	private Vector3d v;
	private Vector3d n;
	private Matrix4d matrix;
	private double distanceEyeVpn;
	private double heightVpn;
	private boolean eyeFixed;
	private boolean lookAtFixed;

	/**
	 * Creates a new instance of Camera
	 * 
	 * @param eyePoint
	 *            the eyePoint in world coordinates. Origin of the rays.
	 * @param lookAtPoint
	 *            the point we look at world coordinates.
	 * @param upVector
	 *            the vector giving the up direction.
	 * @param dist
	 *            distance between eyepoint and view plane.
	 * @param size
	 *            height of the view plane.
	 */
	public Camera(Point3d eyePoint, Point3d lookAtPoint, Vector3d upVector,
			double dist, double size) {
		super();
		eye = eyePoint;
		lookAt = lookAtPoint;
		up = upVector;
		Point3d nouveau = ((Point3d) eye.clone());
		nouveau.sub(lookAt);
		n = new Vector3d(-nouveau.x, -nouveau.y, -nouveau.z);
		n.normalize();
		u = new Vector3d();
		u.cross(up, n);
		u.normalize();
		v = new Vector3d();
		v.cross(n, u);
		matrix = new Matrix4d(u.x, u.y, u.z, -1
				* u.dot(new Vector3d(eye.x, eye.y, eye.z)), v.x, v.y, v.z, -1
				* v.dot(new Vector3d(eye.x, eye.y, eye.z)), n.x, n.y, n.z, -1
				* n.dot(new Vector3d(eye.x, eye.y, eye.z)), 0, 0, 0, 1);
		distanceEyeVpn = dist;
		heightVpn = size;
		eyeFixed = false;
		lookAtFixed = false;
		;
	}

	/**
	 * Creates a new instance of Camera.
	 * 
	 * @param begin
	 *            the date of the beginning of the camera's life.
	 * @param end
	 *            the date of the end of the camera's life.
	 * @param eyePoint
	 *            the eyePoint in world coordinates. Origin of the rays.
	 * @param lookAtPoint
	 *            the point we look at world coordinates.
	 * @param upVector
	 *            the vector giving the up direction.
	 * @param dist
	 *            distance between eyepoint and view plane.
	 * @param size
	 *            height of the view plane.
	 */
	public Camera(long begin, long end, Point3d eyePoint, Point3d lookAtPoint,
			Vector3d upVector, double dist, double size) {
		super(begin, end);
		eye = eyePoint;
		lookAt = lookAtPoint;
		up = upVector;
		updateParameters();
		distanceEyeVpn = dist;
		heightVpn = size;
		eyeFixed = false;
		lookAtFixed = false;
	}

	/**
	 * Set the eye point as a fixed point.
	 */
	public void fixEyePoint() {
		eyeFixed = true;
	}

	/**
	 * Set the LookAt point as a fixed point.
	 */
	public void fixLookAtPoint() {
		lookAtFixed = true;
	}

	/**
	 * Free the eye point if it is a fixed point.
	 */
	public void freeEyePoint() {
		eyeFixed = false;
	}

	/**
	 * Free the LookAt point if it is a fixed point.
	 */
	public void freeLookAtPoint() {
		lookAtFixed = false;
	}

	/**
	 * Indicates is the eye point is considered as fixed.
	 * 
	 * @return true if the eye point is considered as fixed.
	 */
	public boolean isEyeFixed() {
		return eyeFixed;
	}

	/**
	 * Indicates is the lookAt point is considered as fixed.
	 * 
	 * @return true if the look At is considered as fixed.
	 */
	public boolean isLookAtFixed() {
		return lookAtFixed;
	}

	/**
	 * Get the look at point.
	 * 
	 * @return the lookAt point.
	 */
	public Point3d getLookAtPoint() {
		return lookAt;
	}

	/**
	 * Update the parameters of the camera when points have been changed.
	 */
	public void updateParameters() {
		// update paramaters
		Point3d nouveau = ((Point3d) eye.clone());
		nouveau.sub(lookAt);
		n = new Vector3d(-nouveau.x, -nouveau.y, -nouveau.z);
		n.normalize();
		u = new Vector3d();
		u.cross(up, n);
		u.normalize();
		v = new Vector3d();
		v.cross(n, u);
		matrix = new Matrix4d(u.x, u.y, u.z, -1
				* u.dot(new Vector3d(eye.x, eye.y, eye.z)), v.x, v.y, v.z, -1
				* v.dot(new Vector3d(eye.x, eye.y, eye.z)), n.x, n.y, n.z, -1
				* n.dot(new Vector3d(eye.x, eye.y, eye.z)), 0, 0, 0, 1);
	}

	/**
	 * z Give the matrix used to tranform a World Coordinate to a Camera
	 * Coordinate.
	 * 
	 * @return The matrix for the transformation.
	 */
	public Matrix4d getWorldToCameraTransform() {
		return matrix;
	}

	/**
	 * Give the matrix used to tranform a Camera Coordinate to a World
	 * Coordinate.
	 * 
	 * @return The matrix for the transformation.
	 */
	public Matrix4d getCameraToWorldTransform() {
		Matrix4d temp = (Matrix4d) matrix.clone();
		temp.invert();
		return temp;
	}

	/**
	 * Get the position of the eye.
	 * 
	 * @return the Point3d representing the position of the eye.
	 */
	public Point3d getEyePoint() {
		return eye;
	}

	/**
	 * Get the distance between eye Point and view plane.
	 * 
	 * @return distance between eye Point and view plane.
	 */
	public double getDistanceEyeVpn() {
		return distanceEyeVpn;
	}

	/**
	 * Get the height of the view plane.
	 * 
	 * @return height of the view plane.
	 */
	public double getHeightVpn() {
		return heightVpn;
	}

}
