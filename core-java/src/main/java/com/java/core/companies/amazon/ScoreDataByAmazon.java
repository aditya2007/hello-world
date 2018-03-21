package com.java.core.companies.amazon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Given balanced tree, write a BFS to find the score and their count 
 *  and send those scores and count string with comma delimiter.
 * 
 * Example input stream : int[] scores = {4,2,5,2,6,5,1,4};
 *
 */
public class ScoreDataByAmazon {
	List<Node> nodes = new ArrayList<Node>();
	private LinkedList<Node>[] edges; //Edges will be represented as adjacency list
	Node root;
	boolean isEdgesCreated;
	int count;
	
	public String scoresString(int[] scores) {
		
		Map<Integer, Integer> scoresCountMap = calculateScoresCount(scores);
		edges = new LinkedList[scoresCountMap.size()];
		
		for( int i = 0; i < scores.length; i++ ) {
			if( scoresCountMap.get(scores[i]) != null ) {
				addNode(scores[i], scoresCountMap.get(scores[i]));	
				scoresCountMap.remove(scores[i]);
			}
		}
		printTree(root);
		
		return generateScoresCountStringUsingBFS();
		//return generateScoresCountStringUsingDFS();
	} //end of scoresString method
	
	/**
	 * Use BFS to parse the tree and generate a scores:count string
	 * @return
	 */
	private String generateScoresCountStringUsingBFS() {
		StringBuilder builder = new StringBuilder();
		Queue<Node> q = new LinkedBlockingQueue<Node>();
		//Queue<Node> q = new LinkedList<Node>(nodes);
		q.add(this.root);
		builder.append(root.toString()).append(",");
		printNode(this.root);
		root.wasNodeVisited = true;
		while( !q.isEmpty() ) {
			Node node = q.remove();
			System.out.println("Popped Node QUEUE :::: " + node);
			Node child = null;
			while( (child = getUnvisitedChildNodeAsAdjList(node)) != null ) {
				child.wasNodeVisited = true;
				builder.append(child.toString()).append(",");
				printNode(child);
				q.add(child);
			}
		}
		System.out.println("FINAL BFS based Scores String ::::::: " + builder);
		return builder.toString();
	}
	
	/**
	 * Use DFS to parse the tree and generate a scores:count string
	 * @return
	 */
	private String generateScoresCountStringUsingDFS() {
		StringBuilder builder = new StringBuilder();
		Stack<Node> stack = new Stack<>();

		stack.push(this.root);
		builder.append(root.toString()).append(",");
		root.wasNodeVisited = true;
		printNode(this.root);
		
		while( !stack.isEmpty() ) {
			Node node = stack.peek();
			Node child = getUnvisitedChildNodeAsAdjList(node);
			if( child != null ) {
				child.wasNodeVisited = true;
				printNode(child);
				builder.append(child.toString()).append(",");
				stack.push(child);
			}else {
				stack.pop();
			}
		}
		System.out.println("FINAL DFS based Scores String ::::::: " + builder);
		return builder.toString();
	}
	
	public void connectNodeAsAdjacencyList(Node start,Node end)
	{
		int startIndex = nodes.indexOf(start);
		System.out.println("STARt IDX >>>> " + startIndex + ", Start Node : " + start + ", End Node :: " + end);
		edges[startIndex].add(end);
		System.out.println("Linked List contains elemenst ::::: " 
							+ edges[startIndex] + ";; for start Node :::: " + start);
	}
	
	private Node getUnvisitedChildNodeAsAdjList(Node n)
	{
		
		LinkedList<Node> adjList = edges[nodes.indexOf(n)];
		System.out.println("### Adjcent List :: " + adjList + "; for Node :::: " + n.toString());
		if( adjList != null ) {
			for( Node next : adjList ) {
				if( next != null && !next.wasNodeVisited  ) {
					return next;
				}
			}
		}
		//adjList = null;
		return null;
	}
	
	private void createEdge(Node node) {
		LinkedList<Node> edge = new LinkedList<Node>();
		edge.add(node);
		edges[count++] = edge;
	}
	
	private void printNode(Node n)
	{
		System.out.println(n);
	}
	
	private Map<Integer, Integer> calculateScoresCount(int[] scores) {
		Map<Integer, Integer> scoresCountMap = new HashMap<Integer, Integer>();
		for( int i = 0; i < scores.length; i++ ) {
			if( scoresCountMap.get(scores[i]) != null ){
				scoresCountMap.put(scores[i], scoresCountMap.get(scores[i])+1);
			} else {
				scoresCountMap.put(scores[i], 1);
			}
		} //end of for loop
		System.out.println(scoresCountMap);
		return scoresCountMap;
	}
	
	//static class BinaryScoreTree<E> {
		public void addNode(int score, int scoreCount) {
			
			Node newNode = new Node(score, scoreCount);
			nodes.add(newNode);
			
			if( root == null ) {
				root = newNode;
				createEdge(newNode);
				System.out.println("Root Node >>> " + newNode);
			} else {
				Node current = root;
				Node parent;
				
				while(true) {
					parent = current;
					
					if( score < current.score ) {
						current = current.leftChild;
						if( current == null ) {
							parent.leftChild = newNode;
							createEdge(newNode);
							connectNodeAsAdjacencyList(parent, newNode);
							System.out.println("Left Node >>> " + newNode);
							return;
						}
					}// end of left child check
					else {
						current = current.rightChild;
						if( current == null ) {
							parent.rightChild = newNode;
							createEdge(newNode);
							connectNodeAsAdjacencyList(parent, newNode);
							System.out.println("Right Node >>> " + newNode);
							return;
						}
					} // end of right child check
				} //en dof while loop
			} //end of main else
			
		}// end of addNode method

	public void printTree(Node root) {
		if( root == null ) return;

		StringBuilder builder = new StringBuilder();
		Queue<Node> queue = new LinkedBlockingQueue<Node>();
		queue.add(root);
		int idx = 0, nextIdx = 1;
		while( !queue.isEmpty() ) {
			idx++;
			Node current = queue.poll();
			if( current != null ) {
				builder.append(current.score).append(" ");
				if( current.leftChild != null ) {
					queue.add(current.leftChild);
				}
				if( current.rightChild != null ) {
					queue.add(current.rightChild);
				}
			} //end of current!= null if loop

			if( idx == nextIdx ) {
				nextIdx += queue.size();
				builder.append("\n");
			}
		}//end of while loop
		System.out.println(builder);
	}

	static class Node {
			int score;
			int count;
			String delimiter;
			boolean wasNodeVisited;
			Node leftChild;
			Node rightChild;
			
			public Node(int score, int count) {
				this(score, count, ":", false);
			}

			public Node(int score, int count, String delimiter, boolean wasVisited ) {
				this.score = score;
				this.count = count;
				this.delimiter = delimiter;
				this.wasNodeVisited = wasVisited;
			}
			
			public String toString() {
				return this.score + this.delimiter + this.count; 
			}
			
		}//end of Node class
	//}// end of Binary Score Tree class
	
	
	public static void main(String[] args) {
		ScoreDataByAmazon scoresCal = new ScoreDataByAmazon();
		int[] scores = {4,2,5,2,6,5,1,4};
		//scoresCal.calculateScoresCount(scores);
		//scoresCal.scoresString(scores);

		//System.out.println(oddNumbers(3, 9));

	}
}//end of ScoreDataByAmazon class
