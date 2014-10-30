package Materials;

/**
 * Simple color with only float. Supposed to be used with values between 0 and
 * 1.
 * 
 * @author Guilhem Duche
 */
public class Color implements Cloneable {

	public double red;
	public double green;
	public double blue;

	/**
	 * Creates a new instance of Color
	 */
	public Color(double r, double g, double b) {
		red = r;
		green = g;
		blue = b;
		if (r > 1 || g > 1 || b > 1 || r < 0 || g < 0 || b < 0) {
			System.out.println("Problem with a color !! r : " + r + " g : " + g
					+ " b : " + b);
		}
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

	public Color clone() {
		try {
			// Clone the color.
			Color m = (Color) super.clone();
			return m; // Return the clone.
		} catch (CloneNotSupportedException e) {
			// This shouldn't happen.
			throw new AssertionError();
		}
	}

	public String toString() {
		return "Color : " + red + " " + green + " " + blue;
	}

}
