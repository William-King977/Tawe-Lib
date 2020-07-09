/**
 * The User class models any user that is created or existing in the system.
 * A user would be anyone who is a librarian or a regular user.
 * @author William King
 */
public class User {
	
	/** A unique name of a user. */
	protected String username;
	
	public User(String username) {
		this.username = username;
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
	

}
