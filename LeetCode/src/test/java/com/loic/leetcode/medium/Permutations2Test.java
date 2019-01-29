package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.list2Set;
import static com.loic.leetcode.TestHelper.toList;
import static com.loic.leetcode.TestHelper.toSet;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Permutations2Test {

  @Test
  void permute() {
    Set<List<Integer>> expected = toSet(
      toList(1, 1, 3),
      toList(1, 3, 1),
      toList(3, 1, 1));

    List<List<Integer>> result = Permutations2.permute(1, 1, 3);
    Assertions.assertEquals(expected, list2Set(result));

    result = Permutations2.permuteUnique(1, 1, 3);
    Assertions.assertEquals(expected, list2Set(result));
  }
}