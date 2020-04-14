package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.Exp;

public class ExpTest {

	@Test
	public void test() {
		Exp E = new Exp(10);
		double S=0;
		for(int i=0;i<1000000;i++) {
			double x = E.sample();
			S=S+x;
		}
		double Av = S/1000000;
		System.out.println(Av);
		assertTrue((Av-0.1)<0.001 && -0.001<(Av-0.1)); // This is a simple application of Bienaymé-Tchebychev inequality
	}

}
