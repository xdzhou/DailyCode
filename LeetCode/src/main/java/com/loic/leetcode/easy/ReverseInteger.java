package com.loic.leetcode.easy;

/**
 * 7. Reverse Integer
 * https://leetcode.com/problems/reverse-integer/
 * <p>
 * Given a 32-bit signed integer, reverse digits of an integer.
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 */
public class ReverseInteger {

  /**
   * ex. for 3-bit signed integer, (-4,-3,-2,-1,0,1,2,3)
   * (-4)-1=3 ==> smallest number minus 1 is the biggest number
   * 3+1=-4   ==> biggest number plus 1 is the smallest number
   */
  public static int reverse(int num) {
    int threshold = Integer.MAX_VALUE / 10;
    int cur = 0;
    while (num != 0) {
      int mod = num % 10;
      //overflows may happens when compute (10*cur)
      if (Math.abs(cur) > threshold) {
        return 0;
      }
      cur = 10 * cur + mod;
      num /= 10;
    }
    return cur;
  }
}
