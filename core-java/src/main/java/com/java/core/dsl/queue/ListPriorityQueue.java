package com.java.core.dsl.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListPriorityQueue<E> {
	private Node head;
	private int size;
	
	private final int DEFAULT_MAX_CAPACITY = Integer.MAX_VALUE;
	
	public ListPriorityQueue() {
		this.head = null;
		this.size = 0;
	} // end of ListPriorityQueue constructor
	
	public boolean insert(E value) {
		if( isFull() ) { 
			return false;
		}
		
		if ( head == null ) {
			head = new Node(value, null);
		} 
		else if( ((Comparable<E>)value).compareTo(head.value) < 0 ) {
			head = new Node(value, head);
		}
		else {
			Node p = head;
			while( p.next != null 
					&& ((Comparable<E>)value).compareTo(p.next.value) >= 0 ) {
				p = p.next; // or equal to preserve FIFO on equal items
			} // end of while loop
			p.next = new Node(value, p.next);
		} // end of else
		
		++size;
		return true;
	} // end of insert
	
	
	public E remove() {
		if( isEmpty() ) 
			return null;
		
		/*E value = head.value;
		head = head.next;
		--size;*/
		E value = poll();
		if( value != null ) {
			return value;
		} else {
			throw new NoSuchElementException();
		}
	}
	
	private E poll() {
		
		E value = head.value;
		head = head.next;
		--size;
		return value;
	}
	
	public E peek() {
		if( isEmpty() ) 
			return null;
		
		return head.value;
	}
	
	
	public NodeItr iterator() {
		if( isEmpty() ) 
			return null;
		
		return new NodeItr();
	} // end of iterator()
	
	public boolean isFull() {
		return size == DEFAULT_MAX_CAPACITY;
	} //end of isFull
	
	public boolean isEmpty() {
		return size == 0;
	}// end of isEmpty
	
	private class Node {
		private E value;
		private Node next;
		
		public Node( E value, Node next ) {
			this.value = value;
			this.next = next;
		}
	} // end of Node class

	public class NodeItr implements Iterator<E> {
		
		private E[] arr;
		private int n;

		public NodeItr() {
			n = size;
			arr = (E[]) new Object[n];
			Node p = head;
			for( int i = 0; i < n; i++ ) {
				arr[i] = p.value;
				p = p.next;
			} // end of NodItr for loop
		} // end of NodeItr constructor
		
		public boolean hasNext() {
			return n > 0;
		} // end of hasNext

		public E next() {
			if( n == 0 ) {
				throw new NoSuchElementException();
			} // end of if 
			
			return arr[--n];
		} // end of next

		public void remove() {
			throw new UnsupportedOperationException();
		} // end of remove
		
		/**
		 * Utility method get the list of values in the Queue
		 * @return - array of objects in the Queue
		 */
		public E[] values() {
			return arr;
		}
	} // end of NodeItr class
	
	
}
