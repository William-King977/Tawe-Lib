/**
 * The Copy class models a specific copy of a resource in the system.
 * @author William King
 */
public class Copy {
	
	/** The variable which holds the copy ID. */
    private int copyID;
    
    /** The unique ID of the resource that the Copy is associated with. */
    private int resourceID;
    
    /** Checks if the Copy is available for borrowing or not. */
    private boolean isAvailable; //true = can be borrowed, 
    //false = it's being borrowed by another user.
    
    /** The type of resource that the copy is associated with. */
    private ResourceType resourceType;
	
	/** The maximum duration of the loan of this Copy (Days). */
    public LoanDuration loanDuration;
	
    /**
     * Constructor of the Copy class.
     * @param resourceID The unique ID of the resource that the Copy is
     *        associated with.
     * @param copyID The unique ID of the copy.
     * @param isAvailable The status if the copy is available.
     * @param resourceType The type of resource that the Copy is.
     * @param loanDuration Maximum duration of the loan of this Copy (Days).
     */
	public Copy(int copyID, int resourceID, boolean isAvailable, 
    		ResourceType resourceType, LoanDuration loanDuration) {
		this.copyID = copyID;
        this.resourceID = resourceID;
        this.isAvailable = isAvailable;
        this.resourceType = resourceType;
        this.loanDuration = loanDuration;
	}
	
	/**
	 * Gets the full details of the copy.
	 * @return Full details of the copy as a string.
	 */
	public String toStringDetail() {
		String strCopy = copyID + "," + resourceID + "," + isAvailable + 
				"," + resourceType + "," + loanDuration;
		return strCopy;
	}
	
	/**
     * Gets the ID of the Copy.
     * @return The Copy's ID.
     */
    public int getCopyID() {
    	return copyID;
    }

    /**
     * Sets the ID of the copy.
     * @param copyID The ID of the copy.
     */
    public void setCopyID(int copyID) {
    	this.copyID = copyID;
    }

    /**
     * Gets the resource ID of the Copy.
     * @return The resource ID of the Copy.
     */
    public int getResourceID() {
    	return resourceID;
    }

    /**
     * Sets the resource ID for the Copy.
     * @param resourceID The resource ID of the Copy.
     */
    public void setResourceID(int resourceID) {
    	this.resourceID = resourceID;
    }

    /**
     * Checks if the Copy is available for borrowing or not.
     * @return If the Copy can be borrowed or not.
     */
    public boolean isAvailable() {
    	return isAvailable;
    }

    /**
     * Sets if the Copy can be borrowed or not.
     * @param isAvailable Whether the Copy can be borrowed or not.
     */
    public void setAvailable(boolean isAvailable) {
    	this.isAvailable = isAvailable;
    }
    
    /**
     * Gets the type of resource the copy is associated with.
     * @return The type of resource the copy is associated with.
     */
    public ResourceType getResourceType() {
    	return resourceType;
    }

    /**
     * Gets a short description of the Copy that's suitable to display.
     * @return A short description of the Copy.
     */
    public String getCopyDescription() {
    	// Used to display true or false value in a user friendly way.
        // For whether you can borrow the Copy or not.
    	String canBorrow;
        if (isAvailable) {
        	canBorrow = "Yes";
        } else {
        	canBorrow = "No";
        }
        return "Copy ID: " + copyID + " | Resource ID: " + resourceID + 
        		" | Is Available: " + canBorrow;
    }
	
	/**
     * Gets the maximum loan duration of the copy (Days).
     * @return loanDuration The maximum duration of the loan of this 
     *                      copy(Days).
     */
    public int getLoanDuration() {
    	int loanDurationDays = 0;
    	switch (loanDuration) {
    		case DAY:
    			loanDurationDays = 1;
                break;
            case WEEK:
            	loanDurationDays = 7;
            	break;
            case TWO_WEEK:
            	loanDurationDays = 14;
                break;
            case FOUR_WEEK:
            	loanDurationDays = 28;
                break;
    	}
    	return loanDurationDays;
    }
}
