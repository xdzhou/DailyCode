package com.loic.algo.common;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BitMapTest {

  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testNegLength() {
    new BitMap(-12);
  }

  @Test
  public void testSet() {
    BitMap bitMap = new BitMap(12);
    assertTrue(bitMap.set(1));
    assertFalse(bitMap.set(1));

    assertTrue(bitMap.isSet(1));
    assertFalse(bitMap.isSet(10));
  }

  @Test
  public void testBinaryString() {
    BitMap bitMap = new BitMap(40);
    bitMap.set(0);
    bitMap.set(39);

    assertEquals(bitMap.binaryString(), "1000000000000000000000000000000000000001");
  }
}
