package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Queue;
import core.ED;
import core.SimErgy;
import event.Event;
import human.Patient;
import patientStrategy.ArrivalStrategy;



public class ArrivalStrategyTest {

	@Test
	public void test() throws Exception {
		
		Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
		
		ed.patients.add(Paul);
		
		Queue<Event> history = new Queue<Event>();
		
		SimErgy sim = new SimErgy(ed,history);
		
		ArrivalStrategy Ar = new ArrivalStrategy(sim,Paul);
		
		Ar.perform();
		
		assertTrue(Paul.getState()=="is waiting");
	}

}
