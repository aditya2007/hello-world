package com.java.core.careercup.convert;

public class Convert {
	
	
	private static int digitToValue(char c) {
		System.out.println("Char " + c + " = " + (c + 0));
		if( c >= '0' && c <= '9' ) {
			return c - '0';
		}else if( c >= 'A' && c <= 'Z' ) {
			return c + 10 - 'A';
		}else if( c >= 'a' && c <= 'z' ) {
			return c + 10 - 'a';
		}
		return -1;
	}

	private static int divide(int dividend, int divisor) {
		if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1))
			return Integer.MAX_VALUE;
		int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
		long dvd = Math.abs(dividend);
		long dvs = Math.abs(divisor);
		int res = 0;
		while (dvd >= dvs) {
			long temp = dvs, multiple = 1;
			while (dvd >= (temp << 1)) {
				temp <<= 1;
				multiple <<= 1;
			}
			dvd -= temp;
			res += multiple;
		}
		return sign == 1 ? res : -res;
	}


	public static void main(String[] args) {
//		System.out.println("'A' : " + (int)'A' + "; 'a' = " + (int)'a'
//						  + ";'F' : " + (int)'F' + "; 'f' = " + (int)'f');
//		System.out.println(digitToValue('b'));
		int dividend = 10;
		int divisor = 2;

		System.out.println("Divided a number :: " + divide(dividend, divisor));
	}
}
