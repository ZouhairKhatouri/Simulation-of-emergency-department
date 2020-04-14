package patientStrategy;

import core.SimErgy;
import human.Patient;
import resource.BloodTestLab;
import resource.MRIroom;
import resource.RadiographyRoom;
import resource.Visitor;

/**
 * 
 * This class represents the strategy of patient that has just transported.
 *
 */

public class TransportationStrategy extends PatientStrategy{

	// Constructor
	
	public TransportationStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods
	
	@Override
	public void perform() {
		
		if(this.getPatient().state=="is waiting") { // In this case, the patient has been transported to the facility departement and been and added to the right waiting queue.
			
			switch(this.getPatient().getLocation()) {
			
			case "Radiography departement":
				
				if(this.getSim().ed.radiographyDepartment.headOfQueue().equals(this.getPatient())) { // The patient is the head of the waiting queue of radiography departement, he has to be served by this health-service.
					for(RadiographyRoom room:this.getSim().ed.radiographyRooms) {
						if(room.isAvailable()) {
							this.getSim().ed.radiographyDepartment.serve(new Visitor(this.getSim(),room));
							return;
						}
					}
					return;
				}
				
				else { // In this case, the patient is waiting in the middle of the waiting queue, he has nothing to do.
					return;
				}
				
			case "Blood test departement":
				
				if(this.getSim().ed.bloodTestDepartment.headOfQueue().equals(this.getPatient())) { // The patient is the head of the waiting queue of blood-test departement, he has to be served by this health-service.
					for(BloodTestLab room:this.getSim().ed.bloodTestLabs) {
						if(room.isAvailable()) {
							this.getSim().ed.bloodTestDepartment.serve(new Visitor(this.getSim(),room));
							return;
						}
					}
					return;
				}
				
				else { // In this case, the patient is waiting in the middle of the waiting queue, he has nothing to do.
					return;
				}
				
			case "MRI departement":
				
				if(this.getSim().ed.mriDepartment.headOfQueue().equals(this.getPatient())) { // The patient is the head of the waiting queue of MRI departement, he has to be served by this health-service.
					for(MRIroom room:this.getSim().ed.mriRooms){
						if(room.isAvailable()) {
							this.getSim().ed.mriDepartment.serve(new Visitor(this.getSim(),room));
							return;
						}
					}
					return;
				}
				
				else { // In this case, the patient is waiting in the middle of the waiting queue, he has nothing to do.
					return;
				}
			}
			
		}
		else {
			
			if(this.getPatient().state=="being transported") { // In this case, the patient is still being transported, he has nothing to do.
				return;
			}
			
			else { // In this case, the patient has ended the transportation event, but still didn't been added to the waiting queue of the departement where he has to take the exam.
				
				switch(this.getPatient().getLocation()) { // We add him according to his location, and set his state to waiting.
				
				case "Radiography departement":
					this.getSim().ed.radiographyDepartment.newPatient(this.getPatient());
					this.getPatient().setState("is waiting");
					return;
					
				case "Blood test departement":
					this.getSim().ed.bloodTestDepartment.newPatient(this.getPatient());
					this.getPatient().setState("is waiting");
					return;
					
				case "MRI departement":
					this.getSim().ed.mriDepartment.newPatient(this.getPatient());
					this.getPatient().setState("is waiting");
					return;
					
				}
			}
			
			return;
		}
		
		return;
	}
}
