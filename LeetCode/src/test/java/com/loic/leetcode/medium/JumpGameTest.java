package com.loic.leetcode.medium;

import static com.loic.leetcode.medium.JumpGame.canJump;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JumpGameTest {

  @Test
  void test() {
    Assertions.assertTrue(canJump(2, 3, 1, 1, 4));
    Assertions.assertTrue(canJump(0));
    Assertions.assertTrue(canJump(1));
    Assertions.assertTrue(canJump());
    Assertions.assertTrue(canJump(3, 2, 1, 0));
    Assertions.assertFalse(canJump(3, 2, 1, 0, 4));
    Assertions.assertFalse(canJump(0, 4));
  }
}