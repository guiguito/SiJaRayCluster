package server;

import java.util.concurrent.Semaphore;

import network.PointsToCompute;
import network.ThreadSocket;

/**
 * Thread watching for available connections and sending points to compute.
 * 
 * @author guiguito
 */
public class RenderingThread extends Thread {

	private ConcurrentList<ThreadSocket> availableConnections = null;
	private ConcurrentList<ThreadSocket> usedConnections = null;
	private ConcurrentList<PointsToCompute> jobs = null;
	private boolean finished = false;
	private Semaphore availableSemaphore;
	private Semaphore availableJobsSemaphore;
	private ConcurrentHashMap<ThreadSocket, PointsToCompute> jobsSent;

	public RenderingThread(ConcurrentList<ThreadSocket> con,
			ConcurrentList<PointsToCompute> j,
			ConcurrentList<ThreadSocket> used, Semaphore s,
			ConcurrentHashMap<ThreadSocket, PointsToCompute> jobsSen,
			Semaphore jobsSemaphore) {
		availableConnections = con;
		usedConnections = used;
		jobs = j;
		availableSemaphore = s;
		jobsSent = jobsSen;
		availableJobsSemaphore = jobsSemaphore;
	}

	public void stopThread() {
		finished = true;
		availableSemaphore.release();
		availableJobsSemaphore.release();
	}

	public void run() {
		while (!finished) {
			try {
				availableSemaphore.acquire();// check if there is a connection
												// available
			} catch (InterruptedException e) {
				System.out.println("Trouble with availableSemaphore : "
						+ e.getMessage());
				e.printStackTrace();
			}
			if (!finished) {
				if (availableConnections.size() > 0 && jobs.size() == 0
						&& jobsSent.size() == 0) {
					// if no jobs available and no jobs sent stop the treah, the
					// rendering is finished
					availableSemaphore.release();
					stopThread();
				} else {
					// otherwise get a connection
					ThreadSocket sock = availableConnections.getNextAndRemove();
					usedConnections.add(sock);
					try {
						// for the case where no jobs ara available and some are
						// sent
						// if one sent is lost it will be restored to jobs
						availableJobsSemaphore.acquire();// check if there is a
															// job available
					} catch (InterruptedException e) {
						System.out
								.println("Trouble with availableJobsSemaphore : "
										+ e.getMessage());
						e.printStackTrace();
					}
					if (!finished) {
						PointsToCompute pts = jobs.getNextAndRemove();
						jobsSent.add(sock, pts);
						sock.send(pts);
					}
				}
			}
		}
	}

}
