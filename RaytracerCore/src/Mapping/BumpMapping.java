/*
 * BumpMapping.java
 *
 * Created on 14 février 2006, 18:38
 *
 */

package Mapping;

import java.awt.image.BufferedImage;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;
import RaytracedObjects.RaytracedObject;

/**
 * Apply Bump Mapping on an object.
 * 
 * @author Guilhem Duche
 */
public class BumpMapping extends BaseMapping {

	private BufferedImage image;
	private Vector3d vectorigx;
	private Vector3d vectorigz;
	private double xRep;
	private double yRep;
	private double divGrad;

	/**
	 * Creates a new instance of BumpMapping.
	 * 
	 * @param filename
	 *            the filename of the image used for the bumpMap.
	 */
	public BumpMapping(String filename) {
		super();
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		xRep = 1.0;
		yRep = 1.0;
		SetDivGrad();
	}

	/**
	 * Creates a new instance of BumpMapping.
	 * 
	 * @param filename
	 *            the filename of the image used for the bumpMap.
	 * @param xr
	 *            number of repetition on u axis.
	 * @param yr
	 *            number of repetition on v axis.
	 */
	public BumpMapping(String filename, int xr, int yr) {
		super();
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		image = Utils.UtilsImage.toBufferedImage(img);
		xRep = 1.0 / xr;
		yRep = 1.0 / yr;
		SetDivGrad();
	}

	/**
	 * Set the factor dividing the gradient.
	 */
	private void SetDivGrad() {
		double largestXgrad = Integer.MIN_VALUE;
		double largestYgrad = Integer.MIN_VALUE;
		for (int i = 0; i < image.getWidth(); i++) {
			for (int j = 0; j < image.getHeight(); j++) {
				double xm = 0;
				if (i > 0) {
					int c = image.getRGB(i - 1, j);
					java.awt.Color col = new java.awt.Color(c);
					xm = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				} else {
					int c = image.getRGB(i, j);
					java.awt.Color col = new java.awt.Color(c);
					xm = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				}
				double xp = 0;
				if (i < image.getWidth() - 1) {
					int c = image.getRGB(i + 1, j);
					java.awt.Color col = new java.awt.Color(c);
					xp = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				} else {
					int c = image.getRGB(i, j);
					java.awt.Color col = new java.awt.Color(c);
					xp = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				}
				double x_gradient = Math.abs(xm - xp);
				largestXgrad = Math.max(x_gradient, largestXgrad);

				double ym = 0;
				if (j > 0) {
					int c = image.getRGB(i, j - 1);
					java.awt.Color col = new java.awt.Color(c);
					ym = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				} else {
					int c = image.getRGB(i, j);
					java.awt.Color col = new java.awt.Color(c);
					ym = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				}
				double yp = 0;
				if (j < image.getHeight() - 1) {
					int c = image.getRGB(i, j + 1);
					java.awt.Color col = new java.awt.Color(c);
					yp = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3.0;
				} else {
					int c = image.getRGB(i, j);
					java.awt.Color col = new java.awt.Color(c);
					yp = (double) (col.getRed() + col.getGreen() + col
							.getBlue()) / 3;
				}
				double y_gradient = Math.abs(ym - yp);
				largestYgrad = Math.max(y_gradient, x_gradient);
			}
		}
		divGrad = Math.max(largestXgrad, largestYgrad);
	}

	/**
	 * Set the x deviation vector for the normal.
	 * 
	 * @param or
	 *            deviation vector.
	 */
	public void setVectorOrigx(Vector3d or) {
		vectorigx = or;
	}

	/**
	 * Set the z deviation vector for the normal.
	 * 
	 * @param or
	 *            deviation vector.
	 */
	public void setVectorOrigz(Vector3d or) {
		vectorigz = or;
	}

	public void applyMapping(double u, double v, Point3d intersection,
			Material material, Vector3d normal, double distance,
			RaytracedObject obj) {
		double a = (u % xRep) / xRep;
		double b = (v % yRep) / yRep;
		int xImageCoordinate = (int) (a * image.getWidth());
		int yImageCoordinate = (int) ((1 - b) * image.getHeight());
		try {
			double xm = 0;
			if (xImageCoordinate > 0) {
				int c = image.getRGB(xImageCoordinate - 1, yImageCoordinate);
				java.awt.Color col = new java.awt.Color(c);
				xm = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3.0;
			} else {
				int c = image.getRGB(xImageCoordinate, yImageCoordinate);
				java.awt.Color col = new java.awt.Color(c);
				xm = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3.0;
			}
			double xp = 0;
			if (xImageCoordinate < image.getWidth() - 1) {
				int c = image.getRGB(xImageCoordinate + 1, yImageCoordinate);
				java.awt.Color col = new java.awt.Color(c);
				xp = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3.0;
			} else {
				int c = image.getRGB(xImageCoordinate, yImageCoordinate);
				java.awt.Color col = new java.awt.Color(c);
				xp = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3.0;
			}

			double x_gradient = xm - xp;

			double ym = 0;
			if (yImageCoordinate > 0) {
				int c = image.getRGB(xImageCoordinate, yImageCoordinate - 1);
				java.awt.Color col = new java.awt.Color(c);
				ym = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3.0;
			} else {
				int c = image.getRGB(xImageCoordinate, yImageCoordinate);
				java.awt.Color col = new java.awt.Color(c);
				ym = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3.0;
			}
			double yp = 0;
			if (yImageCoordinate < image.getHeight() - 1) {
				int c = image.getRGB(xImageCoordinate, yImageCoordinate + 1);
				java.awt.Color col = new java.awt.Color(c);
				yp = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3;
			} else {
				int c = image.getRGB(xImageCoordinate, yImageCoordinate);
				java.awt.Color col = new java.awt.Color(c);
				yp = (double) (col.getRed() + col.getGreen() + col.getBlue()) / 3;
			}
			double y_gradient = ym - yp;
			x_gradient /= divGrad;
			y_gradient /= divGrad;
			vectorigx.normalize();
			vectorigz.normalize();
			vectorigx.scale(x_gradient);
			vectorigz.scale(y_gradient);
			normal.add(vectorigx);
			normal.add(vectorigz);
		} catch (java.lang.ArrayIndexOutOfBoundsException e) {
			// cant happen
		}
		/* test don't know hy it doesn't work */
		// double xPercent = vectorigx.dot(vectorig)/(size*vectorigx.length());
		// double zPercent = vectorigz.dot(vectorig)/(size*vectorigz.length());
		/* end test */

	}

}
