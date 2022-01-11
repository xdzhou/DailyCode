package com.loic.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LeastOperators2TargetTest {

  @Test
  void resolveCache() {
    assertEquals(5, LeastOperators2Target.resolveCache(3, 19));
    assertEquals(8, LeastOperators2Target.resolveCache(5, 501));
    assertEquals(3, LeastOperators2Target.resolveCache(100, 100000000));
  }

  @Test
  void leetcodeTestWrong() {
    assertEquals(45, LeastOperators2Target.resolveCache(79, 155800339));
    //assertEquals(45, LeastOperators2Target.leastOpsExpressTarget(79, 155800339));
  }
  @Test
  void leetcodeTestWrong2() {
    //assertEquals(45, LeastOperators2Target.resolveCache(79, 155800339));
    assertEquals(45, LeastOperators2Target.leastOpsExpressTarget(79, 155800339));
  }
}