package com.loic.leetcode.helper;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BooleanArrayTest {

  @Test
  void negativeSize() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new BitMask(-5));
  }

  @Test
  void get() {
    BitMask array = new BitMask(100);
    long trueCount = IntStream.range(0, 100).filter(array::get).count();
    Assertions.assertEquals(0, trueCount);

    Assertions.assertFalse(array.get(32));
    array.set(32);
    Assertions.assertTrue(array.get(32));
    Assertions.assertEquals(1, IntStream.range(0, 100).filter(array::get).count());

    Assertions.assertFalse(array.get(99));
    array.set(99);
    Assertions.assertTrue(array.get(99));
    Assertions.assertEquals(2, IntStream.range(0, 100).filter(array::get).count());
  }
}