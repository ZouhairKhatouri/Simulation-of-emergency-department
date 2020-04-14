package auxiliary;

import core.ED;
import human.Patient;

/**
 * 
 * This class is an object that generates the arrival of patients of a specific severity-level.
 *
 */

public class SpecificArrivalGenerator {
	
	//Attributes
	
	/**
	 * @param emergencyDepartement : the emergency departement where the patients arrival will be generated.
	 * @param sev : the severity level of the patients that will be generated.
	 * @param isWaitingForNextArrival : is a boolean parameter that told us if the generator is blocked because it is waiting for the next arrival.
	 * @param nextArrivalTimestamp : is a double parameter that told us the timestamp of the next patient that will be add to the emergency departement.
	 */
	
	public ED emergencyDepartement;
	public SeverityLevel sev;
	private boolean isWaitingForNextArrival = false;
	private double nextArrivalTimestamp = 0;

	// Constructor
	
	public SpecificArrivalGenerator(ED emergencyDepartement,SeverityLevel sev) {
		this.emergencyDepartement = emergencyDepartement;
		this.sev=sev;
	}
	
	// the main methods
	
	/**
	 * this method sets the arrival's timestamp of the next patient that will be add to the emergency departement with the severity sev
	 * by sampling the probability distribution of sev.
	 */
	
	private void setNextArrivalTimestamp() {
		if(isWaitingForNextArrival) {
			return;
		}
		else {
			nextArrivalTimestamp = emergencyDepartement.clock+sev.arrivalprobability.sample();
			isWaitingForNextArrival=true;
			return;
		}
	}
	
	/**
	 * this method adds the new patient to the emergency departement.
	 * @throws Exception 
	 */
	
	private void newArrival() throws Exception {
		
		//We set randomly the health insurance of the next patient we will add to the emergency departement 
		double x = (new Unif(0,100)).sample();
		String healthinsurance;
		if(x<50) {
			healthinsurance="";
		}
		else if(x<80 && x>=50) {
			healthinsurance="Silver";
		}
		else {
			healthinsurance="Gold";
		}
		
		// We set the ID of the next patient we will add to the emergency departement
		int ID = this.emergencyDepartement.nextID();
		
		// The new Patient
		Patient newPatient = new Patient("Generated Patient "+Integer.toString(ID),"",ID,healthinsurance,sev.name,this.emergencyDepartement.clock);
		
		// We add it
		this.emergencyDepartement.patients.add(newPatient);
		
		return;
	}
	
	/**
	 *  This method try to add a new patient in the current time clock of the emergency departement
	 *  by looking at first if we are waiting for his arrival, and then by looking if it is the right time to do so.
	 * @throws Exception 
	 */
	
	public void tryAddingNewPatient() throws Exception {
		if(isWaitingForNextArrival) {
			if(nextArrivalTimestamp<emergencyDepartement.clock) {
				this.newArrival();
				isWaitingForNextArrival=false;
			}
		}
		else {
			this.setNextArrivalTimestamp();
		}
		return;
		
	}
	
}
