package auxiliary;

/**
 * 
 * This Class reprensents the general class of severity-levels.
 *
 */

public abstract class SeverityLevel {
	
	//Attributes
	
	public String name;
	public ProbabilityDensity arrivalprobability;
	
	/**
	 * 
	 * @param name : the name type of the severity.
	 * @param arrivalprobability : the probability distribution of the time-arrival of a patient on a given severity-level category.
	 */
	
	//Constructor
	
	public SeverityLevel(String name,ProbabilityDensity arrivalprobability) {
		this.name=name;
		this.arrivalprobability = arrivalprobability;
	}
}
