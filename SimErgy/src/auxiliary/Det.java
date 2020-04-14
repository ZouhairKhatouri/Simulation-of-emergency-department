package auxiliary;

/**
 *
 *This is the class representing the deterministic distribution
 */

public class Det implements ProbabilityDensity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8519244511833567563L;
	/**
	 * @param val : this the value given each time we sample this distribution 
	 */
	
	//Attributes
	
	public double val = 0;
	
	//Constructor
	
	public Det(double val) {
		this.val=val;
	}
	
	//Methods
	
	@Override
	
	public double sample() {
		return this.val;
	}

	@Override
	public String getType() {
		return "Det("+val+")";
	}

}
