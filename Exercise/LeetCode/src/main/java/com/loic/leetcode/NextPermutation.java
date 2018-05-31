package com.loic.leetcode;

import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
 * 31. Next Permutation
 */
public class NextPermutation implements SolutionProvider<int[], Void> {

  @Override
  public List<Function<int[], Void>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private Void resolve(int[] nums) {
    int start = 0;
    for (int i = nums.length - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        start = i + 1;
        int index = binarySearch(nums, i + 1, nums.length - 1, nums[i] + 1);
        if (index < 0) {
          index = (~index) - 1;
        }
        int tmp = nums[index];
        nums[index] = nums[i];
        nums[i] = tmp;
        break;
      }
    }
    int len = nums.length - start;
    for (int i = 0; i < len >>> 1; i++) {
      int tmp = nums[i + start];
      nums[i + start] = nums[nums.length - 1 - i];
      nums[nums.length - 1 - i] = tmp;
    }
    return null;
  }

  //nums is sorted from big to small, return rightmost index
  private int binarySearch(int[] nums, int from, int to, int key) {
    while (from <= to) {
      int mid = (from + to) >>> 1;
      if (nums[mid] == key) {
        if (mid + 1 < nums.length && nums[mid + 1] == key) {
          from = mid + 1;
        } else {
          return mid;
        }
      } else if (nums[mid] < key) {
        to = mid - 1;
      } else {
        from = mid + 1;
      }
    }
    return ~from;
  }
}
