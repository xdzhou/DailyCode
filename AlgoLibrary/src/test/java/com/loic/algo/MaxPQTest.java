package com.loic.algo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class MaxPQTest {
  private int N = 100;
  private PriorityQueue<Integer> queueJava;
  private MaxPQ<Integer> maxQueue;

  @BeforeClass
  public void beforeClass() {
    Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o2, o1);
    queueJava = new PriorityQueue<>(N, cmp);
    maxQueue = new MaxPQ<>(Integer.class, N);
    assertTrue(maxQueue.isEmpty());
    for (int i = 0; i < N; i++) {
      int d = (int) (Math.random() * 100);
      queueJava.add(d);
      maxQueue.insert(d);
    }
    assertEquals(maxQueue.size(), N);
  }

  @Test
  public void MaxPQtest() {
    for (int i = 0; i < N; i++) {
      double d1 = queueJava.poll();
      double d2 = maxQueue.delMax();

      assertEquals(d1, d2);
    }
  }
}
