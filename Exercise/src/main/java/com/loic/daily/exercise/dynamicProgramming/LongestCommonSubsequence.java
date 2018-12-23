package com.loic.daily.exercise.dynamicProgramming;

/**
 * https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
 */
public class LongestCommonSubsequence {

  /**
   * finding the longest subsequence common to all sequences in a set of sequences,
   * subsequences are not required to occupy consecutive positions within the original sequences
   */
  public static String find(String s1, String s2) {
    //LONGEST(i, j) means the Longest Common Subsequence of all pairs of prefixes of the strings
    int[][] longest = new int[s1.length() + 1][s2.length() + 1];
    for (int i = 0; i <= s1.length(); i++) {
      for (int j = 0; j <= s2.length(); j++) {
        if (i == 0 || j == 0) {
          longest[i][j] = 0;
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          longest[i][j] = longest[i - 1][j - 1] + 1;
        } else {
          longest[i][j] = Math.max(longest[i - 1][j], longest[i][j - 1]);
        }
      }
    }
    StringBuilder sb = new StringBuilder();
    int i = s1.length(), j = s2.length();
    while (i > 0 && j > 0) {
      //ATTENTION: longest[i][j] == longest[i - 1][j - 1] + 1 ==> this condition isn't enough
      if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
        sb.append(s1.charAt(i - 1));
        i--;
        j--;
      } else if (longest[i][j] == longest[i - 1][j]) {
        i--;
      } else {
        j--;
      }
    }
    return sb.reverse().toString();
  }
}
