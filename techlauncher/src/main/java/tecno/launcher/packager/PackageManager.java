package tecno.launcher.packager;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import tecno.launcher.frame.DownloadFrame;
import tecno.launcher.main.App;
import tecno.launcher.threads.DownThread;
import tecno.launcher.threads.FrameLabelThread;
import tecno.launcher.zip.UnzipFile;

/**
 * This class manage downloading and running of pack
 * This is a Thread because managing download graphical form it must be async from download thread
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class PackageManager extends Thread{
	/** URL Object of the pack */
	URL URL;
	/** URL String of the pack */
	String packURL;
	/** File pointing local pack file */
	File file;
	/** MainClass Instance */
	App inst;
	
	/**
	 * Initialize Variables from arguments and Instantiate inst variable
	 * @param URl URL Object of the URL
	 * @param file Local Pack File
	 * @throws MalformedURLException If URL is Malformed
	 */
	public PackageManager(App inst , URL URl , File file) throws MalformedURLException {
		this.packURL = URl.toString();
		this.URL = URl;
		this.file = file;
		this.inst = inst;
	}
	
	/**
	 * Get the String URL
	 * @return packURL The String URL
	 */
	public String getURL() {
		return packURL;
	}
	
	/**
	 * Set the URL String
	 * @param uRL The URL String
	 * @throws MalformedURLException if the URL is Malformed
	 */
	public void setURL(String uRL) throws MalformedURLException {
		packURL = uRL;
		URL = new URL(packURL);
	}
	
	/**
	 * Start Download Thread
	 * @return t The DownThread Class represent Download Thread
	 * @throws MalformedURLException 
	 */
	public Thread updatePack() throws MalformedURLException {
		System.out.println("Updating pack...");
		DownThread t = new DownThread(inst , file , URL , new FrameLabelThread(new DownloadFrame(inst)));
		t.start();
		return t;
    }
	
	/**
	 * Run thread
	 */
	public void run() {
		try {
			updatePack();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Check the folder Structure in APPDATA
	 * @throws IOException If creation failed
	 */
	public void checkFolders() throws IOException {
		String folder = App.datafolder;
		File datas = new File(folder);
		if(!(datas.exists())) {
			boolean success = datas.mkdir();
			if(!success) {
				throw new IOException("Datas directory creation Failed");
			}
		}
		
		datas = new File(App.datafolder + "\\pack");
		
		if(!(datas.exists())) {
			boolean success = datas.mkdir();
			if(!success) {
				throw new IOException("Pack directory creation Failed");
			}
		}
		
		datas = new File(App.datafolder + "\\temp");
		
		if(!(datas.exists())) {
			boolean success = datas.mkdir();
			if(!success) {
				throw new IOException("Temp directory creation Failed");
			}
		}
	}
	
	/**
	 * Extract the pack zip
	 */
	public void extractPack() {
		System.out.println("Deleting old files...");
		File directory = new File(App.minecraftFolder);
		File[] files = directory.listFiles();
		for(File file : files) {
			file.delete();
		}
		System.out.println("Extracting pack...");
		UnzipFile unzip = new UnzipFile(App.packFile , App.minecraftFolder);
		try {
			unzip.unzip();
		} catch (IOException e) {
			e.printStackTrace();
		}
		File zip = new File(App.packFile);
		zip.delete();
	}
	
	/**
	 * Launch Start.bat From pack
	 */
	public void launchPack() {
		System.out.println("Launching pack...");
		String command = /*"start " + */inst.minecraftFolder + "\\start.bat";
		try {
			Process p = Runtime.getRuntime().exec(command);
			inst.frame.setVisible(false);
			p.waitFor();
			inst.frame.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}