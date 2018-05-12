package com.loic.leetcode;

import com.loic.solution.SolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
 * 11. Container With Most Water
 */
public class ContainerMostWater implements SolutionProvider<int[], Integer> {

  @Override
  public List<Function<int[], Integer>> solutions() {
    return Arrays.asList(this::bruteForce, this::optimal);
  }

  /*
   * https://leetcode.com/problems/container-with-most-water/discuss/6099/yet-another-way-to-see-what-happens-in-the-on-algorithm
   */
  private Integer optimal(int[] input) {
    int head = 0;
    int tail = input.length - 1;
    int max = 0;
    while (head < tail) {
      max = Math.max(max, (tail - head) * Math.min(input[head], input[tail]));
      if (input[head] < input[tail]) {
        head ++;
      } else {
        tail --;
      }
    }
    return max;
  }

  private Integer bruteForce(int[] input) {
    if (input.length < 2) {
      return 0;
    }
    int max = Math.min(input[0], input[1]);
    for (int i = 2; i < input.length; i++) {
      for (int j = 0; j < i; j++) {
        max = Math.max(max, (i - j) * Math.min(input[i], input[j]));
      }
    }
    return max;
  }
}
