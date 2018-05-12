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
    return Arrays.asList(this::resolve, this::optimal);
  }

  private List<List<Integer>> optimal(int[] input) {
    Arrays.sort(input);

    List<List<Integer>> lists = new ArrayList<>();

    for(int index = 0; index < input.length - 2; index++) {
      if (index - 1 >= 0 && input[index] == input[index -1]) {
        //avoid putting same triple to the list when previous value is same as current
        continue;
      }
      int head = index + 1;
      int tail = input.length - 1;
      Integer preHead = null;
      while (head < tail) {
        int value = input[head] + input[tail];
        if (value == -input[index]) {
          if (preHead == null || preHead != input[head]) {
            //avoid putting same triple to the list when previous value is same as current
            List<Integer> list = Arrays.asList(input[index], input[head], input[tail]);
            lists.add(list);
            preHead = input[head];
          }
          head ++;
          tail --;
        } else if (value < -input[index]) {
          head ++;
        } else {
          tail --;
        }
      }
    }
    return lists;
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
