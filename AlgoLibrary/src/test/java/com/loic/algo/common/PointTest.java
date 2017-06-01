package com.loic.algo.common;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testConstructor() {
        Point p = new Point(12, 23);
        Assert.assertEquals(p.x, 12);
        Assert.assertEquals(p.y, 23);
    }

    @Test
    public void testEqual() {
        Assert.assertEquals(new Point(12, 23), new Point(12, 23));
    }
}
