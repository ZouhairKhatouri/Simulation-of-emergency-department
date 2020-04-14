package throwables;

/**
 * Throw when the execution of a given command may lead to the duplication of the same object within an ED.
 *
 */

public class ExistantEntityError extends Error{

	/**
	 * 
	 */
	private static final long serialVersionUID = 20L;
	private String[] Args;
	
	
	public ExistantEntityError(String[] args) {
		super();
		Args = args;
	}
	
	
	public String[] getArgs() {
		return Args;
	}
	public void setArgs(String[] args) {
		Args = args;
	}

}
