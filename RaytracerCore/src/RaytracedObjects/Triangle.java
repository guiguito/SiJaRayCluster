/*
 * Triangle.java
 *
 * Created on 29 janvier 2006, 16:57
 *
 */

package RaytracedObjects;

import java.util.ArrayList;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * A class to add trinagles in the scene.
 * 
 * @author Guilhem Duche
 */
public class Triangle extends RaytracedObject {

	Point3d point1;
	Point3d point2;
	Point3d point3;
	Vector3d normal;
	double lastu;
	double lastv;
	double lastt;

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param pt1
	 *            first point of the trianle.
	 * @param pt2
	 *            second point of the trianle.
	 * @param pt3
	 *            third point of the trianle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Triangle(Point3d pt1, Point3d pt2, Point3d pt3, Material col,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(col, inter);
		point1 = pt1;
		point2 = pt2;
		point3 = pt3;
		Vector3d vect1 = new Vector3d(point1.x - point2.x, point1.y - point2.y,
				point1.z - point2.z);
		Vector3d vect2 = new Vector3d(point1.x - point3.x, point1.y - point3.y,
				point1.z - point3.z);
		normal = new Vector3d();
		normal.cross(vect2, vect1);// to invert if necessary
		normal.normalize();
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param pt1
	 *            first point of the trianle.
	 * @param pt2
	 *            second point of the trianle.
	 * @param pt3
	 *            third point of the trianle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Triangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Material col, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, col, inter);
		point1 = pt1;
		point2 = pt2;
		point3 = pt3;
		Vector3d vect1 = new Vector3d(point1.x - point2.x, point1.y - point2.y,
				point1.z - point2.z);
		Vector3d vect2 = new Vector3d(point1.x - point3.x, point1.y - point3.y,
				point1.z - point3.z);
		normal = new Vector3d();
		normal.cross(vect2, vect1);// to invert if necessary
		normal.normalize();
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
		double pnd = normal.dot(direction);
		if (obj == null && pnd > 0) {// launched from the eyepoint and normal
										// badly oriented adapt it.
			normal.negate();
		}

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
			return null;
		double inv_det = 1.0 / det;

		// calculate distance from vert0 to ray origin
		Vector3d tvec = new Vector3d(eyePoint);
		tvec.sub(point1);

		// calculate U parameter and test bounds
		double u = tvec.dot(pvec) * inv_det;
		if (u < 0 || u > 1)
			return null;

		// prepare to test V parameter
		Vector3d qvec = new Vector3d();
		qvec.cross(tvec, edge1);

		// calculate V parameter and test bounds
		double v = direction.dot(qvec) * inv_det;
		if (v < 0 || (u + v > 1))
			return null;

		// calculate t, ray intersects triangle
		double t = edge2.dot(qvec) * inv_det;

		lastu = u;
		lastv = v;
		lastt = 1 - u - v;

		Point3d intersection = new Point3d(eyePoint.x + t * direction.x,
				eyePoint.y + t * direction.y, eyePoint.z + t * direction.z);
		if (t > 0.001 && obj != this) {
			return returnIntersectionResult(intersection, t);
		} else {// object too close or ray launched from the same polygon
			return null;
		}
	}

	/**
	 * Get the IntersectionResult. Override this method if you want to put
	 * shaders on this triangle.
	 * 
	 * @param intersection
	 *            the intersection point.
	 * @param distance
	 *            the distance between the eyePoint and the intersection.
	 * @return the IntersectionResult with the right parameters.
	 */
	IntersectionResult returnIntersectionResult(Point3d intersection, double w) {
		return new IntersectionResult(intersection, material, normal, w, this);
	}

	/**
	 * Update the parameters of the traingle when points have been changed.
	 */
	public void updateParameters() {
		// compute the normal with the first 3 points
		Vector3d vect1 = new Vector3d(point1.x - point2.x, point1.y - point2.y,
				point1.z - point2.z);
		Vector3d vect2 = new Vector3d(point1.x - point3.x, point1.y - point3.y,
				point1.z - point3.z);
		normal = new Vector3d();
		normal.cross(vect2, vect1);// to invert if necessary
		normal.normalize();
	}

	/**
	 * Get the normal of the triangle.
	 * 
	 * @return the normal of throws triangle.
	 */
	public Vector3d getNormal() {
		return normal;
	}

	public double getLastu() {
		return lastu;
	}

	public double getLastv() {
		return lastv;
	}

	double getLastt() {
		return lastt;
	}

	/**
	 * Get the points of the triangle.
	 * 
	 * @return an Arraylist of the points.
	 */
	public ArrayList<Point3d> getPointsList() {
		ArrayList<Point3d> arr = new ArrayList<Point3d>();
		arr.add(point1);
		arr.add(point2);
		arr.add(point3);
		return arr;
	}

}// end class Triangle