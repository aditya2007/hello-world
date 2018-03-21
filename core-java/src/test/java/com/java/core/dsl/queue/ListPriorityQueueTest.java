package com.java.core.dsl.queue;

import java.util.Arrays;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ListPriorityQueueTest {
	
	private ListPriorityQueue<Integer> priorityQ;
	
	@Before
	public void testSetup() {
		priorityQ = new ListPriorityQueue<Integer>();
		priorityQ.insert(40);
		priorityQ.insert(50);
		priorityQ.insert(30);
		priorityQ.insert(60);
		priorityQ.insert(20);
		priorityQ.insert(70);
		priorityQ.insert(10);
	}
	
	@After
	public void tearDown() {
		priorityQ = null;
	}
	
	@Test
	public void testInsertToQ() {
		System.out.println("Begin :testInsertToQ ");
		
		Assert.assertTrue("Invalid order of items, it should be ascending order of items ",
						Arrays.equals(new Integer[]{10,20,30,40,50,60,70}, priorityQ.iterator().values()));

		print( priorityQ.iterator().values());
		System.out.println("\nEnd :testInsertToQ " );
	}
	
	@Test
	public void testRemove() {
		System.out.println("Begin :testRemove ");
		int val = (Integer)priorityQ.remove();
		Assert.assertEquals(10, val);
		Assert.assertTrue("Invalid order of items, it should be order of items [20,30,40,50,60,70]", 
					Arrays.equals(new Integer[]{20,30,40,50,60,70}, priorityQ.iterator().values()));

		print( priorityQ.iterator().values());
		System.out.println("\nEnd :testRemove");
	}
	
	@Test
	public void testPeek() {
		System.out.println("Begin :testPeek ");
		int val = (Integer)priorityQ.peek();
		Assert.assertEquals(10, val);
		Assert.assertTrue("Invalid order of items, it should be order of items [20,30,40,50,60,70]", 
				Arrays.equals(new Integer[]{10,20,30,40,50,60,70}, priorityQ.iterator().values()));

		print( priorityQ.iterator().values());
		System.out.println("\nEnd :testPeek ");
	}
	
	private void print(Object[] values) {
		for( Object val : values) {
			System.out.print(val + ",");
		}
	}

}
