package com.loic.leetcode.medium;

import static com.loic.leetcode.TestHelper.toIntArray;

import java.util.ArrayList;
import java.util.List;

import com.loic.leetcode.SolutionChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ThreeSumTest {
  private final SolutionChecker<int[], List<List<Integer>>> checker = SolutionChecker.create(ThreeSum::resolve, ThreeSum::optimal);

  @Test
  void test() {
    checker.check(toIntArray(-1, 0, 1, 2, -1, -4), tripleList(-1, -1, 2, -1, 0, 1));
  }

  private List<List<Integer>> tripleList(int... nums) {
    Assertions.assertEquals(0, nums.length % 3);
    List<List<Integer>> list = new ArrayList<>();
    int mod = 0;
    List<Integer> tmp = null;
    for (int val : nums) {
      if (mod % 3 == 0) {
        mod = 0;
        tmp = new ArrayList<>(3);
        list.add(tmp);
      }
      tmp.add(val);
      mod++;
    }
    return list;
  }
}