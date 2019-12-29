package com.loic.leetcode.medium;

import java.util.Stack;

/**
 * 227. Basic Calculator II
 * <p>
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 * <p>
 * Example 1:
 * <p>
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 * <p>
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 * <p>
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 * <p>
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */
public class BasicCalculator2 {

  public static int calculate(String s) {
    Stack<Integer> nums = new Stack<>();
    Stack<Character> ops = new Stack<>();
    int curNum = -1;
    for (char c : s.toCharArray()) {
      if (c == ' ') {
        continue;
      }
      int val = c - '0';
      boolean isNum = val >= 0 && val <= 9;
      if (isNum) {
        curNum = Math.max(curNum, 0);
        curNum *= 10;
        curNum += val;
      } else {
        nums.push(curNum);
        curNum = -1;
        while (!ops.isEmpty() && !highPriority(c, ops.peek())) {
          compute(nums, ops);
        }
        ops.push(c);
      }
    }
    if (curNum >= 0) {
      nums.push(curNum);
    }
    while (!ops.isEmpty()) {
      compute(nums, ops);
    }
    return nums.isEmpty() ? 0 : nums.pop();
  }

  private static boolean highPriority(char curOp, char preOp) {
    return (curOp == '*' || curOp == '/') && (preOp == '+' || preOp == '-');
  }

  private static void compute(Stack<Integer> nums, Stack<Character> ops) {
    int num2 = nums.pop();
    nums.push(compute(ops.pop(), nums.pop(), num2));
  }

  private static int compute(char op, int num1, int num2) {
    System.out.println("compute "+num1+op+num2);
    switch (op) {
      case '-':
        return num1 - num2;
      case '+':
        return num1 + num2;
      case '*':
        return num1 * num2;
      default:
        return num1 / num2;
    }
  }
}
