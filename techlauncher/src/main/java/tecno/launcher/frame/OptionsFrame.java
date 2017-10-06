package tecno.launcher.frame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import tecno.launcher.main.App;

/**
 * This class is the Options Frame used by user for edit options
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */

@SuppressWarnings("serial")
public class OptionsFrame extends JFrame {
	
	/** The main Pane */
	private JPanel contentPane;
	
	/** Temp minRAM option */
	int minRAMv;
	/** Temp maxRAM option */
	int maxRAMv;
	
	/**
	 * Construct the frame and add the listeners
	 */
	public OptionsFrame() {
		setResizable(false);
		setTitle("Options");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 175, 165);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMinRam = new JLabel("Min RAM");
		lblMinRam.setFont(new Font("Arial", Font.PLAIN, 17));
		lblMinRam.setBounds(10, 18, 77, 27);
		contentPane.add(lblMinRam);
		
		JLabel lblMaxRam = new JLabel("Max RAM");
		lblMaxRam.setFont(new Font("Arial", Font.PLAIN, 17));
		lblMaxRam.setBounds(10, 61, 77, 27);
		contentPane.add(lblMaxRam);
		
		final JSpinner minRAM = new JSpinner();
		minRAM.setBounds(94, 21, 60, 20);
		contentPane.add(minRAM);
		
		final JSpinner maxRAM = new JSpinner();
		maxRAM.setBounds(94, 64, 60, 20);
		contentPane.add(maxRAM);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(39, 99, 89, 23);
		contentPane.add(btnSave);
		
		btnSave.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse was clicked on 'Save' Button
			 * Save the options in MainClass HashMap
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				minRAMv = (Integer) minRAM.getValue();
				maxRAMv = (Integer) maxRAM.getValue();
				App.options.put("minRAM", Integer.toString(minRAMv));
				App.options.put("maxRAM", Integer.toString(maxRAMv));
				MainFrame.optOpened = false;
				setVisible(false);
			}
		});
		
		addWindowListener(new WindowAdapter() {
			/**
			 * Invoked when the window is closing
			 * Set at false control Variable of MainFrame
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				MainFrame.optOpened = false;
			}
			
			/**
			 * Invoked when the window Activated
			 * Set box's text at saved options
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void windowActivated(WindowEvent e) {
				minRAM.setValue(Integer.parseInt(App.options.get("minRAM")));
				maxRAM.setValue(Integer.parseInt(App.options.get("maxRAM")));
			}
		});
	}
}
