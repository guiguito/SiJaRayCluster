/*
 * BumpMapFloor.java
 *
 * Created on 14 février 2006, 15:18
 *
 */

package RaytracedObjects;

import java.awt.image.BufferedImage;

import javax.vecmath.Point3d;

import Mapping.BumpMapping;
import Materials.Material;

/**
 * A floor with Bump Mapping.
 * 
 * @author Guihem Duche
 */
public class BumpMapFloor extends Rectangle {

	private BufferedImage image;
	Point3d origx;
	Point3d origz;

	/**
	 * Creates a new instance of ImageFloor.
	 * 
	 * @param orig
	 *            the point of origin of the floor.
	 * @param width1
	 *            the width of the floor.
	 * @param height1
	 *            the height of the floor.
	 * @param base
	 *            the base material, will be modified reading the image.
	 * @param filename
	 *            the image file.
	 */
	public BumpMapFloor(Point3d orig, double width1, double height1,
			Material base, String filename)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, width1, height1, base, false);
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = new BumpMapping(filename);
	}

	/**
	 * Creates a new instance of ImageFloor.
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
	 * @param base
	 *            the base material, will be modified reading the image.
	 * @param filename
	 *            the image file.
	 */
	public BumpMapFloor(long begin, long end, Point3d orig, double width1,
			double height1, Material base, String filename)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, width1, height1, base, false);
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = new BumpMapping(filename);
	}

}
