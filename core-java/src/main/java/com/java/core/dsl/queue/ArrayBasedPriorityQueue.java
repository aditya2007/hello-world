package com.java.core.dsl.queue;

/**
 * This implementation from dsl book.
 * Efficiency : Insertion runs in O(N) linear time, 
 * while deleteion takes O(1) time. 
 *
 */
public class ArrayBasedPriorityQueue<E> {

	private int capacity;
	private E[] items;
	private int count;
	
	public ArrayBasedPriorityQueue(int capacity) {
		this.capacity = capacity;
		items = (E[])new Object[capacity];
	}
	
	
	public void enqueue(E item) {
		Comparable<? super E> val = (Comparable<? super E>) item;
		int k;
		
		if( count == 0 ) {
			items[count++] = item;
		} else {
			for( k = count - 1; k >= 0; k-- ) {
				if( val.compareTo((E)items[k]) > 0 ) {
					items[k+1] = items[k];
				} else {
					break;
				}
			} // end of for loop
			items[k+1] = item;
			count++;
		}
	}
	
	/*public void enqueue(long item) {
		int i;
		
		if( count == 0 ) {
			items[count++] = item; //if no item, insert at 0.
		} else { //if items
			for( i = count-1; i >= 0 ; i--) { //start at end
				if( item > items[i] ) { //if new item larger, 
					items[i+1] = items[i]; //shift upward.
				} else { //if smaller
					break; //done shifting
				}
			} // end of for loop
			System.out.println("The current index : " + i);
			items[i+1] = item;
			count++;
		} // end of else
	}*/
	
	public E dequue() {
		return items[--count];
	}
	
	public boolean isEmpty() {  
		return count == 0;
	}
	
	public static void main(String[] args) {
		ArrayBasedPriorityQueue<Long> queue = new ArrayBasedPriorityQueue<Long>(5);
		queue.enqueue(30l);
		queue.enqueue(50l);
		queue.enqueue(10l);
		queue.enqueue(40l);
		queue.enqueue(20l);
		queue.enqueue(60l);
		
		while( !queue.isEmpty() ) {
			long item = queue.dequue();
			System.out.print(item + ", ");
		} // end of while
		System.out.println();
	}
}
