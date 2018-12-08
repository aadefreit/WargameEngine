package co.qhubeka.simmodel;

import java.util.HashMap;

/**
 * This class represents a single location in our simulation
 * @author Adrian de Freitas
 */
public class LocationNode extends SimObject {

	// The cartesian coordinate of the node within the world
	private double x, y, z = 0.0;
	
	// A collection of entities that are located at this node
	private HashMap<String, Entity> entities;
	
	/**
	 * Constructor
	 * @param id - The unique identifier of the location node
	 */
	public LocationNode(String id) {
		
		super(id);
		
		entities = new HashMap<String, Entity>();
	}
	
	/**
	 * Adds an Entity to this Location Node
	 * @param entity
	 */
	public void addEntity(Entity entity) {
		
		// Adds the entity to node
		entities.put(entity.getID(), entity);
		
	}
	
	/**
	 * Removes an Entity from this Location Node
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		
		// Removes the entity from the node
		
		
	}
	
}
