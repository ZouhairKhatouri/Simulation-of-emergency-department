package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import event.Examination;
import human.Patient;
import resource.BloodTestLab;


public class ExaminationTest {

	@Test
	public void test() throws Exception {

		Patient Paul = new Patient("Paul","Sarter",0,"Gold","L3",0);
		
		BloodTestLab lab = new BloodTestLab(0);
		
		Examination event = new Examination(0,Paul,15,20,lab);
		
		assertTrue(event.getName()=="Examination");
		
		assertTrue(event.cost()==70.0);
		
		assertTrue(event.resources().get(0)==lab);
		
		event.end();
		
		assertTrue(event.isOngoin()==false);
		
		assertTrue(lab.isAvailable());
		
		assertTrue(Paul.getState()=="is waiting");
	}

}
