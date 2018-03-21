package com.java.core.dsl.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @See this for alternative and efficient implementation
 * https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-+-Double-linked-list-(with-a-touch-of-pseudo-nodes)
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> {
	
	//Maximum capacity for the LRU cache
	private final int capacity;
	// Queue to store the recently used keys, in FIFO order
	private ConcurrentLinkedQueue<K> queue;
	//key-value store to maintain the actual objects
	private ConcurrentHashMap<K, V> map;
	
	/**
	 * Initial capacity for the LRU cache
	 * 
	 * @param capacity
	 */
	public LRUCache(final int capacity) {
		this.capacity = capacity;
		this.queue = new ConcurrentLinkedQueue<K>();
		this.map = new ConcurrentHashMap<K,V>(capacity);
	}

	/**
	 * Check whether the item exists in the cache.
	 * Return null if the key doesn't exist in the cache.
	 * Meantime ensure to remove the item from the head 
	 * of the Queue and added it back to tail of the Queue. 
	 *  
	 * @param key
	 * @return - the value for the key or null if doesn't exist
	 */
	public V get(final K key) {
		if( map.get(key) != null ) {
			queue.remove(key);
			queue.add(key);
		}
		return map.get(key);
	}
	
	/**
	 * Add a new value to the LRU cache. If the key already exists,
	 * the key will be promoted to end of the cache.
	 * Neither the key nor value can be null.
	 * 
	 *  
	 * @param key
	 * @param value
	 * @throws NullPointerException
	 */
	public void put( final K key, final V value ) {
		if( key == null || value == null ) 
			throw new NullPointerException();
		
		if( map.containsKey(key) ) {
			queue.remove(key);
		}
		
		while( queue.size() >= capacity ) {
			K expiredKey = queue.poll();
			if( expiredKey != null ) {
				map.remove(expiredKey);
			} //end of inside if loop
		}// end of while loop
		queue.add(key);
		map.put(key, value);
	}
	
    public String[] getKeys() {
    	synchronized (queue) {
			return queue.toArray(new String[queue.size()]);
		}
    }
	
    public static int findIndex(String str) {
    	 
        if(str == null) return -1;
        char[] arr = str.toLowerCase().toCharArray();
        int result = 0;
        for(int i=0; i<arr.length; i++) {
            int value = ((int) arr[i]) - ((int) 'a');
            System.out.println("Value of char " + arr[i] +  " is " + value);
            result +=  (26 * value);   // There is a bug here, find out what it is :)
        }
        return result;
    }
	
	public static void main(String[] args) {
		LRUCache<String, String> cache = new LRUCache<String, String>(3);
		cache.put("1", "one");
		cache.put("2", "two");
		cache.put("3", "three");
		System.out.println("Elements in LRU CAche " + cache.map);
		System.out.println("Elements in LRU Queue " + cache.queue);
		cache.get("2");
		cache.put("4", "four");
		//cache.get("4");
		System.out.println("Elements in LRU CAche  after" + cache.map);
		System.out.println("Elements in LRU Queue  after" + cache.queue);
		
		//System.out.println("Index of the given string : " + findIndex("aad"));
	}
}
