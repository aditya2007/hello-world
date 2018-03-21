package com.java.core.dsl.sort;

public class BubbleSort {
	
	private final static int SIZE = 10;
	
	public static void main(String[] args) {
		BubbleSort bSort = new BubbleSort();
		
		int[] a = new int[SIZE];
		for( int i = 0; i < SIZE; i++ ) {
			a[i] = (int) (Math.random() * SIZE);
			//System.out.println("a[" + i +"] = " + a[i]);
		}
		
		//int[] aa = {77,99,44,55,22,88,11,00,66,33};
		System.out.println("Un Sorted array of elements :::: ");
		bSort.print(a);
		
		long start = System.currentTimeMillis();
		bSort.sort(a);
		
		//bSort.bubbleSort(a);
		long end = System.currentTimeMillis();
		
		System.out.println("Time taken to sort a "+ a.length + " elements : "
			    								+ (end - start) + " milliseconds");
		
		System.out.println("Sorted array of elements .... ");
		bSort.print(a);
	}
	

	/* Internet site */
	private int[] sort(int[] input) {
		int temp,compareCnt = 0, swapCnt = 0;

		for( int i = 0; i < input.length; i++ ) {
			for( int j = i+1; j < input.length; j++ ) {
				compareCnt++;
				if( input[i] > input[j] ) {
					swapCnt++;
					temp = input[i];
					input[i] = input[j];
					input[j] = temp;
				} // end of if loop
			} // end of inner for loop
		}// end of outer for loop
		System.out.println("###Number of comparision : " + compareCnt 
				+ " and Number of Swaps : " + swapCnt
				+ " for array of elements : " + input.length);
		return input;
	}

	/* DSL book */
	private int[] bubbleSort(int[] input) {
		int i, j, compareCnt = 0, swapCnt = 0;
		
		for( i = input.length - 1; i > 1; i--) {
			for( j = 0; j < i; j++ ) {
				compareCnt++;
				if( input[j] > input[j+1] ) {
					swapCnt++;
					int temp = input[j];
					input[j] = input[j+1];
					input[j+1] = temp;
				}
			}
		}
		System.out.println("@@@Number of comparision : " + compareCnt 
							+ " and Number of Swaps : " + swapCnt
							+ " for array of elements : " + input.length);
		return input;
	}
	
	
	private void print(int[] input) {
		StringBuilder builder = new StringBuilder();
		
		for( int i = 0; i < input.length; i++ ) {
			builder.append(input[i]).append(",");
		}
		System.out.println(builder);
	}
}
