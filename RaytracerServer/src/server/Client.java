package server;

import network.ThreadSocket;

/**
 * Class that stores information about a client.
 * 
 * @author guiguito
 *
 */
public class Client {

	private ThreadSocket sock;
	private String id;
	private String[] scenes;
	private int jobsDone;
	private long pointsComputed;
	private long timeComputing;
	private double ptsPerSecond;

	public Client(ThreadSocket sck, String ident, String[] sc) {
		id = ident;
		sock = sck;
		scenes = sc;
		pointsComputed = 0;
		jobsDone = 0;
		timeComputing = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ThreadSocket getSock() {
		return sock;
	}

	public void setSock(ThreadSocket sock) {
		this.sock = sock;
	}

	public long getPointsComputed() {
		return pointsComputed;
	}

	public void setPointsComputed(long pointsComputed) {
		this.pointsComputed = pointsComputed;
	}

	public String[] getScenes() {
		return scenes;
	}

	public void setScenes(String[] scenes) {
		this.scenes = scenes;
	}

	public int getJobsDone() {
		return jobsDone;
	}

	public void setJobsDone(int jobsDone) {
		this.jobsDone = jobsDone;
	}

	public long getTimeComputing() {
		return timeComputing;
	}

	public void setTimeComputing(long timeComputing) {
		this.timeComputing = timeComputing;
		updatePtsPerSecond();
	}

	private void updatePtsPerSecond() {
		ptsPerSecond = pointsComputed * 1000.0 / timeComputing;
	}

	public double getPtsPerSecond() {
		return ptsPerSecond;
	}

	public void setPtsPerSecond(double ptsPerSecond) {
		this.ptsPerSecond = ptsPerSecond;
	}

}
