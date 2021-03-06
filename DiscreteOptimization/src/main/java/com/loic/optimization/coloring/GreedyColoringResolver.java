package com.loic.optimization.coloring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class GreedyColoringResolver implements ColoringResolver {

  @Override
  public Map<Vertex, Integer> resolve(List<Vertex> vertices) {
    Map<Vertex, Integer> result = new HashMap<>();
    vertices
      .forEach(v -> {
        Set<Integer> exclude = v.neighbours().stream()
          .filter(result::containsKey)
          .map(result::get)
          .collect(Collectors.toSet());
        result.put(v, minColor(exclude));
      });
    return result;
  }

  private int minColor(Set<Integer> excludeColors) {
    int id = 0;
    do {
      if (!excludeColors.contains(id)) {
        return id;
      }
      id++;
    } while (true);
  }
}
