import java.util.ArrayList;
import java.util.Arrays; 

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
    private static String alphabet = "[a-zA-Z- ]+";
    
    /** A regular expression that holds digits from 0-9. */
    private static String digits = "[0-9]+";
	
    /**
     * Performs insertion sort on an ArrayList of resources by using
     * the resourceID for the sort. 
     * @param list The list of resources to be sorted.
     */
    public static void sortResources(ArrayList<Resource> list) {
    	int n = list.size();
        if (n > 1) {
            for (int i = 1; i < n; i++) { 
            	Resource key = list.get(i);
            	int j = i - 1;
            	
            	while (j >= 0  && 
            			(list.get(j).getResourceID() > key.getResourceID())) {
            		list.set(j + 1, list.get(j));
            		j--;
                }
            	list.set(j + 1, key);
            }
        }
    }
    
    /**
     * Performs insertion sort on an ArrayList of copies by using
     * the copyID for the sort. 
     * @param list The list of copies to be sorted.
     */
    public static void sortCopies(ArrayList<Copy> list) {
    	int n = list.size();
        if (n > 1) {
            for (int i = 1; i < n; i++) { 
            	Copy key = list.get(i);
            	int j = i - 1;
            	
            	while (j >= 0  && 
            			(list.get(j).getCopyID() > key.getCopyID())) {
            		list.set(j + 1, list.get(j));
            		j--;
                }
            	list.set(j + 1, key);
            }
        }
    }
    
	/**
	 * An alert pop-up that tells the user that the username entered
	 * does not exist in the system.
	 */
	public static void userNotExist() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: User Does Not Exist.");
		alert.setHeaderText(null);
		alert.setContentText("The username entered does not exist in the "
				+ "system. Please try again. Remember to click the check "
				+ "box if you are a librarian.");	
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
	 * An alert pop-up that tells the user that some fields
	 * have non-integer characters in them (when they shouldn't).
	 */
	public static void nonIntegerError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Non-Integer Characters "
				+ "Detected In Fields.");
		alert.setHeaderText(null);
		alert.setContentText("Non-integer characters have been detected in "
				+ "the input fields, when they shouldn't be there");	
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
				+ "the input fields, when they shouldn't be there");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that the changes
	 * made to the user have been saved.
	 */
	public static void savedUserChanges() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this user "
				+ "have been saved successfully.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user / staff that the changes
	 * made to the librarian has been saved.
	 */
	public static void savedLibrarianChanges() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Changes Saved Successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The changes made to this librarian "
				+ "have been saved successfully.");
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
	 * An alert pop-up that tells the user that their profile
	 * picture has been saved.
	 */
	public static void profilePictureCreated() {
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Profile Picture Created.");
		alert.setHeaderText(null);
		alert.setContentText("The profile picture has been created "
				+ "successfully. Select it from your profile "
				+ "if you want to use it as your profile picture.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the user that they
	 * haven't selected a user to edit.
	 */
	public static void userNotSelected() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Edit User.");
		alert.setHeaderText(null);
		alert.setContentText("Please click a "
				+ "checkbox to display "
				+ "the users you want to view / edit. "
				+ "If you have, you must select a "
				+ "user to edit their details.");
		alert.showAndWait();
	}
	
	/**
	 * An alert pop-up that tells the librarian that
	 * they can't edit other librarian's profiles.
	 */
	public static void invalidStaffEdit() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Edit Librarian.");
		alert.setHeaderText(null);
		alert.setContentText("You are not allowed to edit "
				+ "other librarian's profiles. Only your own "
				+ "profile or other regular users.");
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
	 * An alert pop-up that tells the user that they have not
	 * selected a resource to edit when clicking on the edit resource button.
	 */
	public static void resourceNotSelectedEdit() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error: Cannot Edit Resource.");
		alert.setHeaderText(null);
		alert.setContentText("Please select the resource "
				+ "that you want to edit.");
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
    public static boolean isUsernameExist(ArrayList<User> userList, 
    		ArrayList<Librarian> librarianList, String username) {
		// Checks if any of the librarians have the same username.
        for (User thisLibrarian : librarianList) {
        	if ((thisLibrarian.getUsername()).equals(username)) { 
        		return true;
        	}
        }
        // Checks if any of the members have the same username.
        for (User thisUser : userList) {
        	if ((thisUser.getUsername()).equals(username)) {
        		return true;
        	}
        }
        // The entered username doesn't exist in the system.
        return false;
    }
    
    /**
     * Checks if the entered language is already in the list view.
     * @param language The subtitle language entered.
     * @param subLang List view of subtitle languages.
     * @return Whether the entered language is in the list view or not.
     */
    public static boolean isLanguageExist(String language, 
    		ArrayList<String> subLang) {
    	for (String lang : subLang) {
    		if (lang.equals(language)) {
    			return true;
    		}
    	}
    	return false;
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
	 * @param genre The entered genre.
	 * @param language The entered language.
	 * @return Whether the DVD being created has the same details as
	 * 		   an already existing DVD in the library.
	 */
	public static boolean isDVDExist(String resourceTitle, int year, 
			String imageName, String director, double runtime, String[] subLang, 
			String genre, String language, ArrayList <DVD> dvdList) {
		String strSubLang = Arrays.toString(subLang);
		boolean dvdExist = false;
		//Searches through all the existing DVDs.
		for (DVD thisDVD : dvdList) {
			
			if (thisDVD.getResourceTitle().equals(resourceTitle) &&
					thisDVD.getYear() == year &&
					thisDVD.getDirector().equals(director) &&
					thisDVD.getRuntime() == runtime &&
					Arrays.toString(thisDVD.getSubLang()).equals(strSubLang) &&
					thisDVD.getGenre().equals(genre) &&
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
     * Checks if the passed down fields only contain letters or dashes.
     * @param firstName The entered first name.
     * @param surname The entered surname.
     * @return Whether the passed down fields have has only letters and 
     *         dashes or not.
     */
    public static boolean isAlphaUser(String firstName, String surname, 
    		String city) {
    	// If the selected fields has only letters or dashes.
	    if (firstName.matches(alphabet) && surname.matches(alphabet) 
	    		&& city.matches(alphabet)) {
	    	return true;
	    // If any of the fields that SHOULD NOT contain non-alphabetical
		// have non-alphabetical characters.
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
     * Checks if the passed down fields only contain letters, dashes
	 * or spaces for the book.
     * @param author The entered author.
     * @param publisher The entered publisher.
     * @param genre The entered genre.
     * @param language The entered language.
     * @return Whether the passed down fields for the book has
     * 		   alphabetical characters or not.
     */
	public static boolean isAlphaBook(String author, String publisher, 
			String genre, String language) {
		if (author.matches(alphabet) && publisher.matches(alphabet) && 
				genre.matches(alphabet) && language.matches(alphabet)) {
			return true;
		//Allow optional fields to be empty.
		} else if (genre.isEmpty() || language.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields only contain letters, dashes
	 * or spaces for the DVD.
	 * @param director The entered director.
	 * @param genre The entered genre.
	 * @param language The entered language.
	 * @return Whether the passed down fields for the DVD has
     * 		   alphabetical characters or not.
	 */
	public static boolean isAlphaDVD(String director, String genre, 
			String language) {
		if (director.matches(alphabet) && genre.matches(alphabet) && 
				language.matches(alphabet)) {
			return true;
		//Allow optional fields to be empty
		} else if (genre.isEmpty() || language.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields only contain letters, dashes
	 * or spaces for the laptop.
	 * @param resourceTitle The entered resource title.
	 * @param manufacturer The entered manufacturer.
	 * @return Whether the passed down fields for the laptop has
     * 		   alphabetical characters or not.
	 */
	public static boolean isAlphaLaptop(String resourceTitle, 
			String manufacturer) {
		if (resourceTitle.matches(alphabet) && 
				manufacturer.matches(alphabet)) {
			return true;
		} else {
			return false;
		}		
	}	
	
	/**
	 * Checks if the entered year only contains numbers.
	 * @param strYear The entered year as a string.
	 * @return Whether the entered year is numeric or not.
	 */
	public static boolean isIntResource(String strYear) {
		if (strYear.matches(digits)) {
			return true;
		} else {
			return false;
		}		
	}
	
	/**
	 * Checks if the entered runtime is a parsable double.
	 * @param strRuntime The entered runtime as a string.
	 * @return Whether the entered runtime is a parsable double or not.
	 */
	public static boolean isDoubleResource(String strRuntime) {
		//Will catch a NumberFormatException if the entered 
		//DVD runtime is not a double (by parsing it as a double).
		try {
			double runtime = Double.parseDouble(strRuntime);
		} catch (Exception NumberFormatException) {
			return false;
		}	
		return true;
	}
}
