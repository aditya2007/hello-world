package com.java.core.dsl.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;

public class StackImpl<E> {
	private E[] elements;
	private int elementCount;
	
	public StackImpl() {
		this(10);
	}
	
	public StackImpl(int intialCapacity) {
		elements = (E[])new Object[intialCapacity];
	}
	
	public boolean isEmpty() {
		return elementCount == 0;
	}

	public int size() {
		return elementCount;
	}
	
	private void ensureCapacity(int minCapacity) {
		
		int oldCapacity = elements.length;
		
		if( minCapacity > oldCapacity ) {
			int newCapacity = oldCapacity * 2;
			//Object[] newElements = new Object[newCapacity];
			//System.arraycopy(elements, 0, newElements, 0, elements.length);
			//elements = newElements;
			elements = Arrays.copyOf(elements, newCapacity);
			System.out.println("Ensuring the capacity of Satck before pushing an element " + elements.length);
		}
	}

	public E push(E element) {
		ensureCapacity(elementCount+1);
		elements[elementCount++] = element;
		return element;
	}

	public E peek() {
		int len = size();
		if( len == 0) throw new EmptyStackException();
		
		return (E)elements[len-1];
	}
	
	public E pop() {
		E element = peek();
		elements[--elementCount] = null; // lets gc do its work
		return element;
		
	}
	
	public int min() {
		Integer min = Integer.MAX_VALUE;
		for( E element : elements ) {
			if( (Integer)element < min ) {
				min = (Integer)element;
			}
		}
		return min;
	}
	
	public static void main(String[] args) {
		
		StackImpl<Integer> stack = new StackImpl<Integer>(5);
		stack.push(10);
		stack.push(5);
		stack.push(15);
		stack.push(2);
		stack.push(20);
		
		System.out.println(stack.min());
		/*StackImpl<String> stack = new StackImpl<String>();
		System.out.println("Is Stack empty @1 ?? " + (stack.isEmpty() ? "Yes" : "No" ));
		System.out.println("Stack size @1: " + stack.size() );

		stack.push("element1");
		stack.push("element2");
		stack.push("element3");
		stack.push("element4");
		stack.push("element5");
		stack.push("element6");
		stack.push("element7");
		stack.push("element8");
		stack.push("element9");
		stack.push("element10");
		
		System.out.println("Is Stack empty @2 ?? " + (stack.isEmpty() ? "Yes" : "No" ));
		System.out.println("Stack size @2 : " + stack.size() );

		String element = stack.peek();
		System.out.println("Elemnet at top of the Stack @1 : " + element );
	
		element = stack.pop();

		element = stack.peek();
		System.out.println("Elemnet at top of the Stack @2 : " + element );

		stack.push("element11");
		stack.push("element12");
		System.out.println("Is Stack empty @3 ?? " + (stack.isEmpty() ? "Yes" : "No" ));
		System.out.println("Stack size @3 : " + stack.size() );
		element = stack.peek();
		System.out.println("Elemnet at top of the Stack @3 : " + element );*/
	}
}
