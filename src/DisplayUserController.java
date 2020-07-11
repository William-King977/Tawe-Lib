import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controller for the Display User / Profile page.
 * Displays all the details for a selected user / librarian.
 * @author William King
 */
public class DisplayUserController {
	
	/** Used to check if the profile is a librarian or not. */
	private boolean isLibrarian;
	
	/** The file location of the profile pictures. */
    private final String PROFILE_PICTURE_PATH = "DataFiles/ProfilePictures/";
    
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
	
	/** A canvas to hold the librarian's profile picture. */
	@FXML private ImageView imageProfilePicture; 
	
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/**
	 * Displays each piece of information of the selected user 
	 * onto the screen.
	 * @param viewUser The selected user in the library system.
	 */
	public void displayProfile(User viewUser) {
		txtUsername.setText(viewUser.getUsername());
		txtFirstName.setText(viewUser.getFirstName());
		txtSurname.setText(viewUser.getSurname());
		txtAddressLine1.setText(viewUser.getAddress1());
		txtAddressLine2.setText(viewUser.getAddress2());
		txtCity.setText(viewUser.getCity());
		txtPostcode.setText(viewUser.getPostcode());
		txtMobileNumber.setText(viewUser.getMobileNumber());

		if (isLibrarian()) {
			//Librarians can't get fined, so not applicable.
			txtCurrentFine.setText("N/A");
			txtStaffID.setText(((Librarian) viewUser).getStaffID() + "");
			txtEmploymentDate.setText(((Librarian) viewUser).getEmploymentDate() + "");
		} else {
			txtCurrentFine.setText(viewUser.getFine() + ""); // Show fine.
			// Hide librarian related details (we are viewing a member).
			lblStaffID.setVisible(false);
			lblEmploymentDate.setVisible(false);
			txtStaffID.setVisible(false);
			txtEmploymentDate.setVisible(false);
		}
		
		//Changes image URL to a file, then converts that to an image.
		File imageURL = new File(PROFILE_PICTURE_PATH + 
				viewUser.getProfilePicture());
        Image profilePicture = new Image(imageURL.toURI().toString());
		imageProfilePicture.setImage(profilePicture);
	}
	
	/**
     * Goes back to the previous page when the button is clicked.
     */
    public void handleBackButtonAction() {
    		//Closes the window.
    		Stage stage = (Stage) btnBack.getScene().getWindow();
    		stage.close();
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
}
