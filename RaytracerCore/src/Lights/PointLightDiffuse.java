/*
 * PointLightDiffuse.java
 *
 * Created on 1 février 2006, 16:48
 */

package Lights;

import javax.vecmath.Point3d;

import Materials.Color;

/**
 * A point light which light is less powerful with the distance.
 * 
 * @author Guilhem Duche
 */
public class PointLightDiffuse extends PointLight {

	double c1;
	double c2;
	double c3;

	/**
	 * Creates a new instance of PointLightDiffuse.
	 * 
	 * @param col
	 *            the color of the point light.
	 * @param xx
	 *            x coordinate of the point light.
	 * @param yy
	 *            y coordinate of the point light.
	 * @param zz
	 *            z coordinate of the point light.
	 * @param cc1
	 *            constant for attenuation.
	 * @param cc2
	 *            constant for attenuation.
	 * @param cc3
	 *            constant for attenuation.
	 */
	public PointLightDiffuse(Color col, double xx, double yy, double zz,
			double cc1, double cc2, double cc3) {
		super(col, xx, yy, zz);
		c1 = cc1;
		c2 = cc2;
		c3 = cc3;
	}

	/**
	 * Creates a new instance of PointLightDiffuse.
	 * 
	 * @param begin
	 *            the date of the beginning of the PointLightDiffuse's life.
	 * @param end
	 *            the date of the end of the PointLightDiffuse's life.
	 * @param col
	 *            the color of the point light.
	 * @param xx
	 *            x coordinate of the point light.
	 * @param yy
	 *            y coordinate of the point light.
	 * @param zz
	 *            z coordinate of the point light.
	 * @param cc1
	 *            constant for attenuation.
	 * @param cc2
	 *            constant for attenuation.
	 * @param cc3
	 *            constant for attenuation.
	 */
	public PointLightDiffuse(long begin, long end, Color col, double xx,
			double yy, double zz, double cc1, double cc2, double cc3) {
		super(begin, end, col, xx, yy, zz);
		c1 = cc1;
		c2 = cc2;
		c3 = cc3;
	}

	/**
	 * Get the color lightw when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return the color seen from the intersection point.
	 */
	public Color getColor(Point3d inter) {
		double distance = inter.distance(light);
		double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
				* distance));
		double red = color.getRed() * fd;
		double green = color.getGreen() * fd;
		double blue = color.getBlue() * fd;
		return new Color(red, green, blue);
	}

	/**
	 * Get the red component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return red component of the light
	 */
	public double getRed(Point3d inter) {
		double distance = inter.distance(light);
		double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
				* distance));
		return color.getRed() * fd;
	}

	/**
	 * Get the green component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return green component oif the light
	 */
	public double getGreen(Point3d inter) {
		double distance = inter.distance(light);
		double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
				* distance));
		return color.getGreen() * fd;
	}

	/**
	 * Get the blue component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return blue component of the light
	 */
	public double getBlue(Point3d inter) {
		double distance = inter.distance(light);
		double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
				* distance));
		return color.getBlue() * fd;
	}

}
