package co.qhubeka.simmodel;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;

public class SimModel {

	// JSON Constants
	public static final String JSON_ARRAY_NODES 		  = "nodes";
	public static final String JSON_ARRAY_EDGES 		  = "edges";
	public static final String JSON_PROPERTY_ATTRIBUTES   = "attributes";
	public static final String JSON_PROPERTY_COORDINATE   = "coordinate";
	public static final String JSON_PROPERTY_COORDINATE_X = "x";
	public static final String JSON_PROPERTY_COORDINATE_Y = "y";
	public static final String JSON_PROPERTY_COORDINATE_Z = "z";
	public static final String JSON_PROPERTY_ID 		  = "id";
	public static final String JSON_PROPERTY_EDGE_START   = "start";
	public static final String JSON_PROPERTY_EDGE_END     = "end";
	public static final String JSON_PROPERTY_EDGE_TYPE    = "type";
	
	// Simulation Model
	private HashMap<String, LocationNode> modelNodes;
	private HashMap<String, LocationEdge> modelEdges;
	private HashMap<String, Entity>		  modelEntities;
	
	/**
	 * Constructor
	 */
	public SimModel() {
		
		// Initializes the model's nodes, edges, and entities
		modelNodes    = new HashMap<String, LocationNode>();
		modelEdges    = new HashMap<String, LocationEdge>();
		modelEntities = new HashMap<String, Entity>();
		
	}
	
	/**
	 * Clears the entire state of the model.  This is used when you need to load a new state.
	 */
	public void clear() {
		// Erases the Entire State of the Model
		modelNodes.clear();
		modelEdges.clear();
	}
	
	/**
	 * Loads a model based on a JSON specification
	 * @param modelJSON
	 */
	public void load(String modelJSON) {
		
		// Creates a parser that can read and interpret the JSON
		JsonParser parser = new JsonParser();
		
		// Parses the JSON
		JsonElement jsonTree = parser.parse(modelJSON);
		JsonArray   nodes    = jsonTree.getAsJsonObject().get(JSON_ARRAY_NODES).getAsJsonArray();
		JsonArray   edges    = jsonTree.getAsJsonObject().get(JSON_ARRAY_EDGES).getAsJsonArray();
		
		// Creates Nodes
		for (int i=0; i<nodes.size(); i++) {
			JsonObject nodeObject = nodes.get(i).getAsJsonObject();
			
			String id = nodeObject.get(JSON_PROPERTY_ID).getAsString();
			double x  = nodeObject.get(JSON_PROPERTY_COORDINATE).getAsJsonObject().get(JSON_PROPERTY_COORDINATE_X).getAsDouble();
			double y  = nodeObject.get(JSON_PROPERTY_COORDINATE).getAsJsonObject().get(JSON_PROPERTY_COORDINATE_Y).getAsDouble();
			double z  = nodeObject.get(JSON_PROPERTY_COORDINATE).getAsJsonObject().get(JSON_PROPERTY_COORDINATE_Z).getAsDouble();
			
			LocationNode newNode 		 = new LocationNode(id, x, y, z);
			JsonObject 	 attributeObject = nodeObject.getAsJsonObject(JSON_PROPERTY_ATTRIBUTES).getAsJsonObject();
			
			for (Map.Entry<String,JsonElement> entry : attributeObject.entrySet()) {
				newNode.setAttribute(entry.getKey(), entry.getValue().getAsString());
				//System.out.println("Attribute " + entry.getKey() + " added: " + entry.getValue().getAsString());
			}
			
			if (!modelNodes.containsKey(newNode.getID())) {
				modelNodes.put(newNode.getID(), newNode);
				System.out.println("Added Node " + id + " (" + x + ", " + y + ", " + z + ")");
			}
			else {
				System.err.println("Unable to add location node:  ID " + newNode.getID() + " already exists.");
			}
		}
		
		// Creates Edges
		for (int i=0; i<edges.size(); i++) {
			JsonObject edgeObject = edges.get(i).getAsJsonObject();
			
			String id 		   = edgeObject.get(JSON_PROPERTY_ID).getAsString();
			String startNodeID = edgeObject.get(JSON_PROPERTY_EDGE_START).getAsString();
			String endNodeID   = edgeObject.get(JSON_PROPERTY_EDGE_END).getAsString();
			String type        = edgeObject.get(JSON_PROPERTY_EDGE_TYPE).getAsString();
			
			LocationNode 		  startNode = modelNodes.get(startNodeID);
			LocationNode 		  endNode   = modelNodes.get(endNodeID);
			LocationEdge.EdgeType edgeType  = type.equals("oneway") ? LocationEdge.EdgeType.oneway : LocationEdge.EdgeType.twoway;
			LocationEdge 		  newEdge   = new LocationEdge(id, startNode, endNode, edgeType);
			
			JsonObject attributeObject = edgeObject.getAsJsonObject(JSON_PROPERTY_ATTRIBUTES).getAsJsonObject();
			
			for (Map.Entry<String,JsonElement> entry : attributeObject.entrySet()) {
				newEdge.setAttribute(entry.getKey(), entry.getValue().getAsString());
			}
			
			if (!modelEdges.containsKey(newEdge.getID())) {
				modelEdges.put(newEdge.getID(), newEdge);
				System.out.println("Added Edge " + id + " (" + newEdge.getStartNode().getID() + " -> " + newEdge.getEndNode().getID() + ")");
			}
			else {
				System.err.println("Unable to add location edge:  ID " + newEdge.getID() + " already exists.");
			}
		}
	}
}
