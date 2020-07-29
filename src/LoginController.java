import java.io.IOException;
import java.util.ArrayList;
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
	
	/** fxID for the username test field */
	@FXML private TextField txtUsername;
	
	/** fxID for the staff check box */
	@FXML private CheckBox cbStaff;
	
	/** fxID for the login button */
	@FXML private Button btnLogin;
	
	/**
	 * Handles a button event for the login button e.g. button press.
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
			// Create arrayList of users / librarians, then search it.
			case 1:
				// Search for librarian.
				ArrayList<Librarian> librarians = FileHandling.getLibrarians();
				for(Librarian librarian : librarians) {
					if(librarian.getUsername().equals(username)) {
						return true;
					}
				}
				break;
			case 2:
				// Search for user.
				ArrayList<User> users = FileHandling.getUsers();
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
		final String DASHBOARD_TITLE;
		// Closes the window.
		Stage stage = (Stage) btnLogin.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root;
			
			// Show appropriate dashboard based on the user type.
			if (userType == 1) {
				// Show staff dashboard.
				root = FXMLLoader.load(getClass()
						.getResource("FXMLFiles/UserDashboardStaff.fxml"));
				DASHBOARD_TITLE = "Staff Dashboard";
					
			} else {
				// Show user dashboard.	
				root = FXMLLoader.load(getClass()
						.getResource("FXMLFiles/UserDashboard.fxml"));
				DASHBOARD_TITLE = "User Dashboard";
			}
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(DASHBOARD_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException ex) {
			//Catches an IO exception such as that where the FXML
            // file is not found.
            ex.printStackTrace();
		}
	}
}
