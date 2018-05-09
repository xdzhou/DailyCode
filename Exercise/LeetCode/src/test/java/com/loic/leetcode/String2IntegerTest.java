package com.loic.leetcode;

import com.loic.solution.SolutionChecker;
import org.junit.Test;

public class String2IntegerTest {
  private final SolutionChecker<String, Integer> checker = SolutionChecker.checker(new String2Integer());

  @Test
  public void testSmallNumber() {
    checker.check("42", 42);
  }

  @Test
  public void testEmpty() {
    checker.check("   42", 42);
  }

  @Test
  public void testEmptyAtEnd() {
    checker.check("42   ", 42);
  }

  @Test
  public void testCharAtEnd() {
    checker.check("42sfsdf", 42);
  }

  @Test
  public void testCharAtStart() {
    checker.check("sfsdf42", 0);
  }

  @Test
  public void testSign() {
    checker.check("  -42", -42)
    .check(" +42", 42);
  }

  @Test
  public void testMaxMin() {
    checker.check("" + Integer.MAX_VALUE, Integer.MAX_VALUE)
      .check(""+Integer.MIN_VALUE, Integer.MIN_VALUE);
  }

  @Test
  public void testBoard() {
    checker.check("2147483646", 2147483646)
      .check("-2147483647", -2147483647);
  }

  @Test
  public void testOverFlow() {
    checker.check("9999999999", Integer.MAX_VALUE)
      .check("2147483648", Integer.MAX_VALUE)
      .check("-9999999999", Integer.MIN_VALUE)
      .check("-2147483649", Integer.MIN_VALUE);
  }

}