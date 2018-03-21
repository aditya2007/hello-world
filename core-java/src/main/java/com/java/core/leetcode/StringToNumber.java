package com.java.core.leetcode;

public class StringToNumber {

	public static void main(String[] args) {
		String str = "213";
		System.out.println(atoi(str) + 87);
		String product = multiplyMyApproach("10", "20");
		System.out.println("Product of 10 * 20 :: " + product);
		//double n = 18.0;
		//System.out.println("Square root of " + n + " is :: " + sqrt(n));
	}

	public static int atoi(String str) {
		if (str == null || str.length() < 1)
			return 0;
	 
		// trim white spaces
		str = str.trim();
	 
		char flag = '+';
	 
		// check negative or positive
		int idx = 0;
		if (str.charAt(0) == '-') {
			flag = '-';
			idx++;
		} else if (str.charAt(0) == '+') {
			idx++;
		}
		int result = 0;
	 
		// calculate value
		while (str.length() > idx && str.charAt(idx) >= '0' && str.charAt(idx) <= '9') {
			result = result * 10 + (str.charAt(idx) - '0');
			idx++;
		}
	 
		if (flag == '-')
			result = -result;
	 
		// handle max and min
		if (result > Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
	 
		if (result < Integer.MIN_VALUE)
			return Integer.MIN_VALUE;
	 
		//return (int) result;
		return result;
	}

	/**
	 * Given two non-negative integers num1 and num2 represented as strings,
	 * return the product of num1 and num2.

	 Note:

	 The length of both num1 and num2 is < 110.
	 Both num1 and num2 contains only digits 0-9.
	 Both num1 and num2 does not contain any leading zero.
	 You must not use any built-in BigInteger library or convert the inputs to integer directly.
	 * @return
	 */
	public static String multiplyMyApproach(String num1, String num2) {
		int convNum1 = atoi(num1);
		int convNum2 = atoi(num2);
		if (convNum1 > 109 || convNum2 > 109) {
			return Integer.MAX_VALUE + "";
		}
		return convNum1 * convNum2 + "";
	}

	public static String multiply(String num1, String num2) {
		int m = num1.length(), n = num2.length();
		int[] pos = new int[m + n];

		for(int i = m - 1; i >= 0; i--) {
			for(int j = n - 1; j >= 0; j--) {
				int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
				int p1 = i + j, p2 = i + j + 1;
				int sum = mul + pos[p2];

				pos[p1] += sum / 10;
				pos[p2] = (sum) % 10;
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
		return sb.length() == 0 ? "0" : sb.toString();
	}

	static int sqrt(int x) {
		if ( x < 1 )return x;
		int r = x;
		while (r * r > x)
			r = (r + x / r) / 2;
		return r ;
	}

	/**
	 * Square Root equation to calculate the square root of a given number.
	 *
	 *  sqrt_n+1 = (sqrt_n + ( num / sqrt_n)) / 2;
	 * @param num
	 */
	private static double sqrt(double num) {
		System.out.println(" MATH.Square Root for a given number " + num + " is " + Math.sqrt(num));
		double t;
		double sqrRoot = num/2;

		do {
			t = sqrRoot;
			System.out.println("#### 't' value :: " + t);
			sqrRoot = (t + (num/sqrRoot)) / 2;
			System.out.println("#### 'sqrRoot' value :: " + sqrRoot);
		} while( (t - sqrRoot ) != 0 );

		System.out.println(" #Square Root for a given number " + num + " is " + sqrRoot);
		return sqrRoot;
	}

}
