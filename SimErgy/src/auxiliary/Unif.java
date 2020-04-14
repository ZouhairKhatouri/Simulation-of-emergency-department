package auxiliary;

import java.util.*;

/**
 * This is the class representing the uniform distribution of the segment [h,l]
 *
 *
 */

public class Unif implements ProbabilityDensity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7413001127484028020L;
	/**
	 * @param h : this is the lower bound of the segment where this distribution is uniform
	 * @param l : this is the upper bound of the segment where this distribution is uniform
	 */
	
	// Attributes
	
	public double h;
	public double l;
	
	//Constructor
	
	public Unif(double h,double l) {
		this.h=h;
		this.l=l;
	}

	//Methods
	
	@Override
	
	public double sample() {
		
		Random rand = new Random();
		double x = rand.nextDouble();
		return (l-h)*x+h;	
	}

	@Override
	public String getType() {
		return "Unif("+h+","+l+")";
	}
	
}
