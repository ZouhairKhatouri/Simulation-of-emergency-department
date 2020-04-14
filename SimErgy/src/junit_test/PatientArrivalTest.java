package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import event.PatientArrival;
import human.Patient;


public class PatientArrivalTest {

	@Test
	public void test() throws Exception {

		Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
		
		PatientArrival event = new PatientArrival(0,Paul,15,"L3");
		
		assertTrue(event.getName()=="Patient Arrival");
		
		assertTrue(event.cost()==0);
		
		assertTrue(event.resources().isEmpty());
		
	}

}
