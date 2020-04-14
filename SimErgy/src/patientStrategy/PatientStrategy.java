package patientStrategy;

import core.SimErgy;
import human.Patient;

/**
 * 
 * This abstract class represents a given patient's strategy, i.e. what a patient aim to do at a given stage of the simulation
 *
 */

public abstract class PatientStrategy {
	
	// Attributes
	
	/**
	 * 
	 * @param sim : the simulation that containts the state that this strategy may change.
	 * @param patient : the patient that own this strategy.
	 * 
	 */
	
	private SimErgy sim;
	private Patient patient;
	
	// Constructor
	
	public PatientStrategy(SimErgy sim, Patient patient) {
		this.sim = sim;
		this.patient = patient;
	}
	
	/**
	 * This method performs the changes on the system that the patient aim to do, according to a strategy that is coeherent with the workflow.
	 */
	
	public abstract void perform();
	
	// Getters and setters
	
	public SimErgy getSim() {
		return sim;
	}

	public void setSim(SimErgy sim) {
		this.sim = sim;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
