package Materials;

/**
 * Simple color with only float. Supposed to be used with values between 0 and
 * 1.
 * 
 * @author Guilhem Duche
 */
public class ExponentRGB implements Cloneable {

	public double red;
	public double green;
	public double blue;

	/**
	 * Creates a new instance of ExponentRGB
	 */
	public ExponentRGB(double r, double g, double b) {
		red = r;
		green = g;
		blue = b;
	}

	public double getRed() {
		return red;
	}

	public double getGreen() {
		return green;
	}

	public double getBlue() {
		return blue;
	}

	public ExponentRGB clone() {
		try {
			// Clone the color.
			ExponentRGB m = (ExponentRGB) super.clone();
			return m; // Return the clone.
		} catch (CloneNotSupportedException e) {
			// This shouldn't happen.
			throw new AssertionError();
		}
	}

}
