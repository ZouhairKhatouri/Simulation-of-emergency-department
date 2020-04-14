package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Patient;
import throwables.ExistantEntityError;
import throwables.SyntaxErrorException;
import view.PatientView;
import view.View;

/**
 * This command displays the state of a given Patient within the CLUI system.
 *
 */

public class DisplayPatientState extends Command{
	
	private String edName;
	private int patientID;

	public DisplayPatientState(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==2) {
			edName=args[0];
			patientID = Integer.parseInt( args[1].replace(",",".") );
		}
		else {
			throw new SyntaxErrorException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED ed = getClui().edMap.get(edName);
			for(Patient patient : ed.patients) {
				if(patientID==(patient.getID())) {
					View view = new PatientView(patient);
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
