/**
 * The Book class models a book in the system.
 * @author William King
 */
public class Book extends Property {
	
	/** Author of a book. */
    private String author;
    
    /** Publisher of a book. */
    private String publisher;
    
    /** The ISBN of a book. */
    private String ISBN;
	
    /**
     * Constructor for the Book class.
     * @param resourceID A unique resource ID.
     * @param resourceTitle A resource's title i.e book title.
     * @param year The year that the resource was released.
     * @param thumbnail A string path to the thumbnail for a resource.
     * @param numberOfCopies The total number of copies of a resource.
     * @param genre A genre of a resource.
     * @param language A language of a resource.
     * @param author The author who wrote the book.
     * @param publisher The publisher of the book.
     * @param ISBN The ISBN of the book.
     */
	public Book(int resourceID, String resourceTitle, int year, String thumbnail,
			int numberOfCopies, String genre, String language, String author, 
			String publisher, String ISBN) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies, 
				genre, language);
		this.author = author;
		this.publisher = publisher;
		this.ISBN = ISBN;
	}
	
	/**
	 * Gets a string of the book's details. Mainly for the resource search.
	 * @return String of the book's details.
	 */
	public String toStringSearch() {
		String bookString = resourceID + " " + resourceTitle + " " + year + 
				" " + genre + " " + language + " " + author + 
				" " + publisher;
		return bookString.toLowerCase();
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
}
