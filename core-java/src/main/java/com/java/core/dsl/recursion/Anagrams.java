package com.java.core.dsl.recursion;

public class Anagrams {
	
	static int size;
	static int count;
	static char[] arrChar;
	
	public static void main(String[] args) {
		String input = "cat";
		size = input.length();
		
		count = 0;
		arrChar = input.toCharArray();
		
		doAnagram(size);
		
	}
	
	private static void doAnagram(int newSize) {
		System.out.println("BEGIN doAnagram >>>>>>>>>> input size :::: " + newSize);
		if( newSize == 1 ) {
			return;
		}
		
		for( int i = 0; i < newSize; i++ ) {
			System.out.println("Before Recursion :::: " + i);
			doAnagram(newSize - 1);
			System.out.println("After Recursion :::: " + i);
			if( newSize == 2 ) {
				displayWord();
			}
			rotate(newSize);
		}//end of for loop
	}
	
	private static void rotate(int newSize) {
		System.out.println("Lets begin rotate for new size : " + newSize);
		int i;
		int pos = size - newSize;
		char temp = arrChar[pos];
		for( i = pos; i < size-1; i++ ) {
			arrChar[i] = arrChar[i+1];
		}
		arrChar[i] = temp;
	}
	
	private static void  displayWord() {
		System.out.print(++count + " ");
		
		for( int i = 0; i < size; i++ ) {
			System.out.print( arrChar[i] );
		}
		
		System.out.print("  ");
		System.out.flush();
		
		//if( count % 2 == 0 ) {
			System.out.println("");
		//}
		
	}
	
	
	
	
	/**
	 * My approach, incomplete 
	 * @param word
	 */
	private static void doAnagrams(char[] word) {
		char[] arrChars = word;
		int wrodLength = arrChars.length;
		int numRotate = wrodLength * getNumOfRotate(wrodLength-1);
		char tmp;
		char[] subCharArr = word.toString().substring(1).toCharArray();
		
		for( int i = 0; i < numRotate; i++) {
			System.out.println(arrChars);	
			
			tmp = arrChars[0];
			int j;
			for( j = 0; j < wrodLength-1; j++ ) {
				arrChars[j] = arrChars[j+1];
			}
			arrChars[j] = tmp;
		}
	}
	
	private static int getNumOfRotate(int wrodLength) {
		if ( wrodLength == 0 ) {
			return 1;
		}else {
			return ( wrodLength * getNumOfRotate(wrodLength-1) );
		}
	}
}


