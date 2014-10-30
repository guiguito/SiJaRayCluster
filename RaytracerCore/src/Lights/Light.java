/*
 * Light.java
 *
 * Created on 6 janvier 2006, 12:57
 *
 */
package Lights;

import javax.vecmath.Point3d;

import Materials.Color;
import Raytracer.RaytracerObject;

/**
 * Light object.
 * 
 * @author Guilhem Duche
 */
public abstract class Light extends RaytracerObject {

	Color color;

	/**
	 * Creates a new instance of Light.
	 * 
	 * @param col
	 *            color of the light
	 */
	public Light(Color col) {
		super();
		color = col;
	}

	/**
	 * Creates a new instance of Light.
	 * 
	 * @param begin
	 *            the date of the beginning of the light's life.
	 * @param end
	 *            the date of the end of the light's life.
	 * @param col
	 *            color of the light
	 */
	public Light(long begin, long end, Color col) {
		super(begin, end);
		color = col;
	}

	/**
	 * Get the color of the light.
	 * 
	 * @return the color of the light;
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Get the red component of the light.
	 * 
	 * @return red component of the light
	 */
	public double getRed() {
		return color.red;
	}

	/**
	 * Get the green component of the light.
	 * 
	 * @return green component oif the light
	 */
	public double getGreen() {
		return color.green;
	}

	/**
	 * Get the blue component of the light.
	 * 
	 * @return blue component of the light
	 */
	public double getBlue() {
		return color.blue;
	}

	/**
	 * Get the color light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return the color seen from the intersection point.
	 */
	public abstract Color getColor(Point3d inter);

	/**
	 * Get the red component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return red component of the light
	 */
	public abstract double getRed(Point3d inter);

	/**
	 * Get the green component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return green component oif the light
	 */
	public abstract double getGreen(Point3d inter);

	/**
	 * Get the blue component of the light when seen from a certain point.
	 * 
	 * @param inter
	 *            the point enlightened by the light.
	 * @return blue component of the light
	 */
	public abstract double getBlue(Point3d inter);
}
