package com.loic.algo.graph.minSpanningTree;

import java.util.Set;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.Graph;
import com.google.common.graph.ValueGraph;

public interface IMinSpanningTree {
    //generate mini spanning tree, return the edge set
    <N> Set<EndpointPair<N>> minSpanningTree(ValueGraph<N, Double> graph);
}
