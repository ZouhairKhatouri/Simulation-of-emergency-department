package resource;

import auxiliary.*;
import human.*;

/**
 * 
 * This is the class modelling the arrival facility in this project.
 *
 */

public class Arrival extends HealthService{
	
	//Constructor
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3391887121969351781L;

	public Arrival(int ID, Queue<Patient> waitingQueue, ProbabilityDensity servicetime) {
		super(ID, waitingQueue, servicetime);
	}
	
	public Arrival(int ID, ProbabilityDensity servicetime) {
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
		return "Arrival";
	}
	
}
