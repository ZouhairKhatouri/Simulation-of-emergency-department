package commands;

import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.ExistantEntityError;
import throwables.SyntaxErrorException;

public class SetCommandType extends Command{

	private String edName;
	private String commandType;

	public SetCommandType(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		if(args.length==2) {
			edName=args[0];
			commandType=args[1];
			if(!(commandType.equals("Manual")||commandType.equals("Driven"))) {
				throw new SyntaxErrorException(args);
			}
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		
		if(getClui().simMap.containsKey(edName)) {
			SimErgy sim = getClui().simMap.get(edName);
			if(commandType.equals("Manual")) {
				sim.isManual=true;
				return;
			}
			else {
				sim.isManual=false;
				return;
			}
		}
			
		else {
			throw new ExistantEntityError(this.getArgs());
		}
		
	}

}
