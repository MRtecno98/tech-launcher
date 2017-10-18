package tecno.launcher.threads;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import tecno.launcher.frame.DownloadFrame;
import tecno.launcher.main.App;

/**
 * This class represent the main Download Thread
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class DownThread extends Thread {
	/** Graphical Download Frame */
	public FrameLabelThread lblT;
	/** URL of the file to download */
	public URL url;
	/** File Represent Destination */
	public File destination;
	public App inst;
	
	/**
	 * Create the Thread using File and URL
	 * @param f File object represent destination
	 * @param u URL of the file to download
	 */
	public DownThread(App inst , File f , URL u , FrameLabelThread lblT) {
		this.destination = f;
		this.url = u;
		this.inst = inst;
		this.lblT = lblT;
	}
	
	/**
	 * Start the Thread
	 * Create and run a FrameLabelThread for animate the label in Download Form
	 * Start Downloading
	 */
	public void run() {
		lblT.setRunning(true);
		lblT.start();
		//lblT.frame.setVisible(true);
		try {
	        FileUtils.copyURLToFile(url, destination, 20000, 20000);
	    }catch (IOException ex){
	        ex.printStackTrace();
	    }
		lblT.setRunning(false);
	}
}
