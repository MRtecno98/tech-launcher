package tecno.launcher.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import tecno.launcher.frame.MainFrame;
import tecno.launcher.main.App;
import tecno.launcher.settings.Settings;

/**
 * This class manage checking launcher version and update it if not
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class VersionManager {
	
	/**
	 * Control if version is updated
	 * @return updated If version is updated is True else False
	 */
	public static boolean versionIsUpdated() {
		String webVersion = getNewVersion().toString();
		String localVersion = App.settings.version.toString();
		
		return getNewVersion().equals(App.settings.version);
	}
	
	/**
	 * Get the updated version from URL in Settings class
	 * @return version The web updated version or NULL if error
	 */
	public static String getNewVersion() {
    	URL url;
		try {
			url = new URL(Settings.versionURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			BufferedReader read = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String text = read.readLine();
			return text;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }
	
	/**
	 * Delete the updater Jar if is present
	 */
	public void deleteUpdaterIfPresent() {
		File updater = new File(App.datafolder + "\\updater.jar");
		if(updater.exists()) {
			updater.delete();
		}
	}
	
	/**
	 * Update Launcher if version is outdated using Updater
	 */
	public void updateVersionIfOutdated() {
		if(!(versionIsUpdated())) {
			
			String launcherPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath().replaceFirst("/", "").replace("/", "\\");
			String launcherLink = App.settings.launcherURL;
			String updaterLink = App.settings.updaterURL;
			
			File updater = new File(App.datafolder + "\\updater.jar");
			if(updater.exists()) {
				updater.delete();
			}
			
			try {
				FileUtils.copyURLToFile(new URL(updaterLink), updater, 20000, 20000);
				runUpdaterJar(updater , launcherPath, launcherLink);
				System.exit(0);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Run the updater Jar after downloading
	 * @param updater The updater File object
	 * @param launcherPath The path of this runnable jar
	 * @param launcherLink The URL of new Launcher
	 * @throws Exception If the Jar is a directory
	 */
	public void runUpdaterJar(File updater , String launcherPath, String launcherLink) throws Exception {
		if(updater.isDirectory()) {
			throw new Exception("The jar is a directory!");
		}
		try {
			ProcessBuilder pb = new ProcessBuilder(System.getProperty("java.home") + "\\bin\\java.exe", "-jar", updater.getAbsolutePath(), launcherPath, launcherLink);
			Process p = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
