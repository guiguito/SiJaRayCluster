/*
 * CheckerBoardFloor.java
 *
 * Created on 19 janvier 2006, 01:07
 */

package RaytracedObjects;

import javax.vecmath.Point3d;

import Materials.Material;

/**
 * CheckerBoard floor. Will do a floor, parallel to xz plane with two materials.
 * 
 * @author Guilhem Duche
 */
public class CheckerBoardFloor extends Rectangle {

	private Material material2;
	private int rows;
	private int columns;
	private double sizeXChecker;
	private double sizeZChecker;

	/**
	 * CheckerBoard floor. Will do a floor, parallel to xz plane with two
	 * materials.
	 * 
	 * @param orig
	 *            the point of origin of the floor.
	 * @param width1
	 *            the width of the floor.
	 * @param height1
	 *            the height of the floor.
	 * @param mat1
	 *            the first material of the checker board.
	 * @param mat2
	 *            the second material of the checker board.
	 * @param col1
	 *            the number of columns on the checkerboard.
	 * @param row1
	 *            the number of rows on the checkerboard.
	 */
	public CheckerBoardFloor(Point3d orig, double width1, double height1,
			Material mat1, Material mat2, int col1, int row1)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, width1, height1, mat1, false);
		material2 = mat2;
		rows = row1;
		columns = col1;
		sizeXChecker = width / columns;
		sizeZChecker = height / rows;
	}

	/**
	 * CheckerBoard floor. Will do a floor, parallel to xz plane with two
	 * materials.
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
	 * @param mat1
	 *            the first material of the checker board.
	 * @param mat2
	 *            the second material of the checker board.
	 * @param col1
	 *            the number of columns on the checkerboard.
	 * @param row1
	 *            the number of rows on the checkerboard.
	 */
	public CheckerBoardFloor(long begin, long end, Point3d orig, double width1,
			double height1, Material mat1, Material mat2, int col1, int row1)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, width1, height1, mat1, false);
		material2 = mat2;
		rows = row1;
		columns = col1;
		sizeXChecker = width / columns;
		sizeZChecker = height / rows;
	}

	/**
	 * Get the IntersectionResult. This method is overriden to create the shader
	 * creating the checkerBoardFloor.
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
		int nbx = (int) (xAbs / sizeXChecker);
		int nbz = (int) (zAbs / sizeZChecker);
		if (even(nbx) && even(nbz)) {
			return new IntersectionResult(intersection, material, normal, w,
					obj);
		} else if (!even(nbx) && !even(nbz)) {
			return new IntersectionResult(intersection, material, normal, w,
					obj);
		} else {
			return new IntersectionResult(intersection, material2, normal, w,
					obj);
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

}// end class CheckerBoardFloor
