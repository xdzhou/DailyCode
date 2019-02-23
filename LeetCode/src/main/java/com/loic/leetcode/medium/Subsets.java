package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets
 * https://leetcode.com/problems/subsets/
 * <p>
 * Given a set of distinct integers, nums, return all possible subsets (the power set).
 * <p>
 * Note: The solution set must not contain duplicate subsets.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,2,3]
 * Output:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 */
public final class Subsets {

  public static List<List<Integer>> create(int... nums) {
    List<List<Integer>> result = new ArrayList<>();
    for (int i = 0; i <= nums.length; i++) {
      process(result, i, nums.length, nums);
    }
    return result;
  }

  /**
   * @param result the list to put result
   * @param count  take now many items to put to the result list
   * @param maxLen we can only take item from first 'maxLen' items
   * @param nums   the array from which we take item
   */
  private static void process(List<List<Integer>> result, int count, int maxLen, int... nums) {
    if (count == 0) {
      result.add(new ArrayList<>());
    } else if (count == maxLen) {
      List<Integer> list = new ArrayList<>(maxLen);
      for (int i = 0; i < maxLen; i++) {
        list.add(nums[i]);
      }
      result.add(list);
    } else {
      // get all the combine without item at index 'maxLen-1'
      process(result, count, maxLen - 1, nums);
      // add all the combine with item at index 'maxLen-1'
      int preSize = result.size();
      process(result, count - 1, maxLen - 1, nums);
      for (int i = preSize; i < result.size(); i++) {
        result.get(i).add(nums[maxLen - 1]);
      }
    }
  }
}
