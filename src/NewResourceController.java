import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

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
    	//Clears other check boxes if selected.
		cbDVD.setSelected(false);
		cbLaptop.setSelected(false);
		
		// Only show appropriate fields.
		if (cbBook.isSelected()) {
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
		// If you're selecting the check box to clear it.	
		} else {
			txtDirector.setVisible(true);
			txtRuntime.setVisible(true);
			txtSubLang.setVisible(true);
			listSubLang.setVisible(true);
			btnAdd.setVisible(true);
			btnRemove.setVisible(true);
			
			txtManufacturer.setVisible(true);
			txtModel.setVisible(true);
			txtOperatingSystem.setVisible(true);
		}
    }
    
    /**
     * Sets the status of the DVD check box and makes appropriate
     * changes to the other check boxes.
     */
    public void setCBDVDStatus() {
    	//Clears other check boxes if selected.
		cbBook.setSelected(false);
		cbLaptop.setSelected(false); 
		
		// Only show appropriate fields.
		if (cbDVD.isSelected()) {
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
		// If you're selecting the check box to clear it.	
		} else {			
			txtAuthor.setVisible(true);
			txtPublisher.setVisible(true);
			txtISBN.setVisible(true);
			
			txtManufacturer.setVisible(true);
			txtModel.setVisible(true);
			txtOperatingSystem.setVisible(true);
		}
    }
    
    /**
     * Sets the status of the Laptop check box and makes appropriate
     * changes to the other check boxes.
     */
    public void setCBLaptopStatus() {
    	//Clears other check boxes if selected.
		cbBook.setSelected(false);
		cbDVD.setSelected(false); 
		
		// Only show appropriate fields.
		if (cbLaptop.isSelected()) {
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
		// If you're selecting the check box to clear it.	
		} else {
			txtGenre.setVisible(true);
			txtLanguage.setVisible(true);
			
			txtAuthor.setVisible(true);
			txtPublisher.setVisible(true);
			txtISBN.setVisible(true);
			
			txtDirector.setVisible(true);
			txtRuntime.setVisible(true);
			txtSubLang.setVisible(true);
			listSubLang.setVisible(true);
			btnAdd.setVisible(true);
			btnRemove.setVisible(true);
		}
    }
	
	/**
     * Creates a new resource based on the information typed in.
     * @throws IOException Throws an exception to be caught when a file
     *                     can't be written.
     */
    public void handleCreateResourceButtonAction() throws IOException {
    	if (cbBook.isSelected()) {
			validateNewBook();
		} else if (cbDVD.isSelected()) {
			validateNewDVD();
		} else if (cbLaptop.isSelected()) {
			validateNewLaptop();
		//If none of the check boxes have been selected.
		} else {
			Utility.resourceNotSelectedCreate();
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
     * Validates the entered information for the new book to be created.
     * @throws IOException Throws an exception to be caught when a file
     *                     can't be written.
     */
    public void validateNewBook() throws IOException {
    	String resourceTitle = txtResourceTitle.getText().trim();
    	String strYear = txtYear.getText().trim(); 
    	String author = txtAuthor.getText().trim();
    	String publisher = txtPublisher.getText().trim();
    	String genre = txtGenre.getText().trim();
    	String language = txtLanguage.getText().trim();
    	String isbn = txtISBN.getText().trim();
    	
    	//Gets the position of the selected resource image.
		int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		//Sets a new resource image if it has been selected.
		if (selectedIndex < 0) {
			Utility.imageNotSelected();
			return;
		} 
		
		String imageName = resourceImageList[selectedIndex].getName();
		
		//Validation applied to the inputed values.
    	boolean bookFieldsFilled = Utility.isBookFieldFilled(resourceTitle, 
    			strYear, author, publisher); 
    	boolean isNum = Utility.isInt(strYear) && Utility.isInt(isbn);
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
			Utility.resourceExistsCreate();
			return;
		}
		
		int resourceID = getMaxResourceID() + 1;
		
		String newBook = resourceID + "," + resourceTitle + "," + year + 
				"," + imageName + "," + NUMBER_OF_COPIES + "," + genre + 
				"," + language + "," + author + "," + publisher + 
				"," + isbn + ",";
		
		addCopies(resourceID, ResourceType.BOOK);
		FileHandling.createResource(newBook, ResourceType.BOOK);
		Utility.resourceCreated();
		handleBackButtonAction();
    }
    
    /**
     * Validates the entered information for the new DVD to be created.
     * @throws IOException Throws an exception to be caught when a file
     *                     can't be written.
     */
    public void validateNewDVD() throws IOException {
    	String resourceTitle = txtResourceTitle.getText().trim();
    	String strYear = txtYear.getText().trim(); 
    	String[] subLang = currentLangList.toArray(
    			new String[currentLangList.size()]);
    	String director = txtDirector.getText().trim();
    	String strRuntime = txtRuntime.getText().trim();
    	String genre = txtGenre.getText().trim();
    	String language = txtLanguage.getText().trim();
    	
    	//Gets the position of the selected resource image.
    	int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		//Sets a new resource image if it has been selected.
		if (selectedIndex < 0) {
			Utility.imageNotSelected();
			return;
		} 
		
		String imageName = resourceImageList[selectedIndex].getName();
		
		//Validation applied to the inputed values.
    	boolean dvdFieldsFilled = Utility.isDVDFieldFilled(resourceTitle, 
    			strYear, director, strRuntime);
    	boolean isNum = Utility.isInt(strYear);
    	boolean isDouble = Utility.isDouble(strRuntime);
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
			Utility.resourceExistsCreate();
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
		
		int resourceID = getMaxResourceID() + 1;
		
		String newDVD = resourceID + "," + resourceTitle + "," + year + 
				"," + imageName + "," + NUMBER_OF_COPIES + "," + genre + 
				"," + language + "," + director + 
				"," + runtime + "," + strSubLang + ",";
		
		addCopies(resourceID, ResourceType.DVD);
		FileHandling.createResource(newDVD, ResourceType.DVD);
		Utility.resourceCreated();
		handleBackButtonAction();
    }
    
    /**
     * Validates the entered information for the new laptop to be
     * created.
     * @throws IOException Throws an exception to be caught when a file
     *                     can't be written.
     */
	public void validateNewLaptop() throws IOException {
		String resourceTitle = txtResourceTitle.getText().trim();
    	String strYear = txtYear.getText().trim();
    	String manufacturer = txtManufacturer.getText().trim();
    	String model = txtModel.getText().trim();
    	String operatingSystem = txtOperatingSystem.getText().trim();
    	
    	//Gets the position of the selected resource image.
    	int selectedIndex = cmbResourceImage.getSelectionModel()
				.getSelectedIndex();
		
		//Sets a new resource image if it has been selected.
		if (selectedIndex < 0) {
			Utility.imageNotSelected();
			return;
		} 
		
		String imageName = resourceImageList[selectedIndex].getName();
		
		//Validation applied to the inputted values.
    	boolean laptopFieldsFilled = Utility.isLaptopFieldFilled(resourceTitle, 
    			strYear, manufacturer, model, operatingSystem);
    	boolean isNum = Utility.isInt(strYear);
    	boolean isAlpha = Utility.isAlphaLaptop(resourceTitle, operatingSystem, 
    			model, manufacturer);
    	
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
			Utility.resourceExistsCreate();
			return;
		}
   		
   		int resourceID = getMaxResourceID() + 1; 
   		
		String newLaptop = resourceID + "," + resourceTitle + "," + year + 
				"," + imageName + "," + NUMBER_OF_COPIES + "," + manufacturer + 
				"," + model + "," + operatingSystem + ",";
		
		addCopies(resourceID, ResourceType.LAPTOP);
		FileHandling.createResource(newLaptop, ResourceType.LAPTOP);
		Utility.resourceCreated();
		handleBackButtonAction();
	}
	
	/**
	 * Gets the current maximum resource ID by sorting all resources
	 * in ascending order and fetching the ID of the last resource.
	 * @return The current maximum resource ID.
	 */
	public int getMaxResourceID() {
		ArrayList<Resource> resourceList = new ArrayList<Resource>();
		resourceList.addAll(bookList);
		resourceList.addAll(dvdList);
		resourceList.addAll(laptopList);
  
        Collections.sort(resourceList, new SortResources());
        int maxIndex = resourceList.size() - 1;
        int maxResourceID = resourceList.get(maxIndex).getResourceID();
		return maxResourceID;
	}
	
	/**
	 * Creates the copies for the new resource.
	 * @param resourceID The ID of the newly created resource.
     * @param resourceType The resource type.
	 */
	public void addCopies(int resourceID, ResourceType resourceType) {
		ArrayList<Copy> copyList = FileHandling.getCopies();
		Collections.sort(copyList, new SortCopies());
	
		int maxIndex = copyList.size() - 1;
		int maxCopyID = (copyList.get(maxIndex)).getCopyID();
		int copyID = maxCopyID; // It's going to be incremented in the loop.
		
		Random rand = new Random(); 
		int randIndex;  // Random number between 0 and 3.
		LoanDuration loanDuration;
		
		// Fetches options of loan durations.
		// One is picked at random for each copy.
		LoanDuration[] durations = new LoanDuration[4];
		durations[0] = LoanDuration.DAY;
		durations[1] = LoanDuration.WEEK;
		durations[2] = LoanDuration.TWO_WEEK;
		durations[3] = LoanDuration.FOUR_WEEK;
		
		for (int i = 0; i < NUMBER_OF_COPIES; i++) {
			copyID += 1;
			randIndex = rand.nextInt(4);
			loanDuration = durations[randIndex];
			
			String newCopy = copyID + "," + resourceID + ",true," + resourceType + 
					"," + loanDuration + ",";
			FileHandling.createCopy(newCopy);
		}
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
