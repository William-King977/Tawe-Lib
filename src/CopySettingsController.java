import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Controller for the View Copies page.
 * Librarians can view all the copies in the system.
 * They can also view overdue copies and a copy's borrow/return history.
 * @author William King
 */
public class CopySettingsController {
	
	/** A list that holds all the copies. */
	private ArrayList<Copy> copyList;
	/** A list that holds all the overdue copies (loans). */
	private ArrayList<Loan> overdueCopyList = new ArrayList<>();
	/** A list that holds all the loans. */
	private ArrayList<Loan> loanList;
	
	/** A list view to display the information of the copies. */
	@FXML private ListView<String> lstShowCopies;
	/** A list view to display the borrow/return history of a copy. */
	@FXML private ListView<String> lstShowCopyHistory;
	/** A check box used to indicate that the librarian wants to view 
	 * only overdue copies. */
	@FXML private CheckBox cbOverdueCopies;
	/** The back button for the page. */
	@FXML private Button btnBack;
	/** The button to initiate the display of the 
	 * borrow/return history of a copy. */
	@FXML private Button viewCopyHistory;
	
	/**
	 * Displays all the copies of all resources onto the screen
	 * with a short description. The method will be called automatically.
	 */
	public void initialize() {
		copyList = FileHandling.getCopies();
		loanList = FileHandling.getLoans();
		
		// Show all copies.
		for (Copy copy : copyList) {
			lstShowCopies.getItems().add(copy.getCopyDescription());
		}
		
		// Store overdue copies from PAST loans (found by the loans).
		for (Loan loan : loanList) {
			if (loan.getDaysOverdue() > 0) {
				overdueCopyList.add(loan); 
			}
		}
	}
	
	/**
	 * Displays the borrow and return history of a selected copy,
	 * showing who borrowed it, date and time of the event and 
	 * whether it was being borrowed or returned.
	 */
	public void showCopyHistory() { 
		lstShowCopyHistory.getItems().clear();
		
		int selectedIndex = lstShowCopies.getSelectionModel()
				.getSelectedIndex();
		
		//If no copy is selected.
		if (selectedIndex < 0) {
			// POSSIIBLY HAVE IT DIRECTLY EMBEDDED IN THE LIST VIEW.
			return;
		}
		// Show the history from overdue copies from PAST loans. 
		if (cbOverdueCopies.isSelected()) {
			Loan selectedLoan = overdueCopyList.get(selectedIndex);
			showOverdueHistory(selectedLoan);
		// Show the history from any copy.
		} else {
			Copy selectedCopy = copyList.get(selectedIndex);
			showHistory(selectedCopy);
		}
	}
	
	/**
	 * Show the borrow/return history of the selected overdue copy (loan).
	 * @param selectedLoan The selected overdue copy (loan).
	 */
	public void showOverdueHistory(Loan selectedLoan) {
		int copyID = selectedLoan.getCopyID();
		
		for (Loan loan : loanList) {
			// If it's currently borrowed.
			if (loan.getCopyID() == copyID && !loan.isReturned()) {
				lstShowCopyHistory.getItems().add(loan.getBorrowedDescription());
			// If it's returned, show when it was borrowed as well.
			} else if (loan.getCopyID() == copyID && loan.isReturned()) {
				lstShowCopyHistory.getItems().add(loan.getBorrowedDescription());
				lstShowCopyHistory.getItems().add(loan.getReturnedDescription());
			}
		}
	}
	
	/**
	 * Shows the borrow/return history of the selected copy.
	 * @param selectedCopy The selected copy.
	 */
	public void showHistory(Copy selectedCopy) {
		int copyID = selectedCopy.getCopyID();
		
		for (Loan loan : loanList) {
			// If it's currently borrowed.
			if (loan.getCopyID() == copyID && !loan.isReturned()) {
				lstShowCopyHistory.getItems().add(loan.getBorrowedDescription());
			// If it's returned, show when it was borrowed as well.
			} else if (loan.getCopyID() == copyID && loan.isReturned()) {
				lstShowCopyHistory.getItems().add(loan.getBorrowedDescription());
				lstShowCopyHistory.getItems().add(loan.getReturnedDescription());
			}
		}
	}
	
	/**
     * Sets the status of the Overdue Copies check box and makes the 
     * appropriate changes to the copy list when selected.
     */
    public void setCBOverdueCopiesStatus() {
    	lstShowCopies.getItems().clear();	
    	lstShowCopyHistory.getItems().clear();	
    	
		if (cbOverdueCopies.isSelected()) {
			for (Loan overdueCopy : overdueCopyList) {
				lstShowCopies.getItems().add(
						overdueCopy.getOverdueDescription());
			}
		// If you're clicking it to clear it, then show all copies.
		} else {
			for (Copy copy : copyList) {
				lstShowCopies.getItems().add(copy.getCopyDescription());
			}
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
