package com.loic.algo.common;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BitMapTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegLength() {
        new BitMap(-12);
    }

    @Test
    public void testSet() {
        BitMap bitMap = new BitMap(12);
        Assert.assertEquals(bitMap.set(1), true);
        Assert.assertEquals(bitMap.set(1), false);

        Assert.assertEquals(bitMap.isSet(1), true);
        Assert.assertEquals(bitMap.isSet(10), false);
    }

    @Test
    public void testBinaryString() {
        BitMap bitMap = new BitMap(40);
        bitMap.set(0);
        bitMap.set(39);

        Assert.assertEquals(bitMap.binaryString(), "1000000000000000000000000000000000000001");
    }
}
