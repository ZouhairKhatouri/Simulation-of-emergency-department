package throwables;

/**
 * 
 * Thrown when the syntax of a given command is wrong.
 *
 */

public class SyntaxErrorException extends Exception{

	private static final long serialVersionUID = 13L;
	private String[] commandLine;

	public SyntaxErrorException(String[] commandline) {
		super();
		this.commandLine = commandline;
	}
	
	public SyntaxErrorException() {
		
	}

	public String[] getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String[] commandLine) {
		this.commandLine = commandLine;
	}
	
	
	
}
