package com.java.core.companies.workday;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorkdayHacker {

	public static void main(String[] args) {
		int[] num = {1, 2, 3};
		System.out.println(reductionCost(num));
		//System.out.println(Arrays.toString(oddNumbers(3, 9)));
	}

	/**
	 * Given a num n integer array, add 2 numbers
	 * and remove those 2 numbers. Add the addition of 2 numbers to end of array.
	 * Continue this till have single element array.
	 *
	 * Example :
	 * input : {1,2,3}
	 *  cost = 1 + 2 = 3 => new Array {3,3}
	 *  cost += 3 + 3 = 9 => new Array{6}
	 *
	 *  output : 9
	 *
	 * @param num
	 * @return
	 */
	static int reductionCost(int[] num) {
		return reduce(num, 0);
	}

	static int reduceCost = 0;

	static int reduce(int[] num, int cost) {
		reduceCost += cost;
		if (num.length == 1) return reduceCost;
		Arrays.sort(num);
		int[] newNum = new int[num.length - 1];
		cost = num[0] + num[1];
		int j = 0;
		for ( int i = 2; i < num.length; i++) {
			newNum[j++] = num[i];
		}
		//num = null;
		newNum[j] = cost;
		return reduce(newNum, cost);
	}

	/**
	 * Find all the odd number between given 2 numbers inclusive.
	 *
	 * Example :
	 * i/p : 2, 5
	 * o/p : 3, 5
	 *
	 * i/p : 3, 9
	 * o/p : 3, 5, 7, 9
	 * @param l
	 * @param r
	 * @return
	 */
	static int[] oddNumbers(int l, int r) {
		List<Integer> list = new ArrayList<>();
		for (int i = l; i <= r; i++) {
			if (i % 2 != 0) {
				list.add(i);
			}
		}
		return list.stream().mapToInt(i -> i).toArray();
	}


}
