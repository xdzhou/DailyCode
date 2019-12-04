package com.loic.leetcode.hard;

/**
 * 154. Find Minimum in Rotated Sorted Array II
 * <p>
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * <p>
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * <p>
 * Find the minimum element.
 * <p>
 * The array may contain duplicates.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,3,5]
 * Output: 1
 * Example 2:
 * <p>
 * Input: [2,2,2,0,1]
 * Output: 0
 */
public class MinInRotatedSortedArray2 {

  public static int findMin(int... nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    int left = 0;
    int right = nums.length - 1;
    while (left < right) {
      int mid = (left + right) >>> 1;
      if (nums[mid] == nums[right]) {
        right--;
      } else if (nums[mid] < nums[right]) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return nums[left];
  }

  public static int findMin2(int... nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }
    return min(0, nums.length - 1, nums);
  }

  private static int min(int from, int to, int... nums) {
    if (from == to) {
      return nums[from];
    } else if (to == from + 1) {
      return Math.min(nums[from], nums[to]);
    }
    int mid = (from + to) >>> 1;
    if (nums[from] < nums[to]) {
      return nums[from];
    } else if (nums[from] == nums[to]) {
      return Math.min(min(from, mid, nums), min(mid, to, nums));
    } else {
      if (nums[mid] >= nums[from]) {
        return min(mid, to, nums);
      } else {
        return min(from, mid, nums);
      }
    }
  }
}
