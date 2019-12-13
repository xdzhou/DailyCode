package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordDictionaryTest {

  @Test
  void simple() {
    WordDictionary dictionary = new WordDictionary();
    dictionary.addWord("bad");
    dictionary.addWord("dad");
    dictionary.addWord("mad");
    Assertions.assertFalse(dictionary.search("pad"));
    Assertions.assertTrue(dictionary.search("bad"));
    Assertions.assertTrue(dictionary.search(".ad"));
    Assertions.assertTrue(dictionary.search("b.."));

    Assertions.assertFalse(dictionary.search(""));
    dictionary.addWord("");
    Assertions.assertTrue(dictionary.search(""));
  }

  @Test
  void failed() {
    WordDictionary dictionary = new WordDictionary();
    dictionary.addWord("a");
    dictionary.addWord("a");

    Assertions.assertTrue(dictionary.search("."));
    Assertions.assertTrue(dictionary.search("a"));
    Assertions.assertFalse(dictionary.search("aa"));
    Assertions.assertTrue(dictionary.search("a"));
    Assertions.assertFalse(dictionary.search(".a"));
    Assertions.assertFalse(dictionary.search("a."));
  }
}