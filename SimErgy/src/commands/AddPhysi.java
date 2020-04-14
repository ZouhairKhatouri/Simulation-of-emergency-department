package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Physician;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;
import throwables.NoneExistantEntityError;

/**
 * 
 * This command adds a Physician to the CLUI simulation system on the given ed (detected by his name) .
 *
 */

public class AddPhysi extends Command{

	private String edName;
	private String physiName;
	private String physiSurname;
	
	public AddPhysi(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException {
		super(clui,args);
		
		// Proceeding the data
		
		if(args.length==1) {
			this.edName=args[0];
			this.physiName=null;
			this.physiSurname=null;
		}
		else if(args.length==3) {
			this.edName=args[0];
			this.physiName=args[1];
			this.physiSurname=args[2];
		}
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED edepartment = getClui().edMap.get(edName);
			
			Physician physician = null;
			int ID = edepartment.nextID();
			
			if(physiName==null) {
				physician = new Physician(ID);
			}
			else {
				try {
					physician = new Physician(physiName,physiSurname,ID);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//
			
			if(edepartment.physicians.contains(physician)) {
				throw new ExistantEntityError(getArgs());
			}
			else {
				edepartment.physicians.add(physician);
				System.out.println("This physician was successfully added, he got the ID number: "+ID);
				return;
			}

		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}	

}
