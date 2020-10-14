package data;

/**
 * The Resource class models a resource in the system and 
 * it's a base for the different types of resources.
 * @author William King
 */
abstract public class Resource implements Comparable<Resource> {
	
	/** A unique resource ID. */
	protected int resourceID;
	
	/** A resource's title i.e book title. */
	protected String resourceTitle; 
	
	/** The year that the resource was released. */
	protected int year;
	
	/** A string path to the thumbnail for a resource. */
	protected String thumbnail;
	
	/** The total number of copies of a resource. */
	protected int numberOfCopies;
	
	/** The fine incurred per day for a late return in GBP (£). */
	protected double finePerDay = 2.0;
	
	/** The maximum fine this resource can incur in GBP (£). */
	protected double maxFine = 25.0;
	
	/**
	 * Constructor for the Resource class.
	 * @param resourceID A unique resource ID.
	 * @param resourceTitle A resource's title i.e book title.
	 * @param year The year that the resource was released.
	 * @param thumbnail A string path to the thumbnail for a resource.
	 * @param numberOfCopies The total number of copies of a resource.
	 */
	public Resource(int resourceID, String resourceTitle, int year, 
			String thumbnail, int numberOfCopies) {
		this.resourceID = resourceID;
		this.resourceTitle = resourceTitle;
		this.year = year;
		this.thumbnail = thumbnail;
		this.numberOfCopies = numberOfCopies;
	}
	
	/**
	 * Gets a string of the resources's details. Mainly for the resource search.
	 * THIS METHOD SHOULD BE OVERRIDDEN.
	 * @return String of the resources's details.
	 */
	public String toStringSearch() {
		String strSearch = " ";
		System.out.println("You forgot to override toStringSearch method.");
		return strSearch;
	}
	
	/**
	 * Gets a string of the resource's full details. Mainly for file saving.
	 * THIS METHOD SHOULD BE OVERRIDDEN.
	 * @return String of the resource's details.
	 */
	public String toStringDetail() {
		String strDetail = " ";
		System.out.println("You forgot to override toStringDetail method.");
		return strDetail;
	}
	
	/**
	 * Gets the unique resource ID of the resource.
	 * @return resourceID The unique ID of the resource.
	 */
	public int getResourceID() {
		return resourceID;
	}
	
	/**
	 * Sets the unique ID of the resource
	 * @param resourceID  Set the unique resource ID of the current resource.
	 */
	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}
	
	/**
	 * Gets the Resource Title of the resource.
	 * @return ResourceTitle The title of the resource.
	 */
	public String getResourceTitle() {
		return resourceTitle;
	}
	
	/**
	 * Sets the title for the resource.
	 * @param resourceTitle The title for the resource.
	 */
	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}
	
	/**
	 * Gets the year of the resource.
	 * @return year The year the resource was released.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Sets the year of the resource.
	 * @param year The year the resource was released.
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * Gets the path to the thumbnail image of the resource.
	 * @return A string path to the thumbnail for a resource.
	 */
	public String getThumbnail() {
		return thumbnail;
	}
	
	/**
	 * Sets the path to the thumbnail image.
	 * @param thumbnail A string path to the thumbnail for a resource.
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	/**
	 * Gets the number of remaining copies for the resource.
	 * @return The total number of copies of a resource.
	 */
	public int getNumberOfCopies() {
		return numberOfCopies;
	}
	
	/**
	 * Sets the number of remaining copies for the resource.
	 * @param numberOfCopies The total number of copies for a resource.
	 */
	public void setNumberOfCopies(int numberOfCopies) {
		this.numberOfCopies = numberOfCopies;
	}
	
	
	/**
	 * Gets the fine per day of the resource in GBP (£).
	 * @return finePerDay The fine per day in GBP (£).
	 */
	public double getFinePerDay() {
		return finePerDay;
	}
	
	/**
	 * Gets the maximum fine that the resource can incur in GBP (£).
	 * @return The maximum fine this resource can incur in GBP (£).
	 */
	public double getMaxFine() {
		return maxFine;
	}
	
	/**
	 * Gets the type of resource this is.
	 * @return The resource type as a string (Book, DVD or Laptop).
	 */
	public String getType() {
		return this.getClass().getSimpleName();
	}
	
	/**
	 * Gets a short description of a resource.
	 * @return toString The short description of a resource.
	 */
	public String toString() {
		String resourceDescription = "ID: " + resourceID + 
				" | Title: " + resourceTitle + " | Year: " + year;
		return resourceDescription;
	}
	
	/**
	 * Comparison method used to sort the Resources in ascending order of resource ID.
	 * @param otherResource The other resource object being compared.
	 * @return A positive integer if this > otherResource, otherwise a negative integer.
	 */
	public int compareTo(Resource otherResource) {
		return this.getResourceID() - otherResource.getResourceID();
	}
}