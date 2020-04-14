package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import event.Transportation;
import human.Patient;
import human.Transporter;

public class TransportationTest {

	@Test
	public void test() throws Exception {
				//Build an ED
		
				ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);

				Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
				
				Transporter transporter = ed.transporters.get(0);
				
				Transportation event = new Transportation(0,Paul,15,5,transporter,"Radiography Departement");
				
				assertTrue(event.getName()=="Transportation");
				
				assertTrue(event.cost()==3.0);
				
				assertTrue(event.resources().get(0)==transporter);
				
				event.end();
				
				assertTrue(event.isOngoin()==false);
				
				assertTrue(Paul.getState()=="waiting in the departement");
				
				assertTrue(transporter.isAvailable());
				
				assertTrue(Paul.getLocation()=="Radiography Departement");
	}

}
