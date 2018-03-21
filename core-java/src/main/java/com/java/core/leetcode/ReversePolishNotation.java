package com.java.core.leetcode;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation. Valid operators are +, -, *, /. Each operand may be an integer or another expression. For example:

  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
  
 * @author m755866
 *
 */
public class ReversePolishNotation {

	public static void main(String[] args) {
		//String[] tokens = new String[]{"2","1","+","3","*"};
		String[] tokens = new String[]{"4","13","0","/","+"};
		System.out.println(evalRPN(tokens));
	}
	
	/**
	 * This problem can be solved by using a stack. 
	 * We can loop through each element in the given array. 
	 * When it is a number, push it to the stack. 
	 * When it is an operator, pop two numbers from the stack, 
	 * do the calculation, and push back the result.
	 * @return
	 */
	private static double evalRPN(String[] tokens) {
		//double retVal = 0.0;
		String operators = "+-*/";
		Stack<String> stack = new Stack<>();
		
		for( String token : tokens) {
			if( !operators.contains(token) ) {
				stack.push(token);
			} else {
				int operand1 = Integer.valueOf(stack.pop());
				int operand2 = Integer.valueOf(stack.pop());
				
				switch(token) {
					case "+":
						stack.push(String.valueOf(operand1+operand2));
						break;
					case "-":
						stack.push(String.valueOf(operand2-operand1));
						break;
					case "*":
						stack.push(String.valueOf(operand1*operand2));
						break;
					case "/":
						if( operand2 != 0 ){
							System.out.println(operand1/operand2);
							stack.push(String.valueOf(operand1/operand2));
						}else{ 
							operand2 = 1;
							stack.push(String.valueOf(operand1/operand2));
						}
						break;
				} //end of Switch statement
			} //end of else block
		}//end of for loop
		
		return Double.parseDouble(stack.pop());
		//return retVal;
	}
}
