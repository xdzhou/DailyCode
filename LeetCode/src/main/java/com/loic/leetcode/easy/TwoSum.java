package com.loic.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Two sum
 * https://leetcode.com/problems/two-sum/
 * <p>
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * <p>
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */
public class TwoSum {

  //check if the complement exists in the array.
  public static int[] resolve(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>(nums.length);
    for (int i = 0; i < nums.length; i++) {

      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[]{map.get(complement), i};
      }
      map.put(nums[i], i);
    }
    throw new IllegalStateException("NO SOLUTION");
  }
}
