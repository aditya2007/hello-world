package com.java.core.companies.google;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 212590467 on 11/17/16.
 */
public class NumberMissingFromList {

    public static void main(String[] args) {
        //int[] input = {0,1,2,50,52,75};
        int[] input = {3,5};
        //String[] output = findMissingNumbers(input);
        //System.out.println(output);

        List<Integer> list = Arrays.asList(0,1,2,50,52,75);
        String str = listRanges(list);
        System.out.println(str);
    }

    //My approach/solution
    private static String[] findMissingNumbers(int[] input) {
        int j = 1, val1 = 0, val2 = 0;
        int minVal = 0, maxVal = 99;
        String val3 = "";
        List<String> list = new ArrayList<>();

        if( input.length == 0 ) {
            val3 = minVal + "-" + maxVal;
            list.add(val3);
            return list.toArray(new String[list.size()]);
        }

        if( input.length == 1 ) {
            val1 = input[0];
            val2 = input[0];
            val3 = minVal + "-" + maxVal;
            list.add(val3);
            return list.toArray(new String[list.size()]);
        }

        for( int i = 0; i < input.length; i++ ) {
            val1 = input[i];
            val2 = input[j];
            if( i == input.length - 1 && input[i] < maxVal) {
                val3 = (input[i] + 1) + "-" + maxVal;
                list.add(val3);
                return list.toArray(new String[list.size()]);
            } else if( (val2 - val1) == 2 ){
                val3 = (val1+1) + "";
                list.add(val3);
            } else if( (val2 - val1) > 1 ) {
                val3 = (val1+1) + "-" + (val2-1);
                list.add(val3);
            }
            if( i < input.length - 2 ) {
                j++;
            }

        }// end of for loop
        return list.toArray(new String[list.size()]);
    }

    private static String listRanges(List<Integer> list){
        int minRangeVal = 0, maxRangeVal = 99;
        if( list.size() == 0 ) {
            return minRangeVal + "-" + maxRangeVal;
        }

        int i = 0;
        int start = -1;
        //int end = -1;

        StringBuilder builder = new StringBuilder();
        int max = list.get(list.size() - 1);
        int lim = 100;

        while (i < 100 && i <= max){
            if (!list.contains(i)){
                if (start == -1){
                    start = i;

                    if (list.contains(i + 1)){
                        builder.append(start + ",");
                        start = -1;
                        i++;
                    }
                }
            }
            else if (start != -1){
                builder.append(start + "-" + (i - 1) + ",");
                start = -1;
            }
            i++;
        }
        builder.append((max + 1) + "-" + (lim - 1));
        return builder.toString();
    }
}
