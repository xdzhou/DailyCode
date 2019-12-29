package com.loic.leetcode.medium;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 229. Majority Element II
 * <p>
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * Note: The algorithm should run in linear time and in O(1) space.
 * <p>
 * Example 1:
 * <p>
 * Input: [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: [1,1,1,3,3,2,2,2]
 * Output: [1,2]
 */
public class MajorityElement2 {

  public static List<Integer> majorityElement(int... nums) {
    if (nums.length == 0) {
      return Collections.emptyList();
    }
    int candidate1 = 0, candidate2 = 0;
    int count1 = 0, count2 = 0;
    for (int num : nums) {
      if (count1 > 0 && candidate1 == num) {
        count1++;
      } else if (count2 > 0 && candidate2 == num) {
        count2++;
      } else if (count1 == 0) {
        candidate1 = num;
        count1 = 1;
      } else if (count2 == 0) {
        candidate2 = num;
        count2 = 1;
      } else {
        count1--;
        count2--;
      }
    }
    return Stream.of(candidate1, candidate2)
      .distinct()
      .filter(num -> count(num, nums) > nums.length / 3)
      .collect(Collectors.toList());
  }

  private static int count(int num, int... nums) {
    int count = 0;
    for (int n : nums) {
      if (num == n) {
        count++;
      }
    }
    return count;
  }
}
