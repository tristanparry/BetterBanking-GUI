package betterBankingGUImaven;

//IMPORT STATEMENTS
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.bson.Document;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class defines a new GUI for the software partition,
 * and implements methods to login a Client object.
 * @author Tristan Parry
 * @version 1.0
 */
public class LoginClient_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel loginClientLabel = new JLabel("Login Client"), accountLoginLabel = new JLabel("<HTML>Invalid username or password. Try again.</HTML>"),
			usernameLabel = new JLabel("Username"), passwordLabel = new JLabel("Password");
	private JTextField usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginButton = new JButton("Login");
	private int loginAttempts = 0;
	
	/**
	 * This constructor creates a GUI for the LoginClient method of the BetterBanking software.
	 */
	public LoginClient_GUI() {
		// POPULATE JFRAME
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.getContentPane().setLayout(null);
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.setBounds(100, 100, 340, 318);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.loginClientLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		this.loginClientLabel.setBounds(19, 21, 261, 46);
		this.frame.getContentPane().add(this.loginClientLabel);
		this.usernameLabel.setForeground(new Color(0, 0, 0));
		this.usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.usernameLabel.setBounds(19, 96, 79, 14);
		this.frame.getContentPane().add(this.usernameLabel);
		this.passwordLabel.setForeground(new Color(0, 0, 0));
		this.passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.passwordLabel.setBounds(19, 144, 79, 14);
		this.frame.getContentPane().add(this.passwordLabel);
		
		this.usernameField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.usernameField.setColumns(10);
		this.usernameField.setBackground(new Color(204, 255, 255));
		this.usernameField.setBounds(101, 88, 213, 33);
		this.frame.getContentPane().add(this.usernameField);
		this.passwordField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.passwordField.setColumns(10);
		this.passwordField.setBackground(new Color(204, 255, 255));
		this.passwordField.setBounds(101, 136, 213, 33);
		this.frame.getContentPane().add(this.passwordField);
		
		this.accountLoginLabel.setForeground(new Color(255, 51, 51));
		this.accountLoginLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		this.accountLoginLabel.setBounds(19, 215, 261, 51);
		this.accountLoginLabel.setVisible(false);
		this.frame.getContentPane().add(this.accountLoginLabel);
		
		this.loginButton.setForeground(new Color(255, 255, 255));
		this.loginButton.setBackground(Color.DARK_GRAY);
		this.loginButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.loginButton.setBounds(9, 181, 89, 23);
		this.loginButton.setFocusPainted(false);
		this.loginButton.addActionListener(this);
		this.frame.getContentPane().add(this.loginButton);
	}
	
	
	
	/**
	 * This method requests user input for username and password, accesses a BetterBanking
	 * Client file, and creates a Client object to manipulate in the program execution.
	 */
	public void actionPerformed(ActionEvent ae) {
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.loginButton) {
			@SuppressWarnings("deprecation")
			String tempUsername = this.usernameField.getText(), tempPassword = this.passwordField.getText();
			boolean usernameCheck = false, passwordCheck = false;
			this.loginAttempts++;
			
			if (tempUsername.isBlank() || tempPassword.isBlank()) {
				accountLoginLabel.setVisible(true);
			} else {
				try {
					if (Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", tempUsername)) != null) {
						usernameCheck = true;
					}
				} catch (NullPointerException e) {}
				try {
					if (Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", tempUsername)).first().getString("Password").equals(tempPassword)) {
						passwordCheck = true;
					}
				} catch (NullPointerException e) {}
				
				if (usernameCheck && passwordCheck && this.loginAttempts <= 3) {
					// CREATE A CLIENT OBJECT
					Client newClient = null;
					newClient = new Client(Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", tempUsername)).first());
					
					// TRANSITION + CALL MAIN METHOD AGAIN
					try { Thread.sleep(500); } catch (InterruptedException e1) {}
					this.frame.dispose();
					ClientOptions_GUI.clientOptions(newClient);
				} else if ((!usernameCheck || !passwordCheck) && this.loginAttempts >= 3) {
					this.frame.dispose();
					Exit_GUI.showExitScreen("<HTML>Too many incorrect<br>login attempts. Your<br>session has now<br>been ended.</HTML>");
				}
				
				// DISPLAY MESSAGE
				this.accountLoginLabel.setVisible(true);
			}
		}
	}
	
	
	
	/**
	 * This method runs the GUI for the LoginClient_GUI class, by instantiating/running a LoginClient_GUI window object.
	 */
	public static void loginClient() {
		EventQueue.invokeLater(new Runnable() {
			LoginClient_GUI window = new LoginClient_GUI();
			public void run() {
				window.frame.setVisible(true);
			}
		});
	}
}