package com.java.core.dsl.util;

public class DslHelper {
	
	public static void print(long[] input) {
		StringBuilder builder = new StringBuilder();
		
		for( int i = 0; i < input.length; i++ ) {
			builder.append(input[i]).append(",");
		}
		System.out.println(builder);
	}
	
	public static void print(int[] input) {
		StringBuilder builder = new StringBuilder();
		
		for( int i = 0; i < input.length; i++ ) {
			builder.append(input[i]).append(",");
		}
		System.out.println(builder);
	}


	public static void print(long[] input, int nOfElms) {
		StringBuilder builder = new StringBuilder();
		
		for( int i = 0; i <= nOfElms; i++ ) {
			builder.append(input[i]).append(",");
		}
		System.out.println(builder);
	}

	public static void swap(long[] arr, int idx1, int idx2 ) {
		long temp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = temp;
	}

	public static void main(String[] args) {
		DslHelper helper1 = new DslHelper();
		DslHelper helper2 = new DslHelper();
		DslHelper helper3 = helper1;
		System.out.println("Testing object equality begin.....");
		if( helper1 == helper2 ) {
			System.out.println("helper1 == helper2");
		}
		
		if( helper1.equals(helper2) ) {
			System.out.println("helper1.equals(helper2)");
		}
		
		if( helper1 == helper3 ) {
			System.out.println("helper1 == helper3");
		}
		
		if( helper1.equals(helper3) ) {
			System.out.println("helper1.equals(helper3)");
		}

		System.out.println("Testing object equality end.....");
	}
}
