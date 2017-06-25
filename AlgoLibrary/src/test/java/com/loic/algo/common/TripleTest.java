package com.loic.algo.common;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TripleTest {

    @Test
    public void testConstructor() {
        Triple<Integer, Integer, Integer> triple = Triple.of(12, 13, 14);
        assertEquals(triple.first(), Integer.valueOf(12));
        assertEquals(triple.second(), Integer.valueOf(13));
        assertEquals(triple.third(), Integer.valueOf(14));
    }

    @Test
    public void testEqual() {
        Triple<Integer, Integer, Integer> triple1 = Triple.of(12, 13, 14);
        Triple<Integer, Integer, Integer> triple2 = Triple.of(12, 13, 14);
        assertEquals(triple1, triple2);
        assertEquals(triple1.hashCode(), triple2.hashCode(), 0);
        assertEquals(triple1.toString(), triple2.toString());
    }
}
