package com.loic.leetcode.medium;

import com.loic.solution.SolutionProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/*
 * 17. Letter Combinations of a Phone Number
 */
public class LetterCombinations implements SolutionProvider<String, List<String>> {

  @Override
  public List<Function<String, List<String>>> solutions() {
    return Arrays.asList(this::resolve);
  }

  private List<String> resolve(String input) {
    String[] digits = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    String[] datas = new String[input.length()];
    for (int i = 0; i < input.length(); i++) {
      datas[i] = digits[input.charAt(i) - '0' - 2];
    }
    List<String> result = new ArrayList<>();
    if (datas.length > 0) {
      buildStr(datas, 0, new StringBuilder(), result);
    }
    return result;
  }

  private void buildStr(String datas[], int index, StringBuilder sb, List<String> result) {
    String str = datas[index];
    for (int i = 0; i < str.length(); i++) {
      sb.append(str.charAt(i));
      if (index + 1 < datas.length) {
        buildStr(datas, index + 1, sb, result);
      } else {
        result.add(sb.toString());
      }
      sb.delete(index, index + 1);
    }
  }
}
