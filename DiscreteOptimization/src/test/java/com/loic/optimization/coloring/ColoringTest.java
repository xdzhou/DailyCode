package com.loic.optimization.coloring;

import static java.util.Comparator.comparingInt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class ColoringTest {

  @TestFactory
  @Disabled
  Stream<DynamicTest> fileInputsTest() {
    Comparator<Vertex> sizeCom = comparingInt(v -> v.neighbours().size());

    ColoringResolver greedy = new GreedyColoringResolver();
    ColoringResolver greedyNeighbourSize = new ComparatorResolverDecorator(greedy, sizeCom.reversed());
    ColoringResolver search = new SearchColoringResolver(100);
    ColoringResolver searchNeighbourSize = new ComparatorResolverDecorator(search, sizeCom.reversed());

    Map<String, ColoringResolver> resolverMap = new HashMap<>();
    resolverMap.put("Greedy-Random", new RandomResolverDecorator(greedy, 1_000));
    resolverMap.put("Greedy-neighbourSize(random)", new RandomResolverDecorator(greedyNeighbourSize, 1_000));
    resolverMap.put("Search-neighbourSize", new ComparatorResolverDecorator(new SearchColoringResolver(1_000), sizeCom.reversed()));
    resolverMap.put("Search-neighbourSize(random)", new RandomResolverDecorator(searchNeighbourSize, 1_000));
    //resolverMap.put("LDS-neighbourSize", new ComparatorResolverDecorator(new LDSResolverDecorator(greedy), sizeCom.reversed()));

    ColoringResolver resolver = new BestColoringResolver(resolverMap);

    File inputsFolder = new File(getClass().getResource("/coloring").getFile());
    return Stream.of(inputsFolder)
      .flatMap(f -> Arrays.stream(f.listFiles()))
      .map(f -> DynamicTest.dynamicTest("test " + f.getName(), () -> testInputFile(f, resolver)));
  }

  private void testInputFile(File inputFile, ColoringResolver resolver) {
    try (Scanner scanner = new Scanner(inputFile)) {
      int vertexCount = scanner.nextInt();
      int edgeCount = scanner.nextInt();
      Map<Integer, Vertex> map = new HashMap<>();
      for (int i = 0; i < edgeCount; i++) {
        Vertex v1 = map.compute(scanner.nextInt(), (k, v) -> v == null ? new Vertex(k) : v);
        Vertex v2 = map.compute(scanner.nextInt(), (k, v) -> v == null ? new Vertex(k) : v);
        v1.addNeighbours(v2);
        v2.addNeighbours(v1);
      }
      Map<Vertex, Integer> result = resolver.resolve(new ArrayList<>(map.values()));
      System.out.println("Color count : " + result.values().stream().distinct().count());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}