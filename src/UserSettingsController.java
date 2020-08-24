import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

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
    private LinkedHashMap<String, Librarian> librarianList;
    /** A list of all the members. */
    private LinkedHashMap<String, User> userList;
    /** An array list holding usernames of all librarians. */
    private ArrayList<String> staffUsernames;
    /** An array list holding usernames of all members. */
    private ArrayList<String> memberUsernames;
    
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
	 * Sets up the linked hashmaps for the users to be displayed
	 * later, depending on which check boxes are ticked.
	 * This method will run automatically.
	 */
	public void initialize() { 
		librarianList = FileHandling.getLibrarians();
		userList = FileHandling.getUsers();
		
		// Shows only members by default.
		for (String key : userList.keySet()) {
			User user = userList.get(key);
			lstShowUsers.getItems().add(user.getDescription());
		}
    }
	
	/**
	 * Handles the actions when the librarian selects a user.
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
			// Gets the username from the String (from the list view).
			String user = lstShowUsers.getItems().get(selectedIndex);
			// It's 10 indexes ahead of where 'Username: ' starts.
			String thisUsername = user.substring(
					user.indexOf("Username: ") + 10, user.indexOf(" | First Name:"));
			if (!currentUser.equals(thisUsername)) {
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
		// Gets the username from the String (from the description shown on the list view).
		String user = lstShowUsers.getItems().get(selectedIndex);
		String thisUsername = user.substring(
				user.indexOf("Username: ") + 10, user.indexOf(" | First Name:"));
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/EditUser.fxml")); 
	
			AnchorPane editRoot = fxmlLoader.load();
			
			// Gets the controller for the FXML file.
			EditUserController editUser = fxmlLoader
					.<EditUserController> getController();
			
			// Sets variables to editUser based on user type being edited.
			if (cbLibrarian.isSelected()) {
				Librarian selectedUser = librarianList.get(thisUsername);
				editUser.setIsLibrarian(true);
				editUser.setEditAnotherUser(false);
    			editUser.editUser(selectedUser);
    		// If editing another user (i.e members).
    		} else if (cbMember.isSelected()) { 
				User selectedUser = userList.get(thisUsername);
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
            User editedUser = editUser.getEditedUser(); // Changes have been made (if any).
            lstShowUsers.getItems().set(selectedIndex, editedUser.getDescription());
            
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
		// Gets the username from the String (from the list view).
		String user = lstShowUsers.getItems().get(selectedIndex);
		String thisUsername = user.substring(
				user.indexOf("Username: ") + 10, user.indexOf(" | First Name:"));
		
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource("FXMLFiles/DisplayUser.fxml")); 
			AnchorPane editRoot = fxmlLoader.load();
			
			//Gets the controller for the FXML file.
			DisplayUserController viewUser = fxmlLoader
					.<DisplayUserController> getController();
			
			// Sets variables to viewUser based on user type being edited.
			if (cbLibrarian.isSelected()) {
				Librarian selectedUser = librarianList.get(thisUsername);
				viewUser.setIsLibrarian(true);
	    		viewUser.displayProfile(selectedUser); 	
	    	// If viewing another user.
    		} else if (cbMember.isSelected()) { 
				User selectedUser = userList.get(thisUsername);
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
			
			// Passes down the linked hashmaps (allows the local changing of them).
			createUser.setUserLists(userList, librarianList); 
			
			Scene scene = new Scene(root);
			Stage primaryStage = new Stage();
			primaryStage.setScene(scene);
			primaryStage.setTitle(CREATE_USER_TITLE);
			primaryStage.initModality(Modality.APPLICATION_MODAL);
            primaryStage.showAndWait();
			
            // Fresh the User Settings page.
            String newUserType = createUser.getNewUserType();
            String newUsername = createUser.getNewUsername();
			refreshUserSettings(newUserType, newUsername); 
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
			
			for (String key : userList.keySet()) {
				User user = userList.get(key);
				lstShowUsers.getItems().add(user.getDescription());
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
		
			for (String key : librarianList.keySet()) {
				User thisLibrarian = librarianList.get(key);
				lstShowUsers.getItems().add(thisLibrarian.getDescription());
			}
		// Keeps it selected if gets clicked on again.
		} else {
			cbLibrarian.setSelected(true);
		}	
    }
    
    /**
     * Refreshes the User Settings page after creating a new user.
     * @param newUserType The type of user that was created
     * @param newUsername The username of the created user.
     */
    public void refreshUserSettings(String newUserType, String newUsername) {
    	switch (newUserType) {
	    	case "User":
	    		if (cbMember.isSelected()) {
	    			User newUser = userList.get(newUsername);
	    			lstShowUsers.getItems().add(newUser.getDescription());
	    		}
	    		break;
	    	case "Librarian":
	    		if (cbLibrarian.isSelected()) {
	    			Librarian newLibrarian = librarianList.get(newUsername);
	    			lstShowUsers.getItems().add(newLibrarian.getDescription());
	    		}
	    		break;
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
