package server;

import network.PointsToCompute;

/**
 * Divide image into chunks
 * 
 * @author guiguito
 *
 */
public class ChunksJobMaker extends JobMaker {

	private int chunkSize;

	public ChunksJobMaker(int x, int y, int size) {
		super(x, y);
		chunkSize = size;
	}

	@Override
	public void fillJobs(ConcurrentList<PointsToCompute> jobs) {
		int current = 0;
		int[] xCoord = new int[chunkSize];
		int[] yCoord = new int[chunkSize];
		for (int b = 0; b < getY(); b++) {
			for (int a = 0; a < getX(); a++) {
				xCoord[current] = a;
				yCoord[current] = b;
				current++;
				if (current >= chunkSize) {
					jobs.add(new PointsToCompute(xCoord, yCoord));
					xCoord = new int[chunkSize];
					yCoord = new int[chunkSize];
					current = 0;
				}
			}
		}
		if (current > 0 && current < chunkSize) {
			int[] xCo = new int[current];
			int[] yCo = new int[current];
			System.arraycopy(xCoord, 0, xCo, 0, current);
			System.arraycopy(yCoord, 0, yCo, 0, current);
			jobs.add(new PointsToCompute(xCo, yCo));
		}
	}

	public int getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

}
