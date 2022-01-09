package com.loic.leetcode.hard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoreGameIVTest {

  @Test
  void resolveDp() {
    assertTrue(StoreGameIV.resolveDp(1));
    assertTrue(StoreGameIV.resolveCache(1));

    assertTrue(StoreGameIV.resolveDp(4));
    assertTrue(StoreGameIV.resolveCache(4));

    assertFalse(StoreGameIV.resolveDp(2));
    assertFalse(StoreGameIV.resolveCache(2));
  }

}