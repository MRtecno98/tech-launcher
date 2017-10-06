package tecno.launcher.frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import tecno.launcher.objects.panels.ImagePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is the frame used to report at user any error.
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 *
 */

public class ErrorFrame extends JFrame {
	
	/** The main pane of JFrame */
	private JPanel contentPane;
	
	/**
	 * Construct frame using text and title and instantiate the listeners
	 * @param text The text of message shown in the frame
	 * @param title The title of the frame
	 * @throws Exception If text is longer than four lines
	 */
	public ErrorFrame(String[] text , String title) throws Exception {
		if(text.length > 4) {
			throw new Exception("Error Text is max 4 lines length");
		}
		setTitle("ERROR TITLE");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 388, 157);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ImagePanel errImg = new ImagePanel(DownloadFrame.class.getClassLoader().getResource("tecno/launcher/resources/error.png").getFile(), 70, 70);
		errImg.setBounds(10 , 10 , 70 , 70);
		contentPane.add(errImg);
		
		JTextPane ErrorText = new JTextPane();
		ErrorText.setBackground(UIManager.getColor("Button.background"));
		ErrorText.setText("ERROR TEXT-----------------------------------LINE 1\r\nERROR TEXT-----------------------------------LINE 2\r\nERROR TEXT-----------------------------------LINE 3\r\nERROR TEXT-----------------------------------LINE 4");
		ErrorText.setBounds(90, 10, 272, 70);
		ErrorText.setEditable(false);
		contentPane.add(ErrorText);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(141, 84, 89, 23);
		contentPane.add(btnOk);
		
		String fusedText = "";
		for(String line : text) {
			fusedText+=line + "\r\n";
		}
		
		setTitle(title);
		ErrorText.setText(fusedText);
		
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
	}
}
