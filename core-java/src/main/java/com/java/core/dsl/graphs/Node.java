package com.java.core.dsl.graphs;

public class Node 
{
	public char label;
	public boolean visited = false;
	public boolean isEndOfLookup = false;
	
	public Node(char l)
	{
		this(l,false);
	}
	
	public Node(char l, boolean isEndOfLookup ) {
		this.label = l;
		this.isEndOfLookup = isEndOfLookup;
	}
}
