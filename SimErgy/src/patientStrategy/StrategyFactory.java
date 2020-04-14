package patientStrategy;

import core.SimErgy;

import event.Event;
import human.Patient;

/**
 * 
 * This class is the factory part of an factory pattern.It make us able to create strategies for patients according to the workflow described in the project document.
 *
 */

public class StrategyFactory {
	
	// Attributes
	
	/**
	 * @param sim is the simulation that enclose the informations needed to determine the right strategy to create.
	 * @param patient the patient for whom the strategy will be created
	 * 
	 */
	
	private SimErgy sim;
	private Patient patient;
	
	// Constructor
	
	public StrategyFactory(SimErgy sim, Patient patient) {
		super();
		this.sim = sim;
		this.patient = patient;
	}
	
	// Getters and Setters
	
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

	/** 
	 * This method returns a strategy for the patient that is coherent with the current state of the simulation (sim) and with the workflow described in the project document.
	 */
	
	public PatientStrategy createStrategy() {
		Event event = this.getPatient().getHistory().first();
		switch(event.getName()) {
		case "Patient Arrival":
			return new ArrivalStrategy(sim,patient);
		case "Registration/Triage":
			return new RegistrationTriageStrategy(sim,patient);
		case "Transportation/Installation":
			return new TransportationInstallationStrategy(sim,patient);
		case "Consultation":
			return new ConsulationStrategy(sim,patient);
		case "Transportation":
			return new TransportationStrategy(sim,patient);
		case "Examination":
			return new ExaminationStrategy(sim,patient);
		case "Outcome":
			return new OutcomeStrategy(sim,patient);
		}
		return null;
	}
}
