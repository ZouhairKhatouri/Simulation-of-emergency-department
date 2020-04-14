package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Nurse;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;
import throwables.NoneExistantEntityError;

/**
 * 
 * This command adds a Nurse to the CLUI simulation system on the given ed (detected by his name) .
 *
 */

public class AddNurse extends Command{
	
	private String edName;
	private String nurseName;
	private String nurseSurname;

	public AddNurse(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException {
		super(clui,args);
		
		// Proceeding the data
		
		if(args.length==1) {
			this.edName=args[0];
			this.nurseName=null;
			this.nurseSurname=null;
		}
		else if(args.length==3) {
			this.edName=args[0];
			this.nurseName=args[1];
			this.nurseSurname=args[2];
		}
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED edepartment = getClui().edMap.get(edName);
			Nurse nurse = null;
			int ID = edepartment.nextID();
			
			if(nurseName==null) {
				nurse = new Nurse(ID);
			}
			else {
				try{
					nurse = new Nurse(nurseName,nurseSurname,ID);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}

			//

			if(edepartment.nurses.contains(nurse)) {
				throw new ExistantEntityError(getArgs());
			}
			else {
				edepartment.nurses.add(nurse);
				System.out.println("This nurse was successfully added, she got the ID number: "+ID);
				return;
			}

		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}	
	}
}