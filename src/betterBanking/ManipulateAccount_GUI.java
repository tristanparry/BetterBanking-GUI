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
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

/**
 * This class defines a new GUI for the software partition,
 * and implements methods to manipulate an Account object.
 * @author Tristan Parry
 * @version 1.0
 */
public class ManipulateAccount_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLayeredPane layeredPane = new JLayeredPane();
	private JLabel titleLabel = new JLabel("Account Actions"), depositPanelLabel = new JLabel("Deposit Amount ($)"), withdrawPanelLabel = new JLabel("Withdraw Amount ($)"),
					balancePanelLabel, interestRatePanelLabel, totalFundsPanelLabel, writeChequePanelLabel1 = new JLabel("Recipient Name"), writeChequePanelLabel2 = new JLabel("Amount ($)"),
					moveMoneyPanelLabel1 = new JLabel("Amount ($)"), moveMoneyPanelLabel2 = new JLabel("Account"), buyStockPanelLabel1 = new JLabel("Company Name"), buyStockPanelLabel2 = new JLabel("Amount ($)");
	private JPanel depositPanel = new JPanel(), withdrawPanel = new JPanel(), balancePanel = new JPanel(), interestRatePanel = new JPanel(), totalFundsPanel = new JPanel(),
					writeChequePanel = new JPanel(), moveMoneyPanel = new JPanel(), buyStockPanel = new JPanel();
	private JButton depositButton = new JButton("Deposit"), withdrawButton = new JButton("Withdraw"), balanceButton = new JButton("Balance"), interestRateButton = new JButton("Interest Rate"),
					totalFundsButton = new JButton("Total Funds"), action6Button = new JButton(), detailsButton = new JButton("Details"), exitButton = new JButton("Exit"),
					depositPanelButton = new JButton("Deposit"), withdrawPanelButton = new JButton("Withdraw"), writeChequePanelButton = new JButton("Write Cheque"),
					moveMoneyPanelChequingButton = new JButton("Chequing"), moveMoneyPanelInvestmentButton = new JButton("Investment"), buyStockPanelButton = new JButton("Buy Stock"), backButton = new JButton("Back");
	private JTextField depositPanelField = new JTextField(), withdrawPanelField = new JTextField(), writeChequePanelField1 = new JTextField(), writeChequePanelField2 = new JTextField(),
						moveMoneyPanelField = new JTextField(), buyStockPanelField1 = new JTextField(), buyStockPanelField2 = new JTextField();
	private JTextPane detailsLabel = new JTextPane();
	private JScrollPane scrollPane;
	private DecimalFormat df = new DecimalFormat("#0.##");
	private Client c;
	private Account a;
	
	/**
	 * This constructor creates a GUI for the ManipulateAccount method of the BetterBanking software.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 * @param accountArg Account object being manipulated in the program
	 * @throws IOException Exception as a result of I/O failure
	 */
	public ManipulateAccount_GUI(Client clientArg, Account accountArg) throws IOException {
		this.c = clientArg;
		this.a = accountArg;
		
		// POPULATE JFRAME
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.setBounds(100, 100, 470, 629);
		this.frame.setResizable(false);
		this.frame.getContentPane().setLayout(null);
		this.titleLabel.setFont(new Font("Consolas", Font.BOLD, 34));
		this.titleLabel.setBounds(10, 11, 312, 45);
		this.frame.getContentPane().add(this.titleLabel);
		
		this.layeredPane.setBounds(10, 68, 448, 268);
		this.frame.getContentPane().add(this.layeredPane);
		
		this.depositPanel.setBackground(new Color(178, 196, 207));
		this.depositPanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.depositPanel);
		this.depositPanel.setLayout(null);
		
		this.depositPanelLabel.setBounds(10, 32, 180, 39);
		this.depositPanelLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.depositPanel.add(this.depositPanelLabel);
		
		this.depositPanelField.setBounds(211, 32, 207, 39);
		this.depositPanelField.setBackground(new Color(204, 255, 255));
		this.depositPanelField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.depositPanelField.setColumns(10);
		this.depositPanel.add(this.depositPanelField);
		
		this.depositPanelButton.setBounds(10, 92, 122, 25);
		this.depositPanelButton.setBackground(new Color(204, 204, 204));
		this.depositPanelButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.depositPanelButton.setFocusPainted(false);
		this.depositPanelButton.addActionListener(this);
		this.depositPanel.add(this.depositPanelButton);
		
		this.detailsLabel.setContentType("text/html");
		this.detailsLabel.setText(this.a.toString());
		this.detailsLabel.setBackground(new Color(178, 196, 207));
		this.detailsLabel.setBounds(0, 0, 447, 268);
		this.detailsLabel.setEditable(false);
		this.scrollPane = new JScrollPane(this.detailsLabel);
		this.scrollPane.setBounds(12, 69, 436, 257);
		this.scrollPane.setBorder(null);
		this.frame.getContentPane().add(this.scrollPane);
		
		this.withdrawPanel.setBackground(new Color(178, 196, 207));
		this.withdrawPanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.withdrawPanel);
		this.withdrawPanel.setLayout(null);
		
		this.withdrawPanelLabel.setBounds(10, 32, 191, 39);
		this.withdrawPanelLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.withdrawPanel.add(this.withdrawPanelLabel);
		
		this.withdrawPanelField.setBounds(211, 32, 207, 39);
		this.withdrawPanelField.setBackground(new Color(204, 255, 255));
		this.withdrawPanelField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.withdrawPanelField.setColumns(10);
		this.withdrawPanel.add(this.withdrawPanelField);
		
		this.withdrawPanelButton.setBounds(10, 92, 122, 25);
		this.withdrawPanelButton.setBackground(new Color(204, 204, 204));
		this.withdrawPanelButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.withdrawPanelButton.setFocusPainted(false);
		this.withdrawPanelButton.addActionListener(this);
		this.withdrawPanel.add(this.withdrawPanelButton);
		
		this.balancePanel.setBackground(new Color(178, 196, 207));
		this.balancePanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.balancePanel);
		
		this.balancePanelLabel = new JLabel("<HTML>" + this.a.getAccountType() + " account balance:<br>$" + this.a.balance() + "</HTML>");
		this.balancePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.balancePanelLabel.setBounds(10, 32, 847, 39);
		this.balancePanelLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
		this.balancePanel.add(this.balancePanelLabel);
		
		this.interestRatePanel.setBackground(new Color(178, 196, 207));
		this.interestRatePanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.interestRatePanel);
		
		this.interestRatePanelLabel = new JLabel("<HTML>" + this.a.getAccountType() + " interest rate:<br>" + this.a.getInterestRate() + "%</HTML>");
		this.interestRatePanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.interestRatePanelLabel.setBounds(10, 32, 872, 39);
		this.interestRatePanelLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
		this.interestRatePanel.add(this.interestRatePanelLabel);
		
		this.totalFundsPanel.setBackground(new Color(178, 196, 207));
		this.totalFundsPanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.totalFundsPanel);
		
		this.totalFundsPanelLabel = new JLabel("<HTML>Total client funds:<br>$" + this.a.getClientFunds() + "</HTML>");
		this.totalFundsPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.totalFundsPanelLabel.setBounds(10, 32, 872, 39);
		this.totalFundsPanelLabel.setFont(new Font("Consolas", Font.PLAIN, 22));
		this.totalFundsPanel.add(this.totalFundsPanelLabel);
		
		this.writeChequePanel.setBackground(new Color(178, 196, 207));
		this.writeChequePanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.writeChequePanel);
		this.writeChequePanel.setLayout(null);
		
		this.writeChequePanelLabel1.setBounds(10, 32, 180, 39);
		this.writeChequePanelLabel1.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.writeChequePanel.add(this.writeChequePanelLabel1);
		
		this.writeChequePanelField1.setBounds(173, 32, 239, 39);
		this.writeChequePanelField1.setBackground(new Color(204, 255, 255));
		this.writeChequePanelField1.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.writeChequePanelField1.setColumns(10);
		this.writeChequePanel.add(this.writeChequePanelField1);
		
		this.writeChequePanelLabel2.setBounds(10, 85, 180, 39);
		this.writeChequePanelLabel2.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.writeChequePanel.add(this.writeChequePanelLabel2);
		
		this.writeChequePanelField2.setBounds(173, 85, 239, 39);
		this.writeChequePanelField2.setBackground(new Color(204, 255, 255));
		this.writeChequePanelField2.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.writeChequePanelField2.setColumns(10);
		this.writeChequePanel.add(this.writeChequePanelField2);
		
		this.writeChequePanelButton.setBounds(10, 142, 152, 25);
		this.writeChequePanelButton.setBackground(new Color(204, 204, 204));
		this.writeChequePanelButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.writeChequePanelButton.setFocusPainted(false);
		this.writeChequePanelButton.addActionListener(this);
		this.writeChequePanel.add(this.writeChequePanelButton);
		
		this.moveMoneyPanel.setBackground(new Color(178, 196, 207));
		this.moveMoneyPanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.moveMoneyPanel);
		this.moveMoneyPanel.setLayout(null);
		
		this.moveMoneyPanelLabel1.setBounds(10, 35, 130, 39);
		this.moveMoneyPanelLabel1.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.moveMoneyPanel.add(this.moveMoneyPanelLabel1);
		
		this.moveMoneyPanelField.setBounds(143, 35, 269, 39);
		this.moveMoneyPanelField.setBackground(new Color(204, 255, 255));
		this.moveMoneyPanelField.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.moveMoneyPanelField.setColumns(10);
		this.moveMoneyPanel.add(this.moveMoneyPanelField);
		
		this.moveMoneyPanelLabel2.setBounds(10, 85, 180, 39);
		this.moveMoneyPanelLabel2.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.moveMoneyPanel.add(this.moveMoneyPanelLabel2);
		
		this.moveMoneyPanelChequingButton.setBounds(143, 92, 122, 25);
		this.moveMoneyPanelChequingButton.setBackground(new Color(204, 204, 204));
		this.moveMoneyPanelChequingButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.moveMoneyPanelChequingButton.setFocusPainted(false);
		this.moveMoneyPanelChequingButton.setVisible(false);
		if (this.c.updateAccounts("Chequing")) { this.moveMoneyPanelChequingButton.setVisible(true); }
		this.moveMoneyPanelChequingButton.addActionListener(this);
		this.moveMoneyPanel.add(this.moveMoneyPanelChequingButton);
		
		this.moveMoneyPanelInvestmentButton.setBounds(290, 92, 122, 25);
		this.moveMoneyPanelInvestmentButton.setBackground(new Color(204, 204, 204));
		this.moveMoneyPanelInvestmentButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.moveMoneyPanelInvestmentButton.setFocusPainted(false);
		this.moveMoneyPanelInvestmentButton.setVisible(false);
		if (this.c.updateAccounts("Investment")) { this.moveMoneyPanelInvestmentButton.setVisible(true); }
		this.moveMoneyPanelInvestmentButton.addActionListener(this);
		this.moveMoneyPanel.add(this.moveMoneyPanelInvestmentButton);
		
		this.buyStockPanel.setBackground(new Color(178, 196, 207));
		this.buyStockPanel.setBounds(0, 0, 447, 268);
		this.layeredPane.add(this.buyStockPanel);
		this.buyStockPanel.setLayout(null);
		
		this.buyStockPanelLabel1.setBounds(10, 32, 180, 39);
		this.buyStockPanelLabel1.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.buyStockPanel.add(this.buyStockPanelLabel1);
		
		this.buyStockPanelField1.setBounds(173, 32, 239, 39);
		this.buyStockPanelField1.setBackground(new Color(204, 255, 255));
		this.buyStockPanelField1.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.buyStockPanelField1.setColumns(10);
		this.buyStockPanel.add(this.buyStockPanelField1);
		
		this.buyStockPanelLabel2.setBounds(10, 85, 180, 39);
		this.buyStockPanelLabel2.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.buyStockPanel.add(this.buyStockPanelLabel2);
		
		this.buyStockPanelField2.setBounds(173, 85, 239, 39);
		this.buyStockPanelField2.setBackground(new Color(204, 255, 255));
		this.buyStockPanelField2.setFont(new Font("Consolas", Font.ITALIC, 17));
		this.buyStockPanelField2.setColumns(10);
		this.buyStockPanel.add(this.buyStockPanelField2);
		
		this.buyStockPanelButton.setBounds(10, 142, 122, 25);
		this.buyStockPanelButton.setBackground(new Color(204, 204, 204));
		this.buyStockPanelButton.setFont(new Font("Consolas", Font.PLAIN, 14));
		this.buyStockPanelButton.setFocusPainted(false);
		this.buyStockPanelButton.addActionListener(this);
		this.buyStockPanel.add(this.buyStockPanelButton);
		
		this.depositButton.setBackground(Color.DARK_GRAY);
		this.depositButton.setForeground(new Color(255, 255, 255));
		this.depositButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.depositButton.setBounds(10, 347, 214, 51);
		this.depositButton.setFocusPainted(false);
		this.depositButton.addActionListener(this);
		this.frame.getContentPane().add(this.depositButton);
		
		this.withdrawButton.setForeground(Color.WHITE);
		this.withdrawButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.withdrawButton.setBackground(Color.DARK_GRAY);
		this.withdrawButton.setBounds(234, 347, 214, 51);
		this.withdrawButton.setFocusPainted(false);
		this.withdrawButton.addActionListener(this);
		this.frame.getContentPane().add(this.withdrawButton);
		
		this.balanceButton.setForeground(Color.WHITE);
		this.balanceButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.balanceButton.setBackground(Color.DARK_GRAY);
		this.balanceButton.setBounds(10, 409, 214, 51);
		this.balanceButton.setFocusPainted(false);
		this.balanceButton.addActionListener(this);
		this.frame.getContentPane().add(this.balanceButton);
		
		this.interestRateButton.setForeground(Color.WHITE);
		this.interestRateButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.interestRateButton.setBackground(Color.DARK_GRAY);
		this.interestRateButton.setBounds(234, 409, 214, 51);
		this.interestRateButton.setFocusPainted(false);
		this.interestRateButton.addActionListener(this);
		this.frame.getContentPane().add(this.interestRateButton);
		
		this.totalFundsButton.setForeground(Color.WHITE);
		this.totalFundsButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.totalFundsButton.setBackground(Color.DARK_GRAY);
		this.totalFundsButton.setBounds(10, 472, 214, 51);
		this.totalFundsButton.setFocusPainted(false);
		this.totalFundsButton.addActionListener(this);
		this.frame.getContentPane().add(this.totalFundsButton);
		
		switch(this.a.getAccountType()) {
		case "Chequing":
			this.action6Button = new JButton("Write Cheque");
			break;
		case "Savings":
			this.action6Button = new JButton("Move Money");
			try {
				if (!this.c.updateAccounts("Chequing") && !this.c.updateAccounts("Investment")) { this.action6Button.setVisible(false); }
			} catch (IOException e1) {}
			break;
		case "Investment":
			this.action6Button = new JButton("Buy Stock");
			break;
		}
		this.action6Button.setForeground(Color.WHITE);
		this.action6Button.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.action6Button.setBackground(Color.DARK_GRAY);
		this.action6Button.setBounds(234, 472, 214, 51);
		this.action6Button.setFocusPainted(false);
		this.action6Button.addActionListener(this);
		this.frame.getContentPane().add(this.action6Button);
		
		this.detailsButton.setForeground(Color.WHITE);
		this.detailsButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.detailsButton.setBackground(Color.DARK_GRAY);
		this.detailsButton.setBounds(10, 534, 214, 51);
		this.detailsButton.setFocusPainted(false);
		this.detailsButton.addActionListener(this);
		this.frame.getContentPane().add(this.detailsButton);
		
		this.exitButton.setForeground(Color.WHITE);
		this.exitButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.exitButton.setBackground(new Color(255, 51, 51));
		this.exitButton.setBounds(347, 534, 101, 51);
		this.exitButton.setFocusPainted(false);
		this.exitButton.addActionListener(this);
		this.frame.getContentPane().add(this.exitButton);
		
		this.backButton.setForeground(Color.WHITE);
		this.backButton.setFont(new Font("Consolas", Font.ITALIC, 16));
		this.backButton.setFocusPainted(false);
		this.backButton.setBackground(new Color(255, 51, 51));
		this.backButton.setBounds(237, 534, 102, 51);
		this.backButton.setFocusPainted(false);
		this.backButton.addActionListener(this);
		this.frame.getContentPane().add(this.backButton);
		
		this.layeredPane.removeAll();
		this.frame.getContentPane().add(this.scrollPane);
	}
	
	
	
	/**
	 * This method accepts user input for a valid BetterBanking Account operation,
	 * and performs the manipulation on the provided Account object.
	 */
	public void actionPerformed(ActionEvent ae) {		
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.depositButton) {
			switchJPanel(this.depositPanel);
		}
		if (ae.getSource() == this.depositPanelButton) {
			try { this.a.deposit(Utilities.readDoubleFrom(this.depositPanelField)); } catch (IOException e) {}
		}
		if (ae.getSource() == this.withdrawButton) {
			switchJPanel(this.withdrawPanel);
		}
		if (ae.getSource() == this.withdrawPanelButton) {
			try { this.a.withdraw(Utilities.readDoubleFrom(this.withdrawPanelField)); } catch (IOException e) {}
		}
		if (ae.getSource() == this.balanceButton) {
			switchJPanel(this.balancePanel);
			this.balancePanelLabel.setText("<HTML>" + a.getAccountType() + " account balance:<br>$" + df.format(a.balance()) + "</HTML>");
		}
		if (ae.getSource() == this.interestRateButton) {
			switchJPanel(this.interestRatePanel);
			this.interestRatePanelLabel.setText("<HTML>" + a.getAccountType() + " interest rate:<br>" + a.getInterestRate() + "%</HTML>");
		}
		if (ae.getSource() == this.totalFundsButton) {
			switchJPanel(this.totalFundsPanel);
			this.totalFundsPanelLabel.setText("<HTML>Total client funds:<br>$" + df.format(a.getClientFunds()) + "</HTML>");
		}
		if (ae.getSource() == this.action6Button) {
			this.frame.getContentPane().remove(this.scrollPane);
			this.layeredPane.removeAll();
			if (this.a instanceof Chequing) {
				this.layeredPane.add(this.writeChequePanel);
			} else if (this.a instanceof Savings) {
				this.layeredPane.add(this.moveMoneyPanel);
			} else if (this.a instanceof Investment) {
				this.layeredPane.add(this.buyStockPanel);
			}
			this.layeredPane.repaint();
			this.layeredPane.revalidate();
		}
		if (ae.getSource() == this.writeChequePanelButton) {
			if (!this.writeChequePanelField1.getText().isBlank()) {
				try {
					((Chequing) this.a).writeCheque(this.writeChequePanelField1.getText(), (Utilities.readDoubleFrom(this.writeChequePanelField2)));
					this.writeChequePanelField1.setText("");
				} catch (IOException e) {}
			} else {
				this.writeChequePanelField1.setText("");
				this.writeChequePanelField2.setText("");
			}
		}
		if (ae.getSource() == this.moveMoneyPanelChequingButton) {
			Account clientAccount = null;
			try { clientAccount = new Chequing(Files.readAllLines(Paths.get("accountFiles/" + c.getUsername() + "_chequing.txt"))); } catch (IOException e1) {}
			try { ((Savings) this.a).moveMoney(Utilities.readDoubleFrom(this.moveMoneyPanelField), clientAccount); } catch (IOException e) {}
		}
		if (ae.getSource() == this.moveMoneyPanelInvestmentButton) {
			Account clientAccount = null;
			try { clientAccount = new Investment(Files.readAllLines(Paths.get("accountFiles/" + c.getUsername() + "_investment.txt"))); } catch (IOException e) {}
			try { ((Savings) this.a).moveMoney(Utilities.readDoubleFrom(this.moveMoneyPanelField), clientAccount); } catch (IOException e) {}
		}
		if (ae.getSource() == this.buyStockPanelButton) {
			if (!this.buyStockPanelField1.getText().isBlank()) {
				try {
					((Investment) this.a).buyStock(buyStockPanelField1.getText(), (Utilities.readDoubleFrom(this.buyStockPanelField2)));
					this.buyStockPanelField1.setText("");
				} catch (IOException e) {}
			} else {
				this.buyStockPanelField1.setText("");
				this.buyStockPanelField2.setText("");
			}
		}
		if (ae.getSource() == this.detailsButton) {
			this.layeredPane.removeAll();
			this.frame.getContentPane().add(this.scrollPane);
			this.detailsLabel.setText(this.a.toString());
		}
		if (ae.getSource() == this.backButton) {
			this.frame.dispose();
			try { ClientOptions_GUI.clientOptions(this.c); } catch (IOException e) {}
		}
		if (ae.getSource() == this.exitButton) {
			this.frame.dispose();
			try { this.a.applyInterest(); } catch (IOException e) {}
			Exit_GUI.showExitScreen("<HTML>Thank you for<br>choosing <b>BetterBanking.</b><br>Your session has<br>now been ended.</HTML>");
		}
	}
	
	
	
	/**
	 * This method allows for efficient switching of JPanels in the GUI JLayeredPane.
	 * @param newPanel JPanel object to be switched/brought into view
	 */
	public void switchJPanel(JPanel newPanel) {
		this.frame.getContentPane().remove(this.scrollPane);
		this.layeredPane.removeAll();
		this.layeredPane.add(newPanel);
		this.layeredPane.repaint();
		this.layeredPane.revalidate();
	}
	
	
	
	/**
	 * This method runs the GUI for the ManipulateAccount_GUI class, by instantiating/running a ManipulateAccount_GUI window object.
	 * @param clientArg Client object used to access the corresponding BetterBanking Client account
	 * @param accountArg Account object being manipulated in the program
	 */
	public static void manipulateAccount(Client clientArg, Account accountArg) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ManipulateAccount_GUI window = null;
				try { window = new ManipulateAccount_GUI(clientArg, accountArg); } catch (IOException e) {}
				window.frame.setVisible(true);
			}
		});
	}
}