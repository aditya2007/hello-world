package com.java.core.dsl.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnboundedQueueImpl<E> {
	
	private Node head;
	private int size;
	private final int DEFAULT_MAX_CAPACITY = Integer.MAX_VALUE;
	
	public UnboundedQueueImpl() {
		this.head = null;
		this.size = 0;
	}
	
	public boolean enqueue(E item) {
		if( item == null )
			throw new NullPointerException();
		
		if( isFull() ) 
			return false;
		
		if( head == null ) {
			head = new Node(item, head);
		} else {
			Node current = head;
			while( current.next != null ) {
				current = current.next;
			} // end of while
			
			current.next = new Node(item, current.next);
		} // end of else
		++size;
		return true;
	} // end of enqueue method
	
	
	public E dequeue() {
		if( isEmpty() )
			return null;
		
		E item = head.item;
		head = head.next;
		--size;
		return item;
	} // end of dequeue method
	
	public boolean isFull() {
		return size == DEFAULT_MAX_CAPACITY;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public NodeItr iterator() {
		if( isEmpty() ) 
			return null;
		
		return new NodeItr();
	}
	
	public class NodeItr implements Iterator<E> {
		
		private E[] arr;
		private int elements;
		
		private NodeItr() {
			elements = size;
			arr = (E[]) new Object[elements];
			Node p = head;
			for( int i = 0; i < elements; i++ ) {
				arr[i] = p.item;
				p = p.next;
			}
		}
		
		public boolean hasNext() {
			return elements > 0;
		}

		public E next() {
			if( elements == 0 )
				throw new NoSuchElementException();
			
			return arr[--elements];
		} 

		public void remove() {
			throw new UnsupportedOperationException();
		}
		
		/**
		 * Utility method get the list of values in the Queue
		 * @return - array of objects in the Queue
		 */
		public E[] values() {
			return arr;
		}
		
	}// end of NodeItr class
	
	private class Node {
		Node next;
		E item;
		
		public Node(E item, Node next) {
			this.item = item;
			this.next = next;
		}
	} // end of node class
	

}
