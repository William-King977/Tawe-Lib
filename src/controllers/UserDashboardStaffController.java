package controllers;

import java.io.IOException;

import data.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the User Dashboard Staff page.
 * Handles actions performed by the librarian on the User Dashboard Staff page.
 * @author William King
 */
public class UserDashboardStaffController {
	/** Title for the View Loans page. */
	private final String VIEW_LOANS_TITLE = "View Loans";
	/** Title for the New Loan page. */
	private final String NEW_LOAN_TITLE = "New Loan";
	/** Title for the Resource Settings page. */
	private final String RESOURCE_SETTINGS_TITLE = "Resource Settings";
	/** Title for the Copy Settings page. */
	private final String COPY_SETTINGS_TITLE = "Copy Settings";
	/** Title for the User Settings page. */
	private final String USER_SETTINGS_TITLE = "User Settings";
	/** Title for the Pay User Fine page. */
	private final String PAY_FINE_TITLE = "Pay User Fine";
	/** Title for the Login page. */
	private final String LOGIN_TITLE = "TaweLib: Library System";
	
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
	 */
	public void handleViewLoansButtonAction() {
		Stage curStage = (Stage) btnViewLoans.getScene().getWindow(); 
		curStage.close(); // Closes current stage.
		
		try {
			// Creates the new stage.
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "ViewLoan.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(VIEW_LOANS_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Displays the New Loan page that allows the librarian to
	 * issue a new loan when clicked.
	 */
	public void handleNewLoanButtonAction() {
		Stage curStage = (Stage) btnNewLoan.getScene().getWindow(); 
		curStage.close(); // Closes current stage.
		
		try {
			// Creates the new stage.
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "NewLoan.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(NEW_LOAN_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Displays a list of all resources in the library when clicked. Also allows
	 * librarians to create a new resource or edit the details of an existing
	 * resource.
	 */
	public void handleResourceSettingsButtonAction() {
		Stage curStage = (Stage) btnResourceSettings.getScene().getWindow();
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "ResourceSettings.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(RESOURCE_SETTINGS_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Displays a list of all copies of each resource in the library 
	 * when clicked. Also, allows the viewing of overdue copies and 
	 * a copy's borrow/return history.
	 */
	public void handleCopySettingsButtonAction() {
		Stage curStage = (Stage) btnCopySettings.getScene().getWindow();
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "CopySettings.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(COPY_SETTINGS_TITLE);
			primaryStage.show(); 
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Goes to a page where the librarian can view all users registered to
	 * the library system as well as creating a new user or editing 
	 * the details of an existing user. 
	 */
	public void handleUserSettingsButtonAction() {
		Stage curStage = (Stage) btnUserSettings.getScene().getWindow();
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "UserSettings.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(USER_SETTINGS_TITLE);
			primaryStage.show(); 
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Goes to a page where the librarian can pay users' fines.
	 */
	public void handlePayFineButtonAction() throws IOException {
		Stage curStage = (Stage) btnPayUserFine.getScene().getWindow();
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "PayUserFine.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(PAY_FINE_TITLE);
			primaryStage.show();
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Closes this page, then goes back to the Login page.
	 */
	public void handleLogoutButtonAction() {
		// Closes the window.
		Stage stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "Login.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(LOGIN_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
}