package com.java.core.dsl.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/discuss/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal
 *
 * This question appeared difficult to me but it is really just a simple in-order traversal! I got really frustrated
 * when other people are showing off Morris Traversal which is totally not necessary here.

 Let’s start by writing the in order traversal:

 private void traverse (TreeNode root) {
 if (root == null)
 return;
 traverse(root.left);
 // Do some business
 traverse(root.right);
 }
 So when we need to print the node values in order, we insert System.out.println(root.val)
 in the place of “Do some business”.

 What is the business we are doing here?
 We need to find the first and second elements that are not in order right?

 How do we find these two elements? For example, we have the following tree that is printed as in order traversal:

 6, 3, 4, 5, 2

 We compare each node with its next one and we can find out that
 6 is the first element to swap because 6 > 3 and 2 is the second element to swap because 2 < 5.

 Really, what we are comparing is the current node and its previous node in the “in order traversal”.
 */
public class RecoverBinarySearchTree {
	TreeNode first;
	TreeNode second;
	// The reason for this initialization is to avoid null pointer exception
	// in the first comparison when prevElement has not been initialized
	TreeNode prev = new TreeNode(Integer.MIN_VALUE);

	private void recoverTree(TreeNode root) {
		//In order traversal to find the two elements
		traverse(root);

		//swap the values of two nodes
		int temp = first.val;
		first.val = second.val;
		second.val = temp;
	}

	private void traverse(TreeNode root) {

		if (root == null) {
			return;
		}

		traverse(root.left);
		//Start : Do some business logic here
		//If first element has not been found, assign it to prev element (refer to 6 in the above example)
		if (first == null && prev.val >= root.val) {
			first = prev;
		}

		//If first element found, assign the second element to the root (refer 2 in the example above)
		if (first != null && prev.val >= root.val) {
			second = root;
		}
		prev = root;
		//End : business logic

		traverse(root.right);
	}

	private static void testRecoverBST() {
		RecoverBinarySearchTree bst = new RecoverBinarySearchTree();

		TreeNode node1 = new TreeNode(4);
		TreeNode node2 = new TreeNode(6);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(3);
		TreeNode node5 = new TreeNode(2);

		node1.left = node2;
		node1.right = node3;
		node2.right = node4;
		node3.right = node5;

		TreesUtil.printTree(node1);
		TreesUtil.inOrderTraversel(node1);
		System.out.println();
		bst.recoverTree(node1);
		System.out.println();
		TreesUtil.inOrderTraversel(node1);
		System.out.println();
		TreesUtil.printTree(node1);
	}

	/**
	 * Given two binary trees, write a function to check if they are the same or not.
  	 * Two binary trees are considered the same if they are structurally identical
	 * and the nodes have the same value.
     *
	 * Example 1:

	 Input:     1         1
	 / \       / \
	 2   3     2   3

	 [1,2,3],   [1,2,3]

	 Output: true
	 Example 2:

	 Input:     1         1
	 /           \
	 2             2

	 [1,2],     [1,null,2]

	 Output: false
	 Example 3:

	 Input:     1         1
	 / \       / \
	 2   1     1   2

	 [1,2,1],   [1,1,2]

	 Output: false
     *
	 */
	private static void testTreesAreSame() {
		RecoverBinarySearchTree bst = new RecoverBinarySearchTree();

		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		//TreeNode node3 = new TreeNode(3);
		node1.left = node2;
		//node1.right = node3;
		TreesUtil.printTree(node1);
		System.out.println();

		TreeNode node11 = new TreeNode(1);
		TreeNode node21 = new TreeNode(2);
		//TreeNode node31 = new TreeNode(3);
		//node11.left = node21;
		node11.right = node21;
		TreesUtil.printTree(node11);

		System.out.println(bst.isSameTree(node1, node11));
	}

	private boolean isSameTree(TreeNode n1, TreeNode n2) {
		if (n1 == null && n2 == null) return true;
		if (n1 == null || n2 == null) return false;
		if (n1.val == n2.val) {
			return isSameTree(n1.left, n2.left) && isSameTree(n1.right, n2.right);
		}
		return false;
	}

	private void consructBinaryTree(int[] input) {
		if (input == null || input.length == 0) return;

		TreeNode root = null;
		if (input.length == 1 ) {
			root = new TreeNode(input[0]);
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if (root == null) {
				root = new TreeNode(input[i]) ;
			} else {
				
			}
		}




	}

	public static void main(String[] args) {
		//testRecoverBST();
		testTreesAreSame();
	}

}
