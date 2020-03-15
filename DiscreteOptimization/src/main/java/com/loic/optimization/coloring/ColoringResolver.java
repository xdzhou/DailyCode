package com.loic.optimization.coloring;

import java.util.List;
import java.util.Map;

public interface ColoringResolver {

  Map<Vertex, Integer> resolve(List<Vertex> vertices);
}
