package data;

/**
 * The Transaction class models a transaction which can either
 * be a fine or payment.
 * @author William King
 */
public class Transaction implements Comparable<Transaction> {
	
	/** The unique ID for the transaction */
	private int transactionID;
	
	/** ID of the resource that caused the fine. */
	private int resourceID;
	
	/** The username of the user who the transaction is issued for. */
	private String username;
	
	/** The amount of money involved in the transaction (in GBP). */
	private double amount;
	
	/** The number of days overdue for the loan (that the user was fined for). */
	private int daysOverdue;
	
	/** The date that the transaction was carried out (YYYY-MM-DD). */
	private String date;
	
	/** The time that the transaction was carried out (HH:MM:SS). */
	private String time;
	
	/** The type of resource that the user was fined for. */
	private ResourceType type;
	
	/** If the transaction is a fine or not (or payment). */
	private boolean isFine; 
	
	/**
	 * Constructor for the Transaction class. 
	 * @param transactionID The unique ID for the transaction.
	 * @param resourceID The ID of the resource that caused the fine.
	 * @param username The username of the user who the transaction is issued for.
	 * @param amount The amount of money involved in the transaction (GBP).
	 * @param daysOverdue The number of days overdue for the loan (for fines).
	 * @param date The date that the transaction was carried out (YYYY-MM-DD).
	 * @param time The time that the transaction was carried out (HH:MM:SS).
	 * @param type The type of resource that the user was fined for.
	 * @param isFine Checks if the transaction is a fine or not (or payment).
	 */
	public Transaction(int transactionID, int resourceID, String username, 
			double amount, int daysOverdue, String date, String time, 
			ResourceType type, boolean isFine) {
		this.transactionID = transactionID;
		this.resourceID = resourceID;
		this.username = username;
		this.amount = amount;
		this.daysOverdue = daysOverdue;
		this.date = date;
		this.time = time;
		this.type = type;
		this.isFine = isFine;
	}
	
	/**
	 * Gets the full details of the transaction.
	 * @return Full details of the transaction as a string.
	 */
	public String toStringDetail() {
		String strTransaction = transactionID + "," + resourceID + 
				"," + username + "," + amount + "," + daysOverdue + 
				"," + date + "," + time + "," + type + "," + isFine + ",";
		return strTransaction;
	}
	
	/**
	 * Gets the ID of the transaction.
	 * @return The unique ID of the transaction.
	 */
	public int getTransactionID() {
		return transactionID;
	}
	
	/**
	 * Gets the resource ID of the resource that caused the fine.
	 * @return The resource ID of the resource that caused the fine.
	 */
	public int getResourceID() {
		return resourceID;
	}
	
	/**
	 * Gets the username of the user who the transaction is issued to.
	 * @return The username of the user who the transaction is issued to.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the amount of money involved in the transaction.
	 * @return The amount of money involved in the transaction.
	 */
	public double getAmount() {
		return amount;
	}
	
	/**
	 * Gets the days overdue for the fined loan.
	 * @return The days overdue for the fined loan.
	 */
	public int getDaysOverdue() {
		return daysOverdue;
	}
	
	/**
	 * Gets the date that the transaction was carried out (YYYY-MM-DD). 
	 * @return The date that the transaction was carried out (YYYY-MM-DD).
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * Gets the time that the transaction was carried out (HH:MM:SS). 
	 * @return The time that the transaction was carried out (HH:MM:SS).
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * Gets the type of resource that the user was fined for.
	 * @return The type of resource.
	 */
	public ResourceType getType() {
		return type;
	}
	
	/**
	 * Gets if the transaction was a fine or not.
	 * @return If the transaction was a fine or not.
	 */
	public boolean isFine() {
		return isFine;
	}
	
	/**
	 * Gets a description of a fine transaction as a string.
	 * @return Description of a fine transaction.
	 */
	public String getFineDescription() {
		String strFineDesc = "FINE - Date: " + date + " | Time: " + time 
				+ " | Amount: £" + amount + " | Days Overdue: " + daysOverdue 
				+ " | Resource ID: " + resourceID + " | Type: " + type;
		return strFineDesc;
	}
	
	/**
	 * Gets a description of a payment transaction as a string.
	 * @return Description of a payment transaction.
	 */
	public String getPaymentDescription() {
		String strPaymentDesc = "PAYMENT - Date: " + date + " | Time: " 
				+ time + " | Amount: £" + amount;
		return strPaymentDesc;
	}

	/**
	 * Comparison method used to sort the Transactions in ascending order of transaction ID.
	 * @param otherTransaction The other transaction object being compared.
	 * @return A positive integer if this > otherTransaction, otherwise a negative integer.
	 */
	public int compareTo(Transaction otherTransaction) {
		return this.getTransactionID() - otherTransaction.getTransactionID();
	}
}