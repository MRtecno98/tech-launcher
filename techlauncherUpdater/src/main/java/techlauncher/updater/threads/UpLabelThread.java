package techlauncher.updater.threads;

import techlauncher.updater.frame.UpdaterFrame;

public class UpLabelThread extends Thread {
	private UpdaterFrame frame;
	boolean running = false;
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public UpLabelThread(UpdaterFrame frame) {
		this.frame = frame;
		this.frame.setVisible(true);
	}
	
	public void run() {
		try { 
			while(running) {
				frame.lblUpdatingLauncher.setText("Updating Launcher");
				sleep(500);
				frame.lblUpdatingLauncher.setText("Updating Launcher.");
				sleep(500);
				frame.lblUpdatingLauncher.setText("Updating Launcher..");
				sleep(500);
				frame.lblUpdatingLauncher.setText("Updating Launcher...");
				sleep(500);
			}
			frame.lblUpdatingLauncher.setText("Download Completed!");
			sleep(1000);
			frame.setVisible(false);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
