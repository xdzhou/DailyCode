package com.loic.optimization.coloring;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class ComparatorResolverDecorator implements ColoringResolver {
  private final ColoringResolver delegate;
  private final Comparator<Vertex> comparator;

  public ComparatorResolverDecorator(ColoringResolver delegate, Comparator<Vertex> comparator) {
    this.delegate = delegate;
    this.comparator = comparator;
  }

  @Override
  public Map<Vertex, Integer> resolve(List<Vertex> vertices) {
    List<Vertex> tmp = new ArrayList<>(vertices);
    tmp.sort(comparator);
    return delegate.resolve(tmp);
  }
}
