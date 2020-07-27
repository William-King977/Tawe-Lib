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
	
	/** A button that leads to the New Loan page. */
	@FXML private Button btnNewLoan;
	/** A button that leads to the View Loans page. */
	@FXML private Button btnViewLoans;
	/** A button that leads to the Resource Settings page. */
	@FXML private Button btnResourceSettings;
	/** A button that leads to the Copy Settings page. */
	@FXML private Button btnCopySettings;
	/** A button that leads to the User Settings page. */
	@FXML private Button btnUserSettings;
	/** A button that leads to librarian Pay User Fine page. */
	@FXML private Button btnPayUserFine;
	/** The log out button for the dashboard. */
	@FXML private Button btnLogout;
	
	/**
	 * Displays a list of all returned loans and a list of all
	 * current loans in the system when clicked.
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleViewLoansButtonAction() throws IOException {
		Stage curStage = (Stage) btnViewLoans.getScene().getWindow(); 
		curStage.close(); // Closes current stage.
	    // Creates the new stage.
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/ViewLoan.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
	
	/**
	 * Displays the New Loan page that allows the librarian to
	 * issue a new loan when clicked.
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleNewLoanButtonAction() throws IOException {
		Stage curStage = (Stage) btnNewLoan.getScene().getWindow(); 
		curStage.close(); // Closes current stage.
	    // Creates the new stage.
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/NewLoan.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
	
	/**
	 * Displays a list of all resources in the library when clicked. Also allows
	 * librarians to create a new resource or edit the details of an existing
	 * resource.
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleResourceSettingsButtonAction() throws IOException {
		Stage curStage = (Stage) btnResourceSettings.getScene().getWindow(); 
		curStage.close(); //closes that stage.
	    /*creates the new stage*/
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/ResourceSettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
	
	/**
	 * Displays a list of all copies of each resource in the library 
	 * when clicked. Also, allows the viewing of overdue copies and 
	 * a copy's borrow/return history.
	 * @throws IOException Throws an exception to be caught when the 
	 * 		   			   FXML file cannot be accessed.
	 */
	public void handleCopySettingsButtonAction() throws IOException {
		Stage curStage = (Stage) btnCopySettings.getScene().getWindow(); 
		curStage.close(); 
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/CopySettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	/**
	 * Goes to a page where the librarian can view all users registered to
	 * the library system as well as creating a new user or editing 
	 * the details of an existing user. 
	 * @throws IOException Throws an exception to be caught when the 
	 * 		   			   FXML file cannot be accessed.
	 */
	public void handleUserSettingsButtonAction() throws IOException {
		Stage curStage = (Stage) btnUserSettings.getScene().getWindow(); 
		curStage.close(); 
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserSettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); 
	}
	
	/**
	 * Goes to a page where the librarian can pay users' fines.
	 * @throws IOException Throws an exception to be caught when the 
	 * 		   			   FXML file cannot be accessed.
	 */
	public void handlePayFineButtonAction() throws IOException {
		Stage curStage = (Stage) btnPayUserFine.getScene().getWindow(); 
		curStage.close(); 
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/PayUserFine.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
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