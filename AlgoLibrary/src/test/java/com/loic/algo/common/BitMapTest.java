package com.loic.algo.common;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BitMapTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegLength() {
        new BitMap(-12);
    }

    @Test
    public void testSet() {
        BitMap bitMap = new BitMap(12);
        assertEquals(bitMap.set(1), true);
        assertEquals(bitMap.set(1), false);

        assertEquals(bitMap.isSet(1), true);
        assertEquals(bitMap.isSet(10), false);
    }

    @Test
    public void testBinaryString() {
        BitMap bitMap = new BitMap(40);
        bitMap.set(0);
        bitMap.set(39);

        assertEquals(bitMap.binaryString(), "1000000000000000000000000000000000000001");
    }
}
