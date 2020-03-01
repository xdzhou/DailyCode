package com.loic.optimization.knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class KnapsackTest {

  @Test
  void simpleTest() {
    testResolve(generateItems(5, 45, 8, 48, 3, 35), 10, 80);
  }

  @TestFactory
  Stream<DynamicTest> fileInputsTest() {
    File inputsFolder = new File(getClass().getResource("/knapsack").getFile());
    return Stream.of(inputsFolder)
      .flatMap(f -> Arrays.stream(f.listFiles()))
      .map(f -> DynamicTest.dynamicTest("test " + f.getName(), () -> testInputFile(f)));
  }

  private void testInputFile(File inputFile) {
    if(inputFile.getName().contains("10000")) return;
    if(inputFile.getName().contains("200_0")) return;
    System.out.println("processing " + inputFile.getName());
    try (Scanner scanner = new Scanner(inputFile);) {
      int size = scanner.nextInt();
      int capacity = scanner.nextInt();
      List<Treasure> list = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
        int value = scanner.nextInt();
        list.add(new Treasure(i, scanner.nextInt(), value));
      }
      System.out.println(Knapsack.resolve(list, capacity).size());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private List<Treasure> generateItems(int... inputs) {
    int size = inputs.length / 2;
    List<Treasure> list = new ArrayList<>(size);
    for (int i = 0; i < size; i++) {
      Treasure t = new Treasure(i, inputs[i * 2], inputs[i * 2 + 1]);
      list.add(t);
    }
    return list;
  }

  private void testResolve(List<Treasure> items, int capacity, int expectedMaxValue) {
    Assertions.assertTrue(Knapsack.resolve(items, 0).isEmpty());
    //nothing can be taken
    int minWeight = items.stream().mapToInt(Treasure::weight).min().getAsInt();
    Assertions.assertTrue(Knapsack.resolve(items, minWeight - 1).isEmpty());
    //everything can be taken
    int sumWeight = items.stream().mapToInt(Treasure::weight).sum();
    Assertions.assertEquals(new HashSet<>(items), Knapsack.resolve(items, sumWeight));

    int maxValue = Knapsack.resolve(items, capacity).stream().mapToInt(Treasure::value).sum();
    Assertions.assertEquals(expectedMaxValue, maxValue);
  }
}