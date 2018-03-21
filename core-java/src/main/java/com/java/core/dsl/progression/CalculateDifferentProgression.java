package com.java.core.dsl.progression;

public class CalculateDifferentProgression {

	/**
	 * Given a sorted array of distinct positive integers, print all triplets that form AP (or Arithmetic Progression)
	 *
		 Example :
		  Input : arr[] = { 2, 6, 9, 12, 17, 22, 31, 32, 35, 42 };
		 Output :
		 6 9 12
		 2 12 22
		 12 17 22
		 2 17 32
		 12 22 32
		 9 22 35
		 2 22 42
		 22 32 42

		 Input : arr[] = { 3, 5, 6, 7, 8, 10, 12};
		 Output :
		 3 5 7
		 5 6 7
		 6 7 8
		 6 8 10
		 8 10 12

		 An efficient solution is based on the fact that array is sorted.
		 We use the same concept as discussed in GP triplet question.
		 The idea is to start from the second element
		 and fix every element as middle element and search for the other two elements
		 in a triplet (one smaller and one greater).
	 */
	static void findAllAPTriplets(int[] input) {

		for (int i = 1; i < input.length - 1; i++) {
			//Search other two elements in of AP with arr[i] as middle
			for (int j = i-1, k = i+1; j >= 0 && k < input.length;) {
				//if a triplet is found
				if (input[j] + input[k] == 2 * input[i]) {
					System.out.println(input[j] + ", " + input[i] + ", " + input[k]);

					//Since elements are distinct,input[k] and input[j] cannot form anymore triplets with input[i]
					k++;
					j--;
				} // If middle element is greater than other 2 elements sum,
				// then move upper side otherwise move lower side
				else if ((2 * input[i]) > (input[j] + input[k])) {
					k++;
				} else {
					j--;
				}
			} // end of inner loop
		} // end of
	} //end of outer for loop

	public static void main(String[] args) {
		//int[] input = {2, 6, 9, 12, 17, 22, 31,32, 35, 42};
		//findAllAPTriplets(input);
		int[] input = {1, 2, 6, 10, 18, 54};
		findAllGPTriplets(input);
	}

	/**
		 Given a sorted array of distinct positive integers,
		 print all triplets that forms Geometric Progression with integral common ratio.

		 A geometric progression is a sequence of numbers where each term after the first
		 is found by multiplying the previous one by a fixed, non-zero number called the common ratio.
		 For example, the sequence 2, 6, 18, 54,… is a geometric progression with common ratio 3.

	 Examples:

		 Input:
		 arr = [1, 2, 6, 10, 18, 54]
		 Output:
		 2 6 18
		 6 18 54

		 Input:
		 arr = [2, 8, 10, 15, 16, 30, 32, 64]
		 Output:
		 2 8 32
		 8 16 32
		 16 32 64

		 Input:
		 arr = [ 1, 2, 6, 18, 36, 54]
		 Output:
		 2 6 18
		 1 6 36
		 6 18 54
	 */
	static void findAllGPTriplets(int[] arr) {
		// One by fix every element as middle element
		for (int j = 1; j < arr.length - 1; j++)
		{
			// Initialize i and k for the current j
			int i = j - 1, k = j + 1;

			// Find all i and k such that (i, j, k)
			// forms a triplet of GP
			while (i >= 0 && k <= arr.length - 1)
			{
				// if arr[j]/arr[i] = r and arr[k]/arr[j] = r
				// and r is an integer (i, j, k) forms Geometric
				// Progression
				while (arr[j] % arr[i] == 0 &&
						arr[k] % arr[j] == 0 &&
						arr[j] / arr[i] == arr[k] / arr[j])
				{
					// print the triplet
					System.out.println(arr[j] + ", " + arr[i] + ", " + arr[k]);

					// Since the array is sorted and elements
					// are distinct.
					k++;
					i--;
				}

				// if arr[j] is multiple of arr[i] and arr[k] is
				// multiple of arr[j], then arr[j] / arr[i] !=
				// arr[k] / arr[j]. We compare their values to
				// move to next k or previous i.
				if(arr[j] % arr[i] == 0 &&
						arr[k] % arr[j] == 0)
				{
					if(arr[j] / arr[i] < arr[k] / arr[j])
						i--;
					else k++;
				}

				// else if arr[j] is multiple of arr[i], then
				// try next k. Else, try previous i.
				else if (arr[j] % arr[i] == 0)
					k++;
				else i--;
			}
		}
	}
}
