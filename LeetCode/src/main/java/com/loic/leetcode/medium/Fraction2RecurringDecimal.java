package com.loic.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. Fraction to Recurring Decimal
 * <p>
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * <p>
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * <p>
 * Example 1:
 * <p>
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 * <p>
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 * <p>
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 */
public class Fraction2RecurringDecimal {

  public static String fraction2Decimal(int numerator, int denominator) {
    return toDecimal(numerator, denominator);
  }

  private static String toDecimal(long numerator, long denominator) {
    boolean negative = numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0;
    numerator = Math.abs(numerator);
    denominator = Math.abs(denominator);

    StringBuilder sb = new StringBuilder();
    if (negative) {
      sb.append('-');
    }
    sb.append(numerator / denominator);
    long rest = numerator % denominator;
    if (rest == 0) {
      return sb.toString();
    }
    sb.append('.');
    // a cache save already computed item
    Map<Long, Integer> cache = new HashMap<>();
    while (rest != 0) {
      rest *= 10;
      if (cache.containsKey(rest)) {
        // found repeating part
        sb.insert(cache.get(rest), "(");
        sb.append(')');
        break;
      }
      cache.put(rest, sb.length());
      sb.append(Math.abs(rest / denominator));
      rest = rest % denominator;
    }
    return sb.toString();
  }
}
