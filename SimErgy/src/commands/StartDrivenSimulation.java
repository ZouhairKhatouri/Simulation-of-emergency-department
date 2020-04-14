package commands;

import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.ArgumentsMismatchException;
import throwables.NoneExistantEntityError;

/**
 * 
 * This command launchs a driven simulation until a time horizon chosen by the user, on a given ED within the CLUI simulation system.
 *
 * I mean by driven that arrivals are generated. 
 * 
 */

public class StartDrivenSimulation extends Command{

	private String edName;
	private double T;
	
	public StartDrivenSimulation(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException {
		super(clui,args);
		
		// Proceeding data
		
		if(args.length==2) {
			this.edName = args[0];
			String t = args[1];
			this.T = Double.parseDouble( t.replace(",",".") );
		}
		
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().simMap.containsKey(edName)) {
			SimErgy sim = getClui().simMap.get(edName);
			sim.drivenSimulation(this.T);
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}

}
