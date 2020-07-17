/**
 * The Request class models a request made for borrowing a copy of 
 * a resource. 
 * @author William King
 */
public class Request {
	
	/** The unique ID of the request made. */
	private int requestID;
	
	/** The ID of the requested copy. */
	private int copyID;
	
	/** The ID of the resource of the requested copy. */
	private int resourceID;
	
	/** The username of the user who made the request. */
	private String username;
	
	/** The date the request was made in format YYYY-MM-DD. */
	private String requestDate;
	
	/** A boolean to hold a value whether the request has been filled or not. */
	private Boolean requestFilled;
	
	/**
	 * Constructor for the Request class.
	 * @param requestID The unique ID for the request.
	 * @param copyID The ID of the requested copy.
	 * @param resourceID The ID of the resource copy.
	 * @param username The ID of the user who made the request.
	 * @param requestDate The date that the request was made.
	 * @param requestFilled Whether the request copy has been given to
	 *                      the user or not.
	 */
	public Request(int requestID, int copyID, int resourceID, String username,
			String requestDate, Boolean requestFilled) {
		this.requestID = requestID;
		this.requestDate = requestDate;
		this.requestFilled = requestFilled;
		this.resourceID = resourceID;
		this.username = username;
		this.copyID = copyID;
	}
	
	/**
	 * Gets the ID of the request.
	 * @return The ID of the request.
	 */
	public int getRequestID() {
		return requestID;
	}
	
	/**
	 * Gets the ID of the requested copy.
	 * @return The ID of the request copy.
	 */
	public int getCopyID() {
		return copyID;
	}
	
	/**
	 * Gets the ID of the resource copy.
	 * @return The ID of the resource copy.
	 */
	public int getResourceID() {
		return resourceID;
	}

	/**
	 * Gets the username of the user who made the request.
	 * @return The user's username.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the date that the request was made.
	 * @return The date when the request was made.
	 */
	public String getRequestDate() {
		return requestDate;
	}

	/**
	 * Gets whether the request has been fulfilled or not.
	 * @return Whether the request copy has been given to
	 *         the user or not.
	 */
	public Boolean getRequestFilled() {
			return requestFilled;
	}

	/**
	 * Sets whether the request has been fulfilled or not.
	 * @param requestFilled Whether the request has been fulfilled or not.
	 */
	public void setRequestFilled(Boolean requestFilled) {
			this.requestFilled = requestFilled;
	}
}
