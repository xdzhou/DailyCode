package com.loic.leetcode.medium;

/**
 * 162. Find Peak Element
 * <p>
 * A peak element is an element that is greater than its neighbors.
 * <p>
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
 * <p>
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * <p>
 * You may imagine that nums[-1] = nums[n] = -∞.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5
 * Explanation: Your function can return either index number 1 where the peak element is 2,
 * or index number 5 where the peak element is 6.
 * Note:
 * <p>
 * Your solution should be in logarithmic complexity.
 */
public class FindPeakElement {

  /**
   * We can view any given sequence in numsnums array as alternating ascending and descending sequences.
   * By making use of this, and the fact that we can return any peak as the result,
   * we can make use of Binary Search to find the required peak element.
   */
  public static int peakIndex(int... nums) {
    int from = 0;
    int to = nums.length - 1;
    while (from < to) {
      int mid = (from + to) >>> 1;
      boolean biggerThanPre = mid == 0 ? true : nums[mid - 1] < nums[mid];
      boolean biggerThanNext = mid == nums.length - 1 ? true : nums[mid + 1] < nums[mid];
      if (biggerThanPre && biggerThanNext) {
        return mid;
      } else if (biggerThanPre) {
        from = mid + 1;
      } else {
        to = mid - 1;
      }
    }
    return from;
  }


}
