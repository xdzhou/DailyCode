package com.loic.leetcode.hard;

import java.util.Stack;

/**
 * 224. Basic Calculator
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
 * <p>
 * Example 1:
 * <p>
 * Input: "1 + 1"
 * Output: 2
 * Example 2:
 * <p>
 * Input: " 2-1 + 2 "
 * Output: 3
 * Example 3:
 * <p>
 * Input: "(1+(4+5+2)-3)+(6+8)"
 * Output: 23
 * Note:
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator {

  public static int calculate(String s) {
    int value = 0;
    // if stack head is false, previous operation will flip, '+' to '-', '-' to '+'
    Stack<Boolean> stack = new Stack<>();
    stack.push(true);
    // previous operation, true for '+', false for '-'
    boolean preOp = true;
    // current number reading
    int curNum = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      int val = c - '0';
      boolean isNum = val >= 0 && val <= 9;
      if (isNum) {
        curNum *= 10;
        curNum += val;
      }
      if ((!isNum || i == s.length() - 1) && curNum > 0) {
        if (preOp == stack.peek()) {
          value += curNum;
        } else {
          value -= curNum;
        }
        curNum = 0;
      }
      if (c == '(') {
        boolean same = preOp == stack.peek();
        stack.push(same);
        preOp = true;
      } else if (c == ')') {
        stack.pop();
      } else if (c == '+' || c == '-') {
        preOp = c == '+';
      }
    }
    return value;
  }
}
