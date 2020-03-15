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

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

class ColoringTest {

  @TestFactory
  Stream<DynamicTest> fileInputsTest() {
    Map<String, ColoringResolver> resolverMap = new HashMap<>();
    resolverMap.put("Greedy-nature", new GreedyColoringResolver(comparingInt(Vertex::id)));
    Comparator<Vertex> sizeCom = comparingInt(v -> v.neighbours().size());
    resolverMap.put("Greedy-neighbourSize", new GreedyColoringResolver(sizeCom.reversed()));
    resolverMap.put("XXX-neighbourSize", new MyColoringResolver(sizeCom.reversed(), 10_000));
    resolverMap.put("XXX-nature", new MyColoringResolver(comparingInt(Vertex::id), 10_000));

    ColoringResolver resolver = new BestColoringResolver(resolverMap);

    File inputsFolder = new File(getClass().getResource("/coloring").getFile());
    return Stream.of(inputsFolder)
      .flatMap(f -> Arrays.stream(f.listFiles()))
      .map(f -> DynamicTest.dynamicTest("test " + f.getName(), () -> testInputFile(f, resolver)));
  }

  @Test
  void test() {
    Comparator<Vertex> sizeCom = comparingInt(v -> v.neighbours().size());
    File file = new File(getClass().getResource("/coloring/gc_100_5").getFile());
    testInputFile(file, new MyColoringResolver(sizeCom.reversed(), 10_000));
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