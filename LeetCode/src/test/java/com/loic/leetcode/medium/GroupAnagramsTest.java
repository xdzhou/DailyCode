package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toSet;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GroupAnagramsTest {

  @Test
  void test() {

    Set<Set<String>> expected = toSet(
      toSet("eat", "tea", "ate"),
      toSet("tan", "nat"),
      toSet("bat")
    );

    List<List<String>> result = GroupAnagrams.groupAnagrams("eat", "tea", "tan", "ate", "nat", "bat");
    Assertions.assertEquals(expected, toSet(result));

    result = GroupAnagrams.optimal("eat", "tea", "tan", "ate", "nat", "bat");
    Assertions.assertEquals(expected, toSet(result));

    result = GroupAnagrams.optimal2("eat", "tea", "tan", "ate", "nat", "bat");
    Assertions.assertEquals(expected, toSet(result));
  }
}