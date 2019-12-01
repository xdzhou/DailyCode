package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
    // dp[i] is all possible partition of s[i,). if Null, s[i,) can NOT break
    Map<Integer, Partition> dp = new HashMap<>();
    for (int begin = s.length() - 1; begin >= 0; begin--) {
      String subS = s.substring(begin);
      int from = begin;
      Partition partition = new Partition(from);
      wordDict.stream()
        .filter(subS::startsWith)
        .forEach(w -> {
          if (w.length() == subS.length()) {
            partition.subBreaks.add(Partition.EMPTY);
          } else if (dp.containsKey(from + w.length())) {
            partition.subBreaks.add(dp.get(from + w.length()));
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

  private static final class Partition {
    private static final Partition EMPTY = new Partition(-1);

    private final int from;
    private final List<Partition> subBreaks = new ArrayList<>();

    private Partition(int from) {
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
