package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Patient;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command adds a Physician to the CLUI simulation system on the given ed (detected by his name) .
 *
 */
public class AddPatient extends Command{
	
	private String edName;
	private String patientName;
	private String patientSurname;
	private String severity;
	private String healthInsurance;

	public AddPatient(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException, SyntaxErrorException {
		
		super(clui,args);
		
		// Proceeding the data
		
		if(args.length==5) {
			this.edName = args[0];
			this.patientName = args[1];
			this.patientSurname = args[2];
			this.severity = args[3];
			this.healthInsurance = args[4];
			if(!(severity.equals("L1")|| severity.equals("L2") || severity.equals("L3")|| severity.equals("L4")|| severity.equals("L5"))) {
				throw new SyntaxErrorException(args);
			}
			else if(!(healthInsurance.equals("Gold") || healthInsurance.equals("Silver") || healthInsurance.equals(""))) {
				throw new SyntaxErrorException(args);
			}
		}
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED edepartment = getClui().edMap.get(edName);
			int ID = edepartment.nextID();
			Patient patient = null;
			try {
				patient = new Patient(patientName,patientSurname,ID,healthInsurance,severity,edepartment.clock);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//
			for(Patient p : edepartment.patients) {
				if(p.equals(patient)) {
					throw new ExistantEntityError(getArgs());
				}
			}
			edepartment.patients.add(patient);
			System.out.println("This patient was successfully added, he got the ID number: "+ID);
			return;

		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}	
	}

}
