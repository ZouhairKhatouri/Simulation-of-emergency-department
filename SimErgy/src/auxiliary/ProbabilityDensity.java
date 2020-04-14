package auxiliary;

import java.io.Serializable;

/**
 * 
 * This an interface making commun the method sample() for all types of probability densities
 *
 */

public interface ProbabilityDensity extends Serializable {
	
	/*
	 * this method returns a sample of a given probability density, the amperical distribution of many samples should follow the probability distribution
	 */
	
	public double sample();

	public String getType();
}
