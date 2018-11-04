package com.loic.algo.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

  @Test
  void testConstructor() {
    Point p = new Point(12, 23);
    assertEquals(p.x, 12);
    assertEquals(p.y, 23);
  }

  @Test
  void testEqual() {
    Point p = new Point(12, 23);
    assertEquals(p, new Point(12, 23));
    assertEquals(p.hashCode(), new Point(12, 23).hashCode());
    assertEquals(p.toString(), new Point(12, 23).toString());
  }
}
