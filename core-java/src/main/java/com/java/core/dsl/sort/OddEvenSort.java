package com.java.core.dsl.sort;

import com.java.core.dsl.util.DslHelper;

public class OddEvenSort {

	public static void main(String[] args) {
		int[] evenodd = {3,8,12,5,9,21,6,11,44, 91, 22, 7,4,33};
		
		evenodd(evenodd);
		DslHelper.print(evenodd);
	}
	
	public static void evenodd(int[] evenodd) {

	    int i = 0;
	    int j = evenodd.length - 1;

	    while (j >= i) {
	        // swap if found odd even combo at i and j
			if (evenodd[i] % 2 == 1 && evenodd[j] % 2 == 0) {
	            int temp = evenodd[i];
	            evenodd[i] = evenodd[j];
	            evenodd[j] = temp;
	            i++;
	            j--;

	        } else {
	            if (evenodd[i] % 2 == 0) {
	                i++;
	            }
	            if (evenodd[j] % 2 == 1) {
	                j--;
	            }
	        } // end of else loop
	    } //end of while loop
	    
	} // end of evenodd
}
