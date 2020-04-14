package event;

import java.util.ArrayList;
import human.Patient;
import resource.Resource;

/**
 * This class represents the event: a patient has just arrived.
 *
 *
 */

public class PatientArrival implements Event{
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6001963999618779971L;
	public static String name = "Patient Arrival" ;
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public double duration = 0;
	public String severity;
	private static boolean ongoin=false;
	
	//Constructor1
	
	public PatientArrival(int ID, Patient patient, double timestamp, String severity) {
		this.ID = ID;
		this.patient = patient;
		this.timestamp = timestamp;
		this.duration = 0;
		this.severity = severity;
	}
	
	//Constructor2
	
	public PatientArrival(int ID,double timestamp,String severity) {
		this.ID=ID;
		this.timestamp=timestamp;
		this.severity=severity;
		this.patient=null;
		this.duration=0;
	}
	
	//Overriden Methods from the interface event
	
	@Override
	public boolean isOngoin() {
		return ongoin;
	}
	@Override
	public void setOngoin(boolean ongoin) {
		PatientArrival.ongoin = ongoin;
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
		return new ArrayList<Resource>();
	}
	@Override
	public double cost() {
		return 0.0;
	}
	@Override
	public void end() {
		return;
	}
}
