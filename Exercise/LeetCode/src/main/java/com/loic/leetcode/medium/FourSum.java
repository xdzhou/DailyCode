package com.loic.leetcode.medium;

import com.loic.solution.BiSolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 18. 4Sum
 */
public class FourSum implements BiSolutionProvider<int[], Integer, List<List<Integer>>> {

  @Override
  public List<BiFunction<int[], Integer, List<List<Integer>>>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private List<List<Integer>> resolve(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 3; i++) {
      if (i - 1 >= 0 && nums[i] == nums[i - 1]) {
        continue;
      }
      for (int j = i + 1; j < nums.length - 2; j++) {
        if (j - 1 >= i + 1 && nums[j] == nums[j - 1]) {
          continue;
        }
        int head = j + 1;
        int tail = nums.length - 1;
        Integer preHead = null;
        while (head < tail) {
          int value = nums[i] + nums[j] + nums[head] + nums[tail];
          if (value == target) {
            if (preHead == null || preHead != nums[head]) {
              result.add(Arrays.asList(nums[i], nums[j], nums[head], nums[tail]));
              preHead = nums[head];
            }
            head++;
            tail--;
          } else if (value < target) {
            head++;
          } else {
            tail--;
          }
        }
      }
    }
    return result;
  }
}
