package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import human.Nurse;

public class NurseTest {

	@Test
	public void test() {
		
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
		
		Nurse nurse = ed.nurses.get(0);
		
		assertTrue(nurse.isAvailable());
		
		System.out.println(nurse.getName());
	}

}
