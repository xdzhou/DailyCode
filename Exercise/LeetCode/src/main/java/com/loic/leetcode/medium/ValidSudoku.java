package com.loic.leetcode.medium;

import com.loic.solution.SolutionProvider;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

/*
 * 10. Regular Expression Matching
 */
public class ValidSudoku implements SolutionProvider<char[][], Boolean> {

  @Override
  public List<Function<char[][], Boolean>> solutions() {
    return Collections.singletonList(this::resolve);
  }

  private boolean resolve(char[][] board) {
    Map<Character, List<Integer>> positionsMap = new HashMap<>();
    return IntStream.range(0, 81)
      .allMatch(pos -> {
        int line = pos / 9;
        int row = pos % 9;
        return check(board[line][row], pos, positionsMap);
      });
  }

  private boolean check(Character key, int pos, Map<Character, List<Integer>> positionsMap) {
    List<Integer> list = positionsMap.get(key);
    boolean valid = true;
    if (list != null) {
      valid = list.stream().allMatch(p -> p / 9 != pos / 9 && p % 9 != pos % 9 && blockPosition(p) != blockPosition(pos));
    } else if (key != '.'){
      list = new ArrayList<>();
      positionsMap.put(key, list);
    }
    if (list != null) {
      list.add(pos);
    }
    return valid;
  }

  private int blockPosition(int index) {
    int line = index / 9 / 3;
    int row = index % 9 / 3;
    return line * 3 + row;
  }
}
