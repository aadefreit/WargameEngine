package co.qhubeka.simmodel;

/**
 * This class represents a "piece" that resides at a location node
 * @author Adrian de Freitas
 *
 */
public class Entity extends SimObject {

	// The location node at which this entity resides
	private LocationNode currentLocation;
	
	/**
	 * Constructor
	 * @param id - the unique identifier of the entity
	 */
	public Entity(String id) {
		
		super(id);
	
		currentLocation = null;
		
	}

	/**
	 * Returns the Current Location of this Entity
	 */
	public LocationNode getCurrentLocation() {
		
		return this.currentLocation;
		
	}
	
	/**
	 * Sets the Current Location of this Entity
	 * @param location
	 */
	public void setCurrentLocation(LocationNode location) {
		
		this.currentLocation = location;
		
	}
	
}
