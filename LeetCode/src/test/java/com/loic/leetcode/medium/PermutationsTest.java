package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.list2Set;
import static com.loic.leetcode.TestHelper.toList;
import static com.loic.leetcode.TestHelper.toSet;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PermutationsTest {

  @Test
  void permute() {
    List<List<Integer>> result = Permutations.permute(1, 2, 3);

    Set<List<Integer>> expected = toSet(
      toList(1, 2, 3),
      toList(1, 3, 2),
      toList(2, 1, 3),
      toList(2, 3, 1),
      toList(3, 1, 2),
      toList(3, 2, 1));

    Assertions.assertEquals(expected, list2Set(result));
  }
}