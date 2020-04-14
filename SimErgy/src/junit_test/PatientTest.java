package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import core.SimErgy;
import human.Patient;

public class PatientTest {

	@Test
	public void test() throws Exception {
		
		Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
		
		Patient Marie = new Patient("Marie","Sarter",0,"Silver","L2",0);
		
		assertTrue(Paul.equals(Marie)==false);
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

		//Manual arrival of a new patient
						
		ed.patients.add(Paul);
						
		SimErgy sim = new SimErgy(ed);
						
		sim.manualSimulation(1440);
				
		Paul.showhistory();
				
		Paul.showCharges();
	}

}
