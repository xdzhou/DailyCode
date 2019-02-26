package com.loic.leetcode.easy;

/**
 * 66. Plus One
 * https://leetcode.com/problems/plus-one/
 * <p>
 * Given a non-empty array of digits representing a non-negative integer, plus one to the integer.
 * <p>
 * The digits are stored such that the most significant digit is at the head of the list, and each element in the array contain a single digit.
 * <p>
 * You may assume the integer does not contain any leading zero, except the number 0 itself.
 * <p>
 * Example 1:
 * <p>
 * Input: [1,2,3]
 * Output: [1,2,4]
 * Explanation: The array represents the integer 123.
 */
public final class PlusOne {

  public static int[] addOne(int... digits) {
    int additional = 1;
    for (int i = digits.length - 1; i >= 0 && additional > 0; i--) {
      int value = additional + digits[i];
      if (value > 9) {
        value = value % 10;
      } else {
        additional = 0;
      }
      digits[i] = value;
    }
    if (additional > 0) {
      int[] result = new int[digits.length + 1];
      result[0] = 1;
      System.arraycopy(digits, 0, result, 1, digits.length);
      return result;
    }
    return digits;
  }
}
