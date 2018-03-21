package com.java.core.dsl.trees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Yogananda Gowda - 212590467 on 10/17/17.
 *
 *  This binary tree is symmetric
 *                 1
 *             /       \
 *            2         2
 *          /  \      /  \
 *         3    4    4    3
 *
 *
 * This binary tree is not valid
 *              1
 *             /  \
 *            2    2
 *            \     \
 *             3    3
 *
 *
 *
 */
public class SymmetricOrMirrorOfATree {

    private Node<Integer> root;

    public static void main(String[] args) {
        SymmetricOrMirrorOfATree mirrorTree = new SymmetricOrMirrorOfATree();

        // Check whether the binary tree is Symmetric(Mirror Image)
        Node<Integer>  n1 = new Node<Integer>(1);
        mirrorTree.root = n1;
        Node<Integer>  n2l = new Node<Integer>(2);
        Node<Integer>  n2r = new Node<Integer>(2);
        Node<Integer>  n3l = new Node<Integer>(3);
        Node<Integer>  n3r = new Node<Integer>(3);
        Node<Integer>  n4l = new Node<Integer>(4);
        Node<Integer>  n4r = new Node<Integer>(4);

        // Symmetric tree
        n1.lefChild = n2l;
        n1.rightChild = n2r;
        n2l.lefChild = n3l;
        n2l.rightChild = n4r;
        n2r.lefChild = n4l;
        n2r.rightChild = n3r;

        mirrorTree.printTree(mirrorTree.root);
        System.out.println(mirrorTree.isSymmetricIterative(mirrorTree.root));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // Non Symmetric tree
        n1.lefChild = n2l;
        n1.rightChild = n2r;
        n2l.lefChild = n4r;
        n2l.rightChild = n3l;
        n2r.lefChild = n4l;
        n2r.rightChild = n3r;

        mirrorTree.printTree(mirrorTree.root);
        System.out.println(mirrorTree.isSymmetricIterative(mirrorTree.root));

    }

    //https://leetcode.com/problems/symmetric-tree/solution/
    //Geeks for Geeks Solution(http://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/)
    //return true if the tree is Symmetric i.e mirror image itself
    private boolean isSymmetricRecurssive(Node root) {
        //check if tree is mirror of itself
        return isMirror(root, root);
    }

    // return true if trees with roots as root1 and root2 are mirror
    private boolean isMirror(Node root1, Node root2) {

        // if both trees are empty, then they are mirror image
        if (root1 == null && root2 == null) {
            return true;
        }

        // For two trees to be mirror images, the following three conditions must be true
        // 1 - Their's root node data must be same
        // 2 - left subtree of left tree and right sub tree of right tree have to be mirror images
        // 3 - right subtree of left tree and left sub tree of right tree have to be mirror images
        if (root1 != null && root2 != null && root1.data == root2.data) {
            return (isMirror(root1.lefChild, root2.rightChild) && isMirror(root1.rightChild, root2.lefChild));
        }

        // If neither of the above conditions is true then root1 and root2 are mirror images
        return false;
    }

    public boolean isSymmetricIterative(Node root) {
        Queue<Node> q =  new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            Node n1 = q.poll();
            Node n2 = q.poll();

            if (n1 == null && n2 == null) return true;
            if (n1 == null || n2 == null) return false;
            if (n1.data != n2.data) return false;
            q.add(n1.lefChild);
            q.add(n2.rightChild);
            q.add(n1.rightChild);
            q.add(n2.lefChild);
        }
        return true;
    }


    public void printTree(Node root) {
        if( root == null ) return;

        StringBuilder builder = new StringBuilder();
        Queue<Node> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        int idx = 0, nextIdx = 1;
        while( !queue.isEmpty() ) {
            idx++;
            Node current = queue.poll();
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


    public static class Node<E> {
        Integer data;
        Node<E> lefChild;
        Node<E> rightChild;

        public Node() {}

        public Node(Integer data) {
            this.data = data;
        }
    }

}
