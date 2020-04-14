package commands;

import clui.SimErgyCLUI;
import throwables.ArgumentsMismatchException;
import throwables.SyntaxErrorException;

/**
 * This command resume the CLUI session.
 *
 */

public class End extends Command {

	public End(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException, SyntaxErrorException {
		super(clui,args);
		
		// Proceeding data
		
		if(args.length!=1) {
			throw new ArgumentsMismatchException(args);
		}
		else if(args[0].isEmpty()==false) {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		getClui().setOn(false);
		return;
	}

}
