/*
 * Scene.java
 *
 * Created on 11 décembre 2005, 17:03
 */

package Raytracer;
import java.util.ArrayList;
import java.util.Iterator;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Lights.AmbientLight;
import Lights.PointLight;
import RaytracedObjects.IntersectionResult;
import RaytracedObjects.RaytracedObject;
import Transformations.ObjectNotSupportedException;


/**
 * An object scene, stores all the objects of a scene (not the camera) given in
 * world coordinates.
 * 
 * @author Guilhem Duché
 */
public class Scene {

	private ArrayList<RaytracedObject> objects;
	private AmbientLight ambientLight;
	private ArrayList<PointLight> lights;
	private long currentTime;
	private ArrayList<Camera> cameras;
	private String name;
	private Camera currentCam;

	/**
	 * Creates a new instance of Scene.
	 * 
	 * @param cc
	 *            the camera set up associated with the Scene.
	 * @param nam
	 *            name of the scene.
	 */
	public Scene(Camera cc, String nam) {
		objects = new ArrayList<RaytracedObject>();
		lights = new ArrayList<PointLight>();
		cameras = new ArrayList<Camera>();
		currentTime = 1;
		currentCam = cc;
		name = nam;
		try {
			addCamera(cc);
		} catch (Exception e) {

		}
	}

	/**
	 * Give the name of the scene.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Add a camera. Check if there is no conflicts with another camera.
	 * 
	 * @param ca
	 *            the camera to add.
	 */
	public boolean addCamera(Camera ca) throws CameraConflictException {
		Iterator<Camera> it = cameras.iterator();
		while (it.hasNext()) {
			Camera cam = it.next();
			if ((ca.getBeginOfLife() >= cam.getBeginOfLife() && ca
					.getBeginOfLife() <= cam.getEndOfLife())
					|| (ca.getEndOfLife() >= cam.getBeginOfLife() && ca
							.getEndOfLife() <= cam.getEndOfLife())) {
				throw new CameraConflictException(
						"Conflict between two cameras !");
			}
		}
		return cameras.add(ca);
	}

	/**
	 * Give the Camera object.
	 * 
	 * @return the current selected Camera object.
	 */
	public Camera getCamera() {
		return currentCam;
	}

	/**
	 * Add an object in the scene.
	 * 
	 * @param o
	 *            the object to add.
	 */
	public void addObject(RaytracedObject o) {
		objects.add(o);
	}

	/**
	 * Add a point light to the scene.
	 * 
	 * @param light
	 *            the point light to add
	 */
	public void addPointLight(PointLight light) {
		lights.add(light);
	}

	/**
	 * Set the ambient light of the scene.
	 * 
	 * @param light
	 *            the ambient light we want in the scene
	 */
	public void setAmbientLight(AmbientLight light) {
		ambientLight = light;
	}

	/**
	 * Get the ambient light.
	 * 
	 * @return the ambient light
	 */
	public AmbientLight getAmbientLight() {
		return ambientLight;
	}

	/**
	 * Update positions of the objects.
	 * 
	 * @param time
	 *            the new time of the scene.
	 */
	public void update(long time) throws ObjectNotSupportedException,
			NoCameraException {
		// update internal time
		currentTime = time + 1;

		// update camera
		if (time > currentCam.getEndOfLife()
				|| time < currentCam.getBeginOfLife()) {
			currentCam = null;
			// set good camera if needed
			Iterator<Camera> it = cameras.iterator();
			while (it.hasNext()) {
				Camera cam = it.next();
				if (time >= cam.getBeginOfLife() && time <= cam.getEndOfLife()) {
					currentCam = cam;
				}
			}
			if (currentCam == null)
				throw new NoCameraException(
						"No camera found for the given time " + currentTime
								+ ".");
		}
		currentCam.update(time);
		currentCam.updateParameters();

		// update objects
		Iterator<RaytracedObject> it = objects.iterator();
		while (it.hasNext()) {
			RaytracedObject obj = it.next();
			if (time >= obj.getBeginOfLife() && time <= obj.getEndOfLife()) {
				obj.update(time);
			}
		}
		// update lights.
		Iterator<PointLight> it2 = lights.iterator();
		while (it2.hasNext()) {
			PointLight obj = it2.next();
			if (time >= obj.getBeginOfLife() && time <= obj.getEndOfLife()) {
				obj.update(time);
			}
		}
	}

	/**
	 * Get the list of point lights.
	 * 
	 * @return the point lights of the scene
	 */
	public ArrayList<PointLight> getLights() {
		// select the activated lights
		ArrayList<PointLight> plop = new ArrayList<PointLight>();
		Iterator<PointLight> it = lights.iterator();
		while (it.hasNext()) {
			PointLight l = it.next();
			if (currentTime >= l.getBeginOfLife()
					&& currentTime <= l.getEndOfLife()) {
				plop.add(l);
			}
		}
		return plop;
	}

	/**
	 * Analyze the path of the ray and give the resul.
	 * 
	 * @param eyePoint
	 *            the origin of the ray.
	 * @param direction
	 *            the direction of the ray.
	 * @param obje
	 *            the object from wich the ray is sent. Null if sent from
	 *            camera.
	 * @return The color of the result.
	 */
	public IntersectionResult getResul(Point3d eyePoint, Vector3d direction,
			RaytracedObject obje) {
		double nearestDistance = -1;
		IntersectionResult col = null;
		Iterator<RaytracedObject> it = objects.iterator();
		while (it.hasNext()) {
			RaytracedObject obj = it.next();
			if (currentTime >= obj.getBeginOfLife()
					&& currentTime <= obj.getEndOfLife()) {
				IntersectionResult result = obj.getNearestIntersection(
						eyePoint, direction, obje);
				double intersectionDistance = -1;
				if (result != null) {
					intersectionDistance = result.getDistance();
				}

				if (result != null && intersectionDistance > 0
						&& intersectionDistance < nearestDistance) {// another
																	// intersection
																	// found and
																	// we keep
																	// the
																	// nearest
					nearestDistance = intersectionDistance;
					col = result;
				} else if (result != null && nearestDistance == -1
						&& intersectionDistance > 0) {// first intersection
														// found
					nearestDistance = intersectionDistance;
					col = result;
				}
			}
		}
		return col;
	}

}
