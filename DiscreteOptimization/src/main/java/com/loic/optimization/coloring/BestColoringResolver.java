package com.loic.optimization.coloring;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BestColoringResolver implements ColoringResolver {
  private static final Logger LOGGER = Logger.getLogger(BestColoringResolver.class.getName());
  private final Map<String, ColoringResolver> resolverMap;

  public BestColoringResolver(Map<String, ColoringResolver> resolverMap) {
    this.resolverMap = new HashMap<>(resolverMap);
  }

  @Override
  public Map<Vertex, Integer> resolve(List<Vertex> vertices) {
    return resolverMap.entrySet().stream()
      .map(e -> {
        Map<Vertex, Integer> result = e.getValue().resolve(vertices);
        LOGGER.info(e.getKey() + " : " + colorCount(result));
        return result;
      })
      .min(Comparator.comparingInt(BestColoringResolver::colorCount))
      .orElse(Collections.emptyMap());
  }

  static int colorCount(Map<Vertex, Integer> result) {
    return (int) result.values().stream().distinct().count();
  }
}
