package resource;

import auxiliary.ProbabilityDensity;
import auxiliary.Queue;
import human.Patient;

/**
 * 
 * This is the class modelling the MRI facility in this project.
 *
 */

public class MRI extends HealthService{
	
	//Constructor
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8924982357068934099L;

	public MRI(int ID,Queue<Patient> waitingQueue,ProbabilityDensity servicetime) {
		super(ID,waitingQueue,servicetime);
	}
	
	public MRI(int ID, ProbabilityDensity servicetime) {
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
		return "MRI";
	}
}
