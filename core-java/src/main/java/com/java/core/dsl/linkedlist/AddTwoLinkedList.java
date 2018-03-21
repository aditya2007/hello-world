package com.java.core.dsl.linkedlist;

import com.java.core.dsl.linkedlist.BasicSinglyLinkdeList.Node;

public class AddTwoLinkedList {
	
	public static void main(String[] args) {
		BasicSinglyLinkdeList<Integer> linkedList1 = new BasicSinglyLinkdeList<Integer>();
		BasicSinglyLinkdeList<Integer> linkedList2 = new BasicSinglyLinkdeList<Integer>();
		
		linkedList1.add(2);
		linkedList1.add(4);
		linkedList1.add(3);
		System.out.println(linkedList1);
		
		linkedList2.add(5);
		linkedList2.add(6);
		linkedList2.add(4);
		System.out.println(linkedList2);
		
		System.out.println("Modulus of 16%10 = " + (16 % 10) + "; Division of = " + (16/10));
		add2LinkedList(linkedList1,linkedList2);
	}
	
	private static void add2LinkedList(BasicSinglyLinkdeList<Integer> linkedList1, 
										BasicSinglyLinkdeList<Integer> linkedList2 ) {
	       int carry = 0;
	       
	       BasicSinglyLinkdeList<Integer> resultList = new BasicSinglyLinkdeList<Integer>();
	       Node<Integer> p1 = linkedList1.start, p2 = linkedList2.start;
	 
	        while(p1 != null || p2 != null){
	            if(p1 != null){
	                carry += p1.data;
	                p1 = p1.next;
	            }
	 
	            if(p2 != null){
	                carry += p2.data;
	                p2 = p2.next;
	            }
	 
	            resultList.add(carry%10);
	            carry /= 10;
				System.out.println("Carry/10 ===> " + carry);
			}//end of while
	 
	        if(carry==1) {
	        	resultList.add(1);
	        }
	        System.out.println(resultList);
		}
	}


