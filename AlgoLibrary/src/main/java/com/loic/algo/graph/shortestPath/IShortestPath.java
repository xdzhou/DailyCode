package com.loic.algo.graph.shortestPath;

import java.util.List;

import com.google.common.graph.ValueGraph;

/*
 * find shortest path from source to target in a Graph
 */
public interface IShortestPath {
    // solves the source-target shortest path problem
    // return empty list if no path found
    <N> List<N> search(ValueGraph<N, Double> valueGraph, N startNode, N endNode);
}
