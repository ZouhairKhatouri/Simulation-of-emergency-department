package commands;

import clui.SimErgyCLUI;
import core.ED;
import resource.HealthService;
import throwables.NoneExistantEntityError;
import throwables.SyntaxErrorException;
import view.HealthServiceView;
import view.View;

/**
 * This command displays the state of a given Health-service within the CLUI system.
 *
 */

public class DisplayHealthServiceState extends Command{

	private String edName;
	private String healthServiceType;

	public DisplayHealthServiceState(SimErgyCLUI clui, String[] args) throws SyntaxErrorException {
		super(clui, args);
		
		// Proceeding data
		
		if(args.length==2) {
			edName=args[0];
			healthServiceType = args[1];
			if(!(healthServiceType.equals("Arrival")||healthServiceType.equals("Consultation")||healthServiceType.equals("Blood-Test")||healthServiceType.equals("MRI")||healthServiceType.equals("Radiography"))) {
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
			HealthService service = null;
			View view = null;
			if(healthServiceType.equals("Arrival")) {
				service=ed.arrival;
			}
			else if (healthServiceType.equals("Consultation")) {
				service=ed.consultationDepartment;
			}
			else if (healthServiceType.equals("Blood-Test")) {
				service=ed.bloodTestDepartment;
			}
			else if ((healthServiceType.equals("MRI"))) {
				service=ed.mriDepartment;
			}
			else {
				service=ed.radiographyDepartment;
			}
			view = new HealthServiceView(service);
			view.display();
			return;
		}
		else {
			throw new NoneExistantEntityError(this.getArgs());
		}
	}

}
