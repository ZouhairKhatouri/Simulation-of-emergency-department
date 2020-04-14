package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Patient;
import throwables.ExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command sets a the patient healthinsurance in a given ED within the CLUI simulation system.
 *
 */

public class SetPatientInsurance extends Command {
	
	private String edName;
	private int patientID;
	private String healthInsurance;
	
	public SetPatientInsurance(SimErgyCLUI clui,String[] args) throws SyntaxErrorException {
		super(clui,args);
		
		// Proceeding data
		
		if(args.length==3) {
			edName=args[0];
			patientID = Integer.parseInt( args[1].replace(",",".") );
			healthInsurance = args[2];
			if(!(healthInsurance.equals("Gold")||healthInsurance.equals("Silver")||healthInsurance.equals(""))) {
				throw new SyntaxErrorException(args);
			}
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
					patient.setHealthIsurance(healthInsurance);
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
