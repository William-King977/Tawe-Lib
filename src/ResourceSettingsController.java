import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The controller for the Resource Settings page.
 * Allows the librarian to edit existing resources and create new resources.
 * @author William King
 */
public class ResourceSettingsController {
	
	/** A list to hold all the resources .*/
	private ArrayList<Resource> resourceList = new ArrayList<Resource>();
	/** A list to hold all the books. */
	private ArrayList<Book> bookList;	
	/** A list to hold all the DVDs. */
	private ArrayList<DVD> dvdList;
	/** A list to hold all the laptops. */
	private ArrayList<Laptop> laptopList;
	
	/** The directory to the thumbnail images for the resources. */
	private final String RESOURCE_IMAGE_PATH = "DataFiles/ResourceThumbnails/";	
	
	/** A list view to display the resources with their short descriptions. */
	@FXML private ListView<String> listShowResource;
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
	/** A text field used to display the DVD's subtitle language. */
	@FXML private TextField txtSubLang;
	
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
	
	/**
	 * Displays all the resources with a short description onto the screen.
	 * The method will be called automatically.
	 */
	public void initialize() {
		
		// Gets an ArrayList for each resource.
		bookList = FileHandling.getBooks();
        dvdList = FileHandling.getDVDs();
        laptopList = FileHandling.getLaptops();
        
        // Displays all resources, then adds each resource to the 
        // resource ArrayList.
        for (Book thisBook : bookList) {
        		listShowResource.getItems().add(thisBook.toString()); 	
        		resourceList.add(thisBook);
        }    
        
        for (DVD thisDVD : dvdList) {
        		listShowResource.getItems().add(thisDVD.toString()); 	
        		resourceList.add(thisDVD);
        } 
        
        for (Laptop thisLaptop : laptopList) {
    		listShowResource.getItems().add(thisLaptop.toString()); 
    		resourceList.add(thisLaptop);
        } 
	}
	
	/**
	 * Displays the full information of a selected resource.
	 */
	public void displayResourceDetails() {
		int selectedIndex = listShowResource.getSelectionModel()
				.getSelectedIndex();
		
		// Checks if any of the check boxes are selected.
		// Then passes down the selected resource.
		if (cbBook.isSelected()) {
				Book selectedBook = bookList.get(selectedIndex);
				displayBookDetails(selectedBook);	
		} else if (cbDVD.isSelected() == true) {
				DVD selectedDVD = dvdList.get(selectedIndex);
				displayDVDDetails(selectedDVD);
		} else if (cbLaptop.isSelected() == true) {
				Laptop selectedLaptop = laptopList.get(selectedIndex);
				displayLaptopDetails(selectedLaptop);
		// If none of the check boxes are selected,
		// check which resource it is.
		} else {
			Resource selectedResource = resourceList.get(selectedIndex);
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
		
		txtLanguage.setText(selectedBook.getLanguage());
		txtGenre.setText(selectedBook.getGenre());
		txtAuthor.setText(selectedBook.getAuthor());
		txtISBN.setText(selectedBook.getISBN());
		txtPublisher.setText(selectedBook.getPublisher());
		
		//Set other text fields involving other 
		//resources to not applicable.
		txtDirector.setText("N/A");
		txtRuntime.setText("N/A");
		txtSubLang.setText("N/A");
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
		
		txtLanguage.setText(selectedDVD.getLanguage());
		txtGenre.setText(selectedDVD.getGenre());
		txtDirector.setText(selectedDVD.getDirector());
		txtRuntime.setText(selectedDVD.getRuntime() + " minutes");
		txtSubLang.setText(selectedDVD.getSubLang());
		
		//Set other text fields involving other 
		//resources to not applicable.
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
		
		//Set other text fields involving other 
		//resources to not applicable.
		txtLanguage.setText("N/A");
		txtGenre.setText("N/A");
		txtAuthor.setText("N/A");
		txtISBN.setText("N/A");
		txtPublisher.setText("N/A");
		txtDirector.setText("N/A");
		txtRuntime.setText("N/A");
		txtSubLang.setText("N/A");
	}
	
	/**
     * Sets the status of the Book check box and makes the 
     * appropriate changes to the resource list when selected.
     */
    public void setCBBookStatus() {
    	// Clears other check boxes if selected.
		cbDVD.setSelected(false);
		cbLaptop.setSelected(false);
		
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
	 * Displays a page where the librarian can edit a selected resource.
	 */
	public void handleEditResourceButtonAction() {
	}
	
	/**
	 * Finds the type of resource that the resource to be edited is and 
	 * prepares the edit resource controller based upon that.
	 * @param selectedResource The resource to be edited.
	 * @param editResource An instance of the edit resource controller.
	 */
	public void searchEditedResource(Resource selectedResource) { 
			//EditResourceController editResource) { INCLUDE PARAMETER ONCE CLASS IS MADE.
	}
	
	/**
	 * Displays a page where the librarian can create a new resource.
	 * @throws IOException Throws an exception to be caught when the 
	 *         FXML file isn't available.
	 */
	public void handleCreateNewResourceButtonAction() {
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
				.getResource("FXMLFiles/UserDashboardStaff.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
	}
}
