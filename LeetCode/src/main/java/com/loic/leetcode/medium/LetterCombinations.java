package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/*
 * 17. Letter Combinations of a Phone Number
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 */
public class LetterCombinations {

  public static List<String> resolve(String input) {
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

  private static void buildStr(String datas[], int index, StringBuilder sb, List<String> result) {
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
