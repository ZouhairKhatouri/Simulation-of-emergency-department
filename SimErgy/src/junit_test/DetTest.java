package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Det;

public class DetTest {

	@Test
	public void test() {
		Det d = new Det(2);
		for(int i=0;i<10;i++) {
			double x = d.sample();
			assertTrue(x==2);
		}
	}

}
