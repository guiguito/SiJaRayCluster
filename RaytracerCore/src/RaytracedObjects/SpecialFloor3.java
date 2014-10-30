/*
 * SpecialFloor.java
 *
 * Created on 23 janvier 2006, 13:58
 */

package RaytracedObjects;

import javax.vecmath.Point3d;

import Materials.Color;
import Materials.Material;

/**
 * Special floor with a shader texture.Will do a floor, parallel to xz plane.
 * Can be rotated.
 * 
 * @author guilhem duche
 */
public class SpecialFloor3 extends Rectangle {

	private double xScale;
	private double beginRed;
	private double beginGreen;
	private double beginBlue;

	/**
	 * Creates a new instance of SpecialFloor3.
	 * 
	 * @param orig
	 *            the point of origin of the floor.
	 * @param width1
	 *            the width of the floor.
	 * @param height1
	 *            the height of the floor.
	 * @param base
	 *            the base material.
	 * @param xSca
	 *            the size of circles.
	 */
	public SpecialFloor3(Point3d orig, double width1, double height1,
			Material base, double xSca)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, width1, height1, base, false);
		xScale = xSca;
		beginRed = material.getAmbientColor().red;
		beginGreen = material.getAmbientColor().green;
		beginBlue = material.getAmbientColor().blue;
	}

	/**
	 * Creates a new instance of SpecialFloor3.
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
	 * @param base
	 *            the base material.
	 * @param xSca
	 *            the size of circles.
	 */
	public SpecialFloor3(long begin, long end, Point3d orig, double width1,
			double height1, Material base, double xSca)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, width1, height1, base, false);
		xScale = xSca;
		beginRed = material.getAmbientColor().red;
		beginGreen = material.getAmbientColor().green;
		beginBlue = material.getAmbientColor().blue;
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
		double zAbs = intersection.distance(new Point3d(intersection.x,
				origin.y, origin.z));
		double xAbs = intersection.distance(new Point3d(origin.x, origin.y,
				intersection.z));
		double yxHeight = Math.cos(xAbs * xScale);
		double yzHeight = Math.cos(zAbs * xScale);
		double avg = (yxHeight + yzHeight + 2.0) / 4.0;
		material.setAllColors(new Color(beginRed * avg, beginGreen * avg,
				beginBlue * avg));
		return new IntersectionResult(intersection, material, normal, w, obj);
	}

	/**
	 * Return true if a is odd.
	 * 
	 * @param a
	 *            .
	 * @return .
	 */
	private double goodPi(double a) {
		double coeff = a / Math.PI;
		// System.out.println(coeff);
		// System.out.println((int)coeff);
		if ((coeff - (int) coeff) >= 0.5) {
			return ((int) coeff + 1) * (Math.PI);
		} else {
			return ((int) coeff) * (Math.PI);
		}
	}
}// end class SpecialFloor3

