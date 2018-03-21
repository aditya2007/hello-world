package com.java.core.dsl.arrays;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Merge2SortArrays {
	
	public static void main(String[] args) {
		int[] arrA = {23,87,81,95};
		int[] arrB = {7,14,39,55,62,74};
		int[] arrC = new int[arrA.length + arrB.length];
		
		//mergeSortedArrays(arrA, arrB, arrC);
		//mergeUnSortedArrays(arrA, arrB, arrC);
		//print(arrC);
		//print(arrA);
		//print(arrB);
		//mergeArrays(arrA, arrB, 4, arrB.length);
		//print(arrA);

		List<Integer> listA = new LinkedList<>();
		listA.add(20);
		listA.add(40);
		listA.add(60);
		listA.add(80);
		listA.add(69);
		listA.add(74);
		listA.add(100);

		List<Integer> listB = new LinkedList<>();
		listB.add(10);
		listB.add(30);
		listB.add(50);
		listB.add(70);
		listB.add(90);
		//System.out.println(mergeUnSortedLists(listA, listB));
		//System.out.println(mergeSortedLists(listA, listB));
		testMergeTwoSortedLinkedList();
		//findIntersection();
		//findIntersectionLeetCode();
	}
	
	//Merge 2 sorted array and produce one array.
	static void mergeSortedArrays( int[] arrA, int[] arrB, int[] arrC ) {
		int aIdx = 0, bIdx = 0, cIdx = 0;
		
		while( aIdx < arrA.length && bIdx < arrB.length ) {
			if( arrA[aIdx] < arrB[bIdx] ) {
				arrC[cIdx++] = arrA[aIdx++];
			} else {
				arrC[cIdx++] = arrB[bIdx++];
			} // end of if/else condition
		} //end of while loop
		
		while( aIdx < arrA.length ) {
			arrC[cIdx++] = arrA[aIdx++];
		} 
		
		while( bIdx < arrB.length ) {
			arrC[cIdx++] = arrB[bIdx++];
		}
	} // end of the method

    //Merge 2 Unsorted array and produce one array.
    static void mergeUnSortedArrays( int[] arrA, int[] arrB, int[] arrC ) {
		System.out.println("Array C  length >> " + arrC.length);
		int aIdx = 0, bIdx = 0, cIdx = 0;

		while (aIdx < arrA.length && bIdx < arrB.length) {
		//while (cIdx < arrC.length) {
			if (aIdx < arrA.length - 1 && arrA[aIdx] > arrA[aIdx + 1]) {
				swap(arrA, aIdx);
			}
			if (bIdx < arrB.length - 1 && arrB[bIdx] > arrB[bIdx + 1]) {
				swap(arrB, bIdx);
			}

			if (arrA[aIdx] < arrB[bIdx]) {
				arrC[cIdx++] = arrA[aIdx++];
			} else {
				arrC[cIdx++] = arrB[bIdx++];
			} // end of if/else condition
		} //end of while loop

		while (aIdx < arrA.length) {
			arrC[cIdx++] = arrA[aIdx++];
		}

		while (bIdx < arrB.length) {
			arrC[cIdx++] = arrB[bIdx++];
		}
	} // end of the method

	private static void swap(int[] arr, int idx) {
		int tmp = arr[idx];
		arr[idx] = arr[idx + 1];
		arr[idx + 1] = tmp;
	}

	static void mergeArrays(int[] a, int[] b, int lastA, int lastB) {
		int idxA = lastA - 1; //index of the last element in array a
		int idxB = lastB - 1;//index of the last element in array b
		int idxMerged = lastB + lastA - 1; //end of merged array
		
		//Merge a & b , starting from the last element in each
		while( idxB >= 0 ) {
			//end of a is > than end of b
			if( idxA >= 0 && a[idxA] > b[idxB] ) {
				a[idxMerged] = a[idxA]; //Copy element
				idxA--;
			} else {
				a[idxMerged] = b[idxB]; //copy element
				idxB--;
			}
			idxMerged--; //move indicieskl, hvvftgfg
		}
		
	}

	static List<Integer> mergeSortedLists(List<Integer> listA, List<Integer> listB) {
		System.out.println(listA);
		System.out.println(listB);
		int idxA = 0; //index of the first element in List A
		int idxB = 0;//index of the first element in list B
		List<Integer> listC = new LinkedList<>();


		//Merge a & b , starting from the last element in each
		while (idxA < listA.size() && idxB < listB.size()) {
			//end of a is > than end of b
			if (listA.get(idxA) < listB.get(idxB)) {
				listC.add(listA.get(idxA));
				idxA++;
			} else {
				listC.add(listB.get(idxB));
				idxB++;
			}
		}

		while (idxA < listA.size()) {
			listC.add(listA.get(idxA));
			idxA++;
		}

		while (idxB < listB.size()) {
			listC.add(listB.get(idxB));
			idxB++;
		}
		return listC;
	}

	static List<Integer> mergeUnSortedLists(List<Integer> listA, List<Integer> listB) {
		System.out.println(listA);
		System.out.println(listB);
		int idxA = 0; //index of the first element in List A
		int idxB = 0;//index of the first element in list B
		List<Integer> listC = new LinkedList<>();


		//Merge a & b , starting from the last element in each
		while (idxA < listA.size() && idxB < listB.size()) {
			//end of a is > than end of b
			if (idxA < listA.size() - 1 && listA.get(idxA) > listA.get(idxA + 1)) {
				swap(listA, idxA);
			}
			if (idxB < listB.size() - 1 && listB.get(idxB) > listB.get(idxB + 1)) {
				swap(listB, idxB);
			}

			if (listA.get(idxA) < listB.get(idxB)) {
				listC.add(listA.get(idxA));
				idxA++;
			} else {
				listC.add(listB.get(idxB));
				idxB++;
			}
		}

		while (idxA < listA.size()) {
			listC.add(listA.get(idxA));
			idxA++;
		}

		while (idxB < listB.size()) {
			listC.add(listB.get(idxB));
			idxB++;
		}
		return listC;
	}

	private static void swap(List<Integer> list, int idx) {
		int tmp = list.get(idx);
		list.set(idx,list.get(idx + 1));
		list.set(idx + 1, tmp);
	}

	//My approach
	private static void findIntersection() {
		int[] input1 = {1,3,4,8,9,12};
		int[] input2 = {2,4,9,15};
		List<Integer> list = new ArrayList<Integer>();
		List<Integer> result = new ArrayList<Integer>();
		
		//Map<Integer, Boolean> map =new HashMap<Integer, Boolean>();
		for( int i = 0; i < input2.length; i++ ) {
			list.add(input2[i]);
		}
		
		for( int j = 0; j < input1.length; j++ ) {
			if( list.contains(input1[j]) ) {
				result.add(input1[j]);
			}
		}
		
		print(result.toArray(new Integer[result.size()]));
	}
	
	private static void findIntersectionLeetCode() {
		int[] input1 = {1,3,4,8,9,12};
		int[] input2 = {2,4,9,15};
		List<Integer> result = new ArrayList<Integer>();
		
		int idx1 = 0;
		int idx2 = 0;
		int arr1Length = input1.length;
		int arr2Length = input2.length;
		
		while (idx1 < arr1Length && idx2 < arr2Length ) {
			if ( input1[idx1] > input2[idx2] ) {
				idx2++;
			} else if ( input2[idx2] > input1[idx1] ) {
				idx1++;
			} else {
				result.add(input1[idx1]);
				idx1++;
				idx2++;
			}
		}
		
		print(result.toArray(new Integer[result.size()]));
	}

	static void testMergeTwoSortedLinkedList() {
		ListNode node1 = new ListNode(1);
		ListNode node1a = new ListNode(2);
		ListNode node1b = new ListNode(4);

		ListNode node2 = new ListNode(1);
		ListNode node2a = new ListNode(3);
		ListNode node2b = new ListNode(4);
		ListNode node2c = new ListNode(5);

		node1b.next = null;
		node1a.next = node1b;
		node1.next = node1a;

		node2c.next = null;
		node2b.next = node2c;
		node2a.next = node2b;
		node2.next = node2a;


		ListNode mergedNode = mergeTwoSortedLinkedLists(node1, node2);
		System.out.println(mergedNode);
	}

	/**
	 * Merge two sorted linked lists and return it as a new list.
	 * The new list should be made by splicing together the nodes of the first two lists.

	 Example:
		 Input: 1->2->4, 1->3->4
		 Output: 1->1->2->3->4->4
	 */
	static ListNode mergeTwoSortedLinkedLists(ListNode l1, ListNode l2) {
		ListNode mergedNode = null;
		ListNode currNode1 = l1;
		ListNode currNode2 = l2;

		while (currNode1 != null && currNode2 != null) {
			ListNode tempNode;
			if (currNode1.val < currNode2.val) {
				tempNode = new ListNode(currNode1.val);
				currNode1 = currNode1.next;
			} else {
				tempNode = new ListNode(currNode2.val);
				currNode2 = currNode2.next;
			}
			if (mergedNode == null) {
				mergedNode = tempNode;
			} else {
				ListNode currNode = mergedNode;
				while (currNode.next != null) {
					currNode = currNode.next;
				}
				currNode.next = tempNode;
			}
		} // end of while loop

		while (currNode1 != null) {
			ListNode tempNode = new ListNode(currNode1.val);
			currNode1 = currNode1.next;

			ListNode currNode = mergedNode;
			if (currNode != null) {
				while (currNode.next != null) {
					currNode = currNode.next;
				}
				currNode.next = tempNode;
			}
		}

		while (currNode2 != null) {
			ListNode tempNode = new ListNode(currNode2.val);
			currNode2 = currNode2.next;

			ListNode currNode = mergedNode;
			if (currNode != null) {
				while (currNode.next != null) {
					currNode = currNode.next;
				}
				currNode.next = tempNode;
			}
		}

		return mergedNode;
	}

	static class ListNode {
		int val;
		ListNode next;
		ListNode(int x) {
		val = x;
		}
  	}

	static void print(Integer[] result) {
		for( Integer elmnt : result ){
			System.out.print(elmnt + ",");
		}
	}

}
