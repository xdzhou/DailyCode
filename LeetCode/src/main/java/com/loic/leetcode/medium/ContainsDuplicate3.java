package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 220. Contains Duplicate III
 * <p>
 * Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,1], k = 3, t = 0
 * Output: true
 * Example 2:
 * <p>
 * Input: nums = [1,0,1,1], k = 1, t = 2
 * Output: true
 * Example 3:
 * <p>
 * Input: nums = [1,5,9,1,5,9], k = 2, t = 3
 * Output: false
 */
public class ContainsDuplicate3 {

  public static boolean containsNearbyAlmostDuplicate(int k, int t, int... nums) {
    if (nums.length < 2 || k <= 0 || t < 0) {
      return false;
    }
    k = Math.min(k, nums.length - 1);
    List<Long> bucket = new ArrayList<>();
    for (int i = 0; i <= k; i++) {
      bucket.add((long) nums[i]);
    }
    Collections.sort(bucket);
    for (int i = 1; i < bucket.size(); i++) {
      if (Math.abs(bucket.get(i) - bucket.get(i - 1)) <= t) {
        return true;
      }
    }
    for (int i = 1; i <= nums.length - 1 - k; i++) {
      if (nums[i - 1] != nums[i + k]) {
        bucket.remove(Collections.binarySearch(bucket, (long) nums[i - 1]));
        int index = Collections.binarySearch(bucket, (long) nums[i + k]);
        if (index >= 0) {
          return true;
        } else {
          index = -index - 1;
          if (index - 1 >= 0 && Math.abs(bucket.get(index - 1) - nums[i + k]) <= t) {
            return true;
          } else if (index < bucket.size() && Math.abs(bucket.get(index) - nums[i + k]) <= t) {
            return true;
          } else {
            bucket.add(index, (long) nums[i + k]);
          }
        }
      }
    }
    return false;
  }
}
