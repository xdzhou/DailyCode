package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49. Group Anagrams
 * https://leetcode.com/problems/group-anagrams/
 * <p>
 * Given an array of strings, group anagrams together.
 */
public final class GroupAnagrams {

  // Two strings are anagrams if and only if their sorted strings are equal
  public static List<List<String>> groupAnagrams(String... strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
      char[] chars = s.toCharArray();
      Arrays.sort(chars);

      String key = new String(chars);
      map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
  }

  // Two strings are anagrams if and only if their character counts (respective number of occurrences of each character) are the same.
  public static List<List<String>> optimal(String... strs) {
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
      char[] chars = new char[26];
      for (char c : s.toCharArray()) {
        chars[c - 'a']++;
      }

      String key = new String(chars);
      map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
  }

  //
  public static List<List<String>> optimal2(String... strs) {
    int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};
    Map<Integer, List<String>> map = new HashMap<>();
    for (String s : strs) {
      int key = 1;
      for (char c : s.toCharArray()) {
        key *= prime[c - 'a'];
      }

      map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
    }
    return new ArrayList<>(map.values());
  }
}
