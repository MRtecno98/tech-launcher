package tecno.launcher.managers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.json.JSONObject;

public class OptionsManager {
	int minRAM;
	int maxRAM;
	
	/**
	 * @param minRAM
	 * @param maxRAM
	 */
	public OptionsManager(int minRAM, int maxRAM) {
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
}
