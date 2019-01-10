package com.loic.leetcode.hard;

import static com.loic.leetcode.hard.SubstringWithConcatenationWords.findSubstring;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SubstringWithConcatenationWordsTest {

  @Test
  void revers() {
    resultCompare(findSubstring("aze"));
    resultCompare(findSubstring("aze", "", ""));
    resultCompare(findSubstring("aze", "foo", "bar"));

    resultCompare(findSubstring("bbaabb", "aa", "bb"), 0, 2);

    resultCompare(findSubstring("aaaaa", "aa"), 0, 1, 3, 2);

    resultCompare(findSubstring("barfoothefoobarman", "foo", "bar"), 0, 9);

    resultCompare(findSubstring("wordgoodgoodgoodbestword", "word", "good", "best", "good"), 8);
  }

  private void resultCompare(List<Integer> list, int... nums) {
    Set<Integer> set = new HashSet<>();
    for (int n : nums) {
      set.add(n);
    }
    Assertions.assertEquals(set.size(), list.size());
    Assertions.assertEquals(set, new HashSet<>(list));
  }
}