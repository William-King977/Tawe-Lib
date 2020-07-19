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
		// Clear boxes from previous transaction (if any).
		txtUsername.clear();
		txtCurrentFines.clear();
		txtPayment.clear();
		lstFinedUsers.getItems().clear();
		btnPayFine.setDisable(true);
		
		userList = FileHandling.getUsers();
		finedUsers.clear();
		
		for (User user : userList) {
			if (user.getFine() > 0) {
				finedUsers.add(user);
				String strUser = "Username: " + user.getUsername() + " | "
						+ "Fine: £" + user.getFine();
				lstFinedUsers.getItems().add(strUser);
			}
		}
	}
	
	/**
	 * Shows the selected user's username and current fines.
	 */
	public void displayUserDetails() {
		int selectedIndex = lstFinedUsers.getSelectionModel()
				.getSelectedIndex();
    	// If nothing was selected i.e. clicking the list view.
		if (selectedIndex < 0) {
			return;
		} 
		
		btnPayFine.setDisable(false);
		User selectedUser = finedUsers.get(selectedIndex);
		
		txtUsername.setText(selectedUser.getUsername());
		txtCurrentFines.setText(selectedUser.getFine() + "");
	}
	
	/**
	 * Pays off the user's fine based on the amount entered.
	 * @throws IOException Throws an exception to be caught when a file
     *                     can't be written.
	 */
	public void handlePayFineButtonAction() throws IOException {
		
		String strPayment = txtPayment.getText().trim();
		// Works for a single value...
		boolean isDouble = Utility.isDouble(strPayment); 
		
		if (strPayment.isEmpty()) {
			Utility.noEnteredPayment();
			return;
		} else if (!isDouble) {
			Utility.nonDoubleError();
			return;
		}
		
		double payment = Double.parseDouble(strPayment);
		// Round it to 2 decimal places.
		double payment2DP = Math.round(payment * 100.0) / 100.0; 
		double userFine = Double.parseDouble(txtCurrentFines.getText().trim());
		
		// If less than 1p.
		if (payment2DP < 0.01) {
			Utility.paymentTooLow();
			return;
		} else if (payment2DP > userFine) {
			Utility.paymentTooHigh();
			return;
		}
		
		// Get the selected user and save the changes.
		int selectedIndex = lstFinedUsers.getSelectionModel()
				.getSelectedIndex();
		User selectedUser = finedUsers.get(selectedIndex);
		
		String oldUser = selectedUser.toStringDetail();
		double previousFine = selectedUser.getFine();
		selectedUser.setFine(previousFine - payment2DP);
		String newUser = selectedUser.toStringDetail();
		
		FileHandling.editProfile(oldUser, newUser, 2);
		Utility.paymentMade();
		initialize();
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
