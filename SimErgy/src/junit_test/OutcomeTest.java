package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import event.Outcome;
import human.Patient;
import human.Physician;
import resource.Room;

public class OutcomeTest {

	@Test
	public void test() throws Exception {
		
				//Build an ED
		
				ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

				Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
				
				Physician physician = ed.physicians.get(0);
				
				Room room = ed.waitingRooms.get(0);
				
				Paul.setPersonnalroom(room);
				
				physician.startHandling(Paul);
				
				Outcome event = new Outcome(0,Paul,15,physician);
				
				assertTrue(event.getName()=="Outcome");
				
				assertTrue(event.cost()==0);
				
				assertTrue(event.resources().get(0)==physician);
				
				event.end();
				
				assertTrue(event.isOngoin()==false);
				
				assertTrue(room.isAvailable());
				
				assertTrue(Paul.getState()=="released");
				
				assertTrue(physician.getState()=="idle");
	}

}
