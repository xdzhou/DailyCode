package com.loic.leetcode.medium;

/*
 * 8. String to Integer (atoi)
 * https://leetcode.com/problems/string-to-integer-atoi/
 *
 * Implement atoi which converts a string to an integer.
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found.
 * Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible,
 * and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number,
 * which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number,
 * or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 */
public class String2Integer {

  public static int atoi(String s) {
    //remove space
    s = s.trim();
    if (s.isEmpty()) {
      return 0;
    }
    int curIndex = 0;
    boolean positive = true;
    //check sign
    if (s.charAt(curIndex) == '-') {
      positive = false;
      curIndex++;
    } else if (s.charAt(curIndex) == '+') {
      curIndex++;
    }
    int value = 0;
    int curDigit;
    while (curIndex < s.length() && (curDigit = s.charAt(curIndex) - '0') >= 0 && curDigit < 10) {
      //overflows may happens when compute (value * 10)
      if (Math.abs(value) > Integer.MAX_VALUE / 10) {
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
      if (positive) {
        value = value * 10 + curDigit;
      } else {
        value = value * 10 - curDigit;
      }
      curIndex++;
      //overflows may happens when compute (+/- curDigit)
      if ((positive && value < 0) || (!positive && value > 0)) {
        return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
      }
    }
    return value;
  }

}
