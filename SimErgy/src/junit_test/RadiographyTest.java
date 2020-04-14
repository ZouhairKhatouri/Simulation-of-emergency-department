package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Queue;
import core.ED;
import core.SimErgy;
import event.Event;
import human.Patient;
import resource.RadiographyRoom;
import resource.Visitor;

public class RadiographyTest {

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

		ed.radiographyDepartment.newPatient(Paul);
		ed.radiographyDepartment.newPatient(Marie);
		
		RadiographyRoom room = ed.radiographyRooms.get(0);
		
		ed.radiographyDepartment.serve(new Visitor(sim,room));
		
		System.out.println(Paul.getState());
		
		//Tests
		
		assertTrue(ed.radiographyDepartment.deq().equals(Paul));
		assertTrue(Marie.getState()=="being examinated");
		sim.showhistory();
	}

}
