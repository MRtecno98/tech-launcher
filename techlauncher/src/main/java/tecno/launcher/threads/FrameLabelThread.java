package tecno.launcher.threads;

import tecno.launcher.frame.DownloadFrame;

/**
 * This class represent the Thread who deals with animate Form Label
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class FrameLabelThread extends Thread {
	/** Graphical Download Frame */
	DownloadFrame frame;
	/** True if label animation is on other false */
	boolean running;
	
	/**
	 * Construct Graphical Frame
	 * @param f Form to use for animation
	 */
	public FrameLabelThread(DownloadFrame f) {
		this.frame = f;
	}
	
	/**
	 * Start the animation Thread
	 */
	public void run() {
		running = true;
		try {
			while(running) {
				frame.lblDownloading.setText("Downloading");
				sleep(1000);
				frame.lblDownloading.setText("Downloading.");
				sleep(1000);
				frame.lblDownloading.setText("Downloading..");
				sleep(1000);
				frame.lblDownloading.setText("Downloading...");
				sleep(1000);
			}
			frame.lblDownloading.setText("Download Completed!");
			sleep(3000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Get running
	 * @return running Running Field
	 */
	public boolean isRunning() {
		return running;
	}
	
	/**
	 * Set Running
	 * @param running Running Field
	 */
	public void setRunning(boolean running) {
		this.running = running;
	}
}
