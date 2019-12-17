package com.loic.leetcode.hard;

/**
 * 214. Shortest Palindrome
 * <p>
 * Given a string s, you are allowed to convert it to a palindrome by adding characters in front of it. Find and return the shortest palindrome you can find by performing this transformation.
 * <p>
 * Example 1:
 * <p>
 * Input: "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: "abcd"
 * Output: "dcbabcd"
 */
public class ShortestPalindrome {

  public static String shortestPalindrome(String s) {
    int len = s.length();
    boolean[] pre = new boolean[len];
    boolean[] cur = new boolean[len];
    if (len > 0) {
      pre[len - 1] = true;
    }
    int to = 0; //s[0,to]is a palindrome, find max 'to'
    for (int i = len - 2; i >= 0; i--) {
      for (int j = i; j < len; j++) {
        if (s.charAt(i) == s.charAt(j)) {
          cur[j] = j - i < 3 || pre[j - 1];
        } else {
          cur[j] = false;
        }
        if (i == 0 && cur[j]) {
          to = Math.max(to, j);
        }
      }
      boolean[] tmp = pre;
      pre = cur;
      cur = tmp;
    }
    if (to == s.length() - 1) {
      return s;
    } else {
      StringBuilder sb = new StringBuilder(s);
      for (int i = to + 1; i < len; i++) {
        sb.insert(0, s.charAt(i));
      }
      return sb.toString();
    }
  }

  public static String shortestPalindrome2(String s) {
    if (s.length() < 2) {
      return s;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = s.length() - 1; i > 0; i--) {
      if (isPalindrome(s, 0, i)) {
        break;
      } else {
        sb.append(s.charAt(i));
      }
    }
    sb.append(s);
    return sb.toString();
  }

  private static boolean isPalindrome(String s, int i, int j) {
    int from = i;
    int to = j;
    while (from < to) {
      if (s.charAt(from) != s.charAt(to)) {
        return false;
      } else {
        from++;
        to--;
      }
    }
    return true;
  }
}
