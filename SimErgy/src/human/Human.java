package human;

import java.io.Serializable;

/**
 * 
 * This interface represents the different human classes used in the project : patients, nurses, physicians and transporters.
 *
 */

public interface Human extends Serializable {
	
	/**
	 * Getter for this human name.
	 * @return this human name.
	 */
	public String getName();
	
	/**
	 * Setter for this human name.
	 * @param name this human new name.
	 */
	public void setName(String name);
	
	/**
	 * Getter for this human surname.
	 * @return this human surname.
	 */
	public String getSurname();
	
	/**
	 * Setter for this human surname.
	 * @param surname this human new surname.
	 */
	public void setSurname(String surname);
	
	/**
	 * Getter for this human ID.
	 * @return this human ID.
	 */
	public int getID();
	
	/**
	 * Setter for this human ID.
	 * @param ID this human new ID.
	 */
	public void setID(int ID);
	
	/**
	 * Getter for this human state.
	 * @return this human state.
	 */
	public String getState();
	
	/**
	 * Setter for this human state.
	 * @param state this human new state.
	 */
	public void setState(String state);
	
}
