package com.loic.leetcode.hard;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordBreak2Test {

  @Test
  void simple() {
    List<String> result = WordBreak2.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    Assertions.assertEquals(2, result.size());
  }

  @Test
  void hard() {
    List<String> result = WordBreak2.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
      Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa", "aaaaaaaaaa"));
    Assertions.assertTrue(result.isEmpty());
  }
}