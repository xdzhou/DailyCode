package com.loic.leetcode;

import com.loic.solution.SolutionProvider;

import java.util.*;
import java.util.function.Function;

/*
 * 15. 3Sum
 */
public class ThreeSum implements SolutionProvider<int[], List<List<Integer>>> {

  @Override
  public List<Function<int[], List<List<Integer>>>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private List<List<Integer>> resolve(int[] input) {
    Arrays.sort(input);
    Map<List<Integer>, List<Integer>> map = new HashMap<>();
    for(int index = 0; index < input.length - 2; index++) {
      int head = index + 1;
      int tail = input.length - 1;
      while (head < tail) {
        int value = input[head] + input[tail];
        if (value == -input[index]) {
          List<Integer> list = Arrays.asList(input[index], input[head], input[tail]);
          map.put(list, list);
          head ++;
          tail --;
        } else if (value < -input[index]) {
          head ++;
        } else {
          tail --;
        }
      }
    }
    return new ArrayList<>(map.values());
  }
}
