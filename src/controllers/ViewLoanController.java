package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;

import data.*;
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
	/** Title for the Staff Dashboard page. */
	private final String STAFF_DASHBOARD_TITLE = "Staff Dashboard";
	
	/** Holds all the loans from the loans file for local storage. */
    private ArrayList<Loan> loanList;
    /** ArrayList to hold all requests. */
    private ArrayList<Request> requests;
    /** A list to hold all the transactions. */
	private ArrayList <Transaction> transactions;
    /** A linked hashmap to hold all users. */
    private LinkedHashMap<String, User> users;
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
    
    /** The return loan button for the page. */
    @FXML private Button btnReturnLoan;
	/** The back button for the page. */
    @FXML private Button btnBack;
    
    /**
	 * Sets up the array lists for the Loans and displays them.
	 * This method will run automatically.
	 */
	public void initialize() { 
		loanList = FileHandling.getLoans();
		
		// Used to update requests (and copies if applicable).
		requests = FileHandling.getRequests();
		copies = FileHandling.getCopies();
		
		// Used for adding a user's fine (if overdue).
		transactions = FileHandling.getTransactions();
		users = FileHandling.getUsers();
		
		// Populate the loan array lists (to past/current loans).
		for (Loan loan : loanList) {
			if (loan.isReturned()) {
				pastLoans.add(loan);
			} else {
				// Show current loans by default.
				currentLoans.add(loan);
				lstShowLoans.getItems().add(loan.getDescription());
				
			}
		}
		
		// Order the lists.
		Collections.sort(requests);
		Collections.sort(transactions);
		Collections.sort(currentLoans, Comparator.reverseOrder());
		
		// Sort returned loans by return date. Most recent are shown first.
		Collections.sort(pastLoans, new Comparator<Loan>() {
			public int compare(Loan a, Loan b) {
		    	// If the return dates are different, calculate days between them.
		    	if (!b.getReturnDate().equals(a.getReturnDate())) {
		    		String firstDate = a.getReturnDate();
		    		String secondDate = b.getReturnDate();
		    		return Utility.daysBetweenDates(firstDate, secondDate);
		    	// Otherwise, calculate the time between them.
		    	} else {
		    		String firstTime = a.getReturnTime();
		    		String secondTime = b.getReturnTime();
		    		return Utility.secondsBetweenTimes(firstTime, secondTime);
		    	}
			}
		}); 
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
		// Also, enables/disables the return loan button.
		Loan selectedLoan = null;
		// Past loans.
		if (cbPastLoans.isSelected()) {
			selectedLoan = pastLoans.get(selectedIndex);
			btnReturnLoan.setDisable(true);
		// Current loans.
		} else if (cbCurrentLoans.isSelected()) {
			selectedLoan = currentLoans.get(selectedIndex);
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
     */
    public void handleReturnLoanButtonAction() {
    	int selectedIndex = lstShowLoans.getSelectionModel()
				.getSelectedIndex();
		
    	// Get the returned loan.
    	Loan returnedLoan = currentLoans.get(selectedIndex);
		
		// Set returned to true.
		String oldLoan = returnedLoan.toStringDetail();
		LocalDate today = LocalDate.now();
		LocalTime timeNow = LocalTime.now().withNano(0);
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
			makeFineTransaction(returnedLoan, userFine, today, timeNow); 
		}
		
		// Check if there are any pending requests for the returned copy. 
		// If not then set isAvailable to TRUE.
		checkReservedRequests(returnedLoan);
		
		// Save loan changes.
		String newLoan = returnedLoan.toStringDetail();
		FileHandling.editLoan(oldLoan, newLoan);
		Utility.loanReturned(); // Loan returned alert.
		refreshViewLoan(selectedIndex, returnedLoan); // Refresh page.
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
    			// Construct a fake resource, we only need the fines.
    			Book book = new Book(-1, "", -1, "", -1, "", "", "", "", "");
    			finePerDay = book.getFinePerDay();
    			maxFine = book.getMaxFine();
    			break;
    		case DVD:
    			String[] list = new String[4];
    			DVD dvd = new DVD(-1, "", -1, "", -1, "", -1, "", list);
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
     * @param username The username of the user being fined.
     * @param fine The amount of money fined for the loan.
     */
    public void addUserFine(String username, double fine) {
    	User finedUser = users.get(username);
    	
    	String oldUser = finedUser.toStringDetail();
    	double currentBalance = finedUser.getFine();
    	finedUser.setFine(currentBalance + fine);
    	String newUser = finedUser.toStringDetail();
    	
    	FileHandling.editProfile(oldUser, newUser, 2);
    }
    
    /**
     * Creates and saves the transaction for the fine.
     * @param returnedLoan The loan that was just returned.
     * @param userFine The amount fined to the user.
     * @param today Today's date.
     * @param time The current time (when the loan was returned).
     */
    public void makeFineTransaction(Loan returnedLoan, double userFine, 
    		LocalDate today, LocalTime time) {
    	int transactionID = getMaxTransactionID() + 1;
    	int resourceID = returnedLoan.getResourceID();
    	String username = returnedLoan.getUsername();
    	double amount = userFine;
    	
    	int daysOverdue = returnedLoan.getDaysOverdue();
    	String date = today.toString();
    	String timeNow = time.toString();
    	ResourceType type = returnedLoan.getType();
    	boolean isFine = true;
    	
    	Transaction fineTransaction = new Transaction(transactionID, 
    			resourceID, username, amount, daysOverdue, date, timeNow, 
    			type, isFine);
    	
    	transactions.add(fineTransaction);
    	String strFineTransaction = fineTransaction.toStringDetail();
    	FileHandling.makeTransaction(strFineTransaction);
    }
    
    /**
	 * Fetches the maximum transaction ID of all current transactions.
	 * @return The current maximum transaction ID.
	 */
	public int getMaxTransactionID() {
		int maxID;
		
		if (transactions.size() == 0) {
			maxID = 0;
		} else {
			int maxIndex = transactions.size() - 1;
			maxID = (transactions.get(maxIndex)).getTransactionID();
		}
		return maxID;
	}
    
    /**
     * Checks if there are any unfilled requests that are waiting
     * to borrow this returned copy and makes appropriate changes
     * if there are.
     * @param returnedLoan The returned loan.
     */
    public void checkReservedRequests(Loan returnedLoan) {
		int copyID = returnedLoan.getCopyID();
		String username = returnedLoan.getUsername();
		boolean anyRequests = false;
		Request nextRequest = null;
		
		for (Request request : requests) {
			// Find the next request for the copy (from a different user).
			if (!request.getRequestFilled() && (copyID == request.getCopyID()) 
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
    	btnReturnLoan.setDisable(true);
    			
    	if (cbPastLoans.isSelected()) {
    		// Clears the content of the resource list if any. 
        	lstShowLoans.getItems().clear();
    		for (Loan thisLoan : pastLoans) {
    			lstShowLoans.getItems().add(thisLoan.getDescription());
    		}
    	// Keep it selected if it gets clicked again.
    	} else {
    		cbPastLoans.setSelected(true); 
    	}
    }
    
    /**
     * Sets the status of the current loans check box and makes the 
     * appropriate changes to the loan list when selected.
     */
    public void setCBCurrentLoansStatus() {
    	cbPastLoans.setSelected(false);
    
    	if (cbCurrentLoans.isSelected()) {
    		// Clears the content of the resource list if any. 
        	lstShowLoans.getItems().clear();
        	btnReturnLoan.setDisable(true);
    		for (Loan thisLoan : currentLoans) {
    			lstShowLoans.getItems().add(thisLoan.getDescription());
    		}
    	// Keep it selected if it gets clicked again.
    	} else {
    		cbCurrentLoans.setSelected(true); 
    	}
    }
    
    /**
     * Refreshes the View Loan page after a loan has been returned.
     * @param index The index of the returned loan.
     * @param returnedLoan The returned loan.
     */
    public void refreshViewLoan(int index, Loan returnedLoan) {
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
		
		btnReturnLoan.setDisable(true); // Disable return loan button.
		
		// Remove the returned loan and add it to past loans.
		currentLoans.remove(returnedLoan);
		pastLoans.add(0, returnedLoan); // Add to the start (to show most recent returns first).
		
		// Remove from the list view (current loans).
		lstShowLoans.getItems().remove(index);
    }
    
    /**
     * Closes the current page, then navigates back to the User Dashboard.
     */
    public void handleBackButtonAction() {
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource(Main.FXML_FILE_PATH + "UserDashboardStaff.fxml"));
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
