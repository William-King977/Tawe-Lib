import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controller for the Edit Resources page.
 * Allows librarians to edit the details of existing resources.
 * @author William King
 */
public class EditResourceController {
	/** The directory to the thumbnail images for the resources. */
	private final String RESOURCE_IMAGE_PATH = 
			"DataFiles/ResourceThumbnails/";
	
    /** An array list that holds all the existing books. */
    private ArrayList<Book> bookList;
    /** An array list that holds all the existing DVDs. */
    private ArrayList<DVD> dvdList;
    /** An array list that holds all the existing laptops.*/
    private ArrayList<Laptop> laptopList;
    
    /** Holds the files of all the resource thumbnail images. */
    private File[] resourceImageList;
    
    /** Local storage of the resource being edited. */
    private Resource resourceBeingEdited;
    
    /** Used to check if the edited resource is a book. */
    private boolean isBook;
    /** Used to check if the edited resource is a DVD. */
    private boolean isDVD;
    /** Used to check if the edited resource is a laptop. */
    private boolean isLaptop;
    
    /** A combo box used to select an image for a resource. */
	@FXML private ComboBox<String> cmbResourceImage;
	/** A list view used to display the DVD's subtitle languages. */
	@FXML private ListView<String>  listSubLang;
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/** An image view to hold the resource thumbnail. */
	@FXML private ImageView imageThumbnail;
	
	/** A text field used to hold the resource's title. */
	@FXML private TextField txtResourceTitle;
	/** A text field used to hold the year the resource was released. */
	@FXML private TextField txtYear;
	/** A text field used to hold the genre of the book / DVD. */
	@FXML private TextField txtGenre;
	/** A text field used to hold the language of the book / DVD. */
	@FXML private TextField txtLanguage;
	
	/** A text field used to hold the author of the book. */
	@FXML private TextField txtAuthor;
	/** A text field used to hold the book's publisher. */
	@FXML private TextField txtPublisher;
	/**  A text field used to hold the book's ISBN. */
	@FXML private TextField txtISBN;
	
	/** A text field used to hold the director of the DVD. */
	@FXML private TextField txtDirector;
	/** A text field used to hold the DVD's runtime. */
	@FXML private TextField txtRuntime;
	/** A text field used to hold the DVD's subtitle language. */
	@FXML private TextField txtSubLang;
	
	/** A text field used to hold the laptop's manufacturer. */
	@FXML private TextField txtManufacturer;
	/** A text field used to hold the laptop's model. */
	@FXML private TextField txtModel;
	/** A text field used to hold the laptop's operating system. */
	@FXML private TextField txtOperatingSystem;
	
	/**
	 * Sets up the ArrayLists for each resource.
	 */
	public void initialize() {
	}
	
	/**
	 * Displays the current details of the resource to be edited in the 
	 * appropriate text fields.
	 * @param editedResource The resource to be edited.
	 */
	public void editResource(Resource editedResource) {
	}
	
	/**
	 * Validates the changes made to the edited resource and saves them
	 * if they are valid.
	 */
	public void handleSaveButtonAction() {
	}
	
	/**
	 * Adds the inputted subtitle language from the text box
	 * into the list view.
	 */
	public void handleAddLanguageButtonAction() {
	}
	
	/**
	 * Removes a selected subtitle language from the list view.
	 */
	public void handleRemoveLanguageButtonAction() {
	}
	
	/**
	 * Goes back to the previous page when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the 
	 *         fxml file isn't available.
	 */
	public void handleBackButtonAction() throws IOException {
		//Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXML/ResourceSettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); //displays the new stage.
	}
}
