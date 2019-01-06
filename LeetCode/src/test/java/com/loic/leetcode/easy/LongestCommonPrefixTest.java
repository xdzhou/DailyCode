package com.loic.leetcode.easy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LongestCommonPrefixTest {

  @Test
  void find() {
    assertEquals("", LongestCommonPrefix.find(""));
    assertEquals("", LongestCommonPrefix.find("", "ab"));
    assertEquals("fl", LongestCommonPrefix.find("flower", "flow", "flight"));
    assertEquals("", LongestCommonPrefix.find("dog", "racecar", "car"));
  }
}