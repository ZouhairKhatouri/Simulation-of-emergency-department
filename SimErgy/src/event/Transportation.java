package event;

import java.util.ArrayList;
import human.Patient;
import human.Transporter;
import resource.Resource;

/**
 * This class represents the event: a patient is transported to examination departement by a transporter.
 *
 *
 */

public class Transportation implements Event { 
	
	//Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8757705687716910549L;
	public static String name = "Transportation";
	public int ID = 0;
	public Patient patient;
	public double timestamp = 0;
	public double duration = 0;
	private boolean ongoin;
	public Transporter transporter;
	public String destination;
	
	//Constructor

	public Transportation(int ID, Patient patient, double timestamp, double duration, Transporter transporter,String destination) {
		this.ID = ID;
		this.patient = patient;
		this.timestamp = timestamp;
		this.duration = duration;
		this.transporter = transporter;
		this.destination = destination;
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
		resources.add(transporter);
		return resources;
	}
	@Override
	public double cost() {
		return 3.0;
	}
	@Override
	public void end() {
		this.patient.setLocation(this.destination);
		this.transporter.setAvailable(true);
		this.transporter.stretcher.setAvailable(true);
		this.setOngoin(false);
		this.patient.setState("waiting in the departement");
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
