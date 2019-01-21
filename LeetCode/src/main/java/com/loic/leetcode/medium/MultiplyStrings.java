package com.loic.leetcode.medium;

/**
 * 43. Multiply Strings
 * https://leetcode.com/problems/multiply-strings/
 * <p>
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the product of num1 and num2, also represented as a string.
 */
public final class MultiplyStrings {

  public static String multiply(String num1, String num2) {
    if (num1.length() < num2.length()) {
      return multiply(num2, num1);
    }
    int[] digs = new int[num1.length() + num2.length()];
    int index;
    for (int i = num2.length() - 1; i >= 0; i--) {
      int dig1 = num2.charAt(i) - '0';
      if (dig1 != 0) {
        index = num1.length() + i;
        for (int j = num1.length() - 1; j >= 0; j--) {
          int dig2 = num1.charAt(j) - '0';
          int sum = dig1 * dig2 + digs[index];
          digs[index] = sum % 10;
          digs[--index] += sum / 10;
        }
      }
    }

    index = 0;
    //skip all 0s
    while (index < digs.length && digs[index] == 0) {
      index++;
    }
    if (index == digs.length) {
      return "0";
    } else {
      StringBuilder sb = new StringBuilder();
      for (int i = index; i < digs.length; i++) {
        sb.append(digs[i]);
      }
      return sb.toString();
    }
  }
}
