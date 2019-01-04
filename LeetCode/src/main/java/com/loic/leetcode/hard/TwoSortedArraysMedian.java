package com.loic.leetcode.hard;

/*
 * 4. Median of Two Sorted Arrays
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * You may assume nums1 and nums2 cannot be both empty.
 */
public class TwoSortedArraysMedian {

  public static double median(int[] nums1, int[] nums2) {
    if (nums1.length > nums2.length) {
      return median(nums2, nums1);
    }
    // find k_th number, assuming k = 3
    int k = (nums1.length + nums2.length + 1) / 2;
    // the first k smallest items can be partially in nums1 array, the number of items int nums1
    // range [from, to]
    int from = 0, to = Math.min(nums1.length, k);
    while (from < to) {
      int mid = (from + to) >>> 1;
      if (getItem(nums1, mid) < getItem(nums2, k - mid - 1)) {
        from = mid + 1;
      } else if (getItem(nums2, k - mid) < getItem(nums1, mid - 1)) {
        to = mid - 1;
      } else {
        from = to = mid;
        break;
      }
    }
    //after this special binary search
    //the first k smallest items has "from" items in nums1, and "k-from" items in nums2
    //the variable "median" is the k_th number
    int median = Math.max(getItem(nums1, from - 1), getItem(nums2, k - from - 1));
    if ((nums1.length + nums2.length) % 2 == 0) {
      //the variable "median2" is the (k+1)_th number
      int median2 = Math.min(getItem(nums1, from), getItem(nums2, k - from));
      return (median + median2) / 2d;
    } else {
      return median;
    }
  }

  private static int getItem(int[] nums, int index) {
    if (index >= nums.length) {
      return Integer.MAX_VALUE;
    } else if (index < 0) {
      return Integer.MIN_VALUE;
    } else {
      return nums[index];
    }
  }
}
