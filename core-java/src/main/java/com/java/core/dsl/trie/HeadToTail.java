package com.java.core.dsl.trie;

/**
 * Amazon -
 * Given the word "HEAD" and the word "TAIL," write code  and/or describe using computer science algorithms
 * how you would transform from the word HEAD to the word TAIL. Each change must be by only one letter,
 * you cannot change the letter in a given position twice, and each new word must be a valid word.
 * Created by Yogananda Gowda - 212590467 on 11/8/17.
 */
public class HeadToTail {


    /*//use a hash set for a fast check if a word is already in the dictionary
    static HashSet Dictionary = new HashSet();
    //dictionary used to find the parent in every node in the graph and to avoid traversing an already traversed node
    static Dictionary parents = new Dictionary();

    public static List FindPath(List input, string start, string end)
    {
        char[] allcharacters = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

        foreach (string s in input)
        Dictionary.Add(s);
        List currentFrontier = new List();
        List nextFrontier = new List();
        currentFrontier.Add(start);
        while (currentFrontier.Count > 0)
        {
            foreach (string s in currentFrontier)
            {
                for (int i = 0; i ExtractPath(string start,string end)
                {
                    List path = new List();
                    string current = end;
                    path.Add(end);
                    while (current != start)
                    {
                        current = parents[current];
                        path.Add(current);
                    }
                    path.Reverse();
                    return path;
                }*/

}
