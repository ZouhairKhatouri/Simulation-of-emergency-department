package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Transporter;
import throwables.ExistantEntityError;
import throwables.SyntaxErrorException;
import view.HumanView;
import view.View;

/**
 * This command displays the state of a given Transporter within the CLUI system.
 *
 */

public class DisplayTransporterState extends Command {

	private String edName;
	private int transporterID;

	public DisplayTransporterState(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==2) {
			edName=args[0];
			transporterID = Integer.parseInt( args[1].replace(",",".") );
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED ed = getClui().edMap.get(edName);
			for(Transporter transporter : ed.transporters) {
				if(transporterID==(transporter.getID())) {
					View view = new HumanView(transporter);
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
