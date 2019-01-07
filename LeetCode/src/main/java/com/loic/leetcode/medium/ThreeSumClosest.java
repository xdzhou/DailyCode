package com.loic.leetcode.medium;

import java.util.Arrays;

/*
 * 16. 3Sum Closest
 * https://leetcode.com/problems/3sum-closest/
 *
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 */
public class ThreeSumClosest {


  public static int resolve(int[] input, int target) {
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
