package com.java.core.dsl.graphs; 

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;


public class Graph 
{
	private Node rootNode;
	private List<Node> nodes = new ArrayList<Node>();
	private int[][] adjMatrix;//Edges will be represented as adjacency Matrix
	private LinkedList[] edges; //Edges will be represented as adjacency list
	int size;
	
	public void setRootNode(Node n)
	{
		this.rootNode=n;
	}
	
	public Node getRootNode()
	{
		return this.rootNode;
	}
	
	public void addNode(Node n)
	{
		nodes.add(n);
	}
	
	//This method will be called to make connect two nodes
	public void connectNode(Node start,Node end)
	{
		if(adjMatrix==null)
		{
			size=nodes.size();
			adjMatrix=new int[size][size];
		}

		int startIndex = nodes.indexOf(start);
		int endIndex = nodes.indexOf(end);
		adjMatrix[startIndex][endIndex] = 1;
		adjMatrix[endIndex][startIndex] = 1;
	}

	private Node getUnvisitedChildNode(Node n)
	{
		
		int index=nodes.indexOf(n);
		int j=0;
		while(j<size)
		{
			if(adjMatrix[index][j]==1 && ((Node)nodes.get(j)).visited==false)
			{
				return (Node)nodes.get(j);
			}
			j++;
		}
		return null;
	}

	public void connectNodeAsAdjacencyList(Node start,Node end)
	{
		if(edges == null)
		{
			size = nodes.size();
			edges = new LinkedList[size]; 
			for( int i = 0; i < size; i++ ) {
				LinkedList<Node> edge = new LinkedList<Node>();
				edge.add(nodes.get(i));
				edges[i] = edge;
			}
		}

		int startIndex = nodes.indexOf(start);
		edges[startIndex].add(end);
	}

	private Node getUnvisitedChildNodeAsAdjList(Node n)
	{
		LinkedList<Node> adjList = edges[nodes.indexOf(n)];
		for( Node next : adjList ) {
			if( next != null && !next.visited  ) {
				return next;
			}
		}
		return null;
	}

	
	//BFS traversal of a tree is performed by the bfs() function
	public void bfs() {

		// BFS uses Queue data structure
		//Queue q = new LinkedList();
		Queue<Node> q = new LinkedBlockingQueue<Node>();
		q.add(this.rootNode);
		printNode(this.rootNode);
		rootNode.visited = true;
		while (!q.isEmpty()) {
			Node n = (Node) q.remove();
			Node child = null;
			while ((child = getUnvisitedChildNodeAsAdjList(n)) != null) {
			//while ((child = getUnvisitedChildNode(n)) != null) {
				child.visited = true;
				printNode(child);
				q.add(child);
			}
		}
		// Clear visited property of nodes
		  clearNodes();
	}
	
	//DFS traversal of a tree is performed by the dfs() function
	public void dfs()
	{
		//DFS uses Stack data structure
		Stack<Node> s = new Stack<Node>();
		s.push(this.rootNode);
		rootNode.visited=true;
		printNode(rootNode);
		while(!s.isEmpty())
		{
			Node n = s.peek();
			Node child = getUnvisitedChildNodeAsAdjList(n);
			//Node child = getUnvisitedChildNode(n);
			if( child != null )
			{
				child.visited = true;
				printNode(child);
				s.push(child);
			}
			else
			{
				s.pop();
			}
		}
		//Clear visited property of nodes
		clearNodes();
	}
	
	
	//Utility methods for clearing visited property of node
	private void clearNodes()
	{
		int i=0;
		while(i<size)
		{
			Node n=(Node)nodes.get(i);
			n.visited=false;
			i++;
		}
	}
	
	//Utility methods for printing the node's label
	private void printNode(Node n)
	{
		System.out.print(n.label+" ");
	}
}
