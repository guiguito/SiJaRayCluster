/*
 * PointLight.java
 *
 * Created on 6 janvier 2006, 13:20
 */

package Lights;

import javax.vecmath.Point3d;

import Materials.Color;

/**
 * A point light object.
 * 
 * @author Guilhem Duche
 */
public class PointLight extends Light {

	Point3d light;

	/**
	 * Creates a new instance of PointLight.
	 * 
	 * @param col
	 *            the color of the point light.
	 * @param xx
	 *            x coordinate of the point light.
	 * @param yy
	 *            y coordinate of the point light.
	 * @param zz
	 *            z coordinate of the point light.
	 */
	public PointLight(Color col, double xx, double yy, double zz) {
		super(col);
		light = new Point3d(xx, yy, zz);
	}

	/**
	 * Creates a new instance of PointLight.
	 * 
	 * @param begin
	 *            the date of the beginning of the PointLight's life.
	 * @param end
	 *            the date of the end of the PointLight's life.
	 * @param col
	 *            the color of the point light.
	 * @param xx
	 *            x coordinate of the point light.
	 * @param yy
	 *            y coordinate of the point light.
	 * @param zz
	 *            z coordinate of the point light.
	 */
	public PointLight(long begin, long end, Color col, double xx, double yy,
			double zz) {
		super(begin, end, col);
		light = new Point3d(xx, yy, zz);
	}

	/**
	 * Get the position of the LightPoint.
	 * 
	 * @return point of the PointLight.
	 */
	public Point3d getPointLight() {
		return light;
	}

	/**
	 * Set the position of the PointLight.
	 * 
	 * @param xx
	 *            the new poition.
	 */
	public void setPointLight(Point3d xx) {
		light = xx;
	}

	/**
	 * Get the color lightw when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return the color seen from the intersection point.
	 */
	public Color getColor(Point3d inter) {
		return color;
	}

	/**
	 * Get the red component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return red component of the light
	 */
	public double getRed(Point3d inter) {
		return color.red;
	}

	/**
	 * Get the green component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return green component oif the light
	 */
	public double getGreen(Point3d inter) {
		return color.green;
	}

	/**
	 * Get the blue component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return blue component of the light
	 */
	public double getBlue(Point3d inter) {
		return color.blue;
	}
}
