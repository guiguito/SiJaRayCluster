/*
 * BoundingBoxFace.java
 *
 * Created on 1 février 2006, 22:26
 *
 */

package Utils;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * The face of a bounding box.
 * 
 * @author Guilhem Duche
 */
public class BoundingBoxFace {

	BoundingBoxTriangleFace t1;
	BoundingBoxTriangleFace t2;

	/**
	 * Creates a new instance of BoundingBoxFace.Points given clockwise to work.
	 * 
	 * @param pt1
	 *            first point.
	 * @param pt2
	 *            first point.
	 * @param pt3
	 *            first point.
	 * @param pt4
	 *            first point.
	 */
	public BoundingBoxFace(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4) {
		t1 = new BoundingBoxTriangleFace(pt1, pt2, pt3);
		t2 = new BoundingBoxTriangleFace(pt3, pt4, pt1);
	}

	/**
	 * Tell us if the face is touched.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @return true if touched false otherwise.
	 */
	public boolean isTouched(Point3d eyePoint, Vector3d direction) {
		if (t1.isTouched(eyePoint, direction))
			return true;
		if (t2.isTouched(eyePoint, direction))
			return true;
		return false;
	}

}
