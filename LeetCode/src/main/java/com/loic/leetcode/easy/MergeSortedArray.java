package com.loic.leetcode.easy;

/**
 * 88. Merge Sorted Array
 * https://leetcode.com/problems/merge-sorted-array/
 * <p>
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * <p>
 * Note:
 * <p>
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * Example:
 * <p>
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * Output: [1,2,2,3,5,6]
 */
public final class MergeSortedArray {

  public static void merge(int[] nums1, int m, int[] nums2, int n) {
    //sort from big to small, from tail to head
    int curIndex = m + n - 1;
    int tail1 = m - 1;
    int tail2 = n - 1;
    while (tail2 >= 0) {
      if (tail1 >= 0 && nums1[tail1] >= nums2[tail2]) {
        nums1[curIndex] = nums1[tail1];
        tail1--;
      } else {
        nums1[curIndex] = nums2[tail2];
        tail2--;
      }
      curIndex--;
    }
  }
}
