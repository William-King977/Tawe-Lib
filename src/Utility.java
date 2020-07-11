import java.util.ArrayList;

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
		alert.setTitle("Error: User Does Not Exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username entered does not exist in the "
				+ "system. Please try again. Remember to click the check "
				+ "box if you are a librarian.");	
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
	 * have non-alphabetic character in them (when they shouldn't).
	 */
	public static void nonAlphaError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Alphabetic Characters "
				+ "Detected In Fields.");
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
		alert.setContentText("The changes made to this user "
				+ "has been saved successfully.");
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
		alert.setContentText("The changes made to this librarian "
				+ "has been saved successfully.");
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
	 * An alert pop-up that tells the user that their profile
	 * picture has been saved.
	 */
	public static void profilePictureCreated() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Profile Picture Created.");
		alert.setHeaderText(null);
		alert.setContentText("The profile picture has been created "
				+ "successfully. Select it from your profile "
				+ "if you want to use it as your profile picture.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they
	 * haven't selected a user to edit.
	 */
	public static void userNotSelected() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Edit User.");
		alert.setHeaderText(null);
		alert.setContentText("Please click a "
				+ "checkbox to display "
				+ "the users you want to view / edit. "
				+ "If you have, you must select a "
				+ "user to edit their details.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that
	 * they can't edit other librarian's profiles.
	 */
	public static void invalidStaffEdit() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Edit Librarian.");
		alert.setHeaderText(null);
		alert.setContentText("You are not allowed to edit "
				+ "other librarian's profiles. Only your own "
				+ "profile or other regular users.");
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
	 * An alert pop-up that tells the user that the user has 
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
    	// If any of the required fields are empty. 
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
    	// If the selected fields has only letters or dashes.
	    if (firstName.matches(alphabet) && surname.matches(alphabet) 
	    		&& city.matches(alphabet)) {
	    	return true;
	    // If any of the fields that SHOULD NOT contain non-alphabetical
		// have non-alphabetical characters.
	    } else {
	    	return false;
	    }
    }
    
    /**
     * Checks if the entered username is the same with any of the current 
     * users registered in the system.
     * @param userList The list of all registered users.
     * @param librarianList The list of all registered librarians.
     * @param username The username entered.
     * @return Whether the username entered exists in the system or not.
     */
    public static boolean isUsernameExist(ArrayList<User> userList, 
    		ArrayList<Librarian> librarianList, String username) {
		// Checks if any of the librarians have the same username.
        for (User thisLibrarian : librarianList) {
        	if ((thisLibrarian.getUsername()).equals(username)) { 
        		return true;
        	}
        }
        // Checks if any of the members have the same username.
        for (User thisUser : userList) {
        	if ((thisUser.getUsername()).equals(username)) {
        		return true;
        	}
        }
        // The entered username doesn't exist in the system.
        return false;
    }
}
