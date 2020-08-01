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
 * Controller for the New Loan page.
 * Allows librarians to create new loans for resource copies.
 * @author William King
 */
public class NewLoanController {
	/** Title for the Staff Dashboard page. */
	private final String STAFF_DASHBOARD_TITLE = "Staff Dashboard";
	
	/** ArrayList to store requests. */
    private ArrayList<Request> requestList;
    /** ArrayList to store all the loans. */
    private ArrayList<Loan> loanList;
    /** ArrayList to store the pending (reserved) requests. */
    private ArrayList<Request> pendingRequests = new ArrayList<>();
    /** ArrayList to store all the copies. */
    private ArrayList<Copy> copies;
    /** ArrayList to store all librarians. */
    private ArrayList<Librarian> librarians;
    
    /** Holds the staff's username (currently using the system). */
    private String staffUsername;

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
    
    /** The create loan button. */
    @FXML private Button btnCreateLoan;
    /** Models the back button. */
    @FXML private Button btnBack;
    
    /**
     * Initialize is automatically run when the page loads. Sets up necessary
     * features and functionality for the page to work correctly.
     */
    public void initialize() {
    	requestList = FileHandling.getRequests();
    	copies = FileHandling.getCopies();
    	loanList = FileHandling.getLoans();
    	Collections.sort(loanList, new SortLoansAsc());
    	
    	librarians = FileHandling.getLibrarians();
    	staffUsername = FileHandling.getCurrentUser();
    	
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
		btnCreateLoan.setDisable(false);
		
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
     */
    public void handleNewLoanButtonAction() {
    	// A request should be selected.
    	int selectedIndex = lstRequests.getSelectionModel()
				.getSelectedIndex();
    	
		// Fetch the details from the textfields.
    	int loanID = getLatestLoanID() + 1;
    	int copyID = Integer.parseInt(txtCopyID.getText().trim());
    	int resourceID = Integer.parseInt(txtResourceID.getText().trim());
    	String username = txtUsername.getText().trim();
    	int staffID = getStaffID();
    	LocalDate checkoutDate = LocalDate.now();
    	LocalTime checkoutTime = LocalTime.now().withNano(0);
    	String strCheckoutDate = checkoutDate.toString();
    	String strCheckoutTime = checkoutTime.toString();
    	ResourceType type = getResourceType(copyID);
    	int loanDuration = -1; // Isn't actually used. Just initialising it.
    	
    	// Create loan object to set due date if necessary.
    	Loan newLoan = new Loan(loanID, copyID, resourceID, username, staffID, 
    			strCheckoutDate, strCheckoutTime, "", false, "", "", 0, type);
    	
    	// Checks if there's any other requests for this copy.
    	boolean othersRequested = checkReservedRequests(copyID, username);
    	
    	// If so, get the loan duration of the copy to set the due date.
    	if (othersRequested) {
        	for (Copy copy : copies) {
        		if (copyID == copy.getCopyID()) {
        			loanDuration = copy.getLoanDuration();
        			newLoan.setDueDate(loanDuration);
        			break;
        		}
        	}
    	}
    	
    	// Save the loan.
    	loanList.add(newLoan);
    	String strNewLoan = newLoan.toStringDetail();
    	FileHandling.createLoan(strNewLoan);
    	
    	// Set request filled to true.    	
    	Request selectedRequest = pendingRequests.get(selectedIndex);
    	String oldRequest = selectedRequest.toStringDetail();
    	selectedRequest.setRequestFilled(true);
    	String newRequest = selectedRequest.toStringDetail();
    	FileHandling.editRequest(oldRequest, newRequest);
    	
    	// Alert to show that the loan has been created.
    	Utility.loanCreated();
    	refreshNewLoan(selectedIndex); // Refresh the request list.    
    }
    
    /**
     * Fetches the ID of the latest loan.
     * @return ID of the latest loan.
     */
    public int getLatestLoanID() {
    	int maxIndex = loanList.size() - 1;
    	
    	// If there are no loans (at all).
    	if (loanList.size() == 0) {
    		return 1;
    	} 
    	
    	int latestLoanID = loanList.get(maxIndex).getLoanID();
    	return latestLoanID;
    }
    
    /**
     * Fetches the staff ID of the librarian authorising the loan.
     * @return Staff ID of the librarian.
     */
    public int getStaffID() {
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
    	for (Copy copy : copies) {
    		if (copyID == copy.getCopyID()) {
    			return copy.getResourceType();
    		}
    	}
    	// Shouldn't get to this point.
    	return null;
    }
    
    /**
     * Checks if there are any unfilled requests that are waiting
     * to borrow this copy as well.
     * @param copyID The copy of the borrowed resource.
     * @param username The user's username.
     * @return If there are any other requests for the resource.
     */
    public boolean checkReservedRequests(int copyID, String username) {
		boolean anyRequests = false;
		Collections.sort(requestList, new SortRequests());
		
		for (Request request : requestList) {
			if (!request.getRequestFilled() && copyID == request.getCopyID() 
					&& !username.equals(request.getUsername())) {
				anyRequests = true;
				break;
			}
		}
		return anyRequests;
    }
    
    /**
     * Refreshes the New Loan page after creating a new loan.
     * @param selectedIndex The index of the fulfilled request to be removed.
     */
    public void refreshNewLoan(int selectedIndex) {
    	txtRequestDate.clear();
		txtUsername.clear();
        txtRequestID.clear();
        txtCopyID.clear();
        txtResourceID.clear();
    	btnCreateLoan.setDisable(true);
    	
    	// Removes the fulfilled request from the array list and list view.
    	lstRequests.getItems().remove(selectedIndex);
    	pendingRequests.remove(selectedIndex);
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
