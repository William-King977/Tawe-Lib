package data;

/**
 * The User class models any user that is created or existing in the system.
 * A user would be anyone who is a librarian or a regular user.
 * @author William King
 */
public class User {
	
	/** A unique name of a user. */
	protected String username;
	
	/** A first name of a user. */
	protected String firstName;
	
	/** A surname of a user. */
	protected String surname;
	
	/** A UK mobile number of a user (+44 ...). */
	protected String mobileNumber;
	
	/** An address line 1 of a user. */
	protected String address1;
	/** An address line 2 of a user. */
	protected String address2;
	
	/** A residing city of a user. */
	protected String city;
	
	/** A postcode of a user. */
	protected String postcode;
	
	/** A string of a file path for a user profile picture. */
	protected String profilePicture;
	
	/**A fine amount of a user in GBP (£).*/
	protected double fine;
	
	/**
	 * Constructor for the user class.
	 * @param username A unique name of a user.
	 * @param firstName A first name of a user.
	 * @param surname A surname of a user.
	 * @param mobileNumber A mobile number of a user (+44 ...).
	 * @param address1 An address line 1 of a user.
	 * @param address2 An address line 2 of a user.
	 * @param city A residing city of a user.
	 * @param postcode A post code of a user.
	 * @param profilePicture A string of a file path for a user profile 
	 *                       picture.
	 * @param fine An amount fined for a user in GBP (£).
	 */
	public User(String username, String firstName, String surname, 
			String mobileNumber, String address1, String address2, 
			String city, String postcode, String profilePicture, 
			double fine) {
		this.username = username;
		this.firstName = firstName;
		this.surname = surname;
		this.mobileNumber = mobileNumber;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.postcode = postcode;
		this.profilePicture = profilePicture;
		this.fine = fine;
	}
	
	/**
	 * Gets a string of the User's full details for file saving.
	 * @return String of the User's full details.
	 */
	public String toStringDetail() {
		String strUser = username + "," + firstName + "," + surname + 
				"," + mobileNumber + "," + address1 + "," + address2 + 
				"," + city + "," + postcode + "," + profilePicture + 
				"," + fine + ",";
		return strUser;
	}
	
	/**
	 * Gets the username of a user.
	 * @return The user's username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username for a user.
	 * @param username Takes the username of a user.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets a first name of a user.
	 * @return Returns a user's first name.
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name of a user.
	 * @param firstName Takes a user's first name.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets a user's surname.
	 * @return Returns a user's surname.
	 */
	public String getSurname() {
		return surname;
	}
	
	/**
	 * Sets a user's surname.
	 * @param surname Takes a user's surname as input.
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	/**
	 * Gets a user's mobile number (+44 ...).
	 * @return Returns a user's mobile number (+44 ...).
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	/**
	 * Sets a user's mobile number (+44 ...).
	 * @param mobileNumber Takes a user's mobile number (+44 ...).
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	/**
	 * Gets a user's address line 1.
	 * @return Returns a user's address line 1.
	 */
	public String getAddress1() {
		return address1;
	}
	
	/**
	 * Sets a user's address line 1.
	 * @param address1 Takes a user's address line 1 as an input.
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	/**
	 * Gets a user's address line 2.
	 * @return Returns a user's address line 2.
	 */
	public String getAddress2() {
		return address2;
	}
	
	/**
	 * Sets a user's address line 2.
	 * @param address2 Takes a user's address line 2 as input.
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	/**
	 * Gets a user's residing city.
	 * @return Returns a user's residing city.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets a user's residing city.
	 * @param city Takes a user's residing city as input.
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets a user's post code.
	 * @return Returns a user's post code.
	 */
	public String getPostcode() {
		return postcode;
	}
	
	/**
	 * Sets a user's post code.
	 * @param postcode Takes a post code as an input.
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	/**
	 * Gets a string of an image location.
	 * @return Returns a file location as a string.
	 */
	public String getProfilePicture() {
		return profilePicture;
	}
	
	/**
	 * Sets an image file location as a string.
	 * @param profilePicture Takes a location as a string as input.
	 */
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	/**
	 * Gets a total for a fine of a user (GBP £).
	 * @return Returns the total fine of a user (GBP £).
	 */
	public double getFine() {
		return fine;
	}
	
	/**
	 * Sets the total fine of a user (GBP £).
	 * @param fine Takes the fine as an input (GBP £).
	 */
	public void setFine(double fine) {
		this.fine = fine;
	}
	
	/**
	 * Gets a short description of the user that's suitable to display.
	 * @return A short description of the user.
	 */
	public String getDescription() {
		return "Username: " + username + " | First Name: " + 
				firstName + " | Surname: " + surname;
	}
}