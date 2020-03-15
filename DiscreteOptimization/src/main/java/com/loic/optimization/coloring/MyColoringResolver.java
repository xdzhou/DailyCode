package com.loic.optimization.coloring;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.loic.optimization.TimeoutException;

public class MyColoringResolver implements ColoringResolver {
  private static final Logger LOGGER = Logger.getLogger(MyColoringResolver.class.getName());
  private final Comparator<Vertex> comparator;
  private final long timeoutMs;

  public MyColoringResolver(Comparator<Vertex> comparator, long timeoutMs) {
    this.comparator = comparator;
    this.timeoutMs = timeoutMs;
  }

  @Override
  public Map<Vertex, Integer> resolve(List<Vertex> vertices) {
    vertices.sort(comparator);
    minColorCount = Integer.MAX_VALUE;
    maxMs = System.currentTimeMillis() + timeoutMs;
    try {
      search(0, new HashMap<>(), vertices, 0);
    } catch (TimeoutException e) {
      LOGGER.info("Timeout reached...");
    }
    return result;
  }

  // best result
  private int minColorCount = Integer.MAX_VALUE;
  private Map<Vertex, Integer> result;
  private long maxMs;

  private void search(int colorCount, Map<Vertex, Integer> colors, List<Vertex> vertices, int from) {
    if (System.currentTimeMillis() > maxMs) {
      throw new TimeoutException(timeoutMs);
    }
    if (from >= vertices.size()) {
      //all vertex are coloring
      if (colorCount < minColorCount) {
        minColorCount = colorCount;
        result = new HashMap<>(colors);
      }
      return;
    }
    //pruning
    if (colorCount >= minColorCount) {
      return;
    }
    Vertex v = vertices.get(from);
    Set<Integer> exclude = v.neighbours().stream()
      .filter(colors::containsKey)
      .map(colors::get)
      .collect(Collectors.toSet());
    boolean assignedColor = false;
    for (int c = 0; c <= colorCount; c++) {
      if (!exclude.contains(c)) {
        assignedColor = true;
        colors.put(v, c);
        search(colorCount, colors, vertices, from + 1);
        colors.remove(v);
      }
    }
    if (!assignedColor) {
      colors.put(v, colorCount + 1);
      search(colorCount + 1, colors, vertices, from + 1);
      colors.remove(v);
    }
  }
}
