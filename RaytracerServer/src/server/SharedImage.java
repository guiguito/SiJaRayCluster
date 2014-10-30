package server;


/**
 * Image we want to render.
 * 
 * @author guiguito
 */
public class SharedImage {

	double[][] imageRed;
	double[][] imageGreen;
	double[][] imageBlue;

	public SharedImage(int x, int y) {
		imageRed = new double[x][y];
		imageGreen = new double[x][y];
		imageBlue = new double[x][y];

	}

	public synchronized void putResults(int[] x, int[] y, double[] red,
			double[] green, double[] blue) {
		for (int i = 0; i < x.length; i++) {
			imageRed[x[i]][y[i]] = red[i];
			imageGreen[x[i]][y[i]] = green[i];
			imageBlue[x[i]][y[i]] = blue[i];
		}
	}

	public Materials.Color getColor(int x, int y) {
		return new Materials.Color(imageRed[x][y], imageGreen[x][y],
				imageBlue[x][y]);
	}
}