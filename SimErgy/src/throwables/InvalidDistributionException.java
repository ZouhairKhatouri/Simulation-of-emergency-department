package throwables;

/**
 * 
 * Thrown when the user inputs a wrong form of a distribution arguments.
 *
 */

public class InvalidDistributionException extends Throwable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1000L;
	private String distType;
	private double[] args;
	
	
	public InvalidDistributionException(String distType, double[] args) {
		super();
		this.distType = distType;
		this.args = args;
	}
	
	
	public String getDistType() {
		return distType;
	}

	public double[] getArgs() {
		return args;
	}
}

