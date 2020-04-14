package throwables;

/**
 * 
 * Thrown when user tries to input wrong type attribute as given field of a given command.
 *
 */

public class ArgumentsMismatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;
	
	private String[] args;

	public ArgumentsMismatchException(String[] args) {
		super();
		this.args = args;
	}
	
	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}
}
