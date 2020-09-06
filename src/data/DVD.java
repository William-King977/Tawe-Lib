package data;

/**
 * The DVD class models a DVD in the system.
 * @author William King
 */
public class DVD extends Resource {
	
	/** The director who directed the DVD content. */
	private String director;
	
	/** The runtime of the DVD. */
	private double runtime;
	
	/** The main language of the DVD. */
	private String language;
	
	/** List of subtitle languages the DVD has. */
	private String[] subLang;
	
	/**
	 * Constructor for the DVD class.
	 * @param resourceID A unique resource ID.
     * @param resourceTitle A resource's title i.e book title.
     * @param year The year that the resource was released.
     * @param thumbnail A string path to the thumbnail for a resource.
     * @param numberOfCopies The total number of copies of a resource.
	 * @param director The director who directed the DVD content.
	 * @param runtime The runtime of the DVD.
	 * @param language The main language of the DVD.
	 * @param subLang The subtitle languages the DVD provides.
	 */
	public DVD(int resourceID, String resourceTitle, int year, String thumbnail,
			   int numberOfCopies, String director, double runtime, 
			   String language, String[] subLang) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies);
		this.director = director;
		this.language = language;
		this.runtime = runtime;
		this.subLang = subLang;
	}
	
	/**
	 * Gets a string of the DVD's details. Mainly for the resource search.
	 * @return String of the DVD's details.
	 */
	public String toStringSearch() {
		String dvdString = resourceID + "," + resourceTitle + "," + year + 
				"," + language + "," + director;
		return dvdString.toLowerCase();
	}
	
	/**
	 * Gets a string of the DVD's full details for file saving.
	 * @return String of the DVD's full details.
	 */
	public String toStringDetail() {
		// Ensures sub languages are in format of 'lang;lang;lang...'
		String strSubLang = "";
		for (int i = 0; i < subLang.length; i++) {
			if (i == 0) {
				strSubLang = subLang[0];
			} else {
				strSubLang = strSubLang + ";" + subLang[i];
			}
		}

		String dvdString = resourceID + "," + resourceTitle + "," + year + 
				"," + thumbnail + "," + numberOfCopies + ","  + director + 
				"," + runtime + "," + language + "," + strSubLang + ",";
		
		return dvdString;
	}
	
	/**
	 * Gets the director of the DVD.
	 * @return The director of the DVD.
	 */
	public String getDirector() {
		return director;
	}
	
	/**
	 * Sets the director of the DVD.
	 * @param director The director to be set.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Gets the runtime of the DVD.
	 * @return The runtime of the DVD.
	 */
	public double getRuntime(){
		return runtime;
	}
	
	/**
	 * Sets the DVD's runtime.
	 * @param runtime The runtime to be set.
	 */
	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}
	
	/**
     * Returns the main language of the DVD.
     * @return language Returns the language of the DVD.
     */
	public String getLanguage() {
		return language;
	}

	/**
	 * Sets the language for the DVD.
	 * @param language Takes a language as a string.
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * Gets the subtitle languages of the DVD.
	 * @return The subtitle languages of the DVD.
	 */
	public String[] getSubLang(){
		return subLang;
	}
	
	/**
	 * Sets the subtitle languages for the DVD.
	 * @param subLang An array of subtitle languages.
	 */
	public void setSubLang(String[] subLang) {
		this.subLang = subLang;
	}
}
