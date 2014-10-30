/*
 * BoundingSphere.java
 *
 * Created on 1 février 2006, 23:40
 */

package Utils;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * A bounding Sphere.
 * 
 * @author Guilhem Duche
 */
public class BoundingSphere extends BoundingObject {

	private double radius;
	private Point3d center;

	/**
	 * Creates a new instance of BoundingSphere.
	 * 
	 * @param close
	 *            the closest lowest point on the left. (x,y,z minima).
	 * @param far
	 *            the furthest highest point on the right. (x,y,z maxima).
	 */
	public BoundingSphere(Point3d close, Point3d far) {
		super();
		double distance = close.distance(far);
		radius = distance / 2.0;
		center = new Point3d((close.x + far.x) / 2.0, (close.y + far.y) / 2.0,
				(close.z + far.z) / 2.0);
	}

	/**
	 * Creates a new instance of BoundingSphere.
	 * 
	 * @param cent
	 *            centerof the bounding sphere..
	 * @param rad
	 *            radius of the bounding sphere.
	 */
	public BoundingSphere(Point3d cent, double rad) {
		super();
		radius = rad;
		center = cent;
	}

	/**
	 * Tell us if the bounding sphere is touched by the given ray.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @return true if touched false otherwise.
	 */
	public boolean isTouched(Point3d eyePoint, Vector3d direction) {
		double b = 2 * (direction.x * (eyePoint.x - center.x) + direction.y
				* (eyePoint.y - center.y) + direction.z
				* (eyePoint.z - center.z));
		double c = (eyePoint.x - center.x) * (eyePoint.x - center.x)
				+ (eyePoint.y - center.y) * (eyePoint.y - center.y)
				+ (eyePoint.z - center.z) * (eyePoint.z - center.z) - radius
				* radius;
		double bcarr = b * b - 4 * c;
		if (bcarr < 0) {// no roots
			return false;
		} else if (bcarr == 0) {// one root
			return true;
		} else if (bcarr > 0) {// two roots
			double w1 = (-1 * b + Math.sqrt(bcarr)) / 2;
			double w2 = (-1 * b - Math.sqrt(bcarr)) / 2;
			if (w1 < 0 && w2 < 0) {// both negative not seen.
				return false;
			} else {// one positive so it's seen
				return true;
			}
		} else {
			return false;
		}
	}
}
