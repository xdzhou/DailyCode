package com.loic.algo.graph.shortestPath;

import com.google.common.graph.ValueGraph;

import java.util.List;

/*
 * find shortest path from source to target in a Graph
 */
public interface IShortestPath {
  // solves the source-target shortest path problem
  // return empty list if no path found
  <N> List<N> search(ValueGraph<N, Double> valueGraph, N startNode, N endNode);
}
