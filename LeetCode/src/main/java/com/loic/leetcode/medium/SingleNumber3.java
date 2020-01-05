package com.loic.leetcode.medium;

/**
 * 260. Single Number III
 * <p>
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once.
 * <p>
 * Example:
 * <p>
 * Input:  [1,2,1,3,2,5]
 * Output: [3,5]
 */
public class SingleNumber3 {

  /**
   * Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:
   * <p>
   * In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find.
   * Note that since the two numbers are distinct, so there must be a set bit (that is, the bit with value '1') in the XOR result. Find
   * out an arbitrary set bit (for example, the rightmost set bit).
   * <p>
   * In the second pass, we divide all numbers into two groups, one with the aforementioned bit set,
   * another with the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups.
   * XOR numbers in each group, we can find a number in either group.
   */
  public static int[] singleNumber(int... nums) {
    int[] result = {0, 0};
    int diff = 0;
    for (int num : nums) {
      diff ^= num;
    }
    // get last '1'
    diff = diff & -diff;
    for (int num : nums) {
      if ((num & diff) == 0) {
        result[0] ^= num;
      } else {
        result[1] ^= num;
      }
    }
    return result;
  }
}
