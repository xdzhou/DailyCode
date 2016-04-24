package com.loic.algo;

public class UnionFind {
    private int[] nextId;
    private int[] treeSize;
    private int count;

    public UnionFind(int N) {
        count = N;
        nextId = new int[N];
        treeSize = new int[N];
        for (int i = 0; i < N; i++) {
            nextId[i] = i;
            treeSize[i] = 1;
        }
    }

    public int find(int p) {
        int p0 = p;
        while (nextId[p] != p)
            p = nextId[p];
        nextId[p0] = p;
        return p;
    }

    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);

        if (i == j)
            return;
        if (treeSize[i] > treeSize[j]) {
            nextId[j] = i;
            treeSize[i] += treeSize[j];
        } else {
            nextId[i] = j;
            treeSize[j] += treeSize[i];
        }
        count--;
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
