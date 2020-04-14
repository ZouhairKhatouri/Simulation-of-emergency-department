package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import event.ConsultationEvent;
import human.Patient;
import human.Physician;

public class ConsultationEventTest {

	@Test
	public void test() throws Exception {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

		Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
		
		Physician physician = ed.physicians.get(0);
		
		ConsultationEvent event = new ConsultationEvent(0,Paul,15,20,physician);
		
		assertTrue(event.getName()=="Consultation");
		
		assertTrue(event.cost()==23.0);
		
		assertTrue(event.resources().get(0)==physician);
		
		event.end();
		
		assertTrue(event.isOngoin()==false);
		
		assertTrue(Paul.getState()=="is waiting");
		
		assertTrue(physician.getState()=="idle");
	}

}
