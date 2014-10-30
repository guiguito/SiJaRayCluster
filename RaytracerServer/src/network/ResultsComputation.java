package network;

import java.io.Serializable;

/**
 * Message for the result of a computation of a list of points.
 * 
 * @author guiguito
 *
 */
public class ResultsComputation implements Serializable {

	private long duration;
	private int[] xCoordinates;
	private int[] yCoordinates;
	private double[] red;
	private double[] green;
	private double[] blue;

	public ResultsComputation(long dur, int[] xCoord, int[] yCoord, double[] r,
			double[] g, double[] b) {
		duration = dur;
		xCoordinates = xCoord;
		yCoordinates = yCoord;
		red = r;
		green = g;
		blue = b;
	}

	public double[] getBlue() {
		return blue;
	}

	public void setBlue(double[] blue) {
		this.blue = blue;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public double[] getGreen() {
		return green;
	}

	public void setGreen(double[] green) {
		this.green = green;
	}

	public double[] getRed() {
		return red;
	}

	public void setRed(double[] red) {
		this.red = red;
	}

	public int[] getXCoordinates() {
		return xCoordinates;
	}

	public void setXCoordinates(int[] coordinates) {
		xCoordinates = coordinates;
	}

	public int[] getYCoordinates() {
		return yCoordinates;
	}

	public void setYCoordinates(int[] coordinates) {
		yCoordinates = coordinates;
	}

}
