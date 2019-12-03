package com.loic.leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 146. LRU Cache
 * <p>
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2);
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class LRUCache {
  private final Map<Integer, Item> itemMap = new HashMap<>();
  private final int capacity;
  // Head is the least recent used item
  private Item head, tail;

  public LRUCache(int capacity) {
    this.capacity = capacity;
  }

  public int get(int key) {
    Item item = itemMap.get(key);
    if (item != null) {
      moveToTail(item);
      return item.value;
    } else {
      return -1;
    }
  }

  public void put(int key, int value) {
    Item item = itemMap.get(key);
    if (item != null) {
      item.value = value;
      moveToTail(item);
    } else {
      item = new Item(key, value);
      if (itemMap.size() == capacity) {
        //evict the head
        itemMap.remove(head.key);
        if (head == tail) {
          head = tail = null;
        } else {
          head = head.post;
          head.pre = null;
        }
      }
      addToTail(item);
      itemMap.put(key, item);
    }
  }

  /**
   * item doesn't belong to the chain, add it as tail
   */
  private void addToTail(Item item) {
    item.pre = item.post = null;
    if (tail == null) {
      head = tail = item;
    } else {
      tail.post = item;
      item.pre = tail;
      tail = item;
    }
  }

  /**
   * item belong to the chain, move it to the end as tail
   */
  private void moveToTail(Item item) {
    if (item != tail) {
      if (item == head) {
        head = item.post;
        head.pre = null;
        addToTail(item);
      } else {
        item.pre.post = item.post;
        item.post.pre = item.pre;
        addToTail(item);
      }
    }
  }

  private static final class Item {

    private Item pre, post;
    private int key;
    private int value;

    private Item(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
}
