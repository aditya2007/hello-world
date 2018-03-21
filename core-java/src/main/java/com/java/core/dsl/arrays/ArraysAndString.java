package com.java.core.dsl.arrays;

import java.util.*;

public class ArraysAndString {

	static Map<String, String> treeMap =  new TreeMap<String, String>();

	public static void main(String[] args) {
		System.out.println(computeBinary(5));
		//toBinary();
		//testBitwiseOperators();
		//testEqualObjectEqualMethod();
		/*String reptCharsStr = "aaaccbaaaaaddddf";
		sortDescRepeatedCharsSequence(reptCharsStr);

		List<String> list = new ArrayList<String>();
		list.addAll(treeMap.values());

		for (String key : treeMap.keySet() ) {
			System.out.println(key + ":" + treeMap.get(key));
		}

		for (int i = list.size() - 1; i >= 0; i-- ) {
			System.out.println(list.get(i));
		}*/
//		List<Integer> fibList = new ArrayList<>();
//		//fibRecurssion(10, fibList);
//		fibLoop(5);
//		System.out.println(fibList);

		/*words = ['cc', 'cb', 'bb', 'ac']
		ordering = ['c', 'b', 'a']
		Output: True

		Input:
		words = ['cc', 'cb', 'bb', 'ac']
		ordering = ['b', 'c', 'a']*/

		String[] words = {"cc", "cb", "bb", "ac"};
		char[] ordering = {'c', 'b', 'a'};
		//System.out.println(isWordsSortedAccordingToOrder(words, ordering));

		//reverseTheVowels();
	}

	protected static void sortDescRepeatedCharsSequence(String reptCharsStr) {
		System.out.println(reptCharsStr);
		char last = reptCharsStr.charAt(0);
		String nextReptCharsStr = last +"";
		int count = 1;
		String reptCharsCount = count +""+ last;
		
		for( int i = 1; i < reptCharsStr.length(); i++ ) {
			if( reptCharsStr.charAt(i) == last ) {
				nextReptCharsStr += reptCharsStr.charAt(i);
				//System.out.println("Next Repeated Chars  **** " + nextReptCharsStr );
				count++;
				if( reptCharsStr.length()-1 == i ) {
					reptCharsCount = count +""+ last;
					//System.out.println("Repeated Chars Count >>> " + reptCharsCount );
					treeMap.put(reptCharsCount, nextReptCharsStr);
				}
			} //end of if condition
			else {
				reptCharsCount = count +""+ last;
				treeMap.put(reptCharsCount, nextReptCharsStr);
				count = 1;
				//System.out.println("Repeated Chars Count >>> " + reptCharsCount );
				last = reptCharsStr.charAt(i);
				//System.out.println("Last visited character >>>> " + last);
				nextReptCharsStr = last +"";
				if( reptCharsStr.length()-1 == i ) {
					reptCharsCount = count +""+ last;
					treeMap.put(reptCharsCount, nextReptCharsStr);
				}
			}// end of else
		}//end of for loop
	}

	static Map<Integer, String> recValMap = new HashMap<>();

	public static String computeBinary(int val) {
		// This is to avoid, repeating of recurrsion call, which reduce time complexity, even though it increase space
		if (recValMap.containsKey(val)) {
			return recValMap.get(val);
		}
		// Base case: value is less than 2
		else if (val < 2) {
			String str = Integer.toString(val);
			System.out.println("str value :: " + str);
			recValMap.put(val, str);
			return str;
		}
		// Recursive case: binary rep = binary of the header + last digit (odd/even)
		else {
			String str1 = computeBinary(val/2) + computeBinary(val%2);
			System.out.println("str1 value :: " + str1);
			return str1;
		}
	}

	private static void toBinary() {
		int number = -8; //0010
		/*System.out.println(" value of number on left shift : " + (8 << 1));
		System.out.println(" value of number on left shift : " + (-8 << 1));
		System.out.println(" value of number on right shift : " + (8 >> 1));
		System.out.println(" value of number on right shift : " + (-8 >> 1));
		System.out.println(" value of number on right shift : " + (8 >>> 1));
		System.out.println(" value of number on right shift : " + (-8 >>> 1));
		System.out.println(" value of number after negation: " + ~number);*/


		System.out.println("1 :: " + Integer.toBinaryString(-12));
		System.out.println("1 << 4 :: " + Integer.toBinaryString(1 << 4));
		System.out.println("1 >> 4 :: " + Integer.toBinaryString(1 >> 4));
		System.out.println("2 :: " + Integer.toBinaryString(2));
		System.out.println("2 << 4 :: " + Integer.toBinaryString(2 << 4));
		System.out.println("2 >> 4 :: " + Integer.toBinaryString(2 >> 4));
		System.out.println("32 :: " + Integer.toBinaryString(32));
		System.out.println("32 << 1 :: " + Integer.toBinaryString(32 << 1));
		System.out.println("32 >> 1 :: " + Integer.toBinaryString(32 >> 1));
		System.out.println("32 << 2 :: " + Integer.toBinaryString(32 << 2));
		System.out.println("32 >> 2 :: " + Integer.toBinaryString(32 >> 2));
		/*System.out.println(Integer.toBinaryString(11));
		System.out.println(Integer.toBinaryString(2 << 11));
		System.out.println(Integer.toBinaryString(11 >> 2));
		System.out.println(Integer.toBinaryString(2 << 22));
		System.out.println(Integer.toBinaryString(2 << 33));
		System.out.println(Integer.toBinaryString(2 << 44));
		System.out.println(Integer.toBinaryString(2 << 55));*/
	}
	
	private static void testBitwiseOperators() {
		int a = 60;	    /* 60 = 0011 1100 */
	     int b = 13;	/* 13 = 0000 1101 */
	     int c = 0;
	     int checker = 65;
	     c = a & b;       /* 12 = 0000 1100 */ 
	     System.out.println("a & b = " + c );

	     c = a | b;       /* 61 = 0011 1101 */
	     System.out.println("a | b = " + c );

	     c = a ^ b;       /* 49 = 0011 0001 */
	     System.out.println("a ^ b = " + c );

	     c = ~a;          /*-61 = 1100 0011 */
	     System.out.println("~a = " + c );

	     c = a << 2;     /* 240 = 1111 0000 */
	     System.out.println("a << 2 = " + c );

	     c = a >> 2;     /* 15 = 1111 */
	     System.out.println("a >> 2  = " + c );

	     c = a >>> 2;     /* 15 = 0000 1111 */
	     System.out.println("a >>> 2 = " + c );
	     
	}
	
	private static void testEqualObjectEqualMethod() {
		Object1 obj1 = new Object1();
		Object1 obj2 = new Object1();
		
		System.out.println("obj1 == obj2 >>>> " + (obj1 == obj2)); //return false
		System.out.println("obj1.equals(obj2) >>>> "  + (obj1.equals(obj2))); //return false
		
		System.out.println("obj1 == obj1 >>>> " + (obj1 == obj1)); // return true
		System.out.println("obj2.equals(obj2) >>>> "  + (obj2.equals(obj2))); //return true
		
		System.out.println("obj1 hascode >>>>> " + Objects.hashCode(obj1)); // return 2133927002
		System.out.println("obj2 hascode >>>>> " + Objects.hashCode(obj2)); // return 1836019240
		
		String str1 = "abc";
		String str2 = "abc";
		String str3 = str1;
		String str4 = new String("abc");
		System.out.println(" str1 == str2 >>>>>> " + (str1 == str2));
		System.out.println(" str1.equals(str2) >>>>>> " + (str1.equals(str2)));
		
		System.out.println(" str1 == str3 >>>>>> " + (str1 == str3));
		System.out.println(" str1.equals(str3) >>>>>> " + (str1.equals(str3)));
		
		System.out.println(" str1 == str4 >>>>>> " + (str1 == str4));
		System.out.println(" str1.equals(str4) >>>>>> " + (str1.equals(str4)));
		
		System.out.println("str1 hascode >>>>> " + Objects.hashCode(str1)); // return 96354
		System.out.println("str2 hascode >>>>> " + Objects.hashCode(str2)); // return 96534
		System.out.println("str3 hascode >>>>> " + Objects.hashCode(str3)); // return 96534
		System.out.println("str4 hascode >>>>> " + Objects.hashCode(str4)); // return 96534
	}

	private static int fibRecurssion (int n, List<Integer> fibList) {
		if (n == 0) {
			fibList.add(0);
			return 0;
		} else if (n == 1) {
			fibList.add(1);
			return 1;
		} else {
			int fib = fibRecurssion(n-1, fibList) + fibRecurssion(n-2, fibList);
			fibList.add(fib);
			return fib;
		}
	}

	private static void fibLoop(int n) {
		int prev = 0;
		int fibonacci = 0;
		int current = 1;
		StringBuilder builder = new StringBuilder();
		builder.append(prev).append(",").append(current).append(",");

		for( int i = 2; i < n; i++ ) {
			fibonacci = prev + current;
			//fibonacci += current;
			if( i < n-1 ) {
				builder.append(fibonacci).append(",");
			} else {
				builder.append(fibonacci);
			}
			prev = current;
			current = fibonacci;
		}
		System.out.println(builder);
	}

	public static void generateFibLoop(Integer n) {
		int prev = 0;
		int current = 1;
		int fib;

		List<Integer> fibList = new ArrayList<>();
		fibList.add(prev);
		fibList.add(current);
		while ((n - 2) > 0) {
			fib = prev + current;
			fibList.add(fib);
			prev = current;
			current = fib;
			n--;
		}
		System.out.println(fibList);
	}

	/**
	 * Given two input arrays, return true if the words array is sorted according to the ordering array
	 Input:
	 words = ['cc', 'cb', 'bb', 'ac']
	 ordering = ['c', 'b', 'a']
	 Output: True

	 Input:
	 words = ['cc', 'cb', 'bb', 'ac']
	 ordering = ['b', 'c', 'a']
	 Output: False
	 */
	static boolean isWordsSortedAccordingToOrder(String[] words, char[] ordering) {
		//if (words.length != ordering.length) return false;

		for (int i = 0; i < words.length; i++) {
				char[] temp = words[i].toCharArray();
				if (!(ordering[i] == temp[0] || ordering[i] == temp[1])) {
					return false;
				}
		} // end of for loop
		return true;
	}

	/**
	 * Given a string, identify the vowels and reverse the vowels in their position.
	 * For eg.
	 	Input: education
	 	Output: odicatuen
	 */
	static void reverseTheVowels() {
		String word = "hello world";
		System.out.println(word);

		List<Character> charsList = new ArrayList<>(10);
		charsList.add('a');
		charsList.add('e');
		charsList.add('i');
		charsList.add('o');
		charsList.add('u');
		charsList.add('A');
		charsList.add('E');
		charsList.add('I');
		charsList.add('O');
		charsList.add('U');

		int bIdx = 0;
		int eIdx = word.length() - 1;
		char[] input = word.toCharArray();
		while (bIdx < eIdx) {
			if (!charsList.contains(input[bIdx])) {
				bIdx++;
				continue;
			}
			if (!charsList.contains(input[eIdx])) {
				eIdx--;
				continue;
			}

			if (bIdx < eIdx) {
				char temp = input[bIdx];
				input[bIdx++] = input[eIdx];
				input[eIdx--] = temp;
			}
		}
		System.out.println(new String(input));
	}

	static boolean isVowel(Character c) {
		return (c=='a' || c=='A' || c=='e' ||
				c=='E' || c=='i' || c=='I' ||
				c=='o' || c=='O' || c=='u' ||
				c=='U');
	}
}
