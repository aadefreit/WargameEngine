package co.qhubeka.simmodel;

/**
 * This class describes a "path" between two nodes.  Edges can represent a single direction, or a two-way path.
 * @author Adrian de Freitas
 *
 */
public class LocationEdge extends SimObject {

	public enum EdgeType { oneway, twoway };
	
	// Specifies where the edge starts and ends
	private LocationNode start, end;
	
	// Specifies if the edge is a one or two-way path
	private EdgeType type;
	
	/**
	 * Constructor
	 * @param id - the unique identifier for this edge.  If NULL, a random ID will be assigned.
	 * @param start - the node where this edge starts
	 * @param end - the node where this edge ends
	 * @param type - the type of node (i.e., one-way, two-way)
	 */
	public LocationEdge(String id, LocationNode start, LocationNode end, EdgeType type) {
		
		super(id);
		
		// Saves the start and end nodes
		this.start = start;
		this.end   = end;
		
		// Saves the node type
		this.type = type;
		
		// Registers the Edge in both the start and end node
		start.addEdge(this);
		end.addEdge(this);
	}

	/**
	 * Returns the node where this edge starts
	 * @return - the location node where this edge starts
	 */
	public LocationNode getStartNode() {
		
		return start;
		
	}
	
	/**
	 * Returns the node where this edge ends
	 * @return - the location node where this edge ends
	 */
	public LocationNode getEndNode() {
		
		return end;
		
	}

	/**
	 * Returns the edge's type (e.g., one-way, two-way)
	 * @return - the edge's type as a LocationEdge.EdgeType
	 */
	public LocationEdge.EdgeType getType() {
		
		return type;
		
	}
	
}
