package event;

import java.util.ArrayList;
import human.Nurse;
import human.Patient;
import resource.Resource;

/**
 * This class represents the event: a patient is registred and sort by a nurse.
 *
 *
 */

public class RegistrationTriage implements Event{
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3327519738557435121L;
	public static String name ="Registration/Triage";
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public double duration = 0;
	private boolean ongoin;
	public Nurse nurse;
	
	//Constructor
	
	public RegistrationTriage(int ID, Patient patient, double timestamp, double duration, Nurse nurse) {
		this.ID = ID;
		this.patient = patient;
		this.timestamp = timestamp;
		this.duration = duration;
		this.nurse = nurse;
		this.ongoin=true;
	}
	
	//Overriden Methods from the interface event
	
	@Override
	public boolean isOngoin() {
		return ongoin;
	}
	@Override
	public void setOngoin(boolean ongoin) {
		this.ongoin = ongoin;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String show() {
		return "( "+this.patient.name+" "+this.patient.surname+" ,"+name+" ,"+timestamp+"min )";
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
		return duration;
	}
	@Override
	public ArrayList<Resource> resources(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		resources.add(nurse);
		return resources;
	}
	@Override
	public double cost() {
		return 3.0;
	}
	@Override
	public void end() {
		this.patient.setState("registred");
		this.setOngoin(false);
		return;
	}
}
