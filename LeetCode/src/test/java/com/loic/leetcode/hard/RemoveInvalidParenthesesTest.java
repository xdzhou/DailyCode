package com.loic.leetcode.hard;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveInvalidParenthesesTest {

  @Test
  void resolve() {
    assertEquals(TestHelper.toSet("()()()", "(())()"), new HashSet<>(RemoveInvalidParentheses.resolve("()())()")));
    assertEquals(TestHelper.toSet("(a)()()", "(a())()"), new HashSet<>(RemoveInvalidParentheses.resolve("(a)())()")));
    assertEquals(TestHelper.toSet(""), new HashSet<>(RemoveInvalidParentheses.resolve(")(")));
  }
}