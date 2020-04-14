package event;

import java.util.ArrayList;
import human.Patient;
import human.Physician;
import resource.Resource;

/**
 * This class represents the event: a patient has quite the emergency departement.
 *
 *
 */

public class Outcome implements Event{
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3256054811153490359L;
	public static String name = "Outcome";
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public Physician physician;
	private boolean ongoin;
	
	//Constructor

	public Outcome(int iD, Patient patient, double timestamp, Physician physician) {
		super();
		ID = iD;
		this.patient = patient;
		this.timestamp = timestamp;
		this.physician = physician;
		this.ongoin=true;
	}
	
	//Overriden Methods from the interface event

	@Override
	public ArrayList<Resource> resources(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(physician);
		return resources;
	}
	@Override
	public double cost() {
		return 0.0;
	}
	@Override
	public void end() {
		this.setOngoin(false);
		this.physician.setState("idle");
		this.patient.setState("released");
		this.patient.deleteObservers();
		this.physician.patientsBeenOverseen.remove(this.patient);
		this.physician.patientsTreated.add(this.patient);
		this.physician.emitVerdict(this.patient,this.timestamp);
		this.patient.getPersonnalroom().setAvailable(true);
		this.patient.setLocation("outside the ed");
		return;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getID() {
		return ID;
	}

	@Override
	public double getTimestamp() {
		return timestamp;
	}

	@Override
	public double getDuration() {
		return 0;
	}

	@Override
	public String show() {
		return "( "+this.patient.name+" "+this.patient.surname+" ,"+name+" ,"+timestamp+"min )";
	}

	@Override
	public boolean isOngoin() {
		return ongoin;
	}

	@Override
	public void setOngoin(boolean b) {
		ongoin=b;
		return;
		
	}
}
