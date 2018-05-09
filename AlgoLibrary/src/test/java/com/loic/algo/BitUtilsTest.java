package com.loic.algo;

import org.testng.annotations.Test;

import static com.loic.algo.BitUtils.getOneCount;
import static com.loic.algo.BitUtils.isBitStaggered;
import static org.testng.Assert.*;

public class BitUtilsTest {

  @Test
  public void testIsBitStaggered() {
    assertTrue(isBitStaggered(convert("101")));
    assertTrue(isBitStaggered(convert("1010")));
    assertFalse(isBitStaggered(convert("1011")));
    assertTrue(isBitStaggered(convert("10101010101")));
    assertFalse(isBitStaggered(convert("1001")));
  }

  @Test
  public void testOneCount() {
    assertEquals(getOneCount(convert("101")), 2);
    assertEquals(getOneCount(convert("111")), 3);
    assertEquals(getOneCount(convert("101110")), 4);
    assertEquals(getOneCount(convert("10000")), 1);
    assertEquals(getOneCount(convert("11111")), 5);
  }

  private int convert(String binary) {
    return Integer.valueOf(binary, 2);
  }
}
