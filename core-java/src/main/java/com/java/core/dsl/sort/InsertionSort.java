package com.java.core.dsl.sort;

import com.java.core.dsl.util.DslHelper;

public class InsertionSort {

	private long[] arr;
	private int nOfElems;
	
	public InsertionSort(int length) {
		arr = new long[length];
		nOfElems = 0;
	}
	
	public void insert(long val) {
		arr[nOfElems] = val;
		nOfElems++;
	}
	
	/* In any case this sort runs in O(N^2) time for random data 
	 * and O(N) for partially sorted data */
	public void insertionSort() {
		int out, in,compareCnt = 0, swapCnt = 0;
		
		for( out = 1; out < nOfElems; out++ ) { // out is dividing the line(partition)
			long temp = arr[out]; // remove marked item
			in = out; //start shifts at out until one is smaller
			compareCnt++;
			while( in > 0 && arr[in-1] >= temp ) {  
				arr[in] = arr[in-1]; //shift item to right
				--in;   // go to left one position
			} // end of while
		arr[in] = temp; //insert marked item	
		} // end of for loop
		System.out.println("Number of comparision : " + compareCnt 
				+ " and Number of Swaps : " + swapCnt
				+ " for array of elements : " + arr.length);
	} // end of insertionSort
	
	public void removeDuplicates() {
		for( int i = 0; i < nOfElems; i++ ) {
			if( arr[i] == arr[i+1] )  {
				delete(i, arr[i]);
			}
		}
	}

	public void removeDuplicatesWithExtraSpace() {
		long[] newArr = new long[arr.length];
		for( int i = 0; i < arr.length-1; i++ ) {
			if( arr[i] != arr[i+1] )  {
				newArr[i] = arr[i];
			}
		}
		DslHelper.print(newArr);
	}

	
	public void delete( int idx, long val ) {
		for( int k = idx; k < nOfElems-1; k++ ) {
			arr[k] = arr[k+1];
		}
		nOfElems--;
		DslHelper.print(arr, nOfElems);
	}
	
	public static void main(String[] args) {
		InsertionSort inSort = new InsertionSort(12);
		inSort.insert(77);
		inSort.insert(99);
		inSort.insert(44);
		inSort.insert(55);
		inSort.insert(22);
		inSort.insert(44);
		inSort.insert(88);
		inSort.insert(11);
		inSort.insert(00);
		inSort.insert(66);
		inSort.insert(77);
		inSort.insert(33);
		
		DslHelper.print(inSort.arr);
		inSort.insertionSort();
		DslHelper.print(inSort.arr);
		//inSort.removeDuplicates();
		//inSort.removeDuplicatesWithExtraSpace();
	}
}
