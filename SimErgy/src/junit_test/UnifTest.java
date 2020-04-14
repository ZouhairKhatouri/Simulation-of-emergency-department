package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Unif;

public class UnifTest {

	@Test
	public void test() {
		Unif U = new Unif(0,1);
		double S=0;
		for(int i=0;i<1000000;i++) {
			double x = U.sample();
			S=S+x;
		}
		double Av = S/1000000;
		assertTrue((Av-0.5)<0.001 && -0.001<(Av-0.5)); // This is a simple application of Bienaymé-Tchebychev inequality
	}

}
