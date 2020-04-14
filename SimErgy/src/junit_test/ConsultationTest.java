package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Queue;
import core.ED;
import core.SimErgy;
import event.Event;
import human.Patient;
import human.Physician;
import resource.Visitor;

public class ConsultationTest {

	@Test
	public void test() throws Exception {
		
		//Settings
		
		Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
		Patient Marie = new Patient("Marie","Dupont",0, "Silver","L1",0);
		
		//Building an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
		
		ed.patients.add(Paul);
		ed.patients.add(Marie);

		Queue<Event> history = new Queue<Event>();
		
		SimErgy sim = new SimErgy(ed,history);

		ed.consultationDepartment.newPatient(Paul);
		ed.consultationDepartment.newPatient(Marie);
		
		Physician physician = ed.physicians.get(0);
		
		ed.consultationDepartment.serve(new Visitor(sim,physician));
		
		System.out.println(Paul.getState());
		
		//Tests
		
		assertTrue(ed.consultationDepartment.deq().equals(Paul));
		assertTrue(Marie.getState()=="being visited");
		assertTrue(physician.getState()=="visiting");
		sim.showhistory();
	}

}
