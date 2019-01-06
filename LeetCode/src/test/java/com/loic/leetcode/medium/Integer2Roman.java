package com.loic.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 12. Integer to Roman
 * https://leetcode.com/problems/integer-to-roman/
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 */
public final class Integer2Roman {

  public static String convert(int num) {
    Map<Integer, String> romanMap = new HashMap<>();
    romanMap.put(1, "I");
    romanMap.put(4, "IV");
    romanMap.put(5, "V");
    romanMap.put(9, "IX");
    romanMap.put(10, "X");
    romanMap.put(40, "XL");
    romanMap.put(50, "L");
    romanMap.put(90, "XC");
    romanMap.put(100, "C");
    romanMap.put(400, "CD");
    romanMap.put(500, "D");
    romanMap.put(900, "CM");
    romanMap.put(1000, "M");

    StringBuilder sb = new StringBuilder();
    int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    while (num != 0) {
      int index = 0;
      while (num < values[index]) {
        index++;
      }
      num -= values[index];
      sb.append(romanMap.get(values[index]));
    }
    return sb.toString();
  }
}
