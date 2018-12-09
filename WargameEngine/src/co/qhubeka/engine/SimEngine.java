package co.qhubeka.engine;

import java.nio.file.Files;
import java.nio.file.Paths;

import co.qhubeka.simcontroller.SimController;
import co.qhubeka.simmodel.SimModel;

public class SimEngine {

	// The Controller
	private SimController controller;
	
	// The Model (Contains Locations, Edges, and Entities)
	private SimModel model;
	
	/**
	 * Constructor
	 */
	public SimEngine() {

		// TODO:  Actually make this configurable.  I'm testing.  Leave me alone.
		try {
			System.out.print("Loading map . . . ");
			String json = new String(Files.readAllBytes(Paths.get("resources/map.json")));
			System.out.println("DONE! (" + json.length() + " bytes read)");
			
			model = new SimModel();
			model.load(json);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Test Application
	 * @param args
	 */
	public static void main(String[] args) {
		
		SimEngine engine = new SimEngine();
	
	}
	
}
