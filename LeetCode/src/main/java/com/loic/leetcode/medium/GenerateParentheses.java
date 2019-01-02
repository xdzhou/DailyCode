package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
 * 22. Generate Parentheses
 * https://en.wikipedia.org/wiki/Catalan_number
 */
public class GenerateParentheses {

  public static List<String> resolve(int num) {
    List<String> result = new ArrayList<>();
    buildParenthese(0, 0, new StringBuilder(), num, result);
    return result;
  }

  //Backtracking
  private static void buildParenthese(int left, int right, StringBuilder sb, int num, List<String> result) {
    if (left == right && left == num) {
      result.add(sb.toString());
    } else {
      //make sure left is always bigger than right
      boolean needLeft = left < num;
      boolean needRight = right < left;
      int len = left + right + 1;
      if (needLeft) {
        sb.append('(');
        buildParenthese(left + 1, right, sb, num, result);
        sb.delete(len - 1, len);
      }
      if (needRight) {
        sb.append(')');
        buildParenthese(left, right + 1, sb, num, result);
        sb.delete(len - 1, len);
      }
    }
  }
}
