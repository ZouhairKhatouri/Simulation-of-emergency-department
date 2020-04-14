package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ArrivalGenerator;
import core.ED;
import core.SimErgy;

public class ArrivalGeneratorTest {

	@Test
	public void test() {
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
				
		SimErgy sim = new SimErgy(ed);
		
		ArrivalGenerator gen = new ArrivalGenerator(sim);
		
		gen.tryAddingNewPatients();
		
		ed.clock+=1400;
		
		gen.tryAddingNewPatients();
		
		assertTrue(ed.patients.size()==5);
	}

}
