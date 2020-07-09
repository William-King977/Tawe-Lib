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
	    
	    // Read each user and store them in an ArrayList.
	    ArrayList<User> users = new ArrayList<>();
	    while(in.hasNextLine()) {
	    	String username = in.next();
	    	User user = new User(username); // Change when expanding class!
	    	users.add(user);
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
	    
	    // Read each librarian and store them in an ArrayList.
	    ArrayList<Librarian> librarians = new ArrayList<>();
	    while(in.hasNextLine()) {
	    	String username = in.next();
	    	int staffID = in.nextInt();
	    	Librarian librarian = new Librarian(username, staffID); // Change when expanding class!
	    	librarians.add(librarian);
	    }
	    in.close();
		return librarians;
	}
}
