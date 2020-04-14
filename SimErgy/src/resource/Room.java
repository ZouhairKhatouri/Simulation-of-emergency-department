package resource;

import gui.Observable;
import human.Human;

/**
 * 
 * This class modelises the rooms used in this project that are: waiting rooms, shock rooms, box rooms, radiography rooms, mri rooms and blood-test laboratories.
 *
 */

public abstract class Room  extends Observable implements NHR{
	
	// Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7557381630841946162L;
	public String name = "";
	private int ID = 0;
	private boolean available = true;
	public Human user = null ;

	//Constructor1
	
	public Room(int ID) {
		this.ID=ID;
	}
	//Constructor2
	
	public Room() {};
	
	//Constructor3
	
	public Room(String name) {
		this.name = name;
	}
	
	// Getters and Setter
	
	@Override
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
		notifyObservers(this.available);
	}
	public Human getUser() {
		return user;
	}
	public void setUser(Human user) {
		this.user = user;
	}
}
