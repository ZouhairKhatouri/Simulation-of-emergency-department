package event;

import java.util.ArrayList;
import human.Patient;
import resource.BloodTestLab;
import resource.RadiographyRoom;
import resource.Resource;
import resource.Room;

/**
 * This class represents the event: a patient is examinated.
 *
 *
 */

public class Examination implements Event{
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -523100882120622377L;
	public static final String name = "Examination";
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public double duration = 0;
	private boolean ongoin;
	public Room room;
	
	//Constructor

	public Examination(int ID, Patient patient, double timestamp, double duration, Room room) {
		this.ID = ID;
		this.patient = patient;
		this.timestamp = timestamp;
		this.duration = duration;
		this.room = room;
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
		String examtype = "";
		if(this.room instanceof BloodTestLab) {
			examtype="BloodTest";
		}
		else if(this.room instanceof RadiographyRoom) {
			examtype="X-Ray";
		}
		else {
			examtype="MRI";
		}
		return "( "+this.patient.name+" "+this.patient.surname+" ,"+name+"/"+examtype+" ,"+timestamp+"min )" ;
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
		resources.add(room);
		return resources;
	}
	@Override
	public double cost() {
		return 70.0;
	}
	@Override
	public void end() {
		this.room.setAvailable(true);
		this.setOngoin(false);
		this.patient.setState("is waiting");
		return;
		}
}
