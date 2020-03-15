package com.loic.optimization.coloring;

import static com.loic.optimization.coloring.BestColoringResolver.colorCount;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LDSResolverDecorator implements ColoringResolver {
  private final ColoringResolver delegate;

  public LDSResolverDecorator(ColoringResolver delegate) {
    this.delegate = delegate;
  }

  @Override
  public Map<Vertex, Integer> resolve(List<Vertex> vertices) {
    Vertex[] array = vertices.toArray(new Vertex[0]);
    Map<Vertex, Integer> best = delegate.resolve(vertices);
    for (int i = 0; i < array.length; i++) {
      for (int j = i + 1; j < array.length; j++) {
        //switch item i & j
        switchItem(array, i, j);
        Map<Vertex, Integer> tmp = delegate.resolve(Arrays.asList(array));
        if (best.isEmpty() || colorCount(tmp) < colorCount(best)) {
          best = tmp;
        }
        //switch back
        switchItem(array, i, j);
      }
    }
    return best;
  }

  private void switchItem(Vertex[] array, int index1, int index2) {
    Vertex tmp = array[index1];
    array[index1] = array[index2];
    array[index2] = tmp;
  }
}
