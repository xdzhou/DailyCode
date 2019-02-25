package com.loic.leetcode.medium;

/**
 * 80. Remove Duplicates from Sorted Array II
 * https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * <p>
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 */
public final class RemoveDuplicatesSortedArray2 {

  public static int remove(int... nums) {
    int to = 0;
    int from = 0;
    // how many duplications of previous item
    int preValueTimes = 0;

    while (from < nums.length) {
      boolean equal = preValueTimes > 0 && nums[to - 1] == nums[from];
      if (!equal || preValueTimes == 1) {
        swap(from, to, nums);
        preValueTimes = equal ? preValueTimes + 1 : 1;
        to++;
      }
      from++;
    }
    return to;
  }

  private static void swap(int from, int to, int... nums) {
    if (from != to) {
      int tmp = nums[from];
      nums[from] = nums[to];
      nums[to] = tmp;
    }
  }
}
