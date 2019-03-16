package com.loic.leetcode.hard;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InterleavingStringTest {

  @Test
  void isInterleave() {
    Assertions.assertTrue(InterleavingString.isInterleave("", "", ""));
    Assertions.assertTrue(InterleavingString.isInterleave("a", "", "a"));
    Assertions.assertTrue(InterleavingString.isInterleave("a", "b", "ab"));
    Assertions.assertTrue(InterleavingString.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    Assertions.assertTrue(InterleavingString.isInterleave("a", "ac", "aca"));
  }

  @Test
  void dp2D() {
    Assertions.assertTrue(InterleavingString.dpSolution2D("", "", ""));
    Assertions.assertTrue(InterleavingString.dpSolution2D("a", "", "a"));
    Assertions.assertTrue(InterleavingString.dpSolution2D("a", "b", "ab"));
    Assertions.assertTrue(InterleavingString.dpSolution2D("aabcc", "dbbca", "aadbbcbcac"));
    Assertions.assertTrue(InterleavingString.dpSolution2D("a", "ac", "aca"));
  }

  @Test
  void dp1D() {
    Assertions.assertTrue(InterleavingString.dpSolution1D("", "", ""));
    Assertions.assertTrue(InterleavingString.dpSolution1D("a", "", "a"));
    Assertions.assertTrue(InterleavingString.dpSolution1D("a", "b", "ab"));
    Assertions.assertTrue(InterleavingString.dpSolution1D("aabcc", "dbbca", "aadbbcbcac"));
    Assertions.assertTrue(InterleavingString.dpSolution1D("a", "ac", "aca"));
  }
}