package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Example:
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 */
public class PalindromePartitioning {

  public static List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    process(result, new ArrayList<>(), 0, s);
    return result;
  }

  private static void process(List<List<String>> result, List<String> list, int start, String s) {
    if (start >= s.length() && !list.isEmpty()) {
      result.add(new ArrayList<>(list));
    } else {
      for (int end = start; end < s.length(); end++) {
        //check whether s[start:end] is a palindrome
        if (isPalindrome(s, start, end)) {
          list.add(s.substring(start, end + 1));
          process(result, list, end + 1, s);
          list.remove(list.size() - 1);
        }
      }
    }
  }

  private static boolean isPalindrome(String s, int start, int end) {
    for (int i = 0; i < (end + 1 - start) >>> 1; i++) {
      if (s.charAt(start + i) != s.charAt(end - i)) {
        return false;
      }
    }
    return true;
  }


}
