package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Nurse;
import throwables.ExistantEntityError;
import throwables.SyntaxErrorException;
import view.HumanView;
import view.View;

/**
 * This command displays the state of a given Nurse within the CLUI system.
 *
 */

public class DisplayNurseState extends Command {

	private String edName;
	private int nurseID;

	public DisplayNurseState(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==2) {
			edName=args[0];
			nurseID = Integer.parseInt( args[1].replace(",",".") );
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED ed = getClui().edMap.get(edName);
			for(Nurse nurse : ed.nurses) {
				if(nurseID==(nurse.getID())) {
					View view = new HumanView(nurse);
					view.display();
					return;
				}
			}
			throw new ExistantEntityError(this.getArgs());
		}
		else {
			throw new ExistantEntityError(this.getArgs());
		}
	}

}
