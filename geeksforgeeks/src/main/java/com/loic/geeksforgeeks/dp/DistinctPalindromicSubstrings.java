package com.loic.geeksforgeeks.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * https://practice.geeksforgeeks.org/problems/distinct-palindromic-substrings/0
 * <p>
 * Given a string of lowercase ASCII characters, find all distinct continuous palindromic sub-strings of it.
 * <p>
 * EX: for string "abaaa", the answer is "a", "b", "aa", "aaa", "aba", so 5 distinct palindromic sub-strings
 */
public class DistinctPalindromicSubstrings {

  public static int resolve(String s) {
    Set<String> palindromic = new HashSet<>();

    //dp(i, j) is true if s.subString(i, j+1) is palindromic
    boolean[][] dp = new boolean[s.length()][s.length()];

    //ATTENTION: here we can't increase "i" from 0 to s.length()-1
    //because "dp[i + 1][j - 1]" isn't computed when we are in "i" step
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
          dp[i][j] = true;
          palindromic.add(s.substring(i, j + 1));
        }
      }
    }
    System.out.println(palindromic);
    return palindromic.size();
  }
}
