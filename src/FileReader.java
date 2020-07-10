import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FileReader class holds all file reading related methods. 
 * @author William King
 */
public class FileReader {
	
	/** File location of the data files. */
	private final static String DATA_FILE_PATH = "DataFiles/";
	
	/**
	 * Fetches all the current users in the system and stores
	 * them in an ArrayList.
	 * @return ArrayList of all users in the system.
	 */
	public static ArrayList<User> getUsers() {
		String filePath = DATA_FILE_PATH + "User.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	//Opens the file for reading
			in = new Scanner (inputFile);	
		}
	    
	    //Catch an exception if the file does not exist and exit the program
		catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(0);
		}
	    
	    in.useDelimiter(",");
	    // Read each user and store them in an ArrayList.
	    ArrayList<User> users = new ArrayList<>();
	    while(in.hasNextLine()) {
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
	    	users.add(user);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return users;
	}
	
	/**
	 * Fetches all the current librarians in the system and stores
	 * them in an ArrayList.
	 * @return ArrayList of all librarians in the system.
	 */
	public static ArrayList<Librarian> getLibrarians() {
		String filePath = DATA_FILE_PATH + "Librarian.txt";
		File inputFile = new File(filePath);
		Scanner in = null;
	    try {
	    	// Opens the file for reading
			in = new Scanner (inputFile);	
		}
	    // Catch an exception if the file does not exist and exit the program
		catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(0);
		}
	    
	    in.useDelimiter(",");
	    // Read each librarian and store them in an ArrayList.
	    ArrayList<Librarian> librarians = new ArrayList<>();
	    while(in.hasNextLine()) {
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
	    	librarians.add(librarian);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return librarians;
	}
}
