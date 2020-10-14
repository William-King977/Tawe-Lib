package controllers;

import java.io.File;
import java.io.IOException;

import data.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the Display User/Profile page.
 * Displays all the details for a selected user/librarian.
 * @author William King
 */
public class DisplayUserController {
	/** Title for the Edit User page. */
	private final String EDIT_USER_TITLE = "Edit Profile";
	/** The file location of the profile pictures. */
	private final String PROFILE_PICTURE_PATH = "DataFiles/ProfilePictures/";
	
	/** Used to check if the profile is a librarian or not. */
	private boolean isLibrarian;
	/** Local storage of the user (that you're viewing). */
	private User thisUser;
	
	/** A text field to hold the librarian's username. */
	@FXML private TextField txtUsername;
	/** A text field to hold the librarian's first name. */
	@FXML private TextField txtFirstName;
	/** A text field to hold the librarian's surname. */
	@FXML private TextField txtSurname;
	/** A text field to hold the librarian's address line 1. */
	@FXML private TextField txtAddressLine1;
	/** A text field to hold the librarian's address line 2. */
	@FXML private TextField txtAddressLine2;
	/** A text field to hold the residing city of the librarian. */
	@FXML private TextField txtCity;
	/** A text field to hold the librarian's postcode. */
	@FXML private TextField txtPostcode;
	/** A text field to hold the librarian's UK mobile number. */
	@FXML private TextField txtMobileNumber;
	/** A text field to hold the librarian's current amount of fines. */
	@FXML private TextField txtCurrentFine;
	/** A text field to hold the librarian's ID. */
	@FXML private TextField txtStaffID;
	/** A text field to hold the librarian's employment date. */
	@FXML private TextField txtEmploymentDate;
	
	/** Label for the Staff ID text field. */
	@FXML private Label lblStaffID;
	/** Label for the Employment Date text field. */
	@FXML private Label lblEmploymentDate;
	
	/** An image view to hold the user's profile picture. */
	@FXML private ImageView imageProfilePicture; 
	
	/** The button for the edit profile page. */
	@FXML private Button btnEditProfile;
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/**
	 * Displays each piece of information of the selected user onto the screen.
	 * @param viewUser The selected user in the library system.
	 */
	public void displayProfile(User viewUser) {
		thisUser = viewUser; // Stores the user locally.
		
		txtUsername.setText(thisUser.getUsername());
		txtFirstName.setText(thisUser.getFirstName());
		txtSurname.setText(thisUser.getSurname());
		txtAddressLine1.setText(thisUser.getAddress1());
		txtAddressLine2.setText(thisUser.getAddress2());
		txtCity.setText(thisUser.getCity());
		txtPostcode.setText(thisUser.getPostcode());
		txtMobileNumber.setText(thisUser.getMobileNumber());
		
		if (isLibrarian()) {
			//Librarians can't get fined, so not applicable.
			txtCurrentFine.setText("N/A");
			txtStaffID.setText(((Librarian) thisUser).getStaffID() + "");
			txtEmploymentDate.setText(((Librarian) thisUser).getEmploymentDate() + "");
		} else {
			txtCurrentFine.setText("£ " + thisUser.getFine()); // Show fine.
			// Hide librarian related details (we are viewing a member).
			lblStaffID.setVisible(false);
			lblEmploymentDate.setVisible(false);
			txtStaffID.setVisible(false);
			txtEmploymentDate.setVisible(false);
		}
		
		//Changes image URL to a file, then converts that to an image.
		File imageURL = new File(PROFILE_PICTURE_PATH + thisUser.getProfilePicture());
		Image profilePicture = new Image(imageURL.toURI().toString());
		imageProfilePicture.setImage(profilePicture);
	}
	
	/**
	 * Displays a page where the user can edit their profile.
	 */
	public void handleEditProfileButtonAction() {
		try {
			// Sets up a new FXML loader.
			FXMLLoader fxmlLoader = new FXMLLoader(getClass()
					.getResource(Main.FXML_FILE_PATH + "EditUser.fxml"));
			
			// Sets a new border pane.
			BorderPane editRoot = fxmlLoader.load();
			
			// Gets the controller for the FXML file loaded.
			EditUserController editUser = fxmlLoader
					.<EditUserController> getController();
			
			// The user is editing their own profile.
			// Adjusts editable fields based on this.
			editUser.setEditAnotherUser(false);        
			editUser.editUser(thisUser); // Pass down the user's details. 
			
			// Sets the scene.
			Scene editScene = new Scene(editRoot); 
			Stage editStage = new Stage();
			editStage.setScene(editScene);
			editStage.setTitle(EDIT_USER_TITLE);
			editStage.initModality(Modality.APPLICATION_MODAL);
			editStage.showAndWait();
			refreshProfile(); // Update profile details on the page.
		} catch (IOException e) {
			// Catches an IO exception such as that where the FXML
			// file is not found.
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	/**
	 * Checks if the selected user is a librarian or a member.
	 * @return If the selected user is a librarian or not.
	 */
	private boolean isLibrarian() {
		return isLibrarian;
	}
	
	/**
	 * Sets whether the selected user is a librarian or a member.
	 * @param isLibrarian If the selected user is a librarian or not.
	 */
	public void setIsLibrarian(boolean isLibrarian) {
		this.isLibrarian = isLibrarian;
	}
	
	/**
	 * Sets the visibility of the edit profile button. 
	 * @param isVisible True for visible, false for invisible.
	 */
	public void setEditProfileVisibility(boolean isVisible) {
		btnEditProfile.setVisible(isVisible);
	}
	
	/**
	 * Updates each text field of the user's profile after
	 * changes have been made (if any).
	 */
	public void refreshProfile() {
		// Changes are made locally as well (in Edit User).
		// This removes the need of searching the user array list.
		displayProfile(thisUser);
	}
	
	/**
	 * Closes the current page.
	 */
	public void handleBackButtonAction() {
		//Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
	}
}