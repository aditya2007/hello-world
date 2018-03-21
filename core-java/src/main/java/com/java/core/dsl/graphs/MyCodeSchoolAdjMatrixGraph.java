package com.java.core.dsl.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Yogananda Gowda - 212590467 on 6/26/17.
 */
public class MyCodeSchoolAdjMatrixGraph<E> {
    //A vertex list map - to look up an index of a given node
    private Map<E, Integer> indexToNodeDataMap = new HashMap<>();
    //Adjacency matrix to store all nodes connection/edges(start and end vertices index)
    //private Edge<E>[][] adjMatrix = new Edge[10][10];
    private Edge<E>[][] adjMatrix = new Edge[8][8];
    private int idx;

    public void createAdjacencyMatrix(E startVrtx, E endVrtx) {
        int rowIdx = getIndex(startVrtx);
        int colIdx = getIndex(endVrtx);
        adjMatrix[rowIdx][colIdx] = new Edge(startVrtx, endVrtx);
    }

    public void createIndexToNodeValueMap(E vertex) {
        indexToNodeDataMap.put(vertex, idx++);
    }

    public Integer getIndex(E vertex) {
        return indexToNodeDataMap.get(vertex);
    }

    public List<E> findAllConnectingNodes(E vertex) {
        List<E> adjacentNodes = new ArrayList<>();
        int idx = indexToNodeDataMap.get(vertex);
        for (int col = 0; col < adjMatrix.length; col++) {
            if (adjMatrix[idx][col] != null) {
                System.out.println(adjMatrix[idx][col]);
                adjacentNodes.add(adjMatrix[idx][col].endVrtx);
            }
            //adjacentNodes.add(indexToNodeDataMap.get());
        }
        return adjacentNodes;
    }

    public boolean isGivenTwoNodesAreConnected(E vrtx1, E vrtx2) {
        int idx = indexToNodeDataMap.get(vrtx1);
        for (int col = 0; col < adjMatrix.length; col++) {
            Edge edge = adjMatrix[idx][col];
            if ( edge != null && Objects.equals(vrtx2, edge.endVrtx)) {
                return true;
            }
        }
        return false;
    }

    public Set<E> suggestANodesToConnect(E givenVrtx) {
        Set<E> suggestedList = new HashSet<E>();
        findAllConnectingNodes(givenVrtx).stream().forEach(vrtx -> {
            suggestedList.addAll(findAllConnectingNodes(vrtx));
        });
        suggestedList.remove(givenVrtx);
        return suggestedList;
//        return findAllConnectingNodes(givenVrtx).stream().map(
//                vrtx -> findAllConnectingNodes(vrtx)).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        MyCodeSchoolAdjMatrixGraph g = new MyCodeSchoolAdjMatrixGraph();
        g.createIndexToNodeValueMap("A");
        g.createIndexToNodeValueMap("B");
        g.createIndexToNodeValueMap("C");
        g.createIndexToNodeValueMap("D");
        g.createIndexToNodeValueMap("E");
        g.createIndexToNodeValueMap("F");
        g.createIndexToNodeValueMap("G");
        g.createIndexToNodeValueMap("H");

        /*g.createIndexToNodeValueMap("Rama");
        g.createIndexToNodeValueMap("Ella");
        g.createIndexToNodeValueMap("Katie");
        g.createIndexToNodeValueMap("Lee");
        g.createIndexToNodeValueMap("Bob");
        g.createIndexToNodeValueMap("Swathi");
        g.createIndexToNodeValueMap("Zahir");
        g.createIndexToNodeValueMap("Sam");
        g.createIndexToNodeValueMap("Tom");
        g.createIndexToNodeValueMap("Arun");*/

        System.out.println(Arrays.deepToString(g.adjMatrix));

        g.createAdjacencyMatrix("A", "B");
        g.createAdjacencyMatrix("A", "C");
        g.createAdjacencyMatrix("A", "D");
        g.createAdjacencyMatrix("B", "A");
        g.createAdjacencyMatrix("B", "E");
        g.createAdjacencyMatrix("B", "F");
        g.createAdjacencyMatrix("C", "A");
        g.createAdjacencyMatrix("C", "G");
        g.createAdjacencyMatrix("D", "A");
        g.createAdjacencyMatrix("D", "H");
        g.createAdjacencyMatrix("E", "B");
        g.createAdjacencyMatrix("E", "H");
        g.createAdjacencyMatrix("F", "B");
        g.createAdjacencyMatrix("F", "H");
        g.createAdjacencyMatrix("G", "C");
        g.createAdjacencyMatrix("G", "H");
        g.createAdjacencyMatrix("H", "D");
        g.createAdjacencyMatrix("H", "E");
        g.createAdjacencyMatrix("H", "F");
        g.createAdjacencyMatrix("H", "G");

        /*g.createAdjacencyMatrix("Rama", "Ella");
        g.createAdjacencyMatrix("Rama", "Katie");
        g.createAdjacencyMatrix("Rama", "Bob");
        g.createAdjacencyMatrix("Katie", "Rama");
        g.createAdjacencyMatrix("Katie", "Lee");
        g.createAdjacencyMatrix("Ella", "Rama");
        g.createAdjacencyMatrix("Ella", "Bob");
        g.createAdjacencyMatrix("Bob", "Ella");
        g.createAdjacencyMatrix("Bob", "Rama");
        g.createAdjacencyMatrix("Bob", "Lee");
        g.createAdjacencyMatrix("Lee", "Bob");
        g.createAdjacencyMatrix("Katie", "Swathi");
        g.createAdjacencyMatrix("Swathi", "Katie");
        g.createAdjacencyMatrix("Lee", "Zahir");
        g.createAdjacencyMatrix("Lee", "Sam");
        g.createAdjacencyMatrix("Zahir", "Lee");
        g.createAdjacencyMatrix("Sam", "Lee");
        g.createAdjacencyMatrix("Bob", "Sam");
        g.createAdjacencyMatrix("Sam", "Bob");
        g.createAdjacencyMatrix("Sam", "Arun");
        g.createAdjacencyMatrix("Arun", "Sam");
        g.createAdjacencyMatrix("Bob", "Tom");
        g.createAdjacencyMatrix("Tom", "Bob");
        g.createAdjacencyMatrix("Sam", "Tom");
        g.createAdjacencyMatrix("Tom", "Sam");*/

        System.out.println(Arrays.deepToString(g.adjMatrix));

        String givenNode = "A";//"Rama";
        System.out.println("Adjacent nodes to given node " + givenNode + " are >> "
                            + g.findAllConnectingNodes(givenNode));

        String vertx1 = "H"; //"Lee";//"H";
        String vertx2 = "A"; //"Tom";
        System.out.println("Are the given two nodes " + vertx1 + " and " + vertx2
                            + " are connected " + g.isGivenTwoNodesAreConnected(vertx1, vertx2));

        System.out.println("Suggested list of nodes for a given node "
                            + givenNode + " are >> " + g.suggestANodesToConnect(givenNode));
    }

    public static class Edge<E> {
        E startVrtx;
        E endVrtx;

        public Edge(E startVrtx, E endVrtx) {
            this.startVrtx = startVrtx;
            this.endVrtx = endVrtx;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "startVrtx=" + startVrtx +
                    ", endVrtx=" + endVrtx +
                    '}';
        }
    }
}
