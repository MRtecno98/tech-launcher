package tecno.launcher.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * This class is used to unzip a file with a tree of directories
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class UnzipFile {
	
	/** Zipfile Path */
	private String zipPath;
	/** Extract Path */
	private String destPath;
	
	/**
	 * Construct Unzipper with params
	 * @param zipPath Zipfile Path
	 * @param destPath Extract Path
	 */
	public UnzipFile(String zipPath, String destPath) {
	   this.zipPath = zipPath;
       this.destPath = destPath;
	}
	
	/**
	 * Unzip the file
	 * @throws IOException if the file is inexistent
	 */
	public void unzip() throws IOException {
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipPath);
			File outDir = new File(destPath);
			for(ZipEntry e : Collections.list(zipFile.entries())) {
				File dest = new File(outDir, e.getName());
				if(e.isDirectory()) {
					dest.mkdirs();
				} else {
					dest.getParentFile().mkdirs();
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dest));
					BufferedInputStream in = new BufferedInputStream(zipFile.getInputStream(e));
					for(int b = in.read(); b != -1; b = in.read()) { out.write(b); }
					try {
	   	    	   out.flush();
					}finally {
						out.close();
						in.close();
					}
				}
			}
		}finally {
			zipFile.close();
		}
	   
	}
}
