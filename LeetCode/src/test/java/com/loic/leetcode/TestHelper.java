package com.loic.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.loic.leetcode.helper.Interval;

public final class TestHelper {
  private TestHelper() {
    //utils
  }

  public static int[] toArray(int... nums) {
    return nums;
  }

  public static <T> Set<Set<T>> toSet(List<List<T>> list) {
    return list.stream().map(HashSet::new).collect(Collectors.toSet());
  }

  public static <T> Set<T> toSet(T... array) {
    return new HashSet<>(Arrays.asList(array));
  }

  public static <T> List<T> toList(T... array) {
    return Arrays.asList(array);
  }

  public static List<Interval> intervals(int... nums) {
    List<Interval> list = new ArrayList<>();
    for (int i = 0; i < nums.length; i = i + 2) {
      list.add(new Interval(nums[i], nums[i + 1]));
    }
    return list;
  }
}
