/**
 * The DVD class models a DVD in the system.
 * @author William King
 */
public class DVD extends Property {
	
	/** The director who directed the DVD content. */
	private String director;
	
	/** The runtime of the DVD. */
	private double runtime;
	
	/** The subtitle language the DVD provides. */
	private String subLang;
	
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
	 * @param subLang The subtitle language the DVD provides.
	 */
	public DVD(int resourceID, String resourceTitle, int year, String thumbnail,
			   int numberOfCopies, String genre, String language, 
			   String director, double runtime, String subLang) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies, 
				genre, language);
		this.director = director;
		this.runtime = runtime;
		this.subLang = subLang;
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
	 * Gets the subtitle language of the DVD.
	 * @return This subtitle language of the DVD.
	 */
	public String getSubLang(){
		return subLang;
	}
}
