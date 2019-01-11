package com.loic.leetcode.medium;

/**
 * 33. Search in Rotated Sorted Array
 * https://leetcode.com/problems/search-in-rotated-sorted-array/
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * <p>
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * <p>
 * You may assume no duplicate exists in the array.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 */
public final class SearchInRotatedSortedArray {

  public static int search(int target, int... nums) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = (left + right) >>> 1;
      if (target == nums[mid]) {
        return mid;
      }
      boolean leftSide;
      // here we are using '<=', as in case of '=', 'leftSide' should be false
      if (nums[left] <= nums[mid]) {
        //nums[left:mid) is increasing
        leftSide = nums[left] <= target && target < nums[mid];
      } else {
        //nums[left:mid) is still in rotated state
        leftSide = nums[left] <= target || target < nums[mid];
      }
      if (leftSide) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}
