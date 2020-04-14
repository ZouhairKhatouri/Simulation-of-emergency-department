package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Physician;
import throwables.ExistantEntityError;
import throwables.SyntaxErrorException;
import view.PhysicianView;
import view.View;

/**
 * This command displays the state of a given Physician within the CLUI system.
 *
 */

public class DisplayPhysicianState extends Command{

	private String edName;
	private int physicianID;

	public DisplayPhysicianState(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==2) {
			edName=args[0];
			physicianID = Integer.parseInt( args[1].replace(",",".") );
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED ed = getClui().edMap.get(edName);
			for(Physician physician : ed.physicians) {
				if(physicianID==(physician.getID())) {
					View view = new PhysicianView(physician);
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
