package com.loic.leetcode.medium;

import com.loic.solution.SolutionChecker;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class LetterCombinationsTest {

  @Test
  void test() {
    SolutionChecker.create(new LetterCombinations())
      .check("23", Arrays.asList("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"));
  }
}