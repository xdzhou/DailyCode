package com.loic.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 208. Implement Trie (Prefix Tree)
 * <p>
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Example:
 * <p>
 * Trie trie = new Trie();
 * <p>
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 * Note:
 * <p>
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */
public class Trie {
  private final Node root;

  public Trie() {
    root = new Node(' ');
  }

  /**
   * Inserts a word into the trie.
   */
  public void insert(String word) {
    root.addString(word, 0);
  }

  /**
   * Returns if the word is in the trie.
   */
  public boolean search(String word) {
    return root.contain(word, 0);
  }

  /**
   * Returns if there is any word in the trie that starts with the given prefix.
   */
  public boolean startsWith(String prefix) {
    return root.startWith(prefix, 0);
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
        char child = s.charAt(from);
        if (children == null) {
          children = new HashMap<>();
        }
        Node node = children.compute(child, (ch, oldNode) -> oldNode == null ? new Node(ch) : oldNode);
        node.addString(s, from + 1);
      }
    }

    private boolean contain(String s, int from) {
      if (from == s.length()) {
        return isTerminate;
      }
      if (children != null && children.containsKey(s.charAt(from))) {
        return children.get(s.charAt(from)).contain(s, from + 1);
      }
      return false;
    }

    private boolean startWith(String s, int from) {
      if (from == s.length()) {
        return true;
      }
      if (children != null && children.containsKey(s.charAt(from))) {
        return children.get(s.charAt(from)).startWith(s, from + 1);
      }
      return false;
    }
  }
}
