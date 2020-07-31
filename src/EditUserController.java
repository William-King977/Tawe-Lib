import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Controller for the Edit User page.
 * Allows a librarian to edit a selected user and handles any actions
 * performed on the page.
 * @author William King
 */
public class EditUserController {
	/** The file location of the profile pictures. */
    private final String PROFILE_PICTURE_PATH = "DataFiles/ProfilePictures/";
	
	/** Used to check if the user edited is a librarian or not. */
	private boolean isLibrarian;
	/** Used to check if a librarian is editing another user's profile
	  * or if a user is editing their own profile. */
	private boolean editAnotherUser;
	
	/** Local storage of the user being edited. */
    private User userBeingEdited;
    
    /** Holds the files of all profile pictures. */
    private File[] profilePictureList;
    
	/** A text field to hold the user's username. */
	@FXML private TextField txtUsername;
	/** A text field to hold the user's first name. */
	@FXML private TextField txtFirstName;
	/** A text field to hold the user's surname. */
	@FXML private TextField txtSurname;
	/** A text field to hold the user's address line 1. */
	@FXML private TextField txtAddressLine1;
	/** A text field to hold the user's address line 2. */
	@FXML private TextField txtAddressLine2;
	/** A text field to hold the residing city of the user. */
	@FXML private TextField txtCity;
	/** A text field to hold the user's postcode. */
	@FXML private TextField txtPostcode;
	/** A text field to hold the user's UK mobile number. */
	@FXML private TextField txtMobileNumber;
	
	/** An image view to hold the librarian's profile picture. */
	@FXML private ImageView imageProfilePicture;
	
	/** A combo box used to select a profile picture. */
	@FXML private ComboBox<String> cmbProfilePicture;
	
	/** A button that leads to a page where the user can create their
	 * own profile picture. */
	@FXML private Button btnCreateProfilePicture;
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/**
     * Initialises the users array lists to be 
     * used later on. This method runs automatically.
     */
    public void initialize() {
        //Creates an array of all profile pictures.
        File folder = new File(PROFILE_PICTURE_PATH);
        profilePictureList = folder.listFiles();
        
        //Clears current contents from the combo box.
        cmbProfilePicture.getItems().clear();
        
        for (File file : profilePictureList) {
        	if (file.isFile()) {
        		cmbProfilePicture.getItems().add(file.getName());
        	}
        }
    }
    
    /**
     * Displays the current details of the user to be edited in the 
     * appropriate text fields.
     * @param editedUser The user to be edited.
     */
    public void editUser(User editedUser) {
		//Keeps local storage of the edited user.
		this.userBeingEdited = editedUser;
		
		//Displays the user's editable details on screen in the
		//appropriate text fields.
    	txtFirstName.setText(editedUser.getFirstName());
		txtSurname.setText(editedUser.getSurname());
		txtAddressLine1.setText(editedUser.getAddress1());
		txtAddressLine2.setText(editedUser.getAddress2());
		txtCity.setText(editedUser.getCity());
		txtPostcode.setText(editedUser.getPostcode());
		txtMobileNumber.setText(editedUser.getMobileNumber());
		
		//Changes image URL to a file, then converts that to an image.
		File imageURL = new File(PROFILE_PICTURE_PATH 
				+ editedUser.getProfilePicture());
        Image profilePicture = new Image(imageURL.toURI().toString());
		imageProfilePicture.setImage(profilePicture);
		
		//Checks if a librarian is editing another user.
		if (isEditAnotherUser()) {
			//If so, prevent the editing of profile pictures.
    		cmbProfilePicture.setDisable(true);
    		btnCreateProfilePicture.setDisable(true);
        }
    }
    
    /**
     * Allows the user to select a profile picture and displays
     * the selected profile picture onto the screen.
     */
    public void handleProfilePictureComboBoxAction() {
	    //Gets the position of the selected profile picture.
		int selectedIndex = cmbProfilePicture.getSelectionModel()
				.getSelectedIndex();			
		File imageURL = profilePictureList[selectedIndex];
		Image profilePicture = new Image(imageURL.toURI().toString());
		imageProfilePicture.setImage(profilePicture);
    }
    
    /**
     * Leads to a page where the user can create their own
     * profile picture.
     * @throws IOException Throws an exception to be caught when the 
	 *                     FXML file cannot be accessed.
     */
    public void handleCreateProfilePictureButtonAction() throws IOException {
    	// Creates the new stage
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/CreateProfilePicture.fxml"));
		// Gets the controller for the FXML file loaded.
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		
		// Sets modality which prevents any other window being
        // used (In the app) until this one is closed.
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.showAndWait();
        
        // Updates the profile pictures.
        File folder = new File(PROFILE_PICTURE_PATH);
        profilePictureList = folder.listFiles();
        
        cmbProfilePicture.getItems().clear();
        
        for (File file : profilePictureList) {
        	if (file.isFile()) {
        		cmbProfilePicture.getItems().add(file.getName());
        	}
        }
    }
    
    /**
     * Validates the edited details of the user and saves any changes made.
     */
    public void handleSaveButtonAction() {
    	String firstName = txtFirstName.getText().trim();
        String surname = txtSurname.getText().trim();
        String mobileNumber = txtMobileNumber.getText().trim();
        String city = txtCity.getText().trim();
        String postcode = txtPostcode.getText().trim();
        String address1 = txtAddressLine1.getText().trim();
        String address2 = txtAddressLine2.getText().trim();
        
        // Set address2 to 'N/A' if it's empty.
        if (address2.isEmpty()) {
        	address2 = "N/A";
        }
        
        // Gets the position of the selected profile picture.
		int selectedIndex = cmbProfilePicture.getSelectionModel()
				.getSelectedIndex();
		
		// Sets to the user's old profile picture.
		String profilePicture = userBeingEdited.getProfilePicture();
		
		// Sets a new profile picture if the user has selected one.
		if (selectedIndex >= 0) {
			//Stores the file of the profile picture.
			profilePicture = profilePictureList[selectedIndex].getName();
		}
		
		// Validation rules applied to certain fields.
        boolean requiredFilled = Utility.isFieldFilledUser(firstName, surname, 
        		mobileNumber, address1, city, postcode);
        boolean hasLetter = Utility.isAlphaUser(firstName, surname, 
        		mobileNumber, address1, address2, city);
        boolean validPostcode = Utility.isPostcodeValid(postcode);
        
        // Shows appropriate alerts if validation has not been met.
        if (!requiredFilled) { 
        	Utility.missingFields();
        	return;
        } else if (!hasLetter) {
        	Utility.nonAlphaError();
        	return;
        } else if (!validPostcode) {
        	Utility.invalidPostcode();
        	return;
        }
        saveUserEdits(firstName, surname, mobileNumber, address1, address2,
        		city, postcode, profilePicture);
    }
    
    /**
     * Saves the edits made to the user.
     * It's done by replacing the details of the old profile with the
     * new edited profile.
     * @param firstName The edited first name.
     * @param surname The edited surname.
     * @param mobileNumber The edited mobile number.
     * @param address1 The edited address line 1.
     * @param address2 The edited address line 2.
     * @param city The edited city.
     * @param postcode The edited postcode.
     * @param profilePicture The changed profile picture file name.
     */
    public void saveUserEdits(String firstName, String surname, 
    		String mobileNumber, String address1, String address2, String city, 
    		String postcode, String profilePicture) {
    	// Create copies of the old and new profiles, then replace the old with new.
    	String oldProfile = userBeingEdited.toStringDetail();
    	
    	userBeingEdited.setFirstName(firstName);
    	userBeingEdited.setSurname(surname);
    	userBeingEdited.setMobileNumber(mobileNumber);
    	userBeingEdited.setAddress1(address1);
    	userBeingEdited.setAddress2(address2);
    	userBeingEdited.setCity(city);
    	userBeingEdited.setPostcode(postcode);
    	userBeingEdited.setProfilePicture(profilePicture);
    	
		String newProfile = userBeingEdited.toStringDetail();
		
    	// If the user is a librarian.
        if (isLibrarian()) {
    		FileHandling.editProfile(oldProfile, newProfile, 1);
    		Utility.savedUserChanges("librarian");
    	// If the user is a member.
        } else {
    		FileHandling.editProfile(oldProfile, newProfile, 2);
    		Utility.savedUserChanges("user");
        }
        handleBackButtonAction(); // Closes the window.
    }

    /**
     * Checks if the edited user is a librarian or a member.
     * @return If the edited user is a librarian or not.
     */
	private boolean isLibrarian() {
		return isLibrarian;
	}

	/**
	 * Sets whether the edited user is a librarian or a member.
	 * @param isLibrarian If the edited user is a librarian or not.
	 */
	public void setIsLibrarian(boolean isLibrarian) {
		this.isLibrarian = isLibrarian;
	}
	
	/**
	 * Gets whether the user is editing their own profile or if
	 * the librarian is editing another user's profile.
	 * @return Whether the user is editing their own profile or if
	 * 		   the librarian is editing another user's profile.
	 */
	private boolean isEditAnotherUser() {
		return editAnotherUser;
	}
	
	/**
	 * Sets whether the user is editing their own profile or if
	 * the librarian is editing another user's profile.
	 * @param editAnotherUser Whether the user is editing their own profile 
	 *                        or if the librarian is editing another user's 
	 *                        profile.
	 */
	public void setEditAnotherUser(boolean editAnotherUser) {
		this.editAnotherUser = editAnotherUser;
	}
	
	/**
     * Goes back to the previous page when the button is clicked.
     */
    public void handleBackButtonAction() {
    	//Closes the window.
    	Stage stage = (Stage) btnBack.getScene().getWindow();
    	stage.close();
    }
}
