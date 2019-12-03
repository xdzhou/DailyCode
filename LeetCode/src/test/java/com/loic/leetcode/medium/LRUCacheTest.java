package com.loic.leetcode.medium;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LRUCacheTest {

  @Test
  void simple() {
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    Assertions.assertEquals(1, cache.get(1));       // returns 1
    cache.put(3, 3);    // evicts key 2
    Assertions.assertEquals(-1, cache.get(2));       // returns -1 (not found)
    cache.put(4, 4);    // evicts key 1
    Assertions.assertEquals(-1, cache.get(1));       // returns -1 (not found)
    Assertions.assertEquals(3, cache.get(3));       // returns 3
    Assertions.assertEquals(4, cache.get(4));       // returns 4
  }

  @Test
  void oneCapacity() {
    LRUCache cache = new LRUCache(1);
    cache.put(2, 1);
    Assertions.assertEquals(1, cache.get(2));
    cache.put(3, 2);
    Assertions.assertEquals(-1, cache.get(2));
    Assertions.assertEquals(2, cache.get(3));
  }
}