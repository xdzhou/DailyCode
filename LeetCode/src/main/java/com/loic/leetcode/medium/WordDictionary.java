package com.loic.leetcode.medium;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 211. Add and Search Word - Data structure design
 * Design a data structure that supports the following two operations:
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.
 * <p>
 * Example:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 */
public class WordDictionary {
  private final Node root;
  private boolean hasEmpty = false;

  public WordDictionary() {
    root = new Node(' ');
  }

  /**
   * Adds a word into the data structure.
   */
  public void addWord(String word) {
    if (word.isEmpty()) {
      hasEmpty = true;
    } else {
      root.addString(word, 0);
    }
  }

  /**
   * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
   */
  public boolean search(String word) {
    if (word.isEmpty()) {
      return hasEmpty;
    } else {
      return root.contain(word, 0);
    }
  }

  private static final class Node {
    private final char c;
    private boolean isTerminate = false;
    private Map<Character, Node> children;

    private Node(char c) {
      this.c = c;
    }

    private void addString(String s, int from) {
      if (from == s.length()) {
        isTerminate = true;
      } else {
        char nextC = s.charAt(from);
        if (children == null) {
          children = new HashMap<>();
        }
        Node node = children.compute(nextC, (ch, oldNode) -> oldNode == null ? new Node(ch) : oldNode);
        node.addString(s, from + 1);
      }
    }

    private boolean contain(String s, int from) {
      if (from == s.length()) {
        return isTerminate;
      }
      Map<Character, Node> map = children == null ? Collections.emptyMap() : children;
      char nextC = s.charAt(from);
      if (nextC == '.') {
        for (Node child : map.values()) {
          if (child.contain(s, from + 1)) {
            return true;
          }
        }
        return false;
      } else {
        Node child = map.get(nextC);
        return child != null && child.contain(s, from + 1);
      }
    }
  }
}
