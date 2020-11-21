package data;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * A class to hold all alert messages in one place.
 * @author William King
 */
public class Alerts {
	/**
	 * An alert pop-up that tells the user that the username entered
	 * does not exist in the system.
	 * @param username The entered username which doesn't exist in the system.
	 */
	public static void userNotExist(String username) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: User Does Not Exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username '" + username + "' does not exist "
				+ "in the system. Please try again. Remember to click the "
				+ "check box if you are a librarian.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have not
	 * filled in all the required fields. 
	 */
	public static void missingFields() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Missing Required Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Please fill in all the "
				+ "required fields.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-alphabetic characters in them.
	 */
	public static void nonAlphaError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Alphabetic Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-alphabetic "
				+ "characters have been detected in the input fields, "
				+ "when they shouldn't be there.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the postcode
	 * field has invalid characters.
	 */
	public static void invalidPostcode() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Invalid Postcode.");
		alert.setHeaderText(null);
		alert.setContentText("Invalid characters has been detected in the "
				+ "postcode field. There should only be capital letters and "
				+ "numbers.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-integer characters in them.
	 */
	public static void nonIntegerError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Integer Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-integer characters have been detected in "
				+ "the input fields, when they shouldn't be there.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-double characters in them.
	 */
	public static void nonDoubleError() {
		//Displays an alert message
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Double Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-double characters have been detected in "
				+ "the input fields, when they shouldn't be there.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the changes
	 * made to the user have been saved.
	 * @param userType The type of user being edited.
	 */
	public static void savedUserChanges(String userType) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this " + userType
				+ " have been saved successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the changes
	 * made to the resource have been saved.
	 */
	public static void savedResourceChanges() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this resource "
				+ "have been saved successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the
	 * loan has been returned.
	 */
	public static void loanReturned() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Loan Returned.");
		alert.setHeaderText(null);
		alert.setContentText("This loan has been returned.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the file textbox
	 * is empty.
	 */
	public static void fileNameEmpty() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: File Name Field Is Empty.");
		alert.setHeaderText(null);
		alert.setContentText("Please enter a name for "
				+ "your designed profile picture.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the file name
	 * already exists.
	 */
	public static void fileNameExists() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: File Name Exists.");
		alert.setHeaderText(null);
		alert.setContentText("The file name you have chosen already "
				+ "exists for an image. Please enter another.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that
	 * the username entered already exists in the system.
	 */
	public static void usernameExists() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: This Username Exists.");
		alert.setHeaderText(null);
		alert.setContentText("An existing user has the same "
				+ "username, please enter a different one.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the librarian that 
	 * the changes made causes the resource to have matching
	 * details with an existing resource.
	 */
	public static void resourceExistsEdit() {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Save Changes.");
		alert.setHeaderText(null);
		alert.setContentText("The changes you have made to "
				+ "this resource matches with the details of "
				+ "an existing resource.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that 
	 * the details for the resource matches with an existing resource.
	 */
	public static void resourceExistsCreate() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Create Resource.");
		alert.setHeaderText(null);
		alert.setContentText("The resource you have created "
				+ "already exists in the system.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that 
	 * they have not selected an image thumbnail for the resource.
	 */
	public static void imageNotSelected() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Create Resource.");
		alert.setHeaderText(null);
		alert.setContentText("Please select a thumbnail "
				+ "image for the resource.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the user has 
	 * been created successfully.
	 */
	public static void userCreated() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User Created Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user has been created successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the resource has 
	 * been created successfully.
	 */
	public static void resourceCreated() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resource Created Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The resource has been created successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that a request for the
	 * selected resource has been made and shows the appropriate
	 * follow-up message.
	 * @param requestType The type of request made (reserved/queue).
	 */
	public static void requestCreated(String requestType) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Request Made.");
		alert.setHeaderText(null);
		
		switch (requestType) {
			case "Reserved":
				alert.setContentText("The request for a copy of the resource "
						+ "has been made and has been reserved for you until "
						+ "a librarian has issued a copy to you.");
				break;
			case "Queue":
				alert.setContentText("There are no available copies for the "
						+ "resource. However, the request for a copy of the "
						+ "resource has been made and has been added to the "
						+ "request queue.");
				break;
		}
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the loan for
	 * a requested copy has been created.
	 */
	public static void loanCreated() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Loan Made.");
		alert.setHeaderText(null);
		alert.setContentText("The loan for this requested copy "
				+ "has been created successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian the fine
	 * payment has been successful.
	 */
	public static void paymentMade() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Payment Made.");
		alert.setHeaderText(null);
		alert.setContentText("The payment for this user has been processed "
				+ "successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have outstanding fines
	 * when requesting to borrow a resource.
	 */
	public static void outstandingFines() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Request Resource.");
		alert.setHeaderText(null);
		alert.setContentText("You have outstanding fines. Please "
				+ "pay them before requesting to borrow a resource.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have overdue copies
	 * when requesting to borrow a resource.
	 */
	public static void overdueCopies() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Request Resource.");
		alert.setHeaderText(null);
		alert.setContentText("You have overdue copies. Please "
				+ "return them before requesting to borrow a resource.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have already requested
	 * for a copy of a resource or if they are currently borrowing a copy. 
	 */
	public static void alreadyRequested() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Request Resource.");
		alert.setHeaderText(null);
		alert.setContentText("You have either already requested to borrow a "
				+ "copy of this resource or you are currently borrowing a "
				+ "copy of this resource.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have not selected
	 * a language when clicking the remove button.
	 */
	public static void languageNotSelected() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Remove Language.");
		alert.setHeaderText(null);
		alert.setContentText("Please select the language "
				+ "that you want to remove.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that there are
	 * no languages left to remove from the list view.
	 */
	public static void languageListEmpty() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Remove Language.");
		alert.setHeaderText(null);
		alert.setContentText("The list is empty. There are no "
				+ "more languages to remove.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have not entered
	 * a language when clicking the add button.
	 */
	public static void languageNotEntered() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Add Language.");
		alert.setHeaderText(null);
		alert.setContentText("Enter a language "
				+ "that you want to add.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have entered
	 * a language that already exists in the list view.
	 */
	public static void languageExists() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Add Language.");
		alert.setHeaderText(null);
		alert.setContentText("The language you've entered "
				+ "already exists for the DVD.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that they
	 * have not entered a payment.
	 */
	public static void noEnteredPayment() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Make Payment.");
		alert.setHeaderText(null);
		alert.setContentText("Please enter the payment you "
				+ "want to make for the user.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the entered
	 * payment is less than 1p.
	 */
	public static void paymentTooLow() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Make Payment.");
		alert.setHeaderText(null);
		alert.setContentText("The payment you have entered is less than 1p, "
				+ "please enter a payment of at least 0.01 (1p).");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the entered
	 * payment is larger than the selected user's outstanding fine.
	 */
	public static void paymentTooHigh() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Make Payment.");
		alert.setHeaderText(null);
		alert.setContentText("The payment you entered is larger than "
				+ "this user's fine. If you want to pay the whole fine, then "
				+ "please enter the user's exact fine.");
		alert.showAndWait();
	}
}