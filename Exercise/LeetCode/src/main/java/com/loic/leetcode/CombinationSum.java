package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

/*
 * 39. Combination Sum
 */
public class CombinationSum implements BiSolutionProvider<int[], Integer, List<List<Integer>>> {

  @Override
  public List<BiFunction<int[], Integer, List<List<Integer>>>> solutions() {
    return Arrays.asList(this::resolve);
  }

  protected List<List<Integer>> resolve(int[] candidates, int target) {
    Arrays.sort(candidates);
    List<List<Integer>> result = new ArrayList<>();
    backtracking(candidates, candidates.length - 1, target, result);
    return result;
  }

  private void backtracking(int[] candidates, int to, int target, List<List<Integer>> result) {
    if (target == 0) {
      result.add(new ArrayList<>());
    } else {
      int previous = -1;
      for (int i = to; i >= 0; i--) {
        if (candidates[i] <= target && candidates[i] != previous) {
          previous = candidates[i];
          int index = i;
          int len = result.size();
          backtracking(candidates, index, target - candidates[index], result);
          IntStream.range(len, result.size()).forEach(item -> result.get(item).add(candidates[index]));
        }
      }
    }
  }
}
