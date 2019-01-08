package com.loic.leetcode.easy;

/**
 * 26. Remove Duplicates from Sorted Array
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
public final class RemoveDuplicatesFromSortedArray {

  public static int remove(int... nums) {
    if (nums.length <= 1) {
      return nums.length;
    }
    int index = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] > nums[i - 1]) {
        nums[index] = nums[i];
        index++;
      }
    }
    return index;
  }
}
