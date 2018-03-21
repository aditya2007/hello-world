package com.java.core.leetcode;

public class LongestPalinDrome {
	
	public static void main(String[] args) {
		String str = "Create a palindrome sentence with car as rac and madam as teacher and malayalam as language";
		//String str = "acgmomfcedecy";
		//String str = "malayalam"; //"abba"; failed
		//System.out.println(longestPalindrome(str));
		System.out.println(findLongestPalindrome(str));
		//System.out.println(isPalindrome(longestPalindrome(str)));
		//System.out.println(isPalindrome(str));
	}
	
	private static String longestPalindrome(String s) {
		if (s == null || s.isEmpty()) {
			return null;
		}
	 
		if (s.length() == 1) {
			return s;
		}
	 
		String longest = s.substring(0, 1);
		for (int i = 0; i < s.length(); i++) {
			// get longest palindrome with center of i
			String tmp = helper(s, i, i);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}
	 
			// get longest palindrome with center of i, i+1
			/*tmp = helper(s, i, i + 1);
			if (tmp.length() > longest.length()) {
				longest = tmp;
			}*/
		}
	 
		return longest;
	}
	
	// Given a center, either one letter or two letter, 
	// Find longest palindrome
	private static String helper(String s, int begin, int end) {
		while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
			begin--;
			end++;
		}
		return s.substring(begin + 1, end);
	}

	/**
	 * My approach
	 *
	 * @param s
	 * @return
	 */
	private static String findLongestPalindrome(String s) {
		String[] words = s.split(" ");
		String longest = "";
		for (String word : words) {
			if (isPalindrome(word) && word.length() > longest.length()) {
				longest = word;
			}
		}
		return longest;
	}


	private static boolean isPalindrome(String word) {
		if ( word == null || word.isEmpty()) return false;
		//This approach is using built in fuction from StringBuilder
		StringBuilder builder = new StringBuilder();
//		builder.append(word);
//		return builder.reverse().toString().equals(word);

		for (int i = word.length() - 1; i >= 0; i--) {
			builder.append(word.charAt(i));
		}
		return builder.toString().equals(word);
	}
}
