package com.java.core.dsl.arrays;

public class NormalArrayImpl {
	
	private long[] arr;
	private int nOfElems;
	
	public NormalArrayImpl(int maxLength) {
		arr = new long[maxLength];
		nOfElems = 0;
	}
	
	public boolean find( long val ) {
		int j;
		
		for( j = 0; j < nOfElems; j++ ) {
			if( val == arr[j] ) return true;
		}
		
		if( j == nOfElems ) return false; // reached the end of the array.
		
		return false;
	}
	
	public void insert( long val ) {
		arr[nOfElems++] = val;
	}
	
	public void delete( long val ) {
		int j;
		
		for( j = 0; j < nOfElems; j++ ) {
			if( val == arr[j] ) break;
		}
		
		//System.out.println(" The position of val " + val + " is " + j);
		for( int k = j; k < nOfElems-1; k++ ) {
//			System.out.println(" arr[" + k + "] = " + arr[k] + ", " +
//					"	arr[" + (k+1) + "] = " + arr[k+1]);
			arr[k] = arr[k+1];
		}
		nOfElems--;
	}

	public void print() {
		for( int j = 0; j < nOfElems; j++ ) {
			System.out.print(arr[j]  + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		NormalArrayImpl arr = new NormalArrayImpl(10);
		
		arr.insert(84);
		arr.insert(61);
		arr.insert(15);
		arr.insert(73);
		arr.insert(26);
		arr.insert(38);
		arr.insert(11);
		arr.insert(49);
		arr.insert(53);
		arr.insert(32);
		
		arr.print();
		
		long delVal = 84;
		System.out.println(" Before Delete : Is key 26 present in the array : " + (arr.find(26) ? "Yes" : "No"));
		System.out.println(" Before Delete : Is key "+ delVal +" present in the array : " + (arr.find(delVal) ? "Yes" : "No"));
		
		arr.delete(delVal);
		arr.print();
		
		System.out.println(" After Delete : Is key 26 present in the array : " + (arr.find(26) ? "Yes" : "No"));
		System.out.println(" After Delete : Is key "+ delVal +" present in the array : " + (arr.find(delVal) ? "Yes" : "No"));

		
	}
}
