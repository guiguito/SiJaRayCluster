/*
 * OffObject.java
 *
 * Created on 29 janvier 2006, 21:47
 */

package RaytracedObjects;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.vecmath.Point3d;

import Materials.Material;
import Utils.BoundingSphere;

/**
 * Class to put an object read from a OFF file.
 * 
 * @author Guilhem Duche
 */
public class OffObject extends ComplexObject {

	/**
	 * Creates a new instance of OffObject.
	 * 
	 * @param orig
	 *            the origin point.
	 * @param mat
	 *            the material to put on the object.
	 * @param file
	 *            the filename of the object.
	 * @param xSca
	 *            x scaling factor.
	 * @param ySca
	 *            y scaling factor.
	 * @param zSca
	 *            z scaling factor.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public OffObject(Point3d orig, Material mat, String file, double xSca,
			double ySca, double zSca, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(orig, mat, inter);
		initObject(file, xSca, ySca, zSca, inter);
	}

	/**
	 * Creates a new instance of OffObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object life.
	 * @param end
	 *            the date of the end of the object life.
	 * @param orig
	 *            the origin point.
	 * @param mat
	 *            the material to put on the object.
	 * @param file
	 *            the filename of the object.
	 * @param xSca
	 *            x scaling factor.
	 * @param ySca
	 *            y scaling factor.
	 * @param zSca
	 *            z scaling factor.
	 * @param inter
	 *            true if the object has an interior.
	 */
	public OffObject(long begin, long end, Point3d orig, Material mat,
			String file, double xSca, double ySca, double zSca, boolean inter)
			throws NotEnoughtPointsInPolygonException,
			PointNotInPolygonException {
		super(begin, end, orig, mat, inter);
		initObject(file, xSca, ySca, zSca, inter);
	}

	/**
	 * Read the off object and initialize variables.
	 * 
	 * @param mat
	 *            the material to put on the object.
	 * @param file
	 *            the filename of the object.
	 * @param xSca
	 *            x scaling factor.
	 * @param ySca
	 *            y scaling factor.
	 * @param zSca
	 *            z scaling factor.
	 * @param inter
	 *            true if the object has an interior.
	 */
	private void initObject(String file, double xSca, double ySca, double zSca,
			boolean inter) {
		ArrayList<Point3d> points = new ArrayList<Point3d>();
		try {
			BufferedInputStream in2 = new BufferedInputStream(
					new FileInputStream(file));
			BufferedReader in = new BufferedReader(new InputStreamReader(in2));
			try {
				boolean finished = false;
				int verticesIndex = 0;// index of points
				int currentVertice = 0;
				int numberOfFaces = 0;
				int currentFace = 0;
				int numberOfVertices = 0;
				int state = 1;
				double smallX = Double.MAX_VALUE;
				double smallY = Double.MAX_VALUE;
				double smallZ = Double.MAX_VALUE;
				double bigX = Double.MIN_VALUE;
				double bigY = Double.MIN_VALUE;
				double bigZ = Double.MIN_VALUE;
				double furthest = Double.MIN_VALUE;
				// state 1 reading OFF
				// state 2 reading line with vertex_count face_count edge_count
				// state 3 reading vertices
				// state 4 reading faces
				while (!finished && in.ready()) {// while it's not finished to
													// read and there is sth to
													// read
					String line = in.readLine();
					if (line.charAt(0) == '#' || line.equals("")
							|| line.equals("\n") || line.equals("\t")) {
						// do not do anything it is a comment
					} else if (state == 1) {
						if (!line.equals("OFF")) {
							throw new OffReadingException(
									"Can't read OFF at the begining of the file.");
						}
						state = 2;
					} else if (state == 2) {// state 2 reading line with
											// vertex_count face_count
											// edge_count
						String[] numbers = line.split(" ");
						int a = 0;
						while (numbers[a] == "") {
							a++;
						}
						numberOfVertices = Integer.parseInt(numbers[a]);
						numberOfFaces = Integer.parseInt(numbers[a + 1]);
						// Double.parseDouble(numbers[2]);//number of edges can
						// be ignored every time.
						state = 3;
					} else if (state == 3) {// state 3 reading vertices
						String[] numbers = line.split(" ");
						int i = 0;
						while (numbers[i] == "") {
							i++;
						}
						// System.out.println(file+" "+numbers[0]+" "+numbers[1]+" "+numbers[2]);
						double x = origin.x + xSca
								* Double.parseDouble(numbers[i]);
						smallX = Math.min(x, smallX);
						bigX = Math.max(x, bigX);
						double y = origin.y + ySca
								* Double.parseDouble(numbers[i + 1]);
						smallY = Math.min(y, smallY);
						bigY = Math.max(y, bigY);
						double z = origin.z + zSca
								* Double.parseDouble(numbers[i + 2]);
						smallZ = Math.min(z, smallZ);
						bigZ = Math.max(z, bigZ);
						Point3d newVertice = new Point3d(x, y, z);
						furthest = Math.max(furthest,
								origin.distance(newVertice));
						points.add(currentVertice, newVertice);
						currentVertice++;
						if (currentVertice == numberOfVertices) {
							state = 4;
						}
					} else if (state == 4) {// state 4 reading faces
						String[] numbers = line.split(" ");
						int nbVertUsed = Integer.parseInt(numbers[0]);
						ArrayList<Point3d> listPoints = new ArrayList<Point3d>();
						for (int i = 1; i <= nbVertUsed; i++) {
							int currentVertex = Integer.parseInt(numbers[i]);
							listPoints.add((Point3d) points.get(currentVertex)
									.clone());
						}
						try {
							faces.add(new Polygon(listPoints, material, inter));
						} catch (Exception e) {

						}
						currentFace++;
						if (currentFace == numberOfFaces) {
							state = 5;
						}
					}

				}
				// boundingObject = new BoundingBox(new
				// Point3d(smallX,smallY,smallZ),new Point3d(bigX,bigY,bigZ));
				boundingObject = new BoundingSphere(origin, furthest);
			} catch (EOFException e) {
				System.out.println("File too short. " + e.getMessage());
				System.exit(0);
			} catch (IOException e) {
				System.out
						.println("Bad format of the file ! " + e.getMessage());
				System.exit(0);
			} catch (NumberFormatException e) {
				System.out.println("Bad format of a number in the file " + file
						+ " ! " + e.getMessage());
				System.exit(0);
			} catch (OffReadingException e) {
				System.out.println("Error reading off file " + file + " "
						+ e.getMessage());
				System.exit(0);
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + file + "  not found. "
					+ e.getMessage());
			System.exit(0);
		}
	}

}
