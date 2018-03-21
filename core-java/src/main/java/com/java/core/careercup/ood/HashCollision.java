package com.java.core.careercup.ood;

import java.util.LinkedList;

public class HashCollision<K,V> {
	private final int MAX_SIZE = 10;
	LinkedList<Entry<K,V>>[] items;
	
	public HashCollision() {

		items = (LinkedList<Entry<K, V>>[]) new LinkedList[MAX_SIZE];
	}
	
	public int hashCodeOfKey(K key) {

		return key.toString().length() % items.length;
	}
	
	public void put(K key, V value) {
		int x = hashCodeOfKey(key);
		System.out.println(key + " :: " + x + " :: " + value);
		if(items[x] == null ) {
			items[x] = new LinkedList<Entry<K,V>>();
		}
		
		LinkedList<Entry<K,V>> collided = items[x];
		
		//Look for items with same key and replace if found
		for( Entry<K, V> entry : collided ) {
			System.out.println("Entry.getKey() >> " + entry.getKey() + " :: " + key);
			if( entry.equivalent(key)) { //There is problem, need to check this
				System.out.println("Got collision for Key " + key );
				collided.remove(entry);
				break;
			}
		}//end of fro loop
		
		Entry<K, V> entry  = new Entry<K, V>(key, value);
		collided.add(entry);
		System.out.println(collided);
	}//end of put
	
	public V get(K key) {
		int x = hashCodeOfKey(key);
		if( items[x] == null ) {
			return null;
		}
		
		LinkedList<HashCollision<K,V>.Entry<K,V>> collided = items[x];
		for( Entry<K, V> entry : collided ) {
			if( entry.equivalent(key)) {
				return entry.getValue();
			}
		}//end of fro loop
		
		return null;
	} //end of get
	
	
	 class Entry<K,V> {
		private K key;
		private V value;
		
		public Entry(K k, V v) {
			key = k;
			value = v;
		}
		
		public boolean equivalent(Entry<K,V> entry) {
			return equivalent(entry.getKey());
		}
		
		public boolean equivalent(K k) {
			return key.equals(k);
		}
		
		public K getKey() {
			return key;
		}
		
		public V getValue() {
			return value;
		}
	}  //end of Entry class
	 
	 public static void main(String[] args) {
		 HashCollision<String, String> hash = new HashCollision<String, String>();
		 
		 hash.put("Bob", "Bob First Name");
		 hash.put("Rob", "Bob Last Name");
		 hash.put("Yoga", "Yoga First Name");
		 hash.put("Gowda", "Yoga Last Name");
		 
		 System.out.println(hash.get("Rob"));
	}
}
