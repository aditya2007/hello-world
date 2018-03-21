package com.java.core.dsl.trees.heap;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *https://www.youtube.com/watch?v=HI97KDV23Ig
 *
 * Created by Yogananda Gowda - 212590467 on 5/23/17.
 */
public class HeapTree {

    private Node root;

    public static void main(String[] args) {
        HeapTree heapTree = new HeapTree();
        int[] input = { 1,14,10,8,7,9,3,2,4,6,5 };
        heapTree.createBinaryTree(input); // This is just for Visualization purpose

        //Max Heapi fy
        heapTree.buildMaxHeap(input);
        heapTree.createBinaryTree(input); // This is just for Visualization purpose

        // Min Heapify
        heapTree.buildMinHeap(input);
        heapTree.createBinaryTree(input); // This is just for Visualization purpose
    }

    private void buildMaxHeap(int[] input) {
        // First find all all leaf nodes indexes, by n/2 to n-1
        // Then start applying max heap from first non leaf idx, input.length/2 - 1 and go upto idx 0.
        int maxHeapStartIdx = input.length / 2 - 1;
        for( int i = maxHeapStartIdx; i >= 0; i--) { //
            maxHeapiFy(input, i);
        }
    }

    private void buildMinHeap(int[] input) {
        // First find all all leaf nodes indexes, by n/2 to n-1
        // Then start applying min heap from first non leaf idx, input.length/2 - 1 and go upto idx 0.
        int minHeapStartIdx = input.length / 2 - 1;
        for( int i = minHeapStartIdx; i >= 0; i--) { //
            minHeapiFy(input, i);
        }
    }

    private void maxHeapiFy(int[] input, int idx) {
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        int largest = 0;

        if( left < input.length && input[left] > input[idx] ) {
            largest = left;
        } else {
            largest = idx;
        }

        if( right < input.length && input[right] > input[largest] ) {
            largest = right;
        }

        if( largest != idx ) {
            int temp = input[idx];
            input[idx] = input[largest];
            input[largest] = temp;

            maxHeapiFy(input, largest);
        }
    }

    private void minHeapiFy(int[] input, int idx) {
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;
        int smallest = 0;

        if( left < input.length && input[left] < input[idx] ) {
            smallest = left;
        } else {
            smallest = idx;
        }

        if( right < input.length && input[right] < input[smallest] ) {
            smallest = right;
        }

        if( smallest != idx ) {
            int temp = input[idx];
            input[idx] = input[smallest];
            input[smallest] = temp;

            minHeapiFy(input, smallest);
        }
    }

    private void print(int[] input) {
        System.out.println(Arrays.toString(input));
    }

    public void createBinaryTree(int[] input) {

        /*Node node0 = new Node(input[0]);
        Node node1 = new Node(input[1]);
        Node node2 = new Node(input[2]);
        Node node3 = new Node(input[3]);
        Node node4 = new Node(input[4]);
        Node node5 = new Node(input[5]);
        Node node6 = new Node(input[6]);
        Node node7 = new Node(input[7]);
        Node node8 = new Node(input[8]);
        Node node9 = new Node(input[9]);
        Node node10 = new Node(input[10]);

        root = node0;
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node3.left = node7;
        node3.right = node8;
        node4.left = node9;
        node4.right = node10;*/
        root = createBinaryTree(input, root, 0);
        //createBinaryTree(input, 0);
        printTree(root);
    }

    public <E extends Number> void printTree(Node<E> root) {
        if( root == null ) return;

        StringBuilder builder = new StringBuilder();
        Queue<Node<E>> queue = new LinkedBlockingQueue<Node<E>>();
        queue.add(root);
        int idx = 0;
        int nextIdx = 1;
        while( !queue.isEmpty() ) {
            idx++;
            Node<E> current = queue.poll();
            if( current != null ) {
                builder.append(current.data).append(" ");
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
        } //end of while loop
        System.out.println(builder);
    }

    protected Node createBinaryTree(int[] input, Node root, int idx) {
        // Base case for recursion
        if (idx < input.length) {
            root = new Node(input[idx]);
            //root = temp;

            root.left = createBinaryTree(input, root.left, (2 * idx + 1));
            root.right = createBinaryTree(input, root.right, (2 * idx + 2));
        }
        //System.out.println("Root -> " + (root != null ? root.data : null));
    return root;
    }

    public void createBinaryTree(int[] input, int idx) {
        if( root == null ) {
            root = new Node(input[idx]);
        } else {
            Node current = root;
            Node parent;
            while (true) {
                int l = 2 * idx + 1;
                int r = 2 * idx + 2;

                parent = current;

                if( l < input.length ) {
                    current = current.left;
                    if ( current == null ) {
                        parent.left = new Node(input[l]);
                        return;
                    } // end of current == null check
                } else if (r < input.length){
                    current = current.right;
                    if ( current == null ) {
                        parent.right = new Node(input[r]);
                        return;
                    }
                } // end of if loop of lfet/right check

            } // end of while loop

        } // end of root == null if/else block
    }

    class Node<E> {
        Node<E> left;
        Node<E> right;
        E data;

        public Node(E data) {
            this.data = data;
        }
    }
}
