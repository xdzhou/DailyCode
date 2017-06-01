package com.loic.algo.common;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PairTest {

    @Test
    public void testConstructor() {
        Pair<Integer, Integer> pair = Pair.of(12, 13);
        Assert.assertEquals(pair.first(), Integer.valueOf(12));
        Assert.assertEquals(pair.second(), Integer.valueOf(13));
    }

    @Test
    public void testEqual() {
        Pair<Integer, Integer> pair = Pair.of(12, 13);
        Assert.assertEquals(pair.clone(), pair);
    }
}
