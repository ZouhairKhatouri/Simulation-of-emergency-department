package commands;

import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.ArgumentsMismatchException;
import throwables.NoneExistantEntityError;

/**
 * 
 * This command launchs a manual simulation until a time horizon chosen by the user, on a given ED within the CLUI simulation system.
 *
 * I mean by manual that no arrival is generated: the user has to choose and to add patients to the system. 
 * 
 */


public class StartManualSimulation extends Command{
	
	private String edName;
	private double T;

	public StartManualSimulation(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException {
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
			sim.manualSimulation(this.T);
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}

}
