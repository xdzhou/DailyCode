package com.loic.leetcode.hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 233. Number of Digit One
 * <p>
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * <p>
 * Example:
 * <p>
 * Input: 13
 * Output: 6
 * Explanation: Digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 */
public class NumberOfDigitOne {

  public static int countDigitOne(int n) {
    return count(n, new HashMap<>());
  }

  private static int count(int n, Map<Integer, Integer> cache) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }
    System.out.println("count " + n);
    if (n <= 0) {
      return 0;
    } else if (n <= 9) {
      return 1;
    }
    int pow = 0;
    int tmp = n;
    while (tmp / 10 > 0) {
      pow++;
      tmp /= 10;
    }
    int base = (int) Math.pow(10, pow);
    int firstNum = n / base;
    int result;
    if (firstNum == 1) {
      result = count(base - 1, cache) + count(n % base, cache) + n - base + 1;
    } else {
      result = firstNum * count(base - 1, cache) + count(n % base, cache) + base;
    }
    cache.put(n, result);
    return result;
  }
}
