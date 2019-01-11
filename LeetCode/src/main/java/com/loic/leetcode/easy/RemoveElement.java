package com.loic.leetcode.easy;

/**
 * 27. Remove Element
 * https://leetcode.com/problems/remove-element/
 * <p>
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
public final class RemoveElement {

  public static int resolve(int val, int... nums) {
    int left = 0, right = nums.length - 1;
    do {
      while (left < nums.length && nums[left] != val) {
        left++;
      }
      while (right >= 0 && nums[right] == val) {
        right--;
      }
      if (left < right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
      }
    } while (left < right);
    return left;
  }
}
