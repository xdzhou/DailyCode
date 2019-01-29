package com.loic.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
}
