package com.loic.leetcode.hard;

/**
 * 316. Remove Duplicate Letters
 * https://leetcode.com/problems/remove-duplicate-letters/
 * <p>
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 * <p>
 * Example 1:
 * <p>
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 * <p>
 * Input: "cbacdcbc"
 * Output: "acdb"
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 */
public class RemoveDuplicateLetters {

  public static String remove(String s) {
    StringBuilder sb = new StringBuilder(s);
    int[] letterCounts = new int[256];
    int index = 0;
    while (index < sb.length()) {
      char thisChar = sb.charAt(index);
      char preChar = index == 0 ? (char) (thisChar + 1) : sb.charAt(index - 1);
      if (thisChar == preChar) {
        sb.deleteCharAt(index);
      } else {
        letterCounts[thisChar]++;
        index++;
      }
    }

    for (char c : s.toCharArray()) {
      letterCounts[c]++;
    }
    index = 0;
    while (index < sb.length() - 1) {
      char thisChar = sb.charAt(index);
      if (letterCounts[thisChar] > 1 && thisChar > sb.charAt(index + 1)) {
        sb.deleteCharAt(index);
        letterCounts[thisChar]--;
      } else {
        index++;
      }
    }
    sb.reverse();
    index = 0;
    while (index < sb.length() - 1) {
      char thisChar = sb.charAt(index);
      if (letterCounts[thisChar] > 1 && thisChar < sb.charAt(index + 1)) {
        sb.deleteCharAt(index);
        letterCounts[thisChar]--;
      } else {
        index++;
      }
    }

    return sb.reverse().toString();
  }
}
