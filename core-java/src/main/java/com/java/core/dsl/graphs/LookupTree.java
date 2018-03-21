package com.java.core.dsl.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Trie --- Radix Trees

	      h
	    /   \
	   a    e*
	  /   / | \
	 t*  e  a  c
	 /   /  |   \
	e*  l*  t*   k*

	hat, hate, he, heel, heat, heck

 * @author ygovinda
 *
 */
public class LookupTree {
	
	private Node rootNode;
	private List<Node> nodes = new ArrayList<Node>();
	private LinkedList[] edges;
	private int size;
	
	public void setRootNode( Node rootNode ) {
		this.rootNode = rootNode;
	}
	
	public Node getRootNode() {
		return this.rootNode;
	}
	
	public void addNode(Node node) {
		nodes.add(node);
	}
	
	public void connectNode( Node start, Node end ) {
		
		if( edges == null ) {
			System.out.println("Number of nodes " + nodes.size());
			size = nodes.size();
			edges = new LinkedList[size];
			for( int i = 0; i < nodes.size(); i++ ) {
				LinkedList<Node> edge = new LinkedList<Node>();
				edge.add(nodes.get(i));
				edges[i] = edge;
			} //end of for loop
		} // end of if block
		
		int startIdx = nodes.indexOf(start);
		edges[startIdx].add(end);
		
	} // end of connectNode

	
	private Node getUnVisitedAdjcentVertex(Node parent ) {
		int idx = nodes.indexOf(parent);
		//System.out.println("What is the current Index : " + idx);
		LinkedList<Node> adjList = edges[idx];
		for( Node next : adjList ) {
			if( next != null && !next.visited ) {
				return next;
			}
		}// end of for loop
		return null;
	}// end of method
	
	public void lookup(Node rootNode, String word ) {
		StringBuilder builder = new StringBuilder();
		
		Stack<Node> s = new Stack<Node>();
		rootNode.visited = true;
		s.push(rootNode);
		printNode(rootNode);
		builder.append(rootNode.label);
		while( !s.isEmpty() ) {
			Node parent = s.peek();
			if( parent.label == rootNode.label ) { // This block added to get the rootNode value
				builder = new StringBuilder(); // Have to think for working solution
				builder.append(parent.label);
			}
			
			Node child = getUnVisitedAdjcentVertex(parent);
			
			if ( child != null ) {
				builder.append(child.label);
				child.visited = true;
				printNode(child);
				s.push(child);
				if( child.isEndOfLookup && builder.toString().equals(word) ) {
					System.out.println("\n" + word + " got a match " + builder);
					return;
				}
			} else {
				s.pop();
			}
			
		} // end of while
	}// end of lookup method
	
	//Utility methods for printing the node's label
	private void printNode(Node n)
	{
		System.out.print(n.label + " ");
	}
}
