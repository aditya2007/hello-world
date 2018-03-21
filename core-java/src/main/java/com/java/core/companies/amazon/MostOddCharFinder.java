package com.java.core.companies.amazon;

import com.util.Timeit;

import java.util.*;

/**
 * AMAZON
 * problem: “given a string of non-delimited characters. Please return the  most oddly occurring character”
 input: “aabacccc”
 output: “a”

 input: “aabaacccc”
 output: “b”
 */
public class MostOddCharFinder {

    public static void main(String[] args) {
        String input = "aabaaccc";
        System.out.println(findOddChar(input));
        /*int runnableCnt = 10;
        while( runnableCnt > 0 ) {
            Timeit.code(() ->
                System.out.println(findOddChar(input))
            );
            /*Timeit.code(new Runnable() {
                @Override
                public void run() {
                    System.out.println(findOddChar(input));
                }
            });
            --runnableCnt; */
    }

    static Character findOddChar(String input) {

        Map<Character, Integer> charsCntMap = new HashMap<>();

        for( int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            if (charsCntMap.containsKey(x)) {
                charsCntMap.put(x, charsCntMap.get(x) + 1);
            } else {
                charsCntMap.put(x, 1);
            }
        }

        //return withMap(charsCntMap);
        return withoutMap(charsCntMap);
    }

    private static Character withoutMap( Map<Character, Integer> charsCntMap ) {
        int maxOddVal = 0;
        Character returnChar = null;
        for( Map.Entry<Character, Integer> entry : charsCntMap.entrySet() ) {
            if( entry.getValue() % 2 != 0 && entry.getValue() > maxOddVal ) {
                maxOddVal = entry.getValue();
                returnChar = entry.getKey();
            }
        }
        return returnChar;
    }

    private static Character withMap( Map<Character, Integer> charsCntMap ) {
        Map<Character, Integer> charsCntOrderedMap = new TreeMap<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return charsCntMap.get(o2).compareTo(charsCntMap.get(o1));
            }
        });

        charsCntOrderedMap.putAll(charsCntMap);
        for( Character key :  charsCntOrderedMap.keySet() ) {
            if( charsCntOrderedMap.get(key) > 1  && charsCntOrderedMap.get(key) % 2 != 0 ) {
                return key;
            }else if( charsCntOrderedMap.get(key) == 1 ){
                return key;
            }
        }
        throw new RuntimeException("Not valid char found");
    }

}
