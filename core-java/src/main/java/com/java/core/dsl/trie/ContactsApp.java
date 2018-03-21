package com.java.core.dsl.trie;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * https://www.hackerrank.com/challenges/contacts
 *
 * Created by Yogananda Gowda - 212590467 on 6/7/17.
 */
public class ContactsApp {
    private static TrieNode root = new TrieNode();

    public static void main(String[] args) throws Exception {
        Map<String, Integer> trieMap = new HashMap<String, Integer>();
        /*Scanner scan = new Scanner(System.in);
        int numOfInputLines = scan.nextInt();
        Map<String, Integer> trieMap = new HashMap<String, Integer>();

        for (int i = 0; i < numOfInputLines; i++) {

            String operation = scan.next();
            String contact = scan.next();

            if (operation.equals("add")){
                //add(trieMap,contact);
                add(contact);
            } else { //query matches
                //find(trieMap, contact);
                System.out.println(find(contact));
            }
        }*/
        add(trieMap, "hack");
        add(trieMap, "hackerrank");
        find(trieMap, "hak");
        find(trieMap, "hack");

//        add("hack");
//        add("hackerrank");
//        find("hak");
//        find("hack");

    }

    private static void add(Map<String, Integer> trieMap, String contact) {
        for (int j = 1; j <= contact.length(); j++){
            String sub = contact.substring(0, j);
            if (trieMap.get(sub) == null){
                trieMap.put(sub, 1);
            } else {
                trieMap.put(sub, trieMap.get(sub) + 1);
            }
        }

    }

    private static int find(Map<String, Integer> trieMap, String prefix) {
        if (trieMap.get(prefix) == null){
            System.out.println(0);
            return 0;
        } else {
            System.out.println(trieMap.get(prefix));
            return trieMap.get(prefix);
        }
    }

    private static void add(String str) {
        TrieNode curr = root;
        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            curr.addChildIfAbsent(ch);
            curr = curr.children.get(ch);
            curr.size++;
        }
    }

    private static int find(String prefix) {
        TrieNode curr = root;

        /* Traverse down tree to end of our prefix */
        for (int i = 0; i < prefix.length(); i++) {
            Character ch = prefix.charAt(i);
            if (!curr.children.containsKey(ch)) {
                return 0;
            } else {
                curr = curr.children.get(ch);
            }
        }
        return curr.size;
    }

    private static boolean delete(TrieNode current, String word, int idx) {
        if ( idx == word.length()) {
            //when end o fthe word is reached only delete if current.endOfWord is true.
            if (!current.isCompleted)  return false;

            current.isCompleted = false;
            //if current has no other mapping then return true
            return current.children.size() == 0;
        }

        char ch = word.charAt(idx);
        TrieNode node = current.children.get(ch);
        if (node == null) return false;

        boolean shouldDelCurrentNode = delete(node, word, idx + 1);
        //if true, then delete the mapping of Character and trieNode reference from map
        if (shouldDelCurrentNode) {
            current.children.remove(ch);
            //return true if no mapping are left in the map
            return current.children.size() == 0;
        }

        return false;
    }



    private static class TrieNode {
        public HashMap<Character, TrieNode> children = new HashMap<>();
        public int size = 0; // main trick to decrease runtime
        public boolean isCompleted = false;

        public void addChildIfAbsent(Character ch) {
            children.putIfAbsent(ch, new TrieNode());
        }
    }
}
