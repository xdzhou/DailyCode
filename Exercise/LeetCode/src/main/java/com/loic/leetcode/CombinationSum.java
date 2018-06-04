package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

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
    return backtracking(candidates, candidates.length - 1, target);
  }

  private List<List<Integer>> backtracking(int[] candidates, int to, int target) {
    List<List<Integer>> result = new ArrayList<>();
    if (target == 0) {
      result.add(new ArrayList<>());
      return result;
    }
    for (int i = to; i >= 0; i--) {
      if (candidates[i] <= target) {
        int index = i;
        List<List<Integer>> preResult = backtracking(candidates, index, target - candidates[index]);
        preResult.forEach(item -> item.add(candidates[index]));
        result.addAll(preResult);
      }
    }
    return result;
  }
}
