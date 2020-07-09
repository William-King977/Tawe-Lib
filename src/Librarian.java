/**
 * The Librarian class models a librarian user in the system. A librarian is
 * any user who works at TaweLib and has extended permissions to manipulate
 * users and resources in the system.
 * @author William King
 */
public class Librarian extends User {
	
	/** A unique ID for each librarian */
	private int staffID;
	
	public Librarian(String username, int staffID) {
		super(username);
		this.staffID = staffID;
	}
}
