package resource;

import auxiliary.ProbabilityDensity;
import auxiliary.Queue;
import human.Patient;

/**
 * 
 * This is the class modelling the blood-test facility in this project.
 *
 */

public class BloodTest extends HealthService{
	
	//Constructor
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6431016971693687025L;

	public BloodTest(int ID,Queue<Patient> waitingQueue,ProbabilityDensity servicetime) {
		super(ID,waitingQueue,servicetime);
	}
	
	public BloodTest(int ID, ProbabilityDensity servicetime) {
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
		return "Blood-Test";
	}
}
