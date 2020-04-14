package patientStrategy;

import core.SimErgy;
import human.Patient;

/**
 * 
 * This class represents the strategy of patient that has just quite the emergency departement.
 *
 */

public class OutcomeStrategy extends PatientStrategy {
	
	// Constructor

	public OutcomeStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods
	
	@Override
	public void perform() {
		return; // The patient has nothing to do with the simulation.
	}
}
