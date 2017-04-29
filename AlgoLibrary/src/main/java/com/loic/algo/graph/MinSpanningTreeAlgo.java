package com.loic.algo.graph;

import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public interface MinSpanningTreeAlgo<T> {
    // set a graph to algo
    void setGraph(UndirectedGraph<T, DefaultWeightedEdge> graph);

    Set<DefaultWeightedEdge> generateMinSpanningTree();

    double getTotalWeight();
}
