package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.L4;
import auxiliary.SpecificArrivalGenerator;
import auxiliary.Unif;
import core.ED;
import human.Patient;

public class L4Test {

	@Test
	public void test() throws Exception {
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
				
		assertTrue(ed.patients.size()==0);
				
		//arrival-time's probability
				
		Unif U = new Unif(0,10);
				
		//Severity Level
				
		L4 sev = new L4(U);
				
		//L4's arrival generator
				
		SpecificArrivalGenerator gen = new  SpecificArrivalGenerator(ed,sev);
				
		gen.tryAddingNewPatient();
				
		ed.clock=10;
				
		gen.tryAddingNewPatient();
				
		assertTrue(ed.patients.size()==1);
				
		Patient generatedPatient = ed.patients.get(0);
				
		assertTrue(generatedPatient.severityLevel=="L4");
	}

}
