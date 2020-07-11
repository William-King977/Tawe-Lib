import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the User Dashboard Staff page.
 * Handles actions performed by the librarian on the User Dashboard Staff page.
 * @author William King
 */
public class UserDashboardStaffController {
	
	/** A button that leads to the User Search page. */
	@FXML private Button btnSearch;
	
	/** A button that leads to the New Loan page. */
	@FXML private Button btnNewLoan;
	
	/** A button that leads to the View Loans page. */
	@FXML private Button btnViewLoans;
	
	/** A button that leads to the View Resources page. */
	@FXML private Button btnViewResources;
	
	/** A button that leads to the View Copies page. */
	@FXML private Button btnViewCopies;
	
	/** A button that leads to the User Settings page. */
	@FXML private Button btnUserSettings;
	
	/** A button that leads to librarian Pay User Fine page. */
	@FXML private Button btnPayUserFine;
	
	/** The log out button for the dashboard. */
	@FXML private Button btnLogout;

	/**
	 * Closes the current page and navigates to the user search page.
	 */
	public void handleSearchesButtonAction() {
	}
	
	/**
	 * Displays a list of all returned loans and a list of all
	 * current loans in the system when clicked.
	 */
	public void handleViewLoansButtonAction() {
	}
	
	/**
	 * Displays the New Loan page that allows the librarian to
	 * issue a new loan when clicked.
	 */
	public void handleNewLoanButtonAction() {
	}
	
	/**
	 * Displays a list of all resources in the library when clicked.
	 */
	public void handleViewResourcesButtonAction() {
	}
	
	/**
	 * Displays a list of all copies of each resource in the library 
	 * when clicked.
	 */
	public void handleViewCopiesButtonAction() {
	}
	
	/**
	 * Goes to a page where the librarian can view all users registered to
	 * the library system.
	 * @throws IOException Throws an exception to be caught when the 
	 * 		   			   FXML file cannot be accessed.
	 */
	public void handleUserSettingsButtonAction() throws IOException {
		Stage curStage = (Stage) btnUserSettings.getScene().getWindow(); 
		curStage.close(); //closes current stage.
	    /*creates the new stage*/
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserSettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
	}
	
	/**
	 * Goes to a page where the librarian can pay users' fine
	 */
	public void handlePayFineButtonAction() {
	}
	
	/**
	 * Goes back to the Login page when the button is clicked.
	 */
	public void handleLogoutButtonAction() throws IOException {
		// Closes the window.
		Stage stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}	
}