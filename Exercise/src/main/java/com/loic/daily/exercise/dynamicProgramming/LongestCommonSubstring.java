package com.loic.daily.exercise.dynamicProgramming;

/**
 * https://en.wikipedia.org/wiki/Longest_common_substring_problem
 */
public class LongestCommonSubstring {

  /**
   * find the longest string (or strings) that is a substring (or are substrings) of two or more strings
   * <p>
   * ATTENTION:
   * For this problem, we can't create a direct function f(i,j) means the longest common substring of all pairs of prefixes of the 2 string,
   * but we could get commonSuffix(i, j) easily ==> the longest common suffix,
   * and f(i,j) = max(commonSuffix(i, j))
   */
  public static String find(String s1, String s2) {
    //commonSuffix(i, j) means the longest common suffix for all pairs of prefixes of the strings
    int[][] commonSuffix = new int[s1.length() + 1][s2.length() + 1];
    //current longest common suffix length
    int longest = 0;
    //current longest common suffix end position in first strings
    int endPosition1 = 0;

    for (int i = 0; i <= s1.length(); i++) {
      for (int j = 0; j <= s2.length(); j++) {
        if (i == 0 || j == 0) {
          commonSuffix[i][j] = 0;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          commonSuffix[i][j] = commonSuffix[i - 1][j - 1] + 1;
          if (commonSuffix[i][j] > longest) {
            longest = commonSuffix[i][j];
            endPosition1 = i - 1;
          }
        } else {
          commonSuffix[i][j] = 0;
        }
      }
    }

    if (longest > 0) {
      return s1.substring(endPosition1 + 1 - longest, endPosition1 + 1);
    } else {
      return "";
    }

  }
}
