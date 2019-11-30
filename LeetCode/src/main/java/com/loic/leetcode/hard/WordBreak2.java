package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 140. Word Break II
 * <p>
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * <p>
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * Example 2:
 * <p>
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 * <p>
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreak2 {

  public static List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> words = new HashSet<>(wordDict);
    Set<Integer> lens = words.stream()
      .map(String::length)
      .collect(Collectors.toSet());
    // dp[i] is all possible break of s[i,). if empty, s[i,) can NOT break
    Map<Integer, Break> dp = new HashMap<>();
    for (int begin = s.length() - 1; begin >= 0; begin--) {
      int from = begin;
      Break partition = new Break(from);
      lens.stream()
        .map(len -> from + len)
        .filter(end -> end <= s.length())
        .filter(end -> words.contains(s.substring(from, end)))
        .forEach(end -> {
          if (end == s.length()) {
            partition.subBreaks.add(Break.EMPTY);
          } else if (dp.containsKey(end)) {
            partition.subBreaks.add(dp.get(end));
          }
        });
      if (!partition.subBreaks.isEmpty()) {
        dp.put(from, partition);
      }
    }
    if (dp.containsKey(0)) {
      List<String> result = new ArrayList<>();
      dp.get(0).partitions(list -> {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
          if (i == list.size() - 1) {
            sb.append(s.substring(list.get(i)));
          } else {
            sb.append(s, list.get(i), list.get(i + 1)).append(' ');
          }
        }
        result.add(sb.toString());
      });
      return result;
    } else {
      return Collections.emptyList();
    }
  }

  private static final class Break {
    private static final Break EMPTY = new Break(-1);

    private final int from;
    private final List<Break> subBreaks = new ArrayList<>();

    private Break(int from) {
      this.from = from;
    }

    private void partitions(Consumer<List<Integer>> consumer) {
      partitions(new ArrayList<>(), consumer);
    }

    private void partitions(List<Integer> partitions, Consumer<List<Integer>> consumer) {
      if (from == -1) {
        // I'm EMPTY
        consumer.accept(partitions);
      } else {
        partitions.add(from);
        subBreaks.forEach(sub -> sub.partitions(partitions, consumer));
        partitions.remove(partitions.size() - 1);
      }
    }
  }
}
