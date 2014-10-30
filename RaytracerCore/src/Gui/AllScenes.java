package Gui;

import java.util.ArrayList;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Lights.AmbientLight;
import Lights.PointLight;
import Lights.PointLightDiffuse;
import Lights.SpotLight;
import Lights.SpotLightDiffuse;
import Mapping.AlphaMapping;
import Mapping.BumpMapping;
import Mapping.ReflectionMapping;
import Mapping.TextureMapping;
import Materials.Color;
import Materials.ExponentRGB;
import Materials.Material;
import RaytracedObjects.CheckerBoardFloor;
import RaytracedObjects.Cube;
import RaytracedObjects.GeneralCube;
import RaytracedObjects.HeightField;
import RaytracedObjects.OffObject;
import RaytracedObjects.Polygon;
import RaytracedObjects.Rectangle;
import RaytracedObjects.SmoothHeightField;
import RaytracedObjects.SmoothOffObject;
import RaytracedObjects.Sphere;
import RaytracedObjects.SpherePerlin;
import Raytracer.Camera;
import Raytracer.CameraConflictException;
import Raytracer.Scene;
import Transformations.Rotation;
import Transformations.Translation;

public class AllScenes {

	private static TextureMapping text = new TextureMapping("lowres2.jpg");
	private static TextureMapping text2 = new TextureMapping("lowres.jpg");
	private static TextureMapping text3 = new TextureMapping("texture2.gif");
	private static TextureMapping textUn = new TextureMapping("text1.jpg");
	private static TextureMapping titleText = new TextureMapping("title.jpg");
	private static TextureMapping quest = new TextureMapping("question.jpg");
	private static TextureMapping text4 = new TextureMapping(
			"bricks_3_256x.png", 3, 3);
	private static TextureMapping text5 = new TextureMapping("BlueSky7_g.jpg",
			3, 3);
	// private static AlphaMapping alpha = new AlphaMapping("alpha2.png",2, 3);
	private static AlphaMapping alpha2 = new AlphaMapping("alpha2.png");
	private static ReflectionMapping reflec2 = new ReflectionMapping(
			"lowres.jpg");
	private static ReflectionMapping reflec1 = new ReflectionMapping(
			"texture4.jpg");
	// private static ReflectionMapping reflec = new
	// ReflectionMapping("alpha2.png",2, 3);
	private static BumpMapping bump2 = new BumpMapping("lowres.jpg");
	private static BumpMapping bump3 = new BumpMapping("bricks_3_256x.png", 3,
			3);
	private static BumpMapping bumpWater = new BumpMapping("water55.png", 4, 4);
	private static BumpMapping bumpText22 = new BumpMapping("texture2.gif", 2,
			2);
	private static BumpMapping bumpText2 = new BumpMapping("texture2.gif");
	private static BumpMapping waves = new BumpMapping("waves.JPG", 2, 2);

	// private static BumpMapping bump = new BumpMapping("lowres.jpg",2, 3);

	// scene 1
	public static Scene getScene1() {
		// create a camera
		Camera c = new Camera(new Point3d(0, 1.4, -8), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "A) Whitted's scene");
		// prepare materials used for the scene
		Material blue = new Material(new Color(1, 1, 1), new Color(1, 1, 1),
				new Color(1, 1, 1), new Color(0.075, 0.075, 0.075), new Color(
						0.075, 0.075, 0.075), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.01, 0.01, 0.01),
				new Color(0.85, 0.85, 0.85), 0.97);
		Material green = new Material(new Color(0.7, 0.7, 0.7), new Color(0.7,
				0.7, 0.7), new Color(1, 1, 1), new Color(0.15, 0.15, 0.15),
				new Color(0.25, 0.25, 0.25), new Color(1, 1, 1),
				new ExponentRGB(20, 20, 20), new Color(0.75, 0.75, 0.75),
				new Color(0, 0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.5, 0.3, 0.3),
				new Color(0.7, 0.7, 0.7), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.4, 0.4, 0.4),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.95, 0.95, 0.95), -4, 20,
				-11);
		s.addPointLight(light2);
		// add objects
		Sphere s1 = new Sphere(1, new Point3d(0, 0.6, 2), blue);
		s.addObject(s1);
		Sphere s2 = new Sphere(1, new Point3d(1.5, -0.5, 7), green);
		s.addObject(s2);
		Rotation r1 = new Rotation(0, 100, new Point3d(4.5, -2, 21.5), 0, 3.6,
				0);
		c.addTransformation(r1);
		Rotation r2 = new Rotation(100, 150, new Point3d(4.5, -2, 21.5), 0, 0,
				-1.8);
		c.addTransformation(r2);
		Translation t1 = new Translation(100, 150, 0, 0, 0.7);
		s1.addTransformation(t1);
		try {
			// s.addObject(new Cube(new Point3d(-1, -0.6, 1),blue,2));
			s.addObject(new CheckerBoardFloor(new Point3d(4.5, -2, 21.5), 13.0,
					47.0, red, yellow, 20, 40));
		} catch (Exception e) {
			System.out.println("Exception scene1 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 2
	public static Scene getScene2() {
		Camera c = new Camera(new Point3d(0, 1.6, -10.5), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.5);
		Scene s = new Scene(c, "B) Yellow ball red triangle");
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.5, 0.5, 0.5), new Color(0.5, 0.5, 0.5),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.5, 0.5, 0.5), new Color(0.5, 0.5, 0.5),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		AmbientLight light1 = new AmbientLight(new Color(1.0, 1.0, 1.0));
		s.setAmbientLight(light1);
		s.addObject(new Sphere(1, new Point3d(-1, 0.6, 2), yellow));
		// PointLightDiffuse light2 = new PointLightDiffuse(new
		// Color(0.4,0.8,0.5),-4, 1,-3, 1,1,1);
		PointLight light4 = new PointLight(new Color(1, 1, 1.0), -2, 7, 2);
		s.addPointLight(light4);
		// s.addPointLight(light2);
		ArrayList<Point3d> vect = new ArrayList<Point3d>();
		vect.add(new Point3d(-2, -2, 40));
		vect.add(new Point3d(11, -2, 10));
		vect.add(new Point3d(-2, -2, 0));
		// System.out.println(RaytracedObjects.SpecialFloor.goodPi(5.5346));
		try {
			s.addObject(new Polygon(vect, red, false));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 3
	public static Scene getScene3() {
		// create a camera
		Camera c = new Camera(new Point3d(20, 10, -10), new Point3d(14, 2, 5),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z) Presentation mapping 1");
		// prepare materials used for the scene
		Material green = new Material(new Color(0.1, 1, 0.1), new Color(1.0,
				1.0, 0.1), new Color(0.1, 1.0, 0.1), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0.3, 0.3, 0.3),
				new Color(0, 0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0),
				new Color(0.01, 0.01, 0.01), new Color(0.1, 0.1, 0.1),
				new Color(0.05, 0.05, 0.05), new ExponentRGB(12, 12, 12),
				new Color(0.3, 0.3, 0.3), new Color(0.90, 0.90, 0.90), 0.95);
		Material base = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.4, 0.4, 0.4), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(8, 8, 8), new Color(0.4, 0.4, 0.4), new Color(
						0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 14, 3, -1);
		PointLight light3 = new PointLight(new Color(0.9, 0.8, 0.9), 20, 10,
				-10);
		s.addPointLight(light2);
		s.addPointLight(light3);
		s.addPointLight(light3);
		s.addPointLight(light3);
		// mappings

		// add objects
		s.addObject(new Sphere(2.1, new Point3d(18, -1, 10), yellow));
		s.addObject(new Sphere(0.7, new Point3d(7.5, -2.2, 10.25), red));
		s.addObject(new Sphere(3, new Point3d(13, 5.2, 9), green, text2));
		try {
			// s.addObject(new Polygon(vect, red));
			s.addObject(new Rectangle(new Point3d(10, -2, 10), 35.0, 20.0,
					base, text, false));
		} catch (Exception e) {
			System.out.println("Exception scene3 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 4 // Benchmark scene DO NOT TOUCH IT
	public static Scene getScene4() {
		// create a camera
		Camera c = new Camera(new Point3d(6, 3, 6), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "D) Benchmark 2 mushroom.off");
		// prepare materials used for the scene
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.3, 0.3, 0.3));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 3, 3, 3);
		s.addPointLight(light2);
		try {
			s.addObject(new OffObject(new Point3d(0, -1, 0), red,
					"mushroom.off", 2, 2, 2, true));
		} catch (Exception e) {
			System.out.println("Exception scene4 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 5
	public static Scene getScene5() {
		// create a camera
		Camera c = new Camera(new Point3d(0, 1, -5), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "E) Presentation mapping 2");
		// prepare materials used for the scene
		Material white = new Material(new Color(1.0, 1, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.4, 0.4, 0.4),
				new Color(0.5, 0.5, 0.5), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material white2 = new Material(new Color(1.0, 1, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.4, 0.4, 0.4),
				new Color(0.5, 0.5, 0.5), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.5, 0.3, 0.3),
				new Color(0.7, 0.7, 0.7), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.6, 0.4, 0.4),
				new Color(0.8, 0.6, 0.6), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.95, 0.95, 0.95), -4, 2,
				-11);
		s.addPointLight(light2);
		// mappings

		// add objects
		s.addObject(new Sphere(1, new Point3d(-1.5, -0.5, 2), yellow, alpha2));
		s.addObject(new Sphere(1, new Point3d(1.5, -0.5, 2), white, reflec2));
		try {
			// s.addObject(new SpecialFloor3(new Point3d(0,-2,0), 30.0, 47.0,
			// red, 1));
			s.addObject(new Rectangle(new Point3d(-2, -3, 0), 30, 30, white2,
					false));
			// Cube c1 = new Cube(new Point3d(0,0,0), base, 2.0);
			// s.addObject(c1);
		} catch (Exception e) {
			System.out.println("Exception scene5 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 6 // Benchmark scene DO NOT TOUCH IT
	public static Scene getScene6() {
		// create a camera
		Camera c = new Camera(new Point3d(0, 25, 15), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "F) Benchmark 1 heightfield");
		// prepare materials used for the scene
		Material green = new Material(new Color(0.1, 1, 0.1), new Color(1.0,
				1.0, 0.1), new Color(0.1, 1.0, 0.1), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 20, 10, -2);
		PointLight light3 = new PointLight(new Color(0.9, 0.8, 0.9), 0, 10, 0);
		s.addPointLight(light2);
		s.addPointLight(light3);
		// add objects
		try {
			s.addObject(new HeightField(new Point3d(0, -10, 0), "height.png",
					green, 7, 1, 1));
		} catch (Exception e) {
			System.out.println("Exception scene6 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 7
	public static Scene getScene7() {
		// create a camera
		Camera c = new Camera(new Point3d(5, 2, 5), new Point3d(-1, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z) Presentation Off1");
		// prepare materials used for the scene
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material black = new Material(new Color(0.3, 0.3, 0.3), new Color(0.3,
				0.3, 0.3), new Color(0.3, 0.3, 0.3), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 7, 7, 7);
		s.addPointLight(light2);
		try {
			s.addObject(new OffObject(new Point3d(0, 0, 0), red, "m102.off", 4,
					4, 4, true));
			s.addObject(new CheckerBoardFloor(new Point3d(0, -0, 0), 20.0,
					20.0, black, white, 10, 10));
		} catch (Exception e) {
			System.out.println("Exception scene7 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 8
	public static Scene getScene8() {
		// create a camera
		Camera c = new Camera(1, 41, new Point3d(-0.1, 15, 0), new Point3d(0,
				0, 0), new Vector3d(0, 1, 0), 1, 0.7);
		c.fixLookAtPoint();
		// Translation tcam = new Translation(-0.5,-0.5,-0.5);
		// c.addTransformation(tcam);
		// camera 2
		// Camera c2 = new Camera(21,41,new Point3d(5,5,5),new Point3d(0,1.5,0),
		// new Vector3d(0,1,0), 1, 0.7);
		// c2.fixLookAtPoint();
		// Translation tcam2 = new Translation(0,0,-0.5);
		// c2.addTransformation(tcam2);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "H) Animation 1");
		/*
		 * try{ s.addCamera(c2); }catch(CameraConflictException e){
		 * System.out.println("camera conflict"); }
		 */
		// prepare materials used for the scene
		Material blue = new Material(new Color(0.1, 0.1, 1.0), new Color(0.1,
				0.7, 1.0), new Color(0.1, 0.1, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.4, 0.2), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material green = new Material(new Color(0.1, 0.6, 0.1), new Color(0.1,
				0.6, 0.1), new Color(0.1, 1.0, 0.1), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.01),
				new Color(0.6, 0.6, 0.0), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material black = new Material(new Color(0, 0, 0.0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0.1, 0.1, 0.1), new Color(0.6,
						0.6, 0.6), new Color(0.2, 0.2, 0.2), new ExponentRGB(
						20, 20, 20), new Color(0, 0, 0), new Color(0, 0, 0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material base = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.4, 0.4, 0.4), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(8, 8, 8), new Color(0, 0, 0),
				new Color(0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 5, 5, 5);
		PointLight light3 = new PointLight(new Color(0.9, 0.8, 0.9), -10, -10,
				-10);
		s.addPointLight(light2);
		s.addPointLight(light3);
		Rotation r1 = new Rotation(new Point3d(0, 0, 0), 0, 0, 8);
		Sphere s1 = new Sphere(1.5, new Point3d(-4, -0.5, 0), green);
		Sphere s2 = new Sphere(1.5, new Point3d(0, -0.5, -4), green);
		Sphere s3 = new Sphere(1.5, new Point3d(0, -0.5, 4), green);
		Sphere s4 = new Sphere(1.5, new Point3d(4, -0.5, 0), green);
		s1.addTransformation(r1);
		s2.addTransformation(r1);
		s3.addTransformation(r1);
		s4.addTransformation(r1);
		s.addObject(s1);
		s.addObject(s2);
		s.addObject(s3);
		s.addObject(s4);
		Rotation r2 = new Rotation(1, 40, new Point3d(5, 5, 0), 10, 0, 0);
		Sphere s5 = new Sphere(1, 40, 2, new Point3d(0, 5, 0), red);
		s5.addTransformation(r2);
		// s.addObject(s5);
		try {
			Cube cub = new Cube(1, 40, new Point3d(0, 0, 0), blue, 2);
			cub.addTransformation(r1);
			s.addObject(cub);
			s.addObject(new CheckerBoardFloor(new Point3d(0, -2, 0), 20.0,
					20.0, black, white, 10, 8));
		} catch (Exception e) {
			System.out.println("Exception scene8 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 9
	public static Scene getScene9() {
		// create a camera
		Camera c = new Camera(1, 20, new Point3d(-0.1, 15, 0), new Point3d(0,
				0, 0), new Vector3d(0, 1, 0), 1, 0.7);
		c.fixLookAtPoint();
		Translation tcam = new Translation(-0.5, -0.5, -0.5);
		c.addTransformation(tcam);
		// camera 2
		Camera c2 = new Camera(21, 41, new Point3d(5, 5, 5), new Point3d(0,
				1.5, 0), new Vector3d(0, 1, 0), 1, 0.7);
		c2.fixLookAtPoint();
		Translation tcam2 = new Translation(0, 0, -0.5);
		c2.addTransformation(tcam2);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "I) Animation 2");
		try {
			s.addCamera(c2);
		} catch (CameraConflictException e) {
			System.out.println("camera conflict");
		}
		// prepare materials used for the scene
		Material blue = new Material(new Color(0.1, 0.1, 1.0), new Color(0.1,
				0.7, 1.0), new Color(0.1, 0.1, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.4, 0.2), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(10, 10, 10), new Color(0.4, 0.4, 0.4),
				new Color(0.7, 0.7, 0.7), 0.95);
		Material green = new Material(new Color(0.1, 0.6, 0.1), new Color(0.1,
				0.6, 0.1), new Color(0.1, 1.0, 0.1), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0.5,
						0.5, 0.5), 0.9);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.01),
				new Color(0.6, 0.6, 0.0), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material black = new Material(new Color(0, 0, 0.0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0.1, 0.1, 0.1), new Color(0.6,
						0.6, 0.6), new Color(0.2, 0.2, 0.2), new ExponentRGB(
						20, 20, 20), new Color(0.3, 0.3, 0.3), new Color(0, 0,
						0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.3, 0.3, 0.3),
				new Color(0, 0, 0), 0);
		Material base = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.4, 0.4, 0.4), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(8, 8, 8), new Color(0, 0, 0),
				new Color(0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(1, 20, new Color(0.8, 0.8, 0.8), 5,
				5, 5);
		PointLight light3 = new PointLight(21, 40, new Color(0.9, 0.8, 0.9),
				-10, -10, -10);
		// s.addPointLight(light2);
		// s.addPointLight(light3);
		Translation t1 = new Translation(1, 10, 0.8, 0, 0);
		Translation t2 = new Translation(11, 20, 0, 0, 0.8);
		Translation t3 = new Translation(21, 30, -0.8, 0, 0);
		Translation t4 = new Translation(31, 40, 0, 0, -0.8);
		Sphere s1 = new Sphere(1.5, new Point3d(-4, -0.5, -4), green);
		s1.addTransformation(t1);
		s1.addTransformation(t2);
		s1.addTransformation(t3);
		s1.addTransformation(t4);
		s.addObject(s1);
		SpotLight spot1 = new SpotLight(1, 20, new Color(0.9, 0.5, 0.5), -2, 8,
				5, 20, new Point3d(-4, -0.5, -4));
		spot1.fixLight();
		spot1.addTransformation(t1);
		spot1.addTransformation(t2);
		s.addPointLight(spot1);
		SpotLight spot2 = new SpotLight(21, 40, new Color(0.1, 0.9, 0.5), 0,
				10, -5, 25, new Point3d(0, -0.5, 0));
		s.addPointLight(spot2);
		Translation t5 = new Translation(1, 40, 0, -0.2, 0);
		spot2.addTransformation(t5);
		Sphere s2 = new Sphere(1, 40, 2, new Point3d(0, 10, 0), red);
		s2.addTransformation(t5);
		// s.addObject(s2);
		try {
			Cube cub = new Cube(1, 40, new Point3d(0, 0, 0), blue, 2);
			c.addTransformation(t2);
			s.addObject(cub);
			s.addObject(new CheckerBoardFloor(new Point3d(0, -2, 0), 20.0,
					20.0, black, white, 10, 8));
		} catch (Exception e) {
			System.out.println("Exception scene9 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 10
	public static Scene getScene10() {
		// create a camera
		Camera c = new Camera(new Point3d(0.1, 12, 0), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z) Presentation light 3");
		// prepare materials used for the scene
		Material blue = new Material(new Color(0.1, 0.1, 1.0), new Color(0.1,
				0.7, 1.0), new Color(0.1, 0.1, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.4, 0.2), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(10, 10, 10), new Color(0.4, 0.4, 0.4),
				new Color(0.7, 0.7, 0.7), 0.95);
		Material green = new Material(new Color(0.1, 0.6, 0.1), new Color(0.1,
				0.6, 0.1), new Color(0.1, 1.0, 0.1), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0.5,
						0.5, 0.5), 0.9);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.01),
				new Color(0.6, 0.6, 0.0), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material black = new Material(new Color(0, 0, 0.0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0.1, 0.1, 0.1), new Color(0.6,
						0.6, 0.6), new Color(0.2, 0.2, 0.2), new ExponentRGB(
						20, 20, 20), new Color(0.3, 0.3, 0.3), new Color(0, 0,
						0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.3, 0.3, 0.3),
				new Color(0, 0, 0), 0);
		Material base = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.4, 0.4, 0.4), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(8, 8, 8), new Color(0, 0, 0),
				new Color(0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.1, 0.1, 0.1));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(1, 20, new Color(0.8, 0.8, 0.8), 5,
				5, 5);
		PointLight light3 = new PointLight(21, 40, new Color(0.9, 0.8, 0.9),
				-10, -10, -10);
		// PointLightDiffuse light4 = new PointLightDiffuse(1,40,new
		// Color(0.9,0.9,0.9), 0, 7, 0, 1, 0, 0.05);
		SpotLightDiffuse light5 = new SpotLightDiffuse(1, 40, new Color(0.9,
				0.9, 0.9), 0, 8, 0, 30.0, new Point3d(0, 0, 0), 0, 0.00, 0.02);
		// s.addPointLight(light2);
		// s.addPointLight(light3);
		// s.addPointLight(light4);
		s.addPointLight(light5);
		s.addObject(new Sphere(1.5, new Point3d(-4, -0.5, 0), green));
		s.addObject(new Sphere(1.5, new Point3d(0, -0.5, -4), green));
		s.addObject(new Sphere(1.5, new Point3d(0, -0.5, 4), green));
		s.addObject(new Sphere(1.5, new Point3d(4, -0.5, 0), green));
		try {
			Cube cub = new Cube(new Point3d(0, 0, 0), blue, 2);
			s.addObject(cub);
			s.addObject(new CheckerBoardFloor(new Point3d(0, -2, 0), 20.0,
					20.0, black, white, 10, 8));
		} catch (Exception e) {
			System.out.println("Exception scene10 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 11
	public static Scene getScene11() {
		// create a camera
		Camera c = new Camera(new Point3d(0, 6, -8), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z) Presentation Mapping 3");
		// prepare materials used for the scene
		Material blue = new Material(new Color(0.1, 0.1, 1.0), new Color(0.1,
				0.7, 1.0), new Color(0.1, 0.1, 1.0), new Color(0.4, 0.4, 0.4),
				new Color(0.5, 0.5, 0.5), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(10, 10, 10), new Color(0.5, 0.5, 0.5),
				new Color(0, 0, 0), 0);
		Material base = new Material(new Color(0.7, 0.4, 0.2), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.6, 0.4, 0.4),
				new Color(0.8, 0.6, 0.6), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.7, 0.7, 0.7));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.95, 0.95, 0.95), 0, 3,
				-5);
		s.addPointLight(light2);
		// add objects
		s.addObject(new Sphere(2, new Point3d(0, 0, 2.5), base, textUn,
				bumpText2, alpha2, null));
		try {
			s.addObject(new Rectangle(new Point3d(0, -2, 2.5), 20.0, 14.0,
					blue, waves, false));
		} catch (Exception e) {
			System.out.println("Exception scene11 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 12
	public static Scene getScene12() {
		// create a camera
		Camera c = new Camera(new Point3d(6, 5, 6), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "L) Animation mushroom.off");
		// prepare materials used for the scene
		Material red = new Material(new Color(0.0, 0.3, 0.5), new Color(0.0,
				0.3, 0.5), new Color(0.0, 0.3, 0.5), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0.4, 0.4, 0.4),
				new Color(0.8, 0.8, 0.8), 0.96);
		Material black = new Material(new Color(0, 0, 0.0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0.1, 0.1, 0.1), new Color(0.6,
						0.6, 0.6), new Color(0.2, 0.2, 0.2), new ExponentRGB(
						20, 20, 20), new Color(0.3, 0.3, 0.3), new Color(0, 0,
						0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.3, 0.3, 0.3),
				new Color(0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.3, 0.3, 0.3));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 3, 3, 3);
		s.addPointLight(light2);
		Rotation r1 = new Rotation(new Point3d(0, 0, 0), 4, 4, 4);
		try {
			OffObject obj = new OffObject(new Point3d(0, 0.5, 0), red,
					"mushroom.off", 2, 2, 2, true);
			obj.addTransformation(r1);
			s.addObject(obj);
			s.addObject(new CheckerBoardFloor(new Point3d(0, -2, 0), 20.0,
					20.0, black, white, 10, 8));
		} catch (Exception e) {
			System.out.println("Exception scene12 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 13
	public static Scene getScene13() {
		// create a camera
		Camera c = new Camera(1, 300, new Point3d(0, 3, -8), new Point3d(0, 3,
				0), new Vector3d(0, 1, 0), 1, 0.7);
		Camera c2 = new Camera(301, 600, new Point3d(22, 10, 0), new Point3d(4,
				3, 0), new Vector3d(0, 1, 0), 1, 0.7);
		Camera c3 = new Camera(601, 1200, new Point3d(-10, 10, 0), new Point3d(
				0, 4, 0), new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "M) Animation final 1");
		try {
			s.addCamera(c2);
			s.addCamera(c3);
		} catch (CameraConflictException e) {
			System.out.println(e.getMessage());
		}
		// prepare materials used for the scene
		Material base1 = new Material(new Color(0.0, 0.3, 0.5), new Color(0.0,
				0.3, 0.5), new Color(0.0, 0.3, 0.5), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.1, 0.1), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0.0, 0.0, 0.0),
				new Color(0, 0, 0), 0);
		Material blue = new Material(new Color(0.0, 0.3, 0.9), new Color(0.0,
				0.3, 0.9), new Color(0.0, 0.3, 0.5), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.5, 0.5), new Color(0.7, 0.7, 0.7),
				new ExponentRGB(15, 15, 15), new Color(0.8, 0.8, 0.8),
				new Color(0, 0, 0), 0);
		Material blue2 = new Material(new Color(0.2, 0.2, 0.8), new Color(0.2,
				0.2, 0.8), new Color(0.2, 0.2, 0.8), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.5, 0.5), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(10, 10, 10), new Color(0.8, 0.8, 0.8),
				new Color(0.6, 0.6, 0.6), 0.97);
		Material green = new Material(new Color(0.2, 0.9, 0.2), new Color(0.2,
				0.8, 0.9), new Color(0.2, 0.7, 0.2), new Color(0.2, 0.2, 0.2),
				new Color(0.7, 0.5, 0.7), new Color(0.3, 0.7, 0.3),
				new ExponentRGB(15, 15, 15), new Color(0.0, 0.0, 0.0),
				new Color(0, 0, 0), 0);
		Material yellow = new Material(new Color(0.8, 0.8, 0.0), new Color(0.8,
				0.8, 0.0), new Color(0.9, 0.3, 0.1), new Color(0.4, 0.4, 0.4),
				new Color(0.2, 0.4, 0.5), new Color(0.6, 0.6, 0.7),
				new ExponentRGB(15, 15, 15), new Color(0.0, 0.0, 0.0),
				new Color(0, 0, 0), 0);
		Material black = new Material(new Color(0, 0, 0.0), new Color(0, 0, 0),
				new Color(0, 0, 0), new Color(0.1, 0.1, 0.1), new Color(0.6,
						0.6, 0.6), new Color(0.2, 0.2, 0.2), new ExponentRGB(
						20, 20, 20), new Color(0, 0, 0), new Color(0, 0, 0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material black2 = new Material(new Color(0.9, 0.07, 0.8), new Color(
				0.9, 0.07, 0.8), new Color(0.9, 0.07, 0.8), new Color(0.1, 0.1,
				0.1), new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.3, 0.3, 0.3),
				new Color(0.7, 0.7, 0.7), 0.95);

		// LIGHTS
		AmbientLight light1 = new AmbientLight(new Color(0.3, 0.3, 0.3));
		s.setAmbientLight(light1);
		SpotLightDiffuse light3 = new SpotLightDiffuse(1, 150, new Color(0.85,
				0.85, 0.85), 0, 5, -8, 30, new Point3d(0, 3, 0), 1, 0.01, 0);
		s.addPointLight(light3);
		PointLightDiffuse light2 = new PointLightDiffuse(150, 1200, new Color(
				0.6, 0.6, 0.8), 5, 6, 5, 1, 0.01, 0);
		s.addPointLight(light2);
		PointLightDiffuse light4 = new PointLightDiffuse(150, 1200, new Color(
				0.1, 0.1, 0.8), -5, 6, -5, 1, 0.01, 0);
		s.addPointLight(light4);
		PointLight light5 = new PointLight(600, 1200, new Color(0.8, 0.8, 0.8),
				0, 6, 0);
		s.addPointLight(light5);

		// Mappings

		// objects
		Sphere s1 = new Sphere(1, 1200, 2, new Point3d(0, 3, 0), base1, text2,
				bump2, null, null);
		s.addObject(s1);
		Sphere s2 = new Sphere(200, 1200, 2, new Point3d(0, -3, -8), blue,
				null, null, alpha2, null);
		s.addObject(s2);
		Sphere s3 = new Sphere(200, 1200, 2, new Point3d(8, -3, 0), green,
				text3, null, null, null);
		s.addObject(s3);
		Sphere s4 = new Sphere(200, 1200, 2, new Point3d(-8, -3, 0), yellow,
				null, null, null, reflec1);
		s.addObject(s4);
		Cube obj1 = null;
		try {
			// obj1 = new OffObject(600,1200,new
			// Point3d(-3,4,-3),black2,"m47.off",6,6,6,true);
			obj1 = new Cube(600, 1200, new Point3d(0, 4, 0), black2, 2);
			s.addObject(obj1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// bottom of the cube
		GeneralCube cub1 = new GeneralCube(400, 1200,
				new Point3d(0, -7.5, 3.5), blue2, 8, 1, 1);
		GeneralCube cub2 = new GeneralCube(400, 1200,
				new Point3d(0, -7.5, -3.5), blue2, 8, 1, 1);
		GeneralCube cub3 = new GeneralCube(400, 1200,
				new Point3d(3.5, -7.5, 0), blue2, 1, 1, 8);
		GeneralCube cub4 = new GeneralCube(400, 1200,
				new Point3d(-3.5, -7.5, 0), blue2, 1, 1, 8);
		s.addObject(cub1);
		s.addObject(cub2);
		s.addObject(cub3);
		s.addObject(cub4);
		// top of the cube
		GeneralCube cub5 = new GeneralCube(400, 1200,
				new Point3d(0, -0.25, 3.5), blue2, 8, 1, 1);
		GeneralCube cub6 = new GeneralCube(400, 1200, new Point3d(0, -0.25,
				-3.5), blue2, 8, 1, 1);
		GeneralCube cub7 = new GeneralCube(400, 1200,
				new Point3d(3.5, -0.25, 0), blue2, 1, 1, 8);
		GeneralCube cub8 = new GeneralCube(400, 1200, new Point3d(-3.5, -0.25,
				0), blue2, 1, 1, 8);
		s.addObject(cub5);
		s.addObject(cub6);
		s.addObject(cub7);
		s.addObject(cub8);
		// link between cubes
		GeneralCube cub9 = new GeneralCube(400, 1200,
				new Point3d(-3.5, -4, 3.5), blue2, 1, 8, 1);
		GeneralCube cub10 = new GeneralCube(400, 1200, new Point3d(-3.5, -4,
				-3.5), blue2, 1, 8, 1);
		GeneralCube cub11 = new GeneralCube(400, 1200, new Point3d(3.5, -4,
				-3.5), blue2, 1, 8, 1);
		GeneralCube cub12 = new GeneralCube(400, 1200,
				new Point3d(3.5, -4, 3.5), blue2, 1, 8, 1);
		s.addObject(cub9);
		s.addObject(cub10);
		s.addObject(cub11);
		s.addObject(cub12);
		// transformations
		// turn around 1st sphere
		Rotation r1 = new Rotation(1, 200, new Point3d(0, 3, 0), 0, 1.8, 0);
		c.fixLookAtPoint();
		c.addTransformation(r1);
		light3.fixLookAt();
		light3.addTransformation(r1);
		// move sphere on throws side and move the camera
		Translation t1 = new Translation(150, 200, 0, 0, 0.16);
		s1.addTransformation(t1);
		Translation t2 = new Translation(150, 200, 0.1, 0.16, -0.1);
		c.addTransformation(t2);
		// make new spheres appear and adjust camera
		Translation t3 = new Translation(200, 300, 0, 0.06, 0);
		s2.addTransformation(t3);
		s3.addTransformation(t3);
		s4.addTransformation(t3);
		Translation t4 = new Translation(200, 300, 0, 0.015, 0.02);
		c.addTransformation(t4);
		// turn around all spheres
		Rotation r2 = new Rotation(300, 1200, new Point3d(0, 3, 0), 0, 4.5, 0);
		s1.addTransformation(r2);
		s2.addTransformation(r2);
		s3.addTransformation(r2);
		s4.addTransformation(r2);
		// move the cube
		Translation t5 = new Translation(400, 700, 0, 0.027, 0);
		cub1.addTransformation(t5);
		cub2.addTransformation(t5);
		cub3.addTransformation(t5);
		cub4.addTransformation(t5);
		cub5.addTransformation(t5);
		cub6.addTransformation(t5);
		cub7.addTransformation(t5);
		cub8.addTransformation(t5);
		cub9.addTransformation(t5);
		cub10.addTransformation(t5);
		cub11.addTransformation(t5);
		cub12.addTransformation(t5);
		// rotate the main object
		Rotation r3 = new Rotation(600, 1200, new Point3d(0, 4, 0), 1.5, 1.5,
				1.5);
		obj1.addTransformation(r3);
		// move last camera
		c3.fixLookAtPoint();
		Rotation r4 = new Rotation(600, 1200, new Point3d(0, 3, 0), 0, 2.0, 0);
		c3.addTransformation(r4);
		Translation t6 = new Translation(600, 800, 0, 0.05, 0);
		Translation t7 = new Translation(801, 100, 0, 0.1, 0);
		c3.addTransformation(t6);
		c3.addTransformation(t7);
		Rotation r5 = new Rotation(1000, 1200, new Point3d(0, 4, 0), 0, 0.0, 2);
		c3.addTransformation(r5);
		try {
			s.addObject(new CheckerBoardFloor(new Point3d(0, 0, 0), 50.0, 50.0,
					black, white, 20, 15));
		} catch (Exception e) {
			System.out.println("Exception scene13 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 14
	public static Scene getScene14() {
		// create a camera
		Camera c = new Camera(new Point3d(0, 1.4, -8), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z) Presentation Light 1");
		// prepare materials used for the scene
		Material blue = new Material(new Color(1, 1, 1), new Color(1, 1, 1),
				new Color(1, 1, 1), new Color(0.075, 0.075, 0.075), new Color(
						0.075, 0.075, 0.075), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.01, 0.01, 0.01),
				new Color(0.85, 0.85, 0.85), 0.97);
		Material green = new Material(new Color(0.7, 0.7, 0.7), new Color(0.7,
				0.7, 0.7), new Color(1, 1, 1), new Color(0.15, 0.15, 0.15),
				new Color(0.25, 0.25, 0.25), new Color(1, 1, 1),
				new ExponentRGB(20, 20, 20), new Color(0.75, 0.75, 0.75),
				new Color(0, 0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.5, 0.3, 0.3),
				new Color(0.7, 0.7, 0.7), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.4, 0.4, 0.4),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.1, 0.1, 0.1));
		s.setAmbientLight(light1);
		PointLightDiffuse light2 = new PointLightDiffuse(new Color(0.95, 0.95,
				0.95), -1, 0, 0, 0, 0.02, 0.01);
		s.addPointLight(light2);
		// add objects
		s.addObject(new Sphere(1, new Point3d(0, 0.6, 2), blue));
		s.addObject(new Sphere(1, new Point3d(1.5, -0.5, 7), green));

		try {
			// s.addObject(new Cube(new Point3d(-1, -0.6, 1),blue,2));
			s.addObject(new CheckerBoardFloor(new Point3d(4.5, -2, 21.5), 13.0,
					47.0, red, yellow, 20, 40));
		} catch (Exception e) {
			System.out.println("Exception scene1 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 15
	public static Scene getScene15() {
		// create a camera
		Camera c = new Camera(new Point3d(7, 7, 0), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z)  Presentation Light 2");
		// prepare materials used for the scene
		Material black = new Material(new Color(0.6, 0.6, 0.6), new Color(0.6,
				0.6, 0.6), new Color(0.6, 0.6, 0.6), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.3, 0.3, 0.3),
				new Color(0, 0, 0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.3, 0.3, 0.3),
				new Color(0, 0, 0), 0);
		Material base = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.4, 0.4, 0.4), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(8, 8, 8), new Color(0, 0, 0),
				new Color(0, 0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.3, 0.3, 0.3));
		s.setAmbientLight(light1);
		SpotLight light4 = new SpotLight(1, 40, new Color(0.8, 0.2, 0.2), 0,
				10, 0, 7, new Point3d(0, 0, 0));
		SpotLight light5 = new SpotLight(1, 40, new Color(0.2, 0.8, 0.2), 0,
				10, -10, 7, new Point3d(0, 0, -2));
		SpotLight light6 = new SpotLight(1, 40, new Color(0.2, 0.2, 0.8), 0,
				10, 10, 7, new Point3d(0, 0, 2));
		s.addPointLight(light6);
		s.addPointLight(light4);
		s.addPointLight(light5);
		s.addObject(new Sphere(1, new Point3d(0, 1, 0), base));
		try {
			s.addObject(new CheckerBoardFloor(new Point3d(0, 0, 0), 20.0, 20.0,
					black, white, 10, 8));
		} catch (Exception e) {
			System.out.println("Exception scene15 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 16
	public static Scene getScene16() {
		// create a camera
		// Camera c = new Camera(new Point3d(5,2,5),new Point3d(-1,0,0), new
		// Vector3d(0,1,0), 1, 0.7);
		Camera c = new Camera(new Point3d(4, 2, 7), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "Z) Presentation SmoothOff 2");
		// prepare materials used for the scene
		Material plop = new Material(new Color(0.7, 0.7, 0.9), new Color(0.7,
				0.7, 0.9), new Color(0.7, 0.0, 0.7), new Color(0.2, 0.2, 0.2),
				new Color(0.5, 0.5, 0.5), new Color(0, 0, 0), new ExponentRGB(
						10, 10, 10), new Color(0.75, 0.75, 0.75), new Color(0,
						0, 0), 0);
		Material black = new Material(new Color(0.3, 0.3, 0.3), new Color(0.3,
				0.3, 0.3), new Color(0.3, 0.3, 0.3), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		Material white = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.6, 0.6, 0.6),
				new Color(0, 0, 0), 0);
		Material base = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.1, 0.1, 0.1),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material base1 = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.3, 0.3, 0.3),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material base2 = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.3, 0.3, 0.3),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material green = new Material(new Color(0.0, 0.7, 0.0), new Color(0.0,
				0.7, 0.0), new Color(0, 1, 0), new Color(0.15, 0.15, 0.15),
				new Color(0.25, 0.25, 0.25), new Color(1, 1, 1),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), -1, 4, 1);
		s.addPointLight(light2);
		PointLight light3 = new PointLight(new Color(1, 1, 1), 2, 3, 2);
		s.addPointLight(light3);
		// s.addObject(new Sphere(2, new Point3d(0,1,-5),green));

		try {
			Rectangle rect = new Rectangle(new Point3d(0, 5, 0), 100, 100,
					base2, text5, false);
			s.addObject(rect);
			Rectangle rectt = new Rectangle(new Point3d(-3, 0, 10),
					new Point3d(-3, 5, 10), new Point3d(6, 5, 10), new Point3d(
							6, 0, 10), base2, text5, false);
			s.addObject(rectt);
			Rectangle rect1 = new Rectangle(new Point3d(-3, 0, 0), new Point3d(
					-3, 5, 0), new Point3d(6, 5, 0), new Point3d(6, 0, 0),
					base1, text4, bump3, null, null, false);
			Rectangle rect2 = new Rectangle(new Point3d(-3, 0, 0), new Point3d(
					-3, 5, 0), new Point3d(-3, 5, 10), new Point3d(-3, 0, 10),
					base1, text4, bump3, null, null, false);
			Rectangle rect3 = new Rectangle(new Point3d(6, 0, 0), new Point3d(
					6, 5, 0), new Point3d(6, 5, 10), new Point3d(6, 0, 10),
					base1, text4, bump3, null, null, false);
			s.addObject(rect1);
			s.addObject(rect2);
			s.addObject(rect3);
			s.addObject(new SmoothOffObject(new Point3d(0, 0, 0), plop,
					"m102.off", 4, 4, 4, true));
			s.addObject(new CheckerBoardFloor(new Point3d(0, -0, 0), 20.0,
					20.0, black, white, 10, 10));
		} catch (Exception e) {
			System.out.println("Exception scene7 : " + e.getMessage());
			System.exit(0);
		}

		return s;
	}

	// scene 17
	public static Scene getScene17() {
		// create a camera
		Camera c = new Camera(new Point3d(1, 25, 0), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "X) Hello world ");
		// prepare materials used for the scene
		Material green = new Material(new Color(0.1, 0.1, 0.8), new Color(0.0,
				0.0, 0.8), new Color(0.1, 0.0, 0.6), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.5, 0.3, 0.3),
				new Color(0.7, 0.7, 0.7), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		// add lights to the scene
		AmbientLight light1 = new AmbientLight(new Color(0.5, 0.5, 0.5));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(new Color(0.8, 0.8, 0.8), 20, 10, -2);
		PointLight light3 = new PointLight(new Color(0.9, 0.8, 0.9), 0, 10, 0);
		s.addPointLight(light2);
		s.addPointLight(light3);
		// Sphere s2 = new Sphere(1,40,2,new Point3d(0,0,0),red);
		// s.addObject(s2);
		// add objects
		try {
			s.addObject(new HeightField(new Point3d(0, 0, 0), "height3.png",
					red, 5, 1, 1));
		} catch (Exception e) {
			System.out.println("Exception scene17 : " + e.getMessage());
			System.exit(0);
		}
		return s;
	}

	// scene 18
	public static Scene getScene18() {
		// create a camera
		Camera c = new Camera(0, 240, new Point3d(0, 1.4, -8), new Point3d(0,
				0, 0), new Vector3d(0, 1, 0), 1, 0.7);
		Camera c2 = new Camera(241, 1000, new Point3d(0, 9, -10), new Point3d(
				0, 8, 0), new Vector3d(0, 1, 0), 1, 0.7);
		// create the complete scene with the camera and the scene
		Scene s = new Scene(c, "z) Animation final 2");
		try {
			s.addCamera(c2);
		} catch (Exception e) {
			System.out.println("Exception scene18 : " + e.getMessage());
			System.exit(0);
		}
		// prepare materials used for the scene
		Material blue = new Material(new Color(1, 1, 1), new Color(1, 1, 1),
				new Color(1, 1, 1), new Color(0.075, 0.075, 0.075), new Color(
						0.075, 0.075, 0.075), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0.01, 0.01, 0.01),
				new Color(0.85, 0.85, 0.85), 0.97);
		Material green = new Material(new Color(0.7, 0.7, 0.7), new Color(0.7,
				0.7, 0.7), new Color(1, 1, 1), new Color(0.15, 0.15, 0.15),
				new Color(0.25, 0.25, 0.25), new Color(1, 1, 1),
				new ExponentRGB(20, 20, 20), new Color(0.75, 0.75, 0.75),
				new Color(0, 0, 0), 0);
		Material red = new Material(new Color(1.0, 0.0, 0.0), new Color(1.0,
				0.0, 0.0), new Color(1.0, 0.0, 0.0), new Color(0.5, 0.3, 0.3),
				new Color(0.7, 0.7, 0.7), new Color(0.2, 0.04, 0.04),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.4, 0.4, 0.4),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.0),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material base1 = new Material(new Color(1.0, 1.0, 1.0), new Color(1.0,
				1.0, 1.0), new Color(1.0, 1.0, 1.0), new Color(0.3, 0.3, 0.3),
				new Color(0.6, 0.6, 0.6), new Color(0.2, 0.2, 0.2),
				new ExponentRGB(20, 20, 20), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material green2 = new Material(new Color(0.1, 1, 0.1), new Color(1.0,
				1.0, 0.1), new Color(0.1, 1.0, 0.1), new Color(0.1, 0.1, 0.1),
				new Color(0.1, 0.2, 0.2), new Color(0.1, 0.3, 0.05),
				new ExponentRGB(10, 10, 10), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material blue2 = new Material(new Color(0.1, 0.1, 1.0), new Color(0.3,
				0.3, 1.0), new Color(0.3, 0.3, 1.0), new Color(0.4, 0.4, 0.4),
				new Color(0.5, 0.5, 0.5), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(10, 10, 10), new Color(0.5, 0.5, 0.5),
				new Color(0.6, 0.6, 0.6), 0.98);
		Material blue3 = new Material(new Color(0.3, 0.3, 0.8), new Color(0.3,
				0.3, 0.8), new Color(0.3, 0.3, 0.8), new Color(0.6, 0.6, 0.6),
				new Color(0.5, 0.5, 0.5), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(40, 40, 40), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		Material duck = new Material(new Color(0.7, 0.7, 0.2), new Color(0.7,
				0.9, 0.3), new Color(0.3, 0.3, 0.8), new Color(0.6, 0.6, 0.6),
				new Color(0.5, 0.5, 0.5), new Color(0.1, 0.01, 0.12),
				new ExponentRGB(40, 40, 40), new Color(0.6, 0.6, 0.6),
				new Color(0.6, 0.6, 0.6), 0.92);
		// add lights to the scnene
		AmbientLight light1 = new AmbientLight(new Color(0.15, 0.15, 0.15));
		s.setAmbientLight(light1);
		PointLight light2 = new PointLight(40, 250,
				new Color(0.95, 0.95, 0.95), -4, 20, -11);
		s.addPointLight(light2);
		SpotLightDiffuse spot1 = new SpotLightDiffuse(1, 50, new Color(0.95,
				0.95, 0.95), -2, 1.4, -8, 7, new Point3d(-2, 1.25, 0), 0, 0.05,
				0.03);
		s.addPointLight(spot1);
		PointLight light3 = new PointLight(250, 500,
				new Color(0.95, 0.95, 0.95), -8, 15, 0);
		s.addPointLight(light3);
		PointLight light4 = new PointLight(250, 500,
				new Color(0.95, 0.95, 0.95), 10, 15, 0);
		s.addPointLight(light4);
		// mappings

		// add objects
		Sphere s1 = new Sphere(50, 240, 1, new Point3d(0, 0.6, 2), blue);
		s.addObject(s1);
		Sphere s2 = new Sphere(50, 240, 1, new Point3d(1.5, -0.5, 7), green);
		s.addObject(s2);
		Rectangle rect1 = null;
		Rectangle rect2 = null;
		Rectangle rect3 = null;
		Rectangle rect4 = null;
		CheckerBoardFloor check1 = null;
		SmoothHeightField height1 = null;
		SmoothOffObject off1 = null;
		Sphere s3 = null;
		try {
			rect1 = new Rectangle(0, 60, new Point3d(-3, -2, -2), new Point3d(
					-3, 2, -2), new Point3d(3, 2, -2), new Point3d(3, -2, -2),
					base1, titleText, false);
			s.addObject(rect1);
			rect2 = new Rectangle(100, 195, new Point3d(-3, -2, -2),
					new Point3d(-3, 2, -2), new Point3d(3, 2, -2), new Point3d(
							3, -2, -2), base1, quest, false);
			s.addObject(rect2);
			check1 = new CheckerBoardFloor(50, 240, new Point3d(4.5, -2, 21.5),
					13.0, 47.0, red, yellow, 20, 40);
			s.addObject(check1);
			height1 = new SmoothHeightField(250, 1000, new Point3d(0, 0, 0),
					"height.jpg", green2, 28, 20, 20);
			s.addObject(height1);
			rect3 = new Rectangle(250, 500, new Point3d(0, 6, 0), 60, 60,
					blue2, bumpWater, false);
			s.addObject(rect3);
			rect4 = new Rectangle(250, 500, new Point3d(0, 30, 0), 300, 300,
					blue3, false);
			s.addObject(rect4);
			s3 = new Sphere(260, 500, 1.5, new Point3d(0, 10, 0), red,
					bumpText22);
			s.addObject(s3);
			off1 = new SmoothOffObject(250, 500, new Point3d(0, 5.5, 0), duck,
					"m50.off", 7, 7, 7, true);
			s.addObject(off1);
		} catch (Exception e) {
			System.out.println("Exception scene18 : " + e.getMessage());
			System.exit(0);
		}
		// transformations
		spot1.fixLight();
		Translation t2 = new Translation(0, 10, 0.4, 0, 0);
		spot1.addTransformation(t2);
		Translation t3 = new Translation(11, 11, -4, -0.9, 0);
		spot1.addTransformation(t3);
		Translation t4 = new Translation(12, 25, 0.5, 0, 0);
		spot1.addTransformation(t4);
		Translation t5 = new Translation(26, 26, -4.3, -1.1, 0);
		spot1.addTransformation(t5);
		Translation t6 = new Translation(27, 37, 0, -0.1, 0);
		spot1.addTransformation(t6);
		Translation t7 = new Translation(50, 60, 0, -0.4, 0);
		rect1.addTransformation(t7);
		c.fixLookAtPoint();
		Rotation r1 = new Rotation(60, 160, new Point3d(1.5, -0.5, 7), 0, 3.6,
				0);
		c.addTransformation(r1);
		Translation t8 = new Translation(180, 195, 0, -0.4, 0);
		rect2.addTransformation(t8);
		Translation t9 = new Translation(196, 220, -0.3, 0, 0);
		s1.addTransformation(t9);
		Translation t10 = new Translation(211, 240, 0.4, 0, 0);
		s2.addTransformation(t10);
		Translation t11 = new Translation(220, 240, 0, -1, 0);
		check1.addTransformation(t11);
		// Rotation r2= new Rotation(250,300,new Point3d(0,8,0.1),0,10,0);
		// c2.addTransformation(r2);
		Rotation r3 = new Rotation(250, 500, new Point3d(0, 8, 0), 0, 5, 0);
		c2.addTransformation(r3);
		// Translation t12= new Translation(240,260,0,6,0);
		// c.addTransformation(t12);
		return s;
	}

	// scene 19
	public static Scene getScene19() {
		Camera c = new Camera(new Point3d(0, 0, -50), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0), 1, 0.5);
		Scene s = new Scene(c, "P) Perlin Noise Test");
		Material yellow = new Material(new Color(1.0, 1.0, 0.0), new Color(1.0,
				1.0, 0.0), new Color(1.0, 1.0, 0.0), new Color(0.1, 0.1, 0.1),
				new Color(0.5, 0.5, 0.5), new Color(0.5, 0.5, 0.5),
				new ExponentRGB(12, 12, 12), new Color(0, 0, 0), new Color(0,
						0, 0), 0);
		AmbientLight light1 = new AmbientLight(new Color(0.1, 0.1, 0.1));
		s.setAmbientLight(light1);
		s.addObject(new SpherePerlin(10, new Point3d(0, 0, 0), yellow));
		PointLight light4 = new PointLight(new Color(1, 1, 1), 0, 0, -20);
		s.addPointLight(light4);
		PointLight light5 = new PointLight(new Color(1, 1, 1), 0, 0, -20);
		s.addPointLight(light5);

		return s;
	}
}
