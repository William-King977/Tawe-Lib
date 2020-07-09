import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Controller for the Login page.
 * Handles actions performed by the login page.
 * This includes checking users and setting up any children windows.
 * @author William King
 */
public class LoginController {
	
	/** fxID for the username test field */
	@FXML private TextField txtUsername;
	
	/** fxID for the staff check box */
	@FXML private CheckBox cbStaff;
	
	/** fxID for the login button */
	@FXML private Button btnLogin;
	
	/**
	 * Handles a button event for the login button e.g. button press.
	 * @param event Passes the event / action of a button press.
	 */
	public void handleLoginButtonAction(ActionEvent event) {
		String username = (txtUsername.getText()).trim();
		boolean userFound;
		int userType;
		
		if(cbStaff.isSelected()) {
			userFound = isUserExist(username, 1);
			userType = 1;
		} else {
			userFound = isUserExist(username, 2);
			userType = 2;
		}
		
		if(!userFound) {
			Utility.userNotExist();
		} else {
			// Show dashboard.
			showDashboard(userType);
		}
	}
	
	/**
	 * Checks if a user exists in the system.
	 * @param username The username.
	 * @param userType An integer value to indicate whether the user is a 
	 * 				   librarian or a regular user.
	 * @return True if the user exists, otherwise false.
	 */
	private boolean isUserExist(String username, int userType) {
		switch(userType) {
			// Create arrayList of users / librarians, then search it.
			case 1:
				// Search for librarian.
				ArrayList<Librarian> librarians = FileReader.getLibrarians();
				for(Librarian librarian : librarians) {
					if(librarian.getUsername().equals(username)) {
						return true;
					}
				}
				break;
			case 2:
				// Search for user.
				ArrayList<User> users = FileReader.getUsers();
				for(User user : users) {
					if(user.getUsername().equals(username)) {
						return true;
					}
				}
				break;
		}
		return false;
	}
	
	/**
	 * Sets up the required settings for the dashboard and displays
	 * the appropriate one depending on the usertype. 
	 * @param userType Integer value indicating the type of 
	 * 				   user (librarian / user).
	 */
	private void showDashboard(int userType) {
		System.out.println("You reached the dashboard with userType: " + userType);
	}
}
