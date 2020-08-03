import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller for the Transaction History page.
 * The user can view a chronological list of their transactions.
 * This is either a fine or a payment for a fine.
 * @author William King
 */
public class TransactionHistoryController {
	/** Title for the User Dashboard page. */
	private final String USER_DASHBOARD_TITLE = "User Dashboard";
	/** Holds the user's username. */
	private String username;
	/** Holds a list of all of the transactions. */
	private ArrayList<Transaction> transactions;
	
	/** A list view to display the transactions of the user. */
	@FXML private ListView<String> lstShowTransactions;
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/**
	 * Displays all of the user's transactions in descending order. 
	 * This method will run automatically.
	 */
	public void initialize() {
		transactions = FileHandling.getTransactions();
		username = FileHandling.getCurrentUser();
		
		Collections.sort(transactions, new SortTransactionsDesc());
		
		for (Transaction elem : transactions) {
			// If it's a fine.
			if (username.equals(elem.getUsername()) && elem.isFine()) {
				lstShowTransactions.getItems().add(elem.getFineDescription());
			// If it's a payment.
			} else if (username.equals(elem.getUsername()) && !elem.isFine()) {
				lstShowTransactions.getItems().add(elem.getPaymentDescription());
			}
		}
	}
	
	/**
	 * Goes back to the User Dashboard when the button is clicked.
	 */
	public void handleBackButtonAction() {
		// Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/UserDashboard.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(USER_DASHBOARD_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
}
