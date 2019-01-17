package com.loic.leetcode.hard;

import java.util.Arrays;

/**
 * 41. First Missing Positive
 * https://leetcode.com/problems/first-missing-positive/
 * <p>
 * Given an unsorted integer array, find the smallest missing positive integer.
 */
public final class FirstMissingPositive {

  public static int find(int... nums) {
    Arrays.sort(nums);

    // 1 is the smallest positive integer, let's try to find it
    int index = Arrays.binarySearch(nums, 1);
    if (index < 0) {
      return 1;
    } else {
      for (int i = index; i < nums.length; i++) {
        if (i + 1 < nums.length && nums[i + 1] - nums[i] > 1) {
          return nums[i] + 1;
        }
      }
      return nums[nums.length - 1] + 1;
    }
  }
}
