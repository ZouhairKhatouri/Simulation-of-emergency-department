package human;

import java.util.ArrayList;
import java.util.Observable;
import auxiliary.Message;
import auxiliary.MessageBox;
import auxiliary.Unif;
import resource.*;
import throwables.SyntaxErrorException;

/**
 * 
 * This class represents the physicians in this project.
 *
 */

public class Physician implements Human,HR,java.util.Observer {
	
	// Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2775610186505853266L;
	public String name = "";
	public String surname = "";
	public int ID = 0;
	public String state = "idle";
	public String username = "";
	public ArrayList<Patient> patientsBeenOverseen;
	public ArrayList<Patient> patientsTreated;
	public MessageBox messageBox= new MessageBox(this);

	// Constructor1
	
	public Physician(String name, String surname, int iD, String state, String username, boolean available,
			ArrayList<Patient> patientsBeenOverseen,ArrayList<Patient> patientsTreated) {
		super();
		this.name = name;
		this.surname = surname;
		this.ID = iD;
		this.state = state;
		this.username = username;
		this.patientsBeenOverseen = patientsBeenOverseen;
		this.patientsTreated= patientsTreated;
	}
	
	// Constructor2
	
	public Physician(String name, String surname, int iD) throws SyntaxErrorException {
		if(name!=null && surname!=null) {
			this.name = name;
			this.surname = surname;
			this.ID = iD;
			this.state = "idle";
			this.username = "";
			this.patientsBeenOverseen = new ArrayList<Patient>();
			this. patientsTreated = new ArrayList<Patient>();
		}
		else {
			throw new SyntaxErrorException();
		}
	}
	
	// Constructor3
	
	public Physician(int ID) {
		this.name = "Physician"+ID;
		this.surname = "Physician"+ID;
		this.ID = ID;
		this.state = "idle";
		this.username = "";
		this.patientsBeenOverseen = new ArrayList<Patient>();
		this. patientsTreated = new ArrayList<Patient>();
	}
	
	// Getters and Setters
	
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
	@Override
	public String getState() {
		return state;
	}
	@Override
	public void setState(String state) {
		this.state = state;
	}
	
	
	// Method that can be deduced from physician's specification
	
	
	/**
	 * This method will prescribe an exam for a given patient at a given timestamp by making this physicians sending a message to the considered patient.
	 * The content will be decided randomly following this probability law:
	 * 			- in 35% of cases, the message content will be : "you have no test to take" ;
	 * 			- in 20% of cases, the message content will be : "you have to take a radiography exam" ;
	 * 			- in 40% of cases, the message content will be : "you have to take a blood-test exam" ;
	 * 			- in  5% of cases, the message content will be : "you have to take a blood-test exam" ;
	 * @param patient the patient for whom the exam will be prescribed.
	 * @param timestamp the timestamp of this operation.
	 */
	
	public void prescribeExam(Patient patient,double timestamp) {
		Unif U = new Unif(0,100);
		double x = U.sample();
		if(0<=(double)x && (double)x<35) {
			Message newMessage = new Message("you have no test to take",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		if(35<=(double)x && (double)x<55) {
			Message newMessage = new Message("you have to take a radiography exam",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		if(55<=(double)x && (double)x<95) {
			Message newMessage = new Message("you have to take a blood-test exam",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		if(95<=(double)x && (double)x<=100) {
			Message newMessage = new Message("you have to take a blood-test exam",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		return;
	}
	
	
	/**
	 * This method makes this physician start handling a given patient by:
	 * 		- Making him his personnal physician.
	 * 		- Adding the patient to the physician list of patients being overseen.
	 * 		- Setting the state of the patient as "being visited".
	 * 		- Setting the state of this physician as "visiting".
	 * @param patient is the patient that this physician will start handling.
	 */
	
	public void startHandling(Patient patient) {
		 if(patientsBeenOverseen.contains(patient)) {
			 return;
		 }
		 else {
			 this.setState("visiting");
			 this.patientsBeenOverseen.add(patient);
			 patient.setPersonnalphysician(this);
			 patient.setState("being visited");
		 }
	}
	
	
	/**
	 * This method will emit a verdict for an outcoming patient at a given timestamp by making this physicians sending a message to the considered patient.
	 * The content will be decided randomly following this probability law:
	 * 		- in 50% of cases, the message content will be : "you will be hospitalised in this hospital".
	 * 		- in 50% of cases, the message content will be : "you have to be transfered to another hospital".
	 * @param patient : the patient for whom the verdict will be emitted.
	 * @param timestamp : the timestamp of this operation.
	 */
	
	
	public void emitVerdict(Patient patient,double timestamp) {
		Unif U = new Unif(0,100);
		double x = U.sample();
		Message message;
		if(x<50) {
			message = new Message("you will be hospitalised in this hospital",this,patient,timestamp);
		}
		else {
			message = new Message("you have to be transfered to another hospital",this,patient,timestamp);
		}
		patient.messageBox.addNewMessage(message);
		return;
	}
	
	/**
	 * 
	 * This method makes this physicians, as specified in the project, able to handle a new message either by storing it in his message-box, removing it or making it unread.
	 * @param message : it the message to handle.
	 * 
	 */
	
	public void handleMessage(Message message) {
		if(this.state=="idle") {
			message.store(this.messageBox);
		}
		else if(this.state=="offduty") {
			message.remove();
		}
		else {
			message.markUnread(this.messageBox);
		}
	}
	
	/**
	 * This method is invoked when the physicien receives a new message.
	 * @param message : is the new message received.
	 */
	
	public void newMessage(Message message) {
		this.messageBox.addNewMessage(message);
	}
	
	//Overriding the java.util.Observable.update
	
	/**
	 * This is method is the observer part of the observer pattern. 
	 */
	
	@Override
	public void update(Observable obs, Object obj) {
		this.setState("visiting "+((Patient)obs).getName()+" "+((Patient)obs).getSurname());
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
