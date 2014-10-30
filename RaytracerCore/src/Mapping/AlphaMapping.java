/*
 * AlphaMapping.java
 *
 * Created on 16 février 2006, 00:12
 */

package Mapping;

import java.awt.image.BufferedImage;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Color;
import Materials.Material;
import RaytracedObjects.RaytracedObject;

/**
 * Alpha mapping. Use an image to determine transparency.
 * 
 * @author Guilhem Duche
 */
public class AlphaMapping extends BaseMapping {

	private BufferedImage image;
	private Vector3d vectorigx;
	private Vector3d vectorigz;
	private double xRep;
	private double yRep;
	private double divGrad;

	/**
	 * Creates a new instance of AlphaMapping.
	 * 
	 * @param filename
	 *            the filename of the image used for the bumpMap.
	 */
	public AlphaMapping(String filename) {
		super();
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		xRep = 1.0;
		yRep = 1.0;
	}

	/**
	 * Creates a new instance of AlphaMapping.
	 * 
	 * @param filename
	 *            the filename of the image used for the bumpMap.
	 * @param xr
	 *            number of repetition on u axis.
	 * @param yr
	 *            number of repetition on v axis.
	 */
	public AlphaMapping(String filename, int xr, int yr) {
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
		double redTransparency = (double) col.getRed() / 255.0;
		double greenTransparency = (double) col.getGreen() / 255.0;
		double blueTransparency = (double) col.getBlue() / 255.0;
		material.setTransmission(new Color(redTransparency, greenTransparency,
				blueTransparency));
		if (redTransparency > 0 && redTransparency > 0 && redTransparency > 0) {
			material.setAllColors(new Color(0, 0, 0));
		}
	}
}
