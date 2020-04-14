package patientStrategy;

import core.SimErgy;
import event.Event;
import human.Nurse;
import human.Patient;
import resource.Visitor;

/**
 * 
 * This class represents the strategy of patient that has just arrived.
 *
 */

public class ArrivalStrategy extends PatientStrategy{
	
	// Constructor
	
	public ArrivalStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods
	
	@Override
	public void perform() {
		
		Event event = this.getPatient().getHistory().first();
		
		if(this.getPatient().state=="is waiting") { // In this case, the patient is waiting in the waiting queue of arrival (health-service).
			
			if(this.getSim().ed.arrival.headOfQueue().equals(this.getPatient())) { // In this case, is the head of the queue of arrival.
				for(Nurse nurse:this.getSim().ed.nurses) {
					if(nurse.isAvailable()) {
						this.getSim().ed.arrival.serve(new Visitor(this.getSim(),nurse));
						return;
					}
				}
				return;
			}
			
			else { // In this case, the patient is in the middle of the waiting queue, he has nothing to do.
				return;
			}			
		}
		
		else {
			
			if(this.getPatient().getArrivalTime()>this.getSim().ed.clock) {
				return;
			}
			
			 // In this case has just arrived to the emergency departement, he has to be add to the arrival waiting queue.
			this.getSim().ed.arrival.newPatient(this.getPatient());
			this.getSim().history.append(event);
			this.getPatient().setState("is waiting");
			return;
		}
		
	}
}
