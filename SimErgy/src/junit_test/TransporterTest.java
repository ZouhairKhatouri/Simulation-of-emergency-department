package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import core.ED;
import human.Transporter;

public class TransporterTest {

	@Test
	public void test() {
		//Build an ED
		
		ED ed = new ED("Hospital Saint Louis",4,4,4,1,1,1,5,5,5,10);
				
		Transporter transporter = ed.transporters.get(0);
				
		assertTrue(transporter.isAvailable());
				
		System.out.println(transporter.getName());
	}

}
