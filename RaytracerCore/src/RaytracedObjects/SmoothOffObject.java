/*
 * OffObject.java
 *
 * Created on 29 janvier 2006, 21:47
 */

package RaytracedObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;

/**
 * Class to put an object read from a OFF file.
 * 
 * @author Guilhem Duche
 */
public class SmoothOffObject extends OffObject {

	HashMap<Point3d, Vector3d> avgNormals;
	HashMap<Point3d, ArrayList<Triangle>> associations;

	/**
	 * Creates a new instance of OffObject.
	 * 
	 * @param orig
	 *            the origin point.
	 * @param mat
	 *            the material to put on the object.
	 * @param file
	 *            the filename of the object.
	 * @param xSca
	 *            x scaling factor.
	 * @param ySca
	 *            y scaling factor.
	 * @param zSca
	 *            z scaling factor.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public SmoothOffObject(Point3d orig, Material mat, String file,
			double xSca, double ySca, double zSca, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, mat, file, xSca, ySca, zSca, inter);
		// calculate normals.
		avgNormals = new HashMap<Point3d, Vector3d>();
		associations = new HashMap<Point3d, ArrayList<Triangle>>();
		makeAssociations();
	}

	/**
	 * Creates a new instance of OffObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin point.
	 * @param mat
	 *            the material to put on the object.
	 * @param file
	 *            the filename of the object.
	 * @param xSca
	 *            x scaling factor.
	 * @param ySca
	 *            y scaling factor.
	 * @param zSca
	 *            z scaling factor.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public SmoothOffObject(long begin, long end, Point3d orig, Material mat,
			String file, double xSca, double ySca, double zSca, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, mat, file, xSca, ySca, zSca, inter);
		// calculate normals.
		avgNormals = new HashMap<Point3d, Vector3d>();
		associations = new HashMap<Point3d, ArrayList<Triangle>>();
		makeAssociations();
	}

	/**
	 * Make associations between point and polyons.
	 */
	public void makeAssociations() {
		// create associations
		Iterator<Polygon> it = faces.iterator();
		// create associations points faces.
		while (it.hasNext()) {
			Polygon popol = it.next();
			ArrayList<Triangle> tri = popol.getTrianglesList();
			Iterator<Triangle> flop = tri.iterator();
			while (flop.hasNext()) {
				Triangle tritri = flop.next();
				ArrayList<Point3d> points = tritri.getPointsList();
				Iterator<Point3d> pt = points.iterator();
				while (pt.hasNext()) {
					Point3d point = pt.next();
					ArrayList<Triangle> triangles = associations.get(point);
					if (triangles == null) {// no associations created yet.
						ArrayList<Triangle> list = new ArrayList<Triangle>();
						list.add(tritri);
						associations.put(point, list);
					} else {// already an associaton add throws the triangl to
							// this association
						triangles.add(tritri);
					}
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
			ArrayList<Triangle> triangles = associations.get(point);
			Vector3d vect = new Vector3d();
			Iterator<Triangle> tttt = triangles.iterator();
			int count = 0;
			while (tttt.hasNext()) {
				count++;
				Triangle pol = tttt.next();
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
