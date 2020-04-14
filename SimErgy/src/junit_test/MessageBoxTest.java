package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import core.SimErgy;
import human.Patient;

public class MessageBoxTest {

	@Test
	public void test() throws Exception {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

		//Manual arrival of a new patient
				
		Patient Paul = new Patient("Paul","Sarter",ed.nextID(),"Gold","L3",0);
				
		ed.patients.add(Paul);
				
		SimErgy sim = new SimErgy(ed);
				
		sim.manualSimulation(1440);
		
		Paul.messageBox.show();
		
		ed.physicians.get(0).messageBox.show();
		
		Paul.messageBox.box.deq();
		
		assertTrue(Paul.messageBox.box.last().content.equals("you have no test to take"));
	}

}
