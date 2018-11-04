package com.loic.algo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnionFindTest {

  @Test
  void test() {
    UnionFind find = new UnionFind(10);

    assertEquals(find.find(9), 9);
    find.union(0, 1);
    find.union(8, 1);
    find.union(0, 9);
    assertTrue(find.connected(8, 9));
  }
}
