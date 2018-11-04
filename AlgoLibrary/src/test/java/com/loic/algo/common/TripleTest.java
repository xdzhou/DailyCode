package com.loic.algo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TripleTest {

  @Test
  void testConstructor() {
    Triple<Integer, Integer, Integer> triple = Triple.of(12, 13, 14);
    assertEquals(triple.first(), Integer.valueOf(12));
    assertEquals(triple.second(), Integer.valueOf(13));
    assertEquals(triple.third(), Integer.valueOf(14));
  }

  @Test
  void testEqual() {
    Triple<Integer, Integer, Integer> triple1 = Triple.of(12, 13, 14);
    Triple<Integer, Integer, Integer> triple2 = Triple.of(12, 13, 14);
    assertEquals(triple1, triple2);
    assertEquals(triple1.hashCode(), triple2.hashCode());
    assertEquals(triple1.toString(), triple2.toString());
  }
}
