package view;

import human.Patient;

/**
 * 
 * This Class is the view of the class Patient.
 *
 */

public class PatientView implements View{
	
	private Patient patient;

	public PatientView(Patient patient) {
		this.patient=patient;
	}	
	
	@Override
	public void display() {
		System.out.println("------------------------------------------------------");
		System.out.println("Name: " + patient.name);
		System.out.println("Surname: " +patient.surname);
		System.out.println("ID number: " +patient.ID);
		System.out.println("Health-Insurance: " +patient.healthInsurance);
		System.out.println("State: "+patient.state);
		System.out.println("Current location: "+patient.getLocation());
		System.out.println("Arrival-time: "+patient.getArrivalTime());
		System.out.println("Current charges: "+patient.charges());
		if(patient.DTDTisavailable) {
			System.out.println("DTDT: "+patient.getDTDT());
		}
		else {
			System.out.println("DTDT isn't available");
		}
		if(patient.LOSisavailable) {
			System.out.println("LOS: "+patient.getLOS());
		}
		else {
			System.out.println("LOS isn't available");
		}
		System.out.println("\nHistory since arrived: \n");
		patient.showhistory();
		System.out.println("\nMessage-Box content: \n");
		patient.messageBox.show();
		System.out.println("------------------------------------------------------");
	}
}
