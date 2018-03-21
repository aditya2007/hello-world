package com.java.core.companies.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GOOGLE - https://gist.github.com/amaxwell01/3728155
 * Given two lists of elements A and B (can be anything strings, integers etc), compute the diff between them.
 * Note that each of the lists can contain duplicates.

 The diff should return two lists:
 - Elements in A that are not in B
 - Elements in B that are not in A

 For e.g.
 Diff([1,2,3,4] [1,2,3]) = [4] and []
 Diff([1,2,2,2], [1,2]) = [2,2] and []
 Diff([2, 2, 1, 2], [1,2]) = [2,2] and []
 Diff([1,1,1,1,1,1,2,2,2,2,2,2], [1,1,2,2]) = [1,1,1,1,2,2,2,2] and []
 * Created by 212590467 on 11/17/16.
 */
public class DifferenceInArray {

    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = {1,2,3};
        System.out.println(Integer.toBinaryString(2^4^7^9^2^4));
        Arrays.sort(a);
        Arrays.sort(b);
        //My approach works for first 2 examples
        List<Integer> listA = new ArrayList<>();
        List<Integer> listB = new ArrayList<>();

        for( int i = 0; i < a.length; i++ ) {
            if( i >= b.length  ) { //&& !listA.contains(a[i])
                listA.add(a[i]);
            }

            for( int j = i; j < b.length; j++ ) {
                if( a[i] == b[j] || listA.contains(a[i])) {
                    break;
                } else {
                    listA.add(a[i]);
                }
            }
        }//end of for loop
        System.out.println(listA);

        /*for( int i = 0; i < b.length; i++ ) {

            for( int j = i; j < a.length; j++ ) {
                if( b[i] == a[j] ) {
                    break;
                } else {
                    listB.add(b[i]);
                }
            }
        }//end of for loop
        System.out.println(listB);*/

    }



}
