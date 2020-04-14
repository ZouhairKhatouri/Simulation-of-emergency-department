package commands;

import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command compute the current DTDT indicator of a given ED (repaired by the name inputed by the user)
 * and print it out on the console.
 *
 */

public class ComputeDTDT extends Command{

	private String edName;

	public ComputeDTDT(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
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
			System.out.println("Current DTDT average: "+sim.currentDTDTAverage());;
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
		
	}

}
