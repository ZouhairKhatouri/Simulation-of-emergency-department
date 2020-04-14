package commands;

import clui.SimErgyCLUI;
import core.ED;
import resource.Stretcher;
import throwables.ArgumentsMismatchException;
import throwables.NoneExistantEntityError;

public class AddStretcher extends Command {
	
	private String edName;

	public AddStretcher(SimErgyCLUI clui, String[] args) throws ArgumentsMismatchException {
		super(clui, args);
		if(args.length==1) {
			this.edName=args[0];
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
			
			Stretcher stretcher = new Stretcher(ID);
			
			//
			
			edepartment.stretchers.add(stretcher);
			
			System.out.println("This stretcher was successfully added, he got the ID number: "+ID);
			
			return;

		}
		else {
			
			throw new NoneExistantEntityError(this.getArgs());
			
		}	
	}

}
