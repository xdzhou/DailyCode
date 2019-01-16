package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
 * 40. Combination Sum II
 * https://leetcode.com/problems/combination-sum-ii/
 *
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.
 * Each number in candidates may only be used once in the combination.
 *
 * Note:
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 */
public final class CombinationSumII {

  public static List<List<Integer>> combine(int target, int... candidates) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    process1(target, 0, result, candidates);
    return result;
  }

  public static List<List<Integer>> combine2(int target, int... candidates) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    process2(target, 0, result, candidates);
    return result;
  }

  private static void process1(int target, int from, List<List<Integer>> result, int... candidates) {
    if (target == 0) {
      result.add(new LinkedList<>());
    } else if (from < candidates.length && candidates[from] <= target) {
      // we pick item 'candidates[from]'
      int beforeSize = result.size();
      process1(target - candidates[from], from + 1, result, candidates);
      for (int index = beforeSize; index < result.size(); index++) {
        result.get(index).add(0, candidates[from]);
      }
      // don't pick item 'candidates[from]'
      int cur = from;
      // skip all the items that are same as current item
      while (cur + 1 < candidates.length && candidates[cur + 1] == candidates[cur]) {
        cur++;
      }
      process1(target, cur + 1, result, candidates);
    }
  }

  private static void process2(int target, int from, List<List<Integer>> result, int... candidates) {
    if (target == 0) {
      result.add(new LinkedList<>());
    } else {
      // we pick a negative number by default, as the input are always positive
      int previous = -1;
      for (int i = from; i < candidates.length && candidates[i] <= target; i++) {
        if (candidates[i] != previous) {
          int beforeSize = result.size();
          process2(target - candidates[i], i + 1, result, candidates);
          for (int index = beforeSize; index < result.size(); index++) {
            result.get(index).add(0, candidates[i]);
          }
        }
        previous = candidates[i];
      }
    }
  }
}
