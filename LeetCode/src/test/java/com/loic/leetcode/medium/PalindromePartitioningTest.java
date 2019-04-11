package com.loic.leetcode.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.loic.leetcode.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PalindromePartitioningTest {

  @Test
  void test() {
    Set<List<String>> expected = TestHelper.toSet(
      Arrays.asList("a", "a", "b"),
      Arrays.asList("aa", "b")
    );
    Set<List<String>> result = new HashSet<>(PalindromePartitioning.partition("aab"));
    Assertions.assertEquals(expected, result);
  }

  @Test
  void simple() {
    Assertions.assertTrue(PalindromePartitioning.partition("").isEmpty());
    Assertions.assertEquals(Arrays.asList(Arrays.asList("a")), PalindromePartitioning.partition("a"));
  }
}