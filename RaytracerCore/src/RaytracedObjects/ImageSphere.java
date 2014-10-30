/*
 * ImageSphere.java
 *
 * Created on 14 février 2006, 11:41
 *
 */

package RaytracedObjects;

import java.awt.image.BufferedImage;

import javax.vecmath.Point3d;

import Mapping.TextureMapping;
import Materials.Material;

/**
 * A sphere that can be textured.
 * 
 * @author Guilhem Duche
 */
public class ImageSphere extends Sphere {

	private BufferedImage image;

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param filename
	 *            the image to put map on throws sphere.
	 */
	public ImageSphere(double rad, Point3d cen, Material c, String filename) {
		super(rad, cen, c);
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		texture = new TextureMapping(filename);
	}

	/**
	 * Creates a new instance of Sphere.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param rad
	 *            the radius of the sphere.
	 * @param cen
	 *            center of the sphere.
	 * @param c
	 *            the material of the object.
	 * @param filename
	 *            the image to put map on throws sphere.
	 */
	public ImageSphere(long begin, long end, double rad, Point3d cen,
			Material c, String filename) {
		super(begin, end, rad, cen, c);
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		texture = new TextureMapping(filename);
	}

}
