package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.SECONDS;

/**
 * The Utility class holds commonly used methods / functions that are used 
 * within different classes such as validation etc.
 * @author William King
 */
public class Utility {
	
	/** A regular expression that holds a dash and, lower and 
	 * upper case letters. */
	private static String alphabet = "[a-zA-Z ]+";
	/** A regular expression that holds digits from 0-9. */
	private static String digits = "[0-9]+";
	
	/** A regular expression for someone's name and city. */
	private static String nameRegex = "[a-zA-Z-' ]+";
	/** A regular expression for an address. */
	private static String addressRegex = "[0-9a-zA-Z-' ]+";
	/** A regular expression for a postcode. */
	private static String postcodeRegex = "[0-9A-Z ]+";
	/** A regular expression for a mobile number. */
	private static String phoneRegex = "[0-9+ ]+";
	
	/** A regular expression for holding numbers and letters. */
	private static String alphaNumRegex = "[0-9a-zA-Z ]+";
	/** A regular expression for the laptop model. */
	private static String modelRegex = "[0-9a-zA-Z- ]+";
	
	/**
	 * Checks if all the required fields have data in them.
	 * @param firstName The entered first name.
	 * @param surname The entered surname.
	 * @param mobileNumber The entered mobile number.
	 * @param address1 The entered address line 1.
	 * @param city The entered city.
	 * @param postcode The entered postcode.
	 * @return Whether all the required fields are filled or not.
	 */
	public static boolean isFieldFilledUser(String firstName, String surname, 
			String mobileNumber, String address1, String city, 
			String postcode) {
		// If any of the required fields are empty. 
		if (firstName.isEmpty() || surname.isEmpty() || 
				mobileNumber.isEmpty() || address1.isEmpty() || 
				city.isEmpty() || postcode.isEmpty()) {
			return false;
		//If all required fields are filled.
		} else {
			return true;
		}
	}
	
	/**
	 * Checks if the required fields for a book has data in them.
	 * @param resourceTitle The title of the book.
	 * @param strYear The year the book was released as a string.
	 * @param author The author who wrote the book.
	 * @param publisher The publisher of the book.
	 * @return Whether the required fields for the book has been 
	 *         filled or not.
	 */
	public static boolean isBookFieldFilled(String resourceTitle, 
			String strYear, String author, String publisher) {
		//If any of the required fields are empty. 
		if (resourceTitle.isEmpty() || strYear.isEmpty() || 
				author.isEmpty() || publisher.isEmpty()) {
			return false;
		//If all required fields are filled.
		} else {
			return true;
		}
	}/**
	 * Checks if the required fields for a DVD has data in them.
	 * @param resourceTitle The title of the DVD.
	 * @param strYear The year the book was released as a string.
	 * @param director The entered director of the DVD.
	 * @param strRuntime The entered runtime of the DVD as a string.
	 * @return Whether the required fields for the DVD has been 
	 *         filled or not.
	 */
	public static boolean isDVDFieldFilled(String resourceTitle, 
			String strYear, String director, String strRuntime) {
		//If any of the required fields are empty. 
		if (resourceTitle.isEmpty() || strYear.isEmpty() || 
				director.isEmpty() || strRuntime.isEmpty()) {
			return false;
		//If all required fields are filled.
		} else {
			return true;
		}
	}
	
	/**
	 * Checks if the required fields for a laptop has data in them.
	 * @param resourceTitle The title of the laptop.
	 * @param strYear The entered year.
	 * @param manufacturer The entered manufacturer.
	 * @param model The entered model.
	 * @param operatingSystem The entered operating system.
	 * @return Whether the required fields for the laptop has been 
	 *         filled or not.
	 */
	public static boolean isLaptopFieldFilled(String resourceTitle, 
			String strYear, String manufacturer, String model, 
			String operatingSystem) {
		//If any of the required fields are empty. 
		if (resourceTitle.isEmpty() || strYear.isEmpty() || 
				manufacturer.isEmpty() || model.isEmpty() || 
				operatingSystem.isEmpty()) {
			return false;
		//If all required fields are filled.
		} else {
			return true;
		}
	}
	
	/**
	 * Checks if the entered username is the same with any of the current 
	 * users registered in the system.
	 * @param userList The list of all registered users.
	 * @param librarianList The list of all registered librarians.
	 * @param username The username entered.
	 * @return Whether the username entered exists in the system or not.
	 */
	public static boolean isUsernameExist(LinkedHashMap<String, User> userList, 
			LinkedHashMap<String, Librarian> librarianList, String username) {
		// Checks if any of the librarians have the same username.
		Boolean userExist = userList.containsKey(username);
		Boolean librarianExist = librarianList.containsKey(username);
		
		// If either is true, then the username already exists.
		if (userExist || librarianExist) {
			return true;
		// The entered username doesn't exist in the system.
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the changes to the book causes it to match 
	 * with an already existing book in the library.
	 * @param resourceTitle The entered book title.
	 * @param year The entered year.
	 * @param imageName The name of the thumbnail image.
	 * @param author The author who wrote the book.
	 * @param publisher The publisher of the book.
	 * @param genre The entered genre.
	 * @param language The entered language.
	 * @param isbn The entered ISBN.
	 * @param bookList The array list of all current books.
	 * @return Whether the book being created has the same details as
	 *         an already existing book in the library.
	 */
	public static boolean isBookExist(String resourceTitle, int year, 
			String imageName, String author, String publisher, String genre,
			String language, String isbn, ArrayList<Book> bookList) {
		boolean bookExist = false;
		//Searches through all the existing books.
		for (Book thisBook : bookList) {
			if (thisBook.getResourceTitle().equals(resourceTitle) && 
					thisBook.getYear() == year &&
					thisBook.getAuthor().equals(author) &&
					thisBook.getPublisher().equals(publisher) &&
					thisBook.getGenre().equals(genre) &&
					thisBook.getLanguage().equals(language) &&
					thisBook.getThumbnail().equals(imageName) &&
					thisBook.getISBN().equals(isbn)) {
				bookExist = true;
			} 
		}
		return bookExist;
	}
	
	/**
	 * Checks if the changes to the DVD causes it to match 
	 * with an already existing DVD in the library.
	 * @param resourceTitle The title of the DVD.
	 * @param year The entered year.
	 * @param imageName The image name of the DVD's thumbnail.
	 * @param director The entered director.
	 * @param runtime The entered runtime.
	 * @param subLang The entered subtitle language.
	 * @param language The entered language.
	 * @param dvdList The array list of all current DVDs.
	 * @return Whether the DVD being created has the same details as
	 *         an already existing DVD in the library.
	 */
	public static boolean isDVDExist(String resourceTitle, int year, 
			String imageName, String director, double runtime, String[] subLang, 
			String language, ArrayList <DVD> dvdList) {
		String strSubLang = Arrays.toString(subLang);
		boolean dvdExist = false;
		//Searches through all the existing DVDs.
		for (DVD thisDVD : dvdList) {
			if (thisDVD.getResourceTitle().equals(resourceTitle) &&
					thisDVD.getYear() == year &&
					thisDVD.getDirector().equals(director) &&
					thisDVD.getRuntime() == runtime &&
					Arrays.toString(thisDVD.getSubLang()).equals(strSubLang) &&
					thisDVD.getLanguage().equals(language) &&
					thisDVD.getThumbnail().equals(imageName)) {
				dvdExist = true;
			}
		}
		return dvdExist;
	}
	
	/**
	 * Checks if the changes to the laptop causes it to match 
	 * with an already existing laptop in the library.
	 * @param resourceTitle The title of the laptop.
	 * @param year The entered year.
	 * @param imageName The image name of the laptop's thumbnail.
	 * @param manufacturer The entered manufacturer.
	 * @param model The entered model.
	 * @param operatingSystem The entered operating system.
	 * @param laptopList The array list of all current laptops.
	 * @return Whether the laptop being created has the same details as
	 *         an already existing laptop in the library.
	 */
	public static boolean isLaptopExist(String resourceTitle, int year,
			String imageName, String manufacturer, String model, 
			String operatingSystem, ArrayList<Laptop> laptopList) {
		boolean laptopExist = false;
		//Searches through all the existing laptops.
		for (Laptop thisLaptop : laptopList) {
			if (thisLaptop.getResourceTitle().equals(resourceTitle) &&
					thisLaptop.getYear() == year &&
					thisLaptop.getManufacturer().equals(manufacturer) &&
					thisLaptop.getModel().equals(model) &&
					thisLaptop.getOperatingSystem().equals(operatingSystem) &&
					thisLaptop.getThumbnail().equals(imageName)) {
				laptopExist = true;
			}
		}
		return laptopExist;
	}
	
	/**
	 * Checks if the passed down fields matches with their respective regex for
	 * the user.
	 * @param firstName The entered first name.
	 * @param surname The entered surname.
	 * @param mobileNum The entered mobile number.
	 * @param address1 The entered address line 1.
	 * @param address2 The entered address line 2.
	 * @param city The entered city.
	 * @return If the passed down fields matches with their regex or not.
	 */
	public static boolean isAlphaUser(String firstName, String surname, 
			String mobileNum, String address1, String address2, String city) {
		// If the selected fields has only letters or dashes.
		if (firstName.matches(nameRegex) && surname.matches(nameRegex) 
				&& mobileNum.matches(phoneRegex) 
				&& address1.matches(addressRegex) 
				&& (address2.matches(addressRegex) || address2.equals("N/A")) 
				&& city.matches(nameRegex)) {
			return true;
		// If any of the fields that SHOULD NOT contain non-alphabetical
		// have non-alphabetical characters.
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the entered postcode for a user has valid characters.
	 * @param postcode The entered postcode.
	 * @return If the postcode has valid characters or not.
	 */
	public static boolean isPostcodeValid(String postcode) {
		if (postcode.matches(postcodeRegex)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the entered language has non-alphabetic characters.
	 * @param lang The entered language.
	 * @return Whether the entered language has non-alphabetic characters
	 *         or not.
	 */
	public static boolean isAlphaLanguage(String lang) {
		if (lang.matches(alphabet)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields match their respective regex for the 
	 * book.
	 * @param author The entered author.
	 * @param publisher The entered publisher.
	 * @param genre The entered genre.
	 * @param language The entered language.
	 * @return Whether the passed down fields for the book has
	 *         alphabetical characters or not.
	 */
	public static boolean isAlphaBook(String author, String publisher, 
			String genre, String language) {
		if (author.matches(nameRegex) && publisher.matches(alphabet) && 
				(genre.matches(alphabet) || genre.isEmpty()) && 
				(language.matches(alphabet) || language.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields match their respective regex for the
	 * DVD.
	 * @param director The entered director.
	 * @param language The entered language.
	 * @return Whether the passed down fields for the DVD has
	 *         alphabetical characters or not.
	 */
	public static boolean isAlphaDVD(String director, String language) {
		if (director.matches(nameRegex) && 
				(language.matches(alphabet) || language.isEmpty())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the passed down fields match their respective regex for 
	 * the laptop.
	 * @param resourceTitle The entered resource title.
	 * @param operatingSystem The entered operating system.
	 * @param model The entered model.
	 * @param manufacturer The entered manufacturer.
	 * @return Whether the passed down fields for the laptop has
	 *         alphabetical characters or not.
	 */
	public static boolean isAlphaLaptop(String resourceTitle, 
			String operatingSystem, String model, String manufacturer) {
		if (resourceTitle.matches(alphaNumRegex) && 
				operatingSystem.matches(alphaNumRegex) && 
				model.matches(modelRegex) &&
				manufacturer.matches(alphabet)) {
			return true;
		} else {
			return false;
		}		
	}	
	
	/**
	 * Checks if the entered value only contains numbers.
	 * @param value The entered value as a string.
	 * @return Whether the entered year is numeric or not.
	 */
	public static boolean isInt(String value) {
		// If it's an optional field, allow it to be empty.
		if (value.matches(digits) || value.isEmpty()) {
			return true;
		} else {
			return false;
		}		
	}
	
	/**
	 * Checks if the entered value is a parsable double.
	 * @param value The entered value as a string.
	 * @return If the entered value is a parsable double or not.
	 */
	public static boolean isDouble(String value) {
		// Will catch a NumberFormatException if the entered 
		// value is not a double (by parsing it as a double).
		try {
			double newDouble = Double.parseDouble(value);
		} catch (Exception NumberFormatException) {
			return false;
		}	
		return true;
	}
	
	/**
	 * Gets the number of days past since the entered date.
	 * @param origionalDate The original date as a string (YYYY-MM-DD).
	 * @return An integer of days past since the entered date.
	 */
	public static int daysPastDate(String originalDate) {
		LocalDate today = LocalDate.now();
		LocalDate thisDate = LocalDate.parse(originalDate);
		int daysBetween = (int) DAYS.between(thisDate, today);
		return daysBetween;
	}
	
	/**
	 * Gets the number of days past between two entered dates.
	 * @param firstDate The first date as a string (YYYY-MM-DD).
	 * @param secondDate The second date as a string (YYYY-MM-DD).
	 * @return An integer of days between the entered dates.
	 */
	public static int daysBetweenDates(String firstDate, String secondDate) {
		LocalDate locFirstDate = LocalDate.parse(firstDate);
		LocalDate locSecondDate = LocalDate.parse(secondDate);
		int daysBetween = (int) DAYS.between(locFirstDate, locSecondDate);
		return daysBetween;
	}
	
	/**
	 * Gets the number of seconds between two entered times.
	 * @param firstTime The first time as a string (HH:MM:SS).
	 * @param secondTime The second time as a string (HH:MM:SS).
	 * @return An integer of seconds between the entered times.
	 */
	public static int secondsBetweenTimes(String firstTime, String secondTime) {
		LocalTime locFirstTime = LocalTime.parse(firstTime);
		LocalTime locSecondTime = LocalTime.parse(secondTime);
		int minutesBetween = (int) SECONDS.between(locFirstTime, locSecondTime);
		return minutesBetween;
	}
}