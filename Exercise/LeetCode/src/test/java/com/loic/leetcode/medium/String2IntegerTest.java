package com.loic.leetcode.medium;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

class String2IntegerTest {
  private final SolutionChecker<String, Integer> checker = SolutionChecker.create(new String2Integer());

  @Test
  void testSmallNumber() {
    checker.check("42", 42);
  }

  @Test
  void testEmpty() {
    checker.check("   42", 42);
  }

  @Test
  void testEmptyAtEnd() {
    checker.check("42   ", 42);
  }

  @Test
  void testCharAtEnd() {
    checker.check("42sfsdf", 42);
  }

  @Test
  void testCharAtStart() {
    checker.check("sfsdf42", 0);
  }

  @Test
  void testSign() {
    checker.check("  -42", -42)
      .check(" +42", 42);
  }

  @Test
  void testMaxMin() {
    checker.check("" + Integer.MAX_VALUE, Integer.MAX_VALUE)
      .check("" + Integer.MIN_VALUE, Integer.MIN_VALUE);
  }

  @Test
  void testBoard() {
    checker.check("2147483646", 2147483646)
      .check("-2147483647", -2147483647);
  }

  @Test
  void testOverFlow() {
    checker.check("9999999999", Integer.MAX_VALUE)
      .check("2147483648", Integer.MAX_VALUE)
      .check("-9999999999", Integer.MIN_VALUE)
      .check("-2147483649", Integer.MIN_VALUE);
  }

}