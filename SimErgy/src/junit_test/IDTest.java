package junit_test;

import static org.junit.Assert.*;

import org.junit.Test;

import auxiliary.ID;

public class IDTest {

	@Test
	public void test() {
		ID ID1 = ID.getinstance();
		ID ID2 = ID.getinstance();
		int x=ID1.Next();
		int y=ID2.Next();
		int z=ID1.Next();
		assertTrue(x==0&&y==1&&z==2);
	}

}
