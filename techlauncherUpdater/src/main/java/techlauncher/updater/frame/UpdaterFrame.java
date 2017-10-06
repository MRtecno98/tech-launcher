package techlauncher.updater.frame;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import techlauncher.updater.objs.ImagePanel;

@SuppressWarnings("serial")
public class UpdaterFrame extends JFrame {

	private JPanel contentPane;
	public JLabel lblUpdatingLauncher = new JLabel("Updating Launcher...");
	
	public UpdaterFrame() {
		setTitle("Updater");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 175);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel panel = new ImagePanel(UpdaterFrame.class.getClassLoader().getResource("techlauncher/updater/resources/updater.png").getFile(), 100,100);
		panel.setBounds(10, 15, 96, 103);
		getContentPane().add(panel);
		
		lblUpdatingLauncher.setFont(new Font("Segoe UI Black", Font.PLAIN, 25));
		lblUpdatingLauncher.setBounds(116, 43, 282, 39);
		contentPane.add(lblUpdatingLauncher);
		
		try {
			this.setIconImage(ImageIO.read(new File(UpdaterFrame.class.getClassLoader().getResource("techlauncher/updater/resources/updater.png").getFile())));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
