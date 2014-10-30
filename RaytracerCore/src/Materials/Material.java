/*
 * Material.java
 *
 * Created on 6 janvier 2006, 10:57
 *
 */

package Materials;


/**
 * Material of an object.
 * 
 * @author Guilhem Duché
 */
public class Material implements Cloneable {

	Color diffuseColor; // diffuse color of the object;
	Color ambientColor; // diffuse color of the object;
	Color specColor; // specular color
	Color ka; // reaction with ambient light
	Color kd; // reaction with diffuse light
	Color ks; // reaction with specular light
	ExponentRGB ke; // exponent to control size of secular highlight
	Color kr;// reflection factor for each component
	Color kt;// transmission factor for each component
	double n;// indice of refraction

	/**
	 * Creates a new instance of Material.
	 * 
	 * @param amb
	 *            ambient color of the object.
	 * @param diff
	 *            diffuse color.
	 * @param spec
	 *            specular color.
	 * @param a
	 *            ambient coeff.
	 * @param d
	 *            diffuse coeff.
	 * @param s
	 *            specular coeff.
	 * @param e
	 *            specular exponent.
	 * @param kkr
	 *            reflection factor for each component.
	 * @param kkt
	 *            transmission factor for each component.
	 * @param ni
	 *            indice of refraction.
	 */
	public Material(Color amb, Color diff, Color spec, Color a, Color d,
			Color s, ExponentRGB e, Color kkr, Color kkt, double ni) {
		diffuseColor = diff;
		ambientColor = amb;
		specColor = spec;
		ka = a;
		kd = d;
		ks = s;
		ke = e;
		kr = kkr;
		kt = kkt;
		n = ni;
		if (n == 0)
			n = 1;// do not authorize 0 for indice. Put the indice of the air
					// instead.
	}

	/**
	 * Set the parameters for the reflexion.
	 * 
	 * @param col
	 *            the parameters for the reflexion.
	 */
	public void setReflection(Color col) {
		kr = col;
	}

	/**
	 * Set the parameters for the transmission.
	 * 
	 * @param col
	 *            the parameters for the transmission.
	 */
	public void setTransmission(Color col) {
		kt = col;
	}

	/**
	 * set the diffuse color of the object.
	 * 
	 * @param c
	 *            the color to affect.
	 */
	public void setDiffuseColor(Color c) {
		diffuseColor = c;
	}

	/**
	 * set the ambient color of the object.
	 * 
	 * @param c
	 *            the color to affect.
	 */
	public void setAmbientColor(Color c) {
		ambientColor = c;
	}

	/**
	 * set the specular color of the object.
	 * 
	 * @param c
	 *            the color to affect.
	 */
	public void setSpecularColor(Color c) {
		specColor = c;
	}

	/**
	 * set the all the colors of the object to the same.
	 * 
	 * @param c
	 *            the color to affect.
	 */
	public void setAllColors(Color c) {
		setAmbientColor(c);
		setDiffuseColor(c);
		setSpecularColor(c);
	}

	/**
	 * Get the diffuse color of the object. return diffuse color of the object.
	 */
	public Color getDiffuseColor() {
		return diffuseColor;
	}

	/**
	 * Get specular color of the object.
	 * 
	 * @return specular color of the object.
	 */
	public Color getSpecColor() {
		return specColor;
	}

	/**
	 * Get ambient color of the object.
	 * 
	 * @return ambient color of the object.
	 */
	public Color getAmbientColor() {
		return ambientColor;
	}

	/**
	 * Get the coefficient for ambient light.
	 * 
	 * @return coefficient for ambient light.
	 */
	public Color getKa() {
		return ka;
	}

	/**
	 * Get the coefficient for diffuse light.
	 * 
	 * @return coefficient for diffuse light.
	 */
	public Color getKd() {
		return kd;
	}

	/**
	 * Get the coefficient for specular light.
	 * 
	 * @return coefficient for specular light.
	 */
	public Color getKs() {
		return ks;
	}

	/**
	 * Get the exponent for specular light.
	 * 
	 * @return exponent for specular light.
	 */
	public ExponentRGB getKe() {
		return ke;
	}

	/**
	 * Get reflection factor.
	 * 
	 * @return refelection factor.
	 */
	public Color getKr() {
		return kr;
	}

	/**
	 * Get the indice of refraction.
	 * 
	 * @return the indice of refraction.
	 */
	public double getNi() {
		return n;
	}

	/**
	 * Get transmission factor.
	 * 
	 * @return transmission factor.
	 */
	public Color getKt() {
		return kt;
	}

	public Material clone() {
		try {
			// Clone the stack.
			Material m = (Material) super.clone();
			m.diffuseColor = (Color) diffuseColor.clone();
			m.ambientColor = (Color) ambientColor.clone();
			m.specColor = (Color) specColor.clone();
			m.ka = (Color) ka.clone();
			m.kd = (Color) kd.clone();
			m.ks = (Color) ks.clone();
			m.ke = (ExponentRGB) ke.clone();
			m.kr = (Color) kr.clone();
			m.kt = (Color) kt.clone();
			return m; // Return the clone.
		} catch (CloneNotSupportedException e) {
			// This shouldn't happen because Stack and
			// ArrayList implement Cloneable.
			throw new AssertionError();
		}
	}
}
