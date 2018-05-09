package com.loic.algo.common;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class PairTest {

  @Test
  public void testConstructor() {
    Pair<Integer, Integer> pair = Pair.of(12, 13);
    assertEquals(pair.first(), Integer.valueOf(12));
    assertEquals(pair.second(), Integer.valueOf(13));
  }

  @Test
  public void testEqual() {
    Pair<Integer, Integer> pair = Pair.of(12, 13);
    Pair<Integer, Integer> copy = pair.clone();
    assertEquals(copy, pair);
    assertEquals(pair.hashCode(), pair.clone().hashCode(), 0);
    assertEquals(pair.toString(), pair.clone().toString());
  }
}
