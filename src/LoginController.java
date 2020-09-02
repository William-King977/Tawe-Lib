import java.io.IOException;
import java.util.LinkedHashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Login page.
 * Handles actions performed by the login page.
 * This includes checking users and setting up any children windows.
 * @author William King
 */
public class LoginController {
	/** A linked hashmap to hold all current users. */
	LinkedHashMap<String, User> users;
	/** A linked hashmap to hold all current librarians. */
	LinkedHashMap<String, Librarian> librarians;
	/** fxID for the username text field */
	@FXML private TextField txtUsername;
	/** fxID for the staff check box */
	@FXML private CheckBox cbStaff;
	/** fxID for the login button */
	@FXML private Button btnLogin;
	
	/**
	 * Sets up the linked hashmaps for both user types.
	 * This method will run automatically.
	 */
	public void initialize() {
		users = FileHandling.getUsers();
		librarians = FileHandling.getLibrarians();
	}
	
	/**
	 * Validates the existence of the entered username, then determine
	 * which dashboard to show.
	 */
	public void handleLoginButtonAction() {
		String username = txtUsername.getText().trim();
		boolean userFound;
		int userType;
		
		// Check whether the username exists.
		if (cbStaff.isSelected()) {
			userFound = isUserExist(username, 1);
			userType = 1;
		} else {
			userFound = isUserExist(username, 2);
			userType = 2;
		}
		
		// Carry out appropriate actions based on username existence,
		if (!userFound) {
			Utility.userNotExist(username);
		} else {
			// Show dashboard and stores current user.
			FileHandling.setCurrentUser(username);
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
		switch (userType) {
			// Create linked hashmap of users/librarians, then search it.
			case 1:
				// Search for librarian.
				if (librarians.containsKey(username)) {
					return true;
				}
				break;
			case 2:
				// Search for user.
				if (users.containsKey(username)) {
					return true;
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
		String dashboardTitle = "";
		// Closes the window.
		Stage stage = (Stage) btnLogin.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = null;
			
			// Show appropriate dashboard based on the user type.
			switch (userType) {
				case 1:
					// Show staff dashboard.
					root = FXMLLoader.load(getClass()
							.getResource("FXMLFiles/UserDashboardStaff.fxml"));
					dashboardTitle = "Staff Dashboard";
					break;
				case 2:
					// Show user dashboard.	
					root = FXMLLoader.load(getClass()
							.getResource("FXMLFiles/UserDashboard.fxml"));
					dashboardTitle = "User Dashboard";
					break;
			}
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(dashboardTitle);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			//Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
}
