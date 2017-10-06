package techlauncher.updater;

import java.io.File;
import java.io.IOException;

import techlauncher.updater.frame.UpdaterFrame;
import techlauncher.updater.threads.UpLabelThread;
import techlauncher.updater.threads.UpThread;

public class Updater {
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws InterruptedException {
		if(args.length < 2) {
			System.out.println("Illegal Args");
			Thread.currentThread().sleep(1000);
			return;
		}
		Thread.currentThread().sleep(1000);
		UpThread thread = new UpThread(new UpLabelThread(new UpdaterFrame()) , args[0] , args[1]);
		thread.start();
	}
}
