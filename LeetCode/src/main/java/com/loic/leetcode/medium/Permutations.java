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
      // generate permutation of nums[start+1:]
      process(result, start + 1, nums);
      int delta = nums.length - 1 - start;

      int size = result.size();
      // for all the permutation of nums[start+1:], insert nums[start] at different position
      for (int subIndex = 0; subIndex < size; subIndex++) {
        for (int insertPosition = 0; insertPosition <= delta; insertPosition++) {
          if (insertPosition == delta) {
            result.get(subIndex).add(insertPosition, nums[start]);
          } else {
            List<Integer> subList = new ArrayList<>(result.get(subIndex));
            subList.add(insertPosition, nums[start]);
            result.add(subList);
          }
        }
      }
    }
  }
}
