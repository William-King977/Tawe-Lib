import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
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
	/** Title for the Edit User page. */
	private final String EDIT_USER_TITLE = "Edit User";
	/** Title for the Display User page. */
	private final String DISPLAY_USER_TITLE = "Display User";
	/** Title for the Create New User page. */
	private final String CREATE_USER_TITLE = "Create New User";
	/** Title for the Staff Dashboard page. */
	private final String STAFF_DASHBOARD_TITLE = "Staff Dashboard";
	
	/** A list of all the librarians. */
    private ArrayList<Librarian> librarianList;
    /** A list of all the members. */
    private ArrayList<User> userList;
    
    /** Stores the currently logged in librarian's username. */
    private String currentUser = FileHandling.getCurrentUser();
	
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
		
		// Shows only members by default.
		for (User user : userList) {
			lstShowUsers.getItems().add(user.getUserDescription());
		}
    }
	
	/**
	 * Handles the actions when the librarian selected a user.
	 * This determines when the Edit/Display User buttons can be selected.
	 */
	public void handleUserSelectAction() {
		//Gets the position of the selected user on the UI.
		int selectedIndex = lstShowUsers.getSelectionModel().getSelectedIndex();
		if (selectedIndex < 0) {
			return;
		} 
		
		btnViewUser.setDisable(false); // You can view their profile.
		
		// Members don't need to be considered for this.
		if (cbLibrarian.isSelected()) {
			User selectedUser = librarianList.get(selectedIndex);
			if (!currentUser.equals(selectedUser.getUsername())) {
				btnEditUser.setDisable(true); // Disable it.
				return;
			}
		} else if (cbMember.isSelected()) {
			// They can edit members.
			btnEditUser.setDisable(false); 
		}
		// Librarians can edit their own profile.
		btnEditUser.setDisable(false); 
	}
	
	/**
	 * Displays a page where the librarian can edit the details of
	 * a selected user.
	 */
	public void handleEditUserButtonAction() {	
		//Gets the position of the selected user on the UI.
		int selectedIndex = lstShowUsers.getSelectionModel().getSelectedIndex();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/EditUser.fxml")); 
	
			AnchorPane editRoot = fxmlLoader.load();
			
			// Gets the controller for the FXML file.
			EditUserController editUser = fxmlLoader
					.<EditUserController> getController();
			
			String currentUser = FileHandling.getCurrentUser();
			User editedUser = null; // Local variable for refreshing any changes.
			
			// Sets variables to editUser based on user type being edited.
			if (cbLibrarian.isSelected()) {
				// Uses the earlier index to find the librarian.
				Librarian selectedUser = librarianList.get(selectedIndex);
				editedUser = selectedUser;
				editUser.setIsLibrarian(true);
				editUser.setEditAnotherUser(false);
    			editUser.editUser(selectedUser);
    		// If editing another user (i.e members).
    		} else if (cbMember.isSelected()) { 
				User selectedUser = userList.get(selectedIndex);
				editedUser = selectedUser;
				editUser.setIsLibrarian(false);
				editUser.setEditAnotherUser(true);
				editUser.editUser(selectedUser);
    		} 
	
            Scene editScene = new Scene(editRoot); 
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(EDIT_USER_TITLE);
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();
            
            // Fresh the list view after any edits.
            lstShowUsers.getItems().set(selectedIndex, editedUser.getUserDescription());
            
		} catch (IOException e) {
            // Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
	
	/**
	 * Displays the all of the user's details when their short description
	 * is selected.
	 */
	public void handleDisplayUserButtonAction() {
		// Gets the position of the selected user on the UI.
		int selectedIndex = lstShowUsers.getSelectionModel().getSelectedIndex();
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/DisplayUser.fxml")); 
			AnchorPane editRoot = fxmlLoader.load();
			
			//Gets the controller for the FXML file.
			DisplayUserController viewUser = fxmlLoader
					.<DisplayUserController> getController();
			
			// Sets variables to viewUser based on user type being edited.
			if (cbLibrarian.isSelected()) {
				// Uses the earlier index to find the librarian 
				// in the librarianList arrayList.
				Librarian selectedUser = librarianList.get(selectedIndex);
				viewUser.setIsLibrarian(true);
	    		viewUser.displayProfile(selectedUser); 	
	    	// If viewing another user.
    		} else if (cbMember.isSelected()) { 
				User selectedUser = userList.get(selectedIndex);
				viewUser.setIsLibrarian(false);
				viewUser.displayProfile(selectedUser); 
    		} 
			
            Scene editScene = new Scene(editRoot); 
            Stage editStage = new Stage();
            editStage.setScene(editScene);
            editStage.setTitle(DISPLAY_USER_TITLE);
            // Sets modality which prevents any other window being
            // used (In the app) until this one is closed.
            editStage.initModality(Modality.APPLICATION_MODAL);
            editStage.showAndWait();
		} catch (IOException e) {
            // Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
	
	/**
	 * Displays a page where the librarian can create a new user.
	 */
	public void handleCreateNewUserButtonAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/NewUser.fxml"));
			
			AnchorPane root = (AnchorPane) fxmlLoader.load();
			// Gets the controller for the FXML file.
			NewUserController createUser = fxmlLoader
					.<NewUserController> getController();
			
			// Passes down the array lists (allows the local changing of them).
			createUser.setUserArrays(userList, librarianList); 
			
			// Used to check if a new user has been made.
			int prevUserSize = userList.size();
			int prevLibSize = librarianList.size();
			
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle(CREATE_USER_TITLE);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.showAndWait();
			
            // Fresh the User Settings page.
			refreshUserSettings(prevUserSize, prevLibSize); 
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            e.printStackTrace();
            System.exit(-1);
		}
	}
	
	/**
     * Sets the status of the Members check box and makes the 
     * appropriate changes to the user list when selected.
     */
    public void setCBMemberStatus() {
    	// Clears librarians check box if previously selected. 
		// Only one can be selected at a time.
		cbLibrarian.setSelected(false);
		
		// Shows all members with a short description of each member 
		// when the member check box is selected.
		if (cbMember.isSelected()) {
			// Clears the content of the current user list if any. 
			lstShowUsers.getItems().clear();
			btnViewUser.setDisable(true);
			btnEditUser.setDisable(true);
    		for (User thisUser: userList) {
    			lstShowUsers.getItems().add(thisUser.getUserDescription());
    		}
    	// Keeps it selected if gets clicked on again.
	    } else {
	    	cbMember.setSelected(true);
	    } 
    }
    
    /**
     * Sets the status of the Librarians check box and makes the 
     * appropriate changes to the user list when selected.
     */
    public void setCBLibrarianStatus() {
    	// Clears member check box if previously selected. 
		cbMember.setSelected(false);
		
		// Shows all librarians with a short description of each 
		// librarian when the librarian check box is selected.
		if (cbLibrarian.isSelected()) {
			//Clears the content of the current user list if any.
			lstShowUsers.getItems().clear();
			btnViewUser.setDisable(true);
			btnEditUser.setDisable(true);
			for (User thisLibrarian : librarianList) {
				lstShowUsers.getItems().add(thisLibrarian.getUserDescription());
			}
		// Keeps it selected if gets clicked on again.
		} else {
			cbLibrarian.setSelected(true);
		}	
    }
    
    /**
     * Refreshes the User Settings page after creating a new user.
     * @param prevUserSize The previous size of the users list.
     * @param prevLibSize The previous size of the librarian list.
     */
    public void refreshUserSettings(int prevUserSize, int prevLibSize) {
    	// Checks if a new user was created by comparing the sizes
    	// of arraylists. If a new user was made, the sizes will change.
    	// If a member was created.
    	if (cbMember.isSelected() && (userList.size() > prevUserSize)) {
    		User newUser = userList.get(userList.size() - 1);
    		lstShowUsers.getItems().add(newUser.getUserDescription());
    	// If a librarian was created.
    	} else if (cbLibrarian.isSelected() && (librarianList.size() > prevLibSize)) {
    		Librarian newLibrarian = librarianList.get(librarianList.size() - 1);
    		lstShowUsers.getItems().add(newLibrarian.getUserDescription());
    	}
    	// Otherwise, do nothing (no user was created).
    }
    
    /**
     * Goes back to the Staff Dashboard when the button is clicked.
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
