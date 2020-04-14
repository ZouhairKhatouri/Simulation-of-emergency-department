package auxiliary;

import java.util.*;

/**
 * This is the class representing the exponentiel distribution
 *
 *
 */

public class Exp implements ProbabilityDensity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4647973802518806659L;
	/**
	 *@param lambda : this is inverse of the average of the exponentiel distribution
	 */
	
	// Attributes
	
	public double lambda;
	
	// Constructor
	
	public Exp(double lambda) {
		this.lambda=lambda;
	}
	
	// Methods
	
	@Override
	
	public double sample() {
		Random Rand =new Random();
		double x = Rand.nextDouble();
		return (-1/this.lambda)*Math.log(1-x);
	}

	@Override
	public String getType() {
		return "Exp("+lambda+")";
	}
	

}
