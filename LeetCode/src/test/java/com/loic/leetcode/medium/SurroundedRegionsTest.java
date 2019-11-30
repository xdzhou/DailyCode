package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SurroundedRegionsTest {

  @Test
  void solve() {
    char[][] board = new char[4][];
    board[0] = "XXXX".toCharArray();
    board[1] = "XOOX".toCharArray();
    board[2] = "XXOX".toCharArray();
    board[3] = "XOXX".toCharArray();

    SurroundedRegions.solve(board);
    Assertions.assertEquals('X', board[1][1]);
    Assertions.assertEquals('X', board[1][2]);
    Assertions.assertEquals('X', board[2][2]);
  }

  @Test
  void empty() {
    char[][] board = new char[0][];
    SurroundedRegions.solve(board);
  }
}