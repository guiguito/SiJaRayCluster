/*
 * SpecialFloor2.java
 *
 * Created on 23 janvier 2006, 17:26
 */

package RaytracedObjects;

import javax.vecmath.Point3d;

import Materials.Material;

/**
 * Special Floor 2. texture in circle.Will do a floor, parallel to xz plane.Can
 * be rotated.
 * 
 * @author guilhem duche
 */
public class SpecialFloor2 extends Rectangle {

	private Material baseMaterial2;
	private Point3d origin;
	private Point3d center;
	private double sizeCircles;

	/**
	 * Creates a new instance of SpecialFloor2.
	 * 
	 * @param orig
	 *            the point of origin of the floor.
	 * @param width1
	 *            the width of the floor.
	 * @param height1
	 *            the height of the floor.
	 * @param base1
	 *            the base material 1.
	 * @param base2
	 *            the base material 2.
	 * @param alternance
	 *            the size of circles.
	 */
	public SpecialFloor2(Point3d orig, double width1, double height1,
			Material base1, Material base2, double alternance)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, width1, height1, base1, false);
		baseMaterial2 = base2;
		sizeCircles = alternance;
		center = new Point3d(origin.x + width1 / 2, origin.y, origin.z
				+ height1 / 2);
	}

	/**
	 * Creates a new instance of SpecialFloor2.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the point of origin of the floor.
	 * @param width1
	 *            the width of the floor.
	 * @param height1
	 *            the height of the floor.
	 * @param base1
	 *            the base material 1.
	 * @param base2
	 *            the base material 2.
	 * @param alternance
	 *            the size of circles.
	 */
	public SpecialFloor2(long begin, long end, Point3d orig, double width1,
			double height1, Material base1, Material base2, double alternance)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, width1, height1, base1, false);
		baseMaterial2 = base2;
		sizeCircles = alternance;
		center = new Point3d(origin.x + width1 / 2, origin.y, origin.z
				+ height1 / 2);
	}

	/**
	 * Get the IntersectionResult. This method is overriden to create the
	 * special shader.
	 * 
	 * @param intersection
	 *            the intersection point.
	 * @param distance
	 *            the distance between the eyePoint and the intersection.
	 * @return the IntersectionResult with the right parameters.
	 */
	IntersectionResult returnIntersectionResult(Point3d intersection, double w,
			RaytracedObject obj) {
		double distance = intersection.distance(center);
		double a = distance / sizeCircles;
		if (even((int) a)) {
			return new IntersectionResult(intersection, material, normal, w,
					obj);
		} else {
			return new IntersectionResult(intersection, baseMaterial2, normal,
					w, obj);
		}
	}

	/**
	 * Return true if a is odd.
	 * 
	 * @param a
	 *            the integer to test.
	 * @return true if a is .
	 */
	private boolean even(int a) {
		return (a % 2) == 0;
	}

}
