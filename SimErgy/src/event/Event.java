package event;

import java.io.Serializable;
import java.util.ArrayList;

import resource.Resource;

/**
 * 
 * This interface represents the different class of events that may occure during an simulation.
 *
 */

public interface Event extends Serializable {
	
	/**
	 * @return the resources need for the occurence of this event.
	 */
	public ArrayList<Resource> resources();
	/**
	 * @return the cost associated to the occurence of this event.
	 */
	public double cost();
	/**
	 * This method performs the changes needed after the end of this event.
	 */
	public void end();
	/**
	 * @return the name code of this event.
	 */
	public String getName();
	/**
	 * @return the ID number of this event.
	 */
	public int getID();
	/**
	 * @return the timestamp of this event.
	 */
	public double getTimestamp() ;
	/**
	 * @return the duration of this event.
	 */
	public double getDuration();
	/**
	 * This method return the relevent caracteristics of this event.
	 */
	public String show();
	/**
	 * @return a boolean value that told us if the event is still on going or not.
	 */
	public boolean isOngoin();
	/**
	 * This is a setter for the internal attribute "ongoin".
	 * @param b the new value of the interne attribute "ongoin".
	 */
	public void setOngoin(boolean b);

	// Overloading equals
	
	public default boolean equals(Event obj) {
		return ((Event)obj).getID()==this.getID();
	}
}
