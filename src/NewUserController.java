import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the New User page. 
 * Allows the librarian to create new users for the system.
 * @author William King
 */
public class NewUserController {
	
	/** A list of all the librarians. */
    private ArrayList<Librarian> librarianList;
    
    /** A list of all the users. */
    private ArrayList<User> userList;
    
	/** A regular expression that holds a dash and, 
	 * lower and upper case letters. */
    private String alphabet = "[a-zA-Z-]+";
    
    /** Holds the file name of the default profile picture. */
    private final String DEFAULT_PROFILE_PICTURE =
    		"Default1.png";
    
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
	
	/** A check box to see if the newly created user is a librarian or a 
	 * member. */
	@FXML private CheckBox cbStaff;
	
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/**
     * Sets up the contents for the array lists for the users.
     * This method will run automatically.
     */
    public void initialize() {
    	librarianList = FileHandling.getLibrarians();
        userList = FileHandling.getUsers();
    }
    
    /**
     * Creates a new user based on the information typed in 
     * and saves the user into the appropriate file system.
     */
    public void handleSaveButtonAction() {
    	//Fetches all the entered information from the text fields.
    	String username = txtUsername.getText().trim();
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
        
        String profilePicture = DEFAULT_PROFILE_PICTURE;
        double fine = 0.00;
        
        // Validation rules applied to certain fields.
        boolean requiredFilled = Utility.isFieldFilledUser(firstName, surname, 
        		mobileNumber, address1, city, postcode);
        boolean hasLetter = Utility.isAlphaUser(firstName, surname, city);
        boolean usernameExist = Utility.isUsernameExist(userList, 
        		librarianList, username);
        
        // Shows appropriate alerts if validation has not been met.
        if (!requiredFilled) { 
        	Utility.missingFields();
        	return;
        } else if (!hasLetter) {
        	Utility.nonAlphaError();
        	return;
        } else if (usernameExist) {
        	Utility.usernameExists();
        	return;
        }
        
        saveUser(username, firstName, surname, mobileNumber, address1, 
        		address2, city, postcode, profilePicture, fine);
    }
    
    /**
     * Registers a new user and saves them to the system.
     * @param username The entered username.
     * @param firstName The entered first name.
     * @param surname The entered surname.
     * @param mobileNumber The entered mobile number.
     * @param address1 The entered address line 1.
     * @param address2 The entered address line 2.
     * @param city The entered city.
     * @param postcode The entered post code.
     * @param profilePicture The default profile picture for the user.
     * @param fine The default fine set for the user.
     */
    private void saveUser(String username, String firstName, String surname, 
    		String mobileNumber, String address1, String address2, String city, 
    		String postcode, String profilePicture, double fine) {
    	String newUser = "";
    	int userType;
    	if (cbStaff.isSelected()) {
    		
    		// Gets todays date in format of YYYY-MM-DD.
    		LocalDate employmentDate = LocalDate.now(); 
    		userType = 1;
    		int staffID;
    		int previousStaffID;
    		// Gets the staff ID of the latest registered librarian.
    		if (librarianList.size() == 0) { // staffID will start at 1.
    			staffID = 1;
    		} else {
    			previousStaffID = librarianList.get(librarianList.size() - 1)
    					.getStaffID();
    			staffID = previousStaffID + 1; 
    		}
    		newUser = username + "," + firstName + "," + surname + 
    				"," + mobileNumber + "," + address1 + "," + address2 + 
    				"," + city + "," + postcode + "," + profilePicture + 
    				"," + fine + "," + staffID + "," + employmentDate + ",";
    	} else {
    		userType = 2;
    		newUser = username + "," + firstName + "," + surname + 
    				"," + mobileNumber + "," + address1 + "," + address2 + 
    				"," + city + "," + postcode + "," + profilePicture + 
    				"," + fine + ",";
    	}
    	FileHandling.createUser(newUser, userType);
    	Utility.userCreated();
    	refresh();
    }	
    
    /**
     * Refreshes the Create New User page once a new user has been saved.
     */
    private void refresh() {
    	//Clears all text boxes and the tick box.
    	txtUsername.clear();
		txtFirstName.clear();
		txtSurname.clear();
		txtAddressLine1.clear();
		txtAddressLine2.clear();
		txtCity.clear();
		txtPostcode.clear();
		txtMobileNumber.clear();
    	cbStaff.setSelected(false);	
    	
    	// Update the arrayLists.
    	initialize();
    }
	
	/**
     * Goes back to the previous page when the button is clicked.
     */
    public void handleBackButtonAction() throws IOException {
    	// Closes the window.
    	Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserSettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
    }
}
