import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the View Loan page.
 * Librarians can view all past and current loans.
 * They can also return current loans.
 * @author William King
 */
public class ViewLoanController {
	
	/** Holds all the loans from the loans file for local storage. */
    private ArrayList<Loan> loanList;
    /** ArrayList to hold all requests. */
    private ArrayList<Request> requests;
    /**ArrayList to hold all users. */
    private ArrayList<User> users;
    /** ArrayList to hold all copies */
    private ArrayList<Copy> copies; 
    /** Holds all the past loans (when filtered). */
    private ArrayList<Loan> pastLoans = new ArrayList<>();
    /** Holds all the current loans (when filtered). */
    private ArrayList<Loan> currentLoans = new ArrayList<>();
	
	/** A list view used to display all of the loans. */
	@FXML private ListView<String> lstShowLoans;
	
	/** A check box to indicate that the librarian wants 
	 * to filter to show the past loans. */
	@FXML private CheckBox cbPastLoans;
	
	/** A check box to indicate that the librarian wants 
	 * to filter to show the current loans. */
	@FXML private CheckBox cbCurrentLoans;
	
	/** The return loan button for the page. */
    @FXML private Button btnReturnLoan;
	
	/** The back button for the page. */
    @FXML private Button btnBack;
    
    /** A text field used to display the selected loan's ID. */
    @FXML private TextField txtLoanID;
    
    /** A text field used to display the selected loan's resource ID. */
    @FXML private TextField txtResourceID;
    
    /** A text field used to display the loan's due date. */
    @FXML private TextField txtDueDate;
    
    /** A text field used to display the loan's checkout date. */
    @FXML private TextField txtCheckoutDate;
    
    /** A text field used to display the number of days the loan is 
     * overdue, for a returned loan. */
    @FXML private TextField txtDaysOverdue;
    
    /** A text field used to display the loan's return date. */
    @FXML private TextField txtReturnedDate;
    
    /** A text field used to display if the loan has been returned or not. */
    @FXML private TextField txtReturned;
    
    /** A text field used to display the username of the user who the loan
     * was issued to. */
    @FXML private TextField txtUsername;
    
    /** A text field used to display the ID of the librarian who 
     * authorised the loan. */
    @FXML private TextField txtStaffID;
    
    /** A text field used to display the ID of the borrowed copy. */
    @FXML private TextField txtCopyID;
    
    /** A text field used to display the borrowed copy's resource type. */
    @FXML private TextField txtResourceType;
    
    /**
	 * Sets up the array lists for the Loans and displays them.
	 * This method will run automatically.
	 */
	public void initialize() { 
		// Clear checkboxes, textboxes and list views.
		txtLoanID.clear();
		txtCopyID.clear();
		txtResourceID.clear();
		txtReturned.clear();
		
		txtCheckoutDate.clear();
		txtReturnedDate.clear();
		txtDueDate.clear();
		txtDaysOverdue.clear();
		
		txtUsername.clear();
		txtStaffID.clear();
		txtResourceType.clear();
	
		btnReturnLoan.setDisable(true);
		
		lstShowLoans.getItems().clear();
		pastLoans.clear();
		currentLoans.clear();
		loanList = FileHandling.getLoans();
		
		for (Loan loan : loanList) {
			// Doesn't completely reset everything i.e. when returning a loan.
			if (!cbPastLoans.isSelected() 
					&& !cbCurrentLoans.isSelected()) {
				lstShowLoans.getItems().add(loan.getDescription());
			}

			// Populate other array lists.
			if (loan.isReturned()) {
				pastLoans.add(loan);
			} else {
				currentLoans.add(loan);
			}
		}
		
		// As mentioned previously (doesn't reset EVERYTHING).
		if (cbPastLoans.isSelected()) {
			setCBPastLoansStatus();
		} else if (cbCurrentLoans.isSelected()) {
			setCBCurrentLoansStatus();
		} else {
			// Both checkboxes are disabled at this point.
			cbPastLoans.setSelected(false);
			cbCurrentLoans.setSelected(false);
		}	
    }
	
	/**
	 * Displays all the details of a selected loan.
	 */
	public void displayLoanDetails() {
    	int selectedIndex = lstShowLoans.getSelectionModel()
				.getSelectedIndex();
    	// If nothing was selected i.e. clicking the list view.
		if (selectedIndex < 0) {
			return;
		} 
		
		// Get selected loan and show its details.
		Loan selectedLoan;
		if (cbPastLoans.isSelected()) {
			selectedLoan = pastLoans.get(selectedIndex);
		} else if (cbCurrentLoans.isSelected()) {
			selectedLoan = currentLoans.get(selectedIndex);
		} else {
			selectedLoan = loanList.get(selectedIndex);
		}
		
		// Disables the return loan button if selecting a past loan.
		if (selectedLoan.isReturned()) {
			btnReturnLoan.setDisable(true);
		} else {
			btnReturnLoan.setDisable(false);
		}
		
		txtLoanID.setText(selectedLoan.getLoanID() + "");
		txtCopyID.setText(selectedLoan.getCopyID() + "");
		txtResourceID.setText(selectedLoan.getResourceID() + "");
		txtReturned.setText(selectedLoan.isReturned() + "");
		
		txtCheckoutDate.setText(selectedLoan.getCheckoutDate());
		txtReturnedDate.setText(selectedLoan.getReturnDate());
		txtDueDate.setText(selectedLoan.getDueDate());
		
		if (!selectedLoan.isReturned()) {
			txtDaysOverdue.setText("N/A");
		} else {
			txtDaysOverdue.setText(selectedLoan.getDaysOverdue() + "");
		}
		
		txtUsername.setText(selectedLoan.getUsername());
		txtStaffID.setText(selectedLoan.getStaffID() + "");
		txtResourceType.setText(selectedLoan.getType() + "");
	}
	
	/**
     * Allows a librarian to return a selected loan.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void handleReturnLoanButtonAction() throws IOException {
    	// A loan is selected (button enabled if so...).
    	int selectedIndex = lstShowLoans.getSelectionModel()
				.getSelectedIndex();
		
    	// Get returned loan.
    	Loan returnedLoan;
		if (cbPastLoans.isSelected()) {
			returnedLoan = pastLoans.get(selectedIndex);
		} else if (cbCurrentLoans.isSelected()) {
			returnedLoan = currentLoans.get(selectedIndex);
		} else {
			returnedLoan = loanList.get(selectedIndex);
		}
		
		// Set returned to true.
		String oldLoan = returnedLoan.toStringDetail();
		LocalDate today = LocalDate.now();
		LocalTime timeNow = LocalTime.now().withSecond(0).withNano(0);
		returnedLoan.setReturnDate(today.toString());
		returnedLoan.setReturnTime(timeNow.toString());
		returnedLoan.setReturned(true);
		
		// Calculate days overdue and fines if necessary.
		returnedLoan.setDaysOverdue();
		int daysOverdue = returnedLoan.getDaysOverdue();
		ResourceType type = returnedLoan.getType();
		
		if (daysOverdue > 0) {
			double userFine = calculateUserFine(daysOverdue, type);
			String user = returnedLoan.getUsername();
			addUserFine(user, userFine); // Adds the fine to the user's balance.
		}
		
		// Check if there are any pending requests for the returned copy. 
		// If not then set isAvailable to TRUE.
		checkReservedRequests(returnedLoan);
		
		// Save loan changes.
		String newLoan = returnedLoan.toStringDetail();
		FileHandling.editLoan(oldLoan, newLoan);
		Utility.loanReturned(); // Loan returned alert.
		initialize();
    }
    
    /**
     * Calculates the user's fine based on the resource.
     * @param daysOverdue Number of days overdue.
     * @param type The type of resource.
     * @return The total fine for the loan.
     */
    public double calculateUserFine(int daysOverdue, ResourceType type) {
    	
    	double finePerDay = 0;
    	double maxFine = 0;
    	switch (type) {
    		case BOOK:
    			// Construct fake resource, we only need the fines.
    			Book book = new Book(-1, "", -1, "", -1, "", "", "", "", "");
    			finePerDay = book.getFinePerDay();
    			maxFine = book.getMaxFine();
    			break;
    		case DVD:
    			String[] list = new String[4];
    			DVD dvd = new DVD(-1, "", -1, "", -1, "", "", "", -1, list);
    			finePerDay = dvd.getFinePerDay();
    			maxFine = dvd.getMaxFine();
    			break;
    		case LAPTOP:
    			Laptop laptop = new Laptop(-1, "", -1, "", -1, "", "", "");
    			finePerDay = laptop.getFinePerDay();
    			maxFine = laptop.getMaxFine();
    			break;
    	}
    	
    	double userFine = daysOverdue * finePerDay;
    	if (userFine > maxFine) {
    		userFine = maxFine;
    	}
  
    	return userFine;
    }
    
    /**
     * Adds the fine for the loan to the user's total balance.
     * @param user User being fined.
     * @param fine The amount of money fined for the loan.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void addUserFine(String user, double fine) throws IOException {
    	users = FileHandling.getUsers();
    	
    	User finedUser = null;
    	for (User thisUser : users) {
    		if (user.equals(thisUser.getUsername())) {
    			finedUser = thisUser;
    			break;
    		}
    	}
    	
    	String oldUser = finedUser.toStringDetail();
    	double currentBalance = finedUser.getFine();
    	finedUser.setFine(currentBalance + fine);
    	String newUser = finedUser.toStringDetail();
    	
    	FileHandling.editProfile(oldUser, newUser, 2);
    }
    
    /**
     * Checks if there are any unfilled requests that are waiting
     * to borrow this returned copy and makes appropriate changes
     * if there are.
     * @param returnedLoan The returned loan.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void checkReservedRequests(Loan returnedLoan) throws IOException {
    	requests = FileHandling.getRequests();
		int copyID = returnedLoan.getCopyID();
		String username = returnedLoan.getUsername();
		boolean anyRequests = false;
		Request nextRequest = null;
		
		Utility.sortRequests(requests);
		for (Request request : requests) {
			if (!request.getRequestFilled() && copyID == request.getCopyID() 
					&& !username.equals(request.getUsername())) {
				nextRequest = request;
				anyRequests = true;
				break;
			}
		}
		
		// Set the next request in the queue to reserved (for the copy).
		if (anyRequests) {
			String oldRequest = nextRequest.toStringDetail();
			nextRequest.setReserved(true);
			String newRequest = nextRequest.toStringDetail();
			FileHandling.editRequest(oldRequest, newRequest);
		// Otherwise set the copy to available.
		} else {
			copies = FileHandling.getCopies();
			Copy borrowedCopy = null;
			for (Copy copy : copies) {
				if (copyID == copy.getCopyID()) {
					borrowedCopy = copy;
					break;
				}
			}
			
			String oldCopy = borrowedCopy.toStringDetail();
			borrowedCopy.setAvailable(true);
			String newCopy = borrowedCopy.toStringDetail();
			FileHandling.editCopy(oldCopy, newCopy);
		}
    }
    
    /**
     * Sets the status of the past loans check box and makes the 
     * appropriate changes to the loan list when selected.
     */
    public void setCBPastLoansStatus() {
    	
    	cbCurrentLoans.setSelected(false);
    	
    	// Clears the content of the resource list if any. 
    	lstShowLoans.getItems().clear();
    			
    	if (cbPastLoans.isSelected()) {
    		for (Loan thisLoan : pastLoans) {
    			lstShowLoans.getItems().add(thisLoan.getDescription());
    		}
        // If you're clicking the check box to clear it. 
    	} else if (!cbPastLoans.isSelected() && !cbCurrentLoans.isSelected()) {
        	initialize();
	    }
    }
    
    /**
     * Sets the status of the past loans check box and makes the 
     * appropriate changes to the loan list when selected.
     */
    public void setCBCurrentLoansStatus() {
    	
    	cbPastLoans.setSelected(false);
    	
    	// Clears the content of the resource list if any. 
    	lstShowLoans.getItems().clear();
    			
    	if (cbCurrentLoans.isSelected()) {
    		for (Loan thisLoan : currentLoans) {
    			lstShowLoans.getItems().add(thisLoan.getDescription());
    		}
        // If you're clicking the check box to clear it. 
    	} else if (!cbPastLoans.isSelected() && !cbCurrentLoans.isSelected()) {
        	initialize();
	    }
    }
    
    /**
     * Closes the current page, then navigates back to the previous page
     * when the button is clicked.
	 * @throws IOException Throws an exception to be caught when 
	 *                     the FXML cannot be reached.
     */
    public void handleBackButtonAction() throws IOException {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserDashboardStaff.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
    }           
}
