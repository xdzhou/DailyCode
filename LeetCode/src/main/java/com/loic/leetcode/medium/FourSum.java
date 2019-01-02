package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 18. 4Sum
 */
public class FourSum {

  public static List<List<Integer>> resolve(int[] nums, int target) {
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
