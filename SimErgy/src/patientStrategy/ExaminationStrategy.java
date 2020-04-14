package patientStrategy;

import auxiliary.Message;
import core.SimErgy;
import event.ConsultationEvent;
import human.Patient;

/**
 * 
 * This class represents the strategy of patient that has just examinated.
 *
 */

public class ExaminationStrategy extends PatientStrategy {
	
	// Constructor

	public ExaminationStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods

	@Override
	public void perform() {
		
		if(this.getPatient().state=="being examinated") { // In this case, the patient is still taking an exam.
			return;
		}
		
		else { // In this case, the patient has ended the examination event and has to start a new visit by his personnal physician.
			
			Message message = new Message("Doctor, i just finished the exam, can you please visite me?",this.getPatient(),this.getPatient().personnalphysician,this.getSim().ed.clock);
			this.getPatient().personnalphysician.handleMessage(message);
			
			if(this.getPatient().personnalphysician.getState()=="idle") {  // In this case, the patient can start a new consultation event.
				this.getPatient().notifyObservers(); // Observer pattern between the patient and his personnal physician.
				this.getPatient().setLocation("personnal room");
				this.getPatient().setState("being visited");
				double t=this.getSim().ed.consultationDepartment.getServicetime().sample();
				ConsultationEvent nextevent = new ConsultationEvent(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t,this.getPatient().personnalphysician);
				this.getPatient().newEvent(nextevent);
				this.getSim().history.append(nextevent);
				return;
			}
			
			else { // The personnal physician is occuped by visiting another patient.
				return;
			}
			
		}
		
	}
}
