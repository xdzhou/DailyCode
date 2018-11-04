package com.loic.algo.array;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ArrayUtilsTest {
  @Test
  void test() {
    Integer[] list = new Integer[]{4, 2, 3, 8, 5, 6, 7, 9, 1};
    List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    Assertions.assertEquals(0, ArrayUtils.binarySearch(arrayList, 1));
    Assertions.assertEquals(1, ArrayUtils.binarySearch(arrayList, 2));
    Assertions.assertEquals(3, ArrayUtils.binarySearch(arrayList, 4));
    Assertions.assertEquals(5, ArrayUtils.binarySearch(arrayList, 6));
    Assertions.assertEquals(7, ArrayUtils.binarySearch(arrayList, 8));
    Assertions.assertEquals(8, ArrayUtils.binarySearch(arrayList, 9));

    Assertions.assertEquals(~9, ArrayUtils.binarySearch(arrayList, 10));
    Assertions.assertEquals(~9, ArrayUtils.binarySearch(arrayList, 100));
    System.out.println("~9 = " + (~9));
    Assertions.assertEquals(~0, ArrayUtils.binarySearch(arrayList, -4));
    Assertions.assertEquals(~0, ArrayUtils.binarySearch(arrayList, -155));
    System.out.println("~0 = " + (~0));

    Assertions.assertEquals(1, (int) ArrayUtils.findKth(list, 0));
    Assertions.assertEquals(2, (int) ArrayUtils.findKth(list, 1));
    Assertions.assertEquals(3, (int) ArrayUtils.findKth(list, 2));
    Assertions.assertEquals(5, (int) ArrayUtils.findKth(list, 4));
  }
}
