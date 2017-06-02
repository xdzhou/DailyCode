package com.loic.algo;

import java.util.Comparator;
import java.util.PriorityQueue;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MaxPQTest {
    private int N = 100;
    private PriorityQueue<Integer> queueJava;
    private MaxPQ<Integer> maxQueue;

    @BeforeClass
    public void beforeClass() {
        Comparator<Integer> cmp = (o1, o2) -> Integer.compare(o2, o1);
        queueJava = new PriorityQueue<>(N, cmp);
        maxQueue = new MaxPQ<>(Integer.class, N);
        Assert.assertEquals(maxQueue.isEmpty(), true);
        for (int i = 0; i < N; i++) {
            int d = (int) (Math.random() * 100);
            queueJava.add(d);
            maxQueue.insert(d);
        }
        Assert.assertEquals(maxQueue.size(), N);
    }

    @Test
    public void MaxPQtest() {
        for (int i = 0; i < N; i++) {
            double d1 = queueJava.poll();
            double d2 = maxQueue.delMax();

            Assert.assertEquals(d1, d2);
        }
    }
}
