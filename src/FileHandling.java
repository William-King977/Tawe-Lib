import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The FileReader class holds all file reading related methods. 
 * @author William King
 */
public class FileHandling {
	
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
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(0);
		}
	    
	    in.useDelimiter(",");
	    // Read each user and store them in an ArrayList.
	    ArrayList<User> users = new ArrayList<>();
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
	    	// Opens the file for reading.
			in = new Scanner (inputFile);	
		// Catch an exception if the file does not exist and exit the program.
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open " + filePath);
			System.exit(0);
		}
	    
	    in.useDelimiter(",");
	    // Read each librarian and store them in an ArrayList.
	    ArrayList<Librarian> librarians = new ArrayList<>();
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
	    	librarians.add(librarian);
	    	in.nextLine(); // Needed if you change delimiter.
	    }
	    in.close();
		return librarians;
	}
	
	/**
	 * A profile is edited by replacing the details of the previous profile
	 * with the new one.
	 * @param oldProfile The string holding details of the old profile.
	 * @param newProfile The string holding details of the new profile.
	 * @param userType Integer showing whether a user is staff or not.
	 * @throws IOException Throws an exception when a file cannot be written.
	 */
	public static void editProfile(String oldProfile, String newProfile, 
			int userType) throws IOException {
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
			System.exit(0);
		}
	    
	    // Uses buffer to write old file contents to a string.
	    String line = reader.readLine();
	    while (line != null) {
	    	oldContent = oldContent + line + System.lineSeparator();
	        line = reader.readLine();
	    }
	    // Replace old profile with the new one within the old textfile.
	    String newContent = oldContent.replace(oldProfile, newProfile);
	   
	    writer = new FileWriter(filePath);
	    writer.write(newContent);
	    reader.close();
	    writer.flush();
	    writer.close();
	    
	    // Show appropriate message to indicate that the changes were saved.
	    switch (userType) {
			case 1:
				Utility.savedLibrarianChanges();
				break;
			case 2:
				Utility.savedUserChanges();
				break;
	    }
	}
	
	/**
     * Checks if an image file and its path already exist.
     * @param fileName Takes the name of the file to test.
     * @return Returns a boolean as to if the file is already in the system.
     */
    public static boolean checkImageExists(String fileName){
    	File pathCheck = new File(DATA_FILE_PATH + 
    			"ProfilePictures/" + fileName); //The path of the file.
    	return  pathCheck.exists();
    }
}

