package commands;

import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.InfiniteLoopException;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command let the next event occure in a given ED. 
 * It can throw an infinite loop exception if no event will occure.
 *
 */

public class ExecuteEvent extends Command {

	private String edName;

	public ExecuteEvent(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		
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
			try {
				int N0 = sim.history.container.size();
				sim.nextEvents();
				int N = sim.history.container.size();
				for(int i=1;i<=N-N0;i++) {
					System.out.println(sim.history.container.get(N0+i-1).show());
				}
			} catch (InfiniteLoopException e) {
				System.out.println("No more event can occure within this department");
			}
			return;
			
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
		
	}

}
