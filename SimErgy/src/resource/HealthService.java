package resource;

import auxiliary.*;
import human.*;

/**
 * 
 * This is an abstract class representing the set of health services used in this project that are: Arrival, Consultation, BloodTest, Radiography and MRI.
 *
 */

public abstract class HealthService implements NHR{
	
	// Attributes
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5078181022514245755L;
	private int ID;
	public Queue<Patient> waitingQueue ;
	public ProbabilityDensity servicetime;
	
	// Constructor
	
	/**
	 * @param ID: the ID number of this entity.
	 * @param waitingQueue: a queue of patients representing the queue of patients waiting to be served by this facility.
	 * @param servicetime: is a probabilty distribution modelling the time of this service.
	 */
	
	public HealthService(int ID,Queue<Patient> waitingQueue,ProbabilityDensity servicetime) {
		this.ID=ID;
		this.waitingQueue=waitingQueue;
		this.servicetime=servicetime;
	}
	
	public HealthService(int ID, ProbabilityDensity servicetime) {
		this.ID=ID;
		this.servicetime=servicetime;
		this.waitingQueue = new Queue<Patient>();
	}
	
	// Getters and Setters
	
	@Override
	public int getID() {
		return ID;
	}
	
	public Queue<Patient> getWaitingQueue() {
		return waitingQueue;
	}

	public void setWaitingQueue(Queue<Patient> waitingQueue) {
		this.waitingQueue = waitingQueue;
		return;
	}

	public ProbabilityDensity getServicetime() {
		return servicetime;
	}

	public void setServicetime(ProbabilityDensity servicetime) {
		this.servicetime = servicetime;
		return;
	}
	
	/**
	 * this method returns the next waiting patient to be served and updates the waiting queue.
	 * @return: the next waiting patient to be served.
	 */
	
	public Patient deq() {
		return this.getWaitingQueue().deq();
	}
	
	/**
	 * This method gives priority to high severity patients.
	 */
	
	public void sortQueueByPriority() {
		
		Queue<Patient> SortedQueue = new Queue<Patient>(); // The queue that will contain the sorted waiting-queue.
		
		int counter = 0; // This loop let the patients with severity L1 be at the first positions of the waiting queue.
		while(counter<this.waitingQueue.container.size()) {
			Patient p = this.waitingQueue.deq();
			if(p.severityLevel.equals("L1")) {
				SortedQueue.append(p);
			}
			this.waitingQueue.append(p);
			counter++;
		}
		
		counter=0; // This loop let the patients with severity L2 be at the second positions of the waiting queue.
		while(counter<this.waitingQueue.container.size()) {
			Patient p = this.waitingQueue.deq();
			if(p.severityLevel.equals("L2")) {
				SortedQueue.append(p);
			}
			this.waitingQueue.append(p);
			counter++;
		}
		
		counter=0; // This loop let the patients with severity L3 be at the third positions of the waiting queue.
		while(counter<this.waitingQueue.container.size()) {
			Patient p = this.waitingQueue.deq();
			if(p.severityLevel.equals("L3")) {
				SortedQueue.append(p);
			}
			this.waitingQueue.append(p);
			counter++;
		}
		
		counter=0; // This loop let the patients with severity L4 be at the fourth positions of the waiting queue.
		while(counter<this.waitingQueue.container.size()) {
			Patient p = this.waitingQueue.deq();
			if(p.severityLevel.equals("L4")) {
				SortedQueue.append(p);
			}
			this.waitingQueue.append(p);
			counter++;
		}
		
		counter=0; // This loop let the patients with severity L5 be at the last positions of the waiting queue.
		while(counter<this.waitingQueue.container.size()) {
			Patient p = this.waitingQueue.deq();
			if(p.severityLevel.equals("L5")) {
				SortedQueue.append(p);
			}
			this.waitingQueue.append(p);
			counter++;
		}
		
		this.setWaitingQueue(SortedQueue); // changing the actual waiting queue to the sorted one ...
		return;
	}
	
	/**
	 * This method allow us in the same time to add a new patient to the waiting queue and to be sure that the waiting queue keeps respecting the priority condition.
	 * @param patient : the new patient to add this health-service waiting queue.
	 */
	
	public void newPatient(Patient patient) {
		this.getWaitingQueue().append(patient);
		this.sortQueueByPriority();
		return;
	}
	
	public Patient headOfQueue() {
		return this.getWaitingQueue().last();
	}
	
	/**
	 * This method makes the patient in the head of the waiting queue be served by the health-service and updates the waiting queue.
	 * @param visitor: is the visitor part of the visitor pattern (i.e. the analog of visitable.visit(Visitor)).
	 */
	
	public abstract void serve(Visitor visitor);
	public abstract String getType();
}
