package techlauncher.updater.threads;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;


public class UpThread extends Thread {
	UpLabelThread labelTh;
	String launcherPath;
	String link;
	
	public UpThread(UpLabelThread thread, String launcherPath , String link) {
		this.labelTh = thread;
		this.launcherPath = launcherPath;
		this.link = link;
	}
	
	public void run() {
		labelTh.setRunning(true);
		labelTh.start();
		try {
			File launcherFile = new File(launcherPath);
			if(launcherFile.exists()) {
				launcherFile.delete();
			}
			FileUtils.copyURLToFile(new URL(link), launcherFile, 20000, 20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		labelTh.setRunning(false);
		try {
			runJar(new File(launcherPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	public void runJar(File file) throws Exception {
		if(file.isDirectory()) {
			throw new Exception("The jar is a directory!");
		}
		try {
			ProcessBuilder pb = new ProcessBuilder(System.getProperty("java.home") + "\\bin\\java.exe", "-jar", file.getAbsolutePath());
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
