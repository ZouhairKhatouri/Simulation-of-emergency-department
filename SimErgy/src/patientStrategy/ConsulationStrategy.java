package patientStrategy;

import core.SimErgy;
import event.Event;
import event.Outcome;
import event.Transportation;
import human.Patient;
import human.Physician;
import human.Transporter;
import resource.Resource;

/**
 * 
 * This class represents the strategy of patient that has just consulted.
 *
 */

public class ConsulationStrategy extends PatientStrategy {
	
	// Constructor

	public ConsulationStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods

	@Override 
	public void perform() {
		Event event = this.getPatient().getHistory().first();
		
		if(this.getPatient().state=="being visited") { // In this case, the patient is still being visited.
			return;
		}
		
		else {
			
			if(this.getPatient().messageBox.box.first().content=="you have no test to take") { // In this case, the patient has just ended a consultation event and he has to quite the emergency departement, he has to start his outcome.
				Resource physician = event.resources().get(0);
				for(Physician p :this.getSim().ed.physicians) {
					if(p.equals(physician)) {
						Outcome nextevent = new Outcome(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,p);
						this.getPatient().newEvent(nextevent);
						this.getSim().history.append(nextevent);
						this.getPatient().setLengthofStay(this.getSim().ed.clock-this.getPatient().getArrivalTime());
						return;
					}
				}
			}
			
			else { // In this case, the patient has just ended a consultation event and he has to take an exam, he has to start an examination event.
				
				for(Transporter transporter:this.getSim().ed.transporters) { 
					
					if(transporter.isAvailable()) {
						
						switch(this.getPatient().messageBox.box.first().content) {
						
						case "you have to take a radiography exam": // In this case, the patient has to take a radiography exam.
							transporter.setAvailable(false);
							transporter.stretcher.setAvailable(false);
							transporter.stretcher.setUser(this.getPatient());
							this.getPatient().setState("being transported");
							double t1= 5;
							Transportation nextevent1 = new Transportation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t1,transporter,"Radiography departement");
							this.getPatient().newEvent(nextevent1);
							this.getSim().history.append(nextevent1);
							return;
							
						case "you have to take a blood-test exam": // In this case, the patient has to take a blood-test exam.
							transporter.setAvailable(false);
							transporter.stretcher.setAvailable(false);
							transporter.stretcher.setUser(this.getPatient());
							this.getPatient().setState("being transported");
							double t2= 5;
							Transportation nextevent2 = new Transportation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t2,transporter,"Blood test departement");
							this.getPatient().newEvent(nextevent2);
							this.getSim().history.append(nextevent2);
							return;
							
						case "you have to take a mri exam": // In this case, the patient has to take a MRI exam.
							transporter.setAvailable(false);
							transporter.stretcher.setAvailable(false);
							transporter.stretcher.setUser(this.getPatient());
							this.getPatient().setState("being transported");
							double t3= 5;
							Transportation nextevent3 = new Transportation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t3,transporter,"MRI departement");
							this.getPatient().newEvent(nextevent3);
							this.getSim().history.append(nextevent3);
							return;
							
						}
					}
					
					else { // In this case, no transporter is available to transport the patient to the exam health-service.
						return;
					}
					
				}
			}
		}
	}
}
