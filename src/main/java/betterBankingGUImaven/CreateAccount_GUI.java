package betterBankingGUImaven;

// IMPORT STATEMENTS
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import org.bson.Document;

/**
 * This class defines a new GUI for the software partition,
 * and implements methods to create an Account object.
 * @author Tristan Parry
 * @version 1.0
 */
public class CreateAccount_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel titleLabel = new JLabel("Create Account");
	private JButton chequingAccountButton = new JButton("Chequing Account"), savingsAccountButton = new JButton("Savings Account"), investmentAccountButton = new JButton("Investment Account");
	Document accountInfo = new Document(), updateDoc;
	private Client c;
	
	/**
	 * This constructor creates a GUI for the CreateAccount method of the BetterBanking software.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 */
	public CreateAccount_GUI(Client clientArg) {
		this.c = clientArg;
		this.updateDoc = Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", this.c.getUsername())).first();
		
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
		this.chequingAccountButton.addActionListener(this);
		if (this.c.updateAccounts("Chequing")) { this.chequingAccountButton.setVisible(false); }
		this.frame.getContentPane().add(this.chequingAccountButton);
		
		this.savingsAccountButton.setForeground(new Color(255, 255, 255));
		this.savingsAccountButton.setBackground(Color.DARK_GRAY);
		this.savingsAccountButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.savingsAccountButton.setBounds(19, 121, 283, 33);
		this.savingsAccountButton.setFocusPainted(false);
		this.savingsAccountButton.addActionListener(this);
		if (this.c.updateAccounts("Savings")) { this.savingsAccountButton.setVisible(false); }
		this.frame.getContentPane().add(this.savingsAccountButton);
		
		this.investmentAccountButton.setForeground(new Color(255, 255, 255));
		this.investmentAccountButton.setBackground(Color.DARK_GRAY);
		this.investmentAccountButton.setFont(new Font("Consolas", Font.PLAIN, 16));
		this.investmentAccountButton.setBounds(19, 165, 283, 32);
		this.investmentAccountButton.setFocusPainted(false);
		this.investmentAccountButton.addActionListener(this);
		if (this.c.updateAccounts("Investment")) { this.investmentAccountButton.setVisible(false); }
		this.frame.getContentPane().add(this.investmentAccountButton);
	}
	
	
	
	/**
	 * This method requests user input for a valid Account type (that is not
	 * currently in use), and creates a BetterBanking Account from this parameter.
	 */
	public void actionPerformed(ActionEvent ae) {		
		this.frame.dispose();
		this.accountInfo.append("Username", this.c.getUsername());
		this.accountInfo.append("AccountBalance", 0.00);
		
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.chequingAccountButton) {
			this.accountInfo.append("AccountType", "Chequing");
			this.accountInfo.append("InterestRate", 0.03);
			this.accountInfo.append("WrittenCheques", new ArrayList<Object>());
			Utilities.writeToDatabase("clientFiles", updateDoc, "Chequing", true);
		}
		if (ae.getSource() == this.savingsAccountButton) {
			this.accountInfo.append("AccountType", "Savings");
			this.accountInfo.append("InterestRate", 0.05);
			Utilities.writeToDatabase("clientFiles", updateDoc, "Savings", true);
		}
		if (ae.getSource() == this.investmentAccountButton) {
			this.accountInfo.append("AccountType", "Investment");
			this.accountInfo.append("InterestRate", 0.10);
			this.accountInfo.append("PurchasedStocks", new ArrayList<Object>());
			Utilities.writeToDatabase("clientFiles", updateDoc, "Investment", true);
		}
		
		// CREATE + ADD TO MONGODB
		Utilities.connectToMongoDB().getCollection("accountFiles").insertOne(this.accountInfo);
		ClientOptions_GUI.clientOptions(this.c);
	}
	
	
	
	/**
	 * This method runs the GUI for the CreateAccount_GUI class, by instantiating a CreateAccount_GUI window object.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 */
	public static void createAccount(Client clientArg) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				CreateAccount_GUI window = null;
				window = new CreateAccount_GUI(clientArg);
				window.frame.setVisible(true);
			}
		});
	}
}