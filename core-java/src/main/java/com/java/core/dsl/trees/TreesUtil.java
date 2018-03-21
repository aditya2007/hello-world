package com.java.core.dsl.trees;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class TreesUtil {

	public static void printTree(TreeNode root) {
		if( root == null ) return;

		StringBuilder builder = new StringBuilder();
		Queue<TreeNode> queue = new LinkedBlockingQueue<>();
		queue.add(root);
		int idx = 0, nextIdx = 1;
		while( !queue.isEmpty() ) {
			idx++;
			TreeNode current = queue.poll();
			if( current != null ) {
				builder.append(current.val).append(" ");
				if( current.left != null ) {
					queue.add(current.left);
				}
				if( current.right != null ) {
					queue.add(current.right);
				}
			} //end of current!= null if loop

			if( idx == nextIdx ) {
				nextIdx += queue.size();
				builder.append("\n");
			}
		}//end of while loop
		System.out.println(builder);
	}

	static void inOrderTraversel(TreeNode root) {
		if (root != null) {
			inOrderTraversel(root.left);
			System.out.print(root.val);
			System.out.print(",");
			inOrderTraversel(root.right);
		}
	}

	static void preOrderTraversal(TreeNode root) {
		if (root != null) {
			System.out.print(root.val);
			System.out.print(",");
			preOrderTraversal(root.left);
			preOrderTraversal(root.right);
		}
	}

	static void preOrderTraversal(TreeNode root, List<Integer> list) {
		if (root == null) {
			list.add(null);
			return;
		}
		list.add(root.val);
		preOrderTraversal(root.left, list);
		preOrderTraversal(root.right, list);

	}
}
