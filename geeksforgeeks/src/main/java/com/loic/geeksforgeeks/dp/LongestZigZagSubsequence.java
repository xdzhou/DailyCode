package com.loic.geeksforgeeks.dp;

import java.util.Arrays;

/**
 * https://practice.geeksforgeeks.org/problems/longest-zig-zag-sub-sequence/0
 * <p>
 * Given an array A of N positive integers.
 * The task is to find the longest Zig-Zag subsequence problem such that all elements of this are alternating (Ai-1 < Ai > Ai+1).
 */
public class LongestZigZagSubsequence {

  public static int resolve(int... nums) {
    int[] highDp = new int[nums.length];
    int[] lowDp = new int[nums.length];
    Arrays.fill(highDp, 1);
    Arrays.fill(lowDp, 1);
    int max = 1;
    for (int i = 1; i < nums.length; i++) {
      for (int j = 0; j < i; j++) {
        if (nums[i] > nums[j]) {
          highDp[i] = Math.max(highDp[i], lowDp[j] + 1);
        } else if (nums[i] < nums[j]) {
          lowDp[i] = Math.max(lowDp[i], highDp[j] + 1);
        }
      }
      max = Math.max(max, highDp[i]);
      max = Math.max(max, lowDp[i]);
    }

    return max;
  }
}
