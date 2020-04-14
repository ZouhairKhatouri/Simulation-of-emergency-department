package junit_test;

import static org.junit.Assert.*;


import org.junit.Test;

import auxiliary.Queue;

public class QueueTest {

	@Test
	public void test() {
		Queue<Integer> Q = new Queue<Integer>();
		Q.append(2);
		Q.append(3);
		Q.append(4);
		int x = Q.deq();
		Queue<Integer> Q1 = new Queue<Integer>();
		Q1.append(3);
		Q1.append(4);
		assertTrue(Q.last()==3);
		assertTrue(Q.first()==4);
		assertTrue((x==2)==true);
		assertTrue(Q1.equals(Q)==true);
	}
}
