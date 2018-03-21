package com.java.core.leetcode;

import java.util.*;

public class FindDupulicatePositions {

    public static void main(String[] args) {
        //int[] input = {11,4,15,12,8,6};
        int[] input = {3,5,8,6,3,4,7,4,8,1,0};
        Map<Integer, Set<Integer>> dupIndexeMap = getDuplicatePositions(input);
        for( Integer key : dupIndexeMap.keySet() ) {
            System.out.print(key + " => ");
            print(dupIndexeMap.get(key));
        }
    }

    static Map<Integer, Set<Integer>> getDuplicatePositions(int[] input) {
        Map<Integer, Set<Integer>>  dupPositions = null;
        Map<Integer,Integer> dupChecker = new HashMap<>();

        for(int i = 0; i < input.length; i++) {
            int x = input[i];
            if (dupChecker.containsKey(x)) {
                dupPositions = dupPositions == null ? new HashMap<>() : dupPositions;
                Set<Integer> list = dupPositions.get(x) == null ? new HashSet<>() : dupPositions.get(x);
                list.add(dupChecker.get(x));
                list.add(i);
                dupPositions.put(x, list);
            } else {
                dupChecker.put(x, i);
            }
        }
        return dupPositions;
    }

    static void printArray(int[] solutionArray) {
        for (int i = 0; i < solutionArray.length; i++)
        {
            System.out.print(solutionArray[i] + " ");
        }
        System.out.println();

    }

    static void print(Set<Integer> set) {
        for( Integer pos : set) {
            System.out.print(pos + ",");
        }
        System.out.println();
    }
}
