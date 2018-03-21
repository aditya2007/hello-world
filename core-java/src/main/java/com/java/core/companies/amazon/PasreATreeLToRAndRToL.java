package com.java.core.companies.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by 212590467 on 3/7/17
 *
 *          CEO
 *         /   \
 *        SVP1  SVP2
 *       /   \    /
 *      D1   D2  D3
 *     /  \
 *   P1   P2
 *
 *   Output : CEO, SVP2, SVP1, D1, D2, D3, P2, P1
 *
 *   https://www.careercup.com/question?id=5765154941698048
 */
public class PasreATreeLToRAndRToL {

    List<Node> nodes = new ArrayList<>();
    Node root;

    void parseTreeInBiDirection(Node node) {
        if( node == null ) {
            System.out.println("Tree is empty");
            return;
        } else {
            StringBuilder builder = new StringBuilder();
            //LinkedBlockingQueue<Node> queue = new LinkedBlockingQueue<>();
            /**
             * or ArrayDeque(performent one compared to LinkedList/Linkedqeueue).
             * The advantage of Deque is u can access elements on both ends
             */
            LinkedBlockingDeque<Node> queue = new LinkedBlockingDeque<>();
            Stack<Node> stack = new Stack();
            queue.offer(node);
            int level = 0, nextLevel = 1, index = 1;
            while( !queue.isEmpty() ) {
                level++;

                Node current = queue.poll();

                if( current != null ) {
                    if (index % 2 == 0) {
                        builder.append(stack.pop().designation).append(",");
                    } else {
                        builder.append(current.designation).append(",");
                    }

                    if (index % 2 != 0) {
                        if( current.left != null ) {
                            queue.offer(current.left);
                            stack.push(current.left);
                        }
                        if( current.right != null ) {
                            queue.offer(current.right);
                            stack.push(current.right);
                        }
                    } else {
                        if( current.left != null ) {
                            queue.offer(current.left);
                        }
                        if( current.right != null ) {
                            queue.offer(current.right);
                        }
                    }
                } // End of current if loop

                if( level == nextLevel ) {
                    nextLevel += queue.size();
                    index++;
                }
            } //End of while loop
            System.out.println(builder.toString());
        } // End of else
    }


    static class Node {
        String designation;
        Node left;
        Node right;

        public Node(String designation) {
            this.designation = designation;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "designation='" + designation + '\'' +
                    ", leftChild=" + left +
                    ", rightChild=" + right +
                    '}';
        }
    }//end of Node class
    //}// end of Binary Score Tree class

    private void printNode(Node n)
    {
        System.out.println(n);
    }

    public static void main(String[] args) {
        PasreATreeLToRAndRToL tree = new PasreATreeLToRAndRToL();
        tree.root = new Node("CEO");
        tree.root.left = new Node("SVP1");
        tree.root.right = new Node("SVP2");
        tree.root.left.left = new Node("D1");
        tree.root.left.right = new Node("D2");
        tree.root.right.left = new Node("D3");
        //tree.root.right.right = new Node("D4");
        tree.root.left.left.left = new Node("P1");
        tree.root.left.left.right = new Node("P2");

        tree.parseTreeInBiDirection(tree.root);
    }
}
