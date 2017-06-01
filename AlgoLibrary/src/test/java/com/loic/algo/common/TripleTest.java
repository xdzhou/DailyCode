package com.loic.algo.common;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TripleTest {

    @Test
    public void testConstructor() {
        Triple<Integer, Integer, Integer> triple = Triple.of(12, 13, 14);
        Assert.assertEquals(triple.first(), Integer.valueOf(12));
        Assert.assertEquals(triple.second(), Integer.valueOf(13));
        Assert.assertEquals(triple.third(), Integer.valueOf(14));
    }

    @Test
    public void testEqual() {
        Assert.assertEquals(Triple.of(12, 13, 14), Triple.of(12, 13, 14));
    }
}
