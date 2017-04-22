package com.loic.algo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxPQTest {
    private int N = 100;
    private PriorityQueue<Double> pq_java;
    private MaxPQ<Double> pq_my;

    @BeforeClass
    public void beforeClass() {
        Comparator<Double> cmp = (o1, o2) -> (int) (o2 - o1);
        pq_java = new PriorityQueue<>(N, cmp);
        pq_my = new MaxPQ<>(Double.class, N);
        for (int i = 0; i < N; i++) {
            double d = Math.random() * 100;
            pq_java.add(d);
            pq_my.insert(d);
        }
    }

    public void MaxPQtest() {
        for (int i = 0; i < N; i++) {
            double d1 = pq_java.poll();
            double d2 = pq_my.delMax();
            System.out.println("d1=" + d1 + " d2=" + d2);
            Assert.assertEquals(d1, d2);
        }
    }
}
