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
 * Controller for the Requested Items page.
 * Displays the user's requests for copies that are currently unavailable.
 * @author William King
 */
public class RequestedItemsController {
	
	/** A list view to display the requests for unavailable copies. */
	@FXML private ListView<String> lstShowRequestedItems;
	/** The back button for the page. */
	@FXML private Button btnBack;

	/** Holds a list of all of the requests. */
	private ArrayList<Request> requestList;
	/** Holds the user's username. */
	private String username;
	
	/**
	 * Displays all the current requests that the user has made for
     * copies that are currently unavailable. 
	 * This method will run automatically.
	 */
	public void initialize() {
		requestList = FileHandling.getRequests();
		username = FileHandling.getCurrentUser();
		
		// Get the user's pending requests.
		for (Request request : requestList) {
			if (username.equals(request.getUsername()) && 
					!request.getRequestFilled() && !request.isReserved()) {
				lstShowRequestedItems.getItems().add(
						request.getDescription());
			}
		}
	}
	
	/**
	 * Goes back to the User Dashboard when the button is clicked.
	 */
	public void handleBackButtonAction() {
		final String USER_DASHBOARD_TITLE = "User Dashboard";
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
		} catch (IOException ex) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            ex.printStackTrace();
		}
	}
}
