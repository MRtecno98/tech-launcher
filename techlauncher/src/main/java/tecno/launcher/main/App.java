/**
 * This is the main class of the launcher
 * @author MRtecno98 <mr.tecno98@gmail.com>
 * @version 1.0.0, 08/20/2017
 */


package tecno.launcher.main;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import tecno.launcher.frame.MainFrame;
import tecno.launcher.managers.VersionManager;
import tecno.launcher.settings.Settings;

public class App 
{
	
	/** If true show 'Debug' button */
	public static boolean debug = false;
	/** Settings Class instance */
	public static Settings settings = new Settings();
	/** The APPDATA folder */
	public static String appdata = System.getenv("APPDATA");
	/** The datas folder(derived by APPDATA) */
	public static String datafolder = appdata + "\\." + Settings.folderName;
	/** The pack file path */
	public static String packFile = datafolder + "\\temp\\pack.zip";
	/** The minecraft datafolder */
	public static String minecraftFolder = datafolder + "\\pack";
	/** The options HashMap */
	public static HashMap<String,String> options = new HashMap<String,String>();
	/** Mojang Autentication Server */
	public static String autentication = "https://authserver.mojang.com/authenticate";
	
	public static File launcherOptions = new File(datafolder + "\\pack\\.minecraft\\launcher_profiles.json");
	/** The MainFrame */
	public static MainFrame frame;
	
	/**
	 * Start the application
	 * @param args Command-Line Application args(Not used in this application)
	 */
	public static void main(String[] args) {
		
		VersionManager version = new VersionManager();
		version.deleteUpdaterIfPresent();
		version.updateVersionIfOutdated();
		
        EventQueue.invokeLater(() -> {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
		});
    }
	
	/**
	 * Save options(An hashmap) in a file JSON
	 */
	public static void saveOptions() {
		JSONObject optionsj = new JSONObject(options);
		
		StringWriter out = new StringWriter();
		try {
			optionsj.writeJSONString(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		File file = new File(datafolder + "\\options.json");
		BufferedWriter writer = null;
		try {
			if(!(file.exists())) {
				file.createNewFile();
			}
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(out.toString());
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			try {
				writer.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
    }
	
	/**
	 * Load options(A JSON File) and convert it in the options HashMap
	 */
	public static void loadOptions() {
		File file = new File(datafolder + "\\options.json");
		BufferedReader reader = null;
		if(file.exists()) {
			try {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file) , "utf-8"));
				
				String json = reader.readLine();
				JSONParser parser = new JSONParser();
				JSONObject object = (JSONObject) parser.parse(json);
				options = (HashMap<String , String>)object;
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				try {
					reader.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
        }else{
        	options.clear();
        	options.put("minRAM", "512");
        	options.put("maxRAM", "1024");
        	options.put("version", settings.version);
        	options.put("forceupdate", "true");
        	options.put("username", null);
        	options.put("password", null);
		}
		
		
	}
}	