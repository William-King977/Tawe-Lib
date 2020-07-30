import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

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
	/** Title for the Staff Dashboard page. */
	private final String STAFF_DASHBOARD_TITLE = "Staff Dashboard";
	
	/** ArrayList to hold all users. */
	private ArrayList<User> userList;
	/** ArrayList to hold all transactions. */
	private ArrayList<Transaction> transactions;
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
	 */
	public void handlePayFineButtonAction() {
		
		String strPayment = txtPayment.getText().trim();
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
		String username = selectedUser.getUsername();
		
		String oldUser = selectedUser.toStringDetail();
		double previousFine = selectedUser.getFine();
		double newFine = Math.round((previousFine - payment2DP) * 100.0) / 100.0;
		selectedUser.setFine(newFine);
		String newUser = selectedUser.toStringDetail();
		
		FileHandling.editProfile(oldUser, newUser, 2);
		makePaymentTransaction(username, payment2DP);
		Utility.paymentMade();
		initialize();
	}
	
	/**
	 * Creates and saves the payment transaction to the user's fines.
	 * @param username The username of the user who sent the payment.
	 * @param payment The amount of money to be paid off.
	 */
	public void makePaymentTransaction(String username, double payment) {
		int transactionID = getMaxTransactionID() + 1;
		String today = LocalDate.now().toString();
		String time = LocalTime.now().withSecond(0).withNano(0).toString();
		ResourceType type = null;
		boolean isFine = false;
		
		// Create payment, adds fake values in non-applicable fields.
		Transaction paymentTransaction = new Transaction(transactionID, -1, 
				username, payment, -1, today, time, type, isFine);
		String strPaymentTransaction = paymentTransaction.toStringDetail();
		FileHandling.makeTransaction(strPaymentTransaction);
	}
	
	/**
	 * Fetches the maximum transaction ID of all current transactions.
	 * @return The current maximum transaction ID.
	 */
	public int getMaxTransactionID() {
		transactions = FileHandling.getTransactions();
		int maxID;
		
		if (transactions.size() == 0) {
			maxID = 0;
		} else {
			Collections.sort(transactions, new SortTransactionsAsc());
			int maxIndex = transactions.size() - 1;
			maxID = (transactions.get(maxIndex)).getTransactionID();
		}
		return maxID;
	}
	
	/**
	 * Goes back to the previous page when the button is clicked.
	 */
	public void handleBackButtonAction() {
		// Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/UserDashboardStaff.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(STAFF_DASHBOARD_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
}
