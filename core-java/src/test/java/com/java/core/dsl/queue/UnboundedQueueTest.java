package com.java.core.dsl.queue;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnboundedQueueTest {
	
	private UnboundedQueueImpl<Integer> unboundQ;
	
	
	@Before
	public void testSetup() {
		unboundQ = new UnboundedQueueImpl<Integer>();
		
		unboundQ.enqueue(40);
		unboundQ.enqueue(50);
		unboundQ.enqueue(30);
		unboundQ.enqueue(60);
		unboundQ.enqueue(20);
		unboundQ.enqueue(70);
		unboundQ.enqueue(10);
	}
	
	@After
	public void tearDown() {
		unboundQ = null;
	}

	@Test
	public void testEnqueue() {
		System.out.println("Begin :testEnqueue ");
		
		Assert.assertTrue("Invalid order of items, it should be in the same as insertion order ",
						Arrays.equals(new Integer[]{40,50,30,60,20,70,10}, unboundQ.iterator().values()));

		print( unboundQ.iterator().values());
		System.out.println("\nEnd :testEnqueue " );
	}

	private void print(Object[] values) {
		for( Object val : values) {
			System.out.print(val + ",");
		}
	}

	
	@Test
	public void testDequeue() {
		System.out.println("Begin :testDequeue ");
		int val = (Integer)unboundQ.dequeue();
		Assert.assertEquals(40, val);
		Assert.assertTrue("Invalid order of items, it should be order of items [40,50,30,60,20,70,10]", 
					Arrays.equals(new Integer[]{50,30,60,20,70,10}, unboundQ.iterator().values()));

		print( unboundQ.iterator().values());
		System.out.println("\nEnd :testDequeue");
	}

}
