package co.qhubeka.simmodel;

import java.util.UUID;

/**
 * This class contains attributes/methods common to all simulation model objects (nodes, entities)
 * @author Adrian de Freitas
 *
 */
public abstract class SimObject {

	// All simulation objects are identified by a unique ID
	private String id;
	
	/**
	 * Constructor
	 * @param id - The unique ID for this simulation object.  If NULL, a random ID will be generated 
	 */
	public SimObject(String id) {
			
		this.id = (id != null) ? id : UUID.randomUUID().toString();
	
	}
	
	/**
	 * Returns the ID for this object
	 * @return
	 */
	public String getID() {
		
		return this.id;
		
	}
	
}
