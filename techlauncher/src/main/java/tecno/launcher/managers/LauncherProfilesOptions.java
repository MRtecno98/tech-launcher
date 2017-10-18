package tecno.launcher.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.json.JSONObject;

public class LauncherProfilesOptions {
	int minRAM;
	int maxRAM;
	
	/**
	 * @param minRAM
	 * @param maxRAM
	 */
	public LauncherProfilesOptions(int minRAM, int maxRAM) {
		super();
		this.minRAM = minRAM;
		this.maxRAM = maxRAM;
	}

	/**
	 * @return the minRAM
	 */
	public int getMinRAM() {
		return minRAM;
	}

	/**
	 * @return the maxRAM
	 */
	public int getMaxRAM() {
		return maxRAM;
	}

	/**
	 * @param minRAM the minRAM to set
	 */
	public void setMinRAM(int minRAM) {
		this.minRAM = minRAM;
	}

	/**
	 * @param maxRAM the maxRAM to set
	 */
	public void setMaxRAM(int maxRAM) {
		this.maxRAM = maxRAM;
	}
	
	@Deprecated
	public void exportOptions(File f) throws IOException {
		BufferedReader reader = null;
		String text = "";
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(f) , "utf-8"));
			while(reader.ready()) {
				text+="\n" + reader.readLine();
			}
		}finally {
			reader.close();
		}
		
		System.out.println(text);
		
		JSONObject obj = new JSONObject(text);
		obj.getJSONObject("profiles").getJSONObject("profile").put("javaArgs", "-Xmx" + maxRAM + "M -XX:+UseConcMarkSweepGC -XX:+CMSIncrementalMode -XX:-UseAdaptiveSizePolicy -Xmn" + minRAM + "M");
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f) , "utf-8"));
			writer.write(obj.toString());
		}finally {
			writer.close();
		}
	}
	
	public void correctProfilesJSON(File f) {
		if((!(f.exists())) || f.isDirectory()) {
			System.out.println("Invalid path");
			return;
		}
		
		BufferedReader reader = null;
		String line;
		ArrayList<String> text = new ArrayList<>();
		try {
			reader = new BufferedReader(new FileReader(f));
			while((line = reader.readLine()) != null) {
				text.add(line);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		for(String s : text) {
			if(s.startsWith("  \"selectedProfile\": ")) {
				text.set(text.indexOf(s), "  \"selectedProfile\": \"profile\",");
			}
		}
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(f));
			for(String s : text) {
				writer.write(s + "\n");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
