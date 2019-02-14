package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PermutationSequenceTest {

  @Test
  void permutation() {
    Assertions.assertEquals("1", PermutationSequence.permutation(1, 1));

    Assertions.assertEquals("2134", PermutationSequence.permutation(4, 7));
    Assertions.assertEquals("1234", PermutationSequence.permutation(4, 1));
    Assertions.assertEquals("1432", PermutationSequence.permutation(4, 6));
  }
}