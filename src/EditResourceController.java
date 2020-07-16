import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    /** Keeps track of the current languages in the list view */
    private ArrayList<String> currentLangList =  new ArrayList<String>();
    
    /** Holds the files of all the resource thumbnail images. */
    private File[] resourceImageList;
    
    /** Local storage of the resource being edited. */
    private Resource resourceBeingEdited;
    
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
	 * Displays the current details of the resource to be edited in the 
	 * appropriate text fields.
	 * @param editedResource The resource to be edited.
	 */
	public void editResource(Resource editedResource) {
		// Keeps local storage of the edited resource.
		this.resourceBeingEdited = editedResource;
		String resourceType = editedResource.getClass().getTypeName();
		
		// Displays the resource's editable details on screen in the
		// appropriate text fields.
    	txtResourceTitle.setText(editedResource.getResourceTitle());
    	txtYear.setText(editedResource.getYear() + "");
    	
    	File imageURL = new File(RESOURCE_IMAGE_PATH 
				+ editedResource.getThumbnail());
        Image resourceImage = new Image(imageURL.toURI().toString());
		imageThumbnail.setImage(resourceImage);
    	
    	if (resourceType.equals("Book")) {
    		Book editedBook = (Book) editedResource;
    		setBookFields(editedBook);
    	} else if (resourceType.equals("DVD")) {
    		DVD editedDVD = (DVD) editedResource;
    		setDVDFields(editedDVD);
    	} else if (resourceType.equals("Laptop")) {
			Laptop editedLaptop = (Laptop) editedResource;
			setLaptopFields(editedLaptop);
    	}	
	}
	
	/**
	 * Validates the changes made to the edited resource and saves them
	 * if they are valid.
	 * @throws IOException Throws an exception when a file cannot be written.
	 */
	public void handleSaveButtonAction() throws IOException {
		String resourceType = resourceBeingEdited.getClass().getTypeName();
		if (resourceType.equals("Book")) {
			validateEditedBook();
		} else if (resourceType.equals("DVD")) {
			validateEditedDVD();
		} else if (resourceType.equals("Laptop")) {
			validateEditedLaptop();
		}
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
     * Validates the changes made to the edited book.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void validateEditedBook() throws IOException {
    	String resourceTitle = txtResourceTitle.getText().trim();
    	String strYear = txtYear.getText().trim(); 
    	String author = txtAuthor.getText().trim();
    	String publisher = txtPublisher.getText().trim();
    	String genre = txtGenre.getText().trim();
    	String language = txtLanguage.getText().trim();
    	String isbn = txtISBN.getText().trim();
    	String imageName;
    	
    	//Gets the position of the selected resource image.
		int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		//Sets a new resource image if it has been selected.
		if (selectedIndex > -1) {
			imageName = resourceImageList[selectedIndex].getName();
		} else {
			//Sets to the previous resource image.
			imageName = resourceBeingEdited.getThumbnail();
		}
		
		//Validation applied to the inputed values.
    	boolean bookFieldsFilled = Utility.isBookFieldFilled(resourceTitle, 
    			strYear, author, publisher); 
    	boolean isNum = Utility.isIntResource(strYear);
    	boolean isAlpha = Utility.isAlphaBook(author, publisher, 
    			genre, language);
    	
    	if (!bookFieldsFilled) {
    		Utility.missingFields();
			return;
		} else if (!isNum) {
			Utility.nonIntegerError();
			return;
		} else if (!isAlpha) {
			Utility.nonAlphaError();
			return;
		}
    	
    	// It's assumed that year is an integer.
		int year = Integer.parseInt(strYear);
		
		boolean bookExists = Utility.isBookExist(resourceTitle, year,
				imageName, author, publisher, genre, 
				language, isbn, bookList);
		
		// Checks if the entered details match with an existing book.
		if (bookExists) {
			Utility.resourceExistsEdit();
			return;
		}
		
		Book editedBook = (Book) resourceBeingEdited;
		String oldBook = editedBook.toStringDetail();
		String newBook = resourceBeingEdited.getResourceID() + 
				"," + resourceTitle + "," + year + "," + imageName+ 
				"," + resourceBeingEdited.getNumberOfCopies() + 
				"," + genre + "," + language + "," + author + 
				"," + publisher + "," + isbn + ",";
		
		FileHandling.editResource(oldBook, newBook, "Book");
		handleBackButtonAction();
    }
    
    /**
     * Validates the changes made to the edited DVD.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void validateEditedDVD() throws IOException {
    	String resourceTitle = txtResourceTitle.getText().trim();
    	String strYear = txtYear.getText().trim(); 
    	String[] subLang = currentLangList.toArray(
    			new String[currentLangList.size()]);
    	String director = txtDirector.getText().trim();
    	String strRuntime = txtRuntime.getText().trim();
    	String genre = txtGenre.getText().trim();
    	String language = txtLanguage.getText().trim();
    	String imageName;
    	
    	//Gets the position of the selected resource image.
    	int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		//Sets a new resource image if it has been selected.
		if (selectedIndex > -1) {
			imageName = resourceImageList[selectedIndex].getName();
		} else {
			//Sets to the previous resource image.
			imageName = resourceBeingEdited.getThumbnail();
		}
		
		//Validation applied to the inputed values.
    	boolean dvdFieldsFilled = Utility.isDVDFieldFilled(resourceTitle, 
    			strYear, director, strRuntime);
    	boolean isNum = Utility.isIntResource(strYear);
    	boolean isDouble = Utility.isDoubleResource(strRuntime);
    	boolean isAlpha = Utility.isAlphaDVD(director, genre, language);
    	
    	if (!dvdFieldsFilled) {
			Utility.missingFields();
			return;
		} else if (!isNum) {
			Utility.nonIntegerError();
			return;
		} else if (!isDouble) {
			Utility.nonDoubleError();
			return;
		} else if (!isAlpha) {
	    	Utility.nonAlphaError();
			return;
		}
    	
    	// It's assumed that year is an integer and that runtime is a double.
		int year = Integer.parseInt(strYear);
		double runtime = Double.parseDouble(strRuntime);
		boolean dvdExists = Utility.isDVDExist(resourceTitle, year, 
				imageName, director, runtime, subLang, genre, language, 
				dvdList);
		
		// Checks if the entered details match with an existing DVD.	
		if (dvdExists) {
			Utility.resourceExistsEdit();
			return;
		}
		
		// Ensures sub languages are in format of 'lang;lang;lang...'
		String strSubLang = "";
		for (int i = 0; i < subLang.length; i++) {
			if (i == 0) {
				strSubLang = subLang[0];
			} else {
				strSubLang = strSubLang + ";" + subLang[i];
			}
		}
		
		DVD editedDVD = (DVD) resourceBeingEdited;
		String oldDVD = editedDVD.toStringDetail();
		String newDVD = resourceBeingEdited.getResourceID() + 
				"," + resourceTitle + "," + year + "," + imageName + 
				"," + resourceBeingEdited.getNumberOfCopies() + 
				"," + genre + "," + language + "," + director + 
				"," + runtime + "," + strSubLang + ",";
		
		FileHandling.editResource(oldDVD, newDVD, "DVD");
		handleBackButtonAction();
    }
    
    /**
     * Validates the changes made to the edited laptop.
     * @throws IOException Throws an exception when a file cannot be written.
     */
    public void validateEditedLaptop() throws IOException {
    	String resourceTitle = txtResourceTitle.getText().trim();
    	String strYear = txtYear.getText().trim();
    	String manufacturer = txtManufacturer.getText().trim();
    	String model = txtModel.getText().trim();
    	String operatingSystem = txtOperatingSystem.getText().trim();
    	String imageName;
    	
    	//Gets the position of the selected resource image.
    	int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		//Sets a new resource image if it has been selected.
		if (selectedIndex > -1) {
			imageName = resourceImageList[selectedIndex].getName();
		} else {
			//Sets to the previous resource image.
			imageName = resourceBeingEdited.getThumbnail();
		}
		
		//Validation applied to the inputted values.
    	boolean laptopFieldsFilled = Utility.isLaptopFieldFilled(resourceTitle, 
    			strYear, manufacturer, model, operatingSystem);
    	boolean isNum = Utility.isIntResource(strYear);
    	boolean isAlpha = Utility.isAlphaLaptop(resourceTitle, manufacturer);
    	
    	if (!laptopFieldsFilled) {
    		Utility.missingFields();
			return;
    	} else if (!isNum) {
    		Utility.nonIntegerError();
			return;
    	} else if (!isAlpha) {
	    	Utility.nonAlphaError();
	    	return;
    	}
    	
    	// It's assumed that year is an integer.
    	int year = Integer.parseInt(strYear);
   		
   		boolean laptopExists = Utility.isLaptopExist(resourceTitle, year, 
				imageName, manufacturer, model, operatingSystem, laptopList);
   		
   	    // Checks if the entered details match with an existing laptop.
   		if (laptopExists) {
			Utility.resourceExistsEdit();
			return;
		}
   		
   		Laptop editedLaptop = (Laptop) resourceBeingEdited;
		String oldLaptop = editedLaptop.toStringDetail();
		String newLaptop = resourceBeingEdited.getResourceID() + 
				"," + resourceTitle + "," + year + "," + imageName + 
				"," + resourceBeingEdited.getNumberOfCopies() + 
				"," + manufacturer + "," + model + "," + operatingSystem + ",";
		
		FileHandling.editResource(oldLaptop, newLaptop, "Laptop");
		handleBackButtonAction();
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
     * Sets the text fields for the edited book.
     */
    public void setBookFields(Book editedBook) {
		
    	txtGenre.setVisible(true);
		txtLanguage.setVisible(true);
		
		txtAuthor.setVisible(true);
		txtPublisher.setVisible(true);
		txtISBN.setVisible(true);
		
		txtDirector.setVisible(false);
		txtRuntime.setVisible(false);
		txtSubLang.setVisible(false);
		listSubLang.setVisible(false);
		btnAdd.setVisible(false);
		btnRemove.setVisible(false);
		
		txtManufacturer.setVisible(false);
		txtModel.setVisible(false);
		txtOperatingSystem.setVisible(false);	
		
		txtAuthor.setText(editedBook.getAuthor());
		txtPublisher.setText(editedBook.getPublisher());
		txtLanguage.setText(editedBook.getLanguage());
		txtISBN.setText(editedBook.getISBN());
		txtGenre.setText(editedBook.getGenre());
    }
    		
    
    /**
     * Sets the text fields for the edited DVD.
     */
    public void setDVDFields(DVD editedDVD) {
    	txtGenre.setVisible(true);
		txtLanguage.setVisible(true);
		
		txtAuthor.setVisible(false);
		txtPublisher.setVisible(false);
		txtISBN.setVisible(false);
		
		txtDirector.setVisible(true);
		txtRuntime.setVisible(true);
		txtSubLang.setVisible(true);
		listSubLang.setVisible(true);
		btnAdd.setVisible(true);
		btnRemove.setVisible(true);
		
		txtManufacturer.setVisible(false);
		txtModel.setVisible(false);
		txtOperatingSystem.setVisible(false);    	
		
		txtDirector.setText(editedDVD.getDirector());
		txtRuntime.setText(editedDVD.getRuntime() + "");
		txtLanguage.setText(editedDVD.getLanguage());
		txtGenre.setText(editedDVD.getGenre());
		listSubLang.getItems().clear();
		
		String[] subLang = editedDVD.getSubLang();
		for (String lang : subLang) {
			listSubLang.getItems().add(lang);
			currentLangList.add(lang);
		}
    }
	
    /**
     * Sets the text fields for the edited laptop.
     */
    public void setLaptopFields(Laptop editedLaptop) {
    	
    	txtGenre.setVisible(false);
		txtLanguage.setVisible(false);
		
		txtAuthor.setVisible(false);
		txtPublisher.setVisible(false);
		txtISBN.setVisible(false);
		
		txtDirector.setVisible(false);
		txtRuntime.setVisible(false);
		txtSubLang.setVisible(false);
		listSubLang.setVisible(false);
		btnAdd.setVisible(false);
		btnRemove.setVisible(false);
		
		txtManufacturer.setVisible(true);
		txtModel.setVisible(true);
		txtOperatingSystem.setVisible(true); 	
		
		txtManufacturer.setText(editedLaptop.getManufacturer());
		txtModel.setText(editedLaptop.getModel());
		txtOperatingSystem.setText(editedLaptop.getOperatingSystem());
    }
	
	/**
	 * Goes back to the previous page when the button is clicked.
	 * Also used to exit the page after an edit has been saved.
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
