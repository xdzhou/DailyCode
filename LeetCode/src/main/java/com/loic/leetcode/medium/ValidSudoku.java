package com.loic.leetcode.medium;

import java.util.HashSet;
import java.util.Set;

import com.loic.leetcode.helper.BooleanArray;

/*
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Determine if a 9x9 Sudoku board is valid.
 */
public class ValidSudoku {

  /**
   * Collect the set of number we see, encoded as new number. For example(0 base):
   * <p>
   * '4' in row 7 is encoded to a number: 7*9+4
   * '4' in column 7 is encoded to a number: 9*9+7*9+4. (9*9 is all the possible row encode count)
   * '4' in the top-right block is encoded to a number: 9*9*2+2*4.
   */
  public static boolean encodeResolve(char[][] board) {
    Set<Integer> set = new HashSet<>();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        char c = board[i][j];
        if (c != '.') {
          int num = c - '1';
          int grid = i / 3 * 3 + j / 3;
          if (!set.add(i * 9 + num) || !set.add(81 + j * 9 + num) || !set.add(162 + grid * 9 + num)) {
            return false;
          }
        }
      }
    }
    return true;
  }

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
          int grid = i / 3 * 3 + j / 3;
          if (!check(array, i * 9 + num, 81 + j * 9 + num, 162 + grid * 9 + num)) {
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
