package event;

import java.util.ArrayList;
import human.Nurse;
import human.Patient;
import resource.Resource;
import resource.Room;

/**
 * This class represents the event: a patient is installed in his personnal room by a nurse.
 *
 *
 */

public class TransportationInstallation implements Event{
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4474717439357166279L;
	public static String name ="Transportation/Installation";
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public double duration = 0;
	private boolean ongoin;
	public Nurse nurse;
	public Room room;
	
	//Constructor

	public TransportationInstallation(int ID, Patient patient, double timestamp, double duration, Nurse nurse, Room room) {
		this.ID = ID;
		this.patient = patient;
		this.timestamp = timestamp;
		this.duration = duration;
		this.nurse = nurse;
		this.room=room;
		this.ongoin=true;
	}
	
	//Overriden Methods from the interface event
	
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
		resources.add(room);
		return resources;
	}
	@Override
	public double cost() {
		return 5.0;
	}
	@Override
	public void end() {
		this.patient.setLocation("personnal room");
		this.patient.setPersonnalroom(this.room);
		this.nurse.setAvailable(true);
		this.patient.setState("is being registred in consultation departement");
		this.setOngoin(false);
	}
	@Override
	public boolean isOngoin() {
		return ongoin;
	}
	@Override
	public void setOngoin(boolean ongoin) {
		this.ongoin = ongoin;
		return;
	}

}
