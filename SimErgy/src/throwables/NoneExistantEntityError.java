package throwables;

/**
 * 
 * Thrown when the user uses a none existing object as an argument in the CLUI.
 *
 */

public class NoneExistantEntityError extends Error{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 22L;
	private String[] Args;
	
	
	public NoneExistantEntityError(String[] args) {
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
