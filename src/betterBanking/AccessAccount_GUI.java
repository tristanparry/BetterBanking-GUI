package betterBanking;

// IMPORT STATEMENTS
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This class defines a new GUI for the software partition,
 * and implements methods to manipulate a Client object.
 * @author Tristan Parry
 * @version 1.0
 */
public class AccessAccount_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel titleLabel = new JLabel("Access Account");
	private JButton chequingAccountButton = new JButton("Chequing Account"), savingsAccountButton = new JButton("Savings Account"), investmentAccountButton = new JButton("Investment Account");
	private Client c;	
	
	/**
	 * This constructor creates a GUI for the AccessAccount method of the BetterBanking software.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 * @throws IOException Exception as a result of I/O failure
	 */
	public AccessAccount_GUI(Client clientArg) throws IOException {
		this.c = clientArg;
		
		// POPULATE JFRAME
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.getContentPane().setLayout(null);
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.setBounds(100, 100, 340, 264);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
		this.titleLabel.setBounds(19, 21, 261, 46);
		this.frame.getContentPane().add(this.titleLabel);
		
		this.chequingAccountButton.setForeground(new Color(255, 255, 255));
		this.chequingAccountButton.setBackground(Color.DARK_GRAY);
		this.chequingAccountButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.chequingAccountButton.setBounds(19, 78, 283, 32);
		this.chequingAccountButton.setFocusPainted(false);
		this.chequingAccountButton.setVisible(false);
		if (this.c.updateAccounts("Chequing")) { this.chequingAccountButton.setVisible(true); }
		this.chequingAccountButton.addActionListener(this);
		this.frame.getContentPane().add(this.chequingAccountButton);
		
		this.savingsAccountButton.setForeground(new Color(255, 255, 255));
		this.savingsAccountButton.setBackground(Color.DARK_GRAY);
		this.savingsAccountButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.savingsAccountButton.setBounds(19, 121, 283, 33);
		this.savingsAccountButton.setFocusPainted(false);
		this.savingsAccountButton.setVisible(false);
		if (this.c.updateAccounts("Savings")) { this.savingsAccountButton.setVisible(true); }
		this.savingsAccountButton.addActionListener(this);
		this.frame.getContentPane().add(this.savingsAccountButton);
		
		this.investmentAccountButton.setForeground(new Color(255, 255, 255));
		this.investmentAccountButton.setBackground(Color.DARK_GRAY);
		this.investmentAccountButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.investmentAccountButton.setBounds(19, 165, 283, 32);
		this.investmentAccountButton.setFocusPainted(false);
		this.investmentAccountButton.setVisible(false);
		if (this.c.updateAccounts("Investment")) { this.investmentAccountButton.setVisible(true); }
		this.investmentAccountButton.addActionListener(this);
		this.frame.getContentPane().add(this.investmentAccountButton);
	}
	
	
	
	/**
	 * This method accepts user input for an existing Account (assigned to a
	 * BetterBanking Client), and accesses this Account to perform internal operations.
	 */
	public void actionPerformed(ActionEvent ae) {
		this.frame.dispose();
		
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.chequingAccountButton) {
			Account clientChequingAccount = null;
			try {
				clientChequingAccount = new Chequing(Files.readAllLines(Paths.get("accountFiles/" + this.c.getUsername() + "_chequing.txt")));
				ManipulateAccount_GUI.manipulateAccount(this.c, clientChequingAccount);
			} catch (IOException e) {}
		}
		if (ae.getSource() == this.savingsAccountButton) {
			Account clientSavingsAccount = null;
			try {
				clientSavingsAccount = new Savings(Files.readAllLines(Paths.get("accountFiles/" + this.c.getUsername() + "_savings.txt")));
				ManipulateAccount_GUI.manipulateAccount(this.c, clientSavingsAccount);
			} catch (IOException e) {}
		}
		if (ae.getSource() == this.investmentAccountButton) {
			Account clientInvestmentAccount = null;
			try {
				clientInvestmentAccount = new Investment(Files.readAllLines(Paths.get("accountFiles/" + this.c.getUsername() + "_investment.txt")));
				ManipulateAccount_GUI.manipulateAccount(this.c, clientInvestmentAccount);
			} catch (IOException e) {}
		}
		
	}
	
	
	
	/**
	 * This method runs the GUI for the AccessAccount_GUI class, by instantiating/running a AccessAccount_GUI window object.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 */
	public static void accessAccount(Client clientArg) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				AccessAccount_GUI window = null;
				try { window = new AccessAccount_GUI(clientArg); } catch (IOException e) {}
				window.frame.setVisible(true);
			}
		});
	}
}