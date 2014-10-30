/*
 * AmbientLight.java
 *
 * Created on 6 janvier 2006, 13:18
 */

package Lights;

import javax.vecmath.Point3d;

import Materials.Color;

/**
 * Class for an ambient light.
 * 
 * @author Guilhem Duche
 */
public class AmbientLight extends Light {

	/**
	 * Creates a new instance of AmbientLight.
	 * 
	 * @param col
	 *            the color of the ambient light
	 */
	public AmbientLight(Color col) {
		super(col);
	}

	/**
	 * Creates a new instance of AmbientLight.
	 * 
	 * @param begin
	 *            the date of the beginning of the AmbientLight's life.
	 * @param end
	 *            the date of the end of the AmbientLight's life.
	 * @param col
	 *            the color of the ambient light.
	 */
	public AmbientLight(long begin, long end, Color col) {
		super(begin, end, col);
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
