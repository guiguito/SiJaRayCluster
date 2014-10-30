/*
 * HeightField.java
 *
 * Created on 23 janvier 2006, 22:36
 */

package RaytracedObjects;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.vecmath.Point3d;

import Materials.Material;
import Utils.BoundingBox;

/**
 * Class for height field object.
 * 
 * @author Guilhem Duche
 */
public class HeightField extends ComplexObject {

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
	public HeightField(Point3d orig, String filename, Material mat, double hf,
			double xf, double zf) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, mat, false);
		initHeightField(orig, filename, mat, hf, xf, zf);
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
	public HeightField(long begin, long end, Point3d orig, String filename,
			Material mat, double hf, double xf, double zf)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, mat, false);
		initHeightField(orig, filename, mat, hf, xf, zf);
	}

	/**
	 * Really Create the heightField.
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
	private void initHeightField(Point3d orig, String filename, Material mat,
			double hf, double xf, double zf)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		double heightFactor = hf;
		double xFactor = xf;
		double zFactor = zf;
		java.awt.Toolkit tool = java.awt.Toolkit.getDefaultToolkit();
		java.awt.Image img = tool.createImage(filename);
		BufferedImage image = Utils.UtilsImage.toBufferedImage(img);
		origin = new Point3d(orig.x - ((double) (image.getWidth()) * xf) / 2.0,
				orig.y, orig.z - ((double) (image.getHeight()) * zf) / 2.0);
		double[][] heights = new double[image.getWidth()][image.getHeight()];
		// get heights from image file.
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				java.awt.Color c = new java.awt.Color(image.getRGB(x,
						image.getHeight() - 1 - y));
				heights[x][y] = (double) (c.getRed() + c.getGreen() + c
						.getBlue()) / 765;
			}
		}
		double smallX = origin.x;
		double smallY = Double.MAX_VALUE;
		double smallZ = origin.z;
		double bigX = origin.x + image.getWidth() * xFactor;
		double bigY = Double.MIN_VALUE;
		double bigZ = origin.z + image.getHeight() * zFactor;
		// create the points.
		for (int x = 0; x < image.getWidth() - 1; x++) {
			for (int z = 1; z < image.getHeight(); z++) {
				if (x == 0) {// left border only one triangle to add
					double height1 = origin.y + heights[x][z] * heightFactor;
					smallY = Math.min(smallY, height1);
					bigY = Math.max(bigY, height1);
					double height2 = origin.y + heights[x][z - 1]
							* heightFactor;
					smallY = Math.min(smallY, height2);
					bigY = Math.max(bigY, height2);
					double height3 = origin.y + heights[x + 1][z - 1]
							* heightFactor;
					smallY = Math.min(smallY, height3);
					bigY = Math.max(bigY, height3);
					ArrayList<Point3d> list = new ArrayList<Point3d>();
					list.add(new Point3d(origin.x + x * xFactor, height1,
							origin.z + z * zFactor));
					list.add(new Point3d(origin.x + x * xFactor, height2,
							origin.z + (z - 1) * zFactor));
					list.add(new Point3d(origin.x + (x + 1) * xFactor, height3,
							origin.z + (z - 1) * zFactor));
					faces.add(new Polygon(list, material, false));
				} else {// at least 2 trianles to add
						// triangle1
					double height1 = origin.y + heights[x - 1][z]
							* heightFactor;
					smallY = Math.min(smallY, height1);
					bigY = Math.max(bigY, height1);
					double height2 = origin.y + heights[x][z - 1]
							* heightFactor;
					smallY = Math.min(smallY, height2);
					bigY = Math.max(bigY, height2);
					double height3 = origin.y + heights[x][z] * heightFactor;
					smallY = Math.min(smallY, height3);
					bigY = Math.max(bigY, height3);
					double height4 = origin.y + heights[x + 1][z - 1]
							* heightFactor;
					smallY = Math.min(smallY, height4);
					bigY = Math.max(bigY, height4);
					ArrayList<Point3d> list = new ArrayList<Point3d>();
					list.add(new Point3d(origin.x + (x - 1) * xFactor, height1,
							origin.z + z * zFactor));
					list.add(new Point3d(origin.x + x * xFactor, height2,
							origin.z + (z - 1) * zFactor));
					list.add(new Point3d(origin.x + x * xFactor, height3,
							origin.z + z * zFactor));
					faces.add(new Polygon(list, material, false));
					ArrayList<Point3d> list2 = new ArrayList<Point3d>();
					list2.add(new Point3d(origin.x + x * xFactor, height3,
							origin.z + z * zFactor));
					list2.add(new Point3d(origin.x + x * xFactor, height2,
							origin.z + (z - 1) * zFactor));
					list2.add(new Point3d(origin.x + (x + 1) * xFactor,
							height4, origin.z + (z - 1) * zFactor));
					faces.add(new Polygon(list2, material, false));
				}
				if (x == image.getWidth() - 2) {// right border add one more
												// triangle
					double height1 = origin.y + heights[x][z] * heightFactor;
					smallY = Math.min(smallY, height1);
					bigY = Math.max(bigY, height1);
					double height2 = origin.y + heights[x + 1][z - 1]
							* heightFactor;
					smallY = Math.min(smallY, height2);
					bigY = Math.max(bigY, height2);
					double height3 = origin.y + heights[x + 1][z]
							* heightFactor;
					smallY = Math.min(smallY, height3);
					bigY = Math.max(bigY, height3);
					ArrayList<Point3d> list = new ArrayList<Point3d>();
					list.add(new Point3d(origin.x + x * xFactor, height1,
							origin.z + z * zFactor));
					list.add(new Point3d(origin.x + (x + 1) * xFactor, height2,
							origin.z + (z - 1) * zFactor));
					list.add(new Point3d(origin.x + (x + 1) * xFactor, height3,
							origin.z + z * zFactor));
					faces.add(new Polygon(list, material, false));
				}
			}
		}
		boundingObject = new BoundingBox(new Point3d(smallX, smallY, smallZ),
				new Point3d(bigX, bigY, bigZ));
		// boundingObject = new BoundingSphere(new
		// Point3d(smallX,smallY,smallZ),new Point3d(bigX,bigY,bigZ));
	}

}
