package techlauncher.updater;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import techlauncher.updater.frame.UpdaterFrame;
import techlauncher.updater.settings.Settings;
import techlauncher.updater.threads.UpLabelThread;
import techlauncher.updater.threads.UpThread;

public class Updater {
	public static String imgurl;
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		if(args.length < 2) {
			System.out.println("Illegal Args");
			Thread.currentThread().sleep(1000);
			return;
		}
		
		Settings settings = new Settings();
		
		if(settings.useNimbus) {
			try {
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
			} catch (Exception e) {
				// If Nimbus is not available, you can set the GUI to another look and feel.
				System.out.println("Nimbus not Avaiable!");
			}
		}
		
		imgurl = settings.updateImg;
		Thread.currentThread().sleep(1000);
		UpThread thread = new UpThread(new UpLabelThread(new UpdaterFrame()) , args[0] , args[1]);
		thread.start();
	}
}
