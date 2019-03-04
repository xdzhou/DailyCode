package com.loic.leetcode.hard;

/**
 * 87. Scramble String
 * https://leetcode.com/problems/scramble-string/
 *
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 *
 * Below is one possible representation of s1 = "great":
 *
 *     great
 *    /    \
 *   gr    eat
 *  / \    /  \
 * g   r  e   at
 *            / \
 *           a   t
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 *
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *
 *     rgeat
 *    /    \
 *   rg    eat
 *  / \    /  \
 * r   g  e   at
 *            / \
 *           a   t
 * We say that "rgeat" is a scrambled string of "great".
 *
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *
 *     rgtae
 *    /    \
 *   rg    tae
 *  / \    /  \
 * r   g  ta  e
 *        / \
 *       t   a
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * Example 1:
 *
 * Input: s1 = "great", s2 = "rgeat"
 * Output: true
 */
public final class ScrambleString {

  public static boolean isScramble(String s1, String s2) {
    if (s1.length() != s2.length() || !sameChar(s1, s2)) {
      return false;
    }
    return isScrambleIntern(s1, s2);
  }

  private static boolean isScrambleIntern(String s1, String s2) {
    if (s1.equals(s2)) {
      return true;
    }
    for (int len = 1; len <= s1.length() - 1; len++) {
      // we take left partial chars
      String left = s2.substring(0, len);
      // take the left partial chars within the same length
      String head = s1.substring(0, len);
      // take the right partial chars within the same length
      String tail = s1.substring(s1.length() - len);
      if (sameChar(left, head) && isScramble(head, left) && isScramble(s1.substring(len),
        s2.substring(len))) {
        return true;
      }
      if (sameChar(left, tail) && isScramble(tail, left) && isScramble(
        s1.substring(0, s1.length() - len), s2.substring(len))) {
        return true;
      }
    }
    return false;
  }

  // whether the two string contain the same chars (NOT consider the order)
  private static boolean sameChar(String s1, String s2) {
    if (s1.length() == 1) {
      return s1.charAt(0) == s2.charAt(0);
    }
    int[] nums = new int[256];
    for (char c : s1.toCharArray()) {
      nums[c]++;
    }
    for (char c : s2.toCharArray()) {
      if (nums[c] > 0) {
        nums[c]--;
      } else {
        return false;
      }
    }
    return true;
  }
}
