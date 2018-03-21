package com.java.core.dsl.arrays;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

//import org.perf4j.StopWatch;
//import org.perf4j.log4j.Log4JStopWatch;

/**
 * 1. implement a method which can cast a mirror image of a graph or tree.
	public Node mirrorMe(Node root) {
	...
	}

	2. find element repetition in an array and remove duplicates in the array
	[1,2,1,2,1,3,4,5] => [1,4,5] logic needs to be either of complexity nlogn or n
	
	3. previous problem what if the elements are in a linked list
	
	4. identify cyclic dependency in a tree or a graph. design all classes and write complete java code
	
	5. design and impl a rest API for pharmacy store
	
	6. how to evaluate performance, security and functionality of a complex integrated system - architecture diagram is provided
	
	7. how to identify slowness in an API? any tools like yourkit or any other profiles used
	
	8. how to do integration testing? why to choose testng over junit? how can we run multi threaded testing and set expectations over the results?
	
	9. how to secure a rest api?
 * 
 *
 */
public class CrackArraysAndString {
	
	public static void main(String[] args) throws ParserConfigurationException {
		
		CrackArraysAndString crackArrString = new CrackArraysAndString();
		String inputStr = "godsracez";
		String inputStr1 = "dog";
		//System.out.println(" 1 << 82 ==== " + (1 << 0));
		//System.out.println( 0 & (1 << 0) );
		//int n = Runtime.getRuntime().availableProcessors();
		//System.out.println("How many procssor doeS my mac has >>>>>>> " + n);

//		boolean isStringHasUniqueChars =
//					crackArrString.determineIsStringHasAllUniqueChars2(inputStr);
//		System.out.println("Is given string " + inputStr
//				+ " has all charaecters unique ? " + isStringHasUniqueChars );
		//crackArrString.findAndDeleteDuplicates();
		//int[] input = new int[]{1, 1, 3, 7, 7, 8, 9, 9, 9, 10};
		//crackArrString.removeDuplicates(input);
		//int firstNonRepeatElmnt = crackArrString.findFirstNonRepeatingElement1();

		//System.out.println("First Non repeating Element >>>>>> " + firstNonRepeatElmnt );
		//String repatingStr = "Salesforce is the best company to work for";//"abaaggeabdfakeafrs";
		//char firstNonRepeatChar = crackArrString.findFirstNonRepeatingChar1(repatingStr);
		//System.out.println("First Non repeating Char is '" + firstNonRepeatChar
		//					+ "' for a given String '" + repatingStr + "'");
		//System.out.println("First uniq character @index :: " + crackArrString.firstUniqChar());
		//System.out.println(" First Uniq Word is :: " + crackArrString.firstUniqWord());
		/*int[] input = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
		System.out.println("Maximum from given array of elements " 
				+ " {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1} is " 
				+ crackArrString.findMaximumElement(input));
		System.out.println("Minimum from given array of elements "
				+ " {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1} is "
				+ crackArrString.findMinimumElement(input));*/

		//crackArrString.reverseAString(inputStr);
		System.out.println(crackArrString.reverseAString1("lac$ok&0l2t"));
		//crackArrString.removeDuplicates(inputStr.toCharArray());
		//boolean isAnargam = crackArrString.anagram1(inputStr, inputStr1);
		//System.out.println("Is given words are anargam >>>>>> " + isAnargam);
		//int sum = crackArrString.add(10, 10);
		//System.out.println("Sum value without using airthmetic expression " + sum);
		//crackArrString.sqrt(4);
		//String comprStr = "aabcccccaaaff";
		//System.out.println(crackArrString.wordCompression(comprStr));
		//System.out.println(crackArrString.wordCompressionWorking(comprStr));
		//System.out.println(crackArrString.isGivenStringsPermuatation("read", "eard"));
		//crackArrString.wordWrap();

		//crackArrString.moveZerosToEndOfArray();
	}
	
	/*
	 * This is my approach leads to O(n^2)
	 */
	private boolean determineIsStringHasAllUniqueChars(String inputStr) {
		//StopWatch stopWatch = new Log4JStopWatch("CrackArraysAndString.UniqueString");
		char[] charArr = inputStr.toLowerCase().toCharArray();
		char[] newCharArr = new char[inputStr.length()];
		
		boolean isStringHasUniqueChars = true;
		
		for( int idx = 0; idx < inputStr.length(); idx++ ) {
			if( idx > 0 ) {
				for(int idx1 = 0; idx1 < newCharArr.length; idx1++ ) {
//					System.out.println("newCharArr[idx1] => " + newCharArr[idx1] 
//										+ " and charArr[idx] => " + charArr[idx]);
					if( newCharArr[idx1] == charArr[idx] ) {
						isStringHasUniqueChars = false;
						break;
					} // End of if loop
				}// End of inner for loop
				if( isStringHasUniqueChars ) {
						newCharArr[idx] = charArr[idx];
//						System.out.println(" Inside>>> newCharArr[idx] => " + newCharArr[idx] 
//								+ " and charArr[idx] => " + charArr[idx]);
				} else {
					break;
				}
			} else {
				newCharArr[idx] = charArr[idx];
			} // End of first if loop
		} // End of outer for loop
		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//stopWatch.stop("CrackArraysAndString.UniqueString.Ends");
		//System.out.println("Total elapsed time >>>> " + stopWatch.getElapsedTime());
		return isStringHasUniqueChars;
	} // End of determineIsStringHasAllUniqueChars

	/**
	 * For simplicity, assume char set is ASCII
	 * (if not, we need to increase the storage size.
	 * The rest of the logic would be the same). 
	 * NOTE: This is a great thing to point out to your interviewer!
	 * 
	 * @param inputStr
	 * @return
	 */
	private boolean determineIsStringHasAllUniqueChars2(String inputStr) {
		boolean[] char_set = new boolean[256];
		System.out.println("Is char _set[2] true ??? " + char_set[inputStr.charAt(0)]);
		for(int i = 0; i < inputStr.length(); i++) {
			int val = inputStr.charAt(i);
			System.out.println("For char " + inputStr.charAt(i) + "; value is " + val );
			if( char_set[val] ) return false;
			char_set[val] = true;
		}
		
		return true;
	}
	
	
	/**
	 * Its not working
	 * We can reduce our space usage a little bit by using a bit vector. 
	 * We will assume, in the below code, that the string is only lower case �a� through �z�. 
	 * This will allow us to use just a single int
	 */
	public boolean determineIsStringHasAllUniqueChars3(String str1) {
		String str = "podprace";
		str = str.toLowerCase();
		int checker = 0;
		for (int i = 0; i < str.length(); i++) {
			int val = str.charAt(i) - 'a';
			//System.out.println("Int value of a character " +  val + " for character : " + str.charAt(i));
		    //System.out.println("1 << "+ val + ") ==> " + Integer.toBinaryString(1 << val ));
		    //System.out.println(checker + " Checker ==> " + Integer.toBinaryString(checker));
		    //System.out.println(checker +" & (1 << "+ val +" ) ==> " + (checker & (1 << val)));
			if ((checker & (1 << val)) > 0) return false;
			checker |= (1 << val);
		 }
		 return true;
	}
	
	/**
	 * If we are allowed to destroy the input string,
	 * we could sort the string in O(n log n) time and then linearly 
	 * check the string for neighboring characters that are identical. 
	 * Careful, though - many sorting algorithms take up extra space.
	 * 
	 * @param inputStr
	 * @return
	 */
	private boolean determineIsStringHasAllUniqueChars4(String inputStr) {
		//inputStr = inputStr.replaceAll("\\s", "");
		char[]inputStrArr = inputStr.toLowerCase().toCharArray();
		Arrays.sort(inputStrArr);
		//String str = new String(inputStrArr);
		//System.out.println(str);
		//int val0 = inputStrArr[0];
		for( int i = 0; i < inputStrArr.length-1; i++ ) {
			System.out.println("inputStrArr[i] <<>> " + inputStrArr[i] 
					+ " :: inputStrArr[i+1] <<>> " + inputStrArr[i+1]);
			if(inputStrArr[i] ==  inputStrArr[i+1] ) return false;
		}
		return true;
	}
	
	/*
	 * My approach with O(n)
	 */
	private void reverseAString(String str) {
		System.out.println("Input String  >>>>>> " + str);
		char[] inputCharArr = str.toCharArray();
		char[] outputCharArr = new char[str.length()];
		int j = 0;
		for( int i = str.length()-1; i >= 0; i-- ) {
			outputCharArr[j++] = inputCharArr[i];
		}
		
		System.out.println("Reversed String >>> " + new String(outputCharArr) );
		
	}

	// Reverse a String but keep all the special characters in place
	private String reverseAString1(String str) {
		System.out.println("Input String  >>>>>> " + str);
		if ( str == null || str.isEmpty() || str.length() == 1) return str;
		int bIdx = 0;
		int eIdx = str.length() - 1;

		char[] charArr = str.toCharArray();

		while (bIdx < eIdx) {
			if (charArr[bIdx] == charArr[eIdx]) {
				bIdx++;
				eIdx--;
				continue;
			}
			if (isSpecialChar(charArr[bIdx])) {
				bIdx++;
			}
			if (isSpecialChar(charArr[eIdx])) {
				eIdx--;
			}

			char temp = charArr[bIdx];
			charArr[bIdx++] = charArr[eIdx];
			charArr[eIdx--] = temp;
		} // en of while
		return new String(charArr);
	}

	private boolean isSpecialChar(char ch) {
		return (ch + "").matches("[^A-Za-z0-9]");
	}

	private void removeDuplicates(char[] str) {
		System.out.println("Before dulicates removed >>> " + String.valueOf(str) );
		if (str == null)
			return;
		int len = str.length;
		if (len < 2)
			return;

		int tail = 1;

		System.out.println("tail = " + tail);
		for (int i = 1; i < len; ++i) {
			System.out.println("i = " + i +" tail = " + tail);
			int j;
			for (j = 0; j < tail; ++j) {
				System.out.println("j =>> " + j );
				if (str[i] == str[j])
					break;
			}
			System.out.println("j = " + j +" tail = " + tail);
			if (j == tail) {
				str[tail] = str[i];
				++tail;
			}
		}
		str[tail] = 0;
		System.out.println("After dulicates removed >>> " + String.valueOf(str) );
	}
	
	/*
	 * Solution #1: Sort the strings
	 */
	private boolean anagram(String s, String t) {
		Arrays.sort(s.toCharArray());
		Arrays.sort(t.toCharArray());
		return s == t;
	}
	
	/*
	 * Solution #2: Check if the two strings have 
	 * identical counts for each unique char.
	 */
	private boolean anagram1(String s, String t) {
		if (s.length() != t.length())
			return false;
		int[] letters = new int[256];
		int num_unique_chars = 0;
		int num_completed_t = 0;
		char[] s_array = s.toCharArray();
		for (char c : s_array) { // count number of each char in s.
			if (letters[c] == 0)
				++num_unique_chars;
			++letters[c];
		}
		for (int i = 0; i < t.length(); ++i) {
			int c = (int) t.charAt(i);
			if (letters[c] == 0) { // Found more of char c in t than in s.
				return false;
			}
			--letters[c];
			if (letters[c] == 0) {
				++num_completed_t;
				if (num_completed_t == num_unique_chars) {
					// it�s a match if t has been processed completely
					return i == t.length() - 1;
				}
			}
		}
		return false;
	}
	
	//Wa***rt starts here
	/*
	 * 2. find element repetition in an array and remove duplicates in the array
	 *	[1,2,1,2,1,3,4,5] => [1,4,5] logic needs to be either of complexity nlogn or n 
	 */
	private void findAndDeleteDuplicates() {
		Integer[] numArr = {1,2,1,2,1,3,4,5,8,9,9,11,17};
		boolean[] dups = new boolean[18];
		Integer[] newNumArr = new Integer[18];
		
		int newCnt = 0;
		for( int i = 0; i < numArr.length; i++ ) {
			 	
			// This approach fails if the element is not 
			// within the range of arr length index
			if( !dups[numArr[i]] ) {
				dups[numArr[i]] = true;
				//newNumArr[newCnt++] = numArr[i];
			} else {
				numArr[i] = null;
			}
		}
		
		System.out.println("Final elements in the array ");
		for( int k = 0; k < numArr.length; k++) {
			if( numArr[k] != null ) {
				System.out.print(numArr[k] + " ");	
			}
		}
	} // end of remove duplicates method
	
	//NetSuite problem1
	int[] removeDuplicates(int[] inputs) {
		
		if( inputs == null ) 
			return null;
		
		if( inputs.length <= 1 )
			return null;	
		
		//int[] input = new int[]{1, 2, 3, 3, 3, 4, 4, 10, 13, 15, 15, 17};
		int[] input = new int[]{1, 2, 3, 3, 4, 5};
		int lastIdx = 0;
		
		//input[lastIdx] = input[0];
		System.out.println("Length of the input : " + input.length);
        
		for (int currIdx = 1; currIdx < input.length; currIdx++)
        {
            if (input[lastIdx] != input[currIdx])
            {
            	//opIdx++;
                input[++lastIdx]=input[currIdx];
            }
        }
		//int[] output = new int[lastIdx+1];
		input = Arrays.copyOf(input, lastIdx+1 );
        //for(int i = 0; i <= lastIdx; i++)
        for(int i = 0; i < input.length; i++)
        {
        	//output[i] = input[i];
            System.out.print(" " + input[i]);
        }

        System.out.println("\n Length of the input after : " + input.length);

	    return input;
	}
	
	/**
	 * Without using additional data structure.
	 * @return
	 */
	private int findFirstNonRepeatingElement() {
		int[] numArr = {1,2,1,2,1,3,15,5,2,3,6,4,15};
		int[] countArr = new int[numArr.length];
		boolean[] dupsArr = new boolean[numArr.length];
		int retVal = -1;
		
		for( int i = 0; i < numArr.length; i++ ) {
			if( dupsArr[numArr[i]] ) { //ArrayIndex OutofBoundException, if any one element is > nummArr.length  
				countArr[numArr[i]] = countArr[numArr[i]] + 1;
			} else {
				dupsArr[numArr[i]] = true;
				countArr[numArr[i]] = 1;
			} // end of if /else block
		}// end of first for loop
		
		for( int i = 0; i < numArr.length; i++ ) {
			if( countArr[numArr[i]] == 1 ) {
				retVal = numArr[i];
				break;
			}
		} // end of 2nd for loop
		return retVal;
	}

	/**
	 * Using an additional DataStructure - Map
	 * @return
	 */
	private int findFirstNonRepeatingElement1() {
		int[] numArr = {1,2,1,2,1,3,17,5,2,3,6,4};
		Map<Integer, Integer> uniqueMap = new HashMap<Integer,Integer>();

		for( int i = 0; i < numArr.length; i++ ) {
			//if(uniqueMap.containsKey(numArr[i]) ) { //Not time efficient
			if(uniqueMap.get(numArr[i]) != null ) {
				uniqueMap.put(numArr[i], uniqueMap.get(numArr[i])+1);
			} else {
				uniqueMap.put(numArr[i],1);
			}
		}// end of for loop
		
		for( int i = 0; i < numArr.length; i++ ) {
			if( uniqueMap.get(numArr[i]) == 1 ) {
				return numArr[i];
			}
		}
		return -1;
	}
	
	public int firstUniqChar() {
		String s = "letleetcode";
		/*int[] repeatChars = new int[256]; // Leet Code approach
		for (int i = 0; i < s.length(); i++) {
			repeatChars[s.charAt(i) - 'a']++;
		}

		for (int i = 0; i < s.length(); i++) {
			if (repeatChars[s.charAt(i) - 'a'] == 1) {
				return i;
			}
		}*/

		Map<Character, Integer> linkMap = new LinkedHashMap<>();
		for (char ch : s.toCharArray()) {
			if (linkMap.containsKey(ch)) {
				linkMap.put(ch, linkMap.get(ch) + 1);
			} else {
				linkMap.put(ch, 1);
			}
		}

		for (char ch : linkMap.keySet()) {
			if (linkMap.get(ch) == 1) {
				return s.indexOf(ch);
			}
		}
		return -1;
	}

	public String firstUniqWord() {
		String[] words = {"upon", "there", "was", "king", "once", "upon","there", "was", "queen", "time", "king", "queen"};
		Map<String, Integer> linkMap = new LinkedHashMap<>();

		/*long dupWords[] = new long[Integer.MAX_VALUE ]; // Exceeds the limit or OutofMemory error
		for (String word : words) {
			System.out.println(word + " = " + word.hashCode());
			dupWords[word.hashCode()] ++;
		}

		for (String word : words) {
			//if (dupWords[word.hashCode()] == 1) {
			if (dupWords[hashCode(word)] == 1) {
				return word;
			}
		} */

		for (String word : words) {
			if (linkMap.containsKey(word)) {
				linkMap.put(word, linkMap.get(word) + 1);
			} else {
				linkMap.put(word, 1);
			}
		} // end of for loop
		System.out.println(linkMap);

		for (String word : linkMap.keySet()) {
			if (linkMap.get(word) == 1) {
				return word;
			}
		}
		return "";
	}

	public int hashCode(String word) {
		int h = 0;
		for (char c : word.toCharArray()) {
			h = 31 * h + c;
		}
		return h;
	}

	private char findFirstNonRepeatingChar1(String str) {
		String s1 = "Salesforce is the best company to work for";
		char[] chars = s1.toLowerCase().replaceAll("\\s","").toCharArray();
		int count;
		for (int i = 0; i < chars.length; i++) {
			count = 0;
			for (int j = i + 1; j < chars.length; j++) {
				if ( chars[i] == chars[j]) {
					count++;
					break;
				}//end of if loop
			}//end of inner for loop
			if (count == 0) {
				System.out.println(chars[i]);
				return chars[i];
			}
		} //end of outer for loop
		return 0;
	}

	// Move all zeros to end of array
	private void moveZerosToEndOfArray() {
		int[] arr = {3,2,6,0,9,0,1,5,0,8};
		System.out.println(Arrays.toString(arr));
		int startIdx = 0;
		int endIdx = arr.length - 1;
		while (startIdx <= endIdx) {
			if (arr[startIdx] != 0) {
				startIdx++;
				continue;
			}
			if (arr[endIdx] == 0) {
				endIdx--;
				continue;
			}
			if (arr[startIdx] == 0 && arr[endIdx] == 0) {
				endIdx--;
				continue;
			}
			if (arr[startIdx] == 0 && arr[endIdx] != 0) {
				int tmp = arr[endIdx];
				arr[endIdx] = arr[startIdx];
				arr[startIdx] = tmp;
				startIdx++;
				endIdx--;
				continue;
			}
			if (arr[endIdx] == 0 && arr[startIdx] != 0) {
				startIdx++;
				endIdx--;
				continue;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	
	int findMaximumElement( int[] input ) {
		int max = input[0];
		
		for( int i = 1; i < input.length; i++ ) {
			if( input[i] > max ) {
				max = input[i];
			}
		} // end of for loop
		return max;
	}

	int findMinimumElement( int[] input ) {
		int min = input[0];

		for( int i = 1; i < input.length; i++ ) {
			if( input[i] < min ) {
				min = input[i];
			}
		} // end of for loop
		return min;
	}


	/**
	 * Write a function that adds two numbers. You should not use + 
	 * or any airthmetic operators
	 * 
	 */
	private int add(int a, int b) {
		if( b == 0 ) return a;
		System.out.println("XOR value of a ^ b ::: " + Integer.toBinaryString(a ^ b));
		int sum = a ^ b; // add without carrying ==> Use XOR binary operator
		System.out.println("Sum without carrry : " + sum);
		int carry = (a & b) << 1; // carry, but don't add ==> Use AND and SHIFT to do carry
		System.out.println("Carry without sum : " + carry);
		return add(sum, carry);
		//return sum;
	}
	
	//This is from Cracking the Code , but the solution is not working
	private String wordCompression(String input) {
		System.out.println(input);
		String retVal = "";
		String endOfString = "";
		char last = input.charAt(0);
		int count = 1;
		for( int i = 1; i < input.length(); i++) {
			if( input.charAt(i) == last ) {
				count++;
				endOfString += input.charAt(i) + "" + count;
				if( input.length() - 1 == i ) {
					retVal += endOfString;
				}
				
			} else {
				retVal += last + "" + count;
				last = input.charAt(i);
				System.out.println(last);
				count = 1;
			}
		}//end of for loop
		System.out.println(retVal);
		return input.length() > retVal.length() ? retVal : input;
	}
	
	//My solution working
	private String wordCompressionWorking(String reptCharsStr) {
		System.out.println(reptCharsStr);
		char last = reptCharsStr.charAt(0);
		int count = 1;
		String reptCharsCount = count +""+ last;
		StringBuilder builder = new StringBuilder();
		
		for( int i = 1; i < reptCharsStr.length(); i++ ) {
			if( reptCharsStr.charAt(i) == last ) {
				count++;
				if( reptCharsStr.length()-1 == i ) {
					reptCharsCount = count +""+ last;
					builder.append(reptCharsCount);
				}
			} //end of if condition
			else {
				reptCharsCount = count +""+ last;
				builder.append(reptCharsCount);
				count = 1;
				last = reptCharsStr.charAt(i);
				if( reptCharsStr.length()-1 == i ) {
					reptCharsCount = count +""+ last;
					builder.append(reptCharsCount);
				}
			}// end of else
		}//end of for loop
		System.out.println(builder);
		
		return reptCharsStr.length() > builder.length() ? builder.toString() : reptCharsStr;
	}
	
	public boolean isGivenStringsPermuatation(String str1, String str2) {
		String s1 = "read", s2 = "earr";
		if( s1.length() != s2.length() ) return false;
		
		int[] letters = new int[128];
		char[] s1Arr = s1.toCharArray();
		for( char c : s1Arr ) {
			letters[c]++;
		}
		
		for( int i = 0; i < s2.length(); i++ ) {
			int c = (int)s2.charAt(i);
			System.out.println(c);
			if(--letters[c] < 0 ) return false;
		}
		return true;
	}

	/**
	 * Apple -
	 * Given a string of words and a maxWidth, write a method that inserts new lines
	 * where appropriate to perform a word-wrap. (Wrap on word boundaries, assume " " is fine).
	 * Now, how does your implementation handle string with multiple spaces between words
	 * ... Are those extra spaces lost or preserved?
	 *
	 * This is a DP problem
	 */
	private void wordWrap() {
		StringBuilder str = new StringBuilder("This is a very long string which has to be  r e converted");

		int maxWidth = 10;
		int length = str.length();

		int currentWidth = maxWidth;

		while( currentWidth < length && str.lastIndexOf(" ",currentWidth) != -1)
		// last word will not have white space at end so this second check is needed
		{
			int spacePoint = str.lastIndexOf(" ",currentWidth);
			str.replace(spacePoint, spacePoint+1, "\n");
			currentWidth = spacePoint + 1 + 10; // start the next substring from space+1th point + 10 chars

			System.out.println("i = " + currentWidth);

		}

		System.out.println(str);
	}

}
