package com.java.core.leetcode;

import java.util.*;

/**
 * Given an array of integers, find two numbers such that they add up to 
 * a specific target number. The function twoSum should return indices of 
 * the two numbers such that they add up to the target, 
 * where index1 must be less than index2. 
 * Please note that your returned answers (both index1 and index2) are not zero-based.

	For example:

	Input: numbers={2, 7, 11, 15}, target=9
	Output: index1=0, index2=1
	
 * @author m755866
 *
 */
public class TwoSum {
	
	public static void main(String[] args) {
		int[] input = {11,4,15,12,8,6};
		//int[] input = {4,3,0,5,8,-1,-3}; // This is for threeSum
		//int[] input = {-1, 0, 1, 2, -1, -4}; // This is for threeSum triplets
		//Arrays.sort(input);
		//printArray(input);
		//int[] input = {1,2,4,7,8,10}; // Only for sorted array.
		int target = 10;
		//int[] result = twoSumBruteForce(input, target);
		//System.out.println((input[result[0]] + input[result[1]]));
		int[] result1 = twoSumLeetCode(input, target);
		System.out.println("The positions are ...... " + result1[0] + "," + result1[1]);
		//int[] result = twoSumOfSortArrayLeetCode(input, target);
		//System.out.println("The positions are ...... " + result[0] + "," + result[1]);
		//boolean isTargetReached = twoSumToZero(input,0);
		//System.out.println("Is target zero reached.... " + isTargetReached);
		//threeSumToZero();
		//threeSum();
		//threeSumTriplets();
		//threeSumTripletsLeetCode();
		//findAllPairsToSum(10);
		/*List<Integer> list = new ArrayList<Integer>();
		list.add(8);
		list.add(18);
		list.add(5);
		list.add(9);
		list.add(3);
		list.add(21);
		int[] solutionArray = { 1, 2, 3, 4, 5, 6, 16, 15, 14, 13, 12, 11 };
		printArray(solutionArray);
		shuffleArray(solutionArray);
		printArray(solutionArray);
		print(list);
		Collections.shuffle(list);
		print(list);*/
	}
	
	//Time complexity of this solution is O(n). + HashMap get/put complexity
	private static int[] twoSumLeetCode(int[] numbers, int target) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < numbers.length; i++) {
			int x = numbers[i];
			if (map.containsKey(target-x) ) {
				return new int[]{map.get(target-x), i};
			} 
			map.put(x, i);
		}// end of for loop
		
		throw new IllegalStateException("");
	}
	
	//O(n) time complexity, O(1) space. if the given input is already sorted
	// Fail in case of sorted array example {-3,-1,0,3,4,5,8} for a target, result i wrong positions
	private static int[] twoSumOfSortArrayLeetCode(int[] nums, int target) {
		int i = 0, j = nums.length - 1;
		while( i < j ) {
			int sum = nums[i] + nums[j];
			if( sum < target ) {
				i++;
			}else if( sum > target ) {
				j--;
			} else {
				return new int[]{i, j};
			}
		}
		throw new IllegalStateException("");
	}
	
	private static boolean twoSumToZero(int[] input, int target) {
		int[] input1 = {4,1,-3,6,8,0,3};
		int sum = 0;
		for( int i = 0; i < input.length-1; i++){
			for( int j = i+1; j < input.length; j++) {
				//System.out.println(i +"=>" + input[i] + " :: " + j + "=>" + input[j+1]);
				sum = input[i] + input[j];
				if( sum == 0) return true;
			}
		}
		return false;
	}

	//Failing with this input {3,7,11,15,2}
	private static int[] twoSumBruteForce(int[] input, int target) {
		for( int i = 0; i < input.length-1; i++ ) {
			int x = input[i];
			for( int j = i+1; j < input.length; j++ ) {
				if( input[j] == (target - x) ) {
					System.out.println(input[j] + "==" + (target-x) );
					return new int[] {i,j};
				}
			}
		}
		throw new IllegalArgumentException();
	}
	
	 static void shuffleArray(int[] ar)
	  {
		 Random random = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = random.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	 
	static void printArray(int[] solutionArray) {
		System.out.println(Arrays.toString(solutionArray));
	}

	static void print(List<Integer> list) {
		System.out.println(Arrays.toString(list.toArray()));
	}


	/**
	 * Carrercup book
	 * Design an algorithm to find all pairs of integers within an given array
	 * which sum to a specified value
	 */

	static void findAllPairsToSum(int targetSum) {
		int[] input = {3,5,1,9,2,11,8};
		Arrays.sort(input);
		printArray(input);
		int sum, first = 0, last = input.length - 1;

		while( first < last ) {
			sum = input[first] + input[last];

			if( sum == targetSum ) {
				System.out.println(input[first] + " " + input[last]);
				first++;
				last--;
			} else  {
				if( sum < targetSum )
					first++;
				else
					last--;
			}
		}
	}

	// ThreeSum problems
	/**
	 * My approach - brute force
	 */
	private static void threeSumToZero() {
		int[] input = {4,3,0,9,8,-5,1};
		printArray(input);

		Map<Integer,Integer> map = new HashMap<>();
		for (int i = 0; i < input.length-1; i++) {
			for (int j = i+1; j< input.length-1; j++ ) {
				for ( int k = j+1; k < input.length; k++) {
					int sum = input[i] + input[j] + input[k];
					if ( sum == 0 ) {
						System.out.println(i + " = " + input[i] + ", "
								+ j + " = " + input[j] + ", "
								+ k + " = " + input[k] );
						return;
					}
				}
			}
		}
	}

	/**
	 * O(n2)
	 * https://en.wikipedia.org/wiki/3SUM
	 */
	private static void threeSum() {
		int[] input = {4,3,0,9,8,-5,1};
		Arrays.sort(input);
		printArray(input);
		int first, second, third, startIdx, endIdx;
		for (int i = 0; i < input.length - 2; i++) {
			first = input[i];
			startIdx = i + 1;
			endIdx = input.length - 1;

			while (startIdx < endIdx) {
				second = input[startIdx];
				third = input[endIdx];
				int sum = first + second + third;
				if ( sum == 0) {
					List<Integer> list = new ArrayList<>();
					list.add(first);
					list.add(second);
					list.add(third);
					System.out.println(i + " = " + input[i] + ", "
							+ startIdx + " = " + input[startIdx] + ", "
							+ endIdx + " = " + input[endIdx] );
					System.out.println(list);
					return;
				} else if ( sum > 0) {
					endIdx--;
				} else {
					startIdx++;
				}
			}//end of while loop
		} //end of for loop
	}

	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
	 * Find all unique triplets in the array which gives the sum of zero.

	 Note: The solution set must not contain duplicate triplets.

	 For example, given array S = [-1, 0, 1, 2, -1, -4],

	 A solution set is:
	 [
	 [-1, 0, 1],
	 [-1, -1, 2]
	 ]

	 * Some of the cases are not satisfying
	 */
	static List<List<Integer>> threeSumTriplets() {
		//List<List<Integer>> tripletsSet = new ArrayList<>();
		Set<List<Integer>> tripletsSet = new HashSet<>();
		//int[] input = {-1, 0, 1, 2, -1, -4}; // This is for threeSum triplets
		//int[] input = {1,-1,-1,0};
		int[] input = {-2,0,1,1,2};
		Arrays.sort(input);
		printArray(input);
		int first, second, third, startIdx, endIdx;
		outer : for (int i = 0; i < input.length - 2; i++) {
			first = input[i];
			startIdx = i + 1;
			endIdx = input.length - 1;

			while (startIdx < endIdx) {
				second = input[startIdx];
				third = input[endIdx];
				int sum = first + second + third;
				if ( sum == 0) {
					List<Integer> list = new ArrayList<>();
					list.add(first);
					list.add(second);
					list.add(third);
					System.out.println(i + " = " + input[i] + ", "
							+ startIdx + " = " + input[startIdx] + ", "
							+ endIdx + " = " + input[endIdx] );
					System.out.println(list);
					tripletsSet.add(list);
					//if (list.get(0) == 0 && list.get(1) == 0 && list.get(2) == 0) return tripletsSet;
					continue outer;
				} else if ( sum > 0) {
					endIdx--;
				} else {
					startIdx++;
				}
			}//end of while loop
		} //end of for loop
		System.out.println(tripletsSet);
		return new ArrayList<>(tripletsSet);
	}

	/**
	 * Working for all conditions
	 *
	 * @return
	 */
	static List<List<Integer>> threeSumTripletsLeetCode() {
		int[] num = {-1, 0, 1, 2, -1, -4}; // This is for threeSum triplets
		//int[] num = {1,-1,-1,0};
		//int[] num = {-2,0,1,1,2};
		//int[] num = {0, 0, 0, 0};

		Arrays.sort(num);
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < num.length - 2; i++) {
			if (i == 0 || (i > 0 && num[i] != num[i - 1])) {
				int lo = i+1, hi = num.length - 1, sum = 0 - num[i];
				while (lo < hi) {
					if (num[lo] + num[hi] == sum) {
						res.add(Arrays.asList(num[i], num[lo], num[hi]));
						while (lo < hi && num[lo] == num[lo + 1]) lo++;
						while (lo < hi && num[hi] == num[hi - 1]) hi--;
						lo++; hi--;
					} else if (num[lo] + num[hi] < sum) lo++;
					else hi--;
				}// end of outer while loop
			}// end of if loop
		}// end of for loop
		System.out.println(res);
		return res;
	}

	/**
	 * Write java code to find out if it exists that the sum of three integers is zero among a sequence integers
	 * http://stackoverflow.com/questions/2070359/finding-three-elements-in-an-array-whose-sum-is-closest-to-an-given-number
	 *
	 */
	//static void threeSum() {}
}
