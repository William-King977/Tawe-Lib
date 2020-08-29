import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * The FileReader class holds all file reading related methods. 
 * @author William King
 */
public class FileHandling {
	
	/** File location of the data files. */
	private final static String DATA_FILE_PATH = "DataFiles/";
	
	/**
	 * Saves the username of the currently logged in user to a text file.
	 * @param currentUser Username of the current user.
	 */
	public static void setCurrentUser(String currentUser) {
		String filePath = DATA_FILE_PATH + "CurrentUser.txt";
		try {
			FileWriter myWriter = new FileWriter(filePath);
			myWriter.write(currentUser);
		    myWriter.close();
		} catch (IOException e) {
		      System.out.println("Cannot write to " + filePath);
		      System.exit(-1);
		}
	}
	
	/**
	 * Fetches the username of the currently logged in user.
	 * @return The current user's username.
	 */
	public static String getCurrentUser() {
		String currentUser = "";
		
		String filePath = DATA_FILE_PATH + "CurrentUser.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	//Opens the file for reading
			in = new Scanner (inputFile);
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    currentUser = in.next();
		return currentUser;
	}
	
	/**
	 * Fetches all the current users in the system and stores
	 * them in a Linked Hashmap.
	 * @return LinkedHashMap of all users in the system.
	 */
	public static LinkedHashMap<String, User> getUsers() {
		String filePath = DATA_FILE_PATH + "User.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	//Opens the file for reading
			in = new Scanner (inputFile);
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each user and store them in a linked hashmap.
	    // The key is the username.
	    LinkedHashMap<String, User> users = new LinkedHashMap<>();
	    while (in.hasNextLine()) {
	    	
	    	String username = in.next();
	    	String firstName = in.next();
	    	String surname = in.next();
	    	String mobileNumber = in.next();
	    	String address1 = in.next();
	    	String address2 = in.next();
	    	String city = in.next();
	    	String postcode = in.next();
	    	String profilePicture = in.next();
	    	double fine = in.nextDouble();
	    	User user = new User(username, firstName, surname, mobileNumber,
	    			address1, address2, city, postcode, profilePicture, fine); 
	    	users.put(username, user);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return users;
	}
	
	/**
	 * Fetches all the current librarians in the system and stores
	 * them in a Linked Hashmap.
	 * @return LinkedHashmap of all librarians in the system.
	 */
	public static LinkedHashMap<String, Librarian> getLibrarians() {
		String filePath = DATA_FILE_PATH + "Librarian.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each librarian and store them in a linked hashmap.
	    LinkedHashMap<String, Librarian> librarians = new LinkedHashMap<>();
	    while (in.hasNextLine()) {
	    	String username = in.next();
	    	String firstName = in.next();
	    	String surname = in.next();
	    	String mobileNumber = in.next();
	    	String address1 = in.next();
	    	String address2 = in.next();
	    	String city = in.next();
	    	String postcode = in.next();
	    	String profilePicture = in.next();
	    	double fine = in.nextDouble();
	    	int staffID = in.nextInt();
	    	String employmentDate = in.next();
	    	Librarian librarian = new Librarian(username, firstName, surname, 
	    			mobileNumber, address1, address2, city, postcode, 
	    			profilePicture, fine, staffID, employmentDate); 
	    	librarians.put(username, librarian);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return librarians;
	}
	
	/**
	 * Fetches all the books in the system and stores
	 * them in an ArrayList.
	 * @return ArrayList of all books in the system.
	 */
	public static ArrayList<Book> getBooks() {
		String filePath = DATA_FILE_PATH + "Book.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each book and store them in an ArrayList.
	    ArrayList<Book> books = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int resourceID = in.nextInt();
	    	String resourceTitle = in.next();
	    	int year = in.nextInt();
	    	String thumbnail = in.next();
	    	int numberOfCopies = in.nextInt();
	    	
	    	String author = in.next();
	    	String publisher = in.next();
	    	String genre = in.next();
	    	String ISBN = in.next();
	    	String language = in.next();
	    	
	    	Book book = new Book(resourceID, resourceTitle, year, thumbnail,
	    			numberOfCopies, author, publisher, genre, ISBN, language); 
	    	books.add(book);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return books;
	}
	
	/**
	 * Fetches all the DVDs in the system and stores
	 * them in an ArrayList.
	 * @return ArrayList of all DVDs in the system.
	 */
	public static ArrayList<DVD> getDVDs() {
		String filePath = DATA_FILE_PATH + "DVD.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each DVD and store them in an ArrayList.
	    ArrayList<DVD> dvds = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int resourceID = in.nextInt();
	    	String resourceTitle = in.next();
	    	int year = in.nextInt();
	    	String thumbnail = in.next();
	    	int numberOfCopies = in.nextInt();
	    	
	    	String director = in.next();
	    	double runtime= in.nextDouble();
	    	String language = in.next();
	    	String[] subLang = {};
	    	
	    	// Checks if the DVD has any subtitle language options.
			if (in.hasNext()) {
				String langSet = in.next();
				subLang = addLanguages(langSet); 
			}
				
	    	DVD dvd = new DVD(resourceID, resourceTitle, year, thumbnail,
	    			numberOfCopies, director, runtime, language, subLang); 
	    	dvds.add(dvd);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return dvds;
	}
	
	/**
	 * Method to read in the individual subtitle language options that
	 * the DVD provides.
	 * @param langSet The string that holds the languages.
	 * @return String array of subtitle languages.
	 */
	private static String[] addLanguages(String langSet) {
		
		// New scanner to read individual languages.
	    Scanner readLang = new Scanner(langSet);
		int numOfLanguages = 0;
	    
	    // Delimiter change to read individual languages.
		readLang.useDelimiter(";");
	    
	    // Reads through each language to check how many there are.
	    while (readLang.hasNext()) {
	    	numOfLanguages++;
	    	readLang.next();
	    }
	    
	    // Closes the file stream after reading all the languages.
	    readLang.close();
	    
	    String[] subLang = new String[numOfLanguages];
	    
	    // readLang is redeclared so that its contents can be inserted 
	    // into the array. (.next() was used on it earlier).
	    readLang = new Scanner(langSet);
	    readLang.useDelimiter(";");
	    
	    // Insert the languages into the array.
	    for (int i = 0; i < numOfLanguages; i++) {
	    	String language = readLang.next();
	    	subLang[i] = language;
	    }
	    
	    readLang.close();
	    return subLang;   
	}
	
	/**
	 * Fetches all the laptops in the system and stores
	 * them in an ArrayList.
	 * @return ArrayList of all laptops in the system.
	 */
	public static ArrayList<Laptop> getLaptops() {
		String filePath = DATA_FILE_PATH + "Laptop.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each laptop and store them in an ArrayList.
	    ArrayList<Laptop> laptops = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int resourceID = in.nextInt();
	    	String resourceTitle = in.next();
	    	int year = in.nextInt();
	    	String thumbnail = in.next();
	    	int numberOfCopies = in.nextInt();
	    	
	    	String manufacturer = in.next();
	    	String model = in.next();
	    	String operatingSystem = in.next();
	    	 
	    	Laptop laptop = new Laptop(resourceID, resourceTitle, year, thumbnail,
	    			numberOfCopies, manufacturer, model, operatingSystem); 
	    	laptops.add(laptop);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return laptops;
	}
	
	/**
	 * Fetches all the copies in the system and stores
	 * them in an ArrayList.
	 * @return ArrayList of all copies in the system.
	 */
	public static ArrayList<Copy> getCopies() {
		String filePath = DATA_FILE_PATH + "Copy.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each copy and store them in an ArrayList.
	    ArrayList<Copy> copies = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int copyID = in.nextInt();
	    	int resourceID = in.nextInt();
	    	boolean isAvailable = in.nextBoolean();
	    	String strType = in.next();
	    	String strLoanDuration = in.next();
	    	
	    	ResourceType resourceType = null;
	    	switch (strType) {
	    		case "BOOK":
	    			resourceType = ResourceType.BOOK;
	    			break;
	    		case "DVD":
	    			resourceType = ResourceType.DVD;
	    			break;
	    		case "LAPTOP":
	    			resourceType = ResourceType.LAPTOP;
	    			break;
	    	}
	    	
	    	LoanDuration duration = null;
	    	switch (strLoanDuration) {
	    		case "DAY":
	    			duration = LoanDuration.DAY;
	    			break;
	    		case "WEEK":
	    			duration = LoanDuration.WEEK;
	    			break;
	    		case "TWO_WEEK":
	    			duration = LoanDuration.TWO_WEEK;
	    			break;
	    		case "FOUR_WEEK":
	    			duration = LoanDuration.FOUR_WEEK;
	    			break;
	    	}
	    
	    	Copy copy = new Copy(copyID, resourceID, isAvailable, resourceType, duration); 
	    	copies.add(copy);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return copies;
	}
	
	/**
	 * Fetches all the requests in the system and stores them in an ArrayList.
	 * @return An ArrayList of all the requests.
	 */
	public static ArrayList<Request> getRequests() {
		String filePath = DATA_FILE_PATH + "Request.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each request and store them in an ArrayList.
	    ArrayList<Request> requests = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int requestID = in.nextInt();
	    	int copyID = in.nextInt();
	    	int resourceID = in.nextInt();
	    	String username = in.next();
	    	String requestDate = in.next();
	    	boolean requestFilled = in.nextBoolean();
	    	boolean reserved = in.nextBoolean();
	    	 
	    	Request request = new Request(requestID, copyID, resourceID, 
	    			username, requestDate, requestFilled, reserved);
	    	requests.add(request);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return requests;
	}
	
	/**
	 * Fetches all the loans in the system and stores them in an ArrayList.
	 * @return An ArrayList of all the loans.
	 */
	public static ArrayList<Loan> getLoans() {
		String filePath = DATA_FILE_PATH + "Loan.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each loan and store them in an ArrayList.
	    ArrayList<Loan> loans = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int loanID = in.nextInt();
	    	int copyID = in.nextInt();
	    	int resourceID = in.nextInt();
	    	String username = in.next();
	    	int staffID = in.nextInt();
	    	String checkoutDate = in.next();
	    	String checkoutTime = in.next();
	    	String dueDate = in.next();
	    	
	    	boolean returned = in.nextBoolean();
	    	String returnDate = in.next();
	    	String returnTime = in.next();
	    	int daysOverdue = in.nextInt();
	    	String strType = in.next();
	    	
	    	ResourceType resourceType = null;
	    	switch (strType) {
	    		case "BOOK":
	    			resourceType = ResourceType.BOOK;
	    			break;
	    		case "DVD":
	    			resourceType = ResourceType.DVD;
	    			break;
	    		case "LAPTOP":
	    			resourceType = ResourceType.LAPTOP;
	    			break;
	    	}
	    	 
	    	Loan loan = new Loan(loanID, copyID, resourceID, username,
	    			staffID, checkoutDate, checkoutTime, dueDate, returned, 
	    			returnDate, returnTime, daysOverdue, resourceType);
	    	loans.add(loan);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
	    return loans;
	}
	
	/**
	 * Fetches all the transactions in the system and stores 
	 * them in an ArrayList.
	 * @return An ArrayList of all the transaction.
	 */
	public static ArrayList<Transaction> getTransactions() {
		String filePath = DATA_FILE_PATH + "Transaction.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    in.useDelimiter(",");
	    // Read each transaction and store them in an ArrayList.
	    ArrayList<Transaction> transactions = new ArrayList<>();
	    while (in.hasNextLine()) {
	    	
	    	int transactionID = in.nextInt();
	    	int resourceID = in.nextInt();
	    	String username = in.next();
	    	double amount = in.nextDouble();
	    	
	    	int daysOverdue = in.nextInt();
	    	String date = in.next();
	    	String time = in.next();
	    	String strType = in.next();
	    	boolean isFine = in.nextBoolean();
	    	
	    	ResourceType resourceType = null;
	    	switch (strType) {
	    		case "BOOK":
	    			resourceType = ResourceType.BOOK;
	    			break;
	    		case "DVD":
	    			resourceType = ResourceType.DVD;
	    			break;
	    		case "LAPTOP":
	    			resourceType = ResourceType.LAPTOP;
	    			break;
	    	}
	    	 
	    	Transaction transaction = new Transaction(transactionID, 
	    			resourceID, username, amount, daysOverdue, date, time, 
	    			resourceType, isFine);
	    	transactions.add(transaction);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
	    return transactions;
	}
	
	/**
	 * A profile is edited by replacing the details of the previous profile
	 * with the new one.
	 * @param oldProfile The string holding details of the old profile.
	 * @param newProfile The string holding details of the new profile.
	 * @param userType Integer showing whether a user is staff or not.
	 */
	public static void editProfile(String oldProfile, String newProfile, 
			int userType) {
		String filePath = "";
		switch (userType) {
			case 1:
				filePath = DATA_FILE_PATH + "Librarian.txt";
				break;
			case 2:
				filePath = DATA_FILE_PATH + "User.txt";
				break;
		}
		
		File inputFile = new File(filePath);
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldContent = "";
		
	    try {
			reader = new BufferedReader(new FileReader(inputFile));
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    try {
		    // Uses buffer to write old file contents to a string.
		    String line = reader.readLine();
		    while (line != null) {
		    	oldContent = oldContent + line + System.lineSeparator();
		        line = reader.readLine();
		    }
		    // Replace old profile with the new one within the textfile.
		    String newContent = oldContent.replace(oldProfile, newProfile);
		   
		    writer = new FileWriter(filePath);
		    writer.write(newContent);
		    reader.close();
		    writer.flush();
		    writer.close();
	    } catch (IOException e) {
	    	// Catches an IO exception when the file can't 
	    	// be written.
            e.printStackTrace();
            System.exit(-1);
	    }
	}
	
	/**
	 * A resource is edited by replacing the details of the previous resource
	 * with the new one.
	 * @param oldResource The string description of the old resource.
	 * @param newResource The string description of the edits made to the resource.
	 * @param resourceType The type of resource edited.
	 */
	public static void editResource(String oldResource, String newResource, 
			String resourceType) {
		String filePath = "";
		
		switch (resourceType) {
			case "Book":
				filePath = DATA_FILE_PATH + "Book.txt";
				break;
			case "DVD":
				filePath = DATA_FILE_PATH + "DVD.txt";
				break;
			case "Laptop":
				filePath = DATA_FILE_PATH + "Laptop.txt";
				break;
		}
		
		File inputFile = new File(filePath);
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldContent = "";
		
	    try {
			reader = new BufferedReader(new FileReader(inputFile));
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    try {
		    // Uses buffer to write old file contents to a string.
		    String line = reader.readLine();
		    while (line != null) {
		    	oldContent = oldContent + line + System.lineSeparator();
		        line = reader.readLine();
		    }
		    // Replace old resource with the new one within the textfile.
		    String newContent = oldContent.replace(oldResource, newResource);
		    writer = new FileWriter(filePath);
		    writer.write(newContent);
		    reader.close();
		    writer.flush();
		    writer.close();
	    } catch (IOException e) {
	    	// Catches an IO exception when the file can't 
	    	// be written.
            e.printStackTrace();
            System.exit(-1);
	    }
	}
	
	/**
	 * A copy is edited by replacing the details of the previous copy
	 * with the new one.
	 * @param oldCopy The string details of the old copy.
	 * @param newCopy The string details of the edited copy.
	 */
	public static void editCopy(String oldCopy, String newCopy) {
		String filePath = DATA_FILE_PATH + "Copy.txt";
		
		File inputFile = new File(filePath);
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldContent = "";
		
	    try {
			reader = new BufferedReader(new FileReader(inputFile));
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    try {
		    // Uses buffer to write old file contents to a string.
		    String line = reader.readLine();
		    while (line != null) {
		    	oldContent = oldContent + line + System.lineSeparator();
		        line = reader.readLine();
		    }
		    // Replace old copy with the new one within the textfile.
		    String newContent = oldContent.replace(oldCopy, newCopy);
		    writer = new FileWriter(filePath);
		    writer.write(newContent);
		    reader.close();
		    writer.flush();
		    writer.close();
	    } catch (IOException e) {
	    	// Catches an IO exception when the file can't 
	    	// be written.
            e.printStackTrace();
            System.exit(-1);
	    }
	}
	
	/**
	 * A request is edited by replacing the details of the previous request
	 * with the new one.
	 * @param oldRequest The string details of the old request.
	 * @param newRequest The string details of the edited request.
	 */
	public static void editRequest(String oldRequest, String newRequest) {
		String filePath = DATA_FILE_PATH + "Request.txt";
		
		File inputFile = new File(filePath);
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldContent = "";
		
	    try {
			reader = new BufferedReader(new FileReader(inputFile));
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    try {
		    // Uses buffer to write old file contents to a string.
		    String line = reader.readLine();
		    while (line != null) {
		    	oldContent = oldContent + line + System.lineSeparator();
		        line = reader.readLine();
		    }
		    // Replace old request with the new one within the textfile.
		    String newContent = oldContent.replace(oldRequest, newRequest);
		    writer = new FileWriter(filePath);
		    writer.write(newContent);
		    reader.close();
		    writer.flush();
		    writer.close();
	    } catch (IOException e) {
	    	// Catches an IO exception when the file can't 
	    	// be written.
            e.printStackTrace();
            System.exit(-1);
	    }
	}
	
	/**
	 * A loan is edited by replacing the details of the previous loan
	 * with the new one.
	 * @param oldLoan The string details of the old loan.
	 * @param newLoan The string details of the edited loan.
	 */
	public static void editLoan(String oldLoan, String newLoan) {
		String filePath = DATA_FILE_PATH + "Loan.txt";
		
		File inputFile = new File(filePath);
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldContent = "";
		
	    try {
			reader = new BufferedReader(new FileReader(inputFile));
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(-1);
		}
	    
	    try {
		    // Uses buffer to write old file contents to a string.
		    String line = reader.readLine();
		    while (line != null) {
		    	oldContent = oldContent + line + System.lineSeparator();
		        line = reader.readLine();
		    }
		    // Replace old loan with the new one within the textfile.
		    String newContent = oldContent.replace(oldLoan, newLoan);
		    writer = new FileWriter(filePath);
		    writer.write(newContent);
		    reader.close();
		    writer.flush();
		    writer.close();
	    } catch (IOException e) {
	    	// Catches an IO exception when the file can't 
	    	// be written.
            e.printStackTrace();
            System.exit(-1);
	    }
	}
	
	/**
	 * A new user is created by adding their details at the end
	 * of their respective textfile.
	 * @param newUser Details of the new registered user.
	 * @param userType Integer showing whether a user is staff or not.
	 */
	public static void createUser(String newUser, int userType) {
		String filePath = "";
		switch (userType) {
			case 1:
				filePath = DATA_FILE_PATH + "Librarian.txt";
				break;
			case 2:
				filePath = DATA_FILE_PATH + "User.txt";
				break;
		}
		
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		try { 
			file = new File(filePath);
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			// Writes the user then adds a new line. 
			printWriter.print(newUser);
			printWriter.println("");
			printWriter.close();
        } catch (IOException e) { 
            System.out.println("Cannot write to " + filePath); 
            System.exit(-1);
        } 
	}
	
	/**
	 * A new copy is created by adding their details at the end
	 * of the textfile.
	 * @param newCopy The details of the new copy.
	 */
	public static void createCopy(String newCopy) {
		String filePath = DATA_FILE_PATH + "Copy.txt";
		
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		try { 
			file = new File(filePath);
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			// Writes the copy then adds a new line. 
			printWriter.print(newCopy);
			printWriter.println("");
			printWriter.close();
        } catch (IOException e) { 
            System.out.println("Cannot write to " + filePath); 
            System.exit(-1);
        } 
	}
	
	/**
	 * A new resource is created by adding their details at the end
	 * of the textfile.
	 * @param newCopy The details of the new copy.
	 */
	public static void createResource(String newResource, 
			ResourceType resourceType) {
		String filePath = "";
		switch (resourceType) {
			case BOOK:
				filePath = DATA_FILE_PATH + "Book.txt";
				break;
			case DVD:
				filePath = DATA_FILE_PATH + "DVD.txt";
				break;
			case LAPTOP:
				filePath = DATA_FILE_PATH + "Laptop.txt";
		}
		
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		try { 
			file = new File(filePath);
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			// Writes the resource then adds a new line.
			printWriter.print(newResource);
			printWriter.println("");
			printWriter.close();
        } catch (IOException e) { 
            System.out.println("Cannot write to " + filePath); 
            System.exit(-1);
        } 	
	}
	
	/**
	 * A new request is made by adding its details at the end
	 * of the textfile.
	 * @param newRequest The details of the request.
	 */
	public static void makeRequest(String newRequest) {
		
		String filePath = DATA_FILE_PATH + "Request.txt";
		
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		try { 
			file = new File(filePath);
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			// Writes the request then adds a new line.
			printWriter.print(newRequest);
			printWriter.println("");
			printWriter.close();
        } catch (IOException e) { 
            System.out.println("Cannot write to " + filePath); 
            System.exit(-1);
        } 	
	}
	
	/**
	 * A new loan is made by adding its details at the end
	 * of the textfile.
	 * @param newRequest The details of the loan.
	 */
	public static void createLoan(String newLoan) {
		
		String filePath = DATA_FILE_PATH + "Loan.txt";
		
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		try { 
			file = new File(filePath);
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			// Writes the loan then adds a new line.
			printWriter.print(newLoan);
			printWriter.println("");
			printWriter.close();
        } catch (IOException e) { 
            System.out.println("Cannot write to " + filePath); 
            System.exit(-1);
        } 	
	}
	
	/**
	 * A new transaction is made by adding its details at the end
	 * of the textfile.
	 * @param newRequest The details of the transaction.
	 */
	public static void makeTransaction(String newTransaction) {
		
		String filePath = DATA_FILE_PATH + "Transaction.txt";
		
		File file = null;
		FileWriter fileWriter = null;
		BufferedWriter buffWriter = null;
		PrintWriter printWriter = null;
		try { 
			file = new File(filePath);
			fileWriter = new FileWriter(file, true);
			buffWriter = new BufferedWriter(fileWriter);
			printWriter = new PrintWriter(buffWriter);
			
			// Writes the transaction then adds a new line.
			printWriter.print(newTransaction);
			printWriter.println("");
			printWriter.close();
        } catch (IOException e) { 
            System.out.println("Cannot write to " + filePath); 
            System.exit(-1);
        } 	
	}
	
	/**
     * Checks if an image file and its path already exist.
     * Because of how Windows works, it's not case sensitive.
     * @param fileName Takes the name of the file to test.
     * @return Returns a boolean as to if the file is already in the system.
     */
    public static boolean checkImageExists(String fileName){
    	File pathCheck = new File(DATA_FILE_PATH + 
    			"ProfilePictures/" + fileName); //The path of the file.
    	return pathCheck.exists();
    }
}

