package com.loic.leetcode.medium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class CombinationSumIITest {

  @Test
  void testEmpty() {
    assertTrue(CombinationSumII.combine(8).isEmpty());
    assertTrue(CombinationSumII.combine(8, 9).isEmpty());
    assertTrue(CombinationSumII.combine(8, 3, 7).isEmpty());
    assertTrue(CombinationSumII.combine(100, 3, 7, 1).isEmpty());

    assertTrue(CombinationSumII.combine2(8).isEmpty());
    assertTrue(CombinationSumII.combine2(8, 9).isEmpty());
    assertTrue(CombinationSumII.combine2(8, 3, 7).isEmpty());
    assertTrue(CombinationSumII.combine2(100, 3, 7, 1).isEmpty());
  }

  @Test
  void testSimpleCase() {
    assertEquals(Arrays.asList(Arrays.asList(7)), CombinationSumII.combine(7, 2, 3, 6, 7));
    assertEquals(Arrays.asList(Arrays.asList(7)), CombinationSumII.combine2(7, 2, 3, 6, 7));
  }

  @Test
  void testSmallCase() {
    List<List<Integer>> result = Arrays.asList(Arrays.asList(1, 7), Arrays.asList(2, 6), Arrays.asList(1, 1, 6), Arrays.asList(1, 2, 5));

    assertTrue(listEqual(result, CombinationSumII.combine(8, 10, 1, 2, 7, 6, 1, 5)));
    assertTrue(listEqual(result, CombinationSumII.combine2(8, 10, 1, 2, 7, 6, 1, 5)));
  }

  private boolean listEqual(List<List<Integer>> list1, List<List<Integer>> list2) {
    return list2Set(list1).equals(list2Set(list2));
  }

  private Set<Set<Integer>> list2Set(List<List<Integer>> list) {
    return list.stream().map(HashSet::new).collect(Collectors.toSet());
  }
}