package com.loic.leetcode.medium;

import com.loic.leetcode.helper.BooleanArray;

/*
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Determine if a 9x9 Sudoku board is valid.
 */
public class ValidSudoku {

  public static boolean resolve(char[][] board) {
    // first 81 items for row
    // second 81 items for column
    // last 81 items for cell
    BooleanArray array = new BooleanArray(9 * 9 * 3);
    // array[0]: row 0 already has '1', array[9]: row 1 already has '1'
    // array[81]: column 0 already has '1' ...
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char c = board[i][j];
        if (c != '.') {
          int num = c - '1';
          int cell = i / 3 * 3 + j / 3;
          if (!check(array, i * 9 + num, 81 + j * 9 + num, 162 + cell * 9 + num)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  // try set true for index, if anyone is already set, return false
  private static boolean check(BooleanArray array, int... index) {
    for (int i : index) {
      if (array.get(i)) {
        return false;
      }
      array.set(i);
    }
    return true;
  }
}
