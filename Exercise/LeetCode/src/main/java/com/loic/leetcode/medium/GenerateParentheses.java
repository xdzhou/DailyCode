package com.loic.leetcode.medium;

import com.loic.solution.SolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
 * 22. Generate Parentheses
 * https://en.wikipedia.org/wiki/Catalan_number
 */
public class GenerateParentheses implements SolutionProvider<Integer, List<String>> {

  @Override
  public List<Function<Integer, List<String>>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private List<String> resolve(int num) {
    List<String> result = new ArrayList<>();
    buildParenthese(0, 0, new StringBuilder(), num, result);
    return result;
  }

  //Backtracking
  private void buildParenthese(int left, int right, StringBuilder sb, int num, List<String> result) {
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
