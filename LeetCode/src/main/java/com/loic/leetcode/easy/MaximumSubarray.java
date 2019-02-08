package com.loic.leetcode.easy;

/**
 * 53. Maximum Subarray
 * https://leetcode.com/problems/maximum-subarray/
 * <p>
 * Given an integer array nums, find the contiguous subarray (containing at least one number)
 * which has the largest sum and return its sum.
 */
public class MaximumSubarray {

  public static int maxSubArray(int... nums) {
    int max = nums[0];
    // previous sum can be connect to next item (which is not negative)
    int previousSum = 0;
    for (int n : nums) {
      max = Math.max(max, n + previousSum);
      if (n + previousSum > 0) {
        previousSum = n + previousSum;
      } else {
        previousSum = 0;
      }
    }
    return max;
  }
}
