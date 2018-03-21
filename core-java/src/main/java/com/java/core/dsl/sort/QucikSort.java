package com.java.core.dsl.sort;

/**
 * http://www.technicalypto.com/2010/01/quick-sort-in-java.html
 * Pros:
	1) Efficient average case compared to any algorithm.
	2) Recursive definition and popularity because of its high efficiency.

	Cons:
		1) Worst case scenario sucks.
 * 
 *
 * Quick sort is one of the coolest algorithms available in sorting and its performance 
   is measured most of the time in O(nlogn), although the worst case sorting times 
   of bubble sort and this are equal.
   Inspite its worst case time, this always performs superiorly when the input is 
   completely shuffled/randomized. This is one of the modest sorts available :

	Quick sort is an example of IN-PLACE sort. No extra space is needed as in merge sort
	where we have to use one full extra array to hold all the merged values. 
	The only space that quick sort uses may be the counter variables and the 
	extra space required for swapping
	
 * Time taken to sort a million elements : 156 milliseconds
 */
public class QucikSort {

	public static int SIZE = 10;//1000000;

	public static void main(String args[]) {
		//int[] a = new int[SIZE];
//		for (int i = 0; i < SIZE; i++) {
//			a[i] = (int) (Math.random() * SIZE);
//		}
		
		int[] a = {54,93,23,43,9,78,21,75,13,40};
		
		
		QucikSort qSort = new QucikSort();
		
		System.out.println("Un Sorted array of elements :::: ");
		qSort.print(a);
		
		long start = System.currentTimeMillis();
		qSort.sort(a);
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken to sort a million elements : "
				+ (end - start) + " milliseconds");

		System.out.println("Sorted array of elements :::: ");
		qSort.print(a);
		
	}
	
	public int[] sort(int[] input) {
		quickSort(input, 0, input.length - 1);
		return input;
	}

	private void quickSort(int arr[], int left, int right) {
		System.out.println("Begin : quickSort, left pos : " + left + "; right pos : " + right);
		int index = partition(arr, left, right);
		System.out.println("Index from partition >>>>> " + index);
		if (left < index - 1) {
			System.out.println("LEFT < INDEX-1 GOT INVOKED ^^^^^^^^ ");
			quickSort(arr, left, index - 1);
		}
		if (index < right) {
			System.out.println("INDEX < RIGHT GOT INVOKED ######### ");
			quickSort(arr, index, right);
		}
	}

	private int partition(int arr[], int left, int right) {
		int i = left, j = right;
		int tmp;
		int pivot = arr[(left + right) / 2];
		System.out.println("Pivot value >>>>> " + pivot);
		while (i <= j) {
			while (arr[i] < pivot)
				i++;
			while (arr[j] > pivot)
				j--;
			if (i <= j) {
				tmp = arr[i];
				arr[i] = arr[j];
				arr[j] = tmp;
				i++;
				j--;
			}
			System.out.println("Sort In progress ******* ");
			print(arr);
		}
		return i;
	}
	
	private void print(int[] input) {
		StringBuilder builder = new StringBuilder();
		
		for( int i = 0; i < input.length; i++ ) {
			builder.append(input[i]).append(",");
		}
		System.out.println(builder);
	}

}
