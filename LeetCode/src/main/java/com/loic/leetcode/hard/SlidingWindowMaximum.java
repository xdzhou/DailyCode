package com.loic.leetcode.hard;

/**
 * 239. Sliding Window Maximum
 * <p>
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.
 * <p>
 * Example:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * <p>
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 */
public class SlidingWindowMaximum {

  public static int[] maxSlidingWindow(int k, int... nums) {
    if (k == 1 || nums.length <= 1) {
      return nums;
    }
    MaxSegTree maxSegTree = new MaxSegTree(nums);
    int[] result = new int[nums.length - k + 1];
    for (int i = 0; i <= nums.length - k; i++) {
      result[i] = maxSegTree.max(i, i + k - 1);
    }
    return result;
  }

  private static final class MaxSegTree {
    private int[] data;

    public MaxSegTree(int... nums) {
      data = new int[nums.length * 2];
      System.arraycopy(nums, 0, data, nums.length, nums.length);
      for (int i = nums.length - 1; i > 0; i--) {
        data[i] = Math.max(data[i * 2], data[i * 2 + 1]);
      }
    }

    public int max(int from, int to) {
      int len = data.length >> 1;
      from += len;
      to += len;
      int max = Integer.MIN_VALUE;
      while (from <= to) {
        if (from % 2 == 1) {
          max = Math.max(max, data[from]);
          from += 1;
        }
        if (to % 2 == 0) {
          max = Math.max(max, data[to]);
          to -= 1;
        }
        from /= 2;
        to /= 2;
      }
      return max;
    }
  }
}
