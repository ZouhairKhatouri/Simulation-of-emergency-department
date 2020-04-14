package throwables;

/**
 * 
 * Thrown when the user inputs a none existing command.
 *
 */

public class CommandNotFoundException extends Exception {

	private static final long serialVersionUID = 11L;
	private String commandLine;

	public CommandNotFoundException(String commandLine) {
		super();
		this.commandLine = commandLine;
	}

	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}
}
