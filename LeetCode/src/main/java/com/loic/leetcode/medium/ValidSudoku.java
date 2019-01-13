package com.loic.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/*
 * 36. Valid Sudoku
 * https://leetcode.com/problems/valid-sudoku/
 *
 * Determine if a 9x9 Sudoku board is valid.
 */
public class ValidSudoku {


  public static boolean resolve(char[][] board) {
    Map<Character, List<Integer>> positionsMap = new HashMap<>();
    return IntStream.range(0, 81)
      .allMatch(pos -> {
        int line = pos / 9;
        int row = pos % 9;
        return check(board[line][row], pos, positionsMap);
      });
  }

  private static boolean check(Character key, int pos, Map<Character, List<Integer>> positionsMap) {
    List<Integer> list = positionsMap.get(key);
    boolean valid = true;
    if (list != null) {
      valid = list.stream().allMatch(p -> p / 9 != pos / 9 && p % 9 != pos % 9 && blockPosition(p) != blockPosition(pos));
    } else if (key != '.') {
      list = new ArrayList<>();
      positionsMap.put(key, list);
    }
    if (list != null) {
      list.add(pos);
    }
    return valid;
  }

  private static int blockPosition(int index) {
    int line = index / 9 / 3;
    int row = index % 9 / 3;
    return line * 3 + row;
  }
}
