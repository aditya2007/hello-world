package com.java.core.dsl.trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yogananda Gowda - 212590467 on 3/26/17.
 *
 * http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/
 *
 * A binary search tree (BST) is a node based binary tree data structure which has the following properties.
    • The left subtree of a node contains only nodes with keys less than the node’s key.
    • The right subtree of a node contains only nodes with keys greater than the node’s key.
    • Both the left and right subtrees must also be binary search trees.
 *
 *                                        4
 *                                       / \
 *                                      /   \
 *                                      2    5
 *                                    /  \
 *                                   1    3
 */
public class ValidBST {

    /** Root of the binary tree */
    Node root;

    /** To keep track of previous node for InOrder Traversal approach only*/
    Node prev;

    //An auxillary array to hold Inorder traversal elements
    List<Integer> inOrderList = new ArrayList<>();

    /** can give min and max value according to your code or
    can write a function to find min and max value of tree. */

    /** returns true if given search tree is binary
        search tree (efficient version)
     Time Complexity: O(n)
     Auxiliary Space : O(1) if Function Call Stack size is not considered, otherwise O(n)
    */
    public boolean isValidBST() {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        //return isValidBST(root, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY);
    }

    /** Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    public boolean isValidBST(Node node, int min, int max) {

        /** an empty tree is BST */
        if( node == null ) return true;

        /** false if this node violates the min/max constraints */
        if( node.data < min || node.data > max ) return false;

        //return ( isValidBST( node.left, min, node.data - 1) && isValidBST(node.right, node.data + 1, max));
        return ( isValidBST( node.left, min, node.data) && isValidBST(node.right, node.data, max));
    }


    /** METHOD 4(Using In-Order Traversal)

        1) Do In-Order Traversal of the given tree and store the result in a temp array.
        2) Check if the temp array is sorted in ascending order, if it is, then the tree is BST.

        Time Complexity: O(n)

         We can avoid the use of Auxiliary Array. While doing In-Order traversal,
         we can keep track of previously visited node. If the value of the currently
         visited node is less than the previous value, then tree is not BST.
     */

    boolean isValidBST1() {
        prev = null;
        return isValidBST(root);
    }

    boolean isValidBST(Node node) {
        /** Traverse the tree in inorder fashion and keep a
         * track of previous node
         */
        if( node != null ) {
            if( !isValidBST(node.left)) {
                return false;
            }

            /** Allows only distinct values node */
            if( prev != null && node.data <= prev.data) {
                return false;
            }
            prev = node;
            return isValidBST(node.right);
        }
        return true;
    }

    boolean isValidBST2() {
        return isValidBST2(root);
    }

    boolean isValidBST2(Node node) {
        inOrderTraversal(node);
        System.out.println(inOrderList);
        return isSortedList(inOrderList);
    }

    protected void inOrderTraversal(Node node) {
        System.out.println("Entering of inOrderTraversal : "
                + (node != null ? node.data : node));
        if( node != null ) {
            inOrderTraversal(node.left);
            System.out.println("Visiting a node : " + node.data);
            inOrderList.add(node.data);
            inOrderTraversal(node.right);
        }
    }

    private boolean isSortedList(List<Integer> list)
    {
        if (list == null || list.isEmpty())
            return false;

        if (list.size() == 1)
            return true;

        for (int i = 1; i < list.size(); i++)
        {
            if (list.get(i).compareTo(list.get(i - 1)) < 0 )
                return false;
        }
        return true;
    }

    static class Node {
        int data;
        Node left, right;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    public static void main(String[] args) {
        ValidBST tree = new ValidBST();

        tree.root = new Node(4);
        tree.root.left = new Node(2);
        tree.root.right = new Node(5);
        tree.root.left.left = new Node(1);
        tree.root.left.right = new Node(3);

        if( tree.isValidBST2() ) {
            System.out.println("It's a valid BST");
        } else {
            System.out.println("It's not a valid BST");
        }
    }
}
