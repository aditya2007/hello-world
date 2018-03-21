package com.java.core.dsl.graphs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yogananda Gowda - 212590467 on 6/25/17.
 */
public class HackRankGraph {
    private Map<Integer, Node> nodeLookup = new HashMap<>();

    public static class Node {
        private int data;
        List<Node> adjacent = new LinkedList<>();
        private Node(int data) {
            this.data = data;
        }
    } // end of node class

    private Node getNode(int data) {
        return nodeLookup.get(data);
    }

    public void addEdge(int source, int des) {
        Node s = getNode(source);
        Node d = getNode(des);
        s.adjacent.add(d);
        d.adjacent.add(s);
    }

    public boolean hasPathDFS(int source, int des) {
        Node s = getNode(source);
        Node d = getNode(des);
        Set<Integer> visited = new HashSet<>();
        return hasPathDFS(s,d,visited);
    }

    private boolean hasPathDFS(Node s, Node d, Set<Integer> visited) {
        if (visited.contains(s.data)) {
            return false;
        }
        visited.add(s.data);
        if (s.data == d.data) {
            return true;
        }
        for (Node child : s.adjacent) {
            if (hasPathDFS(child, d, visited)) return true;
        }
        return false;
    }

    public boolean hasPathBFS(int source, int des) {
        return hasPathBFS(getNode(source), getNode(des));
    }

    private boolean hasPathBFS(Node source, Node des) {
        LinkedList<Node> nextToVisit = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        nextToVisit.add(source);
        while (!nextToVisit.isEmpty()) {
            Node node = nextToVisit.poll();
            if (node.data == des.data) return true;

            if (visited.contains(node.data)) continue;

            visited.add(node.data);

            for (Node child : node.adjacent) {
                nextToVisit.add(child);
            }
        }
        return false;
    }

    private List<Node> findAllAdjecntNodes(int startNode) {
        return nodeLookup.get(startNode).adjacent;
    }


    public static void main(String[] args) {
        HackRankGraph g = new HackRankGraph();
        g.nodeLookup.put(1,new Node(1));
        g.nodeLookup.put(2,new Node(2));
        g.nodeLookup.put(3,new Node(3));
        g.nodeLookup.put(4,new Node(4));
        g.nodeLookup.put(5,new Node(5));
        g.nodeLookup.put(6,new Node(6));
        g.nodeLookup.put(7,new Node(7));
        g.nodeLookup.put(8,new Node(8));
        g.nodeLookup.put(9,new Node(9)); //Just a dummy node to test non reachable path

        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(1,6);
        g.addEdge(2,3);
        g.addEdge(3,6);
        g.addEdge(4,6);
        g.addEdge(6,5);
        g.addEdge(6,8);
        g.addEdge(8,7);

        boolean hasDFSPath = g.hasPathDFS(1,7);
        System.out.println("Is the Graph has DFS path <<<<<<>>>>> " + hasDFSPath);

        boolean hasBFSPath = g.hasPathBFS(1,7);
        System.out.println("Is the Graph has BFS path *********** " + hasBFSPath);

        g.findAllAdjecntNodes(8).stream().forEach(e -> System.out.println(e.data));

    }
}
