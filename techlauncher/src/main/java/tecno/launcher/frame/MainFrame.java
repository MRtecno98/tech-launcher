package tecno.launcher.frame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tecno.launcher.main.App;
import tecno.launcher.managers.AuthManager;
import tecno.launcher.packager.PackageManager;

/**
 * The MainFrame of the Application, is started by Class App and contains the main functions
 * @author MRtecno98
 * @version 1.0.0, 08/20/2017
 */

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	/** The main pane */
	private JPanel contentPane;
	
	/** Textfield for Username */
	private JTextField usrField;
	
	/** CensuredField for Password */
	private JPasswordField pswField;
	
	/** Boolean which indicates if the 'Options' Frame is opened */
	protected static boolean optOpened = false;
	
	/** The Main Class */
	private App inst;
	
	/** The package manager */
	private PackageManager manager;
	
	/**
	 * Construct the frame,
	 * instantiate fields 'inst' and 'manager',
	 * load options using App methods,
	 * check file folder structure using PackageManager,
	 * and instantiate the listeners
	 */
	@SuppressWarnings("static-access")
	public MainFrame() {
		inst = new App();
		try {
			manager = new PackageManager(new URL(inst.settings.packURL) , new File(inst.packFile));
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			manager.checkFolders();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		inst.loadOptions();
		
		System.out.println("Loaded options: minRAM: " + inst.options.get("minRAM") + " maxRAM: " + inst.options.get("maxRAM"));
		System.out.println("Loaded username: " + inst.options.get("username"));
		
		setTitle("TechLauncher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606 , 369);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel buttonsPane = new JPanel();
		buttonsPane.setBounds(0, 267, 590, 66);
		contentPane.add(buttonsPane);
		buttonsPane.setLayout(null);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(10, 5, 117, 23);
		buttonsPane.add(btnOptions);
		
		JButton btnForceUpdate = new JButton("Force Update");
		btnForceUpdate.setBounds(10, 32, 117, 23);
		buttonsPane.add(btnForceUpdate);
		
		usrField = new JTextField();
		usrField.setBounds(361, 14, 140, 20);
		buttonsPane.add(usrField);
		usrField.setColumns(10);
		if(inst.options.get("username") != null) {
			usrField.setText(inst.options.get("username"));
		}
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblUsername.setBounds(279, 12, 72, 21);
		buttonsPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblPassword.setBounds(279, 31, 72, 21);
		buttonsPane.add(lblPassword);
		
		pswField = new JPasswordField();
		pswField.setBounds(361, 35, 140, 20);
		buttonsPane.add(pswField);
		if(inst.options.get("password") != null) {
			pswField.setText(inst.options.get("password"));
		}
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(511, 11, 69, 44);
		buttonsPane.add(btnPlay);
		
		JButton btnDebug = new JButton("Debug");
		btnDebug.setBounds(168, 32, 63, 23);
		buttonsPane.add(btnDebug);
		btnDebug.setVisible(inst.debug);
		
		JEditorPane browser = new JEditorPane();
		browser.setBounds(0, 0, 590, 266);
		browser.setEditable(false);
		try {
			  browser.setPage("http://mcupdate.tumblr.com");
			}catch (IOException e) {
			  browser.setContentType("text/html");
			  browser.setText("<html>Could not load:<br>" + e.getMessage() + "</html>");
			}
		//JScrollPane scroll = new JScrollPane(browser);
		//scroll.setBounds(0, 267, 590, -268);
		contentPane.add(browser);
		
		btnForceUpdate.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse was clicked on 'Force Update' Button
			 * Set the property 'Force Update' at True, at first start of the application by default is True
			 * After the downloading of the pack is False
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void mouseClicked(MouseEvent event) {
				inst.options.put("forceupdate", "true");
				MsgFrame msg = new MsgFrame("    	Al prossimo avvio del gioco\n    	Il pacchetto sarà aggiornato","Force Update");
				msg.setVisible(true);
			}
		});
		
		btnOptions.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse was clicked on 'Options' Button
			 * Open 'Options' Frame for edit Options
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void mouseClicked(MouseEvent event) {
				if(optOpened == false) {
					OptionsFrame optf = new OptionsFrame();
					optf.setVisible(true);
					optOpened = true;
				}
			}
		});
		
		btnPlay.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse was clicked on 'Play' Button
			 * Get Username and Password, check they
			 * if are correct download the pack when option 'Force Update' is True
			 * and start the game
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void mouseClicked(MouseEvent event) {
				String username = usrField.getText();
				String password = new String(pswField.getPassword());
				
				inst.options.put("username", username);
				inst.options.put("password", password);
				
				AuthManager auth = new AuthManager(username , password);
				
				boolean autenticated = auth.autenticate();
				
				if(autenticated) {
					System.out.println(inst.options.get("forceupdate"));
					if(inst.options.get("forceupdate").equals("true")) {
						Thread t = manager.updatePack();
						while(t.isAlive()){}
						manager.extractPack();
						inst.options.put("forceupdate" , "false");
					}
					manager.launchPack();
				}else{
					String[] textErr = {"Username o password non validi"};
					try {
						ErrorFrame err = new ErrorFrame(textErr , "Autentication Error");
						err.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		btnDebug.addMouseListener(new MouseAdapter() {
			/**
			 * Invoked when the mouse was clicked on 'Debug' Button if is visible
			 * Debug Button for Tests
			 * @param event a MouseEvent represent the event listened
			 */
			@Override
			public void mouseClicked(MouseEvent event) {
				
			}
		});
		
		/**
		 * Invoked when closing window
		 * Save the options in a JSON file using Main Class Methods
		 * @param event a MouseEvent represent the event listened
		 */
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				inst.saveOptions();
			}
		});
	}
}
