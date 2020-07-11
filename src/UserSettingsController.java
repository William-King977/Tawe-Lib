import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the User Settings page.
 * Handles actions performed by the librarian on the User Settings page.
 * They can view and edit any user's profile. However, staff can only edit
 * their own profile.
 * @author William King
 */
public class UserSettingsController {
	
	/** A list view used to display the users. */
	@FXML private ListView<String> listShowUsers;
	
	/** A check box to indicate that the librarian wants 
	  * to view all users. */
	@FXML private CheckBox cbMember;
	
	/** A check box to indicate that the librarian wants 
	  * to view all librarians. */
	@FXML private CheckBox cbLibrarian;
	
	/** The button that leads to the View User page. */
	@FXML private Button btnViewUser;
	
	/** The button that leads to the Edit User page. */
	@FXML private Button btnEditUser;
	
	/** The button that leads to the Create New User page. */
	@FXML private Button btnCreateNewUser;
	
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/** A list of all the librarians. */
    private ArrayList<Librarian> librarianList;
    
    /** A list of all the users. */
    private ArrayList<User> userList;
    
    /**
	 * Sets up the array lists for the users to be displayed
	 * later, depending on which check boxes are ticked.
	 * This method will run automatically.
	 */
	public void initialize() { 
		librarianList = FileHandling.getLibrarians();
		userList = FileHandling.getUsers();
    }
	
	/**
	 * Displays a page where the librarian can edit the details of
	 * a selected user.
	 */
	public void handleEditUserButtonAction() {	
	}
	
	/**
	 * Displays a page where the librarian can create a new user.
	 */
	public void handleCreateNewUserButtonAction() {
	}
	
	/**
	 * Displays the all of the user's details when their short description
	 * is selected.
	 */
	public void handleDisplayUserButtonAction() {
	}
	
	/**
     * Sets the status of the Members check box and makes the 
     * appropriate changes to the user list when selected.
     */
    public void setCBMemberStatus() {
    	// Clears librarians check box if previously selected. 
		// Only one can be selected at a time (or none).
		// NOTE: If one box is selected, it will clear the other one.
		cbLibrarian.setSelected(false);
		
		// Clears the content of the current user list if any. 
		listShowUsers.getItems().clear();
		
		// Shows all members with a short description of each member 
		// when the member check box is selected.
		if (cbMember.isSelected() == true) {
    		for (User thisUser: userList) {
    			listShowUsers.getItems().add(thisUser.getUserDescription());
    			}
    	// If you're clicking the members check box to clear it. 
    	// Clears the whole list, assuming that librarian check
    	// box is cleared as well.
	    } else if (cbLibrarian.isSelected() == false && 
	    		cbMember.isSelected() == false) {
	    	listShowUsers.getItems().clear();
	    } 
    }
    
    /**
     * Sets the status of the Librarians check box and makes the 
     * appropriate changes to the user list when selected.
     */
    public void setCBLibrarianStatus() {
    	// Clears member check box if previously selected. 
		cbMember.setSelected(false);
		//Clears the content of the current user list if any.
		listShowUsers.getItems().clear();
		
		// Shows all librarians with a short description of each 
		// librarian when the librarian check box is selected.
		if (cbLibrarian.isSelected() == true) {
			for (User thisLibrarian : librarianList) {
				listShowUsers.getItems().add(thisLibrarian.getUserDescription());
                }
		} else if (cbLibrarian.isSelected() == false && 
				cbMember.isSelected() == false) {
			listShowUsers.getItems().clear();		
		}	
    }
    
    /**
     * Goes back to the Staff Dashboard when the button is clicked.
     * @throws IOException Throws exception to be caught when 
     *         the FXML cannot be accessed.
     */
    public void handleBackButtonAction() throws IOException {
    	// Closes the window.
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
