import static java.time.temporal.ChronoUnit.DAYS;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * The Loan class models a loan of a copy in the system.
 * Librarians can loan copies of a resource to users who request for them.
 * @author William King
 */
public class Loan {
	
	/** A unique ID for the Loan. */
	private int loanID;
	
	/** The date the Loan was issued (YYYY-MM-DD). */
	private String checkoutDate;
	
	/** The time the Loan was issued (HH:MM). */
	private String checkoutTime;
	
	/** The date the Loan needs to be returned. It's set when the copy is 
	 * requested and it's either the loan duration or the day after the 
	 * request was made - (YYYY-MM-DD) - otherwise "".
	 */
	private String dueDate;
	
	/** Whether the Loan has been returned or not. */
	private boolean returned;
	
	/** The date that the Loan was returned (YYYY-MM-DD). */
	private String returnDate;
	
	/** The time that the Loan was returned (HH:MM). */
	private String returnTime;
	
	/** The number of days past the due date for the returned Loan. */
	private int daysOverdue;
	
	/** The username of the user who the Loan was issued to. */
	private String username;
	
	/** The ID of the librarian who authorised the Loan. */
	private int staffID;
	
	/** The ID of the resource. */
	private int resourceID;
	
	/** The ID of the loaned copy. */
	private int copyID;
    
    /** The type of resource of the loaned copy. */
    private ResourceType type;
    
    /**
     * Constructor for the Loan class.
     * @param loanID A unique ID for the Loan.
     * @param copyID The ID of the loaned copy.
     * @param resourceID The ID of the resource.
     * @param username The username of the user who the Loan was issued to.
	 * @param staffID The ID of the librarian who authorised the Loan.
     * @param checkoutDate The date the Loan was issued (YYYY-MM-DD).
     * @param checkoutTime The time the loan was issued (HH:MM).
	 * @param dueDate The date the Loan needs to be returned. It's set when 
	 *                the copy is requested and it's either the loan duration 
	 *                or the day after the request was made - (YYYY-MM-DD).
	 * @param returned Whether the Loan has been returned or not.
	 * @param returnDate The date that the Loan was been returned 
	 *                   (YYYY-MM-DD).
	 * @param returnTime The time that the loan was returned (HH:MM). 
	 * @param daysOverdue The amount of days overdue an item is;
     * @param type The type of resource of the loaned copy.
     */
    public Loan (int loanID, int copyID, int resourceID, String username, 
    		int staffID, String checkoutDate, String checkoutTime, String dueDate,
            boolean returned, String returnDate, String returnTime, int daysOverdue,
            ResourceType type) {
    	this.loanID = loanID;
    	this.copyID = copyID;
    	this.resourceID = resourceID;
    	this.username = username;
        this.staffID = staffID;
        this.checkoutDate = checkoutDate;
        this.checkoutTime = checkoutTime;
        this.type = type;
        
        this.dueDate = dueDate;
        this.returned = returned;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
        this.daysOverdue = daysOverdue;
    }
    
    /**
	 * Gets the full details of the loan.
	 * @return Full details of the loan as a string.
	 */
    public String toStringDetail() {
    	String strLoan = loanID + "," + copyID + "," + resourceID + 
    			"," + username + "," + staffID + "," + checkoutDate + 
    			"," + checkoutTime + "," + dueDate + "," + returned + 
    			"," + returnDate + "," + returnTime + "," + daysOverdue + 
    			"," + type + ",";
    	return strLoan;
    }
    
    /**
	 * Gets the ID of the Loan.
	 * @return The ID of the Loan.
	 */
	public int getLoanID() {
			return loanID;
	}
	
	/**
	 * Gets the ID of the loaned copy.
	 * @return The ID of the loaned copy.
	 */
	public int getCopyID() {
			return copyID;
	}
	
	/**
	 * Gets the ID of the resource associated with the loan.
	 * @return Returns the resource ID.
	 */
	public int getResourceID() {
			return resourceID;
	}
	
	/**
     * Gets the username for the object being viewed.
     * @return Returns a username.
     */
    public String getUsername() {
            return username;
    }
	
	/**
	 * Gets the ID of the librarian who authorised the Loan.
	 * @return The ID of the librarian who authorised the Loan.
	 */
	public int getStaffID() {
			return staffID;
	}
	
	/**
	 * Gets the date that the Loan was issued (YYYY-MM-DD).
	 * @return The date the Loan was issued (YYYY-MM-DD).
	 */
	public String getCheckoutDate() {
		return checkoutDate;
	}
	
	/**
	 * Gets the time that the Loan was issued (HH:MM).
	 * @return The time the Loan was issued (HH:MM).
	 */
	public String getCheckoutTime() {
		return checkoutTime;
	}
	
	/**
	 * Gets the date that the Loan needs to be returned (YYYY-MM-DD).
	 * @return The date the Loan needs to be returned (YYYY-MM-DD).
	 */
	public String getDueDate() {
		return dueDate;
	}
	
	/**
	 * Calculates the date that the Loan needs to be returned by using 
	 * the copy's loan duration (YYYY-MM-DD).
	 * @param duration The loan duration of the loaned copy.
	 */
	public void setDueDate(int duration) {
		int daysPastDueDate = Utility.daysPastDate(this.checkoutDate);
		
		// Past the loan duration.
		if (daysPastDueDate > duration) {
			LocalDate today = LocalDate.now();
			LocalDate tomorrow = today.plusDays(1); 
			this.dueDate = tomorrow.toString();
		// If it's not past the loan duration, let them have it for at least
		// the length of the loan duration.
		} else {
			LocalDate thisDate = LocalDate.parse(checkoutDate);
			LocalDate dueDateLocal = thisDate.plusDays(duration); 
			this.dueDate = dueDateLocal.toString();
		}
	}
    
	/**
	 * Checks whether the Loan has been returned or not.
	 * @return Whether the Loan has been returned or not.
	 */
	public boolean isReturned() {
		return returned;
	}
	
	/**
	 * Sets whether the Loan has been returned or not.
	 * @param returned Whether the Loan has been returned or not.
	 */
	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
	/**
     * Gets the date a user returned a loan (YYYY-MM-DD).
     * @return Returns a date (YYYY-MM-DD).
     */
    public String getReturnDate() {
    	return returnDate;
    }
    
    /**
	 * Sets the date that the Loan was returned (YYYY-MM-DD).
	 * @param returnDate The date that the Loan was returned (YYYY-MM-DD).
	 */
    public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
    
    /**
     * Gets the time a user returned a loan (HH:MM).
     * @return Returns a time (HH:MM).
     */
    public String getReturnTime() {
    	return returnTime;
    }
    
    /**
	 * Sets the time that the Loan was returned (HH:MM).
	 * @param returnTime The time that the Loan was returned (HH:MM).
	 */
    public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	
	/**
	 * Gets the number of days past the due date of the returned Loan.
	 * @return The number of days past the due date of the returned Loan.
	 */
	public int getDaysOverdue() {
		return daysOverdue;
	}
	
	/**
	 * Calculates the number of days past the due date of the returned Loan.
	 * Only happens if the loaned copy is requested or in the middle of a
	 * request queue.
	 */
	public void setDaysOverdue() {
		if (this.dueDate.isEmpty()) {
			this.daysOverdue = 0;
		} else {
			LocalDate returnDateLoc = LocalDate.parse(returnDate);
			LocalDate dueDateLoc = LocalDate.parse(dueDate);
	        int days = (int) DAYS.between(dueDateLoc, returnDateLoc);
	        if (days < 0) {
	        	this.daysOverdue = 0;
	        } else {
	        	this.daysOverdue = days;
	        }	
		}
	}

	/**
     * Gets the resource type of the loaned copy.
     * @return Returns The resource type of the loaned copy.
     */
    public ResourceType getType() {
    	return type;
    }

    /**
	 * Gets a short description of a loan.
	 * @return A short description of the Loan.
	 */
	public String getDescription() {
		String status = "Borrowed";
		String date = checkoutDate;
		String time = checkoutTime;
		if (returned) {
			status = "Returned";
			date = returnDate;
			time = returnTime;
		} 
		return "Loan ID: " + loanID + " | Username: " + username + " | "
				+ "Status: " + status + " | Date: " + date + " | "
						+ "Time: " + time;
	}
	
	/**
	 * Gets a short description of an overdue Loan,
	 * that's suitable to display.
	 * @return A short description of the an overdue Loan.
	 */
	public String getOverdueDescription() {
		return "Copy ID: " + copyID + " | Days Overdue: " + daysOverdue + 
				" | Username: " + username;
	}
	
	/**
	 * Gets a short description of an issued Loan,
	 * that's suitable to display.
	 * @return A short description of the issued Loan.
	 */
	public String getBorrowedDescription() {
		return "Username: " + username + " | Status: Borrowed | "
				+ "Date: " + checkoutDate + " | Time: " + checkoutTime;
	}
	
	/**
	 * Gets a short description of a returned Loan,
	 * that's suitable to display.
	 * @return A short description of the returned Loan.
	 */
	public String getReturnedDescription() {
		return "Username: " + username + " | Status: Returned | "
				+ "Date: " + returnDate + " | Time: " + returnTime;
			
	}
}