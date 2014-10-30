package client;

import java.util.concurrent.Semaphore;

import network.PointsToCompute;
import network.ResultsComputation;
import Raytracer.PointOutOfImageException;
import Raytracer.Raytracer;

/**
 * Thread that computes the points.
 * @author guiguito
 */
public class ComputingThread extends Thread {	
	
	private ConcurrentList<PointsToCompute> jobs;
	private Semaphore availableJobsSemaphore;
	private ConcurrentList<ResultsComputation> results;
	private Semaphore availableResultsToSendSemaphore;
	private boolean finished;
	private Raytracer raytracer;
	private GuiClient gui;
	
	/**
	 * Constructor.
	 * @param job list of jobs to do.
	 * @param avJob semaphore telling if there is a job to compute.
	 * @param res list of results.
	 * @param avJobsToSend semaphore telling if there is a result to send.
	 * @param g link to the gui.
	 */
	public ComputingThread(ConcurrentList<PointsToCompute> job,Semaphore avJob,
							ConcurrentList<ResultsComputation> res, Semaphore avJobsToSend,
							GuiClient g){
		jobs = job;
		availableJobsSemaphore = avJob;
		results = res;
		availableResultsToSendSemaphore  = avJobsToSend;				
		gui = g;
		raytracer = gui.getRaytracer();
		finished = true;
		setPriority(Thread.MAX_PRIORITY);		
	}
	
	public void setAvailableJobsSemaphore(Semaphore sem){
		availableJobsSemaphore = sem;		
	}
	
	public void setAvailableResultsSemaphore(Semaphore sem){
		availableResultsToSendSemaphore = sem;		
	}
	
	/**
	 * Stop this thread.
	 */
	public void stopThread(){
		finished = true;	
		availableJobsSemaphore.release();
	}
	
	
	public void run(){
		finished = false;
		int[] x;
		int[] y;
		double[] red;
		double[] green;
		double[] blue;
		
		while (!finished){
			try {
				availableJobsSemaphore.acquire();//check if some jobs are arrived
			} catch (InterruptedException e) {
				System.out.println("Trouble with availableJobs semaphore.");
				e.printStackTrace();
			}
			if (!finished){
				//gui.addLog("Job received !");
				long computingTime = System.currentTimeMillis();
				//get last job
				PointsToCompute points = (PointsToCompute)jobs.getNextAndRemove();
				x = points.getXCoordinates();
				y = points.getYCoordinates();
				red = new double [x.length];
				green = new double [x.length];
				blue = new double [x.length];

				
				//calculate points
				for (int i = 0; i<x.length;i++){
					Materials.Color color = null;
					try {
						color = raytracer.compute(x[i],y[i]);
					} catch (PointOutOfImageException e) {
						gui.addLog("Error : Point out of image !");				
					}					
					if (color != null){
						red[i]=color.getRed();
						green[i]=color.getGreen();
						blue[i]=color.getBlue();		
					}else{
						red[i]=0;
						green[i]=0;
						blue[i]=0;				
					}
				}
				//gui.addLog(""+results.size());
				computingTime = System.currentTimeMillis()-computingTime;
				//put results in the right list
				results.add(new ResultsComputation(computingTime,null/*x*/,null/*y*/,red,green,blue));
				availableResultsToSendSemaphore.release();
				System.gc();
			}
			
		}
	}
	
}
