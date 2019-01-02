package com.loic.leetcode.medium;

import java.util.Arrays;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Test;

class LetterCombinationsTest {

  @Test
  void test() {
    SolutionChecker.create(LetterCombinations::resolve)
      .check("23", Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
  }
}