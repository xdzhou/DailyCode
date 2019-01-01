package com.loic.geeksforgeeks.dp;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/convert-to-strictly-increasing-array/0
 * <p>
 * Given an array A of N positive integers.
 * Find the minimum number of operations (change a number to greater or lesser than original number)
 * in array so that array is strictly increasing (A[i] < A[i+1]).
 */
public class StrictlyIncreaseArray {

  public static int simpleResolve(int... nums) {
    //suppose that LISWC is longest increasing subsequence with condition: nums[i] - nums[j] >= i - j (i>j)
    //dp[i] is the LISWC which end with nums[i]
    int[] dp = new int[nums.length];
    Arrays.fill(dp, 1);
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] - nums[j] >= i - j && nums[j] > j) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      max = Math.max(max, dp[i]);
    }
    return nums.length - max;
  }

  /**
   * https://stackoverflow.com/questions/21048600/minimum-no-of-changes-required-to-make-array-strictly-increasing
   * <p>
   * this question very close to LIS (longest increasing subsuquence) problem
   * If you could change the numbers to decimals then the answer would be identical.
   * (Min number of changes = length of array - length of LIS)
   * <p>
   * However, as you need distinct integral values in between you will have to slightly modify the standard algorithm.
   * <p>
   * Consider what happens if you change the array by doing x[i]=x[i]-i.
   * <p>
   * You now need to modify this changed array by making the smallest number of changes such that each element increases, or stays the same.
   * <p>
   * You can now search for the longest non-decreasing subsequence in this array and this will tell you how many elements can stay the same.
   * <p>
   * But we may have following problem:
   * Origin Array nums : 1,2,1, 2, 3, 4
   * after nums[i] - i : 1,1,-1,-1,-1,-1
   * the longest non decreasing subsuqence is 4 (-1,-1,-1,-1), but is is not valid
   * because we could not change origin array to become: -1, 0, 1, 2, 3, 4
   *
   * <p>
   * key condition: LIS with following condition: nums[i] - i >= nums[j] - j >= 0 (i>j)
   * nums[j] - j >= 0 because if nums[j] < j, nums[j] has to be changed, so we will skip them
   */
  public static int resolve(int... nums) {
    int[] smallestEnd = new int[nums.length];
    smallestEnd[0] = nums[0];
    // the current right index of smallestEnd array
    int right = 0;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] >= i) {
        int realNum = nums[i] - i;
        /**
         * ATTENTION:
         * this we use ">=", because we search non decreasing subsequence
         */
        if (realNum >= smallestEnd[right]) {
          right++;
          smallestEnd[right] = realNum;
        } else {
          smallestEnd[binarySearch(smallestEnd, 0, right, realNum)] = realNum;
        }
      }
    }
    return nums.length - (right + 1);
  }

  /**
   * search the index of first item which is bigger than key
   */
  private static int binarySearch(int[] nums, int from, int to, int key) {
    while (from <= to) {
      int mid = (from + to) >>> 1;
      if (nums[mid] > key) {
        to = mid - 1;
      } else {
        from = mid + 1;
      }
    }
    return from;
  }
}
