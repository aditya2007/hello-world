package com.java.core.codereview;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Excel column letters to actual numbers, if you recall, 
 * Excel names its columns with letters from A to Z, 
 * and then the sequence goes AA, AB, AC... AZ, BA, BB, etc. 
 * You have to write a function that accepts a string as a parameter (like "AABCCE") 
 * and returns the actual column number. 
 * And then do the exact reverse, given column number return the column name.
 *	
 * Also verify complexity: O(logn), where n is the input number while log is to base the 
 * base being considered (hexa, decimal or binary etc.).
 *
 */
public class ExcelApp {
	
	public ExcelApp() {}  

	public static int getExcelColumnNumber(String column) {
        int result = 0;
        for (int i = 0; i < column.length(); i++) {
            result *= 26;
            result += column.charAt(i) - 'A' + 1;
            System.out.println("Result@Index " + i + " == > " +result);
        }
        return result;
    }

    public static String getExcelColumnName(int number) {
        final StringBuilder sb = new StringBuilder();

        int num = number - 1;
        while (num >=  0) {
            int numChar = (num % 26)  + 65;
            sb.append((char)numChar);
            num = (num  / 26) - 1;
        }
        return sb.reverse().toString();
    }

   /**
    * Improved version - 
    * group - 'A' + 1 with parentheses as - ('A' - 1). Then the compiler can generate 64 as a constant.
    * @param name
    * @return
    * */
    public static int toNumber(String name) {
        int number = 0;
        for (int i = 0; i < name.length(); i++) {
            number = number * 26 + (name.charAt(i) - ('A' - 1));
            System.out.println("Number@Index " + i + " == > " +number);
        }
        return number;
    }

    public static String toName(int number) {
        StringBuilder sb = new StringBuilder();
        while (number-- > 0) {
            sb.append((char)('A' + (number % 26)));
            System.out.println("INSIDE toName char val ::: " + ('A' + (number % 26)));
            System.out.println("INSIDE toName ::: " + sb);
            number /= 26; 
        }
        return sb.reverse().toString();
    }

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


    public static void main(String[] args) {
    	/*String column  = "AAB";
    	//System.out.println(column.charAt(0) - ('A'-1));
    	//System.out.println((char)('A' + 28%26));
    	System.out.println(toNumber(column));
    	System.out.println(getExcelColumnNumber(column));
    	System.out.println(toName(toNumber(column)));
         Assert.assertEquals(53, getExcelColumnNumber("BA"));
         Assert.assertEquals("BA", getExcelColumnName(53));

         Assert.assertEquals(703, getExcelColumnNumber("AAA"));
         Assert.assertEquals("AAA", getExcelColumnName(703));

         Assert.assertEquals(26, getExcelColumnNumber("Z"));
         Assert.assertEquals("Z", getExcelColumnName(26));

         Assert.assertEquals(702, getExcelColumnNumber("ZZ"));
         Assert.assertEquals("ZZ", getExcelColumnName(702));*/

        String str = "MCMIV"; //1904
        //String str = "DCXXI"; //40
        System.out.println("Integer form of Roman Numeral" +
                " is " + romanToInt1(str));
    }

    private static int romanToInt1(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        if (s == null || s.isEmpty()) return -1;

        if (s.length() == 1) {
            return romanMap.get(s.charAt(0));
        }

        int sum = 0;
        for (int idx = 0; idx < s.length(); idx++) {
            int val1 = romanMap.get(s.charAt(idx));
            if ( idx + 1 < s.length()) {
                int val2 = romanMap.get(s.charAt(idx+1));
                if ( val1 >= val2) {
                    sum += val1;
                } else {
                    sum += val2 - val1;
                    idx++;
                }
            } else {
                sum += val1;
            }
        }
        return sum;
    }

    private static int romanToInt(String s) {
        int sum = 0;
        if (s.indexOf("IV") != -1) {
            sum -= 2;
        }
        if (s.indexOf("IX") != -1) {
            sum -= 2;
        }
        if (s.indexOf("XL") != -1) {
            sum -= 20;
        }
        if (s.indexOf("XC") != -1) {
            sum -= 20;
        }
        if (s.indexOf("CD") != -1) {
            sum -= 200;
        }
        if (s.indexOf("CM") != -1) {
            sum -= 200;
        }

        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        char[] cArr = s.toCharArray();
        for (int count = 0; count < s.length(); count++) {
            sum += romanMap.get(cArr[count]); // This with map
            /*if (cArr[count] == 'M') { // This is without having map
                sum += 1000;
                continue;
            }
            if (cArr[count] == 'D') {
                sum += 500;
                continue;
            }
            if (cArr[count] == 'C') {
                sum += 100;
                continue;
            }
            if (cArr[count] == 'L') {
                sum += 50;
                continue;
            }
            if (cArr[count] == 'X') {
                sum += 10;
                continue;
            }
            if (cArr[count] == 'V') {
                sum += 5;
                continue;
            }
            if (cArr[count] == 'I') {
                sum += 1;
                continue;
            }*/
        }

        return sum;
    }

    private static int value(char r) {
       if (r == 'I') return 1;
       if (r == 'V') return 5;
       if (r == 'X') return 10;
       if (r == 'L') return 50;
       if (r == 'C') return 100;
       if (r == 'D') return 500;
       if (r == 'M') return 1000;
       return -1;
    }


}
