import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
		// Constants set for the new window.
		final String DASHBOARD_TITLE = "User Dashboard";
		final int DASHBOARD_HEIGHT = 410;
		final int DASHBOARD_WIDTH = 455;
		
		// Sets up a new FXML loader.
		FXMLLoader fxmlLoader;
		
		try {
			if(userType == 1) {
				// Show staff dashboard.
				Stage curStage = (Stage) btnLogin.getScene().getWindow(); // refers to current stage.
				fxmlLoader = new FXMLLoader(getClass()
						.getResource("FXMLFiles/UserDashboardStaff.fxml"));
					
			} else {
				// Show user dashboard.	
				Stage curStage = (Stage) btnLogin.getScene().getWindow(); // refers to current stage.
				fxmlLoader = new FXMLLoader(getClass()
						.getResource("FXMLFiles/UserDashboard.fxml"));
			}
	
			// Creates a new boarder pane.
    		BorderPane editRoot = (BorderPane)fxmlLoader.load();

            // Sets the scene incl, width and height.
    		Scene editScene = new Scene(editRoot, DASHBOARD_WIDTH, 
    				DASHBOARD_HEIGHT); 
            // Creates a new stage.
            Stage editStage = new Stage();
            // sets the scene to the stage.
            editStage.setScene(editScene);
            // sets the stage title.
            editStage.setTitle(DASHBOARD_TITLE);
          
            // Sets modality which prevents any other window being
            // used (In the app) until this one is closed.
            editStage.initModality(Modality.APPLICATION_MODAL);
            //Shows the window.
            editStage.showAndWait();
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
		}
	}
}
