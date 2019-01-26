package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. Permutations
 * https://leetcode.com/problems/permutations/
 *
 * Given a collection of distinct integers, return all possible permutations.
 */
public final class Permutations {

  public static List<List<Integer>> permute(int... nums) {
    List<List<Integer>> result = new ArrayList<>();
    process(result, 0, nums);
    return result;
  }

  private static void process(List<List<Integer>> result, int start, int... nums) {
    if (start == nums.length) {
      result.add(new ArrayList<>());
    } else {
      process(result, start + 1, nums);
      int delta = nums.length - 1 - start;

      int size = result.size();
      for (int j = 0; j < size; j++) {
        for (int i = 0; i <= delta; i++) {
          if (i == delta) {
            result.get(j).add(i, nums[start]);
          } else {
            List<Integer> subList = new ArrayList<>(result.get(j));
            subList.add(i, nums[start]);
            result.add(subList);
          }
        }
      }
    }
  }
}
