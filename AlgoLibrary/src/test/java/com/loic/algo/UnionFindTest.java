package com.loic.algo;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class UnionFindTest {

    @Test
    public void test() {
        UnionFind find = new UnionFind(10);

        assertEquals(find.find(9), 9);
        find.union(0, 1);
        find.union(8, 1);
        find.union(0, 9);
        assertEquals(find.connected(8, 9), true);
    }
}
