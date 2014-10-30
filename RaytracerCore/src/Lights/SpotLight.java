/*
 * SpotLight.java
 *
 * Created on 1 février 2006, 16:48
 */

package Lights;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Color;


/**
 * A spot light.
 * 
 * @author Guilhem Duche
 */
public class SpotLight extends PointLight {

	double angle;
	Vector3d orientation;
	Point3d lookAt;
	boolean lightFixed;
	boolean lookAtFixed;

	/**
	 * Creates a new instance of SpotLight.
	 * 
	 * @param col
	 *            the color of the SpotLight.
	 * @param xx
	 *            x coordinate of the SpotLight.
	 * @param yy
	 *            y coordinate of the SpotLight.
	 * @param zz
	 *            z coordinate of the SpotLight.
	 * @param ang
	 *            the half-angle of aperture.
	 * @param vect
	 *            direction of enlightment.
	 */
	public SpotLight(Color col, double xx, double yy, double zz, double ang,
			Vector3d vect) {
		super(col, xx, yy, zz);
		angle = Math.toRadians(ang);
		orientation = vect;
		lookAt = new Point3d(xx + vect.x, yy + vect.y, zz + vect.z);
		lightFixed = false;
		lookAtFixed = false;
	}

	/**
	 * Creates a new instance of SpotLight.
	 * 
	 * @param begin
	 *            the date of the beginning of the SpotLight's life.
	 * @param end
	 *            the date of the end of the SpotLight's life.
	 * @param col
	 *            the color of the SpotLight.
	 * @param xx
	 *            x coordinate of the SpotLight.
	 * @param yy
	 *            y coordinate of the SpotLight.
	 * @param zz
	 *            z coordinate of the SpotLight.
	 * @param ang
	 *            the half-angle of aperture.
	 * @param vect
	 *            direction of enlightment.
	 */
	public SpotLight(long begin, long end, Color col, double xx, double yy,
			double zz, double ang, Vector3d vect) {
		super(begin, end, col, xx, yy, zz);
		angle = Math.toRadians(ang);
		orientation = vect;
		lookAt = new Point3d(xx + vect.x, yy + vect.y, zz + vect.z);
		lightFixed = false;
		lookAtFixed = false;
	}

	/**
	 * Creates a new instance of SpotLight.
	 * 
	 * @param col
	 *            the color of theSpotLight.
	 * @param xx
	 *            x coordinate of the SpotLight.
	 * @param yy
	 *            y coordinate of the SpotLight.
	 * @param zz
	 *            z coordinate of the SpotLight.
	 * @param ang
	 *            the half-angle of aperture.
	 * @param look
	 *            the point enlighted.
	 */
	public SpotLight(Color col, double xx, double yy, double zz, double ang,
			Point3d look) {
		super(col, xx, yy, zz);
		angle = Math.toRadians(ang);
		orientation = new Vector3d(look.x - light.x, look.y - light.y, look.z
				- light.z);
		lookAt = look;
		lightFixed = false;
		lookAtFixed = false;
	}

	/**
	 * Creates a new instance of SpotLight.
	 * 
	 * @param begin
	 *            the date of the beginning of the SpotLight's life.
	 * @param end
	 *            the date of the end of the SpotLight's life.
	 * @param col
	 *            the color of the SpotLight.
	 * @param xx
	 *            x coordinate of the SpotLight.
	 * @param yy
	 *            y coordinate of the SpotLight.
	 * @param zz
	 *            z coordinate of the SpotLight.
	 * @param ang
	 *            the half-angle of aperture.
	 * @param look
	 *            the point enlighted.
	 */
	public SpotLight(long begin, long end, Color col, double xx, double yy,
			double zz, double ang, Point3d look) {
		super(begin, end, col, xx, yy, zz);
		angle = Math.toRadians(ang);
		orientation = new Vector3d(look.x - light.x, look.y - light.y, look.z
				- light.z);
		lookAt = look;
		lightFixed = false;
		lookAtFixed = false;
	}

	/**
	 * Mark the light point as considered as fixed.
	 */
	public void fixLight() {
		lightFixed = true;
	}

	/**
	 * Mark the lookAt point as considered as fixed.
	 */
	public void fixLookAt() {
		lookAtFixed = true;
	}

	/**
	 * Mark the light point as considered as free.
	 */
	public void freeLight() {
		lightFixed = false;
	}

	/**
	 * Mark the look atpoint as considered as free.
	 */
	public void freeLookAt() {
		lightFixed = false;
	}

	/**
	 * Know if the light point is considered as fixed.
	 * 
	 * @return true if it's considered as fixed. False otherwise.
	 */
	public boolean isLightPointFixed() {
		return lightFixed;
	}

	/**
	 * Know if the lookAt point is considered as fixed.
	 * 
	 * @return true if it's considered as fixed. False otherwise.
	 */
	public boolean isLookAtPointFixed() {
		return lookAtFixed;
	}

	/**
	 * Get the lookAtPoint.
	 */
	public Point3d getLookAt() {
		return lookAt;
	}

	/**
	 * Set the lookAtPoint.
	 * 
	 * @param look
	 *            the new look at Point.
	 */
	public void setLookAt(Point3d look) {
		lookAt = look;
		orientation = new Vector3d(look.x - light.x, look.y - light.y, look.z
				- light.z);
	}

	/**
	 * Set the position of the PointLight.
	 * 
	 * @param xx
	 *            the new poition.
	 */
	public void setPointLight(Point3d xx) {
		light = xx;
		orientation = new Vector3d(lookAt.x - light.x, lookAt.y - light.y,
				lookAt.z - light.z);
	}

	/**
	 * Get the color lightw when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return the color seen from the intersection point.
	 */
	public Color getColor(Point3d inter) {
		Vector3d intersectionLight = new Vector3d(inter.x - light.x, inter.y
				- light.y, inter.z - light.z);
		if (orientation.angle(intersectionLight) <= angle) {
			return color;
		} else {
			return new Color(0, 0, 0);
		}
	}

	/**
	 * Get the red component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return red component of the light
	 */
	public double getRed(Point3d inter) {
		Vector3d intersectionLight = new Vector3d(inter.x - light.x, inter.y
				- light.y, inter.z - light.z);
		if (orientation.angle(intersectionLight) <= angle) {
			return color.red;
		} else {
			return 0;
		}
	}

	/**
	 * Get the green component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return green component oif the light
	 */
	public double getGreen(Point3d inter) {
		Vector3d intersectionLight = new Vector3d(inter.x - light.x, inter.y
				- light.y, inter.z - light.z);
		if (orientation.angle(intersectionLight) <= angle) {
			return color.green;
		} else {
			return 0;
		}
	}

	/**
	 * Get the blue component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return blue component of the light
	 */
	public double getBlue(Point3d inter) {
		Vector3d intersectionLight = new Vector3d(inter.x - light.x, inter.y
				- light.y, inter.z - light.z);
		if (orientation.angle(intersectionLight) <= angle) {
			return color.blue;
		} else {
			return 0;
		}
	}
}