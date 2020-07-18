import java.io.IOException;
import java.time.LocalDate;
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
    private ArrayList<Request> requestList;
    
    /** ArrayList to store all the loans. */
    private ArrayList<Loan> loanList;
    
    /** ArrayList to store the pending (reserved) requests. */
    private ArrayList<Request> pendingRequests = new ArrayList<>();

    /** Represents the list view and links it to the FXML file. */
    @FXML private ListView<String> lstRequests;

    /** Represents the textbox holding the requested date. */
    @FXML private TextField txtRequestDate;

    /** Represents the request ID text field. */
    @FXML private TextField txtRequestID;

    /** Represents the textbox for the assigned copy ID. */
    @FXML private TextField txtCopyID;
    
    /** Represents the textbox that shows the resource ID. */
    @FXML private TextField txtResourceID;

    /** Models the Username textfield. */
    @FXML private TextField txtUsername;

    /** Models the back button. */
    @FXML private Button btnBack;
    
    /**
     * Initialize is automatically run when the page loads. Sets up necessary
     * features and functionality for the page to work correctly.
     */
    public void initialize() {
    	txtRequestDate.clear();
		txtUsername.clear();
        txtRequestID.clear();
        txtCopyID.clear();
        txtResourceID.clear();
    	lstRequests.getItems().clear();
    	pendingRequests.clear();
    	
    	requestList = FileHandling.getRequests();
    	
    	for (Request request : requestList) {
    		if (!request.getRequestFilled() && request.isReserved()) {
    			lstRequests.getItems().add(request.getDescription());
    			pendingRequests.add(request);
    		}
    	}
    }
    
    /**
	 * Displays the information of a selected request.
	 */
	public void displayRequestDetails() {
		int selectedIndex = lstRequests.getSelectionModel()
				.getSelectedIndex();
		// If nothing was selected i.e. clicking the list view.
		if (selectedIndex < 0) {
			return;
		}
		
		// Get selected request and show its details.
		Request selectedRequest = pendingRequests.get(selectedIndex);
		
		txtRequestDate.setText(selectedRequest.getRequestDate());
		txtUsername.setText(selectedRequest.getUsername());
        txtRequestID.setText(selectedRequest.getRequestID() + "");
        txtCopyID.setText(selectedRequest.getCopyID() + "");
        txtResourceID.setText(selectedRequest.getResourceID() + "");
	}
    
    /**
     * Handles the method to create a new loan when the button is pressed.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void handleNewLoanButtonAction() throws IOException {
    	// A request should be selected.
    	int selectedIndex = lstRequests.getSelectionModel()
				.getSelectedIndex();
		if (selectedIndex < 0) {
			Utility.requestNotSelected();
			return;
		}
    	
		// Fetch the details from the textfields.
    	int loanID = getNextLatestLoanID() + 1;
    	int copyID = Integer.parseInt(txtCopyID.getText().trim());
    	int resourceID = Integer.parseInt(txtResourceID.getText().trim());
    	String username = txtUsername.getText().trim();
    	int staffID = getStaffID();
    	LocalDate checkoutDate = LocalDate.now(); 
    	ResourceType type = getResourceType(copyID);
    	
    	// Save loan.
    	String newLoan = loanID + "," + copyID + "," + resourceID + 
    			"," + username + "," + staffID + "," + checkoutDate + 
    			",,false,,0," + type + ",";
    	FileHandling.createLoan(newLoan);
    	
    	// Set request filled to true.    	
    	Request selectedRequest = pendingRequests.get(selectedIndex);
    	String oldRequest = selectedRequest.toStringDetail();
    	selectedRequest.setRequestFilled(true);
    	String newRequest = selectedRequest.toStringDetail();
    	FileHandling.editRequest(oldRequest, newRequest);
    	
    	// Alert to show that the loan has been created.
    	Utility.loanCreated();
    	initialize(); // Refresh the request list.    
    }
    
    /**
     * Fetches the ID of the latest loan.
     * @return ID of the latest loan.
     */
    public int getNextLatestLoanID() {
    	loanList = FileHandling.getLoans();
    	Utility.sortLoans(loanList); // Sorts loans by loan ID.
    	
    	int maxIndex = loanList.size() - 1;
    	int latestLoanID = loanList.get(maxIndex).getLoanID();
    	return latestLoanID;
    }
    
    /**
     * Fetches the staff ID of the librarian authorising the loan.
     * @return Staff ID of the librarian.
     */
    public int getStaffID() {
    	ArrayList<Librarian> librarians = FileHandling.getLibrarians();
    	String staffUsername = FileHandling.getCurrentUser();
    	
    	for (Librarian librarian : librarians) {
    		if (staffUsername.equals(librarian.getUsername())) {
    			return librarian.getStaffID();
    		}
    	}
    	// Shouldn't get to this point...
    	return -1;
    }
    
    /**
     * Fetches the types of resource that is going to be loaned.
     * @param copyID The ID of the requested copy.
     * @return The type of resource.
     */
    public ResourceType getResourceType(int copyID) {
    	ArrayList<Copy> copies = FileHandling.getCopies();
    	
    	for (Copy copy : copies) {
    		if (copyID == copy.getCopyID()) {
    			return copy.getResourceType();
    		}
    	}
    	
    	// Shouldn't get to this point.
    	return null;
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
