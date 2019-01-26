package com.loic.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class TestHelper {
  private TestHelper() {
    //utils
  }

  public static int[] toArray(int... nums) {
    return nums;
  }

  public static <T> Set<T> list2Set(List<T> list) {
    return new HashSet<>(list);
  }

  public static <T> Set<T> toSet(T... array) {
    return list2Set(Arrays.asList(array));
  }

  public static <T> List<T> toList(T... array) {
    return Arrays.asList(array);
  }
}
