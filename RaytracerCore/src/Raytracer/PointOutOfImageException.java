package Raytracer;

/**
 * Exception if we ask the raytracer to render a point out of the image it has
 * been configured for.
 * 
 * @author Guilhem Duche
 */
public class PointOutOfImageException extends Exception {

	/**
	 * Creates a new instance of PointOutOfImageException
	 * 
	 * @param message
	 *            the error message.
	 */
	public PointOutOfImageException(String message) {
		super(message);
	}

}
