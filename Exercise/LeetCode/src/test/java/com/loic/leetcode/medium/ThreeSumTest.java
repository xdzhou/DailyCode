package com.loic.leetcode.medium;

import com.loic.leetcode.medium.ThreeSum;
import com.loic.solution.SolutionChecker;
import com.loic.solution.TestHelper;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreeSumTest {
  private final SolutionChecker<int[], List<List<Integer>>> checker = SolutionChecker.create(new ThreeSum());

  @Test
  public void test() {
    checker.check(TestHelper.toIntArray(-1, 0, 1, 2, -1, -4), tripleList(-1, -1, 2, -1, 0, 1));
  }

  private List<List<Integer>> tripleList(int... nums) {
    Assert.assertEquals(0, nums.length % 3);
    List<List<Integer>> list = new ArrayList<>();
    int mod = 0;
    List<Integer> tmp = null;
    for(int val : nums) {
      if (mod % 3 == 0) {
        mod = 0;
        tmp = new ArrayList<>(3);
        list.add(tmp);
      }
      tmp.add(val);
      mod ++;
    }
    return list;
  }
}