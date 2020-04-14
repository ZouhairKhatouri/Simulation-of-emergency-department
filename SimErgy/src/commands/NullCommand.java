package commands;

import clui.SimErgyCLUI;

/**
 * This command does nothing. In fact it is called when the enter is pressed by the user.
 *
 */

public class NullCommand extends Command {

	public NullCommand(SimErgyCLUI clui,String[] args) {
		super(clui,new String[0]);
	}

	@Override
	public void execute() {
	}

}
