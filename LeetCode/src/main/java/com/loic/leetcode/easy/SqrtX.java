package com.loic.leetcode.easy;

/**
 * 69. Sqrt(x)
 * https://leetcode.com/problems/sqrtx/
 * <p>
 * Implement int sqrt(int x).
 * <p>
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * <p>
 * Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.
 * <p>
 * Example 1:
 * <p>
 * Input: 4
 * Output: 2
 */
public final class SqrtX {

  public static int sqrt(int x) {
    int threshold = (int) Math.sqrt(Integer.MAX_VALUE);
    int from = 0, to = x;
    //ATTENTION: "from=mid" may cause infinite loop, so the check condition isn't "from<to"
    while (from + 1 < to) {
      int mid = (from + to) >>> 1;
      int midSquare = mid * mid;
      if (midSquare == x) {
        return mid;
      } else if (mid > threshold || midSquare > x) {
        //'midSquare < 0' means over flow
        to = mid - 1;
      } else {
        from = mid;
      }
    }
    if (from == to) {
      return from;
    } else {
      return to * to <= x ? to : from;
    }
  }
}
