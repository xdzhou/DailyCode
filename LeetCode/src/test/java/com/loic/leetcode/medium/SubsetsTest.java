package com.loic.leetcode.medium;

import java.util.List;
import java.util.Set;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubsetsTest {

  @Test
  void create() {
    List<List<Integer>> result = Subsets.create(1, 2, 3);
    Set<Set<Integer>> expected = TestHelper.toSet(
      TestHelper.toSet(1, 2),
      TestHelper.toSet(1, 3),
      TestHelper.toSet(2, 3),
      TestHelper.toSet(),
      TestHelper.toSet(1),
      TestHelper.toSet(2),
      TestHelper.toSet(3),
      TestHelper.toSet(1, 2, 3)
    );
    Assertions.assertEquals(expected, TestHelper.toSet(result));
  }
}