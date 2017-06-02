package com.loic.algo;

import static com.loic.algo.BitUtils.getOneCount;
import static com.loic.algo.BitUtils.isBitStaggered;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class BitUtilsTest {

    @Test
    public void testIsBitStaggered() {
        assertEquals(isBitStaggered(convert("101")), true);
        assertEquals(isBitStaggered(convert("1010")), true);
        assertEquals(isBitStaggered(convert("1011")), false);
        assertEquals(isBitStaggered(convert("10101010101")), true);
        assertEquals(isBitStaggered(convert("1001")), false);
    }

    @Test
    public void testOneCount() {
        assertEquals(getOneCount(convert("101")), 2);
        assertEquals(getOneCount(convert("111")), 3);
        assertEquals(getOneCount(convert("101110")), 4);
        assertEquals(getOneCount(convert("10000")), 1);
        assertEquals(getOneCount(convert("11111")), 5);
    }

    private int convert(String binary) {
        return Integer.valueOf(binary, 2);
    }
}
