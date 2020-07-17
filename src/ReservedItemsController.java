import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller for the Reserved Items page.
 * Users can view copies reserved for them which were previously
 * unavailable copies at the time of their request.
 * @author William King
 */
public class ReservedItemsController {
	
	/** A list view to display the requests for copies that 
	 * are now available. */
	@FXML private ListView<String> listShowReservedItems;
	/** The back button for the page. */
	@FXML private Button btnBack;

	/** Holds a list of all of the requests made by the user. */
	private ArrayList<Request> requestList;
	/** Holds a list of all reserved copies for the user. */
	private ArrayList<Copy> copiesList;
	/** Holds the user's username. */
	private String username;
	
	/**
	 * Displays previously requested items, that are now available for 
	 * the user i.e. are reserved for the user. 
	 * This method will run automatically.
	 */
	public void initialize() {
		requestList = FileHandling.getRequests();
		copiesList = FileHandling.getCopies();
		username = FileHandling.getCurrentUser();
		
		ArrayList<Request> userRequests = new ArrayList<>();
		ArrayList<Copy> availableCopies = new ArrayList<>();
		
		// Get the user's pending requests.
		for (Request request : requestList) {
			if (username.equals(request.getUsername()) && 
					!request.getRequestFilled()) {
				userRequests.add(request);
			}
		}
		
		// Get the available copies
		for (Copy copy : copiesList) {
			if (copy.isAvailable()) {
				availableCopies.add(copy);
			}
		}
            
        //Searches for all requests for copies that are now available.
        for (Request thisRequest : userRequests) {
        	int copyID = thisRequest.getCopyID();
        	for (Copy thisCopy : availableCopies) {
				if (copyID == thisCopy.getCopyID()) {
					listShowReservedItems.getItems().add(
							thisRequest.getReservedDescription());
                }
            }    
        }
	}
	
	/**
	 * Goes back to the User Dashboard when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the 
	 *         FXML file isn't available.
	 */
	public void handleBackButtonAction() throws IOException {
		//Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserDashboard.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
	}
}
