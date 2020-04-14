package view;

/**
 * 
 * This interface is the view part of MVC pattern for CLUI part of this project. 
 * Every implementing class of this interface represents the view of the corresponding class in the CORE part of this project.
 *
 */
public interface View {
	
	/**
	 * This method displays the view of the corresponding class instance.
	 */

	public abstract void display();
	
}
