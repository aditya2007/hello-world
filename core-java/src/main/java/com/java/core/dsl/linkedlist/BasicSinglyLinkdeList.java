package com.java.core.dsl.linkedlist;

import java.util.*;

public class BasicSinglyLinkdeList<E> {
	
	Node<E> start;
	int size;
	Queue<String> q = new LinkedList<>();

	public BasicSinglyLinkdeList() {
		start = null;
		size = 0;
	}
	
	void add(E data) {
		//insertAtFirst(data);
		//insertAtLast(data);
		addToTail(data);
		q.add("3");
		q.add("2");
		q.poll();
	}
	
	void insertAtFirst(E data) {
		if( size == 0 ){
			start = new Node<E>();
			start.next = null;
			start.data = data;
		} else {
			Node<E> newNode = new Node<E>();
			newNode.next = start;
			newNode.data = data;
			start = newNode;
		}
		size++;
	}
	
	void insertAtLast(E data) {
		if( size == 0 ) {
			start = new Node<E>();
			start.next = null;
			start.data = data;
		} else {
			Node<E> currentNode = getNodeAt(size -1);
			Node<E> newNode = new Node<E>();
			newNode.next = null;
			newNode.data = data;
			currentNode.next = newNode;
		}
		size++;
	}

	//Just a different approach
	void addToTail(E data) {
		if( size == 0 ) {
			start = new Node<E>();
			start.next = null;
			start.data = data;
		} else {
			Node<E> newNode = new Node<E>();
			newNode.next = null;
			newNode.data = data;
			Node<E> currentNode = start;
			while( currentNode.next != null ) {
				currentNode = currentNode.next;
			}
			currentNode.next = newNode;
		}
		size++;
	}
	
	void insertAtLastForLoop(E data) {
		if( size == 0 ) {
			start = new Node<E>();
			start.next = null;
			start.data = data;
		} else {
			Node<E> currentNode = getNodeAt(size -1);
			Node<E> newNode = new Node<E>();
			newNode.next = start;
			newNode.data = data;
			currentNode.next = newNode;
		}
		size++;
	}

	public void insertAt(int position, E data) {
		if( position == 0 ) {
			insertAtFirst(data);
		} else if( position == size-1 ) {
			insertAtLast(data);
		} else {
			Node<E> tempNode = getNodeAt(position);
			Node<E> newNode = new Node<E>();
			newNode.data = data;
			newNode.next = tempNode.next;
			tempNode.next = newNode;
		}
		size++;
	}

	Node<E> getFirst() {
		return getNodeAt(0);
	}
	
	Node<E> getLast() {
		return getNodeAt(size-1);
	}

	Node<E> getNodeAt(int nodePos) {
		if( nodePos > size || nodePos < 0 ) {
			throw new ArrayIndexOutOfBoundsException();
		}
		
		Node<E> tempNode = start;
		//System.out.println("Start Node at getNodeAt :::: " + tempNode.data);
		for( int i = 0; i < nodePos; i++ ) {
			tempNode = tempNode.next;
		}
		return tempNode;
	}
	
	E removeAtFirst() throws ArrayIndexOutOfBoundsException {
		if( size == 0 ) throw new ArrayIndexOutOfBoundsException();
		
		E data = start.data;
		start = start.next;
		size--;
		return data;
	}

	E removeAtLast() throws IndexOutOfBoundsException {
		if( size == 0 ) throw new IndexOutOfBoundsException();
		
		Node<E> tempNode = getNodeAt(size-2);
		System.out.println("Current size :: " + size + ", Node At :: " + tempNode.data);
		E data = tempNode.next.data;
		tempNode.next = null;
		size--;
		return data;
	}
	
	E removeAt(int position) {
		if( position == 0 ) {
			return removeAtFirst();
		} else if( position == size -1 ) {
			return removeAtLast();
		} else {
			Node<E> tempNode = getNodeAt(position-1);
			System.out.println("Temp Node data ^^^^^^ " + tempNode.data);
			E data = tempNode.next.data;
			System.out.println("Reomoval Node data ^^^^^^ " + data);
			tempNode.next = tempNode.next.next;
			size--;
			return data;
		}
	}

	
	public String toString() {
		if( size == 0 ) {
			return "";
		} else {
			StringBuilder output = new StringBuilder();
			Node<E> tempNode = start;
			while( tempNode.next != null ) {
				output.append(tempNode.data).append("==>");
				tempNode = tempNode.next;
			}
			output.append(tempNode.data);
			return output.toString();
		}
	}
	
	
	public static void main(String[] args) {
		BasicSinglyLinkdeList<Object> linkedList = new BasicSinglyLinkdeList<Object>();
		/*linkedList.add(27);
		linkedList.add(94);
		linkedList.add(6);
		linkedList.add(33);
		System.out.println("Before Remove :: " + linkedList);
		linkedList.removeAt(3);*/
		linkedList.add("a");
		linkedList.add("b");
		linkedList.add("c");
		linkedList.add("d");
		linkedList.add("e");
		linkedList.add("f");
		linkedList.add("g");
		linkedList.add("e");
		linkedList.add("h");
		linkedList.add("h");

		/*linkedList.add(7);
		linkedList.add(3);
		linkedList.add(8);
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(6);
		linkedList.add(1);
		linkedList.add(4);
		linkedList.add(9);*/

		/*linkedList.add('m');
		linkedList.add('a');
		linkedList.add('d');
		linkedList.add('a');
		linkedList.add('m');
		linkedList.add('s');*/

		/*linkedList.add(11);
		linkedList.add(2);
		linkedList.add(5);
		linkedList.add(21);
		linkedList.add(3);
		linkedList.add(1);
		linkedList.add(4);
		linkedList.add(31);
		linkedList.insertAtLastForLoop(15);*/
		System.out.println(linkedList);

		//linkedList.insertAt(2, "b");
		//linkedList.deleteNode("c");
		//linkedList.removeDuplicateNodes();
		linkedList.removeDuplicateNodesNoBuffer();
		//linkedList.nthToLastRecursive(linkedList.start, 2);
		//System.out.println(linkedList.nthToLastIterative(3));
		//linkedList.partion(n, 5);
		//System.out.println("Point to the start of the loop. " + linkedList.findBegining());

		//System.out.println("Is a palindrome ?? " + (linkedList.isPalindrome() ? "Yes" : "No"));
		//linkedList.reverse();
		//linkedList.reverse1();
		//linkedList.reverseRecursive(); // Not working
		//linkedList.reverseIterative();
		//linkedList.isLinkedListHasALoop();
		System.out.println(linkedList);
		
	}
	
	/*
	 * An easy way to detect if a linked list has a loop is through 
	 * the FastRunner / Slow Runner approach. Fast runner moves 
	 * two steps at a time, while Slow Runner moves one step.  
	 */
	boolean isLinkedListHasALoop() {
		Node<E> slow = start;
		Node<E> fast = start;
		
		/*
		 * Find the meeting point. This will be LOOP_SIZE - k 
		 * steps into the linked list
		 */
		while( fast != null && fast.next != null ) {
			slow = slow.next;
			fast = fast.next.next;
			if( slow == fast ) { // collision, so it has a loop
				System.out.println("There is a loop in the linked list >> " +
						slow.data + " : " + fast.data);
				return true;
			}
		} // end of while loop
		
		/*
		 * Error check - no meeting point, and therefore no loop
		 */
		if( fast == null || fast.next == null ) {
			return false;
		}
		
		/**
		 * Move slow to Head. Keep fast at meeting point. 
		 * Each are at k steps from the Loop start. 
		 * If they move at the same pace, they must meet at Loop start. 
		 */
		slow = start;
		while( slow != fast ) {
			slow = slow.next;
			fast = fast.next;
			System.out.println("#### slow.data :: " + slow.data + "; fast.data :: " + fast.data);
		}
		
		return false;
	}
	
	
	Node<E> findANode(E data) {
		Node<E> n = start;
		
		if( n.data == data ) {
			return n;
		}
		
		while ( n.next != null ) {
			E findNodeData = n.next.data;
			if( findNodeData == data ) {
				return n.next;
			}//end of if loop
			n = n.next;
		}//end of while
		return null;
	}
	
	//Cracking the code book - solutions
	void deleteNode(E data) {
		Node<E> n = start;
		
		if( n.data == data ) {
			start = n.next;
			return;
		}
		
		while( n.next != null ) {
			E delData = n.next.data;
			if( delData == data ) {
				n.next = n.next.next;
				System.out.println("n.next for last node ####### " + n.next);
				return;
			} //end of if loop
			n = n.next;
		} //end of while loop
	}
	
	/**
	 * Implement an algorithm to delete a node in the middle of the singly 
	 * Linked list, given only access to that node.
	 */
	void deleteAGivenNode(Node<E> n) {
		
		if( n == null || n.next == null ) return;
		
		Node<E> next = n.next;
		n.data = next.data;
		n.next = next.next;
	}

	/**
	 * Remove duplicate nodes from an unsorted linked list
	 * 
 	 * This solution takes O(N) time, where N is the number of 
 	 * elements in the linkedlist
	 */
	void removeDuplicateNodes() {
		Node<E> previous = null;
		//Map<E,Boolean> table = new HashMap<E, Boolean>();
		List<E> list = new ArrayList<E>();
		Node<E> n = start;
		
		while( n != null && n.next != null ) {
			//if( table.containsKey(n.data) ) {
			if(list.contains(n.data)) {
				previous.next = n.next;
			} else {
				list.add(n.data);
				//table.put(n.data, true);
				previous = n;
			}
			//This is valid only if the tail element is a duplicate one
			//if( n.next.next == null && table.containsKey(n.next.data) ) {
			if( n.next.next == null && list.contains(n.next.data) ) {
				previous.next = null;
			}
			n = n.next;
		} // End of while loop
	}
	
	/**
	 * 
	 * If we don't have a buffer, we can iterate with two pointers: 
	 * current which iterates through the linked list and runner which 
	 * checks all subsequent nodes for duplicates.
	 * 
	 *  This code runs in O(1) space and O(N^2) time. 
	 */
	void removeDuplicateNodesNoBuffer() {
		Node<E> n = start;
		Node<E> current = start;
		
		while( current != null && current.next != null ) {
			/* Remove all future nodes that have the same value */
			Node<E> runner = current;
			while( runner.next != null ) {
				if( runner.next.data == current.data ) {
					runner.next = runner.next.next;
				} else {
					runner = runner.next;
				}
			}// Inner while loop - ends
			
			current = current.next;
		} // Outer while loop - ends
	}

	/**
	 * Recursive approach, but leads to StackOverflow error.
	 * 
	 * Recursive solution - O(n)  space due to the recursive calls
	 */
	int nthToLastRecursive(Node<E> n, int k) {
		n = start;
		int i = nthToLastRecursive(n.next, k) + 1;
		
		if( i == k ){
			System.out.println(n.data);
		}
		return i;
	}
	
	/**
	 * Implement an algorithm to find the kth to last element of singly linked list
	 * 
	 * This algorithm takes O(n) time and O(1) space 
	 */
	E nthToLastIterative(int k) {
		Node<E> n = start;
		if( k <= 0 ) return n.data;
		
		Node<E> p1 = n;
		Node<E> p2 = n;
		
		/*Move p2 forward k nodes in the list */
		for( int i = 0; i < k-1; i++ ) {
			if( p2 == null ) return n.data;
			
			p2 = p2.next;
			System.out.println("Node position and node value " + i + " = " + p2.data);
		}
		
		if( p2 == null ) return null;
		System.out.println("After p2 move forward k nodes : " + p2.data);
		
		/* Now move p1 & p2 at the same speed. When p2 hits the end,  
		 * p1 will be at the right element */
		while( p2.next != null ) {
			p1 = p1.next;
			p2 = p2.next;
		}
		return p1.data;
	}
	
	/**
	 * Implementation to partition a linked list around a value x,
	 * such that all nodes less than x come before all nodes greater
	 * than or equal to x.
	 * 
	 */
	void partion(Node<E> n, int x) {
		n = start;
		Node<E> beforeStartNode = null;
		Node<E> afterStartNode = null;
		
		/* partition list */
		while( n != null ) {
			Node<E> next = n.next; 
			if( ((Integer)n.data) < x ) {
				/* Insert node into start of before list */
				n.next = beforeStartNode;
				beforeStartNode = n;
			} else {
				/* Insert node into front of after list */
				n.next = afterStartNode;
				afterStartNode = n;
			} 
			n = next;
		}// end of while loop....
		
		if( beforeStartNode == null ) {
			return;
		}
		
		/* Find end of before list, and merge the lists */
		Node<E> head = beforeStartNode;
		while( beforeStartNode.next != null ) {
			beforeStartNode = beforeStartNode.next;
		}
		beforeStartNode.next = afterStartNode;
		return;
	}
	
	/**
	 * Given a circular linked list, implement an algorithm which returns 
	 * the node at the beginning of the loop. 
	 * 
	 * This is not working...
	 */
	Node<E> findBegining() {
		Node<E> slow = start;
		Node<E> fast = start;
		
		/*Find meeting point. This will be LOOP_SIZE -k steps into the linked list. */
		while( fast != null && fast.next != null ) {
			slow = slow.next;
			fast = fast.next.next;
			System.out.println("While iterating slow node : " + slow.data 
								+ " $$$$$ fast node : " + fast.data);
			if( slow == fast ) { // Collision
				break;
			}
		} // End of while loop
		
		/* Error check - no meeting point, and therefore no loop */
		if( fast == null || fast.next == null ) {
			System.out.println("Fast node is null ***** ");
			return null;
		}
		
		/* Move slow to Head. Keep fast at the Meeting point. Each are k steps 
		 * from the loop start. If they move at the same pace, 
		 * they must meet at loop start 
		 */
		slow = start;
		while( slow != fast ) {
			slow = slow.next;
			fast = fast.next;
		}
		
		/* Both now point to the start of the loop */
		return fast;
	}
	
	/**
	 * Check whether a linked list is a palindrome or not 
	 * 
	 * 
	 */
	boolean isPalindrome() {
		Node<E> n = start;
		Node<E> fast = n;
		Node<E> slow = n;
		
		Stack<Character> stack = new Stack<Character>();
		
		/*
		 * Push elements from first half of linked list onto stack.
		 * When fast runner( which is moving at 2x speed) reaches the end of
		 * the linked list, then we know we are at the middle. 
		 */
		while( fast != null && fast.next != null ) {
			stack.push((Character)slow.data);
			slow = slow.next;
			fast = fast.next.next;
		}
		System.out.println("Wat abt fast now >>>> " + fast);
		/* Has odd number of elements skip the middle element */
		if( fast != null ) {
			slow = slow.next;
		}
		
		while( slow != null ) {
			int data = stack.pop();
			
			/* If value are different, then it's not a palindrome */
			if( data != ((Character)slow.data).charValue() ) {
				return false;
			}
			slow = slow.next;
		} // end of while loop.
		return true;
	}
	
	/* http://www.technicalypto.com/2010/01/java-program-to-reverse-singly-linked.html */
	void reverse() {
		Node<E> n = start;
		
		Node<E> nearNode = n;
		Node<E> midNode = null;
		Node<E> farNode = null;
		
		midNode = nearNode.next;
		farNode = midNode.next;
		
		nearNode.next = null;
		while( farNode != null ) {
			midNode.next = nearNode;
			nearNode = midNode;
			midNode = farNode;
			farNode = farNode.next;
		}
		midNode.next = nearNode;
		start = midNode;
	}
	
	/* My Implementation */
	void reverse1() {
		Node<E> n = start;
		Stack<E> stack = new Stack<E>();
		BasicSinglyLinkdeList<E> linklist = new BasicSinglyLinkdeList<E>();
		while ( n != null ) {
			stack.push(n.data);
			n = n.next;
		}

		while(stack.size() > 0) {
			linklist.add(stack.pop());
		}
		System.out.println(linklist);
	}
	
	/* Elements of programming interview - approach 1 
	  This is not working.
	 */
	void reverseRecursive() {
		Node<E> n = start;
		System.out.println("Begin of reverseRecursive ??? " + n.data);
		if( n != start  || n.next != start ) return;
		
		reverseRecursive();
		System.out.println("After recurssion !!! " + n.data);
		n.next.next = n;
		n.next = null;
	}
	
	/* Elements of programming interview - approach 2 */ 
	void reverseIterative() {
		//Node<E> n = start;
		
		Node<E> prev = null, curr = start;
		
		while( curr != null ) {
			//System.out.println("Current : " + curr.data + " curr.next : " + (curr.next != null ? curr.next.data : curr.next));
			Node<E> temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
//			System.out.println("Current : " + (curr != null ? curr.data : curr) + " Temp : " + (temp != null ? temp.data : temp)+ " prev : " +( prev != null ? prev.data : prev )+ " curr.next : " + (curr.next != null ? curr.next.data : curr.next));
//			System.out.println("Current : " + (curr != null ? curr.data : curr) + " Temp : " + (temp != null ? temp.data : temp)+ " prev : " +( prev != null ? prev.data : prev ));
		}
		start = prev;
	}
	
	static class Node<E> {
		Node<E> next = null;
		E data;
	}
	
	

}
