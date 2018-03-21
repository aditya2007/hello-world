package com.java.core.dsl.trees;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Binary Tree : 
 *    If every node in a tree can have at most two children, the tree is called a binary tree.
 * 
 * Binary Search Tree :
 * 		The defining characteristic of a binary search tree is this : A node's left child must
 *      have a key less than its parent, and a node's right child must have a key greater than 
 *      or equal to its parent. 
 *
 */
public class DSLBinarySearchTree<E> {
	public Node<E> root;
	static int steps;
	
	public void add(Integer data) {
		Node<E> newNode = new Node<E>();
		newNode.data = data;
		
		if( root == null ) {
			root = newNode;
			//System.out.println("Root Node : " + root.data);
		} else {
			Node<E> current = root;
			Node<E> parent;
			//Arrays.sort(new int[10]);
			while (true) {
				parent = current;
				
				//if( ((Integer)data) < ((Integer)(current.data)) ) {
				if( data < current.data ) {
					current = current.lefChild;
					if ( current == null ) {
						parent.lefChild = newNode;
						//System.out.println("Left Node : " + parent.lefChild.data);
						return;
					} // end of current == null check
				} else {
					current = current.rightChild;
					if ( current == null ) {
						parent.rightChild = newNode;
						//System.out.println("Right Node : " + parent.rightChild.data);
						return;
					}
				} // end of if loop of lfet/right check
				
			} // end of while loop
			
		} // end of root == null if/else block
	}
	
	protected void addToLeft(Integer data) {
		Node<E> newNode = new Node<E>();
		newNode.data = data;

		Node<E> current = root;
		Node<E> parent;
		Arrays.sort(new int[10]);
		while (true) {
			parent = current;

			//if( ((Integer)data) < ((Integer)(current.data)) ) {
			if (data < current.data) {
				current = current.lefChild;
				if (current == null) {
					parent.lefChild = newNode;
					//System.out.println("Left Node : " + parent.lefChild.data);
					return;
				} // end of current == null check
			} else {
				current = current.rightChild;
				if (current == null) {
					parent.rightChild = newNode;
					//System.out.println("Right Node : " + parent.rightChild.data);
					return;
				}
			} // end of if loop of lfet/right check

		} // end of while loop
	}

	/**
	 * Create a BST for given input array of sorted integers
	 * @param input
	 * @param startIdx
	 * @param endIdx
	 */
	protected Node<Integer> createBST(int[] input, int startIdx, int endIdx) {
		if ( startIdx > endIdx ) return null;

		int midIdx = (startIdx + endIdx) / 2;
		Node<Integer> localRoot = new Node<>(input[midIdx]);

		localRoot.lefChild = createBST(input, startIdx, midIdx - 1);
		localRoot.rightChild = createBST(input, midIdx + 1, endIdx);

		return localRoot;
	}

	protected boolean isTreeHeightBalanced(Node<Integer> root) {
		return checkForBalance(root) == -1 ? false :true;
	}

	private int checkForBalance(Node<Integer> root) {
		if (root == null ) return 0;

		int lh = checkForBalance(root.lefChild);
		if (lh == -1) return -1;

		int rh = checkForBalance(root.rightChild);
		if (rh == -1) return -1;

		if (Math.abs(lh - rh)  > 1) return -1;

		return 1 + Math.max(lh, rh);
	}


	protected Node<E> find(Integer data) {
		Node<E> current = root;
		while (data != current.data) {
			
			if (data < current.data) {
				current = current.lefChild;
			} else {
				current = current.rightChild;
			} // end of if/else for data check
			
			if (current == null) return null;
		} // end of while loop
		
		return current;
	}
	
	/**
	 * 1. Call itself to traverse  the node's left subtree
	 * 2. Visit the node
	 * 3. Call itself to traverse the node's right subtree
	 * For a given tree, it should visit/print left-root-right nodes,
	 * which helps to sort in ascending order
	 * 
	 */
	public void inOrderTraversal(Node<E> root) {
//		System.out.println("Entering of inOrederTraversal : "
//							+ (root != null ? root.data : root));
		if( root != null ) {
			inOrderTraversal(root.lefChild);
			System.out.print(root.data + " ");
			inOrderTraversal(root.rightChild);
		}
		steps++;
	}
	
	protected void inOrderTraversalIterative(Node<E> root) {
		Stack<Node> stack = new Stack();
		traverseLeftNodes(root, stack);

		while (!stack.isEmpty()) {
			Node curr = stack.pop();
			System.out.print(curr.data + ", ");
			if (curr.rightChild != null) {
				traverseLeftNodes(curr.rightChild, stack);
			}
		}
	}

	void traverseLeftNodes(Node<E> node, Stack<Node> stack) {
		while( node != null ) {
			stack.push(node);
			node = node.lefChild;
		}
	}

	/**
	 * 1. Visit the node
	 * 2. Call itself to traverse  the node's left subtree
	 * 3. Call itself to traverse the node's right subtree
	 * For a given tree, it should visit root-left-right nodes
	 * which helps to evaluate algebraic expression ( *A+BC ==> Prefix notation).
	 * Note : For Algebraic expression, to be used as ordinary binary tree, not BST 
	 */
	protected void preOrderTraversal(Node<E> root) {
		if( root != null ) {
			System.out.print(root.data + " ");
			preOrderTraversal(root.lefChild);
			preOrderTraversal(root.rightChild);
		}
		steps++;
	}

	public ArrayList<Integer> preorderItr(Node root) {
		ArrayList<Integer> preorderedList =  new ArrayList<Integer>();
		Stack<Node> s = new Stack<>();

		if (root == null){return preorderedList;}

		s.push(root);
		while(!s.isEmpty()) {
			root = s.pop();
			//preorderedList.add(root.data);
			System.out.print(root.data + ",");
			if (root.rightChild != null) {s.push(root.rightChild);}
			if (root.lefChild  != null) {s.push(root.lefChild);}
		}
		return preorderedList;
	}

	/**
	 * 1. Call itself to traverse  the node's left subtree
	 * 2. Call itself to traverse the node's right subtree
	 * 3. Visit the node
	 * For a given tree, it should visit left-right-root nodes
	 * which helps to evaluate algebraic expression( ABC+* ==> Postfix notation)
	 * Note : For Algebraic expression, to be used as ordinary binary tree, not BST
	 */
	protected void postOrderTraversal(Node<E> root) {
		if( root != null ) {
			postOrderTraversal(root.lefChild);
			postOrderTraversal(root.rightChild);
			System.out.print(root.data + " ");
		}
		steps++;
	}

	protected Node<E> findMinimumNode(Node<E> root) {
		Node<E> current = root;
		Node<E> last = root;
		while( current != null ) {
			last = current;
			current = current.lefChild;
		}
		return last;
	}
	
	protected Node<E> findMaximumNode(Node<E> root) {
		Node<E> current = root;
		Node<E> last = root;
		while( current != null ) {
			last = current;
			current = current.rightChild;
		}
		return last;
	}
	
	public void printTree(Node<E> root) {
		if( root == null ) return;
		
		StringBuilder builder = new StringBuilder();
		Queue<Node<E>> queue = new LinkedBlockingQueue<Node<E>>();
		queue.add(root);
		int idx = 0, nextIdx = 1;
		while( !queue.isEmpty() ) {
			idx++;
			Node<E> current = queue.poll();
			if( current != null ) {
				builder.append(current.data).append(" ");
				if( current.lefChild != null ) {
					queue.add(current.lefChild);
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
	
	public void printTree1(Node<E> root) {
        Queue<Node<E>> q = new LinkedList<Node<E>>();
        q.add(root);

        while(!q.isEmpty()){
            int numNodesAtlevel = q.size();
            while (numNodesAtlevel > 0){
                Node<E> a= q.poll();
                System.out.print(a.data +" ");
                if(a.lefChild != null)
                    q.add(a.lefChild);
                if(a.rightChild != null)
                    q.add(a.rightChild);

                numNodesAtlevel --;
            }

            System.out.println();
        }
    }

	public static void path(Node root, ArrayList<String> pathList, String builder){
		if(root.lefChild == null && root.rightChild == null){
			pathList.add(builder+root.data);
		}else{
			String s= root.data+"->";
			if(root.lefChild != null){
				path(root.lefChild, pathList,builder+s);
			}
			if(root.rightChild != null){
				path(root.rightChild, pathList, builder+s );
			}
		}
	}
	
	public static void main(String[] args) {
		DSLBinarySearchTree<Integer> bst = new DSLBinarySearchTree<Integer>();
		
		/*bst.add(63);
		bst.add(27);
		bst.add(80);
		bst.add(13);
		bst.add(51);
		bst.add(70);
		bst.add(26);
		bst.add(33);
		bst.add(82);
		bst.add(58);
		bst.add(57);
		bst.add(60);
		bst.add(92);*/
		
		//This is for printTree test. 
		/*bst.add(9);
		bst.add(7);
		bst.add(10);
		bst.add(2);
		bst.add(6);
		bst.add(12);
		bst.add(1);
		bst.add(3);
		bst.add(8);*/
		
		Node<Integer>  n1 = new Node<Integer>(1);
		bst.root = n1;
		Node<Integer>  n3 = new Node<Integer>(3);
		Node<Integer>  n5 = new Node<Integer>(5);
		Node<Integer>  n2 = new Node<Integer>(2);
		Node<Integer>  n4 = new Node<Integer>(4);
		Node<Integer>  n7 = new Node<Integer>(7);
		Node<Integer>  n9 = new Node<Integer>(9);
		Node<Integer>  n6 = new Node<Integer>(6);
		Node<Integer>  n8 = new Node<Integer>(8);

		bst.root.lefChild = n3;
		bst.root.rightChild = n5;
		n3.lefChild = n2;
		n3.rightChild = n4;
		n5.rightChild = n7;
		n2.lefChild = n9;
		n2.rightChild = n6;
		n4.lefChild = n8;

		// This construction to test Heap Tree
		/*Node<Integer>  n1 = new Node<Integer>(14);
		bst.root = n1;
		Node<Integer>  n2 = new Node<Integer>(8);
		Node<Integer>  n3 = new Node<Integer>(10);
		Node<Integer>  n4 = new Node<Integer>(4);
		Node<Integer>  n5 = new Node<Integer>(7);
		Node<Integer>  n6 = new Node<Integer>(9);
		Node<Integer>  n7 = new Node<Integer>(3);
		Node<Integer>  n8 = new Node<Integer>(1);
		Node<Integer>  n9 = new Node<Integer>(2);
		Node<Integer>  n10 = new Node<Integer>(6);

		bst.root.lefChild = n2;
		bst.root.rightChild = n3;
		n2.lefChild = n4;
		n2.rightChild = n5;
		n3.lefChild = n6;
		n3.rightChild = n7;
		n4.lefChild = n8;
		n4.rightChild = n9;
		n5.lefChild = n10;*/

		bst.printTree(bst.root);

		//Node<Integer> foundNode = bst.find(12);
		//System.out.println("Is Node found for given key ?? " + (foundNode != null ? "YES" : "NO"));

		//System.out.println("InOrder Traversal >>>>>> ");
		//bst.inOrderTraversal(bst.root);
		//bst.inOrderTraversalIterative(bst.root);
		//System.out.println("\n Number of steps to a traverse a tree in Order : " + steps);

		//System.out.println("\n Pre Order Traversal >>>>> ");
		//steps = 0;
		//bst.preOrderTraversal(bst.root);
		//bst.preorderItr(bst.root);
		//System.out.println("\n Number of steps to a traverse a tree pre Order : " + steps);

		//System.out.println("\n Post Order Traversal >>>>> ");
		//steps = 0;
		bst.postOrderTraversal(bst.root);
		//System.out.println("\n Number of steps to a traverse a tree post Order : " + steps);
//		bst.printTree(bst.root);
//		Node<Integer> minNode = bst.findMinimumNode(bst.root);
//		System.out.println("Min Node in the tree ?? " + minNode.data);
//
//		Node<Integer> maxNode = bst.findMaximumNode(bst.root);
//		System.out.println("Max Node in the tree ?? " + maxNode.data);

		// This is to test creation of BST for a sorted array
		/*int[] input = {1,2,3,4,5,6,7,8};
		Node<Integer> localRoot = bst.createBST(input, 0, input.length - 1);
		bst.printTree(localRoot);*/

		//This is to to test whether the given tree is Hieight balanced
		/*int[] input = {0,1,2,3,4,5,6};
		Node<Integer> localRoot = bst.createBST(input, 0, input.length - 1);
		bst.printTree(localRoot);
		boolean isBalanced = bst.isTreeHeightBalanced(localRoot);
		System.out.println("Is Tree Height Balanced >>>>>>> " + isBalanced + "\n");*/

		/*bst.root = localRoot;
		Node<Integer> node4 = bst.find(4);
		Node<Integer> node5 = bst.find(5);
		Node<Integer> node6 = bst.find(6);
		node4.rightChild = node6;
		node5.rightChild = null;
		bst.printTree(localRoot);
		isBalanced = bst.isTreeHeightBalanced(localRoot);
		System.out.println("Is Tree Height Balanced Now <<<<<<>>>>>>> " + isBalanced);*/

		// To check the height of the tree (height of the tree is longet path[number of edges] from given node/root to leaf)
		// Depth of the tree is number of edges from root to that node.
		/*Node<Integer>  n1 = new Node<Integer>(1);
		bst.root = n1;
		Node<Integer>  n2 = new Node<Integer>(2);
		Node<Integer>  n3 = new Node<Integer>(3);
		Node<Integer>  n4 = new Node<Integer>(4);
		Node<Integer>  n5 = new Node<Integer>(5);
		Node<Integer>  n6 = new Node<Integer>(6);
		Node<Integer>  n7 = new Node<Integer>(7);
		Node<Integer>  n8 = new Node<Integer>(8);
		Node<Integer>  n9 = new Node<Integer>(9);
		Node<Integer>  n10 = new Node<Integer>(10);

		n1.lefChild = n2;
		n1.rightChild = n3;
		n2.lefChild = n4;
		n2.rightChild = n5;
		n3.lefChild = n6;
		n3.rightChild = n7;
		n4.lefChild = n8;
		n4.rightChild = n9;
		//n9.lefChild = n10;
		bst.printTree(bst.root);
		//System.out.println(bottomsUp(bst.root));
		levelOrderBottom(bst.root);
		/*bst.printTree(bst.root);
		System.out.println(findHeightOfATree(bst.root));
		System.out.println(bst.isTreeHeightBalanced(bst.root));*/

		/**
		 * Google
		 * Given a binary tree, output the maximum EVEN sum along any path
		 *               10
		 *             /    \
		 *            2      5
		 *          /  \      \
		 *         1   101     13
		 *
		 *         Maximum even sum = 101 + 2 + 10 + 5 = 118
		 */

		/*Node<Integer>  n10 = new Node<Integer>(10);
		bst.root = n10;
		Node<Integer>  n2 = new Node<Integer>(2);
		Node<Integer>  n5 = new Node<Integer>(5);
		Node<Integer>  n1 = new Node<Integer>(1);
		Node<Integer>  n101 = new Node<Integer>(101);
		Node<Integer>  n13 = new Node<Integer>(13);

		n10.lefChild = n2;
		n10.rightChild = n5;
		n2.lefChild = n1;
		n2.rightChild = n101;
		n5.rightChild = n13;

		bst.printTree(bst.root);

		System.out.println(bst.maxPathSum(bst.root));*/
	}

	int maxValue;
	int maxEvenSum;
	//https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39775/Accepted-short-solution-in-Java
	public int maxPathSum(Node root) {
		maxValue = Integer.MIN_VALUE;
		maxPathDown(root);
		return maxValue;
		//return maxEvenSum;
	}

	private int maxPathDown(Node node) {
		if (node == null) return 0;
			int lVal = Math.max(0, maxPathDown(node.lefChild));
			int rVal = Math.max(0, maxPathDown(node.rightChild));
			maxValue = Math.max(maxValue, (lVal + rVal + node.data));
			/*if ( maxValue % 2 == 0 ) {
				maxEvenSum = maxValue;
			}*/
			return Math.max(lVal, rVal) + node.data;
	}

	public static int findHeightOfATree(Node root) {

		if ( root == null ) return -1;
		int leftHt = findHeightOfATree(root.lefChild);
		System.out.println((root.lefChild != null ? root.lefChild.data : null) + " Left Node Height : " + leftHt);
		int rightHt = findHeightOfATree(root.rightChild);
		System.out.println((root.rightChild != null ? root.rightChild.data : null) + " Right Node Height : " + rightHt);

		int maxHeight = Math.max(leftHt, rightHt) + 1;
		System.out.println("Max Height >>> " + maxHeight);

		return maxHeight;
		//return Math.max(findHeightOfATree(root.lefChild), findHeightOfATree(root.rightChild)) + 1;
	}


	/**
	 * Example to print level order bottomsUp tree contiguosly
	 * Input :      1
	 *           2     3
	 *        4    5 6   7
	 * Output : 4567231
	 * @param root
	 * @return
	 */
    public static String bottomsUp(Node root) {
		StringBuilder sb = new StringBuilder();
		Queue<Node> q = new LinkedList<>();
		Stack<Integer> s = new Stack<>();

		q.add(root);
		while(!q.isEmpty()) {
			Node n = q.poll();
			s.push(n.data);
			if (n.rightChild != null) {
				q.add(n.rightChild);
			}
			if (n.lefChild != null) {
				q.add(n.lefChild);
			}
		}
		while (!s.isEmpty()) {
			sb.append(s.pop());
		}
		//return sb.reverse().toString();
		return sb.toString();
	}

	/**
	 * Example to print level order bottomsUp tree level by level
	 * Input :      1
	 *           2     3
	 *        4    5 6   7
	 * Output : 4567
	 *           23
	 *           1
	 * @param root
	 * @return
	 */
	//public static List<List<Integer>> levelOrderBottom(Node<Integer> root) {
	public static void levelOrderBottom(Node<Integer> root) {
		//List<List<Integer>> result = new ArrayList<List<Integer>>();
		//if(root==null) return result;
		//Stack<List<Integer>> result = new Stack<>();
		Stack<String> strResult = new Stack<>();

		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while (q.size() > 0) {
			//List<Integer> list = new ArrayList<>();
			StringBuilder builder = new StringBuilder();
			int size = q.size();
			for (int i = 0; i < size; i++) {
				if ( i != 0) {
					builder.append(" ");
				}
				Node<Integer> node = q.poll();
				//list.add(node.data);
				builder.append(node.data);
				if (node.lefChild != null) {
					q.add(node.lefChild);
				}
				if (node.rightChild != null) {
					q.add(node.rightChild);
				}
			}
			//result.add(0,list);
			//result.push(list);
			strResult.push(builder.toString());
		}
		//return result;
		while (!strResult.isEmpty()) {
			//System.out.println(Arrays.toString(result.pop().toArray()));
			System.out.println(strResult.pop());
//			System.out.print(result.pop().stream()
//			.map(Object::toString).collect(Collectors.joining(",")));
//			System.out.println();
		}
	}

	public static class Node<E> {
		Integer data;
		Node<E> lefChild;
		Node<E> rightChild;
		
		public Node() {}
		
		public Node (Integer data) {
			this.data = data;
		}
	}

}
