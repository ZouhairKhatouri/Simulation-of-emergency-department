package event;

import java.util.ArrayList;
import human.Patient;
import human.Physician;
import resource.Resource;

/**
 * This class represents the event: a patient is visited by a physician.
 *
 *
 */

public class ConsultationEvent implements Event{
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4128272765535147241L;
	public static String name ="Consultation";
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public double duration = 0;
	private boolean ongoin = true;
	public Physician physician;

	//Constructor
	
	public ConsultationEvent(int ID, Patient patient, double timestamp, double duration, Physician physician) {
		this.ID = ID;
		this.patient = patient;
		this.timestamp = timestamp;
		this.duration = duration;
		this.ongoin=true;
		this.physician = physician;
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
		return 23.0;
	}
	@Override
	public void end() {
		this.physician.setState("idle");
		this.physician.prescribeExam(this.patient,this.timestamp);
		this.patient.setState("is waiting");
		this.setOngoin(false);
		return;
	}
	@Override
	public boolean isOngoin() {
		return ongoin;
	}
	@Override
	public void setOngoin(boolean ongoin) {
		this.ongoin = ongoin;
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
	public String getName() {
		return name;
	}
	@Override
	public String show() {
		return "( "+this.patient.name+" "+this.patient.surname+" ,"+name+" ,"+timestamp+"min )";
	}
}
