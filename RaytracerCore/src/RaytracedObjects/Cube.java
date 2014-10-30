/*
 * Cube.java
 *
 * Created on 1 février 2006, 22:17
 */

package RaytracedObjects;
import javax.vecmath.Point3d;

import Materials.Material;


/**
 * A cube.
 * 
 * @author Guilhem Duche
 */
public class Cube extends GeneralCube {

	/**
	 * Creates a new instance of Cube.
	 * 
	 * @param orig
	 *            the center point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param size
	 *            size of a face.
	 */
	public Cube(Point3d orig, Material mat, double size)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, mat, size, size, size);
	}

	/**
	 * Creates a new instance of Cube.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the center point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param size
	 *            size of a face.
	 */
	public Cube(long begin, long end, Point3d orig, Material mat, double size)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, mat, size, size, size);
	}

}
