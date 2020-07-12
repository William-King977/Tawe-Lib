/**
 * The Resource class models a resource in the system and 
 * it's a base for the different types of resources. 
 * @author William King
 */
abstract public class Resource {
    
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
     * Gets the unique resource ID of the resource.
     * @return resourceID The unique ID of the resource.
     */
    protected int getResourceID() {
    	return resourceID;
    }
    
    /**
     * Sets the unique ID of the resource
     * @param resourceID  Set the unique resource ID of the current resource.
     */
    protected void setResourceID(int resourceID) {
    	this.resourceID = resourceID;
    }
    
    /**
     * Gets the Resource Title of the resource.
     * @return ResourceTitle The title of the resource.
     */
    protected String getResourceTitle() {
    	return resourceTitle;
    }
    
    /**
     * Gets the year of the resource.
     * @return year The year the resource was released.
     */
    protected int getYear() {
    	return year;
    }
    
    /**
     * Gets the path to the thumbnail image of the resource.
     * @return A string path to the thumbnail for a resource.
     */
    protected String getThumbnail() {
    	return thumbnail;
    }
    
    /**
     * Gets the number of remaining copies for the resource.
     * @return The total number of copies of a resource.
     */
    protected int getNumberOfCopies() {
    	return numberOfCopies;
    }
    
    /**
     * Sets the number of remaining copies for the resource.
     * @param numberOfCopies The total number of copies for a resource.
     */
    protected void setNumberOfCopies(int numberOfCopies) {
    	this.numberOfCopies = numberOfCopies;
    }
    

    /**
     * get the amount of fine per day of the resource.
     * @return finePerDay
     */
    protected double getFinePerDay() {
    	return finePerDay;
    }
    
    /**
     * Gets the maximum fine that the resource can incur in GBP (£).
     * @return The maximum fine this resource can incur in GBP (£).
     */
    protected double getMaxFine() {
    	return maxFine;
    }
        
    /**
     * Gets a short description of a resource.
     * @return toString The short description of a resource.
     */
    public String toString() {
    	String resourceDescription = "Resource ID: " + resourceID + 
    			" | Title: " + resourceTitle + " | Year: " + year;
    	return resourceDescription;
    }
}
