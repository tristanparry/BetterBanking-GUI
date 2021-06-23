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
import com.mongodb.client.model.Filters;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements various methods to manage a BetterBanking Client account.
 * @author Tristan Parry
 * @version 1.0
 */
public class ManageClient_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel createClientLabel = new JLabel("Create Client"), accountCreationLabel = new JLabel("<HTML>Invalid credentials/Error creating client. Try Again.</HTML>"),
			firstNameLabel = new JLabel("First Name"), lastNameLabel = new JLabel("Last Name"), usernameLabel = new JLabel("Username"), passwordLabel = new JLabel("Password");
	private JTextField firstNameField = new JTextField(), lastNameField = new JTextField(), usernameField = new JTextField();
	private JPasswordField passwordField = new JPasswordField();
	private JButton submitButton = new JButton("Create");
	private Document clientInfo = new Document();
	
	/**
	 * This constructor creates a GUI for the ManageClient method of the BetterBanking software.
	 */
	public ManageClient_GUI() {
		// POPULATE JFRAME
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.getContentPane().setLayout(null);
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.setBounds(100, 100, 340, 413);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.createClientLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		this.createClientLabel.setBounds(19, 21, 261, 46);
		this.frame.getContentPane().add(this.createClientLabel);
		
		this.firstNameLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.firstNameLabel.setForeground(new Color(0, 0, 0));
		this.firstNameLabel.setBounds(10, 95, 88, 14);
		this.frame.getContentPane().add(this.firstNameLabel);
		this.lastNameLabel.setForeground(new Color(0, 0, 0));
		this.lastNameLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.lastNameLabel.setBounds(10, 141, 88, 14);
		this.frame.getContentPane().add(this.lastNameLabel);
		this.usernameLabel.setForeground(new Color(0, 0, 0));
		this.usernameLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.usernameLabel.setBounds(10, 188, 88, 14);
		this.frame.getContentPane().add(this.usernameLabel);
		this.passwordLabel.setForeground(new Color(0, 0, 0));
		this.passwordLabel.setFont(new Font("Consolas", Font.PLAIN, 15));
		this.passwordLabel.setBounds(10, 236, 88, 14);
		this.frame.getContentPane().add(this.passwordLabel);
		
		this.firstNameField.setBackground(new Color(204, 255, 255));
		this.firstNameField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.firstNameField.setBounds(108, 86, 206, 33);
		this.frame.getContentPane().add(this.firstNameField);
		this.firstNameField.setColumns(10);
		this.lastNameField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.lastNameField.setColumns(10);
		this.lastNameField.setBackground(new Color(204, 255, 255));
		this.lastNameField.setBounds(108, 133, 206, 33);
		this.frame.getContentPane().add(this.lastNameField);
		this.usernameField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.usernameField.setColumns(10);
		this.usernameField.setBackground(new Color(204, 255, 255));
		this.usernameField.setBounds(108, 180, 206, 33);
		this.frame.getContentPane().add(this.usernameField);
		this.passwordField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.passwordField.setColumns(10);
		this.passwordField.setBackground(new Color(204, 255, 255));
		this.passwordField.setBounds(108, 228, 206, 33);
		this.frame.getContentPane().add(this.passwordField);
		
		this.submitButton.setForeground(new Color(255, 255, 255));
		this.submitButton.setBackground(Color.DARK_GRAY);
		this.submitButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.submitButton.setBounds(10, 274, 89, 23);
		this.submitButton.setFocusPainted(false);
		this.submitButton.addActionListener(this);
		this.frame.getContentPane().add(this.submitButton);
		
		this.accountCreationLabel.setForeground(new Color(255, 51, 51));
		this.accountCreationLabel.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 14));
		this.accountCreationLabel.setBounds(19, 314, 228, 34);
		this.accountCreationLabel.setVisible(false);
		this.frame.getContentPane().add(this.accountCreationLabel);
	}
	
	
	
	/**
	 * This method accepts user input for first/last name, username,
	 * and password, and creates a BetterBanking Client account from these parameters.
	 */
	public void actionPerformed(ActionEvent ae) {
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.submitButton) {
			// VARIABLE INITIALIZATIONS
			@SuppressWarnings("deprecation")
			String tempFirstName = this.firstNameField.getText(), tempLastName = this.lastNameField.getText(), tempUsername = this.usernameField.getText(), tempPassword = this.passwordField.getText();
			boolean firstNameCheck = true, lastNameCheck = true, usernameCheck = true, passwordCheck = true;
			
			this.accountCreationLabel.setText("<HTML>Invalid credentials/Error creating client. Try Again.</HTML>");
			if (tempFirstName.isBlank() || tempLastName.isBlank() || tempUsername.isBlank() || tempPassword.isBlank()) { this.accountCreationLabel.setVisible(true); }
			else {
				if (Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", tempUsername)).first() != null) {
					this.accountCreationLabel.setText("<HTML>Username already exists.<br>Enter a different username.</HTML>");
					usernameCheck = false;
				}
				
				if (firstNameCheck && lastNameCheck && usernameCheck && passwordCheck) {
					// ADD TO CLIENTINFO ARRAYLIST
					this.clientInfo.append("FirstName", Character.toUpperCase(tempFirstName.charAt(0)) + tempFirstName.substring(1).toLowerCase());
					this.clientInfo.append("LastName", Character.toUpperCase(tempLastName.charAt(0)) + tempLastName.substring(1).toLowerCase());
					this.clientInfo.append("Username", tempUsername);
					this.clientInfo.append("Password", tempPassword);
					this.clientInfo.append("Chequing", false).append("Savings", false).append("Investment", false).append("ClientFunds", 0.00);
					
					// CREATE + ADD TO MONGODB
					Utilities.connectToMongoDB().getCollection("clientFiles").insertOne(this.clientInfo);
					
					// TRANSITION + CALL MAIN METHOD AGAIN
					try { Thread.sleep(500); } catch (InterruptedException e1) {}
					this.frame.dispose();
					Main_GUI.main(null);
				}
				
				// DISPLAY MESSAGE
				this.accountCreationLabel.setVisible(true);
			}
		}
	}
	
	
	
	/**
	 * This method runs the GUI for the ManageClient_GUI class, by instantiating/running a ManageClient_GUI window object.
	 */
	public static void createClient() {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ManageClient_GUI window = new ManageClient_GUI();
				window.frame.setVisible(true);
			}
		});
	}
	
	
	
	/**
	 * This method accesses/deletes a BetterBanking Client account, as well as its associated Account files.
	 * @param c Client object used to access/delete the corresponding BetterBanking Client account
	 */
	public static void deleteClient(Client c) {
		// DELETE THE CLIENT'S ACCOUNT FILES
		Utilities.connectToMongoDB().getCollection("accountFiles").deleteMany(Filters.eq("Username", c.getUsername()));
		
		// DELETE THE CLIENT FILE
		Utilities.connectToMongoDB().getCollection("clientFiles").deleteOne(Filters.eq("Username", c.getUsername()));
		
		// EXIT
		Exit_GUI.showExitScreen("<HTML>The client and all of<br>its associated accounts<br>have been <b>deleted.</b> Your<br>session has now<br>been ended.</HTML>");
	}
}