package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import event.RegistrationTriage;
import human.Nurse;
import human.Patient;

public class RegistrationTriageTest {

	@Test
	public void test() throws Exception {
				//Build an ED
		
				ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

				Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
				
				Nurse nurse = ed.nurses.get(0);
				
				RegistrationTriage event = new RegistrationTriage(0,Paul,15,20,nurse);
				
				assertTrue(event.getName()=="Registration/Triage");
				
				assertTrue(event.cost()==3.0);
				
				assertTrue(event.resources().get(0)==nurse);
				
				event.end();
				
				assertTrue(event.isOngoin()==false);
				
				assertTrue(Paul.getState()=="registred");
		
	}

}
