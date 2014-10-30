/*
 * ImageFloor.java
 *
 * Created on 19 janvier 2006, 02:05
 */

package RaytracedObjects;

import java.awt.image.BufferedImage;

import javax.vecmath.Point3d;

import Mapping.TextureMapping;
import Materials.Material;

/**
 * Image board floor. Will do a floor, parallel to xz plane with an image on it.
 * 
 * @author Guilhem Duche
 */
public class ImageFloor extends Rectangle {

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
	public ImageFloor(Point3d orig, double width1, double height1,
			Material base, String filename)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, width1, height1, base, false);
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		origx = points.get(3);
		origz = points.get(1);
		texture = new TextureMapping(filename);
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
	public ImageFloor(long begin, long end, Point3d orig, double width1,
			double height1, Material base, String filename)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, width1, height1, base, false);
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		origx = points.get(3);
		origz = points.get(1);
		texture = new TextureMapping(filename);
	}

}// end class ImageFloor
