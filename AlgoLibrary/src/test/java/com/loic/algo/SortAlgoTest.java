package com.loic.algo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class SortAlgoTest {
  private Double lists[];
  private int Num = 200;

  @BeforeEach
  void beforeClass() {
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
  void sortInsert() {
    Double[] testTable = cloneTable(lists);
    SortAlgo.sortInsert(testTable);
    assertTrue(SortAlgo.isSorted(testTable));
  }

  @Test
  void sortMerge() {
    Double[] testTable = cloneTable(lists);
    SortAlgo.sortMerge(testTable);
    assertTrue(SortAlgo.isSorted(testTable));
  }

  @Test
  void sortMergeBU() {
    Double[] testTable = cloneTable(lists);
    SortAlgo.sortMergeBU(testTable);
    assertTrue(SortAlgo.isSorted(testTable));
  }

  @Test
  void sortQuick() {
    Double[] testTable = cloneTable(lists);
    SortAlgo.sortQuick(testTable);
    assertTrue(SortAlgo.isSorted(testTable));
  }

  @Test
  void sortSelect() {
    Double[] testTable = cloneTable(lists);
    SortAlgo.sortSelect(testTable);
    assertTrue(SortAlgo.isSorted(testTable));
  }

  @Test
  void sortShell() {
    Double[] testTable = cloneTable(lists);
    SortAlgo.sortShell(testTable);
    assertTrue(SortAlgo.isSorted(testTable));
  }
}
