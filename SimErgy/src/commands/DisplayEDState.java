package commands;

import clui.SimErgyCLUI;
import core.ED;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;
import view.EDView;
import view.View;

/**
 * This command displays the state of a given EDwithin the CLUI system.
 *
 */

public class DisplayEDState extends Command{

	private String edName;
	
	public DisplayEDState(SimErgyCLUI clui,String[] args) throws SyntaxErrorException {
		super(clui,args);
		
		// Proceeding data
		
		if(args.length==1) {
			this.edName=args[0];
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED ed = getClui().edMap.get(edName);
			View view = new EDView(ed);
			view.display();
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}

}
