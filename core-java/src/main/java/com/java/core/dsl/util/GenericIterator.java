package com.java.core.dsl.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GenericIterator<E> implements Iterator<E> {

	private E[] values;
	private int n;
	private int lastIdx;

	public GenericIterator(E[] values) {
		this.values = values;
		for (int currIdx = 1; currIdx < values.length; currIdx++)
        {
            if (values[lastIdx] != values[currIdx])
            {
            	values[++lastIdx] = values[currIdx];
            }
        }
	} // end of  constructor

	public boolean hasNext() {
		return n <= lastIdx;
	} // end of hasNext

	public E next() {
		if (n > lastIdx) {
			throw new NoSuchElementException();
		} // end of if
		
		return values[n++];
	} // end of next

	public void remove() {
		throw new UnsupportedOperationException();
	} // end of remove
	
	public static void main(String[] args) {
		Integer[] input = new Integer[]{1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
		Iterator<Integer> itr = new GenericIterator<Integer>(input);
		while( itr.hasNext() ) {
			System.out.print(" " + itr.next());
		}
	}
}
