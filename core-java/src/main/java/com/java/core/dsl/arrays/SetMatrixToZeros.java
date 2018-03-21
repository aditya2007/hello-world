package com.java.core.dsl.arrays;

import java.util.Arrays;
import java.util.Stack;

/**
 * http://www.java67.com/2014/10/how-to-create-and-initialize-two-dimensional-array-java-example.html
 */
public class SetMatrixToZeros {
	Stack stack;
	
    public static void main(String[] args) {
        //Scanner in = new Scanner(System.in);
    	//test2DArray();
    	setZeros(createMxNMatrix());
    	
    }

    
    /**
     * Write an algorithm such that if an element in an MxN matrix is 0, 
     * its entire row and column are set to 0. 
     * @param matrix
     */
    private static void setZeros(int[][] matrix) {
    	boolean[] row = new boolean[matrix.length];
    	boolean[] column = new boolean[matrix[0].length];
    	
    	//Store the row and column index with value 0
    	for( int i = 0; i < row.length; i++ ) {
    		for( int j = 0; j < column.length; j++ ) {
    			if( matrix[i][j] == 0 ) {
    				row[i] = true;
    				column[j] = true;
    			}
    		}// end of inner for loop
    	}// end of outer for loop
    	
    	//Nullify Rows
    	for( int i = 0; i < row.length; i++ ) {
    		if( row[i] ) nullifyRow(matrix, i);
    	}
    	
    	//Nullify Columns
    	for( int j = 0; j < column.length; j++ ) {
    		if( column[j] ) nullifyColumn(matrix, j);
    	}
    	
    	System.out.println(Arrays.deepToString(matrix));
    }

    private static void nullifyRow(int[][] matrix, int row) {
    	for( int j = 0; j < matrix[0].length; j++ ) {
    		matrix[row][j] = 0;
    	}
    }
    
    private static void nullifyColumn(int[][] matrix, int col) {
    	for( int i = 0; i < matrix.length; i++ ) {
    		matrix[i][col] = 0;
    	}
    }
    
    /**
     * Given a m * n matrix, if an element is 0, set its entire row and column to 0.
		Do it in place.

		Analysis

		This problem should be solved in place, i.e., 
		no other array should be used. We can use the first column 
		and the first row to track if a row/column should be set to 0.

		Since we used the first row and first column 
		to mark the zero row/column, the original values are changed.
     * @param matrix
     */
    public void setZeroesLeetCode(int[][] matrix) {
        boolean firstRowZero = false;
        boolean firstColumnZero = false;
 
        //set first row and column zero or not
        for(int i=0; i<matrix.length; i++){
            if(matrix[i][0] == 0){
                firstColumnZero = true;
                break;
            }
        }
 
        for(int i=0; i<matrix[0].length; i++){
            if(matrix[0][i] == 0){
                firstRowZero = true;
                break;
            }
        }
 
        //mark zeros on first row and column
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][j] == 0){
                   matrix[i][0] = 0;
                   matrix[0][j] = 0;
                }
            }
        }
 
        //use mark to set elements
        for(int i=1; i<matrix.length; i++){
            for(int j=1; j<matrix[0].length; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                   matrix[i][j] = 0;
                }
            }
        }
 
        //set first column and row
        if(firstColumnZero){
            for(int i=0; i<matrix.length; i++)
                matrix[i][0] = 0;
        }
 
        if(firstRowZero){
            for(int i=0; i<matrix[0].length; i++)
                matrix[0][i] = 0;
        }
    }
    
    private static int[][] createMxNMatrix() {
    	int[][] matrix = new int[3][4];
    	matrix[0][0] = 2;
    	matrix[0][1] = 1;
    	matrix[0][2] = 0;
    	matrix[0][3] = 3;
    	
    	matrix[1][0] = 5;
    	matrix[1][1] = 0;
    	matrix[1][2] = 5;
    	matrix[1][3] = 6;
    	
    	matrix[2][0] = 7;
    	matrix[2][1] = -1;
    	matrix[2][2] = 10;
    	matrix[2][3] = 9;
    	
    	return matrix;
    }
    
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

}
