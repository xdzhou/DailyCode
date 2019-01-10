package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * <p>
 * You are given a string, s, and a list of words, words, that are all of the same length.
 * Find all starting indices of substring(s) in s that is a concatenation of each word in words
 * exactly once and without any intervening characters.
 */
public final class SubstringWithConcatenationWords {

  public static List<Integer> findSubstring(String s, String... words) {
    int wordLen = words.length == 0 ? 0 : words[0].length();
    if (wordLen == 0 || s.length() < wordLen * words.length) {
      return Collections.emptyList();
    }
    List<Integer> result = new ArrayList<>();
    // this is the expected word count state we want to have
    WordsState expected = new WordsState(words);
    // the biggest index we could have in the result list
    int maxIndex = s.length() - wordLen * words.length;
    // k is the group, we split the index into k groups
    // group 0: 0, k, 2k ...
    // group 1: 1, k+1, 2k+1 ...
    for (int k = 0; k < wordLen; k++) {
      // the current word count state we have
      WordsState current = new WordsState();
      // the current index to check, is success, put it into result list
      int first;
      for (int i = 0; (first = k + wordLen * i) <= maxIndex; i++) {
        for (int len = current.size; len < words.length; len++) {
          int index = first + wordLen * len;
          if (!tryAdd(expected, current, s.substring(index, index + wordLen))) {
            break;
          }
        }
        if (current.size == expected.size) {
          result.add(first);
        }
        current.removeWord(s.substring(first, first + wordLen));
      }
    }

    return result;
  }

  private static boolean tryAdd(WordsState expected, WordsState current, String toAdd) {
    boolean canAdd = expected.wordCount(toAdd) > current.wordCount(toAdd);
    if (canAdd) {
      current.addWord(toAdd);
    }
    return canAdd;
  }

  private static class WordsState {
    //key is word, value is its count
    private final Map<String, Integer> wordCountMap;
    // the word count in the map
    private int size = 0;

    private WordsState(String... words) {
      wordCountMap = new HashMap<>();
      for (String w : words) {
        addWord(w);
      }
    }

    private void addWord(String word) {
      Integer count = wordCountMap.get(word);
      wordCountMap.put(word, count == null ? 1 : count + 1);
      size++;
    }

    private void removeWord(String word) {
      Integer count = wordCountMap.get(word);
      if (count == null) {
        return;
      }
      if (count == 1) {
        wordCountMap.remove(word);
      } else {
        wordCountMap.put(word, count - 1);
      }
      size--;
    }

    private int wordCount(String word) {
      Integer count = wordCountMap.get(word);
      return count == null ? 0 : count;
    }
  }
}
