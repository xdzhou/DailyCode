package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 315. Count of Smaller Numbers After Self
 * https://leetcode.com/problems/count-of-smaller-numbers-after-self/
 * <p>
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [5,2,6,1]
 * Output: [2,1,1,0]
 * Explanation:
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 */
public class CountSmaller {

  public static List<Integer> countSmaller(int... nums) {
    List<Integer> result = new ArrayList<>();
    List<Integer> sorted = new ArrayList<>();
    for (int i = nums.length - 1; i >= 0; i--) {
      int index = binarySearch(sorted, nums[i]);
      if (index < 0) {
        index = ~index;
      }
      result.add(0, index);
      sorted.add(index, nums[i]);
    }
    return result;
  }

  private static int binarySearch(List<Integer> sorted, int key) {
    int from = 0, to = sorted.size() - 1;
    while (from <= to) {
      int mid = (from + to) >>> 1;
      if (sorted.get(mid) == key) {
        if (from == mid || sorted.get(mid - 1) < key) {
          return mid;
        } else {
          to = mid - 1;
        }
      } else if (sorted.get(mid) < key) {
        from = mid + 1;
      } else {
        to = mid - 1;
      }
    }
    return ~from;
  }
}
