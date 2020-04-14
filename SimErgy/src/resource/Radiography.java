package resource;

import auxiliary.ProbabilityDensity;
import auxiliary.Queue;
import human.Patient;

/**
 * 
 * This is the class modelling the radiography facility in this project.
 *
 */

public class Radiography extends HealthService {
	
	//Constructor
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6556258278408584134L;

	public Radiography(int ID,Queue<Patient> waitingQueue,ProbabilityDensity servicetime) {
		super(ID,waitingQueue,servicetime);
	}
	
	public Radiography(int ID, ProbabilityDensity servicetime) {
		super(ID,servicetime);
	}
	
	//This is this the "visit" part of the Visitor pattern
	
	@Override
	public void serve(Visitor visitor) {
		visitor.serve(this);
		return;
	}

	@Override
	public String getType() {
		return "Radiography";
	}
}
