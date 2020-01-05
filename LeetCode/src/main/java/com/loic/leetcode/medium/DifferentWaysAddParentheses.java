package com.loic.leetcode.medium;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * 241. Different Ways to Add Parentheses
 * <p>
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.
 * <p>
 * Example 1:
 * <p>
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 * <p>
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 */
public class DifferentWaysAddParentheses {

  public static List<Integer> diffWaysToCompute(String input) {
    List<Integer> nums = new ArrayList<>();
    List<Character> ops = new ArrayList<>();
    int curNum = 0;
    for (char c : input.toCharArray()) {
      int tmp = c - '0';
      if (tmp >= 0 && tmp <= 9) {
        curNum *= 10;
        curNum += tmp;
      } else {
        nums.add(curNum);
        curNum = 0;
        ops.add(c);
      }
    }
    nums.add(curNum);
    //
    List<Integer>[][] dp = (List<Integer>[][]) Array.newInstance(List.class, nums.size(), nums.size());
    for (int i = nums.size() - 1; i >= 0; i--) {
      for (int j = i; j < nums.size(); j++) {
        if (i == j) {
          dp[i][j] = Collections.singletonList(nums.get(i));
        } else if (j == i + 1) {
          dp[i][j] = Collections.singletonList(compute(nums.get(i), nums.get(j), ops.get(i)));
        } else {
          List<Integer> list = new ArrayList<>();
          for (int m = i; m < j; m++) {
            compute(dp[i][m], dp[m + 1][j], ops.get(m), list::add);
          }
          dp[i][j] = list;
        }
      }
    }
    return dp[0][nums.size() - 1];
  }

  private static void compute(List<Integer> nums1, List<Integer> nums2, char op, Consumer<Integer> consumer) {
    for (int n1 : nums1) {
      for (int n2 : nums2) {
        consumer.accept(compute(n1, n2, op));
      }
    }
  }

  private static int compute(int num1, int num2, char op) {
    switch (op) {
      case '+':
        return num1 + num2;
      case '-':
        return num1 - num2;
      case '*':
        return num1 * num2;
      default:
        throw new UnsupportedOperationException("Unknown op " + op);
    }
  }
}
