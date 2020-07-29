import java.io.IOException;
import java.util.ArrayList;

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
	/** A button that leads to the Transaction History page. */
	@FXML private Button btnTransactionHistory; 
	/** A button that leads to the View Resources page. */
	@FXML private Button btnViewResources;
	/** A button that leads to the user's profile. */
	@FXML private Button btnViewProfile;
	/** The log out button for the dashboard. */
	@FXML private Button btnLogout;
	
	/**
	 * Displays the currently borrowed items of the user when the button 
	 * is clicked. This will show the due date of each item (if set). 
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleBorrowedItemsButtonAction() throws IOException {
		Stage curStage = (Stage) btnBorrowedItems.getScene().getWindow(); 
		curStage.close(); 
	  
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/BorrowedItems.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
	
	/**
	 * Displays items requested by the user that are not currently 
	 * available when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleRequestedItemsButtonAction() throws IOException {
		Stage curStage = (Stage) btnRequestedItems.getScene().getWindow(); 
		curStage.close(); // Closes that stage.
	    
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/RequestedItems.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
	
	/**
	 * Displays previously requested items that are now available when the 
	 * button is clicked (reserved for the user).
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleReservedItemsButtonAction() throws IOException {
		Stage curStage = (Stage) btnReservedItems.getScene().getWindow(); 
		curStage.close(); // Closes that stage.
	    
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/ReservedItems.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}
    
    /**
     * Displays a chronologically ordered list showing each transaction 
     * on the user’s account when the button is clicked.
     * Can be either a fine (showing the date and time of the fine, the 
     * amount, the item that caused the fine, and the number of days the 
     * item was overdue) or a payment (showing the date and time of 
     * payment and the amount).
     * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
     */
    public void handleTransactionHistoryButtonAction() throws IOException {
    	Stage curStage = (Stage) btnTransactionHistory.getScene().getWindow();
		curStage.close(); //closes that stage.
	    /*creates the new stage*/
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/TransactionHistory.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
    }
    
    /**
	 * Displays a list of all resources in the library and allows the user
	 * to request to borrow resources when clicked.
	 * @throws IOException Throws an exception to be caught when the
	 *                     FXML file cannot be accessed.
	 */
	public void handleViewResourcesButtonAction() throws IOException {
		Stage curStage = (Stage) btnViewResources.getScene().getWindow(); 
		curStage.close(); //closes that stage.
	    /*creates the new stage*/
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/ViewResource.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
	}
	
	/**
	 * Displays the user's profile where they can view 
	 * and edit their details.
	 * @throws IOException Throws an exception to be caught when the 
	 *                     FXML file cannot be accessed.
	 */
	public void handleViewProfileButtonAction() throws IOException {
        try {
        	//Sets up a new FXML loader.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/DisplayUser.fxml"));
			
			// Sets a new anchor pane.
			AnchorPane editRoot = fxmlLoader.load();
			
			// Gets the controller for the FXML file loaded.
			DisplayUserController viewUser = fxmlLoader
					.<DisplayUserController> getController();
			
			// Gets array list of all users.
			ArrayList<User> userList = FileHandling.getUsers();
			String currentUser = FileHandling.getCurrentUser();
			
			// Finds the logged in user in the user list.
			for (User thisUser : userList) {
				if (thisUser.getUsername().equals(currentUser)) {
					viewUser.setIsLibrarian(false);
					viewUser.displayProfile(thisUser); 
					break;
				}
			}	
			viewUser.setEditProfileVisibility(true);
			
            //Sets the scene incl, width and height
            Scene editScene = new Scene(editRoot); 
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();
        } catch (IOException ex) {
        	//Catches an IO exception such as that where the FXML
            // file is not found.
            ex.printStackTrace();
        }
	}
	
	/**
	 * Goes back to the Log in page when the button is clicked.
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
