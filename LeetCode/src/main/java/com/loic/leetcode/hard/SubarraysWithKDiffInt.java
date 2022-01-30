package com.loic.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. Subarrays with K Different Integers
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * A good array is an array where the number of different integers in that array is exactly k.
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * <p>
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * <p>
 * Example 2:
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * Constraints:
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i], k <= nums.length
 */
public class SubarraysWithKDiffInt {

  public int resolve(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();
    int size = 0;
    int head, tail = 0, rightMost = -1;
    for (head = 0; head <= nums.length - k; head++) {
      while (count.size() < k && tail < nums.length) {
        count.put(nums[tail], count.getOrDefault(nums[tail], 0) + 1);
        tail++;
      }
      if (count.size() < k) {
        break;
      }
      if (rightMost < tail) {
        rightMost = tail;
        while (rightMost < nums.length && count.containsKey(nums[rightMost])) {
          rightMost++;
        }
      }
      size += (rightMost - tail + 1);
      // advance head to right
      int headCount = count.get(nums[head]);
      if (headCount == 1) {
        count.remove(nums[head]);
      } else {
        count.put(nums[head], headCount - 1);
      }
    }
    return size;
  }
}
