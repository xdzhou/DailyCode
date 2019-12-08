package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LargestNumberTest {

  @Test
  void largestNumber() {
    Assertions.assertEquals("", LargestNumber.largestNumber());
    Assertions.assertEquals("123", LargestNumber.largestNumber(123));
    Assertions.assertEquals("121212", LargestNumber.largestNumber(12, 12, 12));
    Assertions.assertEquals("3433331", LargestNumber.largestNumber(3, 33, 31, 34));
    Assertions.assertEquals("1200", LargestNumber.largestNumber(0, 0, 12));
    Assertions.assertEquals("0", LargestNumber.largestNumber(0, 0, 0));


    Assertions.assertEquals("210", LargestNumber.largestNumber(10, 2));
    Assertions.assertEquals("9534330", LargestNumber.largestNumber(3, 30, 34, 5, 9));
  }

  @Test
  void failed() {
    Assertions.assertEquals("12121", LargestNumber.largestNumber(121, 12));
  }
}