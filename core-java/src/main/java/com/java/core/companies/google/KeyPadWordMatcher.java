package com.java.core.companies.google;

import java.util.*;

/**
 * GOOGLE
 * Given an array of integers and a dictionary (Map<String, String>)
 * - determine all the words in the dictionary that could match the
 * list of integers according to a phone keypad.

 1 -
 2 - a b c
 3 - d e f
 4 - g h i
 5 - m n o
 7 - j k l
 6 - p q r s
 8 Ex:- t u v
 9 - w x y z

  41281
 int[] input = {4,1,2,8,1}

 gc
 ga
 gb
 hat <- valid word
 hb
 hc
 i

 private Map<Integer, Set<String>> keypad = ImmutableMap.of(
 1, ImmutableSet.of(),
 2, ImmutableSet.of(“a”,”b”,”c”),
 … );

 private Map<String, String> dictionary = ImmutableMap.of( … );

 public List<String> getWords(int[] input) {.....} //implement this method
 */
public class KeyPadWordMatcher {

    private String[] words = { "","", "ABC", "DEF", "GHI", "JKL", "MNO",
            "PQRS", "TUV", "WXYZ" };

    Map<String, String> dictionary;
    List<String> matches = new ArrayList<>();

    public void generateCombinations(int[] nums) {
        if (nums == null || nums.length <= 0)
            throw new IllegalArgumentException("Please check the args");

//remove 0s and 1's
        int i, j;
        for (i = j = 0; j < nums.length; ++j)
            if ( 0 != nums[j] && 1 != nums[j]) nums[i++] = nums[j];
        nums = Arrays.copyOf(nums, i);

        printAll(nums, 0, "");
    }

    private void printAll(int[] nums, int wIdx, String str) {
        if (str.length() == nums.length) {
            //if( dictionary.containsKey(str.toLowerCase()))
            for( String key : dictionary.keySet() ) {
                if( key.equalsIgnoreCase(str)) {
                    matches.add(str.toLowerCase());
                }
            }

            //System.out.println(str);
            return;
        }

        String word = words[nums[wIdx]];

        for (int i = 0; i < word.length(); i++) {
            printAll(nums, wIdx + 1, str + word.charAt(i));
        }
    }

    private void immutableMap() {
            dictionary = new HashMap<>();
            dictionary.put("Card", "Deck of card");
            dictionary.put("Dog", "Dog is a trusted animal");
            dictionary.put("hat", "Wear the nice hat");
            dictionary.put("Document", "Document to read..");
            dictionary.put("icu", "Intensive care Unit");
    }

    public static void main(String[] args) {
        int[] a = {4,1,2,8,9};

        KeyPadWordMatcher pbc = new KeyPadWordMatcher();
        pbc.immutableMap();
        pbc.generateCombinations(a);
        System.out.println("Matched word = [" + pbc.matches + "]");
    }


}
