import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
		//System.out.println("Yes");
		
		//Add this to isUser method.
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: User does not exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username entered does not exist in the "
				+ "system. Please try again.");	
		alert.showAndWait();
	}
	
	/**
	 * Checks if a user exists in the system.
	 * @param username The username.
	 * @param userType An integer value to indicate whether the user is a 
	 * 				   librarian or a regular user.
	 * @return True if the user exists, otherwise false.
	 */
	private boolean isUser(String username, int userType) {
		boolean userExists = false;
		return userExists;
	}
	
	/**
	 * Sets up the required settings for the dashboard and displays
	 * the appropriate one depending on the usertype. 
	 * @param userType Integer value indicating the type of 
	 * 				   user (librarian / user).
	 */
	private void showDashboard(int userType) {
		
	}
}
