package resource;

import human.Human;

/**
 * 
 * This class modelises the stretchers in this project.
 *  
 */

public class Stretcher implements NHR{
	
	// Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8972470179083097320L;
	public int ID;
	public boolean available = true;
	public Human user = null;
	
	// Constructor
	
	public Stretcher(int iD) {
		this.ID = iD;
	}
	
	// Getters and Setters

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Human getUser() {
		return user;
	}

	public void setUser(Human user) {
		this.user = user;
	}
}
