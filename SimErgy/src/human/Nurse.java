package human;

import resource.*;
import throwables.SyntaxErrorException;

/**
 * 
 * This class represents the nurses in this project.
 *
 */

public class Nurse implements Human,HR {
	
	// Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2141002479355532295L;
	public String name = "";
	public String surname = "";
	public int ID = 0;
	public String state = "offduty";
	private boolean available = false;
	
	// Constructor1
	
	public Nurse(String name, String surname, int iD, String state, boolean available) {
		super();
		this.name = name;
		this.surname = surname;
		ID = iD;
		this.state = state;
		this.available = available;
	}
	
	// Constructor2
	
	public Nurse(String name, String surname, int iD) throws SyntaxErrorException {
		if(name!=null && surname!=null) {
			this.name = name;
			this.surname = surname;
			ID = iD;
			this.state = "onduty";
			this.available = true;
		}
		else {
			throw new SyntaxErrorException();
		}
	}
	
	//Constructor3
	
	public Nurse(int ID) {
		this.name = "Nurse"+ID;
		this.surname = "Nurse"+ID;
		this.ID=ID;
		this.state = "onduty";
		this.available = true;
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

	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
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
