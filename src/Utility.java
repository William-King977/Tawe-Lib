import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Utility class holds commonly used methods / functions that are used 
 * within different classes such as validation etc.
 * @author William King
 */
public class Utility {
	
	/** A regular expression that holds a dash and, lower and 
	 * upper case letters. */
    private static String alphabet = "[a-zA-Z-]+";
	
	/**
	 * An alert pop-up that tells the user that the username entered
	 * does not exist in the system.
	 */
	public static void userNotExist() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: User does not exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username entered does not exist in the "
				+ "system. Please try again.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have not
	 * filled in all the required fields. 
	 */
	public static void missingFields() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Missing required fields.");
		alert.setHeaderText(null);
		alert.setContentText("Please fill in all the "
				+ "required fields.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-alphabetic character in them (when they shouldn't).
	 */
	public static void nonAlphaError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-alphabetic characters "
				+ "detected in fields.");
		alert.setHeaderText(null);
		alert.setContentText("You cannot have non-alphabetic "
				+ "characters in the "
				+ "First Name, Surname or City fields.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the changes
	 * made to the user has been saved.
	 */
	public static void savedUserChanges() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this user"
				+ " has been saved successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user / staff that the changes
	 * made to the librarian has been saved.
	 */
	public static void savedLibrarianChanges() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this librarian"
				+ " has been saved successfully.");
		alert.showAndWait();
	}
	
	/**
     * Checks if all the required fields have data in them.
     * @param firstName The entered first name.
     * @param surname The entered surname.
     * @param mobileNumber The entered mobile number.
     * @param address1 The entered address line 1.
     * @param city The entered city.
     * @param postcode The entered postcode.
     * @return Whether all the required fields are filled or not.
     */
    public static boolean isFieldFilled(String firstName, String surname, 
    		String mobileNumber, String address1, String city, 
    		String postcode) {
    	//If any of the required fields are empty. 
        if (firstName.isEmpty() || surname.isEmpty() || 
        		mobileNumber.isEmpty() || address1.isEmpty() || 
        		city.isEmpty() || postcode.isEmpty()) {
        	return false;
        //If all required fields are filled.
        } else {
        	return true;
        }
    }
    
    /**
     * Checks if the passed down fields only contain letters or dashes.
     * @param firstName The entered first name.
     * @param surname The entered surname.
     * @return Whether the passed down fields have has only letters and 
     *         dashes or not.
     */
    public static boolean isAlpha(String firstName, String surname, String city) {
    	//If the selected fields has only letters or dashes.
	    if (firstName.matches(alphabet) && surname.matches(alphabet) 
	    		&& city.matches(alphabet)) {
	    	return true;
	    //If any of the fields that SHOULD NOT contain non-alphabetical
		//have non-alphabetical characters
	    } else {
	    	return false;
	    }
    }

}
