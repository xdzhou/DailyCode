package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toSet;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Permutations2Test {

  @Test
  void permute() {
    Set<Set<Integer>> expected = toSet(
      toSet(1, 1, 3),
      toSet(1, 3, 1),
      toSet(3, 1, 1));

    List<List<Integer>> result = Permutations2.permute(1, 1, 3);
    Assertions.assertEquals(expected, toSet(result));
  }
}