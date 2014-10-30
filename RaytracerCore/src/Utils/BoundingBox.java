/*
 * BoundingBox.java
 *
 * Created on 1 février 2006, 22:34
 */

package Utils;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * A bounding box.
 * 
 * @author Guilhem Duche
 */
public class BoundingBox extends BoundingObject {

	private BoundingBoxFace face1;
	private BoundingBoxFace face2;
	private BoundingBoxFace face3;
	private BoundingBoxFace face4;
	private BoundingBoxFace face5;
	private BoundingBoxFace face6;

	/**
	 * Creates a new instance of BoundingBox.
	 * 
	 * @param close
	 *            the closest lowest point on the left. (x,y,z minima).
	 * @param far
	 *            the furthest highest point on the right. (x,y,z maxima).
	 */
	public BoundingBox(Point3d close, Point3d far) {
		super();
		double sizeX = far.x - close.x;
		double sizeY = far.y - close.y;
		double sizeZ = far.z - close.z;

		face1 = new BoundingBoxFace((Point3d) close.clone(), new Point3d(
				close.x, close.y + sizeY, close.z), new Point3d(
				close.x + sizeX, close.y + sizeY, close.z), new Point3d(close.x
				+ sizeX, close.y, close.z));

		face2 = new BoundingBoxFace((Point3d) close.clone(), new Point3d(
				close.x, close.y + sizeY, close.z), new Point3d(close.x,
				close.y + sizeY, close.z + sizeZ), new Point3d(close.x,
				close.y, close.z + sizeZ));

		face3 = new BoundingBoxFace(new Point3d(close.x, close.y, close.z
				+ sizeZ),
				new Point3d(close.x, close.y + sizeY, close.z + sizeZ),
				new Point3d(close.x + sizeX, close.y + sizeY, close.z + sizeZ),
				new Point3d(close.x + sizeX, close.y, close.z + sizeZ));

		face4 = new BoundingBoxFace(new Point3d(close.x + sizeX, close.y,
				close.z + sizeZ),
				new Point3d(close.x + sizeX, close.y, close.z), new Point3d(
						close.x + sizeX, close.y + sizeY, close.z),
				new Point3d(close.x + sizeX, close.y + sizeY, close.z + sizeZ));

		face5 = new BoundingBoxFace(new Point3d(close.x, close.y, close.z),
				new Point3d(close.x, close.y, close.z + sizeZ), new Point3d(
						close.x + sizeX, close.y, close.z + sizeZ),
				new Point3d(close.x + sizeX, close.y, close.z));

		face6 = new BoundingBoxFace(new Point3d(new Point3d(close.x, close.y
				+ sizeY, close.z)), new Point3d(close.x, close.y + sizeY,
				close.z + sizeZ), new Point3d(close.x + sizeX, close.y + sizeY,
				close.z + sizeZ), new Point3d(close.x + sizeX, close.y + sizeY,
				close.z));

	}

	/**
	 * Tell us if the bounding box is touched by the given ray.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of te ray.
	 * @return true if touched false otherwise.
	 */
	public boolean isTouched(Point3d eyePoint, Vector3d direction) {
		if (face1.isTouched(eyePoint, direction)) {
			return true;
		} else if (face2.isTouched(eyePoint, direction)) {
			return true;
		} else if (face3.isTouched(eyePoint, direction)) {
			return true;
		} else if (face4.isTouched(eyePoint, direction)) {
			return true;
		} else if (face5.isTouched(eyePoint, direction)) {
			return true;
		} else if (face6.isTouched(eyePoint, direction)) {
			return true;
		} else {
			return false;
		}
	}

}
