import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Pay User Fine page.
 * Librarians can pay a fine (partially or fully) for a user.
 * @author William King
 */
public class PayUserFineController {
	
	/** ArrayList to hold all users. */
	private ArrayList<User> userList;
	/** ArrayList to hold users who currently have outstanding fines. */
	private ArrayList<User> finedUsers = new ArrayList<>();
	
	/** List view that holds users that have fines to pay off. */
    @FXML private ListView<String> lstFinedUsers;

    /** Represents the textbox holding the fined user's username. */
    @FXML private TextField txtUsername;

    /** Represents the textbox holding the user's current fines. */
    @FXML private TextField txtCurrentFines;

    /** Represents the textbox holding the amount to pay off. */
    @FXML private TextField txtPayment;
    
    /** Models the pay fine button. */
    @FXML private Button btnPayFine;

    /** Models the back button. */
    @FXML private Button btnBack;
    
    /**
	 * Displays the user's who currently have outstanding fines.
	 * This method will run automatically.
	 */
	public void initialize() {
	}
	
	/**
	 * Shows the selected user's username and current fines.
	 */
	public void displayUserDetails() {
	}
	
	/**
	 * Pays off the user's fine based on the amount entered.
	 */
	public void handlePayFineButtonAction() {
	}
	
	/**
	 * Goes back to the previous page when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the 
	 *         FXML file isn't available.
	 */
	public void handleBackButtonAction() throws IOException {
		//Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserDashboardStaff.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
}
