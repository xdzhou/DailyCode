package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toSet;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PermutationsTest {

  @Test
  void permute() {
    List<List<Integer>> result = Permutations.permute(1, 2, 3);

    Set<Set<Integer>> expected = toSet(
      toSet(1, 2, 3),
      toSet(1, 3, 2),
      toSet(2, 1, 3),
      toSet(2, 3, 1),
      toSet(3, 1, 2),
      toSet(3, 2, 1));

    Assertions.assertEquals(expected, toSet(result));
  }
}