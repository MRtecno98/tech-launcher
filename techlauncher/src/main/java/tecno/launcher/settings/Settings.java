package tecno.launcher.settings;

/*
 * This is the Settings Class, here there are ALL Settings
 * of the launcher.
 */

/**
 * This class contains various settings variables used in the Application
 * @author MRtecno98
 *
 */
public class Settings {
	
	//Normal URLS Section, in this section are located all variables which represent a normal FILE URL
	/** URL of the pack */
	public static String packURL = "http://www.database98.altervista.org/Techraft/update.zip"; //The update.zip file URL
	/** URL of the text file contains newest launcher version */
	public static String versionURL = "http://www.database98.altervista.org/Techraft/version.txt"; //The version.txt file URL
	/** URL of the newest jar of the launcher*/
	public static String launcherURL = "http://www.database98.altervista.org/Techraft/launcher.jar"; //The launcher.jar file URL
	/** URL of the updater jar */
	public static String updaterURL = "http://www.database98.altervista.org/Techraft/updater.jar"; //The updater.jar file URL
	/** URL of news */
	public static String newsURL = "http://mcupdate.tumblr.com"; //The NEWS URL
	//---------------------------------------END------------------------------------------------------------------------------------
	
	//Miscellaneous, various settings for launcher
	/** Name of Data Folder in APPDATA(Without '.') */
	public static String folderName = "techpack";
	/** Local Version of the launcher */
	public static String version = "1.4.0";
	//-------------------END----------------------------
	
	//WIP Section, here there are the Work in Progress functionality
	/** Enable Menu (WIP)*/
	public static boolean enableMenu = false; //WORK IN PROGRESS, DON'T ENABLE!
	//--------------------------------------------------------------------------
	
	//Resources Section, here are located URLS that represent an Image File used by Launcher
	public static String downloadImg = "http://www.database98.altervista.org/Techraft/resources/dwn.png";  //The Downloader Frame Image
	public static String errorImg = "http://www.database98.altervista.org/Techraft/resources/err.png";     //The Error Frame Image
	public static String logoImg = "http://www.database98.altervista.org/Techraft/resources/l.png";        //Minecraft Logo(New Version)
	public static String oldLogoImg = "http://www.database98.altervista.org/Techraft/resources/oldl.png";  //Minecraft Logo(Old Version)
	public static String bgImg = "http://www.database98.altervista.org/Techraft/resources/bg.png";         //Launcher Background(The Image is looped)
	public static String iconImg = "http://www.database98.altervista.org/Techraft/resources/icon.png";
	public static boolean useOldLogo = false;                                                               //Use Old or New Version of the Minecraft Logo
	//------------------------------------------------------------------END----------------------------------------------------------------------------------
}
