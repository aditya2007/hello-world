package com.java.core.hackrank.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class GameOfTwoStacks {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int g = in.nextInt();
		System.out.println("Number of games :: " + g);
		for(int a0 = 0; a0 < g; a0++) {
			int n = in.nextInt();
			int m = in.nextInt();
			System.out.println("Length of first array " + n
					+ ", length of second array " + m);
			int x = in.nextInt();
			System.out.println("Target number :: " + x);
			int[] a = new int[n];
			for(int a_i=0; a_i < n; a_i++){
				a[a_i] = in.nextInt();
			}
			System.out.println("Elements in first array :: " + Arrays.toString(a));
			int[] b = new int[m];
			for(int b_i=0; b_i < m; b_i++){
				b[b_i] = in.nextInt();
			}
			System.out.println("Elements in second array :: " + Arrays.toString(b));
			// your code goes here
			int sum = 0, count = 0, aIdx = 0, bIdx = 0;

			//considering only first stack and calculating count
			while(aIdx < n && sum + a[aIdx] <= x){
				sum += a[aIdx];
				aIdx++;
			}
			count = aIdx;

			//now adding one element of second stack at a time and
			// subtracting the top element of first stack and calculating the count
			while (bIdx < m && aIdx >= 0) {
				sum += b[bIdx];
				bIdx++;
				while (sum > x && aIdx > 0){
					aIdx--;
					sum -= a[aIdx];
				} // end of inner while loop
				if (sum <= x && (aIdx + bIdx) > count) {
					count = aIdx + bIdx;
				}
			} // end of outer while loop for second stack
			System.out.println("Number of moves :: " + count);
		} //end of outer for loop
	} // end of main method
}
