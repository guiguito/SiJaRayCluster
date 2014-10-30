/*
 * Sphere.java
 *
 * Created on 11 décembre 2005, 20:02
 */

package RaytracedObjects;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Mapping.AlphaMapping;
import Mapping.BumpMapping;
import Mapping.ReflectionMapping;
import Mapping.TextureMapping;
import Materials.Material;


/**
 * A Sphere object to put in a scene to be raytraced.
 * 
 * @author Guilhem Duché
 */
public class Sphere extends RaytracedObject {

	private double radius;
	private Point3d center;
	protected TextureMapping texture;
	protected BumpMapping bumpMap;
	protected AlphaMapping alphaMap;
	protected ReflectionMapping reflectMap;

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
	public Sphere(double rad, Point3d cen, Material c) {
		super(c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 */
	public Sphere(long begin, long end, double rad, Point3d cen, Material c) {
		super(begin, end, c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param text
	 *            the texture we want to aplly on the sphere. null if you dont
	 *            want any.
	 */
	public Sphere(double rad, Point3d cen, Material c, TextureMapping text) {
		super(c, true);
		radius = rad;
		center = cen;
		texture = text;
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param text
	 *            the texture we want to aplly on the sphere. null if you dont
	 *            want any.
	 */
	public Sphere(long begin, long end, double rad, Point3d cen, Material c,
			TextureMapping text) {
		super(begin, end, c, true);
		radius = rad;
		center = cen;
		texture = text;
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param bump
	 *            the BumpMapping we want to apply on the sphere. null if you
	 *            dont want any.
	 */
	public Sphere(double rad, Point3d cen, Material c, BumpMapping bump) {
		super(c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = bump;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param bump
	 *            the texture we want to aplly on the sphere. null if you dont
	 *            want any.
	 */
	public Sphere(long begin, long end, double rad, Point3d cen, Material c,
			BumpMapping bump) {
		super(begin, end, c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = bump;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 */
	public Sphere(double rad, Point3d cen, Material c, AlphaMapping alpha) {
		super(c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = null;
		alphaMap = alpha;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 */
	public Sphere(long begin, long end, double rad, Point3d cen, Material c,
			AlphaMapping alpha) {
		super(begin, end, c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = null;
		alphaMap = alpha;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 */
	public Sphere(double rad, Point3d cen, Material c, ReflectionMapping reflec) {
		super(c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = null;
		alphaMap = null;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 */
	public Sphere(long begin, long end, double rad, Point3d cen, Material c,
			ReflectionMapping reflec) {
		super(begin, end, c, true);
		radius = rad;
		center = cen;
		texture = null;
		bumpMap = null;
		alphaMap = null;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param text
	 *            the texture we want to aplly on the sphere. null if you dont
	 *            want any.
	 * @param bump
	 *            the BumpMapping we want to apply on the sphere. null if you
	 *            dont want any.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 */
	public Sphere(double rad, Point3d cen, Material c, TextureMapping text,
			BumpMapping bump, AlphaMapping alpha, ReflectionMapping reflec) {
		super(c, true);
		radius = rad;
		center = cen;
		texture = text;
		bumpMap = bump;
		alphaMap = alpha;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param text
	 *            the texture we want to aplly on the sphere. null if you dont
	 *            want any.
	 * @param bump
	 *            the BumpMapping we want to apply on the sphere. null if you
	 *            dont want any.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 */
	public Sphere(long begin, long end, double rad, Point3d cen, Material c,
			TextureMapping text, BumpMapping bump, AlphaMapping alpha,
			ReflectionMapping reflec) {
		super(begin, end, c, true);
		radius = rad;
		center = cen;
		texture = text;
		bumpMap = bump;
		alphaMap = alpha;
		reflectMap = reflec;
	}

	/**
	 * Set the texture of the object.
	 * 
	 * @param text
	 *            the texture to associate with the object.
	 */
	public void setTextureMap(TextureMapping text) {
		texture = text;
	}

	/**
	 * Set the BumpMap of the object.
	 * 
	 * @param bump
	 *            the BumpMap to associate with the object.
	 */
	public void setBumpMap(BumpMapping bump) {
		bumpMap = bump;
	}

	/**
	 * Set the AlphaMap of the object.
	 * 
	 * @param alpha
	 *            the AlphaMap to associate with the object.
	 */
	public void setAlphaMap(AlphaMapping alpha) {
		alphaMap = alpha;
	}

	/**
	 * Set the ReflectionMap of the object.
	 * 
	 * @param reflec
	 *            the ReflectionMap to associate with the object.
	 */
	public void setReflectionMap(ReflectionMapping reflec) {
		reflectMap = reflec;
	}

	/**
	 * Set the center of the sphere.
	 * 
	 * @param pt
	 *            the new center of the sphere.
	 */
	public void setCenter(Point3d pt) {
		center = pt;
	}

	/**
	 * Set the radius of the sphere.
	 * 
	 * @param rad
	 *            the new radius of the sphere.
	 */
	public void setRadius(double rad) {
		radius = rad;
	}

	/**
	 * Get the radius of the sphere.
	 * 
	 * @return the current radius of the sphere.
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Get the center point of the Sphere.
	 * 
	 * @return the center point of the sphere.
	 */
	public Point3d getCenter() {
		return center;
	}

	/**
	 * give the distance with the nearest intersection of the object or -1.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @param obj
	 *            the object from where the ray is launched, null if from
	 *            camera.
	 * @return information on the intersection of the object or null if no
	 *         intersection.
	 */
	public IntersectionResult getNearestIntersection(Point3d eyePoint,
			Vector3d direction, RaytracedObject obj) {
		double b = 2 * (direction.x * (eyePoint.x - center.x) + direction.y
				* (eyePoint.y - center.y) + direction.z
				* (eyePoint.z - center.z));
		double c = (eyePoint.x - center.x) * (eyePoint.x - center.x)
				+ (eyePoint.y - center.y) * (eyePoint.y - center.y)
				+ (eyePoint.z - center.z) * (eyePoint.z - center.z) - radius
				* radius;

		double bcarr = b * b - 4 * c;
		if (bcarr < 0) {// no roots
			return null;
		} else if (bcarr == 0) {// one root or /*bcarr < 0.00000000001 && bcarr
								// > -0.00000000001*/
			double distance = (-1 * b) / 2;
			Point3d intersection = new Point3d(eyePoint.x + distance
					* direction.x, eyePoint.y + distance * direction.y,
					eyePoint.z + distance * direction.z);
			Vector3d normal = new Vector3d(intersection.x - center.x,
					intersection.y - center.y, intersection.z - center.z);
			normal.normalize();

			if (direction.dot(normal) > 0) {
				normal.negate();
			}
			if (obj == this && distance < 0.0001) {// 0.00000000001
				return null;
			} else {
				return returnIntersectionResult(intersection, normal, distance);
			}
		} else if (bcarr > 0) {// two roots
			double w1 = (-1 * b + Math.sqrt(bcarr)) / 2;
			double w2 = (-1 * b - Math.sqrt(bcarr)) / 2;
			if (w1 < 0 && w2 < 0) {// both negative not seen.
				return null;
			} else if (w1 < 0 || w2 < 0) {// one negative the other one
											// positive, keep the positive
				double distance = Math.max(w1, w2);
				Point3d intersection = new Point3d(eyePoint.x + distance
						* direction.x, eyePoint.y + distance * direction.y,
						eyePoint.z + distance * direction.z);
				Vector3d normal = new Vector3d(intersection.x - center.x,
						intersection.y - center.y, intersection.z - center.z);
				normal.normalize();
				if (direction.dot(normal) > 0) {
					normal.negate();
				}
				if (obj == this && distance < 0.0001) {// same object so it's
														// around off error
					return null;
				} else {
					return returnIntersectionResult(intersection, normal,
							distance);
				}
			} else {// both positive keep the smallest
				double distance = Math.min(w1, w2);
				double distance2 = Math.max(w1, w2);
				if (obj == this && distance < 0.0001) {// same object so it's
														// around off error
					Point3d intersection2 = new Point3d(eyePoint.x + distance2
							* direction.x,
							eyePoint.y + distance2 * direction.y, eyePoint.z
									+ distance2 * direction.z);
					Vector3d normal2 = new Vector3d(intersection2.x - center.x,
							intersection2.y - center.y, intersection2.z
									- center.z);
					normal2.normalize();
					if (direction.dot(normal2) > 0) {
						normal2.negate();
					}
					return returnIntersectionResult(intersection2, normal2,
							distance2);
				} else {
					Point3d intersection = new Point3d(eyePoint.x + distance
							* direction.x, eyePoint.y + distance * direction.y,
							eyePoint.z + distance * direction.z);
					Vector3d normal = new Vector3d(intersection.x - center.x,
							intersection.y - center.y, intersection.z
									- center.z);
					normal.normalize();
					if (direction.dot(normal) > 0) {
						normal.negate();
					}
					return returnIntersectionResult(intersection, normal,
							distance);
				}
			}
		} else {
			return null;
		}
	}// end method getNearestIntersection

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
		if (texture != null || bumpMap != null || alphaMap != null
				|| reflectMap != null) {
			double u = Math.asin(normal.x) / Math.PI + 0.5;
			double v = Math.asin(normal.y) / Math.PI + 0.5;
			if (texture != null) {
				texture.applyMapping(u, v, intersection, material2, normal2, w,
						this);
			}
			if (bumpMap != null) {
				Vector3d vec1 = new Vector3d(normal.y * normal.z, normal.x
						* normal.z, 2 * normal.x * normal.y);
				Vector3d vec2 = new Vector3d();
				vec2.cross(vec1, normal);
				bumpMap.setVectorOrigx(vec1);
				bumpMap.setVectorOrigz(vec2);
				bumpMap.applyMapping(u, v, intersection, material2, normal2, w,
						this);
			}
			if (alphaMap != null) {
				alphaMap.applyMapping(u, v, intersection, material2, normal2,
						w, this);
			}
			if (reflectMap != null) {
				reflectMap.applyMapping(u, v, intersection, material2, normal2,
						w, this);
			}
		}
		return new IntersectionResult(intersection, material2, normal2, w, this);
	}

}// end of class Sphere
