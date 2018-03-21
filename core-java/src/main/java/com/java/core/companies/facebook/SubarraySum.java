package com.java.core.companies.facebook;

/**
 * Created by Yogananda Gowda - 212590467 on 3/28/17.
 */
public class SubarraySum {

    /* Returns true if the there is a subarray of arr[] with sum equal to
       'sum' otherwise returns false.  Also, prints the result */
    int subArraySum(int arr[], int n, int sum)
    {
        int curr_sum = arr[0], start = 0, i;

        // Pick a starting point
        for (i = 1; i <= n; i++)
        {
            // If curr_sum exceeds the sum, then remove the starting elements
            while (curr_sum > sum && start < i-1)
            {
                curr_sum = curr_sum - arr[start];
                start++;
            }

            // If curr_sum becomes equal to sum, then return true
            if (curr_sum == sum)
            {
                int p = i-1;
                System.out.println("Sum found between indexes " + start
                        + " and " + p);
                return 1;
            }

            // Add this element to curr_sum
            if (i < n)
                curr_sum = curr_sum + arr[i];

        }

        System.out.println("No subarray found");
        return 0;
    }

    int[] getSubArr(int[] input, int targetSum)
    {
        int currSum = input[0], startIdx = 0, currIdx, inputLength = input.length;
        int[] output;

        // Pick a starting point
        for (currIdx = 1; currIdx <= inputLength; currIdx++)
        {
            // If current Sum exceeds the sum, then remove the starting elements
            while (currSum > targetSum && startIdx < currIdx - 1)
            {
                currSum -= input[startIdx];
                startIdx++;
            }

            // If curr_sum becomes equal to sum, then return true
            if (currSum == targetSum)
            {
                int outputLength = currIdx - startIdx;
                output = new int[outputLength];
                for( int i = 0; i < output.length; i++ ){
                    output[i] = input[startIdx++];
                }
                return output;
            }

            // Add this element to curr_sum
            if (currIdx < inputLength)
                currSum += input[currIdx];
        }

        return null;
    }

    public static void main(String[] args)
    {
        SubarraySum arraysum = new SubarraySum();
        int arr[] = {15, 2, 4, 8, 9, 5, 10, 25};
        int n = arr.length;
        int sum = 25;
        //arraysum.subArraySum(arr, n, sum);
        int[] output = arraysum.getSubArr(arr, sum);
        if( null != output ) {
            for( int i = 0; i < output.length; i++) {
                System.out.print(output[i]);
                System.out.print(",");
            }
        }
    }
}
