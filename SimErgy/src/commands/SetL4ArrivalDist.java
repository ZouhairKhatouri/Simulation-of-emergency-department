package commands;

import auxiliary.Det;
import auxiliary.Exp;
import auxiliary.ProbabilityDensity;
import auxiliary.Unif;
import clui.SimErgyCLUI;
import core.SimErgy;
import throwables.ArgumentsMismatchException;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;

/**
 * 
 * This command sets the arrival time probability of patients with severity L4 to the ED  the given name inputed by the user, within the CLUI system; 
 *
 */

public class SetL4ArrivalDist extends Command{

	private String edName;
	private ProbabilityDensity probL4;

	public SetL4ArrivalDist(SimErgyCLUI clui,String[] args) throws SyntaxErrorException, ArgumentsMismatchException {
		super(clui,args);
		
		// Proceeding data

		if(args.length==3) {
			this.edName=args[0];
			String distType = args[1];
			if(distType.equals("Det")) {
				String Delta = args[2];
				double delta = Double.parseDouble( Delta.replace(",",".") );
				this.probL4 = new Det(delta);
			}
			else if(distType.equals("Exp")) {
				String Lambda = args[2];
				double lambda = Double.parseDouble( Lambda.replace(",",".") );
				this.probL4 = new Exp(lambda);
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
				this.probL4 = new Unif(h,k);
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
		if(getClui().simMap.containsKey(edName)) {
			SimErgy sim = getClui().simMap.get(edName);
			sim.setProbL4(probL4);
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}
}
