package patientStrategy;

import core.SimErgy;
import event.Event;
import event.TransportationInstallation;
import human.Nurse;
import human.Patient;
import resource.Resource;
import resource.Room;

/**
 * 
 * This class represents the strategy of patient that has just registred.
 *
 */

public class RegistrationTriageStrategy extends PatientStrategy{
	
	// Constructor

	public RegistrationTriageStrategy(SimErgy sim, Patient patient) {
		super(sim, patient);
	}
	
	// Methods
	
	@Override
	public void perform() {
		Event event = this.getPatient().getHistory().first();
		
		if(this.getPatient().state=="registring") { // In this case, the patient is sill registring, he has nothing to do.
			return;
		}
		
		else {
			
			switch(this.getPatient().severityLevel) { // In this case, the patient has just ended registration event,
														//he has to start the transportation/installation in a room, depending in his severity level.
			
			
			case "L1":
				for(Room room:this.getSim().ed.shockRooms) { // In this case, the patient has to be installed in a shock room.
					if(room.isAvailable()) {
						Resource nurse = event.resources().get(0);
						for(Nurse n: this.getSim().ed.nurses) {
							if(n.equals(nurse)) {
								room.setAvailable(false);
								this.getPatient().setState("being installed");
								double t= 2;
								TransportationInstallation nextevent = new TransportationInstallation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t,n,room);
								this.getPatient().newEvent(nextevent);
								this.getSim().history.append(nextevent);
								return;
							}
						}
					}
				}
				return;
				
				
			case "L2":
				for(Room room:this.getSim().ed.boxRooms) { // In this case, the patient has to be installed in a box room.
					if(room.isAvailable()) {
						Resource nurse = event.resources().get(0);
						for(Nurse n: this.getSim().ed.nurses) {
							if(n.equals(nurse)) {
								room.setAvailable(false);
								this.getPatient().setState("being installed");
								double t= 2;
								TransportationInstallation nextevent = new TransportationInstallation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t,n,room);
								this.getPatient().newEvent(nextevent);
								this.getSim().history.append(nextevent);
								return;
							}
						}
					}
				}
				return;
				
				
			case "L3":
				for(Room room:this.getSim().ed.waitingRooms) { // In this case, the patient has to be installed in a waiting room.
					if(room.isAvailable()) {
						Resource nurse = event.resources().get(0);
						for(Nurse n: this.getSim().ed.nurses) {
							if(n.equals(nurse)) {
								room.setAvailable(false);
								this.getPatient().setState("being installed");
								double t= 2;
								TransportationInstallation nextevent = new TransportationInstallation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t,n,room);
								this.getPatient().newEvent(nextevent);
								this.getSim().history.append(nextevent);
								return;
							}
						}
					}
				}
				return;
				
				
			case "L4":
				for(Room room:this.getSim().ed.waitingRooms) { // In this case, the patient has to be installed in a waiting room.
					if(room.isAvailable()) {
						Resource nurse = event.resources().get(0);
						for(Nurse n: this.getSim().ed.nurses) {
							if(n.equals(nurse)) {
								room.setAvailable(false);
								this.getPatient().setState("being installed");
								double t= 2;
								TransportationInstallation nextevent = new TransportationInstallation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t,n,room);
								this.getPatient().newEvent(nextevent);
								this.getSim().history.append(nextevent);
								return;
							}
						}
					}
				}
				return;
				
				
			case "L5":
				for(Room room:this.getSim().ed.waitingRooms) { // In this case, the patient has to be installed in a waiting room.
					if(room.isAvailable()) {
						Resource nurse = event.resources().get(0);
						for(Nurse n: this.getSim().ed.nurses) {
							if(n.equals(nurse)) {
								room.setAvailable(false);
								this.getPatient().setState("being installed");
								double t= 2;
								TransportationInstallation nextevent = new TransportationInstallation(this.getSim().ed.nextID(),this.getPatient(),this.getSim().ed.clock,t,n,room);
								this.getPatient().newEvent(nextevent);
								this.getSim().history.append(nextevent);
								return;
							}
						}
					}
				}
				return;
				
				
			}
		}
		
	}
}
