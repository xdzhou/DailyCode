package com.loic.algo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitMapTest {

  @Test
  void testNegLength() {
    assertThrows(IllegalArgumentException.class, () -> new BitMap(-12));
  }

  @Test
  void testSet() {
    BitMap bitMap = new BitMap(12);
    assertTrue(bitMap.set(1));
    assertFalse(bitMap.set(1));

    assertTrue(bitMap.isSet(1));
    assertFalse(bitMap.isSet(10));
  }

  @Test
  void testBinaryString() {
    BitMap bitMap = new BitMap(40);
    bitMap.set(0);
    bitMap.set(39);

    assertEquals(bitMap.binaryString(), "1000000000000000000000000000000000000001");
  }
}
