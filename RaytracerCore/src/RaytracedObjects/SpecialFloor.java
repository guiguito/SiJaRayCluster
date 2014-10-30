/*
 * SpecialFloor.java
 *
 * Created on 23 janvier 2006, 13:58
 */

package RaytracedObjects;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * Special floor with a shader texture.Will do a floor, parallel to xz plane.Can
 * be rotated.
 * 
 * @author guilhem duche
 */
public class SpecialFloor extends Rectangle {

	private double yScale;
	private double xScale;

	/**
	 * Creates a new instance of SpecialFloor.
	 * 
	 * @param orig
	 *            the point of origin of the floor.
	 * @param width1
	 *            the width of the floor.
	 * @param height1
	 *            the height of the floor.
	 * @param base
	 *            the base material.
	 * @param ySca
	 *            stretching on y axis.
	 * @param xSca
	 *            stretching on x and z axis.
	 */
	public SpecialFloor(Point3d orig, double width1, double height1,
			Material base, double ySca, double xSca)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, width1, height1, base, false);
		yScale = ySca;
		xScale = xSca;
	}

	/**
	 * Creates a new instance of SpecialFloor.
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
	 * @param ySca
	 *            stretching on y axis.
	 * @param xSca
	 *            stretching on x and z axis.
	 */
	public SpecialFloor(long begin, long end, Point3d orig, double width1,
			double height1, Material base, double ySca, double xSca)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, width1, height1, base, false);
		yScale = ySca;
		xScale = xSca;
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
		double yxHeight = origin.y + yScale * Math.cos(xAbs * xScale);
		double yzHeight = origin.y + yScale * Math.cos(zAbs * xScale);
		double goodXpi = goodPi(xAbs * xScale) / xScale;
		double goodZpi = goodPi(zAbs * xScale) / xScale;
		Vector3d newNormalx = new Vector3d();
		Vector3d newNormalz = new Vector3d();
		Point3d origPointx = new Point3d(goodXpi, origin.y, zAbs);
		Point3d endPointx = new Point3d(xAbs, yxHeight, zAbs);
		Point3d origPointz = new Point3d(xAbs, origin.y, goodZpi);
		Point3d endPointz = new Point3d(xAbs, yzHeight, zAbs);
		if (Math.cos(xAbs * xScale) >= 0) {
			newNormalx = new Vector3d(endPointx.x - origPointx.x, endPointx.y
					- origPointx.y, endPointx.z - origPointx.z);
		} else {
			newNormalx = new Vector3d(origPointx.x - endPointx.x, origPointx.y
					- endPointx.y, origPointx.z - endPointx.z);
		}
		if (Math.cos(zAbs * xScale) >= 0) {
			newNormalz = new Vector3d(endPointz.x - origPointz.x, endPointz.y
					- origPointz.y, endPointz.z - origPointz.z);
		} else {
			newNormalz = new Vector3d(origPointz.x - endPointz.x, origPointz.y
					- endPointz.y, origPointz.z - endPointz.z);
		}
		Vector3d newNormal = new Vector3d((newNormalx.x + newNormalz.x) / 2,
				(newNormalx.y + newNormalz.y) / 2,
				(newNormalx.z + newNormalz.z) / 2);
		newNormal.normalize();
		return new IntersectionResult(intersection, material, newNormal, w, obj);
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

}
