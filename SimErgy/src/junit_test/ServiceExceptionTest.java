package junit_test;

import org.junit.Test;

import core.ED;
import core.SimErgy;
import human.Physician;
import resource.Arrival;
import resource.Visitor;

public class ServiceExceptionTest {

	@Test
	public void test() {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
		
		//Iniation of a simulation
		
		SimErgy sim = new SimErgy(ed);
		
		sim.manualSimulation(1440);
		
		Arrival arrival = ed.arrival;
		
		Physician physician = ed.physicians.get(0);
		
		Visitor visitor = new Visitor(sim,physician);
		
		visitor.serve(arrival);
		
	}

}
