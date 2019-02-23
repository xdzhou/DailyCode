package com.loic.leetcode.medium;

import java.util.List;
import java.util.Set;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CombinationsTest {

  @Test
  void combine() {
    List<List<Integer>> result = Combinations.combine(4, 2);
    Set<Set<Integer>> expected = TestHelper.toSet(
      TestHelper.toSet(1, 2),
      TestHelper.toSet(1, 3),
      TestHelper.toSet(1, 4),
      TestHelper.toSet(2, 3),
      TestHelper.toSet(4, 3),
      TestHelper.toSet(2, 4)
    );
    Assertions.assertEquals(expected, TestHelper.toSet(result));
  }
}