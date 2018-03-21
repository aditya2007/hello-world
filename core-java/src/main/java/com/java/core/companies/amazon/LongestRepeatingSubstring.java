package com.java.core.companies.amazon;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yogananda Gowda - 212590467 on 11/8/17.
 */
public class LongestRepeatingSubstring {

    public static void main(String[] args)
    {
        LongestRepeatingSubstring l = new LongestRepeatingSubstring();

       //System.out.println(l.find("bananabeanbe"));
        //System.out.println(l.find("abcdefg"));
       System.out.println(l.find("aaakcaaaab"));
//        System.out.println(l.find(""));
    }

    public String find(String s)
    {
        String max = "";
        String localMax = "";
        for (int i = 1; i < s.length(); i++)
        {
            localMax = helper(s, i);
            if (localMax.length() > max.length())
                max = localMax;
        }
        return max;
    }

    private String helper(String s, int windowSize)
    {
        String res = "";
        Set<String> set = new HashSet<>();
        for (int i = 0; i < (s.length() - windowSize + 1); i++)
        {
            String temp = s.substring(i, i + windowSize);
            if (set.contains(temp))
            {
                res = temp;
            }
            else
            {
                set.add(temp);
            }
        }
        return res;
    }
}
