package com.loic.algo;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UnionFindTest {

  @Test
  public void test() {
    UnionFind find = new UnionFind(10);

    assertEquals(find.find(9), 9);
    find.union(0, 1);
    find.union(8, 1);
    find.union(0, 9);
    assertTrue(find.connected(8, 9));
  }
}
