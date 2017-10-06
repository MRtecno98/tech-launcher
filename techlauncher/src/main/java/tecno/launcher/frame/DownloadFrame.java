package tecno.launcher.frame;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tecno.launcher.objects.panels.ImagePanel;

/**
 * Graphical Downloader JFrame
 * This class is controlled by FrameLabelThread Class
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */

@SuppressWarnings("serial")
public class DownloadFrame extends JFrame {
	
	/**JFrame main pane*/
	private JPanel contentPane;
	/** JLabel for report to the user the state of download */
	public final JLabel lblDownloading = new JLabel("Downloading...");
	
	
	/**
	 * Construct the frame, initialize the parts of JFrame and instantiate the listeners
	 */
	public DownloadFrame() {
		setTitle("Downloader");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 365, 155);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblDownloading.setFont(new Font("Segoe UI Black", Font.BOLD, 26));
		lblDownloading.setBounds(128, 43, 209, 32);
		contentPane.add(lblDownloading);
		
		ImagePanel panel = new ImagePanel(DownloadFrame.class.getClassLoader().getResource("tecno/launcher/resources/downloaderLogo.png").getFile(), 108, 97);
		//ImagePanel panel = new ImagePanel("C:\\Users\\Giorgio\\Desktop\\downloaderLogo.png", 108, 97);
		panel.setBounds(10, 10, 108, 97);
		contentPane.add(panel);
	}
}
