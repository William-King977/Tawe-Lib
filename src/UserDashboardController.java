import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Controller for the User Dashboard page.
 * Handles actions performed by the user on the User Dashboard page.
 * @author William King
 */
public class UserDashboardController {
	
	/** A button that leads to the Borrowed Items page. */
	@FXML private Button btnBorrowedItems;
	
	/** A button that leads to the Requested Items page. */
	@FXML private Button btnRequestedItems;
	
	/** A button that leads to the Reserved Items page. */
	@FXML private Button btnReservedItems;
	
	/** A button that leads to the Current Balance page. */
	@FXML private Button btnCurrentBalance;
	
	/** A button that leads to the Transaction History page. */
	@FXML private Button btnTransactionHistory; 
	
	/** A button that leads to the View Resources page. */
	@FXML private Button btnViewResources;
	
	/** A button that leads to the user's profile. */
	@FXML private Button btnViewProfile;
	
	/** A button that leads to the View Copies page. */
	@FXML private Button btnViewCopies; 
	
	/** The log out button for the dashboard. */
	@FXML private Button btnLogout;
	
	/**
	 * Displays the currently borrowed items of the user when the button 
	 * is clicked. This will show the due date of each item (if set). 
	 */
	public void handleBorrowedItemsButtonAction() {
	}
	
	/**
	 * Displays items requested by the user that are not currently 
	 * available when the button is clicked.
	 */
	public void handleRequestedItemsButtonAction() {
	}
	
	/**
	 * Displays previously requested items that are now available when the 
	 * button is clicked (reserved for the user).
	 */
	public void handleReservedItemsButtonAction() {
	}
	
	/**
     * Displays the current amount of fines for the user when the 
     * button is clicked.
     */
    public void handleCurrentBalanceButtonAction() {  
	}
    
    /**
     * Displays a chronologically ordered list showing each transaction 
     * on the user’s account when the button is clicked.
     * Can be either a fine (showing the date and time of the fine, the 
     * amount, the item that caused the fine, and the number of days the 
     * item was overdue) or a payment (showing the date and time of 
     * payment and the amount).
     */
    public void handleTransactionHistoryButtonAction() {
    }
    
    /**
	 * Displays a list of all resources in the library and allows the user
	 * to request to borrow resources when clicked.
	 */
	public void handleViewResourcesButtonAction() {
	
	}
	
	/**
	 * Displays the user's profile where they can view 
	 * and edit their details.
	 */
	public void handleViewProfileButtonAction() {
	}
	
	/**
	 * Goes back to the Log in page when the button is clicked.
	 */
	public void handleLogoutButtonAction() {
		//Closes the window.
		Stage stage = (Stage) btnLogout.getScene().getWindow();
		stage.close();
	}
}
