package betterBankingGUImaven;

// IMPORT STATEMENTS
import java.util.List;
import org.bson.Document;
import com.mongodb.client.model.Updates;
import java.text.DecimalFormat;

/**
 * This abstract class defines a BetterBanking Account object,
 * and implements various methods to manipulate this Account.
 * @author Tristan Parry
 * @version 1.0
 */
public abstract class Account {
	// VARIABLE INITIALIZATIONS
	protected String accountType, username;
	protected double accountBalance, accountInterestRate;
	protected static double clientFunds;
	protected DecimalFormat df = new DecimalFormat("#0.##");
	
	/**
	 * This constructor creates a BetterBanking Account with a specified
	 * account type, Client username, balance, interest rate, and file path.
	 * @param accountInfo Document used to read the required Account parameters from an associated database file
	 */
	public Account(Document accountInfo) {
		this.accountType = accountInfo.getString("AccountType");
		this.username = accountInfo.getString("Username");
		this.accountBalance = accountInfo.getDouble("AccountBalance");
		this.accountInterestRate = accountInfo.getDouble("InterestRate");
		clientFunds = Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", this.username)).first().getDouble("ClientFunds");
	}
	
	/**
	 * This accessor method returns the Account object's accountType instance field.
	 * @return The Account's accountType field value
	 */
	public String getAccountType() {
		return this.accountType;
	}
	
	/**
	 * This mutator method calculates and applies a
	 * user deposit into a BetterBanking Account.
	 * @param depositArg Double value being deposited into the Account
	 */
	public void deposit(double depositArg) {
		if ((depositArg > 0) && (!Double.isInfinite(depositArg)) && (!Double.isNaN(depositArg)) && (!String.valueOf(depositArg).isBlank())) {
			this.accountBalance += depositArg;
			clientFunds += depositArg;
			Utilities.writeToDatabase("accountFiles", Utilities.connectToMongoDB().getCollection("accountFiles").find(new Document("Username", this.username).append("AccountType", this.accountType)).first(),
					"AccountBalance", Double.parseDouble(df.format(this.accountBalance)));
			Utilities.writeToDatabase("clientFiles", Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", this.username)).first(),
					"ClientFunds", Double.parseDouble(df.format(clientFunds)));
		}
	}
	
	/**
	 * This mutator method calculates and applies a
	 * user withdrawal from a BetterBanking Account.
	 * @param withdrawArg Double value being withdrawn from the Account
	 */
	public void withdraw(double withdrawArg) {
		if ((withdrawArg <= this.accountBalance) && (withdrawArg > 0)  && (!Double.isInfinite(withdrawArg)) && (!Double.isNaN(withdrawArg)) && (!String.valueOf(withdrawArg).isBlank())) {
			this.accountBalance -= withdrawArg;
			clientFunds -= withdrawArg;
			Utilities.writeToDatabase("accountFiles", Utilities.connectToMongoDB().getCollection("accountFiles").find(new Document("Username", this.username).append("AccountType", this.accountType)).first(),
					"AccountBalance", Double.parseDouble(df.format(this.accountBalance)));
			Utilities.writeToDatabase("clientFiles", Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", this.username)).first(),
					"ClientFunds", Double.parseDouble(df.format(clientFunds)));
		}
	}
	
	/**
	 * This accessor method returns the Account object's accountBalanace instance field.
	 * @return The Account's accountBalanace field value
	 */
	public double balance() {
		return this.accountBalance;
	}
	
	/**
	 * This accessor method returns the Account object's accountInterestRate instance field.
	 * @return The Account's accountInterestRate field value
	 */
	public double getInterestRate() {
		return this.accountInterestRate;
	}
	
	/**
	 * This accessor method returns the Account clientFunds static field.
	 * @return The Account clientFunds static field value
	 */
	public double getClientFunds() {
		return clientFunds;
	}
	
	/**
	 * This mutator method calculates/applies an Account object's interest
	 * rate, and writes the resulting balances to corresponding database files
	 */
	public void applyInterest() {
		this.accountBalance += this.accountBalance * (this.accountInterestRate / 100);
		clientFunds += this.accountBalance * (this.accountInterestRate / 100);
		Utilities.writeToDatabase("accountFiles", Utilities.connectToMongoDB().getCollection("accountFiles").find(new Document("Username", this.username).append("AccountType", this.accountType)).first(),
				"AccountBalance", Double.parseDouble(df.format(this.accountBalance)));
		Utilities.writeToDatabase("clientFiles", Utilities.connectToMongoDB().getCollection("clientFiles").find(new Document("Username", this.username)).first(),
				"ClientFunds", Double.parseDouble(df.format(clientFunds)));
	}
	
	/**
	 * This method returns a formatted String of the Account object's field values.
	 */
	public String toString() {
		return "<html><body style=\"font-family: consolas; font-size: 18; color: #333333\"<i>" + this.username.toUpperCase() + "'S " + this.accountType.toUpperCase() + " ACCOUNT:<br>"
				+ "-----------------------------</i><br>"
				+ "Balance: $" + df.format(Double.valueOf(this.accountBalance)) + "<br>"
				+ "Interest Rate: " + this.accountInterestRate + "%<br>"
				+ "Total Client Funds: $" + df.format(Double.valueOf(clientFunds)) + "</html>";
	}
}



/**
 * This class defines a BetterBanking Chequing object, inheriting from the parent
 * Account class and implementing various methods to manipulate this object.
 * @author Tristan Parry
 * @version 1.0
 */
class Chequing extends Account {
	// VARIABLE INITIALIZATIONS
	private String writtenCheques = "<html>";
	
	/**
	 * This constructor creates a Chequing Account with a specified
	 * account type, Client username, balance, interest rate, and file path.
	 * @param accountInfo Document used to read the required Account parameters from an associated database file
	 */
	public Chequing(Document accountInfo) {
		super(accountInfo);
		@SuppressWarnings("unchecked")
		List<Document> chequeList = (List<Document>) Utilities.connectToMongoDB().getCollection("accountFiles").find(new Document("Username", this.username)
				.append("AccountType", this.accountType)).first().get("WrittenCheques");
		try {
			for (Object i : chequeList) {
				this.writtenCheques += i + "\n\t\t ";
			}
		} catch (NullPointerException e) { this.writtenCheques = ""; }
	}
	
	/**
	 * This mutator method writes a cheque from the Chequing Account,
	 * and records the details to a corresponding database file.
	 * @param recipientArg String name of the written cheque's recipient
	 * @param amountArg Double value being written to a cheque/withdrawn from the Account
	 */
	public void writeCheque(String recipientArg, double amountArg) {
		if ((!recipientArg.isBlank()) && (amountArg <= this.accountBalance) && (amountArg > 0) && (!Double.isInfinite(amountArg)) && (!Double.isNaN(amountArg)) && (!String.valueOf(amountArg).isBlank())) {
			withdraw(amountArg);
			this.writtenCheques += recipientArg.toUpperCase() + ": $" + df.format(Double.valueOf(amountArg)) + "<br>&nbsp;&nbsp;&nbsp;";
			Utilities.connectToMongoDB().getCollection("accountFiles").updateOne(Utilities.connectToMongoDB().getCollection("accountFiles")
					.find(new Document("Username", this.username).append("AccountType", this.accountType)).first(),
					Updates.addToSet("WrittenCheques", recipientArg.toUpperCase() + ": $" + df.format(Double.valueOf(amountArg))));
		}
	}
	
	/**
	 * This method returns a formatted String of the Chequing Account object's field values (includes written cheques).
	 */
	public String toString() {		
		return "<html><body style=\"font-family: consolas; font-size: 18; color: #333333\"<i>" + this.username.toUpperCase() + "'S " + this.accountType.toUpperCase() + " ACCOUNT:<br>"
				+ "-----------------------------</i><br>"
				+ "Balance: $" + df.format(Double.valueOf(this.accountBalance)) + "<br>"
				+ "Interest Rate: " + this.accountInterestRate + "<br>"
				+ "Total Client Funds: $" + df.format(Double.valueOf(clientFunds)) + "<br>"
				+ "<i>Written Cheques:</i><br>&nbsp;&nbsp;&nbsp;" + this.writtenCheques + "</html>";
	}
}



/**
 * This class defines a BetterBanking Savings object, inheriting from the parent
 * Account class and implementing various methods to manipulate this object.
 * @author Tristan Parry
 * @version 1.0
 */
class Savings extends Account {
	/**
	 * This constructor creates a Savings Account with a specified
	 * account type, Client username, balance, interest rate, and file path.
	 * @param accountInfo Document used to read the required Account parameters from an associated database file
	 */
	public Savings(Document accountInfo) {
		super(accountInfo);
	}
	
	/**
	 * This mutator method transfers money from a
	 * Savings Account to a Chequing/Investment account.
	 * @param amountArg Double value being transferred from the Savings Account
	 * @param a Account object to transfer funds to (Chequing/Investment)
	 */
	public void moveMoney(double amountArg, Account a) {
		if ((amountArg <= this.accountBalance) && (amountArg >= 0)) {
			withdraw(amountArg);
			a.deposit(amountArg);
		}
	}
}



/**
 * This class defines a BetterBanking Investment object, inheriting from the parent
 * Account class and implementing various methods to manipulate this object.
 * @author Tristan Parry
 * @version 1.0
 */
class Investment extends Account {
	// VARIABLE INITIALIZATIONS
	private String purchasedStocks = "<html>";
	
	/**
	 * This constructor creates an Investment Account with a specified
	 * account type, Client username, balance, interest rate, and file path.
	 * @param accountInfo Document used to read the required Account parameters from an associated database file
	 */
	public Investment(Document accountInfo) {
		super(accountInfo);
		@SuppressWarnings("unchecked")
		List<Document> stockList = (List<Document>) Utilities.connectToMongoDB().getCollection("accountFiles").find(new Document("Username", this.username)
				.append("AccountType", this.accountType)).first().get("WrittenCheques");
		try {
			for (Object i : stockList) {
				this.purchasedStocks += i + "\n\t\t ";
			}
		} catch (NullPointerException e) { this.purchasedStocks = ""; }
	}
	
	/**
	 * This mutator method buys a stock through the Investment Account,
	 * and records the details to a corresponding database file.
	 * @param companyName String name of the purchased stock's company
	 * @param investmentAmountArg Double value being bought in stocks/withdrawn from the Account
	 */
	public void buyStock(String companyName, double investmentAmountArg) {
		if ((!companyName.isBlank()) && (investmentAmountArg <= this.accountBalance) && (investmentAmountArg > 0) && (!Double.isInfinite(investmentAmountArg)) && (!Double.isNaN(investmentAmountArg)) && (!String.valueOf(investmentAmountArg).isBlank())) {
			withdraw(investmentAmountArg);
			this.purchasedStocks += companyName.toUpperCase() + ": $" + df.format(Double.valueOf(investmentAmountArg)) + "<br>&nbsp;&nbsp;&nbsp;";
			Utilities.connectToMongoDB().getCollection("accountFiles").updateOne(Utilities.connectToMongoDB().getCollection("accountFiles")
					.find(new Document("Username", this.username).append("AccountType", this.accountType)).first(),
					Updates.addToSet("PurchasedStocks", companyName.toUpperCase() + ": $" + df.format(Double.valueOf(investmentAmountArg))));
		}
	}
	
	/**
	 * This method returns a formatted String of the Investment Account object's field values (includes purchased stocks).
	 */
	public String toString() {		
		return "<html><body style=\"font-family: consolas; font-size: 18; color: #333333\"<i>" + this.username.toUpperCase() + "'S " + this.accountType.toUpperCase() + " ACCOUNT:<br>"
				+ "-----------------------------</i><br>"
				+ "Balance: $" + df.format(Double.valueOf(this.accountBalance)) + "<br>"
				+ "Interest Rate: " + this.accountInterestRate + "%<br>"
				+ "Total Client Funds: $" + df.format(Double.valueOf(clientFunds)) + "<br>"
				+ "<i>Purchased Stocks:</i><br>&nbsp;&nbsp;&nbsp;" + this.purchasedStocks + "</html>";
	}
}