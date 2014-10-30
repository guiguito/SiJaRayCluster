package client;

import java.util.concurrent.Semaphore;

import network.PointsToCompute;
import network.ResultsComputation;
import network.ThreadSocket;
import Raytracer.Raytracer;

/**
 * Thread responsible of sending results.
 * @author guiguito
 */
public class SendingThread extends Thread {

	private ConcurrentList<ResultsComputation> results;
	private Semaphore availableResultsToSendSemaphore;
	private boolean finished;
	private GuiClient gui;
	private ThreadSocket thsock;
	
	
	public SendingThread(ConcurrentList<ResultsComputation> res, Semaphore sem, GuiClient g){
		results = res;
		availableResultsToSendSemaphore = sem;
		gui = g;
		thsock = g.getThreadSocket();
		finished = true;
	}
	
	/**
	 * Stop this thread.
	 */
	public void stopThread(){
		finished = true;	
		availableResultsToSendSemaphore.release();
	}
	
	public void setAvailableResultsSemaphore(Semaphore sem){
		availableResultsToSendSemaphore = sem;		
	}
	
	public void run(){
		finished = false;
		
		while (!finished){
			try {
				availableResultsToSendSemaphore.acquire();
			} catch (InterruptedException e) {
				System.out.println("Trouble with availableResultsToSendSemaphore semaphore.");
				e.printStackTrace();
			}
			if (!finished){
				//System.out.println("results");
				thsock.send(results.getNextAndRemove());
				//gui.addLog("Results sent");
			}
		}
	}
	
}
