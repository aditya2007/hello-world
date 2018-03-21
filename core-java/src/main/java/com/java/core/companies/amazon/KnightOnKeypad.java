package com.java.core.companies.amazon;

import java.util.*;

/**
 *
		 1 2 3
		 4 5 6
		 7 8 9
		   0

 Starting from the digit 1, and choosing successive digits as a knight moves in chess,
 determine how many different paths can be formed of length n. There is no need to make
 a list of the paths, only to count them.

 A knight moves two steps either horizontally or vertically followed by one step in the
 perpendicular direction; thus, from the digit 1 on the keypad a knight can move to digits
 6 or 8, and from the digit 4 on the keypad a knight can move to digits 3, 9 or 0.
 A path may visit the same digit more than once.

 Your task is to write a function that determines the number of paths of length n that a knight
 can trace on a keyboard starting from digit 1
 */
public class KnightOnKeypad {

	public static void main(String[] args) {
		Map<Integer, Set<Integer>>  neighbours = new HashMap<>();
		Set<Integer> hop1= new HashSet<>();
		hop1.add(6);
		hop1.add(8);
		neighbours.put(1, hop1);
		Set<Integer> hop2 = new HashSet<>();
		hop2.add(9);
		hop2.add(7);
		neighbours.put(2, hop2);
		Set<Integer> hop3 = new HashSet<>();
		hop3.add(4);
		hop3.add(8);
		neighbours.put(3, hop3);
		Set<Integer> hop4 = new HashSet<>();
		hop4.add(3);
		hop4.add(9);
		hop4.add(0);
		neighbours.put(4, hop4);
		Set<Integer> hop5 = new HashSet<>();
		neighbours.put(5, hop5);
		Set<Integer> hop6 = new HashSet<>();
		hop6.add(1);
		hop6.add(7);
		hop6.add(0);
		neighbours.put(6, hop6);
		Set<Integer> hop7 = new HashSet<>();
		hop7.add(6);
		hop7.add(2);
		neighbours.put(7, hop7);
		Set<Integer> hop8 = new HashSet<>();
		hop8.add(3);
		hop8.add(1);
		neighbours.put(8, hop8);
		Set<Integer> hop9 = new HashSet<>();
		hop9.add(4);
		hop9.add(2);
		neighbours.put(9, hop9);
		Set<Integer> hop0 = new HashSet<>();
		hop0.add(6);
		hop0.add(4);
		neighbours.put(0, hop0);

		System.out.println(neighbours);

		int numOfPaths = findNumberOfPaths(1, 4);
		System.out.println(numOfPaths);
	}

	static int findNumberOfPaths(int digit, int step) {
		int[][] keypad = new int[4][3];
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				keypad[i][j] = i*3 + j+1;
			}
		}
		keypad[3][0] = -1;
		keypad[3][2] = -1;
		keypad[3][1] = 0;

		int x = -1;
		int y = -1;

		if(digit == 0){
			x = 3;
			y = 1;
		}else{
			x = digit / 3;
			y = (digit % 3) -1;
		}
		return findNumberOfPaths(keypad, x, y, step, 0);
	}

	static int findNumberOfPaths(int[][] keypad, int x, int y, int step, int count) {
		if(x < 0 || y < 0 || x > 3 || y > 2)
			return count;

		if(keypad[x][y] < 0)
			return count;

		if(step == 0)
			return count+1;

		int[] mx = {2, 2, -2, -2, -1, 1, 1, -1};
		int[] my = {-1, 1, 1, -1, 2, 2, -2, -2};

		for(int i = 0; i < 8; i++){
			count = findNumberOfPaths(keypad, x+mx[i], y+my[i], step-1, count);
		}
		return count;
	}
}
