package betterBanking;

//IMPORT STATEMENTS
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.BorderLayout;

/**
 * This class defines a new GUI for the software partition,
 * and implements methods to display an exit window.
 * @author Tristan Parry
 * @version 1.0
 */
public class Exit_GUI {	
	// VARIABLE INITIALIZATIONS
	private JFrame frame = new JFrame();
	private JLabel exitLabel = new JLabel();
	
	/**
	 * This constructor creates a GUI for the Exit method of the BetterBanking software.
	 * @param exitMessage String that is written to the exit window
	 */
	public Exit_GUI(String exitMessage) {
		this.frame.setIconImage(Toolkit.getDefaultToolkit().getImage("misc/icon.png"));
		this.frame.setTitle("BetterBanking | \u00A9 2021");
		this.frame.getContentPane().setBackground(new Color(178, 196, 207));
		this.frame.setBounds(100, 100, 379, 235);
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.exitLabel.setBackground(new Color(178, 196, 207));
		this.exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.exitLabel.setFont(new Font("Consolas", Font.ITALIC, 22));
		this.exitLabel.setText(exitMessage);
		this.exitLabel.setBounds(35, 0, 295, 191);
		this.frame.getContentPane().add(this.exitLabel, BorderLayout.CENTER);
	}



	/**
	 * This method runs the GUI for the Exit_GUI class, by instantiating/running a Exit_GUI window object.
	 * @param exitMessage String that is written to the exit window
	 */
	public static void showExitScreen(String exitMessage) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Exit_GUI window = new Exit_GUI(exitMessage);
				window.frame.setVisible(true);
			}
		});
	}
}
