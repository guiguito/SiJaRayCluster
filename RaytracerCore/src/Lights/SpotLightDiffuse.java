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
public class SpotLightDiffuse extends SpotLight {

	double c1;
	double c2;
	double c3;

	/**
	 * Creates a new instance of SpotLightDiffuse.
	 * 
	 * @param col
	 *            the color of the SpotLightDiffuse.
	 * @param xx
	 *            x coordinate of the SpotLightDiffuse.
	 * @param yy
	 *            y coordinate of the SpotLightDiffuse.
	 * @param zz
	 *            z coordinate of the SpotLightDiffuse.
	 * @param cc1
	 *            constant for attenuation.
	 * @param cc2
	 *            constant for attenuation.
	 * @param cc3
	 *            constant for attenuation.
	 */
	public SpotLightDiffuse(Color col, double xx, double yy, double zz,
			double ang, Vector3d vect, double cc1, double cc2, double cc3) {
		super(col, xx, yy, zz, ang, vect);
		c1 = cc1;
		c2 = cc2;
		c3 = cc3;
	}

	/**
	 * Creates a new instance of SpotLightDiffuse
	 * 
	 * @param begin
	 *            the date of the beginning of the SpotLightDiffuse's life.
	 * @param end
	 *            the date of the end of the SpotLightDiffuse's life.
	 * @param col
	 *            the color of the SpotLightDiffuse.
	 * @param xx
	 *            x coordinate of the SpotLightDiffuse.
	 * @param yy
	 *            y coordinate of the SpotLightDiffuse.
	 * @param zz
	 *            z coordinate of the SpotLightDiffuse.
	 * @param cc1
	 *            constant for attenuation.
	 * @param cc2
	 *            constant for attenuation.
	 * @param cc3
	 *            constant for attenuation.
	 */
	public SpotLightDiffuse(long begin, long end, Color col, double xx,
			double yy, double zz, double ang, Vector3d vect, double cc1,
			double cc2, double cc3) {
		super(begin, end, col, xx, yy, zz, ang, vect);
		c1 = cc1;
		c2 = cc2;
		c3 = cc3;
	}

	/**
	 * Creates a new instance of SpotLightDiffuse.
	 * 
	 * @param col
	 *            the color of the SpotLightDiffuse.
	 * @param xx
	 *            x coordinate of the SpotLightDiffuse.
	 * @param yy
	 *            y coordinate of the SpotLightDiffuse.
	 * @param zz
	 *            z coordinate of the SpotLightDiffuse.
	 * @param ang
	 *            the half-angle of aperture.
	 * @param look
	 *            the point enlighted.
	 * @param cc1
	 *            constant for attenuation.
	 * @param cc2
	 *            constant for attenuation.
	 * @param cc3
	 *            constant for attenuation.
	 */
	public SpotLightDiffuse(Color col, double xx, double yy, double zz,
			double ang, Point3d look, double cc1, double cc2, double cc3) {
		super(col, xx, yy, zz, ang, look);
		c1 = cc1;
		c2 = cc2;
		c3 = cc3;
	}

	/**
	 * Creates a new instance of SpotLightDiffuse.
	 * 
	 * @param begin
	 *            the date of the beginning of the SpotLightDiffuse's life.
	 * @param end
	 *            the date of the end of the SpotLightDiffuse's life.
	 * @param col
	 *            the color of theSpotLightDiffuse.
	 * @param xx
	 *            x coordinate of the SpotLightDiffuse.
	 * @param yy
	 *            y coordinate of the SpotLightDiffuse.
	 * @param zz
	 *            z coordinate of the SpotLightDiffuse.
	 * @param ang
	 *            the half-angle of aperture.
	 * @param look
	 *            the point enlighted.
	 * @param cc1
	 *            constant for attenuation.
	 * @param cc2
	 *            constant for attenuation.
	 * @param cc3
	 *            constant for attenuation.
	 */
	public SpotLightDiffuse(long begin, long end, Color col, double xx,
			double yy, double zz, double ang, Point3d look, double cc1,
			double cc2, double cc3) {
		super(begin, end, col, xx, yy, zz, ang, look);
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
		Vector3d intersectionLight = new Vector3d(inter.x - light.x, inter.y
				- light.y, inter.z - light.z);
		if (orientation.angle(intersectionLight) <= angle) {
			double distance = inter.distance(light);
			double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
					* distance));
			double red = color.getRed() * fd;
			double green = color.getGreen() * fd;
			double blue = color.getBlue() * fd;
			return new Color(red, green, blue);
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
			double distance = inter.distance(light);
			double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
					* distance));
			return color.getRed() * fd;
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
			double distance = inter.distance(light);
			double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
					* distance));
			return color.getGreen() * fd;
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
			double distance = inter.distance(light);
			double fd = Math.min(1, 1 / (c1 + c2 * distance + c3 * distance
					* distance));
			return color.getBlue() * fd;
		} else {
			return 0;
		}
	}

}