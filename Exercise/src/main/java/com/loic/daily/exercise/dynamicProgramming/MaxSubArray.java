package com.loic.daily.exercise.dynamicProgramming;

/**
 * https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/
 */
public class MaxSubArray {

  /**
   * find the sum of contiguous subarray within a one-dimensional array of numbers which has the largest sum
   */
  public static int resolve(int[] nums) {
    //this subarray meet following conditions
    //1. the first and late item of subarray is positive
    //2. Sum between first and i-th item of the subarray is positive
    //ATTENTION: special case : all the items are negative

    int currentSum = 0;
    int maxSum = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      currentSum += nums[i];
      maxSum = Math.max(maxSum, currentSum);
      if (currentSum < 0) {
        currentSum = 0;
      }
    }
    return maxSum;
  }

  //using dp algo
  public static int dpResolveOptimal(int[] nums) {
    //same idea of method dpResolve
    //but we don't use max aray, as every time we only use max(i-1) to compute max(i)

    //the max sum subarray which end at (i-1)-th item
    int previousMax = nums[0];
    int maxSum = previousMax;
    for (int i = 1; i < nums.length; i++) {
      previousMax = previousMax > 0 ? previousMax + nums[i] : nums[i];
      maxSum = Math.max(maxSum, previousMax);
    }
    return maxSum;
  }

  //using dp algo
  private static int dpResolve(int[] nums) {
    //max(i) is the max subArray which end at i-th item
    int[] max = new int[nums.length];
    max[0] = nums[0];

    int maxSum = max[0];
    for (int i = 1; i < nums.length; i++) {
      max[i] = max[i - 1] > 0 ? max[i - 1] + nums[i] : nums[i];
      maxSum = Math.max(maxSum, max[i]);
    }
    return maxSum;
  }
}
