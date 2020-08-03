import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Utility class holds commonly used methods / functions that are used 
 * within different classes such as validation etc.
 * @author William King
 */
public class Utility {
	
	/** A regular expression that holds a dash and, lower and 
	 * upper case letters. */
    private static String alphabet = "[a-zA-Z ]+";
    /** A regular expression that holds digits from 0-9. */
    private static String digits = "[0-9]+";
    
    /** A regular expression for someone's name and city. */
    private static String nameRegex = "[a-zA-Z-' ]+";
    /** A regular expression for an address. */
    private static String addressRegex = "[0-9a-zA-Z-' ]+";
    /** A regular expression for a postcode. */
    private static String postcodeRegex = "[0-9A-Z ]+";
    /** A regular expression for a mobile number. */
    private static String phoneRegex = "[0-9+ ]+";
    
    /** A regular expression for holding numbers and letters. */
    private static String alphaNumRegex = "[0-9a-zA-Z ]+";
    /** A regular expression for the laptop model. */
    private static String modelRegex = "[0-9a-zA-Z- ]+";
    
	/**
	 * An alert pop-up that tells the user that the username entered
	 * does not exist in the system.
	 * @param username The entered username which doesn't exist in the system.
	 */
	public static void userNotExist(String username) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: User Does Not Exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username '" + username + "' does not exist "
				+ "in the system. Please try again. Remember to click the "
				+ "check box if you are a librarian.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have not
	 * filled in all the required fields. 
	 */
	public static void missingFields() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Missing Required Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Please fill in all the "
				+ "required fields.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-alphabetic characters in them (when they shouldn't).
	 */
	public static void nonAlphaError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Alphabetic Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-alphabetic "
				+ "characters have been detected in the input fields, "
				+ "when they shouldn't be there.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the postcode
	 * field has invalid characters.
	 */
	public static void invalidPostcode() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Invalid Postcode.");
		alert.setHeaderText(null);
		alert.setContentText("Invalid characters has been detected in the "
				+ "postcode field. There should only be capital letters and "
				+ "numbers.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-integer characters in them (when they shouldn't).
	 */
	public static void nonIntegerError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Integer Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-integer characters have been detected in "
				+ "the input fields, when they shouldn't be there.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that some fields
	 * have non-double characters in them (when they shouldn't).
	 */
	public static void nonDoubleError() {
		//Displays an alert message
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Double Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-double characters have been detected in "
				+ "the input fields, when they shouldn't be there.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the changes
	 * made to the user have been saved.
	 * @param userType The type of user being edited.
	 */
	public static void savedUserChanges(String userType) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this " + userType
				+ " have been saved successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the changes
	 * made to the resource have been saved.
	 */
	public static void savedResourceChanges() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this resource "
				+ "have been saved successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the
	 * loan has been returned.
	 */
	public static void loanReturned() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Loan Returned.");
		alert.setHeaderText(null);
		alert.setContentText("This loan has been returned.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the file textbox
	 * is empty.
	 */
	public static void fileNameEmpty() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: File Name Field Is Empty.");
		alert.setHeaderText(null);
		alert.setContentText("Please enter a name for "
				+ "your designed profile picture.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the file name
	 * already exists.
	 */
	public static void fileNameExists() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: File Name Exists.");
		alert.setHeaderText(null);
		alert.setContentText("The file name you have chosen already "
				+ "exists for an image. Please enter another.");	
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that
	 * the username entered already exists in the system.
	 */
	public static void usernameExists() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: This Username Exists.");
		alert.setHeaderText(null);
		alert.setContentText("An existing user has the same "
				+ "username, please enter a different one.");
		alert.showAndWait();
    	return;
	}
	
	/**
	 * An alert pop-up that tells the librarian that 
	 * the changes made causes the resource to have matching
	 * details with an existing resource.
	 */
	public static void resourceExistsEdit() {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Save Changes.");
		alert.setHeaderText(null);
		alert.setContentText("The changes you have made to "
				+ "this resource matches with the details of "
				+ "an existing resource.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that 
	 * the details for the resource matches with an existing resource.
	 */
	public static void resourceExistsCreate() {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Create Resource.");
		alert.setHeaderText(null);
		alert.setContentText("The resource you have created "
				+ "already exists in the system.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that 
	 * they have not selected an image thumbnail for the resource.
	 */
	public static void imageNotSelected() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Create Resource.");
		alert.setHeaderText(null);
		alert.setContentText("Please select a thumbnail "
				+ "image for the resource.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the user has 
	 * been created successfully.
	 */
	public static void userCreated() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User Created Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user has been created successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the resource has 
	 * been created successfully.
	 */
	public static void resourceCreated() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resource Created Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The resource has been created successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that a request for the
	 * selected resource has been made and shows the appropriate
	 * follow-up message.
	 * @param requestType The type of request made (reserved/queue).
	 */
	public static void requestCreated(String requestType) {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Request Made.");
		alert.setHeaderText(null);
		
		switch (requestType) {
			case "Reserved":
				alert.setContentText("The request for a copy of the resource "
						+ "has been made and has been reserved for you until "
						+ "a librarian has issued a copy to you.");
				break;
			case "Queue":
				alert.setContentText("There are no available copies for the "
						+ "resource. However, the request for a copy of the "
						+ "resource has been made and has been added to the "
						+ "request queue.");
				break;
		}
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the loan for
	 * a requested copy has been created.
	 */
	public static void loanCreated() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Loan Made.");
		alert.setHeaderText(null);
		alert.setContentText("The loan for this requested copy "
				+ "has been created successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian the fine
	 * payment has been successful.
	 */
	public static void paymentMade() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Payment Made.");
		alert.setHeaderText(null);
		alert.setContentText("The payment for this user has been processed "
				+ "successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have not selected
	 * a resource to create when clicking on the create resource button.
	 */
	public static void resourceNotSelectedCreate() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Create Resource.");
		alert.setHeaderText(null);
		alert.setContentText("Please select a check box "
				+ "to show which resource you want "
				+ "to create.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have outstanding fines
	 * when requesting to borrow a resource.
	 */
	public static void outstandingFines() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Request Resource.");
		alert.setHeaderText(null);
		alert.setContentText("You have outstanding fines. Please "
				+ "pay them before requesting to borrow a resource.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have overdue copies
	 * when requesting to borrow a resource.
	 */
	public static void overdueCopies() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Request Resource.");
		alert.setHeaderText(null);
		alert.setContentText("You have overdue copies. Please "
				+ "return them before requesting to borrow a resource.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have already requested
	 * for a copy of a resource or if they are currently borrowing a copy. 
	 */
	public static void alreadyRequested() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Request Resource.");
		alert.setHeaderText(null);
		alert.setContentText("You have either already requested to borrow a "
				+ "copy of this resource or you are currently borrowing a "
				+ "copy of this resource.");
		alert.showAndWait();
		return;
	}
	
	/**
	 * An alert pop-up that tells the user that they have not selected
	 * a language when clicking the remove button.
	 */
	public static void languageNotSelected() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Remove Language.");
		alert.setHeaderText(null);
		alert.setContentText("Please select the language "
				+ "that you want to remove.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that there are
	 * no languages left to remove from the list view.
	 */
	public static void languageListEmpty() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Remove Language.");
		alert.setHeaderText(null);
		alert.setContentText("The list is empty. There are no "
				+ "more languages to remove.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have not entered
	 * a language when clicking the add button.
	 */
	public static void languageNotEntered() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Add Language.");
		alert.setHeaderText(null);
		alert.setContentText("Enter a language "
				+ "that you want to add.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they have entered
	 * a language that already exists in the list view.
	 */
	public static void languageExists() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Add Language.");
		alert.setHeaderText(null);
		alert.setContentText("The language you've entered "
				+ "already exists for the DVD.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that they
	 * have not entered a payment.
	 */
	public static void noEnteredPayment() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Make Payment.");
		alert.setHeaderText(null);
		alert.setContentText("Please enter the payment you "
				+ "want to make for the user.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the entered
	 * payment is less than 1p.
	 */
	public static void paymentTooLow() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Make Payment.");
		alert.setHeaderText(null);
		alert.setContentText("The payment you entered is less than 1p, "
				+ "please enter a payment of at least 0.01 (1p).");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that the entered
	 * payment is larger than the selected user's outstanding fine.
	 */
	public static void paymentTooHigh() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Make Payment.");
		alert.setHeaderText(null);
		alert.setContentText("The payment you entered is larger than "
				+ "this user's fine. If you want to pay the whole fine, then "
				+ "please enter the user's exact fine.");
		alert.showAndWait();
	}
	
	/**
     * Checks if all the required fields have data in them.
     * @param firstName The entered first name.
     * @param surname The entered surname.
     * @param mobileNumber The entered mobile number.
     * @param address1 The entered address line 1.
     * @param city The entered city.
     * @param postcode The entered postcode.
     * @return Whether all the required fields are filled or not.
     */
    public static boolean isFieldFilledUser(String firstName, String surname, 
    		String mobileNumber, String address1, String city, 
    		String postcode) {
    	// If any of the required fields are empty. 
        if (firstName.isEmpty() || surname.isEmpty() || 
        		mobileNumber.isEmpty() || address1.isEmpty() || 
        		city.isEmpty() || postcode.isEmpty()) {
        	return false;
        //If all required fields are filled.
        } else {
        	return true;
        }
    }
    
    /**
	 * Checks if the required fields for a book has data in them.
	 * @param resourceTitle The title of the book.
	 * @param strYear The year the book was released as a string.
	 * @param author The author who wrote the book.
	 * @param publisher The publisher of the book.
	 * @return Whether the required fields for the book has been 
	 * 		   filled or not.
	 */
	public static boolean isBookFieldFilled(String resourceTitle, 
			String strYear, String author, String publisher) {
		//If any of the required fields are empty. 
	    if (resourceTitle.isEmpty() || strYear.isEmpty() || 
	    		author.isEmpty() || publisher.isEmpty()) {
	        return false;
	    //If all required fields are filled.
	    } else {
	    	return true;
	    }
    }/**
	 * Checks if the required fields for a DVD has data in them.
	 * @param resourceTitle The title of the DVD.
	 * @param strYear The year the book was released as a string.
	 * @param director The entered director of the DVD.
	 * @param strRuntime The entered runtime of the DVD as a string.
	 * @return Whether the required fields for the DVD has been 
	 * 		   filled or not.
	 */
	public static boolean isDVDFieldFilled(String resourceTitle, 
			String strYear, String director, String strRuntime) {
		//If any of the required fields are empty. 
        if (resourceTitle.isEmpty() || strYear.isEmpty() || 
        		director.isEmpty() || strRuntime.isEmpty()) {
        	return false;
        //If all required fields are filled.
        } else {
        	return true;
        }
    }
	
	/**
	 * Checks if the required fields for a laptop has data in them.
	 * @param resourceTitle The title of the laptop.
	 * @param strYear The entered year.
	 * @param manufacturer The entered manufacturer.
	 * @param model The entered model.
	 * @param operatingSystem The entered operating system.
	 * @return Whether the required fields for the laptop has been 
	 * 		   filled or not.
	 */
	public static boolean isLaptopFieldFilled(String resourceTitle, 
			String strYear, String manufacturer, String model, 
			String operatingSystem) {
		//If any of the required fields are empty. 
        if (resourceTitle.isEmpty() || strYear.isEmpty() || 
        		manufacturer.isEmpty() || model.isEmpty() || 
        		operatingSystem.isEmpty()) {
        	return false;
        //If all required fields are filled.
        } else {
        	return true;
        }	
	}
    
    /**
     * Checks if the entered username is the same with any of the current 
     * users registered in the system.
     * @param userList The list of all registered users.
     * @param librarianList The list of all registered librarians.
     * @param username The username entered.
     * @return Whether the username entered exists in the system or not.
     */
    public static boolean isUsernameExist(LinkedHashMap<String, User> userList,
    		LinkedHashMap<String, Librarian> librarianList, String username) {
		// Checks if any of the librarians have the same username.
        Boolean userExist = userList.containsKey(username);
        Boolean librarianExist = librarianList.containsKey(username);
        
        // If either is true, then the username already exists.
        if (userExist || librarianExist) {
        	return true;
        // The entered username doesn't exist in the system.
        } else {
        	return false;
        }
    }
    
    /**
	 * Checks if the changes to the book causes it to match 
	 * with an already existing book in the library.
	 * @param resourceTitle The entered book title.
	 * @param year The entered year.
	 * @param imageName The name of the thumbnail image.
	 * @param author The author who wrote the book.
	 * @param publisher The publisher of the book.
	 * @param genre The entered genre.
	 * @param language The entered language.
	 * @param isbn The entered ISBN.
	 * @return Whether the book being created has the same details as
	 * 		   an already existing book in the library.
	 */
	public static boolean isBookExist(String resourceTitle, int year, 
			String imageName, String author, String publisher, String genre,
			String language, String isbn, ArrayList<Book> bookList) {
		boolean bookExist = false;
		//Searches through all the existing books.
		for (Book thisBook : bookList) {
			if (thisBook.getResourceTitle().equals(resourceTitle) && 
					thisBook.getYear() == year &&
					thisBook.getAuthor().equals(author) &&
					thisBook.getPublisher().equals(publisher) &&
					thisBook.getGenre().equals(genre) &&
					thisBook.getLanguage().equals(language) &&
					thisBook.getThumbnail().equals(imageName) &&
					thisBook.getISBN().equals(isbn)) {						
				bookExist = true;
			} 
		}
		return bookExist;
	}
	
	/**
	 * Checks if the changes to the DVD causes it to match 
	 * with an already existing DVD in the library.
	 * @param resourceTitle The title of the DVD.
	 * @param year The entered year.
	 * @param imageName The image name of the DVD's thumbnail.
	 * @param director The entered director.
	 * @param runtime The entered runtime.
	 * @param subLang The entered subtitle language.
	 * @param language The entered language.
	 * @return Whether the DVD being created has the same details as
	 * 		   an already existing DVD in the library.
	 */
	public static boolean isDVDExist(String resourceTitle, int year, 
			String imageName, String director, double runtime, String[] subLang, 
			String language, ArrayList <DVD> dvdList) {
		String strSubLang = Arrays.toString(subLang);
		boolean dvdExist = false;
		//Searches through all the existing DVDs.
		for (DVD thisDVD : dvdList) {
			if (thisDVD.getResourceTitle().equals(resourceTitle) &&
					thisDVD.getYear() == year &&
					thisDVD.getDirector().equals(director) &&
					thisDVD.getRuntime() == runtime &&
					Arrays.toString(thisDVD.getSubLang()).equals(strSubLang) &&
					thisDVD.getLanguage().equals(language) &&
					thisDVD.getThumbnail().equals(imageName)) {
				dvdExist = true;
			}
		}
		return dvdExist;
	}
	
	/**
	 * Checks if the changes to the laptop causes it to match 
	 * with an already existing laptop in the library.
	 * @param resourceTitle The title of the laptop.
	 * @param year The entered year.
	 * @param imageName The image name of the laptop's thumbnail.
	 * @param manufacturer The entered manufacturer.
	 * @param model The entered model.
	 * @param operatingSystem The entered operating system. 
	 * @return Whether the laptop being created has the same details as
	 * 		   an already existing laptop in the library.
	 */
	public static boolean isLaptopExist(String resourceTitle, int year,
			String imageName, String manufacturer, String model, 
			String operatingSystem, ArrayList<Laptop> laptopList) {
		boolean laptopExist = false;
		//Searches through all the existing laptops.
		for (Laptop thisLaptop : laptopList) {
			if (thisLaptop.getResourceTitle().equals(resourceTitle) &&
					thisLaptop.getYear() == year &&
					thisLaptop.getManufacturer().equals(manufacturer) &&
					thisLaptop.getModel().equals(model) &&
					thisLaptop.getOperatingSystem().equals(operatingSystem) &&
					thisLaptop.getThumbnail().equals(imageName)) {
				laptopExist = true;
			}
		}
		return laptopExist;		
	}
    
    /**
     * Checks if the passed down fields matches with their respective regex for
     * the user.
     * @param firstName The entered first name.
     * @param surname The entered surname.
     * @param mobileNum The entered mobile number.
     * @param address1 The entered address line 1.
     * @param address2 The entered address line 2.
     * @param city The entered city.
     * @return If the passed down fields matches with their regex or not.
     */
    public static boolean isAlphaUser(String firstName, String surname, 
    		String mobileNum, String address1, String address2, String city) {
    	// If the selected fields has only letters or dashes.
	    if (firstName.matches(nameRegex) && surname.matches(nameRegex) 
	    		&& mobileNum.matches(phoneRegex) 
	    		&& address1.matches(addressRegex) 
	    		&& (address2.matches(addressRegex) || address2.equals("N/A")) 
	    		&& city.matches(nameRegex)) {
	    	return true;
	    // If any of the fields that SHOULD NOT contain non-alphabetical
		// have non-alphabetical characters.
	    } else {
	    	return false;
	    }
    }
    
    /**
     * Checks if the entered postcode for a user has valid characters.
     * @param postcode The entered postcode.
     * @return If the postcode has valid characters or not.
     */
    public static boolean isPostcodeValid(String postcode) {
    	if (postcode.matches(postcodeRegex)) {
    		return true;
    	} else {
    		return false;
    	}
    }
 
    /**
     * Checks if the entered language has non-alphabetic characters.
     * @param lang The entered language.
     * @return Whether the entered language has non-alphabetic characters
     *         or not.
     */
    public static boolean isAlphaLanguage(String lang) {
    	if (lang.matches(alphabet)) {
	    	return true;
	    } else {
	    	return false;
	    }
    }
    
    /**
     * Checks if the passed down fields match their respective regex for the 
     * book.
     * @param author The entered author.
     * @param publisher The entered publisher.
     * @param genre The entered genre.
     * @param language The entered language.
     * @return Whether the passed down fields for the book has
     * 		   alphabetical characters or not.
     */
	public static boolean isAlphaBook(String author, String publisher, 
			String genre, String language) {
		if (author.matches(nameRegex) && publisher.matches(alphabet) && 
				(genre.matches(alphabet) || genre.isEmpty())  && 
				(language.matches(alphabet) || language.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields match their respective regex for the
	 * DVD.
	 * @param director The entered director.
	 * @param language The entered language.
	 * @return Whether the passed down fields for the DVD has
     * 		   alphabetical characters or not.
	 */
	public static boolean isAlphaDVD(String director, String language) {
		if (director.matches(nameRegex) && 
				(language.matches(alphabet) || language.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields match their respective regex for 
	 * the laptop.
	 * @param resourceTitle The entered resource title.
	 * @param operatingSystem The entered operating system.
	 * @param model The entered model.
	 * @param manufacturer The entered manufacturer.
	 * @return Whether the passed down fields for the laptop has
     * 		   alphabetical characters or not.
	 */
	public static boolean isAlphaLaptop(String resourceTitle, 
			String operatingSystem, String model, String manufacturer) {
		if (resourceTitle.matches(alphaNumRegex) && 
				operatingSystem.matches(alphaNumRegex) &&
				model.matches(modelRegex) &&
				manufacturer.matches(alphabet)) {
			return true;
		} else {
			return false;
		}		
	}	
	
	/**
	 * Checks if the entered value only contains numbers.
	 * @param value The entered value as a string.
	 * @return Whether the entered year is numeric or not.
	 */
	public static boolean isInt(String value) {
		// If it's an optional field, allow it to be empty.
		if (value.matches(digits) || value.isEmpty()) {
			return true;
		} else {
			return false;
		}		
	}
	
	/**
	 * Checks if the entered value is a parsable double.
	 * @param value The entered value as a string.
	 * @return If the entered value is a parsable double or not.
	 */
	public static boolean isDouble(String value) {
		// Will catch a NumberFormatException if the entered 
		// value is not a double (by parsing it as a double).
		try {
			double runtime = Double.parseDouble(value);
		} catch (Exception NumberFormatException) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Gets the number of days past since the entered date.
	 * @param origionalDate The original date as a string (YYYY-MM-DD).
	 * @return An integer of days past since the entered date.
	 */
	public static int daysPastDate(String originalDate) {
        LocalDate today = LocalDate.now();
        LocalDate thisDate = LocalDate.parse(originalDate);
        int daysBetween = (int) DAYS.between(thisDate, today);
        return daysBetween;
	}
	
	/**
	 * Gets the number of days past between two entered dates.
	 * @param firstDate The first date as a string (YYYY-MM-DD).
	 * @param secondDate The second date as a string (YYYY-MM-DD).
	 * @return An integer of days between the entered dates.
	 */
	public static int daysBetweenDates(String firstDate, String secondDate) {
        LocalDate locFirstDate = LocalDate.parse(firstDate);
        LocalDate locSecondDate = LocalDate.parse(secondDate);
        int daysBetween = (int) DAYS.between(locFirstDate, locSecondDate);
        return daysBetween;
	}
	
	/**
	 * Gets the number of seconds between two entered times.
	 * @param firstTime The first time as a string (HH:MM:SS).
	 * @param secondTime The second time as a string (HH:MM:SS).
	 * @return An integer of seconds between the entered times.
	 */
	public static int secondsBetweenTimes(String firstTime, String secondTime) {
        LocalTime locFirstTime = LocalTime.parse(firstTime);
        LocalTime locSecondTime = LocalTime.parse(secondTime);
        int minutesBetween = (int) SECONDS.between(locFirstTime, locSecondTime);
        return minutesBetween;
	}
}
