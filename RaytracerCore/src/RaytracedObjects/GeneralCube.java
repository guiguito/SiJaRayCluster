/*
 * GeneralCube.java
 *
 * Created on 1 février 2006, 21:50
 */

package RaytracedObjects;
import java.util.Iterator;

import javax.vecmath.Point3d;

import Mapping.AlphaMapping;
import Mapping.BumpMapping;
import Mapping.ReflectionMapping;
import Mapping.TextureMapping;
import Materials.Material;


/**
 * A cube that we can setup in the three dimensions.
 * 
 * @author Guilhem Duche
 */
public class GeneralCube extends PolygonListObject {

	/**
	 * Creates a new instance of GeneralCube.
	 * 
	 * @param origi
	 *            the center point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param sizeX
	 *            size in the x dimensions.
	 * @param sizeY
	 *            size in the y dimensions.
	 * @param sizeZ
	 *            size in the z dimensions.
	 */
	public GeneralCube(Point3d origi, Material mat, double sizeX, double sizeY,
			double sizeZ) {
		super(origi, mat, true);
		initCube(origi, mat, sizeX, sizeY, sizeZ);
	}

	/**
	 * Creates a new instance of GeneralCube.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param origi
	 *            the center point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param sizeX
	 *            size in the x dimensions.
	 * @param sizeY
	 *            size in the y dimensions.
	 * @param sizeZ
	 *            size in the z dimensions.
	 */
	public GeneralCube(long begin, long end, Point3d origi, Material mat,
			double sizeX, double sizeY, double sizeZ) {
		super(begin, end, origi, mat, true);
		initCube(origi, mat, sizeX, sizeY, sizeZ);
	}

	/**
	 * Does the work to initialize the box.
	 * 
	 * @param origi
	 *            the center point of the object.
	 * @param mat
	 *            the material of the object.
	 * @param sizeX
	 *            size in the x dimensions.
	 * @param sizeY
	 *            size in the y dimensions.
	 * @param sizeZ
	 *            size in the z dimensions.
	 */
	private void initCube(Point3d origi, Material mat, double sizeX,
			double sizeY, double sizeZ) {
		Point3d orig = new Point3d(origi.x - sizeX / 2, origi.y - sizeY / 2,
				origi.z - sizeZ / 2);
		try {
			Point3d pta1 = (Point3d) orig.clone();
			Point3d pta2 = new Point3d(orig.x, orig.y + sizeY, orig.z);
			Point3d pta3 = new Point3d(orig.x + sizeX, orig.y + sizeY, orig.z);
			Point3d pta4 = new Point3d(orig.x + sizeX, orig.y, orig.z);
			Rectangle face1 = new Rectangle(pta1, pta2, pta3, pta4, mat, true);
			faces.add(face1);

			Point3d ptb1 = (Point3d) orig.clone();
			Point3d ptb2 = new Point3d(orig.x, orig.y + sizeY, orig.z);
			Point3d ptb3 = new Point3d(orig.x, orig.y + sizeY, orig.z + sizeZ);
			Point3d ptb4 = new Point3d(orig.x, orig.y, orig.z + sizeZ);
			Rectangle face2 = new Rectangle(ptb1, ptb2, ptb3, ptb4, mat, true);
			faces.add(face2);

			Point3d ptc1 = new Point3d(orig.x, orig.y, orig.z + sizeZ);
			Point3d ptc2 = new Point3d(orig.x, orig.y + sizeY, orig.z + sizeZ);
			Point3d ptc3 = new Point3d(orig.x + sizeX, orig.y + sizeY, orig.z
					+ sizeZ);
			Point3d ptc4 = new Point3d(orig.x + sizeX, orig.y, orig.z + sizeZ);
			Rectangle face3 = new Rectangle(ptc1, ptc2, ptc3, ptc4, mat, true);
			faces.add(face3);

			Point3d ptd1 = new Point3d(orig.x + sizeX, orig.y, orig.z + sizeZ);
			Point3d ptd2 = new Point3d(orig.x + sizeX, orig.y, orig.z);
			Point3d ptd3 = new Point3d(orig.x + sizeX, orig.y + sizeY, orig.z);
			Point3d ptd4 = new Point3d(orig.x + sizeX, orig.y + sizeY, orig.z
					+ sizeZ);
			Rectangle face4 = new Rectangle(ptd1, ptd2, ptd3, ptd4, mat, true);
			faces.add(face4);

			Point3d pte1 = new Point3d(orig.x, orig.y, orig.z);
			Point3d pte2 = new Point3d(orig.x, orig.y, orig.z + sizeZ);
			Point3d pte3 = new Point3d(orig.x + sizeX, orig.y, orig.z + sizeZ);
			Point3d pte4 = new Point3d(orig.x + sizeX, orig.y, orig.z);
			Rectangle face5 = new Rectangle(pte1, pte2, pte3, pte4, mat, true);
			faces.add(face5);

			Point3d ptf1 = new Point3d(orig.x, orig.y + sizeY, orig.z);
			Point3d ptf2 = new Point3d(orig.x, orig.y + sizeY, orig.z + sizeZ);
			Point3d ptf3 = new Point3d(orig.x + sizeX, orig.y + sizeY, orig.z
					+ sizeZ);
			Point3d ptf4 = new Point3d(orig.x + sizeX, orig.y + sizeY, orig.z);
			Rectangle face6 = new Rectangle(ptf1, ptf2, ptf3, ptf4, mat, true);
			faces.add(face6);
		} catch (NotEnoughtPointsInPolygonException e) {
			// can't happen
		} catch (PointNotInPolygonException e) {
			// can't happen
		}
	}

	/**
	 * Set the texture of the object.
	 * 
	 * @param text
	 *            the texture to associate with the object.
	 */
	public void setTextureMap(TextureMapping text) {
		Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Rectangle rect = (Rectangle) it.next();
			rect.setTextureMap(text);
		}
	}

	/**
	 * Set the BumpMap of the object.
	 * 
	 * @param bump
	 *            the BumpMap to associate with the object.
	 */
	public void setBumpMap(BumpMapping bump) {
		Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Rectangle rect = (Rectangle) it.next();
			rect.setBumpMap(bump);
		}
	}

	/**
	 * Set the AlphaMap of the object.
	 * 
	 * @param alpha
	 *            the AlphaMap to associate with the object.
	 */
	public void setAlphaMap(AlphaMapping alpha) {
		Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Rectangle rect = (Rectangle) it.next();
			rect.setAlphaMap(alpha);
		}
	}

	/**
	 * Set the ReflectionMap of the object.
	 * 
	 * @param reflec
	 *            the ReflectionMap to associate with the object.
	 */
	public void setReflectionMap(ReflectionMapping reflec) {
		Iterator<Polygon> it = faces.iterator();
		while (it.hasNext()) {
			Rectangle rect = (Rectangle) it.next();
			rect.setReflectionMap(reflec);
		}
	}

}
