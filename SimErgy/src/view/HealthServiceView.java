package view;

import human.Patient;
import resource.HealthService;

/**
 * 
 * This Class is the view of the class HealthService.
 *
 */

public class HealthServiceView implements View {
	
	private HealthService service;
	

	public HealthServiceView(HealthService service) {
		super();
		this.service = service;
	}


	@Override
	public void display() {
		System.out.println("------------------------------------------------------");
		System.out.println("Service ID number: "+service.getID());
		System.out.println("Department of: "+service.getType());
		System.out.println("This service has time-service law: "+service.servicetime.getType());
		if(service.waitingQueue.container.isEmpty()) {
			System.out.println("No one is waiting in this service waiting-queue");
		}
		else {
			System.out.println("\npeople waiting in the queue for this service are: \n");
			int i=0;
			Patient patient;
			while(i<service.waitingQueue.container.size()) {
				patient = service.waitingQueue.container.get(i);
				HumanView view = new HumanView(patient);
				view.display();
				i++;
			}
		}
		System.out.println("------------------------------------------------------");
	}
	

}
