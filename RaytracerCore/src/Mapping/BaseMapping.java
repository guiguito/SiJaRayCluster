/*
 * BaseMapping.java
 *
 * Created on 14 février 2006, 18:25
 */

package Mapping;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Materials.Material;
import RaytracedObjects.RaytracedObject;

/**
 * Base class to create a mapping.
 * 
 * @author Guilhem Duche
 */
public abstract class BaseMapping {

	/**
	 * Creates a new instance of BaseMapping
	 */
	public BaseMapping() {
	}

	public abstract void applyMapping(double u, double v, Point3d intersection,
			Material material, Vector3d normal, double distance,
			RaytracedObject obj);

}
