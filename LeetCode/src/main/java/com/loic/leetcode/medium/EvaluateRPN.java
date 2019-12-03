package com.loic.leetcode.medium;

import java.util.Stack;

/**
 * 150. Evaluate Reverse Polish Notation
 * <p>
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
 * Example 1:
 * <p>
 * Input: ["2", "1", "+", "3", "*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 * Example 2:
 * <p>
 * Input: ["4", "13", "5", "/", "+"]
 * Output: 6
 * Explanation: (4 + (13 / 5)) = 6
 */
public class EvaluateRPN {

  public static int evalRPN(String... tokens) {
    Stack<Integer> stack = new Stack<>();
    for (String token : tokens) {
      int num;
      if ("+".equals(token)) {
        num = stack.pop() + stack.pop();
      } else if ("-".equals(token)) {
        int val = stack.pop();
        num = stack.pop() - val;
      } else if ("*".equals(token)) {
        num = stack.pop() * stack.pop();
      } else if ("/".equals(token)) {
        int val = stack.pop();
        num = stack.pop() / val;
      } else {
        num = Integer.parseInt(token);
      }
      stack.push(num);
    }
    return stack.pop();
  }
}
