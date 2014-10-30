package network;

import java.io.Serializable;

/**
 * Message with a list of points to compute.
 * 
 * @author guiguito
 *
 */
public class PointsToCompute implements Serializable {

	private int[] xCoordinates;
	private int[] yCoordinates;

	public PointsToCompute(int[] xCoord, int[] yCoord) {
		xCoordinates = xCoord;
		yCoordinates = yCoord;
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
