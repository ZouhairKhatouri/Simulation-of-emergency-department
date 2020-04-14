package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import human.Patient;
import human.Physician;

public class PhysicianTest {

	@Test
	public void test() throws Exception {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

		Patient Paul = new Patient("Paul","Sarter",ed.nextID(),"Gold","L3",0);
		
		ed.patients.add(Paul);
		
		Physician physician = ed.physicians.get(0);
		
		physician.startHandling(Paul);
		
		assertTrue(Paul.getState()=="being visited" && physician.getState()=="visiting");
		
		physician.emitVerdict(Paul, 3);
		
		Paul.messageBox.show();
	}

}
