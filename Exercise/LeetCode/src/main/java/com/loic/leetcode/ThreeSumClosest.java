package com.loic.leetcode;

import com.loic.solution.BiSolutionProvider;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

/*
 * 16. 3Sum Closest
 */
public class ThreeSumClosest implements BiSolutionProvider<int[], Integer, Integer> {

  @Override
  public List<BiFunction<int[], Integer, Integer>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private int resolve(int[] input, int target) {
    Arrays.sort(input);
    int retVal = input[0] + input[1] + input[2];
    for (int index = 0; index < input.length - 2; index++) {
      if (index - 1 >= 0 && input[index] == input[index - 1]) {
        //avoid putting same triple to the list when previous value is same as current
        continue;
      }
      int head = index + 1;
      int tail = input.length - 1;

      while (head < tail) {
        int value = input[head] + input[tail] + input[index];
        if (value == target) {
          return target;
        } else if (value < target) {
          head++;
        } else {
          tail--;
        }
        retVal = Math.abs(retVal - target) < Math.abs(value - target) ? retVal : value;
      }
    }
    return retVal;
  }
}
