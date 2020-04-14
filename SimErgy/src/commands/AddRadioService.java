package commands;

import auxiliary.Det;
import auxiliary.Exp;
import auxiliary.ProbabilityDensity;
import auxiliary.Unif;
import clui.SimErgyCLUI;
import core.ED;
import resource.Radiography;
import throwables.ArgumentsMismatchException;
import throwables.ExistantEntityError;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command adds a Radiography service to the CLUI simulation system on the ed (repaired by the name inputed by the user) 
 * and with a service-time law also inputed by the user.
 *
 */

public class AddRadioService extends Command{
	
	private String edName;
	private ProbabilityDensity servicetime;

	public AddRadioService(SimErgyCLUI clui,String[] args) throws ArgumentsMismatchException, SyntaxErrorException {
		
		super(clui,args);
		
		// Proceeding the data.
		
		if(args.length==3) {
			this.edName=args[0];
			String distType = args[1];
			if(distType.equals("Det")) {
				String Delta = args[2];
				double delta = Double.parseDouble( Delta.replace(",",".") );
				this.servicetime = new Det(delta);
			}
			else if(distType.equals("Exp")) {
				String Lambda = args[2];
				double lambda = Double.parseDouble( Lambda.replace(",",".") );
				this.servicetime = new Exp(lambda);
			}
			else {
				throw new SyntaxErrorException(args);
			}
		}
		
		else if (args.length==4) {
			this.edName=args[0];
			String distType = args[1];
			if(distType.equals("Unif")) {
				String H = args[2];
				String K = args[3];
				double h = Double.parseDouble( H.replace(",",".") );
				double k = Double.parseDouble( K.replace(",",".") );
				this.servicetime = new Unif(h,k);
			}
			else {
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
			if(edepartment.radiographyDepartment==null) {
				int ID = edepartment.nextID();
				Radiography radiographyDepartement = new Radiography(ID,servicetime);
				edepartment.radiographyDepartment = radiographyDepartement;
				System.out.println("This service was successfully service, he got the ID number: "+ID);
				return;
			}
			else {
					throw new ExistantEntityError(this.getArgs());
			}
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
		
	}

}
