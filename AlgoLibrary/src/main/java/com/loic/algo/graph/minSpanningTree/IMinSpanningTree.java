package com.loic.algo.graph.minSpanningTree;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.ValueGraph;

import java.util.Set;

public interface IMinSpanningTree {
  //generate mini spanning tree, return the edge set
  <N> Set<EndpointPair<N>> search(ValueGraph<N, Double> graph);
}
