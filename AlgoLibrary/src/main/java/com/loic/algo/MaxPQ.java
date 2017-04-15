package com.loic.algo;

import java.lang.reflect.Array;

public class MaxPQ<T extends Comparable<T>> {
    private T[] pq;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public MaxPQ(Class<T> clazz, int maxN) {
        pq = (T[]) Array.newInstance(clazz, maxN + 1);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(T t) {
        pq[++N] = t;
        swin(N);
    }

    public T delMax() {
        T max = pq[1];
        exch(1, N--);
        pq[N + 1] = null;
        sink(1);
        return max;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1))
                j++;
            if (!less(k, j))
                break;
            exch(k, j);
            k = j;
        }
    }

    private void swin(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        T t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

}
