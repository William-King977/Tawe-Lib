import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the Current Balance page.
 * The user can view their current balance in this page.
 * @author William King
 */
public class CurrentBalanceController {
	
	/** ArrayList to hold all the user. */
	private ArrayList<User> userList;
	
	/** The username of the logged in user. */
	private String username;
	
	/** The fxID for the text field which holds the balance. */
	@FXML private TextField txtBalance;
	
	/** The fxID for the text field which navigates backwards a page. */
	@FXML private Button btnBack;
	
	/**
	 * Displays the current balance of the logged in user.
	 * This method is called automatically.
	 */
	public void initialize() {
		userList = FileHandling.getUsers();
		username = FileHandling.getCurrentUser();
		
		for (User user : userList) {
			if (username.equals(user.getUsername())) {
				txtBalance.setText("£ " + user.getFine());
				break;
			}
		}
	}
	
	/**
	 * Goes back to the previous page when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the 
	 *         FXML file isn't available.
	 */
	public void handleBackButtonAction() throws IOException {
		// Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserDashboard.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
}
