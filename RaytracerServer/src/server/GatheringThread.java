package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.concurrent.Semaphore;

import network.MessageConf;
import network.ResultsComputation;

public class GatheringThread extends Thread {

	private ConcurrentList<ResultsComputation> results;
	private Semaphore semaphoreResultsPresents;
	private int amountExpected;
	private int amountArrived;
	private String filename;
	private MessageConf messageConf;
	private RenderingWindow window;
	private boolean running = false;
	private ServerLogic server;

	public GatheringThread(ConcurrentList<ResultsComputation> res, int number,
			Semaphore s, String file, MessageConf mess, ServerLogic serv) {
		results = res;
		amountExpected = number;
		semaphoreResultsPresents = s;
		amountArrived = 0;
		filename = file;
		messageConf = mess;
		server = serv;
	}

	public void stopThread() {
		running = false;
		semaphoreResultsPresents.release();
	}

	public void run() {
		running = true;
		// initialize rendering window
		window = new RenderingWindow(messageConf.getXRes(),
				messageConf.getYRes());
		window.setVisible(true);
		Image image = window.getImage();
		Dimension d = window.getSize();
		java.awt.Graphics offG = image.getGraphics();
		offG.setColor(Color.BLACK);
		offG.fillRect(0, 0, d.width, d.height);
		Graphics2D g2 = (Graphics2D) image.getGraphics();

		while ((amountArrived < amountExpected) && running) {
			try {
				semaphoreResultsPresents.acquire();
			} catch (InterruptedException e) {
				System.out.println("Trouble with semaphoreResultsPresents : "
						+ e.getMessage());
				e.printStackTrace();
			}
			if (running) {
				// get last resulkt
				ResultsComputation res = results.getNextAndRemove();
				if (res != null) {// @TODO check this condition again
					amountArrived++;
					int[] x = res.getXCoordinates();
					int[] y = res.getYCoordinates();
					double[] red = res.getRed();
					double[] green = res.getGreen();
					double[] blue = res.getBlue();
					for (int i = 0; i < x.length; i++) {
						java.awt.Color col = new java.awt.Color((float) red[i],
								(float) green[i], (float) blue[i]);
						g2.setColor(col);
						g2.drawRect(x[i], y[i], 1, 1);
					}
					image.flush();
					window.repaint();
				}
			}
		}

		if (amountArrived == amountExpected) {
			window.setFilename(filename);
			server.tellRenderingIsFinished();
		}
	}
}
