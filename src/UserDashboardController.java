import java.io.IOException;
import java.util.LinkedHashMap;

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
	/** Title for the Borrowed Items page. */
	private final String BORROWED_ITEMS_TITLE = "Borrowed Items";
	/** Title for the Requested Items page. */
	private final String REQUESTED_ITEMS_TITLE = "Requested Items";
	/** Title for the Reserved Items page. */
	private final String RESERVED_ITEMS_TITLE = "Reserved Items";
	/** Title for the Transaction History page. */
	private final String TRANSACTION_HISTORY_TITLE = "Transaction History";
	/** Title for the View Resources page. */
	private final String VIEW_RESOURCES_TITLE = "View Resources";
	/** Title for the View Profile page. */
	private final String VIEW_PROFILE_TITLE = "View Profile";
	/** Title for the Login page. */
	private final String LOGIN_TITLE = "TaweLib: Library System";
	/** Holds the logged in user's username. */
	private String currentUser;
	
	/** Linked hashmap holding all the users. */
	private LinkedHashMap<String, User> userList;
	
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
	 * Sets up the variables required for the page.
	 * This method will run automatically.
	 */
	public void initialize() {
		userList = FileHandling.getUsers();
		currentUser = FileHandling.getCurrentUser();
	}
	
	/**
	 * Displays the currently borrowed items of the user when the button 
	 * is clicked. This will show the due date of each item (if set). 
	 */
	public void handleBorrowedItemsButtonAction() {
		Stage curStage = (Stage) btnBorrowedItems.getScene().getWindow(); 
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/BorrowedItems.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(BORROWED_ITEMS_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
	
	/**
	 * Displays items requested by the user that are not currently 
	 * available when the button is clicked.
	 */
	public void handleRequestedItemsButtonAction() {
		Stage curStage = (Stage) btnRequestedItems.getScene().getWindow(); 
		curStage.close(); // Closes that stage.
	    
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/RequestedItems.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(REQUESTED_ITEMS_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
	
	/**
	 * Displays previously requested items that are now available when the 
	 * button is clicked (reserved for the user).
	 */
	public void handleReservedItemsButtonAction() {
		Stage curStage = (Stage) btnReservedItems.getScene().getWindow(); 
		curStage.close(); // Closes that stage.
	    
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/ReservedItems.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(RESERVED_ITEMS_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
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
    	Stage curStage = (Stage) btnTransactionHistory.getScene().getWindow();
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/TransactionHistory.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(TRANSACTION_HISTORY_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
    }
    
    /**
	 * Displays a list of all resources in the library and allows the user
	 * to request to borrow resources when clicked.
	 */
	public void handleViewResourcesButtonAction() {
		Stage curStage = (Stage) btnViewResources.getScene().getWindow(); 
		curStage.close(); 
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/ViewResource.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(VIEW_RESOURCES_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
	
	/**
	 * Displays the user's profile where they can view 
	 * and edit their details.
	 */
	public void handleViewProfileButtonAction() {
        try {
        	// Sets up a new FXML loader.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/DisplayUser.fxml"));
			
			// Sets a new anchor pane.
			AnchorPane editRoot = fxmlLoader.load();
			
			// Gets the controller for the FXML file loaded.
			DisplayUserController viewUser = fxmlLoader
					.<DisplayUserController> getController();
			
			// Finds the logged in user in the user linked hashmap.
			User thisUser = userList.get(currentUser);
			viewUser.setIsLibrarian(false);
			viewUser.displayProfile(thisUser); 
			viewUser.setEditProfileVisibility(true);
			
            // Sets the scene.
            Scene editScene = new Scene(editRoot); 
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(VIEW_PROFILE_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();
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
					.getResource("FXMLFiles/Login.fxml"));
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
