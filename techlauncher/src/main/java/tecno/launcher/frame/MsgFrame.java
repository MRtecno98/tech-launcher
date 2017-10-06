package tecno.launcher.frame;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is the JFrame used to report to user any message
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */
public class MsgFrame extends JFrame {
	
	/** The main Pane */
	private JPanel contentPane;
	
	/**
	 * Construct the frame using text and title params and add Listeners
	 * @param text The text of message
	 * @param title The title of Frame
	 */
	public MsgFrame(String text, String title) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 358, 156);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setBounds(124, 83, 89, 23);
		contentPane.add(btnOk);
		
		JTextPane txtpnTestmessage = new JTextPane();
		txtpnTestmessage.setEditable(false);
		txtpnTestmessage.setBackground(UIManager.getColor("Button.background"));
		txtpnTestmessage.setText(text);
		txtpnTestmessage.setBounds(10, 11, 322, 61);
		contentPane.add(txtpnTestmessage);
		
		/**
		 * Invoked when the mouse was clicked on 'OK' Button
		 * Close the Frame
		 * @param event a MouseEvent represent the event listened
		 */
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
	}
}
