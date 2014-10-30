/*
 * PolygonListObject.java
 *
 * Created on 1 février 2006, 21:40
 *
 */

package RaytracedObjects;
import java.util.ArrayList;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;


/**
 * Mother class for the objects made with a list of Polygon.
 * 
 * @author Guilhem Duche
 */
public abstract class PolygonListObject extends RaytracedObject {

	ArrayList<Polygon> faces;
	Point3d origin;

	/**
	 * Creates a new instance of PolygonListObject.
	 * 
	 * @param orig
	 *            the origin point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public PolygonListObject(Point3d orig, Material mat, boolean inter) {
		super(mat, inter);
		faces = new ArrayList<Polygon>();
		origin = orig;
	}

	/**
	 * Creates a new instance of PolygonListObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public PolygonListObject(long begin, long end, Point3d orig, Material mat,
			boolean inter) {
		super(begin, end, mat, inter);
		faces = new ArrayList<Polygon>();
		origin = orig;
	}

	/**
	 * Get the polygons used in this object.
	 * 
	 * @return the faces of the polygon.
	 */
	public ArrayList<Polygon> getPolygons() {
		return faces;
	}

	/**
	 * Get the origin of the object.
	 * 
	 * @return the origin of the object.
	 */
	public Point3d getOrigin() {
		return origin;
	}

	/**
	 * Set the origin of the object. ATTENTION : this method doesn't update the
	 * faces according to the new origin.
	 * 
	 * @param pt
	 *            the new origin point.
	 */
	public void setOrigin(Point3d pt) {
		origin = pt;
	}

	/**
	 * give the distance with the nearest intersection of the object or null.
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
		double dist = Double.MAX_VALUE;
		IntersectionResult bestResul = null;
		java.util.Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Polygon pol = it.next();
			IntersectionResult res = pol.getNearestIntersection(eyePoint,
					direction, obj);
			if (res != null && res.getDistance() < dist) {
				dist = res.getDistance();
				bestResul = res;
			}
		}
		return returnIntersectionResult(bestResul);
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
		return bestResul;
	}

}
