/**
 * The Laptop class models a laptop within the system.
 * @author William King
 */
public class Laptop extends Resource {
	
	/** The Laptop's manufacturer. */
    private String manufacturer;
    
    /** The Laptop's model. */
    private String model;
    
    /** The Laptop's operating system. */
    private String operatingSystem;
    
    /** The fine incurred per day for a late return in GBP (£). */
    private final double FINE_PER_DAY = 10.00;
    
    /** The maximum fine this resource can incur in GBP (£). */
    private final double MAX_FINE = 100.00;
    
    /**
     * Constructor for the Laptop class.
     * @param resourceID A unique resource ID.
     * @param resourceTitle A resource's title i.e book title.
     * @param year The year that the resource was released.
     * @param thumbnail A string path to the thumbnail for a resource.
     * @param numberOfCopies The total number of copies of a resource.
     * @param loanDuration The maximum duration of the loan of this 
     *                     resource (Days).
     * @param manufacturer The Laptop's manufacturer.
     * @param model The Laptop's model.
     * @param operatingSystem The Laptop's operating system.
     */
	public Laptop(int resourceID, String resourceTitle, int year, 
			String thumbnail, int numberOfCopies, String manufacturer, 
			String model, String operatingSystem) {
		super(resourceID, resourceTitle, year, thumbnail, numberOfCopies);
		this.manufacturer = manufacturer;
        this.model = model;
        this.operatingSystem = operatingSystem;
	}
	
	/**
	 * Gets a string of the laptop's details. Mainly for the resource search.
	 * @return String of the laptop's details.
	 */
	public String toStringSearch() {
		String laptopString = resourceID + "," + resourceTitle + "," + year + 
				"," + manufacturer + "," + model + "," + operatingSystem;
		return laptopString.toLowerCase();
	}
	
	/**
	 * Gets a string of the laptop's full details for file saving.
	 * @return String of the laptop's full details.
	 */
	public String toStringDetail() {

		String laptopString = resourceID + "," + resourceTitle + "," + year + 
				"," + thumbnail + "," + numberOfCopies + "," + manufacturer + 
				"," + model + "," + operatingSystem + ",";
		
		return laptopString;
	}
	
	/**
     * Gets the Laptop's manufacturer.
     * @return The Laptop's manufacturer.
     */
    public String getManufacturer() {
    	return manufacturer;
    }
    
    /**
     * Sets the Laptop's manufacturer.
     * @param manufacturer The Laptop's manufacturer.
     */
    public void setManufacturer(String manufacturer) {
    	this.manufacturer = manufacturer;
    }

    /**
     * Gets the Laptop's model.
     * @return The Laptop's model.
     */
    public String getModel() {
    	return model;
    }
    
    /**
     * Sets the Laptop's model.
     * @param model The Laptop's model.
     */
    public void setModel(String model) {
    	this.model = model;
    }
    
    /**
     * Gets the Laptop's operating system.
     * @return The Laptop's operating system.
     */
    public String getOperatingSystem() {
    	return operatingSystem;
    }
    
    /**
     * Sets the Laptop's operating system.
     * @param operatingSystem The Laptop's operating system.
     */
    public void setOperatingSystem(String operatingSystem) {
    	this.operatingSystem = operatingSystem;
    }

    /**
     * Gets the fine per day for the Laptop.
     * @return The Laptop's fine per day.
     */
    public double getFinePerDay() {
    	return FINE_PER_DAY;
    }

    /**
     * Gets the maximum fine for the Laptop.
     * @return The Laptop's maximum fine.
     */
    public double getMaxFine() {
    	return MAX_FINE;
    }
}
