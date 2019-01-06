package com.loic.leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer
 * https://leetcode.com/problems/roman-to-integer/
 * <p>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 */
public final class Roman2Integer {

  public static int convert(String roman) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('I', 1);
    map.put('V', 5);
    map.put('X', 10);
    map.put('L', 50);
    map.put('C', 100);
    map.put('D', 500);
    map.put('M', 1000);

    int num = 0;
    for (int i = 0; i < roman.length(); i++) {
      int curNum = map.get(roman.charAt(i));
      int nextNum = i + 1 < roman.length() ? map.get(roman.charAt(i + 1)) : Integer.MIN_VALUE;
      if (curNum < nextNum) {
        num += (nextNum - curNum);
        i++;
      } else {
        num += curNum;
      }
    }
    return num;
  }
}
