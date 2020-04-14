package resource;

import auxiliary.ProbabilityDensity;
import auxiliary.Queue;
import human.Patient;

/**
 * 
 * This is the class modelling the consultation facility in this project.
 *
 */

public class Consultation extends HealthService{
	
	//Constructor
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5271605575002880089L;

	public Consultation(int ID,Queue<Patient> waitingQueue,ProbabilityDensity servicetime) {
		super(ID,waitingQueue,servicetime);
	}
	
	public Consultation(int ID, ProbabilityDensity servicetime) {
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
		return "Consultation";
	}
}
