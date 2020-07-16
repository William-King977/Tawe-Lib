import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
	
	/** The directory to the thumbnail images for the resources. */
	private final String RESOURCE_IMAGE_PATH = "DataFiles/ResourceThumbnails/";	
	/** Used to check if the resources have been searched via search box or not. */
	private boolean isSearch = false;
	
	/** A list view to display the resources with their short descriptions. */
	@FXML private ListView<String> listShowResource;
	/** A list view to display the copies with their short descriptions. */
	@FXML private ListView<String> listShowCopies;
	/** The back button for the page. */
	@FXML private Button btnBack;
	/** A button that leads to the Edit Resource page. */
	@FXML private Button btnEditResource;
	/** A button that leads to the Create New Resource page. */
	@FXML private Button btnCreateResource;
	
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
	
	/** A text field used to display the genre of the book / DVD. */
	@FXML private TextField txtGenre;
	/** A text field used to display the language of the book / DVD. */
	@FXML private TextField txtLanguage;
	
	/** A text field used to display the author of the book. */
	@FXML private TextField txtAuthor;
	/** A text field used to display the book's publisher. */
	@FXML private TextField txtPublisher;
	/** A text field used to display the book's ISBN. */
	@FXML private TextField txtISBN;
	
	/** A text field used to display the director of the DVD. */
	@FXML private TextField txtDirector;
	/** A text field used to display the DVD's runtime. */
	@FXML private TextField txtRuntime;
	/** A list view used to display the DVD's subtitle languages. */
	@FXML private ListView<String>  listSubLang;
	
	/** A text field used to display the laptop's manufacturer. */
	@FXML private TextField txtManufacturer;
	/** A text field used to display the laptop's model. */
	@FXML private TextField txtModel;
	/** A text field used to display the laptop's operating system. */
	@FXML private TextField txtOperatingSystem;
	
	/** A check box to indicate that the librarian wants 
	 * to filter the resources to only display books. */
	@FXML private CheckBox cbBook;
	/** A check box to indicate that the librarian wants 
	 * to filter the resources to only display DVDs. */
	@FXML private CheckBox cbDVD;
	/** A check box to indicate that the librarian wants 
	 * to filter the resources to only display laptops. */
	@FXML private CheckBox cbLaptop;
	
	/** A text field used to the user's input (partial info) for a resource. */
	@FXML private TextField txtSearchResource;
	
	/**
	 * Displays all the resources with a short description onto the screen.
	 * The method will be called automatically.
	 */
	public void initialize() {
		listShowResource.getItems().clear();
		resourceList.clear();
		
		// Gets an ArrayList for each resource and their copies.
		bookList = FileHandling.getBooks();
        dvdList = FileHandling.getDVDs();
        laptopList = FileHandling.getLaptops();
        copyList = FileHandling.getCopies();
        
        // Adds each resource to the resource ArrayList.
        for (Book thisBook : bookList) {
        	resourceList.add(thisBook);
        }    
        for (DVD thisDVD : dvdList) {
        	resourceList.add(thisDVD);
        } 
        for (Laptop thisLaptop : laptopList) { 
    		resourceList.add(thisLaptop);
        } 
        
        Utility.sortResources(resourceList); // Sorts resources on list view.
        // Show the resources on list view.
        for (Resource thisResource : resourceList) {
        	listShowResource.getItems().add(thisResource.toString());
        }  
	}
	
	/**
	 * Adds a request of a selected copy to the request queue.
	 */
	public void handleRequestButtonAction() {
	}
	
	/**
	 * Displays a short description of each copy of a selected
	 * resource in the list view.
	 * @param resourceID The ID of the selected resource.
	 */
	public void displayCopies(int resourceID) {
		for (Copy thisCopy : copyList) {
			if (thisCopy.getResourceID() == resourceID) {
				listShowCopies.getItems().add(thisCopy.getCopyDescription());
			}
		}
	}
	
	/**
	 * Displays the full information of a selected resource.
	 */
	public void displayResourceDetails() {
		
		int selectedIndex = listShowResource.getSelectionModel()
				.getSelectedIndex();
		// If nothing was selected i.e. clicking the list view.
		if (selectedIndex < 0) {
			return;
		}
		
		// Clear copies list view.
		listShowCopies.getItems().clear();
		
		// Checks if any of the check boxes are selected.
		// Then passes down the selected resource.
		if (cbBook.isSelected()) {
			// Goes through appropriate ArrayLists based on whether the resources
			// were searched or not.
			if (isSearch) {
				Book selectedBook = (Book) searchedList.get(selectedIndex);
				displayBookDetails(selectedBook);
			} else {
				Book selectedBook = bookList.get(selectedIndex);
				displayBookDetails(selectedBook);	
			}
		} else if (cbDVD.isSelected() == true) {
			if (isSearch) {
				DVD selectedDVD = (DVD) searchedList.get(selectedIndex);
				displayDVDDetails(selectedDVD);
			} else {
				DVD selectedDVD = dvdList.get(selectedIndex);
				displayDVDDetails(selectedDVD);
			}
		} else if (cbLaptop.isSelected() == true) {
			if (isSearch) {
				Laptop selectedLaptop = (Laptop) searchedList.get(selectedIndex);
				displayLaptopDetails(selectedLaptop);
			} else {
				Laptop selectedLaptop = laptopList.get(selectedIndex);
				displayLaptopDetails(selectedLaptop);
			}
		// If none of the check boxes are selected,
		// check which resource it is.
		} else {
			Resource selectedResource;
			if (isSearch) {
				selectedResource = searchedList.get(selectedIndex);
			} else {
				selectedResource = resourceList.get(selectedIndex);
			}
			
			// Checks if the selected resource is a Book
			for (Book thisBook : bookList) {
				if (thisBook.getResourceID() == 
						selectedResource.getResourceID()) {
					displayBookDetails(thisBook);		
				}
			}
				
			// Checks if the selected resource is a DVD
			for (DVD thisDVD : dvdList) {
				if (thisDVD.getResourceID() == 
						selectedResource.getResourceID()) {
					displayDVDDetails(thisDVD);
				}
			}
				
			//Otherwise, check if the selected resource is a Laptop
			for (Laptop thisLaptop : laptopList) {
				if (thisLaptop.getResourceID() == 
						selectedResource.getResourceID()) {
					displayLaptopDetails(thisLaptop);	
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
		listSubLang.getItems().clear();
		listSubLang.getItems().add("N/A");
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
		
		listSubLang.getItems().clear();
		setOptionalFields(selectedDVD, "DVD"); // Set values for optional fields.
		displayCopies(selectedDVD.getResourceID());
		
		// Set other text fields involving other 
		// resources to not applicable.
		txtAuthor.setText("N/A");
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
		listSubLang.getItems().clear();
		listSubLang.getItems().add("N/A");
	}
	
	/**
	 * Sets the values for the resources that have optional fields.
	 * @param selectedResource The resource selected from the list view.
	 * @param resourceType The type of resource to be shown.
	 */
	public void setOptionalFields(Resource selectedResource, 
			String resourceType) {
		if (resourceType.equals("DVD")) {
			DVD selectedDVD = (DVD) selectedResource;
			String genre = selectedDVD.getGenre();
			String language = selectedDVD.getLanguage();
			String[] subLang = selectedDVD.getSubLang();
			
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
			
			// Check subtitle languages.
			if (subLang.length == 0) {
				listSubLang.getItems().add("None");
			} else { // Show them if there are subtitle languages.
				for (String lang : subLang) {
					listSubLang.getItems().add(lang);
				}
			}
		} else if (resourceType.equals("Book")) {
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
		isSearch = false;
		
		// Clears the content of the resource list if any. 
		listShowResource.getItems().clear();
		
		if (cbBook.isSelected()) {
    		for (Book thisBook : bookList) {
            	listShowResource.getItems().add(thisBook.toString());
    		}
    	// If you're clicking the Book check box to clear it. 
    	// Clears the whole list, then show all resources, if 
    	// the other check boxes are cleared as well.
	    } else if (cbBook.isSelected() == false && 
	    		cbDVD.isSelected() == false &&
	    		cbLaptop.isSelected() == false) {
	    	initialize();
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
		isSearch = false;
		
		// Clears the content of the resource list if any. 
		listShowResource.getItems().clear();
		
		if (cbDVD.isSelected() == true) {
    		for (DVD thisDVD : dvdList) {
            	listShowResource.getItems().add(thisDVD.toString());
    		}
    	// If you're clicking the DVD check box to clear it. 
    	// Clears the whole list, then show all resources, if 
    	// the other check boxes are cleared as well.
	    } else if (cbBook.isSelected() == false && 
	    		cbDVD.isSelected() == false &&
	    		cbLaptop.isSelected() == false) {
        	initialize();
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
		isSearch = false;
		
		// Clears the content of the resource list if any. 
		listShowResource.getItems().clear();
		
		if (cbLaptop.isSelected() == true) {
    		for (Laptop thisLaptop : laptopList) {
    			listShowResource.getItems().add(thisLaptop.toString());
    		}
    	// If you're clicking the Laptop check box to clear it. 
    	// Clears the whole list, then show all resources, if 
    	// the other check boxes are cleared as well.
	    } else if (cbBook.isSelected() == false && 
	    		cbDVD.isSelected() == false &&
	    		cbLaptop.isSelected() == false) {
        	initialize();
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
    	listShowResource.getItems().clear();
    	
    	// Show all of appropriate resources if there's nothing in 
    	// the search bar.
    	if (keywords.isEmpty()) {
    		isSearch = false;
    		if (cbBook.isSelected()) {
        		for (Book thisBook : bookList) {
                	listShowResource.getItems().add(thisBook.toString());
        		}
    		} else if (cbDVD.isSelected()) {
    			for (DVD thisDVD : dvdList) {
                	listShowResource.getItems().add(thisDVD.toString());
        		}
    		} else if (cbLaptop.isSelected()) {
    			for (Laptop thisLaptop : laptopList) {
                	listShowResource.getItems().add(thisLaptop.toString());
        		}
    		} else {
    			initialize(); // Shows all resources.
    		}
    		return;
    	}
    	
    	// Search appropriate ArrayLists to fetch related resources.
    	if (cbBook.isSelected()) {
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
    		listShowResource.getItems().add(thisResource.toString());
    	}
    }
    
    /**
	 * Goes back to the User Dashboard when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the 
	 *         FXML file isn't available.
	 */
	public void handleBackButtonAction() throws IOException {
		//Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/UserDashboard.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
	}
}