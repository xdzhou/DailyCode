package com.loic.algo.common;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testConstructor() {
        Point p = new Point(12, 23);
        assertEquals(p.x, 12);
        assertEquals(p.y, 23);
    }

    @Test
    public void testEqual() {
        Point p = new Point(12, 23);
        assertEquals(p, new Point(12, 23));
        assertEquals(p.hashCode(), new Point(12, 23).hashCode(), 0);
        assertEquals(p.toString(), new Point(12, 23).toString());
    }
}
