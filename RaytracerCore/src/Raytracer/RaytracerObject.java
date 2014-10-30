/*
 * RaytracerObject.java
 *
 * Created on 9 février 2006, 23:26
 */

package Raytracer;

import java.util.ArrayList;
import java.util.Iterator;

import Transformations.ObjectNotSupportedException;
import Transformations.Transformation;

/**
 * Mother class off all object in the raytracer.
 * 
 * @author Guilhem Duche
 */
public class RaytracerObject {

	protected long beginOfLife;
	protected long endOfLife;
	protected ArrayList<Transformation> transformations;

	/**
	 * Creates a new instance of RaytracerObject
	 */
	public RaytracerObject() {
		transformations = new ArrayList<Transformation>();
		beginOfLife = 1;
		endOfLife = Long.MAX_VALUE;
	}

	/**
	 * Creates a new instance of RaytracerObject.
	 * 
	 * @param begin
	 *            the date of the beginning of the object's life.
	 * @param end
	 *            the date of the end of the object's life.
	 */
	public RaytracerObject(long begin, long end) {
		transformations = new ArrayList<Transformation>();
		beginOfLife = begin;
		endOfLife = end;
	}

	/**
	 * Get the date of the beginning of light's life.
	 * 
	 * @return the date of the beginning of light's life.
	 */
	public long getBeginOfLife() {
		return beginOfLife;
	}

	/**
	 * Get the date of the end of light's life.
	 * 
	 * @return the date of the end of light's life.
	 */
	public long getEndOfLife() {
		return endOfLife;
	}

	/**
	 * Set the date of beginning of light's life.
	 * 
	 * @param i
	 *            the new date of beginning of the light's life.
	 */
	public void setBeginOfLife(long i) {
		beginOfLife = i;
	}

	/**
	 * Set the date of end of light's life.
	 * 
	 * @param a
	 *            the new date of end of the light's life.
	 */
	public void setEndOfLife(long a) {
		endOfLife = a;
	}

	/**
	 * Get the transformations.
	 * 
	 * @return the transformations associated to the light.
	 */
	public ArrayList<Transformation> getTransformations() {
		return transformations;
	}

	/**
	 * Add a transformation in the history of the light.
	 * 
	 * @param trans
	 *            the transformation you want to add.
	 */
	public void addTransformation(Transformation trans) {
		transformations.add(trans);
	}

	/**
	 * Update characteristics according to the given time and the
	 * transformations of the light.
	 * 
	 * @param time
	 *            the time in the scene for the update.
	 */
	public void update(long time) throws ObjectNotSupportedException {
		Iterator<Transformation> it = transformations.iterator();
		while (it.hasNext()) {
			Transformation trans = it.next();
			if (time >= trans.getBegin() && time <= trans.getEnd()) {
				trans.apply(this, time);
			}
		}
	}

}
