import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * Controller for the View Resource page.
 * Users can view all the resources in the system and can request to take out
 * copies of a resource.
 * @author William King
 */
public class ViewResourceController {
	
	/** A list to hold all the resources .*/
	private ArrayList<Resource> resourceList = new ArrayList<Resource>();
	/** A list to hold all the resources found in the search. */
	private ArrayList<Resource> searchedList = new ArrayList<Resource>();
	/** A list to hold all the books. */
	private ArrayList<Book> bookList;	
	/** A list to hold all the DVDs. */
	private ArrayList<DVD> dvdList;
	/** A list to hold all the laptops. */
	private ArrayList<Laptop> laptopList;
	/** A list to hold all the copies. */
	private ArrayList<Copy> copyList;
	/** A list to hold all the requests. */
	ArrayList <Request> requestList;
	/** ArrayList to hold all the pending requests. */
	ArrayList<Request> pendingRequests = new ArrayList<>();
	/** A list to hold all the loans. */
	ArrayList<Loan> loanList;
	/** A list to hold all the copies for the selected resource. */
	private ArrayList<Copy> currentCopiesList = new ArrayList<Copy>();
	/** A list to hold all the users in the system. */
	private ArrayList<User> userList;
	/** ArrayList to hold all of the user's current loans. */
	private ArrayList<Loan> userCurrentLoans = new ArrayList<>();
	
	/** The directory to the thumbnail images for the resources. */
	private final String RESOURCE_IMAGE_PATH = "DataFiles/ResourceThumbnails/";	
	/** Used to check if the resources have been searched via search box or not. */
	private boolean isSearch = false;
	
	/** A list view to display the resources with their short descriptions. */
	@FXML private ListView<String> lstShowResource;
	/** A list view to display the copies with their short descriptions. */
	@FXML private ListView<String> lstShowCopies;
	
	/** A button that allows the user to request a copy. */
	@FXML private Button btnRequestCopy;
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/** A text field used to display the resource's unique ID. */
	@FXML private TextField txtResourceID;
	/** A text field used to display the resource's title. */
	@FXML private TextField txtResourceTitle;
	/** A text field used to display the fine per day for the resource. */
	@FXML private TextField txtFinePerDay;
	/** A text field used to display the maximum fine for the resource. */
	@FXML private TextField txtMaxFine;
	/** A text field used to display the year the resource was released. */
	@FXML private TextField txtYear;
	/** A text field used to display the number of copies of the resource. */
	@FXML private TextField txtNumberOfCopies;
	/** An image view used to display the thumbnail image of the resource. */
	@FXML private ImageView imageThumbnail;
	
	/** A text field used to display the language of the book / DVD. */
	@FXML private TextField txtLanguage;
	
	/** A text field used to display the author of the book. */
	@FXML private TextField txtAuthor;
	/** A text field used to display the book's publisher. */
	@FXML private TextField txtPublisher;
	/** A text field used to display the genre of the book. */
	@FXML private TextField txtGenre;
	/** A text field used to display the book's ISBN. */
	@FXML private TextField txtISBN;
	
	/** A text field used to display the director of the DVD. */
	@FXML private TextField txtDirector;
	/** A text field used to display the DVD's runtime. */
	@FXML private TextField txtRuntime;
	/** A list view used to display the DVD's subtitle languages. */
	@FXML private ListView<String>  lstSubLang;
	
	/** A text field used to display the laptop's manufacturer. */
	@FXML private TextField txtManufacturer;
	/** A text field used to display the laptop's model. */
	@FXML private TextField txtModel;
	/** A text field used to display the laptop's operating system. */
	@FXML private TextField txtOperatingSystem;
	
	/** A check box to indicate that the user wants 
	 * to filter the resources to only display books. */
	@FXML private CheckBox cbBook;
	/** A check box to indicate that the user wants 
	 * to filter the resources to only display DVDs. */
	@FXML private CheckBox cbDVD;
	/** A check box to indicate that the user wants 
	 * to filter the resources to only display laptops. */
	@FXML private CheckBox cbLaptop;
	
	/** A text field used to the user's input (partial info) for a resource. */
	@FXML private TextField txtSearchResource;
	
	/**
	 * Displays all the resources with a short description onto the screen.
	 * The method will be called automatically.
	 */
	public void initialize() {
		
		// Gets an ArrayList for each resource and their copies.
		bookList = FileHandling.getBooks();
        dvdList = FileHandling.getDVDs();
        laptopList = FileHandling.getLaptops();
        copyList = FileHandling.getCopies();
        requestList = FileHandling.getRequests();
        loanList = FileHandling.getLoans();
        
        // Adds each resource to the resource ArrayList.
        resourceList.addAll(bookList);
        resourceList.addAll(dvdList);
        resourceList.addAll(laptopList);
        Collections.sort(resourceList, new SortResources());
        
        // Show the resources on list view.
        for (Resource thisResource : resourceList) {
        	lstShowResource.getItems().add(thisResource.toString());
        }  
	}
	
	/**
	 * Adds a request of a selected copy to the request queue.
	 */
	public void handleRequestButtonAction() {
		// Find current user.
		String currentUsername = FileHandling.getCurrentUser();
		User currentUser = null;
		userList = FileHandling.getUsers();
		for (User thisUser : userList) {
			if (thisUser.getUsername().equals(currentUsername)) {
				currentUser = thisUser;
				break;
			}
		}
		
		// Checks if the user has outstanding fines or overdue copies.
		boolean isOverdue = getOverdue(currentUsername);
		if (currentUser.getFine() > 0) {
			Utility.outstandingFines();
			return;
		// Checks if the user has overdue (unreturned) copies.
		} else if (isOverdue) { 
			Utility.overdueCopies();
			return;
		}
		
		int copyID = 0;
		int resourceID = 0;
		int duration = 0;
		Copy requestedCopy = null;
		boolean isCopyFound = false;
		
		for (Copy thisCopy : currentCopiesList) {
			// Needed anyway if no copies are available.
			resourceID = thisCopy.getResourceID(); 
			duration = thisCopy.getLoanDuration();
			// Checks if the user has requested to borrow this resource already.
			boolean requested = isAlreadyRequested(currentUsername, resourceID);
			if (requested) {
				Utility.alreadyRequested();
				return;
			} else if (thisCopy.isAvailable()) {
				copyID = thisCopy.getCopyID();
				requestedCopy = thisCopy;
				isCopyFound = true;
				
				// Set the copy to unavailable.
				String oldCopy = requestedCopy.toStringDetail();
				requestedCopy.setAvailable(false);
				String newCopy = requestedCopy.toStringDetail();
				FileHandling.editCopy(oldCopy, newCopy);
				break;
			}
		}
		
		// Choose the copy to request if they're all unavailable.
		int minCopyID = currentCopiesList.get(0).getCopyID();
		int maxCopyID = currentCopiesList.get(
				currentCopiesList.size() - 1).getCopyID();
		boolean reserved = true; // Set to false if no available copies.
		if (!isCopyFound) {
			copyID = getNextLatestCopyID(resourceID, minCopyID, maxCopyID);
			reserved = false;
		}
		
		// Create request.
		int requestID = getMaxRequestID() + 1;
		LocalDate requestDate = LocalDate.now(); 
		String newRequest = requestID + "," + copyID + "," + resourceID + 
				"," + currentUsername + "," + requestDate + ",false," + reserved + ",";
		
		FileHandling.makeRequest(newRequest);
		setLoanDueDate(isCopyFound, copyID, duration); // Set due date if necessary.
		displayCopies(resourceID); // Refresh copies.
	}
	
	/**
	 * Fetches the maximum request ID of all current requests.
	 * @return The current maximum request ID.
	 */
	public int getMaxRequestID() {
		int maxID;
		
		if (requestList.size() == 0) {
			maxID = 0;
		} else {
			Collections.sort(requestList, new SortRequests());
			int maxIndex = requestList.size() - 1;
			maxID = (requestList.get(maxIndex)).getRequestID();
		}
		return maxID;
	}
	
	/**
	 * Checks if the user has any unreturned overdue copies i.e.
	 * past the due date.
	 * @param currentUsername Username of the user.
	 * @return Whether the user has any overdue copies or not.
	 */
	public boolean getOverdue(String currentUsername) {
		// The same user can't request the same resource twice, but
	    // have this just in case.
		userCurrentLoans.clear();
		
		// Fetch the user's active loans.
		for (Loan loan : loanList) {
			if (currentUsername.equals(loan.getUsername()) && 
					!loan.isReturned()) {
				userCurrentLoans.add(loan);
			}
		}
		
		// Check if there's a due date - due dates are set 
		// when the copy is requested.
		// If there is, check if it's past the due date.
		for (Loan loan : userCurrentLoans) {
			if ((loan.getDueDate()).isEmpty()) {
				// Nothing happens...
			} else {
				int daysPastDueDate = Utility.daysPastDate(
						loan.getDueDate());
				// Will be negative if the due date is in the future.
				if (daysPastDueDate > 0) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if the user has either already requested to borrow a copy of the 
	 * resource or they are currently borrowing a copy of this resource.
	 * @param username The username of the user who made the request.
	 * @param resourceID The resource ID of the resource copy.
	 * @return Whether the user has already requested to borrow the resource 
	 *         already or not.
	 */
	public boolean isAlreadyRequested(String username, int resourceID) {
		// Check requests.
		for (Request request : requestList) {
			if (username.equals(request.getUsername()) && 
					resourceID == request.getResourceID() && 
					!request.getRequestFilled()) {
				return true;
			}
		}
		// Check loans.
		for (Loan loan : loanList) {
			if (username.equals(loan.getUsername()) && 
					resourceID == loan.getResourceID() && 
					!loan.isReturned()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Fetches the copy ID of the most recently requested copy of the 
	 * resource then calculates the next copy to be requested. 
	 * Needed if all the copies are unavailable.
	 * @param resourceID The ID of the requested resource.
	 * @param minCopyID The smallest ID of the copy of the resource.
	 * @param maxCopyID The highest ID of the copy of the resource.
	 * @return The copy ID of the (next) most recently requested copy.
	 */
	public int getNextLatestCopyID(int resourceID, int minCopyID, 
			int maxCopyID) {
		// The same user can't request the same resource twice, but
		// have this just in case.
		pendingRequests.clear();
		
		// Fetch pending requests for copies of the requested resource.
		for (Request request : requestList) {
			if (request.getResourceID() == resourceID && 
					!request.getRequestFilled()) {
				pendingRequests.add(request);
			}
		}
		
		// We know the requests are sorted already.
		int maxIndex = pendingRequests.size() - 1;
		int requestCopyID; 
		// If there's only one copy of the resource.
		if (pendingRequests.size() == 0) {
			requestCopyID = minCopyID;
		} else {
			int copyID = pendingRequests.get(maxIndex).getCopyID();
			
			// Checks if the copyID is at the maximum for the resource.
			if (copyID == maxCopyID) {
				 requestCopyID = minCopyID;
			} else {
				requestCopyID = copyID + 1;
			}	
		}
		return requestCopyID;
	}
	
	/**
	 * Sets the due date of the current loan for the copy if necessary
	 * and displays the appropriate alert after creating a request.
	 * @param isCopyFound If there was any available copies or not.
	 * @param copyID The ID of the requested copy.
	 * @param duration The loan duration of the requested copy.
	 */
	public void setLoanDueDate(boolean isCopyFound, int copyID, int duration) {
		// If there are available copies, add to 'reserved' (reserved for user).
		if (isCopyFound) {
			Utility.requestCreated("Reserved");
		// If no available copies.
		} else {
			// Set the due date to the current loan of the resource
			// if it doesn't have one.
			for (Loan loan : loanList) {
				if (loan.getCopyID() == copyID && 
						(loan.getDueDate()).isEmpty()) {
					
					Loan selectedLoan = loan;
					String oldLoan = selectedLoan.toStringDetail();
					selectedLoan.setDueDate(duration);
					String newLoan = selectedLoan.toStringDetail();
					FileHandling.editLoan(oldLoan, newLoan);
					break;
				}
			}
			Utility.requestCreated("Queue");
		}
		requestList = FileHandling.getRequests();
        loanList = FileHandling.getLoans();
	}
	
	/**
	 * Displays a short description of each copy of a selected
	 * resource in the list view.
	 * @param resourceID The ID of the selected resource.
	 */
	public void displayCopies(int resourceID) {
		lstShowCopies.getItems().clear();
		currentCopiesList.clear();
		for (Copy thisCopy : copyList) {
			if (thisCopy.getResourceID() == resourceID) {
				lstShowCopies.getItems().add(thisCopy.getCopyDescription());
				currentCopiesList.add(thisCopy);
			}
		}
		btnRequestCopy.setDisable(false); // Enable the button.
	}
	
	/**
	 * Displays the full information of a selected resource.
	 */
	public void displayResourceDetails() {
		int selectedIndex = lstShowResource.getSelectionModel()
				.getSelectedIndex();
		// If nothing was selected i.e. clicking the list view.
		if (selectedIndex < 0) {
			return;
		}
		
		// Clear copies list view.
		lstShowCopies.getItems().clear();
		currentCopiesList.clear();
		
		// If it was found by search.
		// You're looking in the same list (searchedList).
		if (isSearch) {
			Resource selectedResource = searchedList.get(selectedIndex);
			String resourceType = selectedResource.getClass().getTypeName();
			switch (resourceType) {
				case "Book":
					displayBookDetails((Book) selectedResource);
					break;
				case "DVD":
					displayDVDDetails((DVD) selectedResource);
					break;
				case "Laptop":
					displayLaptopDetails((Laptop) selectedResource);
					break;
			}
		// If it wasn't found by search.
		} else {
			// Checks if any of the check boxes are selected.
			// Then passes down the selected resource.
			if (cbBook.isSelected()) {
				Book selectedBook = bookList.get(selectedIndex);
				displayBookDetails(selectedBook);	
			} else if (cbDVD.isSelected()) {
				DVD selectedDVD = dvdList.get(selectedIndex);
				displayDVDDetails(selectedDVD);
			} else if (cbLaptop.isSelected()) {
				Laptop selectedLaptop = laptopList.get(selectedIndex);
				displayLaptopDetails(selectedLaptop);
			
			// If none of the check boxes are selected,
			// check which resource it is.
			} else {
				Resource selectedResource = resourceList.get(selectedIndex);
				String resourceType = selectedResource.getClass().getTypeName();
				switch (resourceType) {
					case "Book":
						displayBookDetails((Book) selectedResource);
						break;
					case "DVD":
						displayDVDDetails((DVD) selectedResource);
						break;
					case "Laptop":
						displayLaptopDetails((Laptop) selectedResource);
						break;
				}
			}
		}
	}
	
	/**
	 * Displays the details of the selected book.
	 * @param selectedBook The selected book.
	 */
	public void displayBookDetails(Book selectedBook) {
		//Displays the selected book's details
		txtResourceID.setText(selectedBook.getResourceID() + "");
		txtResourceTitle.setText(selectedBook.getResourceTitle());
		txtYear.setText(selectedBook.getYear() + "");
		txtFinePerDay.setText("£ " + selectedBook.getFinePerDay());
		txtMaxFine.setText("£ " + selectedBook.getMaxFine());
		txtNumberOfCopies.setText(selectedBook.getNumberOfCopies() + "");
		
		//Changes image URL to a file, then converts that to an image.
		File imageURL = new File(RESOURCE_IMAGE_PATH + 
				selectedBook.getThumbnail());
		Image thumbnail = new Image(imageURL.toURI().toString());
		imageThumbnail.setImage(thumbnail);
		
		txtAuthor.setText(selectedBook.getAuthor());
		txtPublisher.setText(selectedBook.getPublisher());
		
		setOptionalFields(selectedBook, "Book"); // Set values for optional fields.
		displayCopies(selectedBook.getResourceID());
		
		//Set other text fields involving other 
		//resources to not applicable.
		txtDirector.setText("N/A");
		txtRuntime.setText("N/A");
		lstSubLang.getItems().clear();
		lstSubLang.getItems().add("N/A");
		txtManufacturer.setText("N/A");
		txtModel.setText("N/A");
		txtOperatingSystem.setText("N/A");
	}
	
	/**
	 * Displays the details of the selected DVD.
     * @param selectedDVD The selected DVD.
	 */
	public void displayDVDDetails(DVD selectedDVD) {
		//Displays the selected DVD's details
		txtResourceID.setText(selectedDVD.getResourceID() + "");
		txtResourceTitle.setText(selectedDVD.getResourceTitle());
		txtYear.setText(selectedDVD.getYear() + "");
		txtFinePerDay.setText("£ " + selectedDVD.getFinePerDay());
		txtMaxFine.setText("£ " + selectedDVD.getMaxFine());
		txtNumberOfCopies.setText(selectedDVD.getNumberOfCopies() + "");
		
		//Changes image URL to a file, then converts that to an image.
		File imageURL = new File(RESOURCE_IMAGE_PATH + 
				selectedDVD.getThumbnail());
		Image thumbnail = new Image(imageURL.toURI().toString());
		imageThumbnail.setImage(thumbnail);
		
		txtDirector.setText(selectedDVD.getDirector());
		txtRuntime.setText(selectedDVD.getRuntime() + " minutes");
		
		lstSubLang.getItems().clear();
		setOptionalFields(selectedDVD, "DVD"); // Set values for optional fields.
		displayCopies(selectedDVD.getResourceID());
		
		// Set other text fields involving other 
		// resources to not applicable.
		txtAuthor.setText("N/A");
		txtGenre.setText("N/A");
		txtISBN.setText("N/A");
		txtPublisher.setText("N/A");
		
		txtManufacturer.setText("N/A");
		txtModel.setText("N/A");
		txtOperatingSystem.setText("N/A");
	}
	
	/**
	 * Displays the details of the selected laptop.
     * @param selectedLaptop  The selected laptop.
	 */
	public void displayLaptopDetails(Laptop selectedLaptop) {
		//Displays the selected laptop's details
		txtResourceID.setText(selectedLaptop.getResourceID() + "");
		txtResourceTitle.setText(selectedLaptop.getResourceTitle());
		txtYear.setText(selectedLaptop.getYear() + "");
		txtFinePerDay.setText("£ " + selectedLaptop.getFinePerDay());
		txtMaxFine.setText("£ " + selectedLaptop.getMaxFine());
		txtNumberOfCopies.setText(selectedLaptop.getNumberOfCopies() + "");
		
		//Changes image URL to a file, then converts that to an image.
		File imageURL = new File(RESOURCE_IMAGE_PATH + 
				selectedLaptop.getThumbnail());
		Image thumbnail = new Image(imageURL.toURI().toString());
		imageThumbnail.setImage(thumbnail);
	
		txtManufacturer.setText(selectedLaptop.getManufacturer());
		txtModel.setText(selectedLaptop.getModel());
		txtOperatingSystem.setText(selectedLaptop.getOperatingSystem());	
		
		displayCopies(selectedLaptop.getResourceID());
		
		// Set other text fields involving other 
		// resources to not applicable.
		txtLanguage.setText("N/A");
		txtGenre.setText("N/A");
		txtAuthor.setText("N/A");
		txtISBN.setText("N/A");
		txtPublisher.setText("N/A");
		txtDirector.setText("N/A");
		txtRuntime.setText("N/A");
		lstSubLang.getItems().clear();
		lstSubLang.getItems().add("N/A");
	}
	
	/**
	 * Sets the values for the resources that have optional fields.
	 * @param selectedResource The resource selected from the list view.
	 * @param resourceType The type of resource to be shown.
	 */
	public void setOptionalFields(Resource selectedResource, 
			String resourceType) {
		switch (resourceType) {
			case "Book":
				Book selectedBook = (Book) selectedResource;
				String genre = selectedBook.getGenre();
				String language = selectedBook.getLanguage();
				String isbn = selectedBook.getISBN();
				
				// Check genre.
				if (genre.isEmpty()) {
					txtGenre.setText("None");
				} else {
					txtGenre.setText(genre);
				}
				
				// Check language.
				if (language.isEmpty()) {
					txtLanguage.setText("None");
				} else {
					txtLanguage.setText(language);
				}
				
				// Check ISBN.
				if (isbn.isEmpty()) {
					txtISBN.setText("None");
				} else { 
					txtISBN.setText(isbn);
				}
				break;
			case "DVD":
				DVD selectedDVD = (DVD) selectedResource;
				language = selectedDVD.getLanguage();
				String[] subLang = selectedDVD.getSubLang();
				
				// Check language.
				if (language.isEmpty()) {
					txtLanguage.setText("None");
				} else {
					txtLanguage.setText(language);
				}
				
				// Check subtitle languages.
				if (subLang.length == 0) {
					lstSubLang.getItems().add("None");
				} else { // Show them if there are subtitle languages.
					for (String lang : subLang) {
						lstSubLang.getItems().add(lang);
					}
				}
				break;
		}
	}
	
	/**
     * Sets the status of the Book check box and makes the 
     * appropriate changes to the resource list when selected.
     */
	public void setCBBookStatus() {
    	// Clears other check boxes if selected.
		cbDVD.setSelected(false);
		cbLaptop.setSelected(false);
		btnRequestCopy.setDisable(true);
		
		// Clears the content of the resource and copy list. 
		lstShowResource.getItems().clear();
		lstShowCopies.getItems().clear();
		currentCopiesList.clear();
		
		if (isSearch) {
			handleResourceSearchAction();
		} else {
			if (cbBook.isSelected()) {
				for (Book thisBook : bookList) {
	            	lstShowResource.getItems().add(thisBook.toString());
	    		}
			// If you're clicking the check box to clear it.
			} else {
				// Show the resources on list view.
	            for (Resource thisResource : resourceList) {
	            	lstShowResource.getItems().add(thisResource.toString());
	            } 
			}
		}
    }
    
    /**
     * Sets the status of the DVD check box and makes the 
     * appropriate changes to the resource list when selected.
     */
	public void setCBDVDStatus() {
    	// Clears other check boxes if selected.
		cbBook.setSelected(false);
		cbLaptop.setSelected(false);
		btnRequestCopy.setDisable(true);
		
		// Clears the content of the resource and copy list. 
		lstShowResource.getItems().clear();
		lstShowCopies.getItems().clear();
		currentCopiesList.clear();
		
		if (isSearch) {
			handleResourceSearchAction();
		} else {
			if (cbDVD.isSelected()) {
				for (DVD thisDVD : dvdList) {
	            	lstShowResource.getItems().add(thisDVD.toString());
	    		}
			// If you're clicking the check box to clear it.
			} else {
				// Show the resources on list view.
	            for (Resource thisResource : resourceList) {
	            	lstShowResource.getItems().add(thisResource.toString());
	            } 
			}
		}
    }
    
    /**
     * Sets the status of the Laptop check box and makes the 
     * appropriate changes to the resource list when selected.
     */
	public void setCBLaptopStatus() {
    	// Clears other check boxes if selected.
		cbBook.setSelected(false);
		cbDVD.setSelected(false);
		btnRequestCopy.setDisable(true);
		
		// Clears the content of the resource and copy list. 
		lstShowResource.getItems().clear();
		lstShowCopies.getItems().clear();
		currentCopiesList.clear();
		
		if (isSearch) {
			handleResourceSearchAction();
		} else {
			if (cbLaptop.isSelected()) {
				for (Laptop thisLaptop : laptopList) {
	    			lstShowResource.getItems().add(thisLaptop.toString());
	    		}
			// If you're clicking the check box to clear it.
			} else {
				// Show the resources on list view.
	            for (Resource thisResource : resourceList) {
	            	lstShowResource.getItems().add(thisResource.toString());
	            } 
			}
		}
    }
    
    /**
     * Searches through the resources using the key words entered
     * in the search box, and displays the related resources.
     */
    public void handleResourceSearchAction() {
    	String keywords = txtSearchResource.getText().trim().toLowerCase();
    	isSearch = true;
    	searchedList.clear(); // Clear ArrayList from previous search.
    	lstShowResource.getItems().clear();
		lstShowCopies.getItems().clear();
		currentCopiesList.clear();
		btnRequestCopy.setDisable(true);
    	
    	// Show all of appropriate resources if there's nothing in 
    	// the search bar.
    	if (keywords.isEmpty()) {
    		isSearch = false;
    		if (cbBook.isSelected()) {
        		setCBBookStatus();
    		} else if (cbDVD.isSelected()) {
    			setCBDVDStatus();
    		} else if (cbLaptop.isSelected()) {
    			setCBLaptopStatus();
    		} else {
    			// Show the resources on list view.
    	        for (Resource thisResource : resourceList) {
    	        	lstShowResource.getItems().add(thisResource.toString());
    	        }  
    		}
    	} else if (cbBook.isSelected()) {
    		for (Book book : bookList) {
    			if ((book.toStringSearch()).contains(keywords)) {
    				searchedList.add(book);
    			}
    		}
    	} else if (cbDVD.isSelected()) {
    		for (DVD dvd : dvdList) {
    			if ((dvd.toStringSearch()).contains(keywords)) {
    				searchedList.add(dvd);
    			}
    		}
    	} else if (cbLaptop.isSelected()) {
    		for (Laptop laptop : laptopList) {
    			if ((laptop.toStringSearch()).contains(keywords)) {
    				searchedList.add(laptop);
    			}
    		}
    	// If the type of resource is unknown via checkbox.
    	} else {
    		for (Resource resource : resourceList) {
    			if ((resource.toStringSearch()).contains(keywords)) {
    				searchedList.add(resource);
    			}
    		}
    	}
    	
    	// Display filtered items to the resource list view. 
    	for (Resource thisResource : searchedList) {
    		lstShowResource.getItems().add(thisResource.toString());
    	}
    }
    
    /**
	 * Goes back to the User Dashboard when the button is clicked.
	 */
	public void handleBackButtonAction() {
		final String USER_DASHBOARD_TITLE = "User Dashboard";
		// Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass()
					.getResource("FXMLFiles/UserDashboard.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle(USER_DASHBOARD_TITLE);
			primaryStage.show(); // Displays the new stage.
		} catch (IOException ex) {
			// Catches an IO exception such as that where the FXML
            // file is not found.
            ex.printStackTrace();
		}
	}
}
