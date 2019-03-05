package com.loic.leetcode.medium;

import java.util.Set;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Subsets2Test {

  @Test
  void subsetsWithDup() {
    Set<Set<Integer>> expexted = TestHelper.toSet(
      TestHelper.toSet(),
      TestHelper.toSet(2),
      TestHelper.toSet(2, 2),
      TestHelper.toSet(2, 2, 2)
    );
    Assertions.assertEquals(expexted, TestHelper.toSet(Subsets2.subsetsWithDup(2, 2, 2)));
  }

  @Test
  void leetcode() {
    Set<Set<Integer>> expexted = TestHelper.toSet(
      TestHelper.toSet(),
      TestHelper.toSet(2),
      TestHelper.toSet(1),
      TestHelper.toSet(2, 2),
      TestHelper.toSet(1, 2),
      TestHelper.toSet(1, 2, 2)
    );
    Assertions.assertEquals(expexted, TestHelper.toSet(Subsets2.subsetsWithDup(1, 2, 2)));
  }
}