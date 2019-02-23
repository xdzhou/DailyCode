package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. Combinations
 * https://leetcode.com/problems/combinations/
 * <p>
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * Example:
 * <p>
 * Input: n = 4, k = 2
 * Output:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public final class Combinations {

  public static List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    process(result, n, k);
    return result;
  }

  private static void process(List<List<Integer>> result, int n, int k) {
    if (k == 0) {
      result.add(new ArrayList<>());
    } else if (n == k) {
      List<Integer> list = new ArrayList<>(n);
      for (int i = 1; i <= n; i++) {
        list.add(i);
      }
      result.add(list);
    } else {
      // get all the combine without 'n'
      process(result, n - 1, k);
      // add all the combine with 'n'
      int preSize = result.size();
      process(result, n - 1, k - 1);
      for (int i = preSize; i < result.size(); i++) {
        result.get(i).add(n);
      }
    }
  }
}
