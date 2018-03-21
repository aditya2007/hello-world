package com.java.core.hackrank.arrays;

import java.util.Arrays;
import java.util.Scanner;

public class HourglassSumTwoDArray {
	
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
    	test2DArray();
    	
    }
    
    /**
     * http://www.java67.com/2014/10/how-to-create-and-initialize-two-dimensional-array-java-example.html
     */
    private static void test2DArray() {
    	// initializing two dimensional array as literal 
    	String[][] names = { 
    			{"John", "Smith"}, 
    			{"Javin", "Paul"}, 
    			{"James", "Gosling"},
    		};
    	
    	for( String[] outer : names ) {
    		for(String name : outer ) {
    			System.out.print(name + "\t");
    		}
    		System.out.println("\n");
    	}
    	// how to initialize two dimensional array in Java 
    	// using for loop 
    	int[][] board = new int[3][2]; 

    	for (int i = 0; i < board.length; i++) { 
    		for (int j = 0; j < board[i].length; j++) { 
    			board[i][j] = i + j; 
    		} 
    	}//end of outer for loop 
    	
    	// now let's print a two dimensional array in Java 
    	for (int[] a : board) { 
    		for (int i : a) { 
    			System.out.print(i + "\t"); 
    		} 
    		System.out.println("\n"); 
    	} //end of outer for loop
    	
    	// printing 2D array using Arrays.deepToString() method 
    	System.out.println("another way to print 2D arrays"); 
    	System.out.println(Arrays.deepToString(board)); 
    }

    private void sum2DArray(Scanner in) {
        int arr[][] = new int[6][6];
        
        for(int arr_i=0; arr_i < 6; arr_i++){
            for(int arr_j=0; arr_j < 6; arr_j++){
                arr[arr_i][arr_j] = in.nextInt();
            }
        }
        
       int k = 0, n = 0; 
       for( int i = 0; i < arr.length; i++) {
    	   System.out.print(arr[k] + " " + arr[n]);
       }
    }

}
