package com.loic.leetcode.hard;

import java.util.Arrays;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WordSearch2Test {

  @Test
  void simple() {
    Assertions.assertEquals(Arrays.asList("oath", "eat"), WordSearch2.findWords(board("oaan", "etae", "ihkr", "iflv"), "oath", "pea", "eat", "rain"));
  }

  @Test
  void simple2() {
    Assertions.assertEquals(Arrays.asList("oath", "eat"), WordSearch2.findWords2(board("oaan", "etae", "ihkr", "iflv"), "oath", "pea", "eat", "rain"));
  }

  private char[][] board(String... rows) {
    char[][] board = new char[rows.length][];
    for (int i = 0; i < rows.length; i++) {
      board[i] = rows[i].toCharArray();
    }
    return board;
  }
}