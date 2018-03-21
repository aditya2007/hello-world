package com.java.core.dsl.sort;

import com.java.core.dsl.util.DslHelper;

public class SelectionSort {

	
	
	private long[] arr;
	private int nOfElems;
	
	public SelectionSort(int length) {
		arr = new long[length];
		nOfElems = 0;
	}
	
	public void insert(long val) {
		arr[nOfElems] = val;
		nOfElems++;
	}
	
	/* Number of comparison is O(N^2) and number of swaps is O(N) */
	public void selectionSort() {
		int i, j, min,compareCnt = 0, swapCnt = 0;
		
		for( i = 0; i < nOfElems-1; i++ ) {
			min = i;
			for( j = i+1; j < nOfElems; j++ ) {
				compareCnt++;
				if( arr[j] < arr[min] ) {
					min = j;
				}
			} // end of inner for loop
			swapCnt++;
			DslHelper.swap(arr, i, min);
		} // end of outer for loop
		System.out.println("Number of comparision : " + compareCnt 
				+ " and Number of Swaps : " + swapCnt
				+ " for array of elements : " + arr.length);

	}
	
	public static void main(String[] args) {
		
		SelectionSort selSort = new SelectionSort(10);
		selSort.insert(77);
		selSort.insert(99);
		selSort.insert(44);
		selSort.insert(55);
		selSort.insert(22);
		selSort.insert(88);
		selSort.insert(11);
		selSort.insert(00);
		selSort.insert(66);
		selSort.insert(33);
		
		DslHelper.print(selSort.arr);
		selSort.selectionSort();
		DslHelper.print(selSort.arr);
	}
	

}
