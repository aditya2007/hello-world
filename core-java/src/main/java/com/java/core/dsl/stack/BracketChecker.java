package com.java.core.dsl.stack;

public class BracketChecker {
	
	private String input;
	
	public BracketChecker(String input) {
		this.input = input;
	}
	
	private void checker() {
		int stackSize = input.length();
		StackImpl<Character> checkerStack = new StackImpl<Character>(stackSize);
		
		for( int i = 0; i < input.length(); i++ ) {
			char ch = input.charAt(i);
			
			switch(ch) {
				case '{':
				case '(':
				case '[':
					checkerStack.push(ch);
					break;
					
				case '}':
				case ']':
				case ')':

					if( !checkerStack.isEmpty() ) {
						char chx = checkerStack.pop();
						if( (ch == '}' && chx != '{') ||
							(ch == ']' && chx != '[') ||
							(ch == ')' && chx != '(')
						  )
						{
							System.out.println("Error @checker comparision : " + ch + " at " + i );
						} //End of if loop
					} else { // End of checker Stack if loop ,//Prematurely empty
						System.out.println("Error @Empty check: " + ch + " at " + i);
					}
					break;
				default: // no action on other characters	
			} //end of Switch
		} // end of for loop
		//At this point, all characters have been processed
		if( !checkerStack.isEmpty() ) {
			System.out.println("Error @ end : missing right delimiter");
		}
		
		System.out.println("DONE the DEAL!!!!!!!");
	}
	
	public static void main(String[] args) {
		
		String inputStr = "a{b(c[d]e]f}g";
		
		BracketChecker checker = new BracketChecker(inputStr);
		checker.checker();
	}

}
