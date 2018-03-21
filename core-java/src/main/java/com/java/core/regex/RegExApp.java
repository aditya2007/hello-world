package com.java.core.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExApp {
	
	public static void main(String[] args) {
		/*String regex = "I lost my \\w+";
		boolean aMatch = Pattern.matches(regex, "wallet");
		System.out.println(aMatch);
		
		String str = "This is my small example "
			      + "string . which I'm going to " + "use , for pattern matching.";
		
		String str1 = "Here to extract some <title-bar> Hello Pattern Match </title> what about this";
		
		System.out.println(str.replaceAll("\\s", "%20"));
		
		// Removes whitespace between a word character and . or ,
		String pattern = "(\\w)(\\s+)([\\.,])";
		System.out.println(str.replaceAll(pattern, "$1$3")); 
		
		// Extract the text between the two title elements
		pattern = "(?i)(<title.*?>)(.+?)(</title>)";
		String updated = str1.replaceAll(pattern, "$2");
		System.out.println(updated);

		String str2 = "without ac, you should be a'le to surview";
		//String str2 = "ac";
		System.out.println(str2.matches("^[0-9]*[a-z\\s,']*"));
		
		String str3 = "gfh";
		System.out.println(str3.matches("[a-zA-Z]{3}"));*/

		System.out.println(isMatch("aa", "a"));
		System.out.println(isMatch("aa", "aa"));
		System.out.println(isMatch("aaa", "aa"));
		System.out.println(isMatch("aa", "a*"));
		System.out.println(isMatch("aa", ".*"));
		System.out.println(isMatch("ab", ".*"));
		System.out.println(isMatch("aab", "c*a*b"));
	}


	private static boolean isMatch(String str, String matchStr) {
		String strRegExpr = "^[a-z]*";
		String matchStrRegExpr = "[^\\*\\.]";

		String newStr = matchStr.replaceAll(strRegExpr, str);
		System.out.println(newStr);

		return true;
	}
}
