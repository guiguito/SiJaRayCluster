package server;

import network.PointsToCompute;

/**
 * Abastract class. Classes extending this class, are strategies to divide an
 * image to compute into several list of points.
 * 
 * @author guiguito
 */
public abstract class JobMaker {

	private int x;
	private int y;

	/**
	 * Constructor.
	 * 
	 * @param x
	 * @param y
	 */
	public JobMaker(int xx, int yy) {
		x = xx;
		y = yy;
	}

	public abstract void fillJobs(ConcurrentList<PointsToCompute> jobs);

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
