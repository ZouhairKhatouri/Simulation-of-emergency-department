package commands;

import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command shows the history of events of a given ED within the CLUI simulation system.
 *
 */

public class ShowHistory extends Command{

	private String edName;

	public ShowHistory(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==1) {
			edName=args[0];
		}
		else {
			throw new SyntaxErrorException(args);
		}

	}

	@Override
	public void execute() {
		if(getClui().simMap.containsKey(edName)) {
			SimErgy sim = getClui().simMap.get(edName);
			sim.showhistory();
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}

}
