package com.loic.leetcode.medium;

/**
 * 215. Kth Largest Element in an Array
 * <p>
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargestElement {

  public static int findKthLargest(int k, int... nums) {
    return findKthLargest(k, 0, nums.length - 1, nums);
  }

  private static int findKthLargest(int k, int from, int to, int[] nums) {
    if (k == 1) {
      // get max
      int max = nums[from];
      for (int i = from + 1; i <= to; i++) {
        max = Math.max(max, nums[i]);
      }
      return max;
    } else if (k == to - from + 1) {
      // get min
      int min = nums[from];
      for (int i = from + 1; i <= to; i++) {
        min = Math.min(min, nums[i]);
      }
      return min;
    }
    // use quick sort
    int left = from + 1, right = to;
    while (left < right) {
      while (left <= to && nums[left] >= nums[from]) {
        left++;
      }
      while (right > from && nums[right] < nums[from]) {
        right--;
      }
      if (left < right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
      }
    }
    int len = right - from + 1;
    if (k == len) {
      return nums[from];
    } else if (k > len) {
      return findKthLargest(k - len, left, to, nums);
    } else {
      int tmp = nums[from];
      nums[from] = nums[right];
      nums[right] = tmp;
      return findKthLargest(k, from, right - 1, nums);
    }
  }
}
