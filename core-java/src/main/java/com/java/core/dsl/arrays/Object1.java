package com.java.core.dsl.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * http://salesforce.careermount.com/career/47369/Lead-Software-Engineer-Workflow-Approvals-Us-California-San-Francisco-Hq
 * @author m755866
 *
 */
public class Object1 {
	
	String input;
	StringBuilder cur;
	public static void main(String[] args) throws IOException {
		
		//System.out.println("Enter your username: ");
		//System.out.println("Enter INtegers::: ");
		//Scanner scanner = new Scanner(System.in);
		//String username = scanner.nextLine();
		//System.out.println("Your username is " + username);
		//int _n = Integer.parseInt(scanner.nextLine().trim());
		//int[] nums = new int[_n];
		/*int sum = 0;
		for( int i = 0; i < _n; i++) {
			//nums[i] = Integer.parseInt(scanner.nextLine().trim());
			sum += Integer.parseInt(scanner.nextLine().trim());
		}*/
		/*int sum = 0;
		for(int k = 0; k < nums.length; k++ ) {
			sum += nums[k];
		}*/
		//System.out.println("Total SUM ::::: " + sum);
		//System.out.println(stringToNumber(letters, "BA"));
		//System.out.println(stringToNumber("AZ"));
		
//		String input = "AA";
//		for( int i = 0; i < input.length(); i++ ) {
//			System.out.println(input.charAt(i));
//			System.out.println(letters.get(input.charAt(i) + ""));
//			System.out.println(letters.get(input.charAt(i) + "") * 26 + 1); 
//		}
		//stairCase(6);
		
		//maxCapacityOfMap();
		 BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		    String s;
		    Object1 obj = new Object1();
		    
		    while ((s = in.readLine()) != null) {
		      System.out.println(s);
		      String[] inputArr = s.split(" ");
		      String output = sum(inputArr[0], inputArr[1]);
		      System.out.println(output);
		      /*boolean hasOverla = isRectangleHasOverlap(inputArr);
		      String boolVal = String.valueOf(hasOverla);
		      System.out.println(String.valueOf(hasOverla));
		      obj.generate(s);*/
		      
		    }
	}
	

	
	static String[] buildSubsequences(String s) {
		
		return null;
	}
	 List<String> list = new ArrayList<String>();
	 private void next(int pos, int reminder) {
		    cur.append(input.charAt(pos));
		   
		    if (reminder == 1) {
		      //System.out.println(cur);
		    	list.add(cur.toString());
		    	
		    } else {
		      for (int i = pos + 1; i + reminder - 1 <= input.length(); i++)
		        next(i, reminder - 1);
		    }
		    cur.deleteCharAt(cur.length() - 1);
		    
	  }

	public void generate(String input) {
		cur = new StringBuilder();
		this.input = input;
		for (int length = 1; length <= input.length(); length++) {
			for (int pos = 0; pos + length <= input.length(); pos++) {
				next(pos, length);
			}
		}
		Arrays.sort(list.toArray());
		for( String val : list ) {
			System.out.println(val);
		}
	}
		  
	private static boolean isRectangleHasOverlap(String[] inputArr ) {
		int rectAxCord = Integer.parseInt(inputArr[0]);
		int rectAyCord = Integer.parseInt(inputArr[1]);
		int rectAWidth = Integer.parseInt(inputArr[2]);
		int rectAHeight = Integer.parseInt(inputArr[3]);

		int rectBxCord = Integer.parseInt(inputArr[4]);
		int rectByCord = Integer.parseInt(inputArr[5]);
		int rectBWidth = Integer.parseInt(inputArr[6]);
		int rectBHeight = Integer.parseInt(inputArr[7]);

		if( (rectAxCord == rectBxCord &&  rectAyCord == rectByCord) ) {
			return true;
		}
		return false;
	}
	
	public static String sum(String s1, String s2) {
	    if (s1 == null || s2 == null) {
	    	throw new IllegalArgumentException("");
	    }
	    int first = s1.length() - 1;
	    int second = s2.length() - 1;
	    StringBuilder sb = new StringBuilder();
	    int carry = 0;
	    while (first >= 0 || second >= 0) {
	        int sum = carry;
	        if (first >= 0) {
	            sum += s1.charAt(first) - '0';
	            first--;
	        }
	        if (second >= 0) {
	            sum += s2.charAt(second) - '0';
	            second--;
	        }
	        carry = sum >> 1;
	        sum = sum & 1;
	        sb.append(sum == 0 ? '0' : '1');
	        System.out.println("#### sum == " + sb + " ; ### Carry === " + carry);
	    }
	    if (carry > 0)
	        sb.append('1');

	    sb.reverse();
	    //return String.valueOf(sb);
	    String result = sb.toString();
	    return result.substring(result.indexOf('1'));
	    //return result;
	    //return sb.toString();
	}


	private static void stairCase(int n) {

		int numOfHash = 1;
		int padding = 0;
		while ( n >= 1 ) {
			padding = n/2;
			System.out.print(getSpaces(padding));
			System.out.print(append(numOfHash));
			System.out.println(getSpaces(padding));
			n--;
			numOfHash++;
		}
	}
	
	private static String getSpaces(int padding) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < padding; i++) {
			builder.append(" ");
		}
		return builder.toString();
	}

	private static String append(int numOfHash) {
		StringBuilder builder = new StringBuilder();

		while (numOfHash > 0) {
			builder.append("#");
			numOfHash--;
		}

		return builder.toString();
	}
}
