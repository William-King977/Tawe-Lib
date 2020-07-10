/**
 * The Librarian class models a librarian user in the system. A librarian is
 * any user who works at TaweLib and has extended permissions to manipulate
 * users and resources in the system.
 * @author William King
 */
public class Librarian extends User {
	
	/** A unique ID for each librarian */
	private int staffID;
	
	/** Employment date of a librarian (YYYY-MM-DD). */
    private String employmentDate;
	
    /**
     * Constructor for the librarian class.
     * @param username A unique name of a user.
     * @param firstName A first name of a user.
     * @param surname A surname of a user.
     * @param mobileNumber A mobile number of a user (+44 ...).
     * @param address1 An address line 1 of a user.
     * @param address2 An address line 2 of a user.
     * @param city A residing city of a user.
     * @param postcode A postcode of a user.
     * @param profilePicture A string of a file path for a user profile 
     * 					     picture.
     * @param fine An amount fined for a user in GBP (£).
     * @param staffID The unique ID of a staff member.
     * @param employmentDate The date a librarian joined the staff team
     *                       (YYYY-MM-DD).
     */
	public Librarian(String username, String firstName, String surname, 
			String mobileNumber, String address1, String address2,
			String city, String postcode, String profilePicture,
            double fine, int staffID, String employmentDate) {
		super(username, firstName, surname, mobileNumber, address1,
                address2, city, postcode, profilePicture, fine);
		this.staffID = staffID;
		this.employmentDate = employmentDate;
	}
	
	/**
     * Gets the unique staffID of a staff member.
     * @return Returns a unique staff ID for the staff member.
     */
    public int getStaffID() {
            return staffID;
    }
    
    /**
     * Sets the unique staffID of a staff member.
     * @param staffID The ID of the staff member.
     */
    public void setStaffID(int staffID) {
            this.staffID = staffID;
    }

    /**
     * Gets the employment date of the staff member (YYYY-MM-DD).
     * @return returns a starting date (YYYY-MM-DD).
     */
    public String getEmploymentDate() {
            return employmentDate;
    }

    /**
     * Sets the employment date of a staff member (YYYY-MM-DD).
     * @param employmentDate Takes a date as input (YYYY-MM-DD).
     */
    private void setEmploymentDate(String employmentDate) {
            this.employmentDate = employmentDate;
    }
}
