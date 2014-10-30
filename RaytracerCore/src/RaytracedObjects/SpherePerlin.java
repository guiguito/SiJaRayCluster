package RaytracedObjects;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Mapping.PerlinImprovedNoise;
import Materials.Color;
import Materials.Material;

/**
 * Class for spheres where we test perlin noise
 * 
 * @author Guilhem Duche
 */
public class SpherePerlin extends Sphere {

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 */
	public SpherePerlin(double rad, Point3d cen, Material c) {
		super(rad, cen, c);
	}

	/**
	 * Get the IntersectionResult. Override this method if you want to put
	 * shaders on throws polygon.
	 * 
	 * @param intersection
	 *            the intersection point.
	 * @param distance
	 *            the distance between the eyePoint and the intersection.
	 * @return the IntersectionResult with the right parameters.
	 */
	IntersectionResult returnIntersectionResult(Point3d intersection,
			Vector3d normal, double w) {
		Vector3d normal2 = (Vector3d) normal.clone();
		Material material2 = (Material) material.clone();

		double noise = (1 + PerlinImprovedNoise.noise(intersection.x,
				intersection.y, intersection.z)) / 2.0;

		double red = noise * material2.getAmbientColor().getRed();
		double green = noise * material2.getAmbientColor().getGreen();
		double blue = noise * material2.getAmbientColor().getBlue();
		material2.setAmbientColor(new Color(red, green, blue));

		red = noise * material2.getDiffuseColor().getRed();
		green = noise * material2.getDiffuseColor().getGreen();
		blue = noise * material2.getDiffuseColor().getBlue();
		material2.setDiffuseColor(new Color(red, green, blue));

		red = noise * material2.getSpecColor().getRed();
		green = noise * material2.getSpecColor().getGreen();
		blue = noise * material2.getSpecColor().getBlue();
		material2.setSpecularColor(new Color(red, green, blue));

		/*
		 * double noise =
		 * PerlinImprovedNoise.noise(intersection.x,intersection.y
		 * ,intersection.z); double dx = noise/intersection.x; double dy =
		 * noise/intersection.y; double dz = noise/intersection.z; Vector3d vect
		 * = new Vector3d(dx,dy,dz);
		 * 
		 * normal2.add(vect); normal2.normalize();
		 */

		return new IntersectionResult(intersection, material2, normal2, w, this);
	}

}
