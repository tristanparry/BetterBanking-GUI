package betterBanking;

// IMPORT STATEMENTS
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This method displays a welcome message to the user, and allows
 * them to navigate from the main menu of the BetterBanking software.
 * @author Tristan Parry
 * @version 1.0
 */
public class Main_GUI implements ActionListener {
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel titleLabel1 = new JLabel("Welcome to"), titleLabel2 = new JLabel("BetterBanking");
	private JButton createClientButton = new JButton("Create Client"), loginClientButton = new JButton("Login Client"), exitButton = new JButton("Exit");

	/**
	 * This constructor creates a GUI for the Main method of the BetterBanking software.
	 */
	public Main_GUI() {
		// POPULATE JFRAME
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.getContentPane().setLayout(null);
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.setBounds(100, 100, 346, 413);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.titleLabel1.setFont(new Font("Consolas", Font.ITALIC, 20));
		this.titleLabel1.setBounds(35, 26, 116, 26);
		this.frame.getContentPane().add(this.titleLabel1);
		
		this.titleLabel2.setFont(new Font("Consolas", Font.BOLD, 34));
		this.titleLabel2.setBounds(35, 48, 261, 46);
		this.frame.getContentPane().add(this.titleLabel2);
		
		this.createClientButton.setBackground(Color.DARK_GRAY);
		this.createClientButton.setForeground(new Color(255, 255, 255));
		this.createClientButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.createClientButton.setBounds(22, 128, 283, 61);
		this.createClientButton.setFocusPainted(false);
		this.createClientButton.addActionListener(this);
		this.frame.getContentPane().add(this.createClientButton);
		
		this.loginClientButton.setForeground(Color.WHITE);
		this.loginClientButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.loginClientButton.setBackground(Color.DARK_GRAY);
		this.loginClientButton.setBounds(22, 207, 283, 61);
		this.loginClientButton.setFocusPainted(false);
		this.loginClientButton.addActionListener(this);
		this.frame.getContentPane().add(this.loginClientButton);
		
		this.exitButton.setForeground(Color.WHITE);
		this.exitButton.setFont(new Font("Consolas", Font.PLAIN, 18));
		this.exitButton.setBackground(new Color(255, 51, 51));
		this.exitButton.setBounds(22, 288, 283, 61);
		this.exitButton.setFocusPainted(false);
		this.exitButton.addActionListener(this);
		this.frame.getContentPane().add(this.exitButton);
	}
	
	/**
	 * This method accepts user input to create/login to a BetterBanking Client.
	 */
	public void actionPerformed(ActionEvent ae) {
		this.frame.dispose();
		// CHECKING + PROCESSING BUTTON PRESSES
		if (ae.getSource() == this.createClientButton) {
			ManageClient_GUI.createClient();
		}
		if (ae.getSource() == this.loginClientButton) {
			LoginClient_GUI.loginClient();
		}
		if (ae.getSource() == this.exitButton) {
			Exit_GUI.showExitScreen("<HTML>Thank you for<br>choosing <b>BetterBanking.</b><br>Your session has<br>now been ended.</HTML>");
		}
	}
	
	
	
	/**
	 * This method runs the GUI for the Main_GUI class, by instantiating/running a Main_GUI window object.
	 * @param args String[] arguments passed as part of execution
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Main_GUI window = new Main_GUI();
				window.frame.setVisible(true);
			}
		});
	}
}