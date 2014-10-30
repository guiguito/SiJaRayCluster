/*
 * Rectangle.java
 *
 * Created on 29 janvier 2006, 15:51
 *
 */

package RaytracedObjects;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Mapping.AlphaMapping;
import Mapping.BumpMapping;
import Mapping.ReflectionMapping;
import Mapping.TextureMapping;
import Materials.Material;


/**
 * A Rectangle object to put in a scene to be raytraced. This rectangle is
 * parallel is perpendicular to xz plane but can be rotated.
 * 
 * @author Guilhem Duche
 */
public class Rectangle extends Polygon {

	protected Point3d origin;
	protected double width;
	protected double height;
	protected TextureMapping texture;
	protected BumpMapping bumpMap;
	protected AlphaMapping alphaMap;
	protected ReflectionMapping reflectMap;
	Point3d origx;
	Point3d origz;

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d orig, double width1, double height1, Material col,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		// origin = orig;
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d orig, double width1,
			double height1, Material col, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d orig, double width1, double height1, Material col,
			TextureMapping text, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d orig, double width1,
			double height1, Material col, TextureMapping text, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d orig, double width1, double height1, Material col,
			BumpMapping bump, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d orig, double width1,
			double height1, Material col, BumpMapping bump, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d orig, double width1, double height1, Material col,
			AlphaMapping alpha, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = alpha;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d orig, double width1,
			double height1, Material col, AlphaMapping alpha, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = alpha;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d orig, double width1, double height1, Material col,
			ReflectionMapping reflec, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d orig, double width1,
			double height1, Material col, ReflectionMapping reflec,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d orig, double width1, double height1, Material col,
			TextureMapping text, BumpMapping bump, AlphaMapping alpha,
			ReflectionMapping reflec, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = alpha;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin of the rectangle.
	 * @param width1
	 *            the width of the rectangle.
	 * @param height1
	 *            the height of the rectangle.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d orig, double width1,
			double height1, Material col, TextureMapping text,
			BumpMapping bump, AlphaMapping alpha, ReflectionMapping reflec,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList(orig, width1, height1), col, inter);
		origin = new Point3d(orig.x - width1 / 2, orig.y, orig.z - height1 / 2);
		width = width1;
		height = height1;
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = alpha;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4,
			Material col, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Point3d pt4, Material col, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4,
			Material col, TextureMapping text, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Point3d pt4, Material col, TextureMapping text,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4,
			Material col, BumpMapping bump, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Point3d pt4, Material col, BumpMapping bump,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = null;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4,
			Material col, AlphaMapping alpha, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = alpha;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Point3d pt4, Material col, AlphaMapping alpha,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = alpha;
		reflectMap = null;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4,
			Material col, ReflectionMapping reflec, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Point3d pt4, Material col, ReflectionMapping reflec,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = null;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = null;
		alphaMap = null;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(Point3d pt1, Point3d pt2, Point3d pt3, Point3d pt4,
			Material col, TextureMapping text, BumpMapping bump,
			AlphaMapping alpha, ReflectionMapping reflec, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = alpha;
		reflectMap = reflec;
	}

	/**
	 * Creates a new instance of Rectangle.Points must be given clockwise
	 * starting with the bottom left.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @param col
	 *            the material of the Rectangle.
	 * @param text
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param bump
	 *            the texture we want to aplly on the rectangle. null if you
	 *            dont want any.
	 * @param alpha
	 *            the AlphaMapping we want to aplly on the sphere. null if you
	 *            dont want any.
	 * @param reflec
	 *            the ReflectionMapping we want to aplly on the sphere. null if
	 *            you dont want any.
	 * @param inter
	 *            true if the object which the polygon belongs to has an
	 *            interior, false otherwise. False if it's an isolated polygon.
	 */
	public Rectangle(long begin, long end, Point3d pt1, Point3d pt2,
			Point3d pt3, Point3d pt4, Material col, TextureMapping text,
			BumpMapping bump, AlphaMapping alpha, ReflectionMapping reflec,
			boolean inter) throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, setUpPointsList2(pt1, pt2, pt3, pt4), col, inter);
		origin = pt1;
		width = pt1.distance(pt4);
		height = pt1.distance(pt2);
		texture = text;
		origx = points.get(3);
		origz = points.get(1);
		bumpMap = bump;
		alphaMap = alpha;
		reflectMap = reflec;
	}

	/**
	 * Create the ArrayList of points for the Polygon's constructor.
	 * 
	 * @return the ArrayList of points for the Polygon's constructor.
	 */
	private static java.util.ArrayList setUpPointsList(Point3d origi,
			double width, double height) {
		java.util.ArrayList<Point3d> list = new java.util.ArrayList<Point3d>();
		Point3d origin = new Point3d(origi.x - width / 2, origi.y, origi.z
				- height / 2);
		list.add(origin);
		list.add(new Point3d(origin.x, origin.y, origin.z + height));
		list.add(new Point3d(origin.x + width, origin.y, origin.z + height));
		list.add(new Point3d(origin.x + width, origin.y, origin.z));
		return list;
	}

	/**
	 * Create the ArrayList of points for the Polygon's constructor.
	 * 
	 * @param pt1
	 *            the first point.
	 * @param pt2
	 *            the second point.
	 * @param pt3
	 *            the third point.
	 * @param pt4
	 *            the fourth point.
	 * @return the ArrayList of points for the Polygon's constructor.
	 */
	private static java.util.ArrayList setUpPointsList2(Point3d pt1,
			Point3d pt2, Point3d pt3, Point3d pt4) {
		java.util.ArrayList<Point3d> list = new java.util.ArrayList<Point3d>();
		list.add(pt1);
		list.add(pt2);
		list.add(pt3);
		list.add(pt4);
		return list;
	}

	/**
	 * Set the texture of the object.
	 * 
	 * @param text
	 *            the texture to associate with the object.
	 */
	public void setTextureMap(TextureMapping text) {
		texture = text;
	}

	/**
	 * Set the BumpMap of the object.
	 * 
	 * @param bump
	 *            the BumpMap to associate with the object.
	 */
	public void setBumpMap(BumpMapping bump) {
		bumpMap = bump;
	}

	/**
	 * Set the AlphaMap of the object.
	 * 
	 * @param alpha
	 *            the AlphaMap to associate with the object.
	 */
	public void setAlphaMap(AlphaMapping alpha) {
		alphaMap = alpha;
	}

	/**
	 * Set the ReflectionMap of the object.
	 * 
	 * @param reflec
	 *            the ReflectionMap to associate with the object.
	 */
	public void setReflectionMap(ReflectionMapping reflec) {
		reflectMap = reflec;
	}

	/**
	 * Get the IntersectionResult. Override this method if you want to put
	 * shaders on throws polygon.
	 * 
	 * @param intersection
	 *            the intersection point.
	 * @param distance
	 *            the distance between the eyePoint and the intersection.
	 * @return the IntersectionResult with the right parameters.
	 */
	IntersectionResult returnIntersectionResult(Point3d intersection, double w,
			RaytracedObject obj) {
		Vector3d normal2 = (Vector3d) normal.clone();
		Material material2 = (Material) material.clone();
		if (texture != null || bumpMap != null || alphaMap != null
				|| reflectMap != null) {
			Vector3d vectorig = new Vector3d(intersection);
			vectorig.sub(origin);
			Vector3d vectorigx = new Vector3d(origx);
			vectorigx.sub(origin);
			Vector3d vectorigz = new Vector3d(origz);
			vectorigz.sub(origin);
			double size = vectorig.length();
			double xAbs = size * Math.cos(vectorigx.angle(vectorig));
			double zAbs = size * Math.sin(vectorigx.angle(vectorig));
			double u = xAbs / width;
			double v = zAbs / height;
			if (texture != null) {
				texture.applyMapping(u, v, intersection, material2, normal2, w,
						obj);
			}
			if (bumpMap != null) {
				Vector3d vec1 = new Vector3d(origin);
				vec1.sub(origx);
				Vector3d vec2 = new Vector3d(origin);
				vec2.sub(origz);
				bumpMap.setVectorOrigx(vec1);
				bumpMap.setVectorOrigz(vec2);
				bumpMap.applyMapping(u, v, intersection, material2, normal2, w,
						obj);
			}
			if (alphaMap != null) {
				alphaMap.applyMapping(u, v, intersection, material2, normal2,
						w, obj);
			}
			if (reflectMap != null) {
				reflectMap.applyMapping(u, v, intersection, material2, normal2,
						w, obj);
			}
		}
		return new IntersectionResult(intersection, material2, normal2, w, obj);
	}

}
