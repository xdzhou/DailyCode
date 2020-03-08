package com.loic.optimization.knapsack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
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
    Map<String, KnapsackResolver> resolvers = new HashMap<>();
    resolvers.put("Greedy-Density-First", new GreedyKnapsackResolver(Comparator.comparingDouble(Treasure::density).reversed()));
    resolvers.put("Greedy-Weight-First", new GreedyKnapsackResolver(Comparator.comparingDouble(Treasure::weight).reversed()));
    resolvers.put("Greedy-Value-First", new GreedyKnapsackResolver(Comparator.comparingDouble(Treasure::value).reversed()));
    resolvers.put("Branch-Bound-10000", new BranchBoundKnapsackResolver(10_000));
    resolvers.put("LDS:Nature-10000", new LDSKnapsackResolver(10_000));
    resolvers.put("LDS:Greedy-Density-First-10000", new LDSKnapsackResolver(10_000, Comparator.comparingDouble(Treasure::density).reversed()));
    resolvers.put("LDS:Greedy-Weight-First-10000", new LDSKnapsackResolver(10_000, Comparator.comparingDouble(Treasure::weight).reversed()));
    resolvers.put("LDS:Greedy-Value-First-10000", new LDSKnapsackResolver(10_000, Comparator.comparingDouble(Treasure::value).reversed()));

    BestKnapsackResolver resolver = new BestKnapsackResolver(resolvers);

    File inputsFolder = new File(getClass().getResource("/knapsack").getFile());
    return Stream.of(inputsFolder)
      .flatMap(f -> Arrays.stream(f.listFiles()))
      .map(f -> DynamicTest.dynamicTest("test " + f.getName(), () -> testInputFile(f, resolver)));
  }

  @TestFactory
  Stream<DynamicTest> LDSTest() {
    Map<String, KnapsackResolver> resolvers = new HashMap<>();

    //resolvers.put("LDS:Greedy-Weight-First", new LDSKnapsackResolver(Comparator.comparingDouble(Treasure::weight).reversed()));
    //resolvers.put("LDS:Greedy-Value-First", new LDSKnapsackResolver(Comparator.comparingDouble(Treasure::value).reversed()));
    BestKnapsackResolver resolver = new BestKnapsackResolver(resolvers);

    File inputsFolder = new File(getClass().getResource("/knapsack").getFile());
    return Stream.of(inputsFolder)
      .flatMap(f -> Arrays.stream(f.listFiles()))
      .map(f -> DynamicTest.dynamicTest("test " + f.getName(), () -> testInputFile(f, resolver)));
  }

  private void testInputFile(File inputFile, KnapsackResolver resolver) {
    try (Scanner scanner = new Scanner(inputFile)) {
      int size = scanner.nextInt();
      int capacity = scanner.nextInt();
      List<Treasure> list = new ArrayList<>(size);
      for (int i = 0; i < size; i++) {
        int value = scanner.nextInt();
        list.add(new Treasure(i, scanner.nextInt(), value));
      }
      Set<Treasure> treasures = resolver.resolve(list, capacity);
      int value = treasures.stream().mapToInt(Treasure::value).sum();
      Assertions.assertTrue(treasures.stream().mapToInt(Treasure::weight).sum() <= capacity);
      System.out.println("best : " + value);
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
    KnapsackResolver resolver = new BranchBoundKnapsackResolver(Integer.MAX_VALUE);
    Assertions.assertTrue(resolver.resolve(items, 0).isEmpty());
    //nothing can be taken
    int minWeight = items.stream().mapToInt(Treasure::weight).min().getAsInt();
    Assertions.assertTrue(resolver.resolve(items, minWeight - 1).isEmpty());
    //everything can be taken
    int sumWeight = items.stream().mapToInt(Treasure::weight).sum();
    Assertions.assertEquals(new HashSet<>(items), resolver.resolve(items, sumWeight));

    int maxValue = resolver.resolve(items, capacity).stream().mapToInt(Treasure::value).sum();
    Assertions.assertEquals(expectedMaxValue, maxValue);
  }
}