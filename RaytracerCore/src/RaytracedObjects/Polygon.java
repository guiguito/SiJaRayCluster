/*
 * Polygon.java
 *
 * Created on 11 décembre 2005, 20:04
 */

package RaytracedObjects;

import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * A Polygon object to put in a scene to be raytraced.
 * 
 * @author Guilhem Duché
 */
public class Polygon extends RaytracedObject {

	ArrayList<Triangle> triangles;
	ArrayList<Point3d> points;
	Vector3d normal;

	/**
	 * Creates a new instance of Polygon.
	 * 
	 * @param vect
	 *            a vector of Point3d that defines the polygon. They must be in
	 *            throws same plane and given clockwise.
	 * @param col
	 *            the material of the polygon.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Polygon(ArrayList<Point3d> vect, Material col, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(col, inter);
		if (vect.size() < 3) {
			throw new NotEnoughtPointsInPolygonException(vect.size());
		}
		points = vect;
		triangles = new ArrayList<Triangle>();
		Point3d firstPoint = vect.get(0);
		triangles.add(new Triangle(firstPoint, vect.get(1), vect.get(2), col,
				inter));
		Point3d lastPoint = vect.get(2);
		for (int i = 3; i < vect.size(); i++) {
			triangles.add(new Triangle(lastPoint, vect.get(i), firstPoint, col,
					inter));
			lastPoint = vect.get(i);
		}

		// get the normal from the first triangle.
		normal = triangles.get(0).getNormal();
		// normal.negate();

		// compute the shortest distance between the plane defined by the first
		// 3 points and the origin.
		double shortestDistanceToOrigin = -(normal.x * vect.get(0).x + normal.y
				* vect.get(0).y + normal.z * vect.get(0).z);

		double calc;
		// verify that all points are in the defined plane
		for (int i = 3; i < vect.size(); i++) {// starting with the fourth point
												// because the three first were
												// used to make the plane
												// equation
			calc = normal.x * vect.get(i).x + normal.y * vect.get(i).y
					+ normal.z * vect.get(i).z + shortestDistanceToOrigin;
			if (calc < -0.0001 || calc > 0.0001) {
				throw new PointNotInPolygonException(vect.get(i));
			}
		}
	}

	/**
	 * Creates a new instance of Polygon.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param vect
	 *            a vector of Point3d that defines the polygon. They must be in
	 *            throws same plane and given clockwise.
	 * @param col
	 *            the material of the polygon.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Polygon(long begin, long end, ArrayList<Point3d> vect, Material col,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, col, inter);
		if (vect.size() < 3) {
			throw new NotEnoughtPointsInPolygonException(vect.size());
		}
		points = vect;
		triangles = new ArrayList<Triangle>();
		Point3d firstPoint = vect.get(0);
		triangles.add(new Triangle(begin, end, firstPoint, vect.get(1), vect
				.get(2), col, inter));
		Point3d lastPoint = vect.get(2);
		for (int i = 3; i < vect.size(); i++) {
			triangles.add(new Triangle(begin, end, lastPoint, vect.get(i),
					firstPoint, col, inter));
			lastPoint = vect.get(i);
		}

		// get the normal from the first triangle.
		normal = triangles.get(0).getNormal();
		// normal.negate();

		// compute the shortest distance between the plane defined by the first
		// 3 points and the origin.
		double shortestDistanceToOrigin = -(normal.x * vect.get(0).x + normal.y
				* vect.get(0).y + normal.z * vect.get(0).z);

		double calc;
		// verify that all points are in the defined plane
		for (int i = 3; i < vect.size(); i++) {// starting with the fourth point
												// because the three first were
												// used to make the plane
												// equation
			calc = normal.x * vect.get(i).x + normal.y * vect.get(i).y
					+ normal.z * vect.get(i).z + shortestDistanceToOrigin;
			if (calc < -0.0001 || calc > 0.0001) {
				throw new PointNotInPolygonException(vect.get(i));
			}
		}
	}

	/**
	 * Get the the triangles of the polygon.
	 * 
	 * @return the triangles of the polygon.
	 */
	public ArrayList<Triangle> getTrianglesList() {
		return triangles;
	}

	/**
	 * Get the the points of the polygon.
	 * 
	 * @return the points of the polygon.
	 */
	public ArrayList<Point3d> getPointsList() {
		return points;
	}

	/**
	 * Get the normal.
	 * 
	 * @return the normal of the polygon.
	 */
	public Vector3d getNormal() {
		return normal;
	}

	/**
	 * Update the parameters of the polygon when points have been changed.
	 */
	public void updateParameters() throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		Iterator<Triangle> it = triangles.iterator();
		while (it.hasNext()) {
			Triangle t = it.next();
			t.updateParameters();
		}
		normal = triangles.get(0).getNormal();
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
		// go through all triangles.
		double dist = Double.MAX_VALUE;
		IntersectionResult bestResul = null;
		java.util.Iterator<Triangle> it = triangles.iterator();
		while (it.hasNext()) {
			Triangle pol = it.next();
			IntersectionResult res = pol.getNearestIntersection(eyePoint,
					direction, obj);
			if (res != null && res.getDistance() < dist) {
				dist = res.getDistance();
				bestResul = res;
			}
		}
		if (bestResul == null)
			return null;
		else
			return returnIntersectionResult(bestResul.getIntersectionPoint(),
					bestResul.getDistance(), bestResul.getObjectTouched());
	}

	/**
	 * Get the IntersectionResult. Override this method if you want to put
	 * shaders on the polygon.
	 * 
	 * @param intersection
	 *            the intersection point.
	 * @param distance
	 *            the distance between the eyePoint and the intersection.
	 * @return the IntersectionResult with the right parameters.
	 */
	IntersectionResult returnIntersectionResult(Point3d intersection, double w,
			RaytracedObject obj) {
		return new IntersectionResult(intersection, material, normal, w, obj);
	}

}// end class Polygon

