package resource;

import core.SimErgy;
import event.ConsultationEvent;
import event.Examination;
import event.RegistrationTriage;
import human.Nurse;
import human.Patient;
import human.Physician;
import throwables.ServiceException;

/**
 * 
 * This class is the visitor part of the visitor pattern.
 * It makes the operation of serving by health-services more centralised.
 *
 */

public class Visitor implements HealthServiceVisitor{
	
	//Attributes
	
	/**
	 * @param sim : is the simulation of the emergency departement where the changes will occure.
	 * @param ressource : is the resource needed to serve the patient on the head of the waiting queue of a given health-service.
	 * 
	 */
	
	public SimErgy sim;
	public Resource ressource;
	
	//Constructor
	
	public Visitor(SimErgy sim, Resource ressource) {
		this.sim = sim;
		this.ressource = ressource;
	}
	
	
	// This is the visitor part of the visitor pattern

	/**
	 * This method provides the main changes after a patient is served by a given by the arrival service.
	 */
	
	@Override // Arrival visitor
	public void serve(Arrival arrival) {
		if(this.ressource instanceof Nurse) {
			Patient patient=arrival.deq();
			((Nurse)this.ressource).setAvailable(false);
			patient.setState("registring");
			double t=arrival.getServicetime().sample();
			RegistrationTriage event = new RegistrationTriage(sim.ed.nextID(),patient,sim.ed.clock,t,(Nurse)this.ressource);
			patient.newEvent(event);
			sim.history.append(event);
			return;
		}
		else {
			throw new ServiceException(arrival);
		}
	}

	/**
	 * This method provides the main changes after a patient is served by a given by the blood-test service.
	 */
	
	@Override // Blood Test visitor
	public void serve(BloodTest bloodTest) {
		if(this.ressource instanceof BloodTestLab) {
			Patient patient=bloodTest.deq();
			((BloodTestLab)ressource).setAvailable(false);
			((BloodTestLab)ressource).setUser(patient);
			patient.setState("being examinated");
			double t=bloodTest.getServicetime().sample();
			Examination event = new Examination(sim.ed.nextID(),patient,sim.ed.clock,t,(BloodTestLab)ressource);
			patient.newEvent(event);
			sim.history.append(event);
			return;
		}
		else {
			throw new ServiceException(bloodTest);
		}
	}
	
	/**
	 * This method provides the main changes after a patient is served by a given by the consultation service.
	 */

	@Override // Consultation Visitor
	public void serve(Consultation consultation) {
		if(this.ressource instanceof Physician) {
			Patient patient=consultation.deq();
			((Physician)ressource).startHandling(patient);
			double t= consultation.getServicetime().sample();
			ConsultationEvent nextevent = new ConsultationEvent(sim.ed.nextID(),patient,sim.ed.clock,t,((Physician)ressource));
			patient.newEvent(nextevent);
			sim.history.append(nextevent);
			patient.setDTDT(sim.ed.clock-patient.getArrivalTime());
			return;
		}
		else {
			throw new ServiceException(consultation);
		}
	}
	
	/**
	 * This method provides the main changes after a patient is served by a given by the MRI service.
	 */
 
	@Override // MRI Visitor
	public void serve(MRI mri) {
		if(this.ressource instanceof MRIroom) {
			Patient patient=mri.deq();
			((MRIroom)ressource).setAvailable(false);
			((MRIroom)ressource).setUser(patient);
			patient.setState("being examinated");
			double t=mri.getServicetime().sample();
			Examination event = new Examination(sim.ed.nextID(),patient,sim.ed.clock,t,((MRIroom)ressource));
			patient.newEvent(event);
			sim.history.append(event);
			return;
		}
		else {
			throw new ServiceException(mri);
		}
	}
	
	/**
	 * This method provides the main changes after a patient is served by a given by the Radiography service.
	 */

	@Override //Radiography Visitor
	public void serve(Radiography radiography) {
		if(this.ressource instanceof RadiographyRoom) {
			Patient patient=radiography.deq();
			((RadiographyRoom)ressource).setAvailable(false);
			((RadiographyRoom)ressource).setUser(patient);
			patient.setState("being examinated");
			double t=radiography.getServicetime().sample();
			Examination event = new Examination(sim.ed.nextID(),patient,sim.ed.clock,t,((RadiographyRoom)ressource));
			patient.newEvent(event);
			sim.history.append(event);
			return;
		}
		else {
			throw new ServiceException(radiography);
		}
		
	}

}
