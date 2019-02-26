package com.loic.leetcode.easy;

/**
 * 67. Add Binary
 * https://leetcode.com/problems/add-binary/
 * <p>
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * Example 1:
 * <p>
 * Input: a = "11", b = "1"
 * Output: "100"
 */
public class AddBinary {

  public static String add(String a, String b) {
    if (b.length() > a.length()) {
      return add(b, a);
    }
    StringBuilder sb = new StringBuilder();
    int additional = 0;
    int index = 0;
    while (index < a.length()) {
      if (additional == 0 && index >= b.length()) {
        break;
      }
      int first = a.charAt(a.length() - 1 - index) - '0';
      int second = index < b.length() ? b.charAt(b.length() - 1 - index) - '0' : 0;
      int sum = first + second + additional;
      sb.insert(0, sum % 2);
      additional = sum / 2;
      index++;
    }
    if (index < a.length()) {
      sb.insert(0, a.substring(0, a.length() - index));
    }
    if (additional > 0) {
      sb.insert(0, additional);
    }
    return sb.toString();
  }
}
