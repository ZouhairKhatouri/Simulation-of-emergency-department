package view;

import core.ED;
import human.Nurse;
import human.Patient;
import human.Physician;
import human.Transporter;

/**
 * 
 * This Class is the view of the class ED.
 *
 */

public class EDView implements View {

	private ED ed;
	
	public EDView(ED ed) {
		super();
		this.ed = ed;
	}


	@Override
	public void display() {
		System.out.println("------------------------------------------------------");
		System.out.println("Emergency Department of "+ed.name+" has the current state: ");
		View view;
		System.out.println("\nPatients: \n");
		for(Patient patient:ed.patients) {
			view = new HumanView(patient);
			view.display();
		}
		System.out.println("\nPhysicians: \n");
		for(Physician physician:ed.physicians) {
			view = new PhysicianView(physician);
			view.display();
		}
		System.out.println("\nNurses: \n");
		for(Nurse nurse:ed.nurses) {
			view = new HumanView(nurse);
			view.display();
		}
		System.out.println("\nTransporters: \n");
		for(Transporter transporter:ed.transporters) {
			view = new HumanView(transporter);
			view.display();
		}
		System.out.println("\nArrival Department: \n");
		view = new HealthServiceView(ed.arrival);
		view.display();
		System.out.println("\nConsultation Department: \n");
		view = new HealthServiceView(ed.consultationDepartment);
		view.display();
		System.out.println("\nBlood-Test Department: \n");
		view = new HealthServiceView(ed.bloodTestDepartment);
		view.display();
		System.out.println("\nRadiography Department: \n");
		view = new HealthServiceView(ed.radiographyDepartment);
		view.display();
		System.out.println("\nMRI Department: \n");
		view = new HealthServiceView(ed.mriDepartment);
		view.display();
		System.out.println("------------------------------------------------------");
	}

}
