package human;

import auxiliary.MessageBox;
import auxiliary.Queue;
import event.*;
import resource.Room;
import throwables.SyntaxErrorException;

/**
 * 
 * This class represents a patient with his relevent caracteristics.
 *
 */


public class Patient extends java.util.Observable implements Human {
	
	// Explicitely specified attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8114871572043656594L;
	public String name = "";
	public String surname = "";
	public int ID = 0;
	public String healthInsurance;
	public String severityLevel;
	public String state = "notwaiting";
	private String Location = "arrival";
	private Queue<Event> history = new Queue<Event>();
	private double arrivalTime;
	
	//Attributes that i added on purpose of an easy implementation
	
	public MessageBox messageBox= new MessageBox(this);
	public Physician personnalphysician = null;
	private Room personnalroom = null;
	private double DTDT;
	public boolean DTDTisavailable;
	private double LOS;
	public boolean LOSisavailable;
	
	//Complete Constructor

	public Patient(String name, String surname, int iD, String healthInsurance, String severityLevel, String state,
			String location, Queue<Event> history, double arrivalTime,Physician personnalphysician,Room personnalroom) {
		this.name = name;
		this.surname = surname;
		this.ID = iD;
		this.healthInsurance = healthInsurance;
		this.severityLevel = severityLevel;
		this.state = state;
		this.Location = location;
		this.history = history;
		this.arrivalTime=arrivalTime;
		this.personnalphysician=personnalphysician;
		this.personnalroom=personnalroom;
		this.DTDT=0;
		this.LOS=0;
		this.DTDTisavailable=false;
		this.LOSisavailable=false;
	}
	
	//Easy Constructor
	
	public Patient(String name,String surname,int ID,String healthInsurance,String severityLevel,double arrivalTime) throws SyntaxErrorException {
		if(name!=null && surname!=null && healthInsurance!=null && severityLevel!=null && arrivalTime>=0) {
			this.name=name;
			this.surname=surname;
			this.ID=ID;
			this.healthInsurance=healthInsurance;
			this.severityLevel = severityLevel;
			this.state = "just arrived";
			this.Location = "in ED";
			Queue<Event> hist = new Queue<Event>();
			Event event = new PatientArrival(ID,this,arrivalTime,severityLevel);
			hist.append(event);
			this.history = hist;
			this.arrivalTime=arrivalTime;
			this.personnalphysician=null;
			this.personnalroom=null;
			this.DTDT=0;
			this.LOS=0;
			this.DTDTisavailable=false;
			this.LOSisavailable=false;
		}
		else {
			throw new SyntaxErrorException();
		}
	}
	
	
	//Methods overriden from from the interface Human
	
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String getSurname() {
		return surname;
	}
	@Override
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public int getID() {
		return ID;
	}
	@Override
	public void setID(int iD) {
		ID = iD;
	}
	public String getHealthIsurance() {
		return healthInsurance;
	}
	public void setHealthIsurance(String healthIsurance) {
		this.healthInsurance = healthIsurance;
	}
	public String getSeverityLevel() {
		return severityLevel;
	}
	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}
	@Override
	public String getState() {
		return state;
	}
	@Override
	public void setState(String state) {
		this.state = state;
	}
	
	//Getters and Setters
	
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public Queue<Event> getHistory() {
		return history;
	}
	public void setHistory(Queue<Event> history) {
		this.history = history;
	}
	public double getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(float arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public MessageBox getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(MessageBox messageBox) {
		this.messageBox = messageBox;
	}

	public Physician getPersonnalphysician() {
		return personnalphysician;
	}

	public void setPersonnalphysician(Physician personnalphysician) {
		this.personnalphysician = personnalphysician;
	}

	public Room getPersonnalroom() {
		return personnalroom;
	}

	public void setPersonnalroom(Room personnalroom) {
		this.personnalroom = personnalroom;
	}
	public double getDTDT() {
		return DTDT;
	}

	public void setDTDT(double dTDT) {
		DTDT = dTDT;
		DTDTisavailable=true;
	}

	public double getLOS() {
		return LOS;
	}

	public void setLengthofStay(double lenghtofStay) {
		LOS = lenghtofStay;
		LOSisavailable=true;
	}

	// Charging of this patient
	
	/**
	 * This method counts the charges of this patient given his current state. This is done by adding the cost of every event that he lived during the simulation.
	 * @return this patient charges.
	 */
	
	public double charges() {
		double S=0;
		for(Event ev:history.container) {
			S+=ev.cost();
		}
		switch(this.healthInsurance) {
		case "Gold":
			return S/2;
		case "Silver":
			return 4*S/5;
		case "":
			return S;
		}
		return 0;
	}
	
	// showing charges of this patient
	
	/**
	 * this method print out in the screen the charges of this patient.
	 */
	
	public void showCharges() {
		System.out.println(this.name+" "+this.surname+" has to pay:"+this.charges()+"$");
	}
	
	//Showing history of this patient
	
	/**
	 * this method print out in the screen this patient's history.
	 */
	
	public void showhistory() {
		for(Event event:this.history.container) {
			System.out.println(event.show());
		}
	}
	
	//adding a new event to this patient's history
	
	/**
	 * this method adds to this patient's history a new event.
	 * @param event the new event to add.
	 */
	
	public void newEvent(Event event) {
		history.append(event);
	}
	
	// Overriding equals
		@Override
		public boolean equals(Object human) {
			if(human instanceof Human) {
				return (this.getName().equals(((Human)human).getName())) && (this.getSurname().equals(((Human)human).getSurname()));
				}
			return false;
		}
	//Overriding hashCode
		@Override
		public int hashCode() {
			return this.getID();
		}
}

