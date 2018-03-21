package com.java.core.dsl.arrays;

/*http://maleskoding.wordpress.com/tag/binary-tree/  This is for graphs and binary tree*/
public class BinarySearchOnOrderedArray {
	
	private long[] arr;
	private int nOfElms;
	
	public BinarySearchOnOrderedArray(int length) {
		arr = new long[length];
		nOfElms = 0;
	}
	
	public int binarySearch(long val) {
		int lowIdx = 0;
		int highIdx = nOfElms - 1;
		int currIdx;
		int counter = 1;
		
		while( true ) {
			System.out.println("Number of Times/Comparision >>>>> " + counter);
			currIdx = (lowIdx + highIdx) / 2;
			//System.out.println("Current index : "+ currIdx + " Low Index : " + lowIdx + "  High Index : " + highIdx);
			
			if( arr[currIdx] == val ) {
				return currIdx;
				//Eventually, the range will get so small that it can't be divided any more
				// If lower bound is greater than the upper bound than the search 
				// has ceased to exist
			} else if( lowIdx > highIdx ) { 
				 return -1;
			} else {
				if ( val > arr[currIdx] ) {
					lowIdx = currIdx + 1;
				} else {
					highIdx = currIdx - 1;
				}
			}
			counter++;
		} // end of while loop
	}
	
	
	/*
	 * After insert(linear search to find- the order position)
	 * move up the higher values 
	 */
	public void insert(long val) {
		int i;
		
		for( i = 0; i < nOfElms; i++ ) {
			if( arr[i] > val ) break;
			//System.out.println("Index i : " + i + ", for input val : " + val);
		}

//		System.out.println("Index i : " + i + ", for input val : " 
//							+ val + ", no of elmns : " + nOfElms);
		for( int k = nOfElms; k > i; k-- ) {
			arr[k] = arr[k-1];
		}
		
		arr[i] = val;
		//print();
		nOfElms++;
	}
	
	/*
	 * Binary search to find the deleting element
	 */
	public boolean delete(long val) {
		int i = binarySearch(val);
		
		if( i == -1 ) { 
			return false;
		} else { //move bigger one down
			for( int k = i; k < nOfElms; k++ ) {
				arr[k] = arr[k+1];
			}
		}
		nOfElms--;
		return true;
	}
	
	
	public void print() {
		for( int j = 0; j < nOfElms; j++ ) {
			System.out.print(arr[j]  + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		BinarySearchOnOrderedArray search = new BinarySearchOnOrderedArray(11);
		search.insert(248);
		search.insert(311);
		search.insert(34);
		search.insert(427);
		search.insert(287);
		search.insert(13);
		search.insert(585);
		search.insert(915);
		search.insert(386);
		search.insert(885);
		//search.insert(34);
		
		search.print();
		
		long val = 287;
//		System.out.println("Is the given value : " +  val 
//				+ " found " + (search.binarySearch(val) > 1 ? "Yes" : "No"));
		
		System.out.println("Is the given value : " +  val 
				+ " found  and deleted " + (search.delete(val) ? "Yes" : "No"));
		
		search.print();

		/*search.insert(6);
		search.insert(8);
		search.insert(10);
		search.insert(427);
		search.insert(487);
		search.insert(513);
		search.insert(585);
		search.insert(615);
		search.insert(686);
		search.insert(785);
		search.insert(834);*/
	}

}
