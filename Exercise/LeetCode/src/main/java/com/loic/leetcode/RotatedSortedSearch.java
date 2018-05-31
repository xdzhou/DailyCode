package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 33. Search in Rotated Sorted Array
 */
public class RotatedSortedSearch implements BiSolutionProvider<int[], Integer, Integer> {

  @Override
  public List<BiFunction<int[], Integer, Integer>> solutions() {
    return Arrays.asList(this::resolve, this::search);
  }

  private int search(int[] nums, int target) {
    int from = 0;
    int to = nums.length - 1;
    //find index of smallest value
    // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
    // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would have been terminated.
    while (from < to) {
      int mid = (from + to) >>> 1;
      if (nums[mid] > nums[to]) {
        from = mid +1;
      } else {
        //ATTENTION: to != mid -1
        to = mid;
      }
    }
    int rot = from;

    from = 0;
    to = nums.length - 1;
    //then use normal binary search and accounting for rotation
    while (from <= to) {
      int m = (from + to) >>> 1;
      int mid = (m + rot) % nums.length;
      if (nums[mid] == target) {
        return mid;
      } else if (nums[mid] < target) {
        from = m + 1;
      } else {
        to = m - 1;
      }
    }
    return -1;
  }

  private int resolve(int[] nums, int key) {
    int from = 0;
    int to = nums.length - 1;
    while (from <= to) {
      int mid = (from + to) >>> 1;
      if (nums[mid] == key) {
        return mid;
      }
      boolean left = true;
      if (nums[from] < nums[to]) {
        left = nums[mid] > key;
      } else if (nums[mid] >= nums[from]) {
        left = key < nums[mid] && key >= nums[from];
      } else {
        left = key < nums[mid] || key >= nums[from];
      }

      if (left) {
        to = mid - 1;
      } else {
        from = mid + 1;
      }
    }
    return -1;
  }
}
