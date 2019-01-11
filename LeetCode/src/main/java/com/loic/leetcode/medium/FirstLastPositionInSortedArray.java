package com.loic.leetcode.medium;

/**
 * 34. Find First and Last Position of Element in Sorted Array
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * <p>
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * <p>
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * <p>
 * If the target is not found in the array, return [-1, -1].
 */
public final class FirstLastPositionInSortedArray {

  public static int[] find(int target, int... nums) {
    int rightMost = rightMostBinarySearch(target, nums);
    if (rightMost == -1) {
      return new int[]{-1, -1};
    } else {
      return new int[]{leftMostBinarySearch(0, rightMost, target, nums), rightMost};
    }
  }

  private static int leftMostBinarySearch(int left, int right, int target, int... nums) {
    while (left <= right) {
      int mid = (left + right) >>> 1;
      if (target < nums[mid]) {
        right = mid - 1;
      } else if (target > nums[mid]) {
        left = mid + 1;
      } else {
        if (mid - 1 >= left && nums[mid - 1] == target) {
          right = mid - 1;
        } else {
          return mid;
        }
      }
    }
    return -1;
  }

  private static int rightMostBinarySearch(int target, int... nums) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int mid = (left + right) >>> 1;
      if (target < nums[mid]) {
        right = mid - 1;
      } else if (target > nums[mid]) {
        left = mid + 1;
      } else {
        if (mid + 1 <= right && nums[mid + 1] == target) {
          left = mid + 1;
        } else {
          return mid;
        }
      }
    }
    return -1;
  }
}
