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
	
	/** A list of all the librarians. */
    private ArrayList<Librarian> librarianList;
    /** A list of all the users. */
    private ArrayList<User> userList;
	
	/** A list view used to display the users. */
	@FXML private ListView<String> lstShowUsers;
	
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
		//Constants set for the new window to be displayed.
		final String EDIT_USER_TITLE = "Edit User";
		
		//Gets the position of the selected user on the UI.
		int selectedIndex = lstShowUsers.getSelectionModel().getSelectedIndex();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/EditUser.fxml")); 
	
			AnchorPane editRoot = fxmlLoader.load();
			
			//Gets the controller for the FXML file loaded.
			EditUserController editUser = fxmlLoader
					.<EditUserController> getController();
			
			// Sets variables to editUser based on user type being edited.
			if (selectedIndex < 0) {
				Utility.userNotSelected();
				return;
			} else if (cbLibrarian.isSelected()) {
				// Uses the earlier index to find the librarian.
				Librarian selectedUser = librarianList.get(selectedIndex);
				
				// Librarian's can only edit their own profile (or regular users).
				if ((selectedUser.getUsername())
						.equals(FileHandling.getCurrentUser())) {
					// Sets if the edited user is a librarian or not
					// and displays user info on the new window.
					editUser.setIsLibrarian(true);
					editUser.setEditAnotherUser(false);
	    			editUser.editUser(selectedUser); 
				} else {
					Utility.invalidStaffEdit();
					return;
				}
    		} else { // If editing another user.
				User selectedUser = userList.get(selectedIndex);
				editUser.setIsLibrarian(false);
				editUser.setEditAnotherUser(true);
				editUser.editUser(selectedUser); 
    		}
	
            Scene editScene = new Scene(editRoot); 
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(EDIT_USER_TITLE);
          
            // Sets modality which prevents any other window being
            // used (In the app) until this one is closed.
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();
            
            // Refreshes the View Users page to load any
            // changes made to a user, if any.
            initialize();
            cbLibrarian.setSelected(false);
            cbMember.setSelected(false);
            lstShowUsers.getItems().clear();
			
		} catch (IOException ex) {
            // Catches an IO exception such as that where the FXML
            // file is not found.
            ex.printStackTrace();
		}
	}
	
	/**
	 * Displays the all of the user's details when their short description
	 * is selected.
	 */
	public void handleDisplayUserButtonAction() {
		//Constants set for the new window to be displayed.
		final String DISPLAY_USER_TITLE = "Display User";
		
		//Gets the position of the selected user on the UI.
		int selectedIndex = lstShowUsers.getSelectionModel().getSelectedIndex();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/DisplayUser.fxml")); 
			
			// Sets a new anchor pane.
			AnchorPane editRoot = fxmlLoader.load();
			
			//Gets the controller for the FXML file loaded.
			DisplayUserController viewUser = fxmlLoader
					.<DisplayUserController> getController();
			
			// Sets variables to editUser based on user type being edited.
			if (selectedIndex < 0) {
				Utility.userNotSelected();
				return;
			} else if (cbLibrarian.isSelected()) {
				// Uses the earlier index to find the librarian 
				// in the librarianList arrayList.
				Librarian selectedUser = librarianList.get(selectedIndex);
				viewUser.setIsLibrarian(true);
	    		viewUser.displayProfile(selectedUser); 
				
    		} else { // If editing another user.
				User selectedUser = userList.get(selectedIndex);
				viewUser.setIsLibrarian(false);
				viewUser.displayProfile(selectedUser); 
    		}
			
			// Sets the scene incl, width and height
            Scene editScene = new Scene(editRoot); 
            // Creates a new stage
            Stage editStage = new Stage();
            // Sets the scene to the stage
            editStage.setScene(editScene);
            // Sets the stage title
            editStage.setTitle(DISPLAY_USER_TITLE);
          
            // Sets modality which prevents any other window being
            // used (In the app) until this one is closed.
            editStage.initModality(Modality.APPLICATION_MODAL);
            //Shows the window.
            editStage.showAndWait();
			
		} catch (IOException ex) {
            // Catches an IO exception such as that where the FXML
            // file is not found.
            ex.printStackTrace();
		}
	}
	
	/**
	 * Displays a page where the librarian can create a new user.
	 */
	public void handleCreateNewUserButtonAction() throws IOException {
		// Closes the window.
    	Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/NewUser.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
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
		lstShowUsers.getItems().clear();
		
		// Shows all members with a short description of each member 
		// when the member check box is selected.
		if (cbMember.isSelected() == true) {
    		for (User thisUser: userList) {
    			lstShowUsers.getItems().add(thisUser.getUserDescription());
    			}
    	// If you're clicking the members check box to clear it. 
    	// Clears the whole list, assuming that librarian check
    	// box is cleared as well.
	    } else if (cbLibrarian.isSelected() == false && 
	    		cbMember.isSelected() == false) {
	    	lstShowUsers.getItems().clear();
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
		lstShowUsers.getItems().clear();
		
		// Shows all librarians with a short description of each 
		// librarian when the librarian check box is selected.
		if (cbLibrarian.isSelected() == true) {
			for (User thisLibrarian : librarianList) {
				lstShowUsers.getItems().add(thisLibrarian.getUserDescription());
                }
		} else if (cbLibrarian.isSelected() == false && 
				cbMember.isSelected() == false) {
			lstShowUsers.getItems().clear();		
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
