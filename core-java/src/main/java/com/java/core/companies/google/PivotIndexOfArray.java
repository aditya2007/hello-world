package com.java.core.companies.google;

/**
 * Time : O(n)
 1. initialize leftSum, rightSum to 0
 2.find sum of all elements - rightSum
 3. for each element in array
 a) reduce cur element from rightSum
 b) compare rightSum with Left sum, if same return
 c) add cur element to leftSum
 *
 * Created by 212590467 on 11/17/16.
 */
public class PivotIndexOfArray {

    public static void main(String[] args) {
        int[] input = {1,2,3,4,0,6};
        System.out.println(input[findPivot(input)]);
    }

    static int findPivot(int a[]) {
        int leftSum=0,rightSum=0;
        for(int i=0;i<a.length;i++){
            rightSum+=a[i];
        }

        for(int i=0;i<a.length;i++){
            rightSum-=a[i];
            if(leftSum == rightSum)
                return i;
            leftSum+=a[i];
        }
        return -1;
    }

}
