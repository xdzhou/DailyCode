package com.loic.leetcode.medium;

import com.loic.solution.BiSolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 34. Search for a Range
 */
public class SearchRange implements BiSolutionProvider<int[], Integer, int[]> {

  @Override
  public List<BiFunction<int[], Integer, int[]>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private int[] resolve(int[] nums, int target) {
    int[] range = new int[]{-1, -1};

    int from = 0;
    int to = nums.length - 1;
    while (from <= to) {
      int mid = (from + to) >>> 1;
      if (nums[mid] == target) {
        if (mid+1 <= to && nums[mid + 1] == target) {
          from = mid + 1;
        } else {
          range[1] = mid;
          break;
        }
      } else if (nums[mid] < target) {
        from = mid + 1;
      } else {
        to = mid - 1;
      }
    }

    if (range[1] < 0) {
      return range;
    }

    from = 0;
    to = nums.length - 1;
    while (from <= to) {
      int mid = (from + to) >>> 1;
      if (nums[mid] == target) {
        if (mid-1 >= from && nums[mid - 1] == target) {
          to = mid - 1;
        } else {
          range[0] = mid;
          break;
        }
      } else if (nums[mid] < target) {
        from = mid + 1;
      } else {
        to = mid - 1;
      }
    }

    return range;
  }
}
