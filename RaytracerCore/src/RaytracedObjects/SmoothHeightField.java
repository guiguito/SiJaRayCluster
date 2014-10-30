/*
 * HeightField.java
 *
 * Created on 23 janvier 2006, 22:36
 */

package RaytracedObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * Class fora smooth height field object.
 * 
 * @author Guilhem Duche
 */
public class SmoothHeightField extends HeightField {

	HashMap<Point3d, Vector3d> avgNormals;
	HashMap<Point3d, ArrayList<Polygon>> associations;

	/**
	 * Creates a new instance of HeightField.
	 * 
	 * @param orig
	 *            the origin point of the heightField (middle bottom point).
	 * @param filename
	 *            the filename of the image.
	 * @param mat
	 *            the material to put on this heightField.
	 * @param hf
	 *            the multiplicative factor on height.
	 * @param xf
	 *            the multiplicative factor on x coordinates.
	 * @param zf
	 *            the multiplicative factor on z coordinates.
	 */
	public SmoothHeightField(Point3d orig, String filename, Material mat,
			double hf, double xf, double zf)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, filename, mat, hf, xf, zf);
		// calculate normals.
		avgNormals = new HashMap<Point3d, Vector3d>();
		associations = new HashMap<Point3d, ArrayList<Polygon>>();
		makeAssociations();
	}

	/**
	 * Creates a new instance of HeightField.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin point of the heightField (middle bottom point).
	 * @param filename
	 *            the filename of the image.
	 * @param mat
	 *            the material to put on this heightField.
	 * @param hf
	 *            the multiplicative factor on height.
	 * @param xf
	 *            the multiplicative factor on x coordinates.
	 * @param zf
	 *            the multiplicative factor on z coordinates.
	 */
	public SmoothHeightField(long begin, long end, Point3d orig,
			String filename, Material mat, double hf, double xf, double zf)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, filename, mat, hf, xf, zf);
		// calculate normals.
		avgNormals = new HashMap<Point3d, Vector3d>();
		associations = new HashMap<Point3d, ArrayList<Polygon>>();
		makeAssociations();
	}

	/**
	 * Make associations between point and polyons
	 */
	public void makeAssociations() {
		// create associations
		Iterator<Polygon> it = faces.iterator();
		// create associations points faces.
		while (it.hasNext()) {
			Polygon tri = it.next();
			ArrayList<Point3d> points = tri.getPointsList();
			Iterator<Point3d> pt = points.iterator();
			while (pt.hasNext()) {
				Point3d point = pt.next();
				ArrayList<Polygon> triangles = associations.get(point);
				if (triangles == null) {// no associations created yet.
					ArrayList<Polygon> list = new ArrayList<Polygon>();
					list.add(tri);
					associations.put(point, list);
				} else {// already an associaton add throws the triangl to this
						// association
					triangles.add(tri);
				}
			}
		}
		updateAveragedNormals();
	}

	/**
	 * Update the averaged normals for each point.
	 */
	public void updateAveragedNormals() {
		// calculate averaged normals
		avgNormals.clear();
		java.util.Set<Point3d> keys = associations.keySet();
		Iterator<Point3d> points = keys.iterator();
		while (points.hasNext()) {
			Point3d point = points.next();
			ArrayList<Polygon> triangles = associations.get(point);
			Vector3d vect = new Vector3d();
			Iterator<Polygon> tttt = triangles.iterator();
			int count = 0;
			while (tttt.hasNext()) {
				count++;
				Polygon pol = tttt.next();
				vect.add(pol.getNormal());
			}
			vect.scale(1.0 / count);
			avgNormals.put(point, vect);
		}
	}

	/**
	 * Get the IntersectionResult. Override this method if you want a special
	 * treatment of the result.
	 * 
	 * @param IntersectionResult
	 *            the result we have so far.
	 * @return the IntersectionResult with the right parameters.
	 */
	IntersectionResult returnIntersectionResult(IntersectionResult bestResul) {
		// updateAveragedNormals();
		if (bestResul != null) {
			Vector3d goodNormal = new Vector3d();
			Point3d intersection = bestResul.getIntersectionPoint();
			Triangle triangle = (Triangle) bestResul.getObjectTouched();
			ArrayList<Point3d> points = triangle.getPointsList();
			Vector3d vect1 = (Vector3d) avgNormals.get(points.get(0)).clone();
			vect1.scale(triangle.getLastt());
			goodNormal.add(vect1);
			Vector3d vect2 = (Vector3d) avgNormals.get(points.get(1)).clone();
			vect2.scale(triangle.getLastu());
			goodNormal.add(vect2);
			Vector3d vect3 = (Vector3d) avgNormals.get(points.get(2)).clone();
			vect3.scale(triangle.getLastv());
			goodNormal.add(vect3);
			goodNormal.normalize();
			return new IntersectionResult(intersection,
					bestResul.getMaterial(), goodNormal,
					bestResul.getDistance(), bestResul.getObjectTouched());
		} else {
			return null;
		}
	}

}

/**
 * Other Test to smooth the results. double distance1 =
 * 1.0/intersection.distance(points.get(0)); double distance2 =
 * 1.0/intersection.distance(points.get(1)); double distance3 =
 * 1.0/intersection.distance(points.get(2)); double total = distance1 +
 * distance2 + distance3; Vector3d vect1 =
 * (Vector3d)avgNormals.get(points.get(0)).clone();
 * vect1.scale(distance1/total); goodNormal.add(vect1); Vector3d vect2 =
 * (Vector3d)avgNormals.get(points.get(1)).clone();
 * vect2.scale(distance2/total); goodNormal.add(vect2); Vector3d vect3 =
 * (Vector3d)avgNormals.get(points.get(2)).clone();
 * vect3.scale(distance3/total); goodNormal.add(vect3); goodNormal.normalize();
 * //System.out.println(goodNormal.length());
 */

