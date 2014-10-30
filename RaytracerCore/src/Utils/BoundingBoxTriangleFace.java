/*
 * BoundingBoxTriangleFace.java
 */

package Utils;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * Triangle for bounding box.
 * 
 * @author Guilhem Duché
 */
public class BoundingBoxTriangleFace {

	Point3d point1;
	Point3d point2;
	Point3d point3;

	/**
	 * Creates a new instance of BoundingBoxTriangleFace. Points given clockwise
	 * to work.
	 * 
	 * @param pt1
	 *            first point of the trianle.
	 * @param pt2
	 *            second point of the trianle.
	 * @param pt3
	 *            third point of the trianle.
	 */
	public BoundingBoxTriangleFace(Point3d pt1, Point3d pt2, Point3d pt3) {
		point1 = pt1;
		point2 = pt2;
		point3 = pt3;
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
		// find vectors for two edges sharing vert0
		Vector3d edge1 = new Vector3d(point2);
		edge1.sub(point1);
		Vector3d edge2 = new Vector3d(point3);
		edge2.sub(point1);

		// begin calculating determinant - also used to calculate U parameter
		Vector3d pvec = new Vector3d();
		pvec.cross(direction, edge2);

		// if determinant is near zero, ray lies in plane of triangle
		double det = edge1.dot(pvec);

		if (det > -0.0001 && det < 0.0001)
			return false;
		double inv_det = 1.0 / det;

		// calculate distance from vert0 to ray origin
		Vector3d tvec = new Vector3d(eyePoint);
		tvec.sub(point1);

		// calculate U parameter and test bounds
		double u = tvec.dot(pvec) * inv_det;
		if (u < 0 || u > 1)
			return false;

		// prepare to test V parameter
		Vector3d qvec = new Vector3d();
		qvec.cross(tvec, edge1);

		// calculate V parameter and test bounds
		double v = direction.dot(qvec) * inv_det;
		if (v < 0 || (u + v > 1))
			return false;

		// calculate t, ray intersects triangle
		double t = edge2.dot(qvec) * inv_det;

		Point3d intersection = new Point3d(eyePoint.x + t * direction.x,
				eyePoint.y + t * direction.y, eyePoint.z + t * direction.z);
		if (t > 0.0001) {
			return true;
		} else {// object too close or ray launched from the same polygon
			return false;
		}
	}

}
