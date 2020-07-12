/**
 * The Property class extends the properties of Book and DVD from a resource. 
 * It allows a genre and language to be associated with them.
 * @author William King
 */
public class Property extends Resource {
	
	/** A genre of a resource. */
    protected String genre;
    
    /** A language of a resource. */
    protected String language;
	
    /**
     * Constructor for the Property class.
     * @param resourceID A unique resource ID.
     * @param resourceTitle A resource's title i.e book title.
     * @param year The year that the resource was released.
     * @param thumbnail A string path to the thumbnail for a resource.
     * @param numberOfCopies The total number of copies of a resource.
     * @param genre A genre of a resource.
     * @param language A language of a resource.
     */
	public Property(int resourceID, String resourceTitle, int year, String thumbnail,
			int numberOfCopies, String genre, String language) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies);
		this.genre = genre;
		this.language = language;
	}
	
	/**
     * Returns a genre such as Horror etc.
     * @return genre Returns the genre.
     */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the genre for a resource.
	 * @param genre Takes a genre as string.
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	/**
     * Returns a language of a specific resource.
     * @return language Returns the language of a resource.
     */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language for a resource.
	 * @param language Takes a language as a string.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
}
