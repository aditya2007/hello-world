package com.java.core.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, 
 * determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words.

	For example, given
	s = "leetcode",
	dict = ["leet", "code"].

 * Return true because "leetcode" can be segmented as "leet code".
 * @author m755866
 *
 */
public class DictWordBreaker {
	
	public static void main(String[] args) {
		String s = "leetcode";
		Set<String> dict = new HashSet<String>();
		dict.add("leet");
		dict.add("code");
		
		DictWordBreaker braker = new DictWordBreaker();
		System.out.println(braker.wordBreak2(s, dict));
	}
	
	/**
	 * In Solution 2, if the size of the dictionary is very large, the time is bad. 
	 * Instead we can solve the problem in O(n^2) time (n is the length of the string).
	 * 
	 * @param s
	 * @param dict
	 * @return
	 * Not working..
	 */
	private boolean wordBreak1(String s, Set<String> dict ) {
		int[] pos = new int[s.length()+1];
		System.out.println(pos.length);
		
		pos[0] = 0;
		
		for( int i = 0; i < s.length(); i++ ) {
			if( pos[i] != -1 ) {
				for( int j = i + 1; j <= s.length(); j++ ) {
					String sub = s.substring(i, j);
					if( dict.contains(sub) ) {
						pos[j] = i;
					}
				} //end of inner loop
			}
		}//end of outer for loop
		return pos[s.length()] != -1;
	}
	
	
	/**
	 * Dynamic programming
	 * The key to solve this problem by using dynamic programming approach:
		Define an array t[] such that t[i]==true => 0-(i-1) can be segmented using dictionary
		Initial state t[0] == true
		
		Time: O(string length * dict size).
		
	 * @param s
	 * @param dict
	 * @return
	 */
	private boolean wordBreak2(String s, Set<String> dict) {
		boolean[] t = new boolean[s.length() + 1];
		t[0] = true; //set first to be true, why?? Because we need initial state
		
		for( int i = 0; i < s.length(); i++ ) {
			//should continue from match position
			if( !t[i] ) {
				continue;
			}
			
			for( String word : dict ) {
				int len = word.length();
				int end = i + len;
				
				if( end > s.length() ) continue;
				
				if( t[end] ) continue;
				
				if( s.substring(i, end).equals(word) ) {
					t[end] = true;
				}
				
			}//end of inner loop
		}// end of outer for loop
		
		return t[s.length()];
	}
	
	
	/**
	 * Solve by using a naive approach, which is trivial.
	 * Time is O(n^2) and exceeds the time limit
	 */
	private boolean wordBreak3(String s, Set<String> dict) {
		return wordBreakHelper(s, dict, 0);
	}
	
	private boolean wordBreakHelper(String s, Set<String> dict, int start) {
		if( start == s.length() ) return true;
		
		for( String word : dict ) {
			int len = word.length();
			int end = start + len;
			
			//end index should be <= string length
			if( end > s.length() ) {
				continue;
			}
			
			if( s.substring(start, start+len).equals(word) ) {
				if(wordBreakHelper(s, dict, start+len)) return true;
			}
		}//end of for loop
		
		return false;
	}

}
