package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 40. Combination Sum II
 */
public class CombinationSumII implements BiSolutionProvider<int[], Integer, List<List<Integer>>> {

  @Override
  public List<BiFunction<int[], Integer, List<List<Integer>>>> solutions() {
    return Arrays.asList(this::resolve);
  }

  protected List<List<Integer>> resolve(int[] candidates, int target) {
    Arrays.sort(candidates);
    return backtracking(candidates, candidates.length - 1, target);
  }

  private List<List<Integer>> backtracking(int[] candidates, int to, int target) {
    List<List<Integer>> result = new ArrayList<>();
    if (target == 0) {
      result.add(new ArrayList<>());
      return result;
    }
    int previous = -1;
    for (int i = to; i >= 0; i--) {
      if (candidates[i] <= target && candidates[i] != previous) {
        previous = candidates[i];
        int index = i;
        List<List<Integer>> preResult = backtracking(candidates, index - 1, target - candidates[index]);
        preResult.forEach(item -> item.add(candidates[index]));
        result.addAll(preResult);
      }
    }
    return result;
  }
}
