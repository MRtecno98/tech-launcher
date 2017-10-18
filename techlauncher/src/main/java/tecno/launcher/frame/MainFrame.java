package tecno.launcher.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tecno.launcher.main.App;
import tecno.launcher.managers.AuthManager;
import tecno.launcher.managers.LauncherProfilesOptions;
import tecno.launcher.objects.misc.JBounds;
import tecno.launcher.objects.panels.BackgroundPanel;
import tecno.launcher.objects.panels.ImagePanel;
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
	private static App inst;
	
	/** The package manager */
	private PackageManager manager;
	
	private LauncherProfilesOptions profmanager;
	
	/**
	 * Construct the frame,
	 * instantiate fields 'inst' and 'manager',
	 * load options using App methods,
	 * check file folder structure using PackageManager,
	 * and instantiate the listeners
	 * @throws IOException 
	 */
	@SuppressWarnings("static-access")
	public MainFrame(App inst) throws IOException {
		this.setInst(inst);
		try {
			manager = new PackageManager(inst , new URL(inst.settings.packURL) , new File(inst.packFile));
		}catch(MalformedURLException e) {
			e.printStackTrace();
		}
		
		try {
			manager.checkFolders();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		
		inst.loadOptions();
		
		profmanager = new LauncherProfilesOptions(Integer.parseInt(inst.options.get("minRAM")) , Integer.parseInt(inst.options.get("maxRAM")));
		
		System.out.println("Loaded options: minRAM: " + inst.options.get("minRAM") + " maxRAM: " + inst.options.get("maxRAM"));
		System.out.println("Loaded username: " + inst.options.get("username"));
		
		setTitle("TechLauncher");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1019 , 561);
		setIconImage(ImageIO.read(new URL(inst.settings.iconImg)));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BackgroundPanel buttonsPane = new BackgroundPanel(new URL(inst.settings.bgImg));
		buttonsPane.setBounds(0, 433, 1003, 89);
		contentPane.add(buttonsPane);
		buttonsPane.setLayout(null);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(903, 11, 90, 27);
		buttonsPane.add(btnOptions);
		
		usrField = new JTextField();
		usrField.setBounds(712, 15, 181, 24);
		buttonsPane.add(usrField);
		usrField.setColumns(10);
		if(inst.options.get("username") != null) {
			usrField.setText(inst.options.get("username"));
		}
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblUsername.setBounds(630, 15, 72, 21);
		lblUsername.setForeground(Color.WHITE);
		buttonsPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		lblPassword.setBounds(630, 39, 72, 21);
		lblPassword.setForeground(Color.WHITE);
		buttonsPane.add(lblPassword);
		
		pswField = new JPasswordField();
		pswField.setBounds(712, 39, 181, 24);
		buttonsPane.add(pswField);
		if(inst.options.get("password") != null) {
			pswField.setText(inst.options.get("password"));
		}
		
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(903, 38, 90, 27);
		buttonsPane.add(btnPlay);
		
		JButton btnDebug = new JButton("Debug");
		btnDebug.setBounds(485, 38, 63, 23);
		buttonsPane.add(btnDebug);
		
		ImagePanel mclogo;
		if(inst.settings.useOldLogo) mclogo = new ImagePanel(new URL(inst.settings.oldLogoImg) , new JBounds(0, -11, 407, 88)); else mclogo = new ImagePanel(new URL(inst.settings.logoImg) , new JBounds(0, -11, 433, 93));
		if(inst.settings.useOldLogo) mclogo.setBounds(20, 20, 407, 58); else mclogo.setBounds(20, 15, 433, 63);
		mclogo.setOpaque(false);
		buttonsPane.add(mclogo);
		
		btnDebug.setVisible(inst.debug);
		
		JEditorPane browser = new JEditorPane();
		browser.setBounds(-7, 15, 1025, 447);
		browser.setEditable(false);
		try {
			  browser.setPage(App.settings.newsURL);
			}catch (IOException e) {
			  browser.setContentType("text/html");
			  browser.setText("<html>Could not load:<br>" + e.getMessage() + "</html>");
			}
		
		//JScrollPane scroll = new JScrollPane(browser);
		//scroll.setBounds(0, 267, 590, -268);
		contentPane.add(browser);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu();
		menu.setText("Test");
		JMenuItem item = new JMenuItem();
		item.setText("Ciao");
		menu.add(item);
		menuBar.add(menu);
		menuBar.setBounds(0, 0, 1003, 28);
		if(inst.settings.enableMenu) contentPane.add(menuBar); else browser.setBounds(-7, 0, 1025, 445);
		
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
						Thread t = null;
						try {
							t = manager.updatePack();
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						while(t.isAlive()){}
						manager.extractPack();
						inst.options.put("forceupdate" , "false");
					}
					
					profmanager.correctProfilesJSON(new File(inst.datafolder + File.separator + "pack" + File.separator + ".minecraft" + File.separator + "launcher_profiles.json"));
					
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
				LauncherProfilesOptions test = new LauncherProfilesOptions(10,10);
				test.correctProfilesJSON(new File("C:\\Users\\Giorgio\\Desktop\\launcher_profiles.json"));
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

	/**
	 * @return the inst
	 */
	public static App getInst() {
		return inst;
	}

	/**
	 * @param inst the inst to set
	 */
	public void setInst(App inst) {
		this.inst = inst;
	}
}
