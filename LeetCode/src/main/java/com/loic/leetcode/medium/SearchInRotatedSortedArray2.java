package com.loic.leetcode.medium;

import java.util.Arrays;

/**
 * 81. Search in Rotated Sorted Array II
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return true, otherwise return false.
 */
public final class SearchInRotatedSortedArray2 {

  public static boolean search(int target, int... nums) {
    return search(0, nums.length - 1, target, nums);
  }

  private static boolean search(int from, int to, int target, int[] nums) {
    if (from > to) {
      return false;
    }
    int mid = (from + to) >>> 1;
    if (target == nums[mid]) {
      return true;
    }

    if (nums[from] < nums[mid]) {
      //left part is ascending
      if (nums[from] <= target && target < nums[mid]) {
        return Arrays.binarySearch(nums, from, mid, target) >= 0;
      } else {
        return search(mid + 1, to, target, nums);
      }
    } else if (nums[mid] < nums[to]) {
      // right part is ascending
      if (nums[mid] < target && target <= nums[to]) {
        return Arrays.binarySearch(nums, mid + 1, to + 1, target) >= 0;
      } else {
        return search(from, mid - 1, target, nums);
      }
    } else {
      return search(from, mid - 1, target, nums) || search(mid + 1, to, target, nums);
    }
  }
}
