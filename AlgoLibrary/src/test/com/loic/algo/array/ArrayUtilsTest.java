package com.loic.algo.array;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ArrayUtilsTest {
    @Test
    public void test() {
        Integer[] list = new Integer[]{4, 2, 3, 8, 5, 6, 7, 9, 1};
        List<Integer> arrayList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        Assert.assertEquals(0, ArrayUtils.binarySearch(arrayList, 1));
        Assert.assertEquals(1, ArrayUtils.binarySearch(arrayList, 2));
        Assert.assertEquals(3, ArrayUtils.binarySearch(arrayList, 4));
        Assert.assertEquals(5, ArrayUtils.binarySearch(arrayList, 6));
        Assert.assertEquals(7, ArrayUtils.binarySearch(arrayList, 8));
        Assert.assertEquals(8, ArrayUtils.binarySearch(arrayList, 9));

        Assert.assertEquals(~9, ArrayUtils.binarySearch(arrayList, 10));
        Assert.assertEquals(~9, ArrayUtils.binarySearch(arrayList, 100));
        System.out.println("~9 = " + (~9));
        Assert.assertEquals(~0, ArrayUtils.binarySearch(arrayList, -4));
        Assert.assertEquals(~0, ArrayUtils.binarySearch(arrayList, -155));
        System.out.println("~0 = " + (~0));

        Assert.assertEquals(1, (int) ArrayUtils.findKth(list, 0));
        Assert.assertEquals(2, (int) ArrayUtils.findKth(list, 1));
        Assert.assertEquals(3, (int) ArrayUtils.findKth(list, 2));
        Assert.assertEquals(5, (int) ArrayUtils.findKth(list, 4));
    }
}
