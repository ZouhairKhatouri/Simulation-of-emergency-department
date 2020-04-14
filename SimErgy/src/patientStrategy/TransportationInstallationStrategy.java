package patientStrategy;

import java.util.Observer;

import core.SimErgy;
import human.Patient;
import human.Physician;
import resource.Visitor;

/**
 * 
 * This class represents the strategy of patient that has just installed.
 *
 */

public class TransportationInstallationStrategy extends PatientStrategy{
	
	// Constructor

	public TransportationInstallationStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods
	
	@Override
	public void perform() {
		
		if(this.getPatient().state=="being installed") { // In this case, the patient is still being installed in his personnal room by a nurse.
			return;
		}
		
		else if (this.getPatient().getState()=="is being registred in consultation departement") { // In this case, the patient has ended the transportation/Installation event, however, he still didn't get in the waiting queue of the consultation health-service.
			this.getSim().ed.consultationDepartment.newPatient(this.getPatient());
			this.getPatient().setState("is waiting");
			return;
		}
		
		else { // In this case, the patient is only waiting for his turn in the consultation departement (health-service) to start his first consultation.
			
			if(this.getSim().ed.consultationDepartment.headOfQueue().equals(this.getPatient())) { // In this case, the patient is the head of the waiting queue, he has to be served by the consultation departement.
				for(Physician physician:this.getSim().ed.physicians) {
					if(physician.state=="idle") {
						this.getSim().ed.consultationDepartment.serve(new Visitor(this.getSim(),physician));
						this.getPatient().addObserver((Observer)physician);
						return;
					}
				}
				return;
			}
			
			return; // Otherwise, the patient is in the middle of the waiting queue, he has nothing to do.
		}
		
	}
}
