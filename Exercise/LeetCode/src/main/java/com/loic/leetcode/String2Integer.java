package com.loic.leetcode;

import com.loic.solution.SingleSolutionProvider;

/*
 * 8. String to Integer (atoi)
 */
public class String2Integer extends SingleSolutionProvider<String, Integer> {

  @Override
  protected Integer resolve(String input) {
    boolean positive = true;
    int index = 0;
    char[] chars = input.toCharArray();
    while (index < chars.length && chars[index] == ' ') {
      index++;
    }
    if (index < chars.length) {
      char first = chars[index];
      if (first == '-') {
        positive = false;
        index++;
      } else if (first == '+') {
        index++;
      }
    }
    int value = 0;
    int temp = Integer.MIN_VALUE / 10;
    for (int i = index; i < chars.length; i++) {
      int dig = chars[i] - '0';
      if (dig >= 0 && dig <= 9) {
        if (value < temp) {
          return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        value = value * 10 - dig;
        if (value > 0) {
          return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
      } else {
        break;
      }
    }
    int result = positive ? -value : value;
    return positive && result < 0 ? Integer.MAX_VALUE : result;
  }
}
