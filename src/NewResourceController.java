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
 * Controller for the Create New Resource page.
 * Allows librarians to create new resources (new book, DVD or laptop).
 * @author William King
 */
public class NewResourceController {
	
	/** The directory to the thumbnail images for the resources. */
	private final String RESOURCE_IMAGE_PATH = "DataFiles/ResourceThumbnails/";
	/** The number of copies a resource has. */
	private final int NUMBER_OF_COPIES = 5;
	
    /** An array list that holds all the existing books. */
    private ArrayList<Book> bookList;
    /** An array list that holds all the existing DVDs. */
    private ArrayList<DVD> dvdList;
    /** An array list that holds all the existing laptops. */
    private ArrayList<Laptop> laptopList;
    /** Keeps track of the current languages in the list view */
    private ArrayList<String> currentLangList =  new ArrayList<String>();
    /** Holds the files of all resource images. */
    private File[] resourceImageList;
    
    /** A combo box used to select an image for a resource. */
	@FXML private ComboBox<String> cmbResourceImage;
	/** A list view used to display the DVD's subtitle languages. */
	@FXML private ListView<String>  listSubLang;
	/** The add button for the subtitle languages list view. */
	@FXML private Button btnAdd;
	/** The remove button for the subtitle languages list view.. */
	@FXML private Button btnRemove;	
	/** The back button for the page. */
	@FXML private Button btnBack;
	
	/** An image view to hold the resource thumbnail. */
	@FXML private ImageView imageThumbnail;
	
	/** A text field used to hold the resource's title. */
	@FXML private TextField txtResourceTitle;
	/** A text field used to hold the year the resource was released. */
	@FXML private TextField txtYear;	
	/** A text field used to hold the number of copies that a resource has. */
	@FXML private TextField txtNumberOfCopies;
	/** A text field used to hold the genre of the book / DVD. */
	@FXML private TextField txtGenre;
	/** A text field used to hold the language of the book / DVD. */
	@FXML private TextField txtLanguage;
	
	/** A text field used to hold the author of the book. */
	@FXML private TextField txtAuthor;
	/** A text field used to hold the book's publisher. */
	@FXML private TextField txtPublisher;
	/** A text field used to hold the book's ISBN. */
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
	
	/** A check box to indicate that the librarian wants to create a new book. */
	@FXML private CheckBox cbBook;
	/** A check box to indicate that the librarian wants to create a new DVD. */
	@FXML private CheckBox cbDVD;
	/** A check box to indicate that the librarian wants to create a new laptop. */
	@FXML private CheckBox cbLaptop;
	
	/**
     * Sets up the ArrayLists for each resource.
     * This method will run automatically.
     */
	public void initialize() {
		bookList = FileHandling.getBooks();
        dvdList = FileHandling.getDVDs();
        laptopList = FileHandling.getLaptops();
        
        //Creates an array of all resource images.
        File folder = new File(RESOURCE_IMAGE_PATH);
        resourceImageList = folder.listFiles();

        for (File file : resourceImageList) {
        	if (file.isFile()) {
        		cmbResourceImage.getItems().add(file.getName());
        	}
        }
	}
	
	/**
     * Allows the librarian to select a resource thumbnail and
     * displays it onto the screen.
     */
    public void handleResourceThumbnailComboBoxAction() {
    	//Gets the position of the selected thumbnail.
		int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		File imageURL = resourceImageList[selectedIndex];
        Image profilePicture = new Image(imageURL.toURI().toString());
		imageThumbnail.setImage(profilePicture);
    }
    
    /**
     * Sets the status of the Book check box and makes appropriate
     * changes to the other check boxes.
     */
    public void setCBBookStatus() {
    }
    
    /**
     * Sets the status of the DVD check box and makes appropriate
     * changes to the other check boxes.
     */
    public void setCBDVDStatus() {
    }
    
    /**
     * Sets the status of the Laptop check box and makes appropriate
     * changes to the other check boxes.
     */
    public void setCBLaptopStatus() {
    }
	
	/**
     * Creates a new resource based on the information typed in.
     */
    public void handleCreateResourceButtonAction() {
    }
    
    /**
	 * Adds the inputted subtitle language from the text box
	 * into the list view.
	 */
	public void handleAddLanguageButtonAction() {
		String language = txtSubLang.getText().trim();
		// Checks if there's no duplicate languages.
		boolean languageExist;
		boolean hasLetter = Utility.isAlphaLanguage(language);
		 
		if (language.length() == 0) {
			Utility.languageNotEntered();
			return;
		} else if (!hasLetter) {
			Utility.nonAlphaError();
			return;
		}

		languageExist = Utility.isLanguageExist(language, currentLangList);
		if (languageExist) {
			Utility.languageExists();
			return;
		}
		listSubLang.getItems().add(language);
		currentLangList.add(language);
		txtSubLang.clear();
	}
	
	/**
	 * Removes a selected subtitle language from the list view.
	 */
	public void handleRemoveLanguageButtonAction() {
		int selectedIndex = listSubLang.getSelectionModel().getSelectedIndex();
		
		if (currentLangList.size() == 0) {
			Utility.languageListEmpty();
			return;
		} else if (selectedIndex < 0) {
			Utility.languageNotSelected();
			return;
		}
		listSubLang.getItems().remove(selectedIndex);
		currentLangList.remove(selectedIndex);
	}
	
	/**
	 * Goes back to the previous page when the button is clicked.
	 * @throws IOException Throws an exception to be caught when the 
	 *                     FXML file isn't available.
	 */
	public void handleBackButtonAction() throws IOException {
		//Closes the window.
		Stage stage = (Stage) btnBack.getScene().getWindow();
		stage.close();
		Stage primaryStage = new Stage();
		Parent root = FXMLLoader.load(getClass()
				.getResource("FXMLFiles/ResourceSettings.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show(); // Displays the new stage.
	}

}
