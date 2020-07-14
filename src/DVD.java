/**
 * The DVD class models a DVD in the system.
 * @author William King
 */
public class DVD extends Property {
	
	/** The director who directed the DVD content. */
	private String director;
	
	/** The runtime of the DVD. */
	private double runtime;
	
	/** List of subtitle languages the DVD has. */
	private String[] subLang;
	
	/**
	 * Constructor for the DVD class.
	 * resourceID A unique resource ID.
     * @param resourceTitle A resource's title i.e book title.
     * @param year The year that the resource was released.
     * @param thumbnail A string path to the thumbnail for a resource.
     * @param numberOfCopies The total number of copies of a resource.
     * @param genre A genre of a resource.
     * @param language A language of a resource.
	 * @param director The director who directed the DVD content.
	 * @param runtime The runtime of the DVD.
	 * @param subLang The subtitle languages the DVD provides.
	 */
	public DVD(int resourceID, String resourceTitle, int year, String thumbnail,
			   int numberOfCopies, String genre, String language, 
			   String director, double runtime, String[] subLang) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies, 
				genre, language);
		this.director = director;
		this.runtime = runtime;
		this.subLang = subLang;
	}
	
	/**
	 * Gets a string of the DVD's details. Mainly for the resource search.
	 * @return String of the DVD's details.
	 */
	public String toStringSearch() {
		String dvdString = resourceID + "," + resourceTitle + "," + year + 
				"," + genre + "," + language + "," + director;
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
				"," + thumbnail + "," + numberOfCopies + "," + genre + 
				"," + language + "," + director + "," + runtime + 
				"," + strSubLang + ",";
		
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
	 * Gets the runtime of the DVD.
	 * @return The runtime of the DVD.
	 */
	public double getRuntime(){
		return runtime;
	}

	/**
	 * Gets the subtitle languages of the DVD.
	 * @return This subtitle languages of the DVD.
	 */
	public String[] getSubLang(){
		return subLang;
	}
}
