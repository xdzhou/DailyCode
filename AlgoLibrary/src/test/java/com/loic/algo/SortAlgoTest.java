package com.loic.algo;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class SortAlgoTest {
	private Comparable<Double>[] lists;
	private int Num = 20;
	
  @BeforeClass
  public void beforeClass() {
	  lists = new Comparable[Num];
	  for(int i=0; i<Num; i++){
		  lists[i] = Math.random()*100;
	  }	  
  }

  @AfterClass
  public void afterClass() {
  }
  
  private Comparable[] cloneTable(Comparable[] c){
	  Comparable[] aux = new Comparable[Num];
	  for(int i=0; i<Num; i++){
		  aux[i] = c[i];
	  }
	  return aux;
  }


  @Test
  public void sortInsert() {
	  Comparable[] testTable = cloneTable(lists);
	  SortAlgo.sortInsert(testTable);
	  Assert.assertTrue(SortAlgo.isSorted(testTable));
	  SortAlgo.show(testTable);
  }

  @Test
  public void sortMerge() {
	  Comparable[] testTable = cloneTable(lists);
	  SortAlgo.sortMerge(testTable);
	  Assert.assertTrue(SortAlgo.isSorted(testTable));
	  SortAlgo.show(testTable);
  }

  @Test
  public void sortMergeBU() {
	  Comparable[] testTable = cloneTable(lists);
	  SortAlgo.sortMergeBU(testTable);
	  Assert.assertTrue(SortAlgo.isSorted(testTable));
	  SortAlgo.show(testTable);
  }

  @Test
  public void sortQuick() {
	  Comparable[] testTable = cloneTable(lists);
	  SortAlgo.sortQuick(testTable);
	  Assert.assertTrue(SortAlgo.isSorted(testTable));
	  SortAlgo.show(testTable);
  }

  @Test
  public void sortSelect() {
	  Comparable[] testTable = cloneTable(lists);
	  SortAlgo.sortSelect(testTable);
	  Assert.assertTrue(SortAlgo.isSorted(testTable));
	  SortAlgo.show(testTable);
  }

  @Test
  public void sortShell() {
	  Comparable[] testTable = cloneTable(lists);
	  SortAlgo.sortShell(testTable);
	  Assert.assertTrue(SortAlgo.isSorted(testTable));
	  SortAlgo.show(testTable);
  }
}
