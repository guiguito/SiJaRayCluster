/*
 * TextureMapping.java
 *
 * Created on 14 février 2006, 18:30
 */

package Mapping;

import java.awt.image.BufferedImage;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Color;
import Materials.Material;
import RaytracedObjects.RaytracedObject;

/**
 * Map a texture on an object.
 * 
 * @author Guilhem Duche
 */
public class TextureMapping extends BaseMapping {

	private BufferedImage image;
	private double xRep;
	private double yRep;

	/**
	 * Creates a new instance of TextureMapping.
	 * 
	 * @param filename
	 *            the filename of the texture.
	 */
	public TextureMapping(String filename) {
		super();
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		xRep = 1.0;
		yRep = 1.0;
	}

	/**
	 * Creates a new instance of TextureMapping.
	 * 
	 * @param filename
	 *            the filename of the texture.
	 * @param xr
	 *            number of repetition on u axis.
	 * @param yr
	 *            number of repetition on v axis.
	 */
	public TextureMapping(String filename, int xr, int yr) {
		super();
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		xRep = 1.0 / xr;
		yRep = 1.0 / yr;
	}

	public void applyMapping(double u, double v, Point3d intersection,
			Material material, Vector3d normal, double distance,
			RaytracedObject obj) {
		double a = (u % xRep) / xRep;
		double b = (v % yRep) / yRep;
		int xImageCoordinate = (int) (a * image.getWidth());
		int yImageCoordinate = (int) ((1 - b) * image.getHeight());
		java.awt.Color col = null;
		int c = 0;
		try {
			c = image.getRGB(xImageCoordinate, yImageCoordinate);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			col = new java.awt.Color(0, 0, 0);
		}
		if (col == null) {
			col = new java.awt.Color(c);
		}
		material.setAllColors(new Color((double) col.getRed() / (double) 255,
				(double) col.getGreen() / (double) 255, (double) col.getBlue()
						/ (double) 255));
	}
}
