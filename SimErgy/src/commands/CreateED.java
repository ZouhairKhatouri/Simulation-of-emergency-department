package commands;

import clui.SimErgyCLUI;
import core.ED;
import core.SimErgy;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;

/**
 * 
 * This command creates and adds a new ED  with the given name by the user to the CLUI simulation system.
 *
 */

public class CreateED extends Command{

	private ED ed;
	private String edName;

	public CreateED(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException {
		super(clui,args);
		
		// Proceeding data
		
		if(args.length==1) {
			this.edName=args[0];
			this.ed = new ED(args[0]);
		}
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			
			throw new ExistantEntityError(getArgs());
			
		}
	
		else {
			
			getClui().edMap.put(edName, ed);
			
			SimErgy sim = new SimErgy(ed);
		
			getClui().simMap.put(edName, sim);
			
			System.out.println("This emergency department was successfully created");
		
			return;
		}
	}
}
