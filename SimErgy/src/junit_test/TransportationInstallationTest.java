package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import event.TransportationInstallation;
import human.Nurse;
import human.Patient;
import resource.Room;

public class TransportationInstallationTest {

	@Test
	public void test() throws Exception {
				//Build an ED
		
				ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

				Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
				
				Nurse nurse = ed.nurses.get(0);
				
				Room room = ed.waitingRooms.get(0);
				
				TransportationInstallation event = new TransportationInstallation(0,Paul,15,2,nurse,room);
				
				assertTrue(event.getName()=="Transportation/Installation");
				
				assertTrue(event.cost()==5.0);
				
				assertTrue(event.resources().get(0)==nurse);
				
				assertTrue(event.resources().get(1)==room);
				
				event.end();
				
				assertTrue(event.isOngoin()==false);
				
				assertTrue(Paul.getState()=="is being registred in consultation departement");
				
				assertTrue(nurse.isAvailable());
							
				assertTrue(Paul.getPersonnalroom()==room);
	}

}
