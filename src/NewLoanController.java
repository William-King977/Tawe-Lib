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
 * Controller for the New Loan page.
 * Allows librarians to create new loans for resource copies.
 * @author William King
 */
public class NewLoanController {
	
	/** ArrayList to store requests. */
    private ArrayList<Request> requests = new ArrayList<>();

    /** Represents the list view and links it to the FXML file. */
    @FXML private ListView<String> lstRequests;

    /** Represents the textbox holding the requested date. */
    @FXML private TextField txtRequestDate;

    /** Represents the request ID text field. */
    @FXML private TextField txtRequestID;

    /** Represents the textbox for the assigned copy ID. */
    @FXML private TextField txtCopyID;
    
    /** Represents the textbox that shows the resource ID. */
    @FXML TextField txtResourceID;

    /** Models the Username textfield. */
    @FXML TextField txtUserame;

    /** Models the back button. */
    @FXML Button btnBack;
    
    /**
     * Initialize is automatically run when the page loads. Sets up necessary
     * features and functionality for the page to work correctly.
     */
    public void initialize() {
    }
    
    /**
     * Handles the method to create a new loan when the button is pressed.
     */
    public void handleNewLoanButtonAction() {
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
		primaryStage.show(); //displays the new stage.
	}
}
