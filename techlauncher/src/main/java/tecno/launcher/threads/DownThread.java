package tecno.launcher.threads;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import tecno.launcher.frame.DownloadFrame;

/**
 * This class represent the main Download Thread
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class DownThread extends Thread {
	/** Graphical Download Frame */
	public DownloadFrame downframe;
	/** URL of the file to download */
	public URL url;
	/** File Represent Destination */
	public File destination;
	
	/**
	 * Create the Thread using File and URL
	 * @param f File object represent destination
	 * @param u URL of the file to download
	 */
	public DownThread(File f , URL u) {
		this.downframe = new DownloadFrame();
		downframe.setVisible(true);
		this.destination = f;
		this.url = u;
	}
	
	/**
	 * Start the Thread
	 * Create and run a FrameLabelThread for animate the label in Download Form
	 * Start Downloading
	 */
	public void run() {
		FrameLabelThread lblT = new FrameLabelThread(downframe);
		lblT.start();
		try {
	        FileUtils.copyURLToFile(url, destination, 20000, 20000);
	    }catch (IOException ex){
	        ex.printStackTrace();
	    }
		lblT.setRunning(false);
		downframe.setVisible(false);
	}
}
