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
	@FXML private ListView<String> lstShowReservedItems;
	/** The back button for the page. */
	@FXML private Button btnBack;

	/** Holds a list of all of the requests. */
	private ArrayList<Request> requestList;
	/** Holds the user's username. */
	private String username;
	
	/**
	 * Displays previously requested items, that are now available for 
	 * the user i.e. are reserved for the user. 
	 * This method will run automatically.
	 */
	public void initialize() {
		requestList = FileHandling.getRequests();
		username = FileHandling.getCurrentUser();
		
		// Show the user's pending reserved requests.
		for (Request request : requestList) {
			if (username.equals(request.getUsername()) && 
					!request.getRequestFilled() && request.isReserved()) {
				lstShowReservedItems.getItems().add(
						request.getReservedDescription());
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
