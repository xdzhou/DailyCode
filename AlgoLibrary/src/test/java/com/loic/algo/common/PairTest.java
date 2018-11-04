package com.loic.algo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairTest {

  @Test
  void testConstructor() {
    Pair<Integer, Integer> pair = Pair.of(12, 13);
    assertEquals(pair.first(), Integer.valueOf(12));
    assertEquals(pair.second(), Integer.valueOf(13));
  }

  @Test
  void testEqual() {
    Pair<Integer, Integer> pair = Pair.of(12, 13);
    Pair<Integer, Integer> copy = pair.clone();
    assertEquals(copy, pair);
    assertEquals(pair.hashCode(), pair.clone().hashCode());
    assertEquals(pair.toString(), pair.clone().toString());
  }
}
