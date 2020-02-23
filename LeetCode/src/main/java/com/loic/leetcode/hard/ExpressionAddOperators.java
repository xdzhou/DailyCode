package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 282. Expression Add Operators
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p>
 * Example 1:
 * <p>
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 * <p>
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 * <p>
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 * <p>
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 * <p>
 * Input: num = "3456237490", target = 9191
 * Output: []
 */
public class ExpressionAddOperators {

  public static List<String> addOperators(String num, int target) {
    if (num == null || num.isEmpty()) {
      return Collections.emptyList();
    }
    if (num.length() == 1) {
      return target == num.charAt(0) - '0' ? Collections.singletonList(num) : Collections.emptyList();
    }
    List<String> result = new ArrayList<>();
    compute(num, target, 0, 0, 0, "", result);
    return result;
  }

  private static void compute(String num, int target, int from, long eval, long preOperand, String formal, List<String> result) {
    if (from >= num.length()) {
      if (target == eval) {
        result.add(formal);
      }
    } else {
      int maxReadLen = num.charAt(from) - '0' == 0 ? 1 : num.length() - from;
      for (int i = from; i < from + maxReadLen; i++) {
        long cur = Long.parseLong(num.substring(from, i + 1));
        if (from == 0) {
          compute(num, target, i + 1, cur, cur, cur + "", result);
        } else {
          compute(num, target, i + 1, eval + cur, cur, formal + '+' + cur, result);
          compute(num, target, i + 1, eval - cur, -cur, formal + '-' + cur, result);
          compute(num, target, i + 1, eval - preOperand + preOperand * cur, preOperand * cur, formal + '*' + cur, result);
        }
      }
    }
  }
}
