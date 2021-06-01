package betterBanking;

// IMPORT STATEMENTS
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class defines a new GUI for the software partition,
 * and implements methods to execute Client options.
 * @author Tristan Parry
 * @version 1.0
 */
public class ClientOptions_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel titleLabel = new JLabel("Options");
	private JTextPane detailsLabel = new JTextPane();
	private JScrollPane scrollPane;
	private JButton createAccountButton = new JButton("Create Account"), accessAccountButton = new JButton("<HTML>Access<br>Account</HTML>"),
			deleteAccountButton = new JButton("<HTML>Delete<br>Client</HTML>"), exitButton = new JButton("Exit");
	private Client c;
	
	/**
	 * This constructor creates a GUI for the ClientOptions method of the BetterBanking software.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 * @throws IOException Exception as a result of I/O failure
	 */
	public ClientOptions_GUI(Client clientArg) throws IOException {
		this.c = clientArg;
		
		// POPULATE JFRAME
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.getContentPane().setLayout(null);
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.setBounds(100, 100, 346, 453);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		this.titleLabel.setBounds(22, 25, 261, 46);
		this.frame.getContentPane().add(this.titleLabel);
		
		this.detailsLabel.setContentType("text/html");
		this.detailsLabel.setText(this.c.toString());
		this.detailsLabel.setBackground(new Color(178, 196, 207));
		this.detailsLabel.setBounds(0, 0, 447, 268);
		this.detailsLabel.setEditable(false);
		this.scrollPane = new JScrollPane(this.detailsLabel);
		this.scrollPane.setBounds(20, 69, 308, 124);
		this.scrollPane.setBorder(null);
		this.frame.getContentPane().add(this.scrollPane);
		
		
		this.createAccountButton.setForeground(Color.WHITE);
		this.createAccountButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.createAccountButton.setFocusPainted(false);
		this.createAccountButton.setBackground(Color.DARK_GRAY);
		this.createAccountButton.setBounds(22, 207, 283, 40);
		this.createAccountButton.addActionListener(this);
		if (this.c.updateAccounts("Chequing") && this.c.updateAccounts("Savings") && this.c.updateAccounts("Investment")) { this.createAccountButton.setVisible(false); }
		this.frame.getContentPane().add(this.createAccountButton);
		
		this.accessAccountButton.setBackground(Color.DARK_GRAY);
		this.accessAccountButton.setForeground(new Color(255, 255, 255));
		this.accessAccountButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.accessAccountButton.setBounds(22, 261, 135, 61);
		this.accessAccountButton.setFocusPainted(false);
		this.accessAccountButton.addActionListener(this);
		if (!this.c.updateAccounts("Chequing") && !this.c.updateAccounts("Savings") && !this.c.updateAccounts("Investment")) { this.accessAccountButton.setVisible(false); }
		this.frame.getContentPane().add(this.accessAccountButton);
		
		this.deleteAccountButton.setForeground(Color.WHITE);
		this.deleteAccountButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.deleteAccountButton.setBackground(Color.DARK_GRAY);
		this.deleteAccountButton.setBounds(170, 261, 135, 61);
		this.deleteAccountButton.setFocusPainted(false);
		this.deleteAccountButton.addActionListener(this);
		this.frame.getContentPane().add(this.deleteAccountButton);
		
		this.exitButton.setForeground(Color.WHITE);
		this.exitButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.exitButton.setBackground(new Color(255, 51, 51));
		this.exitButton.setBounds(22, 340, 283, 61);
		this.exitButton.setFocusPainted(false);
		this.exitButton.addActionListener(this);
		this.frame.getContentPane().add(this.exitButton);
	}
	
	
	
	/**
	 * This method requests user input for the manipulation of a BetterBanking
	 * Client, allowing the user to create or access a BetterBanking Account.
	 */
	public void actionPerformed(ActionEvent ae) {
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.createAccountButton) {
			this.frame.dispose();
			CreateAccount_GUI.createAccount(this.c);
		}
		if (ae.getSource() == this.accessAccountButton) {
			this.frame.dispose();
			AccessAccount_GUI.accessAccount(this.c);
		}
		if (ae.getSource() == this.deleteAccountButton) {
			this.frame.dispose();
			try { ManageClient_GUI.deleteClient(this.c); } catch (IOException e) {}
		}
		if (ae.getSource() == this.exitButton) {
			this.frame.dispose();
			Exit_GUI.showExitScreen("<HTML>Thank you for<br>choosing <b>BetterBanking.</b><br>Your session has<br>now been ended.</HTML>");
		}
	}
	
	
	
	/**
	 * This method runs the GUI for the ClientOptions_GUI class, by instantiating/running a ClientOptions_GUI window object.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 * @throws IOException Exception as a result of I/O failure
	 */
	public static void clientOptions(Client clientArg) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			ClientOptions_GUI window = new ClientOptions_GUI(clientArg);
			public void run() {
				window.frame.setVisible(true);
			}
		});
	}
}
