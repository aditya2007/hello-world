package com.java.core.dsl.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import com.java.core.dsl.queue.ListPriorityQueue;
import com.java.core.dsl.trees.DSLBinarySearchTree;

/**
 * Created by Yogananda Gowda - 212590467 on 5/21/17.
 */
public class BinarySort {

    public static void main(String[] args) {
        //int[] input = {0,1,0,1,1,0,1,0,1,0,1,0};
        //int[] input = {0,1,0,1,0,1,0,1,0,1,0,1};
        //int[] input = {1,0,1,0,1,0,1,0,1,0,1,0};
        int[] input = {1,0,0,0,0,0,0,1,1,1,1};
       /*System.out.println("Before sort >>>>>> " );
        print(input);
        sortBinary(input);
        System.out.println("After sort >>>>>> ");
        print(input);*/
        //printsmallToBigWithoutSort();
        //findASingleRepeatingNumber();
        //findANonRepeatingNumber();
        //System.out.println(findARepeatingNumber());
        String braces1 = "{[()]}";
        String braces2 = "{[(])}";
        String braces3 = "{{[[(())]]}}";
        String braces4 = "{{[()]}";
        String braces5 = "}{";

        //System.out.println(isTheSymbolsAreComplete(braces1));
        //System.out.println(isTheSymbolsAreComplete(braces2));
        //System.out.println(isTheSymbolsAreComplete(braces3));
        //System.out.println(isTheSymbolsAreComplete(braces5));

        /*System.out.println(isExpressionBalanced(braces1));
        System.out.println(isExpressionBalanced(braces2));
        System.out.println(isExpressionBalanced(braces3));
        System.out.println(isExpressionBalanced(braces5));*/
        //System.out.println(calculatePrimes(3));
    }

    static int calculatePrimes(int n) {
        int[] primes = new int[n];
        int sum = 0;
       // int primeIdx = 0;
        if (n == 1) {
            //primes[0] = 2;
            sum = 2;
            //return primes;
            return sum;
        }

        //for (int i = 2; i < Integer.MAX_VALUE; i++) {
        int i = 2;
        while (i < Integer.MAX_VALUE && n > 0) {
            if ( i == 2 || isPrime(i)) {
                //primes[primeIdx] = i;
                sum += i;
                n--;
            } /*else if (isPrime(i)) {
                //primes[++primeIdx] = i;
                sum += i;
                n--;
            }*/
            i++;
            //if (n == 0) break;
        }
        //return primes;
        return sum;
    }

    static boolean isPrime(int n) {
        //check if n is a multiple of 2
        if (n%2 == 0) return false;
        //if not, then just check the odds
        for (int i = 3; i*i <= n; i += 2) {
            if (n%i == 0)
                return false;
        }
        return true;
    }
    /**
     * Apple -
     * @param nums
     * @return
     */
    static int[] sortBinary(int[] nums) {
        if (nums == null || nums.length == 0)
            return nums;
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] == 0)
                left++;
            if (nums[right] == 1)
                right--;
            if (left < right && nums[left] != nums[right]) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
        return nums;
    }

    /**
     * Apple -
     * Print out, from small to big, of a sequence integers, without sorting
     * Solution 1 : Priority Queue
     * Solution 2 : Add the sequence of integers to HashSet,
     *              which will apply natural order and then print the collection elements
     * Solution 3 : Construct binary search tree and do inorder traversal
     *
     * Example - input -> 6,3,8,2,9,4,0,1 output -> 0,1,2,3,4,6,8,9
     */
    static void printsmallToBigWithoutSort() {
        Integer[] input = {6,3,8,2,9,4,0,1,8};
        priorityQSolution(input);
        //hasSetSolution(input);
        //bstSoultion(input);
        Set<Integer> intersection = new HashSet<>();
        intersection.add(6);
        intersection.add(3);
        intersection.add(8);
        intersection.add(2);
        intersection.add(9);
        intersection.add(4);
        intersection.add(0);
        intersection.add(1);

        Set<Integer> hset2 = new HashSet<>();
        hset2.add(7);
        hset2.add(3);
        hset2.add(10);
        hset2.add(20);
        hset2.add(9);
        hset2.add(14);
        hset2.add(110);
        hset2.add(111);

        //intersection.retainAll(hset2); // This is from HashSet class
        //intersect(intersection, hset2);
        //intersection.stream().forEach(System.out::print);
    }

    private static void intersect(Collection<? extends Number> c1, Collection<? extends Number> c2) {
        Objects.requireNonNull(c1);
        Objects.requireNonNull(c2);
        boolean modified = false;
        Iterator<? extends Number> it = c1.iterator();
        while (it.hasNext()) {
            if (!c2.contains(it.next())) {
                it.remove();
            }
        }
    }
    /** Not workingwith java.uti.PriorityQueue, but working with my custom PriorityQueue
     *
     * @param input
     */
    private static void priorityQSolution(Integer[] input) {
        System.out.println("Before elements re arranged ==> " + Arrays.toString(input));
        //PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        //priorityQueue.addAll(Arrays.asList(input));
        //priorityQueue.iterator().forEachRemaining(System.out::print);
        //System.out.println("After elements re arranged ==> " + Arrays.toString(priorityQueue.toArray()));

        ListPriorityQueue<Integer> priorityQueue = new ListPriorityQueue<>();
        Arrays.stream(input).forEach(e -> priorityQueue.insert(e));
        System.out.println("After elements re arranged ==> "
                + Arrays.toString(priorityQueue.iterator().values()));
    }

    /**
     * Java 8 Hashmap/Hashset implementation has changed,
     * in a way that now it useses balanced tree for some cases instead of LinkedList to store elements
     * @param input
     */
    private static void hasSetSolution(Integer[] input) {
        System.out.println("Before elements re arranged ==> " + Arrays.toString(input));
        HashSet<Integer> elemts = new HashSet();
        elemts.addAll(Arrays.asList(input));
        System.out.println("After elements re arranged ==> " + Arrays.toString(elemts.toArray()));
    }

    private static void bstSoultion(Integer[] input) {
        System.out.println("Before elements re arranged ==> " + Arrays.toString(input));
        DSLBinarySearchTree<Integer> bst = new DSLBinarySearchTree<>();
        Arrays.stream(input).forEach(e -> bst.add(e));
        bst.printTree(bst.root);
        bst.inOrderTraversal(bst.root);
    }

    /**
     * Apple-
     * Given a stream of integers from 1 to n, only one number will be repeated.
     * How can you tell what that number is?
     *
     * Solution 1 :
     * You know n. S = n*(n+1)/2 is the sum of 1st n numbers. P = sum of the n+1 numbers you are provided with.
     *  Finding P given an array of n+1 integers can be done in O(n). P - S is the repeated integer.
     *
     *  Solution 2 :
     *   https://www.techinterview.org/post/526329049/sum-it-up/
     *
     *  Solution 3 :
     *
     *
     int sum = 0;
     int xorSum = 0;
     for(int i =0 ; i < n; i++)
     {
     sum += input[i];
     xorSum ^= input[i]
     }
     return (sum - xorSum)/2;
     */
    private static void findASingleRepeatingNumber() {
        Integer[] input = {1,2,3,3,4,5,null,4};
        //Character[] input = {'a','b','c','c','d','e','d',null};
        //Set<Integer> hashSet = new HashSet<>();
        Set<Object> hashSet = new HashSet<>();
        long startTime = System.nanoTime();
        //Arrays.stream(input).filter(e -> !hashSet.add(e)).forEach(System.out::println); //Taking average of 70 ms
        for (Object e : input) { // This take average of 0 ms.
            if (!hashSet.add(e)) {
                System.out.println(e);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Time taken is " + (endTime - startTime) / 1000000 + " milliseconds");

    }


    /** My solution
     *
     * @return
     */
    private static int findARepeatingNumber() {
        Integer[] input = {1,2,3,3,4,5};
        int idx = 2;
        int curr = input[idx-1];
        int prev = input[idx-2];

        if ( curr == prev) return curr;


        while (idx < input.length) {
            curr = input[idx];
            prev = input[idx-1];
            if (curr == prev) return curr;
            idx++;
        }
        return 0;
    }

    private static void findANonRepeatingNumber() {
        Integer[] input = {1,2,3,3,4,5,null,4};
        //Character[] input = {'a','b','a','b','c','c','d','e','d',null};
        //Set<Integer> hashSet = new HashSet<>();
        Set<Object> hashSet = new HashSet<>();
        Stack<Object> stack = new Stack<>();
        long startTime = System.nanoTime();
        for (Object e : input ) {
            if (!hashSet.add(e)) {
                stack.pop();
            } else {
                stack.push(e);
            }
        }
        System.out.println(Arrays.toString(stack.toArray()));
        long endTime = System.nanoTime();
        System.out.println("Time taken is " + (endTime - startTime) / 1000000 + " milliseconds");
    }

    private static String isTheSymbolsAreComplete(String braces) {
        String isBalanced = "NO";
        if ( braces.length() % 2 != 0 ) return isBalanced;
        List<Character> list = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        for (Character e : braces.toCharArray()) {
            list.add(e);
            stack.push(e);
        }
        System.out.println(list);
        System.out.println(stack);
        for (int idx = 0; idx < braces.length() / 2; idx++) {
            if (isOpenFlowerBr(list.get(idx)) && isClosedFlowerBr(stack.peek())
                || (isOpenSqBr(list.get(idx)) && isClosedSqBr(stack.peek()))
                || (isOpenBrace(list.get(idx)) && isClosedBrace(stack.peek()))    ) {
                System.out.println("YES its balanced!!!!");
                isBalanced = "YES";
            } else {
                System.out.println("NO its not balanced!!!!");
                stack.pop();
                isBalanced = "NO";
                break;
            }
            stack.pop();
        }
        return isBalanced;
    }

    //My Code school
    private static boolean isExpressionBalanced(String expr) {
        System.out.println(expr);
        Stack<Character> stack = new Stack<>();
        for (Character c : expr.toCharArray()) {
            if (isOpenBracket(c)) {
                stack.push(c);
            } else if (isClosedBracket(c)) {
                if (stack.isEmpty() || !isOpenAndClosed(stack, c)) {
                    return false;
                } else {
                    stack.pop();
                }
            } // end of else if
        }// end of for loop
        return stack.isEmpty();
    }

    private static boolean isOpenBracket(Character c) {
        return c == '[' || c == '{' || c == '(';
    }

    private static boolean isClosedBracket(Character c) {
        return c == ']' || c == '}' || c == ')';
    }

    private static boolean isOpenAndClosed(Stack<Character> stack, Character c) {
        return ((isOpenFlowerBr(stack.peek()) && isClosedFlowerBr(c))
                || (isOpenSqBr(stack.peek()) && isClosedSqBr(c))
                || (isOpenBrace(stack.peek()) && isClosedBrace(c)));
    }

    private static boolean isOpenBrace(Character c) {
        return c == '(';
    }

    private static boolean isClosedBrace(Character c) {
        return c == ')';
    }

    private static boolean isOpenSqBr(Character c) {
        return c == '[';
    }

    private static boolean isClosedSqBr(Character c) {
        return c == ']';
    }

    private static boolean isOpenFlowerBr(Character c) {
        return c == '{';
    }

    private static boolean isClosedFlowerBr(Character c) {
        return c == '}';
    }


    private static void print(int[] input) {
        System.out.println(Arrays.toString(input));
    }

}
