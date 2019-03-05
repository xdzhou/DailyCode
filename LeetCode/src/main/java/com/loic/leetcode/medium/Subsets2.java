package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. Subsets II
 * https://leetcode.com/problems/subsets-ii/
 *
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 */
public final class Subsets2 {

  public static List<List<Integer>> subsetsWithDup(int... nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i <= nums.length; i++) {
      subsetsWithlen(result, nums, nums.length, i);
    }
    return result;
  }

  // generate subset of length 'len' from array nums[0,to)
  private static void subsetsWithlen(List<List<Integer>> result, int[] nums, int to, int len) {
    if (len == 0) {
      result.add(new ArrayList<>());
    } else if (to == len) {
      List<Integer> tmp = new ArrayList<>();
      for (int i = 0; i < to; i++) {
        tmp.add(nums[i]);
      }
      result.add(tmp);
    } else if (to > len) {
      // generate subsets from array nums[0,to-1)
      subsetsWithlen(result, nums, to - 1, len);
      int index = to - 2;
      while (index >= 0 && to - 2 - index < len && nums[index] == nums[to - 1]) {
        index--;
      }
      // how many items from tail is same as nums[to-1]
      int sameNumberCount = to - 2 - index;
      if (sameNumberCount < len) {
        int size = result.size();
        subsetsWithlen(result, nums, to - sameNumberCount - 1, len - sameNumberCount - 1);
        for (int i = size; i < result.size(); i++) {
          for (int count = 0; count < sameNumberCount + 1; count++) {
            result.get(i).add(nums[to - 1]);
          }
        }
      }
    }
  }
}
