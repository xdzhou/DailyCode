package com.loic.algo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SortAlgoTest {
    private Double lists[];
    private int Num = 200;

    @BeforeClass
    public void beforeClass() {
        lists = new Double[Num];
        for (int i = 0; i < Num; i++) {
            lists[i] = Math.random() * 100;
        }
    }

    private Double[] cloneTable(Double[] c) {
        Double[] aux = new Double[Num];
        for (int i = 0; i < Num; i++) {
            aux[i] = c[i];
        }
        return aux;
    }

    @Test
    public void sortInsert() {
        Double[] testTable = cloneTable(lists);
        SortAlgo.sortInsert(testTable);
        Assert.assertTrue(SortAlgo.isSorted(testTable));
    }

    @Test
    public void sortMerge() {
        Double[] testTable = cloneTable(lists);
        SortAlgo.sortMerge(testTable);
        Assert.assertTrue(SortAlgo.isSorted(testTable));
    }

    @Test
    public void sortMergeBU() {
        Double[] testTable = cloneTable(lists);
        SortAlgo.sortMergeBU(testTable);
        Assert.assertTrue(SortAlgo.isSorted(testTable));
    }

    @Test
    public void sortQuick() {
        Double[] testTable = cloneTable(lists);
        SortAlgo.sortQuick(testTable);
        Assert.assertTrue(SortAlgo.isSorted(testTable));
    }

    @Test
    public void sortSelect() {
        Double[] testTable = cloneTable(lists);
        SortAlgo.sortSelect(testTable);
        Assert.assertTrue(SortAlgo.isSorted(testTable));
    }

    @Test
    public void sortShell() {
        Double[] testTable = cloneTable(lists);
        SortAlgo.sortShell(testTable);
        Assert.assertTrue(SortAlgo.isSorted(testTable));
    }
}
