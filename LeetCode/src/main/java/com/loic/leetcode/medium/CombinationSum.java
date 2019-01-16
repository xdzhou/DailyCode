package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/
 *
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * The same repeated number may be chosen from candidates unlimited number of times.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 */
public class CombinationSum {

  public static List<List<Integer>> combine(int target, int... candidates) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    backtracking(0, target, result, candidates);
    return result;
  }

  private static void backtracking(int from, int target, List<List<Integer>> result, int... candidates) {
    if (target == 0) {
      result.add(new ArrayList<>());
    } else {
      for (int i = from; i < candidates.length && candidates[i] <= target; i++) {
        int beforeSize = result.size();
        backtracking(i, target - candidates[i], result, candidates);
        for (int index = beforeSize; index < result.size(); index++) {
          // add at beginning, as we want non-decreasing order
          result.get(index).add(0, candidates[i]);
        }
      }
    }
  }
}
