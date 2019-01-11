package com.loic.leetcode.easy;

import java.util.LinkedList;

/**
 * 20. Valid Parentheses
 * https://leetcode.com/problems/valid-parentheses/
 * <p>
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 */
public final class ValidParentheses {

  public static boolean isValid(String s) {
    LinkedList<Character> stack = new LinkedList<>();
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
      } else if (stack.isEmpty() || c != opponent(stack.pop())) {
        return false;
      }
    }

    return stack.isEmpty();
  }

  private static char opponent(char c) {
    if (c == '(') {
      return ')';
    } else if (c == '{') {
      return '}';
    } else {
      return ']';
    }
  }
}
