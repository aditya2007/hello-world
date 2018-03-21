package com.java.core.dsl.sort;

/**
 * 
 * Pros:
 * 1) Works in O(nlogn) time.
 *	2) Worst case of merge sort is equal to best case of a quick sort! (We shall discuss more about in the coming sections).

 * 	Cons:
 *	1) Consumes extra space.
 *	2) Due to the recursive nature of the algorithm, may eat up stack.
 *
 * Time taken to sort a million elements : 247 milliseconds
 */
public class MergeSort {
	
	public static int SIZE = 10;

    public static void main(String args[])
    {
        MergeSort mSort = new MergeSort();

       /* int[] a = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            a[i] = (int) (Math.random() * SIZE);
        } */
        
       // int[] a = {64,21,33,70,12,85,44,3,99,0,108,36};
        int[] a = {64,21,33,70,12};
        
		System.out.println("Un Sorted array of elements :::: ");
		mSort.print(a);

        long start = System.currentTimeMillis();
        mSort.sort(a);
        long end = System.currentTimeMillis();
        System.out.println("Time taken to sort a " + SIZE +" elements in "
        							+ (end - start) + " milliseconds");
        
		System.out.println("Sorted array of elements .... ");
		mSort.print(a);
    }

    private int[] sort( int[] input ) {
    	
    	int[] temp = new int[input.length];
    	mergeSort(input, temp, 0, input.length - 1);
    	return input;
    }

    private void mergeSort(int[] fromArray, int[] toArray, int left, int right) {
    	
    	if( left < right ) {
    		int center = (left + right) / 2;
    		System.out.println("leftPos : " + left + ", center : " 
    										+ center + ", right : " + right);
    		mergeSort(fromArray, toArray, left, center);
    		System.out.println("After 1st recursive call ....... ");
    		System.out.println("leftPos : " + left + ", center : " 
					+ center + ", right : " + right);

    		mergeSort(fromArray, toArray, center+1, right);
    		System.out.println("After 2nd recursive call ....... ");
    		System.out.println("leftPos : " + left + ", center : " 
					+ center + ", right : " + right);

    		merge(fromArray, toArray, left, center+1, right);
    	}
    }
    
    
	private void merge(int[] fromArray, int[] toArray, int leftPos,
			int rightPos, int rightEnd) {
		System.out.println("Inside Merge :: leftPos : " + leftPos + ", rightPos : " 
				+ rightPos + ", rightEnd : " + rightEnd);

		int leftEnd = rightPos - 1;
		int tempPos = leftPos;

		int n = rightEnd - leftPos + 1;
		System.out.println("Number of elements >>>>> " + n);

		while (leftPos <= leftEnd && rightPos <= rightEnd) {
			if (fromArray[leftPos] < fromArray[rightPos]) {
				toArray[tempPos++] = fromArray[leftPos++];
			} else {
				toArray[tempPos++] = fromArray[rightPos++];
			}
		}

		while (leftPos <= leftEnd) {
			toArray[tempPos++] = fromArray[leftPos++];
		}
		while (rightPos <= rightEnd) {
			toArray[tempPos++] = fromArray[rightPos++];
		}

		for (int i = 0; i < n; i++, rightEnd--) {
			fromArray[rightEnd] = toArray[rightEnd];
		}
		/*for( int i = 0; i < numElements; i++ ) {
			fromArray[leftPos++] = toArray[i];
		}*/
	}
    
    
	private void print(int[] input) {
		StringBuilder builder = new StringBuilder();
		
		for( int i = 0; i < input.length; i++ ) {
			builder.append(input[i]).append(",");
		}
		System.out.println(builder);
	}

}
