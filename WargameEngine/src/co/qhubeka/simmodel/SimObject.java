package co.qhubeka.simmodel;

import java.util.HashMap;
import java.util.UUID;

/**
 * This class contains attributes/methods common to all simulation model objects (nodes, entities)
 * @author Adrian de Freitas
 *
 */
public abstract class SimObject {

	// All simulation objects are identified by a unique ID
	private String id;
	
	// All simulation objects have the ability to store custom attributes
	private HashMap<String, String> attributes;
	
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
		
	/**
	 * Gets an attribute (if it exists)
	 * @param attributeName - the attribute name (case sensitive)
	 * @return the attribute value (if it exists)
	 */
	public String getAttribute(String attributeName) {
		
		// Returns null if no attributes have been set
		if (attributes == null) {
			return null;
		}
		
		return attributes.get(attributeName);
		
	}
	
	/**
	 * Specifies if this attribute actually exists
	 * @param attributeName - the name of the attribute (case sensitive)
	 * @return TRUE if the attribute exists; FALSE otherwise
	 */
	public boolean hasAttribute(String attributeName) {
		
		if (attributes == null) {
			return false;
		}
		
		return attributes.containsKey(attributeName);
	}
	
	/**
	 * Adds an attribute
	 * @param attributeName - the name of the attribute (case sensitive)
	 * @param attributeValue - the value of the attribute
	 */
	public void setAttribute(String attributeName, String attributeValue) {
		
		// Memory saving feature:  only creates if an attribute is set
		if (attributes == null) {
			attributes = new HashMap<String, String>(); 
		}
		
		attributes.put(attributeName, attributeValue);
	}

	/**
	 * Removes an attribute
	 * @param attributeName
	 */
	public void removeAttribute(String attributeName) {
		
		// Only removes if the attributes object has actually been created
		if (attributes != null) {
			attributes.remove(attributeName);
			
			if (attributes.size() == 0) {
				attributes = null;
			}
		}
	}
}
