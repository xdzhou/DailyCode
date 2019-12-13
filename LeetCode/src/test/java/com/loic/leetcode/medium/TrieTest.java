package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TrieTest {

  @Test
  void simple() {
    Trie trie = new Trie();

    trie.insert("apple");
    Assertions.assertTrue(trie.search("apple"));   // returns true
    Assertions.assertFalse(trie.search("app"));     // returns false
    Assertions.assertTrue(trie.startsWith("app")); // returns true
    trie.insert("app");
    Assertions.assertTrue(trie.search("app"));     // returns true
  }
}