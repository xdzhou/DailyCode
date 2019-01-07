package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 15. 3Sum
 * https://leetcode.com/problems/3sum/
 *
 * Given an array nums of n integers, are there elements a, b, c in nums
 * such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 *
 * NOTE: The solution set must not contain duplicate triplets.
 */
public class ThreeSum {

  public static List<List<Integer>> optimal(int[] input) {
    Arrays.sort(input);

    List<List<Integer>> lists = new ArrayList<>();

    for (int index = 0; index < input.length - 2; index++) {
      if (index - 1 >= 0 && input[index] == input[index - 1]) {
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
          head++;
          tail--;
        } else if (value < -input[index]) {
          head++;
        } else {
          tail--;
        }
      }
    }
    return lists;
  }

  public static List<List<Integer>> resolve(int[] input) {
    Arrays.sort(input);
    Set<List<Integer>> set = new HashSet<>();
    for (int index = 0; index < input.length - 2; index++) {
      int head = index + 1;
      int tail = input.length - 1;
      while (head < tail) {
        int value = input[head] + input[tail];
        if (value == -input[index]) {
          List<Integer> list = Arrays.asList(input[index], input[head], input[tail]);
          set.add(list);
          head++;
          tail--;
        } else if (value < -input[index]) {
          head++;
        } else {
          tail--;
        }
      }
    }
    return new ArrayList<>(set);
  }
}
