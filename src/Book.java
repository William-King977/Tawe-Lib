/**
 * The Book class models a book in the system.
 * @author William King
 */
public class Book extends Resource {
	
	/** The author of the book. */
    private String author;
    
    /** The publisher of the book. */
    private String publisher;
    
    /** The genre of the book. */
    private String genre;
    
    /** The ISBN of the book. */
    private String ISBN;
    
    /** The language of the book. */
    private String language;
	
    /**
     * Constructor for the Book class.
     * @param resourceID A unique resource ID.
     * @param resourceTitle A resource's title i.e book title.
     * @param year The year that the resource was released.
     * @param thumbnail A string path to the thumbnail for a resource.
     * @param numberOfCopies The total number of copies of a resource.
     * @param author The author who wrote the book.
     * @param publisher The publisher of the book.
     * @param genre The genre of the book. 
     * @param ISBN The ISBN of the book.
     * @param language The language of the book.
     */
	public Book(int resourceID, String resourceTitle, int year, String thumbnail,
			int numberOfCopies, String author, 
			String publisher, String genre, String ISBN, String language) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies);
		this.author = author;
		this.publisher = publisher;
		this.genre = genre;
		this.ISBN = ISBN;
		this.language = language;
	}
	
	/**
	 * Gets a string of the book's details. Mainly for the resource search.
	 * @return String of the book's details.
	 */
	public String toStringSearch() {
		String bookString = resourceID + "," + resourceTitle + "," + year + 
				"," + genre + "," + language + "," + author + 
				"," + publisher;
		return bookString.toLowerCase();
	}
	
	/**
	 * Gets a string of the book's full details for file saving.
	 * @return String of the book's full details.
	 */
	public String toStringDetail() {

		String bookString = resourceID + "," + resourceTitle + "," + year + 
				"," + thumbnail + "," + numberOfCopies + "," + author + 
				"," + publisher + "," + genre + "," + ISBN + 
				"," + language + ",";
		
		return bookString;
	}
	
	/**
	 * Gets the author who wrote the book.
	 * @return Author of the book.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Sets the author who wrote the book.
	 * @param author The author of the book.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
     * Returns a genre such as Horror etc.
     * @return genre Returns the genre of the book.
     */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the genre for the book.
	 * @param genre Takes a genre as string.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
	 * Gets the publisher of the book.
	 * @return The publisher of the book.
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * Sets the publisher of the book.
	 * @param publisher The publisher of the book.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Gets the ISBN of the book.
	 * @return The ISBN of the book.
	 */
	public String getISBN() {
		return ISBN;
	}
	
	/**
	 * Sets the ISBN of the book.
	 * @param ISBN The ISBN of the book.
	 */
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	/**
     * Returns the language of the book.
     * @return language Returns the language of the book.
     */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language for the book.
	 * @param language Takes a language as a string.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
