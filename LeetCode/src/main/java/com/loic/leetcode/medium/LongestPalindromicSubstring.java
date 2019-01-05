package com.loic.leetcode.medium;

/**
 * 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 */
public final class LongestPalindromicSubstring {

  //manacher's algorithm
  public static String manacher(String s) {
    if (s.isEmpty()) {
      return s;
    }
    //assuming origin string s is "anan"
    //string T is "#a#n#a#n#"
    //len[i] is the radius of longest palindromic which center is T[i]
    int[] len = new int[s.length() * 2 + 1];
    len[0] = 1;
    //the max item's index in len array
    int maxIndex = 1;
    //current palindromic which has rightmost range
    int center = 0, right = 0;
    for (int i = 1; i < len.length - 1; i++) {
      if (i < right) {
        int opponent = 2 * center - i;
        //ATTENTION: here is '<' not '<='
        if (len[opponent] < right + 1 - i) {
          len[i] = len[opponent];
        } else {
          len[i] = radius(s, i, right);
        }
      } else {
        len[i] = radius(s, i, i);
      }
      if (right < i + len[i] - 1) {
        right = i + len[i] - 1;
        center = i;
      }
      if (len[i] > len[maxIndex]) {
        maxIndex = i;
      }
    }
    return s.substring(origalIndex(maxIndex - len[maxIndex] + 2), origalIndex(maxIndex + len[maxIndex]));
  }

  private static int radius(String originString, int center, int right) {
    right++;
    while (right < originString.length() * 2 + 1 && center * 2 - right >= 0 &&
      (right % 2 == 0 || originString.charAt(origalIndex(right)) == originString.charAt(origalIndex(center * 2 - right)))) {
      right++;
    }
    return right - center;
  }

  private static int origalIndex(int index) {
    return (index - 1) >>> 1;
  }

  //DP solution
  public static String find(String s) {
    if (s.isEmpty()) {
      return s;
    }
    int from = 0, to = 0;
    //dp(i, j) is true if s.subString(i, j+1) is palindromic
    boolean[][] dp = new boolean[s.length()][s.length()];
    //ATTENTION: here we can't increase "i" from 0 to s.length()-1
    //because "dp[i + 1][j - 1]" isn't computed when we are in "i" step
    for (int i = s.length() - 1; i >= 0; i--) {
      for (int j = i; j < s.length(); j++) {
        if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1])) {
          dp[i][j] = true;
          if (j - i > to - from) {
            from = i;
            to = j;
          }
        } else {
          dp[i][j] = false;
        }
      }
    }
    return s.substring(from, to + 1);
  }
}
