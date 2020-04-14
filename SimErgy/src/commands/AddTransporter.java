package commands;

import clui.SimErgyCLUI;
import core.ED;
import human.Transporter;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;
import throwables.NoneExistantEntityError;

/**
 * 
 * This command adds a Transporter to the CLUI simulation system on the given ed (detected by his name) .
 *
 */

public class AddTransporter extends Command{

	private String edName;
	private String transporterName;
	private String transporterSurname;
	
	public AddTransporter(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException {
		super(clui,args);
		
		// Proceeding the data
		
		if(args.length==1) {
			this.edName=args[0];
			this.transporterName=null;
			this.transporterSurname=null;
		}
		else if(args.length==3) {
			this.edName=args[0];
			this.transporterName=args[1];
			this.transporterSurname=args[2];
		}
		else {
			throw new ArgumentsMismatchException(args);
		}
	}

	@Override
	public void execute() {
		if(getClui().edMap.containsKey(edName)) {
			ED edepartment = getClui().edMap.get(edName);
			Transporter transporter = null;
			int ID = edepartment.nextID();
			
			if(transporterName==null) {
				transporter = new Transporter(ID);
			}
			else {
				try {
					transporter = new Transporter(transporterName,transporterSurname,ID);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			//
			
			if(edepartment.transporters.contains(transporter)) {
				throw new ExistantEntityError(getArgs());
			}
			else {
				edepartment.transporters.add(transporter);
				System.out.println("This transporter was successfully added, he got the ID number: "+ID);
				return;
			}

		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}	
	}
}
