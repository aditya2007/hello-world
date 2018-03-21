package com.java.core.dsl.cache;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {
	
	private LRUCache<String, String> cache;
	
	/*@Before
	public void testSetup() {
		cache = new LRUCache<String, String>(3);
		System.out.println("Cache Instance intialized ....");
	}*/
	
	@After
	public void tearDown() {
		cache = null;
		System.out.println("Cache Instance got destroyed ...");
	}
	
	@Test
	public void testLRU() {
		System.out.println("Begin : testLRU ");
		cache = new LRUCache<String, String>(3);
		
		String key1 = "1";
		String key2 = "2";
		String key3 = "3";
		String key4 = "4";
		
		cache.put(key1, "one");
		cache.put(key2, "two");
		cache.put(key3, "three");
		
		Assert.assertTrue("Invalid order of items, intially it should be insertion ordered",
				Arrays.equals(new String[]{key1, key2, key3}, cache.getKeys()));
		
		cache.get(key1);
		Assert.assertTrue("Invalid order of items, accessing key1 should move to the end", 
				Arrays.equals(new String[]{key2, key3, key1}, cache.getKeys()));
		
		cache.get(key3);
		Assert.assertTrue("Invalid order of items, accessing key3 should move to the end", 
				Arrays.equals(new String[]{key2, key1, key3}, cache.getKeys()));

		cache.get(key2);
		Assert.assertTrue("Invalid order of items, accessing key2 should move to the end", 
				Arrays.equals(new String[]{key1, key3, key2}, cache.getKeys()));

		cache.put(key4, "four");
		Assert.assertTrue("Invalid order of items, last inserted item should be at last",
				Arrays.equals(new String[]{key3, key2, key4}, cache.getKeys()));
		System.out.println("End : testLRU ");
	}
	
	@Test
	public void testCacheSize() {
		System.out.println("Begin : testCacheSize ");
		cache = new LRUCache<String, String>(1);
		
		String key1 = "1";
		String key2 = "2";
		
		cache.put(key1, "one");
		Assert.assertEquals("one", cache.get(key1));
		cache.put(key2, "two");
		Assert.assertEquals("two", cache.get(key2));
		Assert.assertNull("Old key still existing, unexpected cache size", cache.get(key1));

		System.out.println("End : testCacheSize ");
	}

}
