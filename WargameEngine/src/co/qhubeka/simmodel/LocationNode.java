package co.qhubeka.simmodel;

import java.util.HashMap;

/**
 * This class represents a single location in our simulation
 * @author Adrian de Freitas
 */
public class LocationNode extends SimObject {

	// The cartesian coordinate of the node within the world
	private double x, y, z;
	
	// A collection of entities that are located at this node
	private HashMap<String, Entity> entities;
	
	// A collection of edges that include this node as either the start or end
	private HashMap<String, LocationEdge> edges;
	
	/**
	 * Constructor
	 * @param id - The unique identifier of the location node
	 */
 	public LocationNode(String id) {
		
		super(id);
		
		// Creates a collection to store all entities
		entities = new HashMap<String, Entity>();
		
		// Creates a collection to store all edges
		edges = new HashMap<String, LocationEdge>();
	
	}
	
	/**
	 * Adds an Edge to this Location NOde
	 * @param edge - the Edge to add
	 */
	public void addEdge(LocationEdge edge) {
		
		edges.put(edge.getID(), edge);
		
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
	 * Removes an Edge from this Location Node
	 * @param edge - the edge to remove
	 */
	public void removeEdge(LocationEdge edge) {
		
		edges.remove(edge.getID());
		
	}
	
	/**
	 * Removes an Entity from this Location Node
	 * @param entity
	 */
	public void removeEntity(Entity entity) {
		
		// Removes the entity from the node
		entities.remove(entity.getID());
		
	}
	
}
