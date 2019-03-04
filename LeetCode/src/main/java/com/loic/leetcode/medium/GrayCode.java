package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 89. Gray Code
 * https://leetcode.com/problems/gray-code/
 *
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. A gray code sequence must begin with 0.
 *
 * Example 1:
 *
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 *
 * For a given n, a gray code sequence may not be uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence.
 *
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 */
public final class GrayCode {

  public static List<Integer> grayCodeOptimal(int n) {
    List<Integer> result = new ArrayList<>();
    result.add(0);
    if (n == 0) {
      return result;
    }
    for (int changeBitPos = 0; changeBitPos < n; changeBitPos++) {
      int size = result.size();
      for (int j = size - 1; j >= 0; j--) {
        result.add(result.get(j) | (1 << changeBitPos));
      }
    }
    return result;
  }

  public static List<Integer> grayCode(int n) {
    if (n == 0) {
      return Collections.singletonList(0);
    }
    boolean[] nums = new boolean[1 << n];
    List<Integer> result = new ArrayList<>(nums.length);
    result.add(0);
    nums[0] = true;
    while (result.size() < nums.length) {
      int lastInt = result.get(result.size() - 1);
      for (int i = 0; i < n; i++) {
        int next = changeOneBit(lastInt, i);
        if (!nums[next]) {
          nums[next] = true;
          result.add(next);
          break;
        }
      }
    }
    return result;
  }

  // change the bit of 'origin' int at the position
  private static int changeOneBit(int origin, int position) {
    return origin ^ (1 << position);
  }
}
