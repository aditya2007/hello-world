package com.java.core.dsl.util;

/**
 * You have a large text file containing words. Given any two words,
 * find the shortest distance(in terms of number of words) between
 * them in the file. If the operation will be repeated many times
 * for the same file ( but different pairs of words) can you
 * optimize your solution.
 *
 * Solution : To solve this problem, we can traverse the file just once.
 * We remember throughout our traversal where we've last seen word1 and word2,
 * storing the locations in lastPosWord1 and lastPosWord2.When we come across
 * word1, we compare it to lastPosWord2 and update min as necessary and 
 * then update lastPosWord1. We do the equivalent operation on word2.
 * At the end of the traversal, we will have the minimum distance.
 */
public class ShortestDistanceBwWords {

	public static int shortest(String[] words, String word1, String word2 ) {
		int min = Integer.MAX_VALUE;
		int lastPosWord1 = -1;
		int lastPosWord2 = -1;
		
		for( int i = 0; i < words.length; i++ ) {
			String currentWord = words[i];
			
			if( currentWord.equalsIgnoreCase(word1) ) {
				lastPosWord1 = i;
				// Comment following 3 lines if word order matters
				/*int distance = lastPosWord1 - lastPosWord2;
				System.out.println("lastPosWord1 - lastPosWord2 >> " + distance);
				if( lastPosWord2 >= 0 && min > distance ) {
					min = distance;
					System.out.println("Min value of Word1 >>> " + min);
				}*/
			} else if ( currentWord.equalsIgnoreCase(word2) ) {
				lastPosWord2 = i;
				int distance = lastPosWord2 - lastPosWord1;
				System.out.println("lastPosWord2 - lastPosWord1 >> " + distance);
				if( lastPosWord1 >= 0 && min > distance ) {
					min = distance;
					System.out.println("Min value of Word2 >>> " + min);
				}
			} // end of if/else block
			
		}// end of for loop
		return min;
	} // end of shortest method
	
	//From stack overflow
	public static int add(int a, int b){  
        while (b != 0){
            int carry = (a & b) ; //Carry but don't add

            a = a ^ b; //add without carrying

            b = carry << 1; //add carry to sum
        }
        return a;
	}

	
	//From CareerCu book
	public static int add1( int a, int b) {
		if( b == 0 ) return a;
		
		int sum = a ^ b; //add without carrying
		int carry = (a & b) << 1; //carry but don't add
		return add1(sum, carry); //recurse
	}
	
	/**
	 * Multiplication of binary numbers without using '*' operator
	 * uses bitwise Shifting/Anding
	 *
	 * @param n1
	 * @param n2
	 */
	public static int multiply(int n1, int n2) {
	    int temp, i = 0, result = 0;

	    while (n2 != 0) {
	        if ((n2 & 1) == 1) {
	            temp = n1;

	            // result += (temp>>=(1/i)); // To do it only using Right shift
		            result += (temp<<=i); // Left shift (temp * 2^i)
	        }
	        n2 >>= 1;   // Right shift n2 by 1.
	        i++;
	    }

	    return result;
	}
	
	public static int multiply1(int n1, int n2) {
		int small, big, result = 0;
		small = n1 > n2 ? n2 : n1;// These two extra variables to ensure 
		big = n1 > n2 ? n1 : n2;// to have less loop, based on the small operand  
		int numOfItrn = 0;
		while( small != 0 ) {
			result += big;
			small--;
			numOfItrn++;
		}
		System.out.println(numOfItrn);
		return result;
	}
	
	
	/**
	 * Write a function to swap a number in place( that is without using temporary variables)
	 * @param a
	 * @param b
	 */
	public static void swapInPlace(int a, int b) {
		System.out.println("Before Swap : a = " + a + " and b = " + b);
		a = a - b;
		b = a + b;
		a = b - a;
		System.out.println("After Swap : a = " + a + " and b = " + b);
	}

	/**
	 * Write a function to swap a number in place( that is without using temporary variables)
	 * for all data types.
	 * @param a
	 * @param b
	 */
	public static void swapInPlace1(int a, int b) {
		System.out.println("Before Swap : a = " + a + " and b = " + b);
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("After Swap : a = " + a + " and b = " + b);
	}
	
	public static void main(String[] args) {
		String str = "Hello Yoga how are you what is the plan for the day, how about your plan tomorrow.";
					//+ " how ever we have and here plan to go out on this weekend";
		String[] words = str.split(" ");
		int min = shortest(words, "how", "plan");
		System.out.println("Short distance between given words : " + min);
		//System.out.println(add(7,6));
		//System.out.println(multiply1(10, 100));
		//swapInPlace1(5, 6);
	}
}
