package com.loic.leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 212. Word Search II
 * <p>
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * Input:
 * board = [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * words = ["oath","pea","eat","rain"]
 * <p>
 * Output: ["eat","oath"]
 */
public class WordSearch2 {

  public static List<String> findWords(char[][] board, String... words) {
    if (board.length == 0 || board[0].length == 0 || words.length == 0) {
      return Collections.emptyList();
    }
    int rowLen = board[0].length;
    CharNetCount charNetCount = new CharNetCount();
    Map<Character, List<Integer>> positionMap = new HashMap<>();

    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        char c = board[row][col];
        List<Integer> list = positionMap.computeIfAbsent(c, k -> new ArrayList<>());
        list.add(row * rowLen + col);
        //up
        if (row - 1 >= 0) {
          charNetCount.connect(c, board[row - 1][col]);
        }
        //left
        if (col - 1 >= 0) {
          charNetCount.connect(c, board[row][col - 1]);
        }
      }
    }
    return Arrays.stream(words)
      .filter(w -> contain(w, positionMap, charNetCount, rowLen))
      .collect(Collectors.toList());
  }

  private static boolean contain(String word, Map<Character, List<Integer>> positionMap, CharNetCount globalCharCount, int rowLen) {
    if (word.isEmpty()) {
      return false;
    } else if (word.length() == 1) {
      return positionMap.getOrDefault(word.charAt(0), Collections.emptyList()).size() > 0;
    } else if (word.length() == 2) {
      return globalCharCount.connectCount(word.charAt(0), word.charAt(1)) > 0;
    }
    CharNetCount charCount = new CharNetCount();
    for (int i = 1; i < word.length(); i++) {
      if (charCount.connectAndCheck(word.charAt(i - 1), word.charAt(i), globalCharCount)) {
        return false;
      }
    }
    return contain(word, 0, positionMap, rowLen, new HashSet<>(), -1);
  }

  private static boolean contain(String word, int from, Map<Character, List<Integer>> positionMap, int rowLen, Set<Integer> visited, int curTail) {
    if (from == word.length()) {
      return true;
    }
    char c = word.charAt(from);
    for (int p : positionMap.get(c)) {
      if (!visited.contains(p) && neighbour(curTail, p, rowLen)) {
        visited.add(p);
        if (contain(word, from + 1, positionMap, rowLen, visited, p)) {
          return true;
        }
        visited.remove(p);
      }
    }
    return false;
  }

  private static boolean neighbour(int p1, int p2, int rowLen) {
    if (p1 < 0 || p2 < 0) {
      return true;
    }
    int row1 = p1 / rowLen;
    int col1 = p1 % rowLen;
    int row2 = p2 / rowLen;
    int col2 = p2 % rowLen;
    return (row1 == row2 && Math.abs(col1 - col2) == 1) || (col1 == col2 && Math.abs(row1 - row2) == 1);
  }

  private static final class CharNetCount {
    private final int[][] charTable;

    private CharNetCount() {
      int len = 'z' - 'a' + 1;
      charTable = new int[len][len];
    }

    public void connect(char c1, char c2) {
      if (c1 < c2) {
        charTable[c1 - 'a'][c2 - 'a']++;
      } else {
        charTable[c2 - 'a'][c1 - 'a']++;
      }
    }

    public int connectCount(char c1, char c2) {
      return c1 < c2 ? charTable[c1 - 'a'][c2 - 'a'] : charTable[c2 - 'a'][c1 - 'a'];
    }

    public boolean connectAndCheck(char c1, char c2, CharNetCount globalCharCount) {
      int row = c1 < c2 ? c1 - 'a' : c2 - 'a';
      int col = c1 < c2 ? c2 - 'a' : c1 - 'a';
      charTable[row][col]++;
      return globalCharCount.charTable[row][col] < charTable[row][col];
    }
  }


  /////optimal///
  public static List<String> findWords2(char[][] board, String... words) {
    Trie root = buildTrie(words);
    List<String> result = new ArrayList<>();
    for (int row = 0; row < board.length; row++) {
      for (int col = 0; col < board[0].length; col++) {
        dfs(row, col, board, root, result);
      }
    }
    return result;
  }

  private static void dfs(int row, int col, char[][] board, Trie t, List<String> result) {
    char c = board[row][col];
    if (c == '@' || t.next(c) == null) {
      return;
    }
    t = t.next(c);
    if (t.word != null) {
      result.add(t.word);
      t.word = null; // remove duplicate
    }
    board[row][col] = '@';
    if (row > 0) {
      dfs(row - 1, col, board, t, result);
    }
    if (row < board.length - 1) {
      dfs(row + 1, col, board, t, result);
    }
    if (col > 0) {
      dfs(row, col - 1, board, t, result);
    }
    if (col < board[0].length - 1) {
      dfs(row, col + 1, board, t, result);
    }
    board[row][col] = c;
  }

  private static Trie buildTrie(String... words) {
    Trie root = new Trie();
    for (String w : words) {
      Trie t = root;
      for (char c : w.toCharArray()) {
        int index = c - 'a';
        if (t.next[index] == null) {
          t.next[index] = new Trie();
        }
        t = t.next[index];
      }
      t.word = w;
    }
    return root;
  }

  private static final class Trie {
    private final Trie[] next = new Trie[26];
    private String word;

    public Trie next(char c) {
      return next[c - 'a'];
    }
  }
}
