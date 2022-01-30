package com.loic.leetcode.hard;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubarraysWithKDiffIntTest {
  private final SubarraysWithKDiffInt solution = new SubarraysWithKDiffInt();

  @Test
  void resolve() {
    Assertions.assertEquals(7, solution.resolve(TestHelper.toArray(1, 2, 1, 2, 3), 2));
    Assertions.assertEquals(3, solution.resolve(TestHelper.toArray(1,2,1,3,4), 3));

    Assertions.assertEquals(8, solution.resolve(TestHelper.toArray(2,1,1,1,2), 1));
  }
}