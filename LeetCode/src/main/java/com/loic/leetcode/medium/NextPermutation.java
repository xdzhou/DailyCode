package com.loic.leetcode.medium;

/*
 * 31. Next Permutation
 * https://leetcode.com/problems/next-permutation/
 *
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place and use only constant extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 */
public class NextPermutation {


  public static void resolve(int[] nums) {
    if (nums.length > 1) {
      int index = nums.length - 1;
      // from end to start, find the first index which decrease
      while (index - 1 >= 0 && nums[index] <= nums[index - 1]) {
        index--;
      }
      // now we know that nums[index:] is non-increasing
      if (index - 1 >= 0) {
        // switch num[index-1] and the minimum number that is bigger than num[index-1] from
        // nums[index:]
        // after this switch, nums[index:] is still non-increasing
        int from = index, to = nums.length - 1;
        while (from < to) {
          int mid = (from + to) >>> 1;
          if (nums[mid] <= nums[index - 1]) {
            to = mid - 1;
          } else {
            if (nums[mid + 1] > nums[index - 1]) {
              from = mid + 1;
            } else {
              from = to = mid;
            }
          }
        }
        int temp = nums[index - 1];
        nums[index - 1] = nums[from];
        nums[from] = temp;
      }
      // make nums[index:] non-decreasing
      int mid = (index + nums.length - 1) >>> 1;
      for (int i = index; i <= mid; i++) {
        int temp = nums[i];
        nums[i] = nums[index + nums.length - 1 - i];
        nums[index + nums.length - 1 - i] = temp;
      }
    }
  }
}
