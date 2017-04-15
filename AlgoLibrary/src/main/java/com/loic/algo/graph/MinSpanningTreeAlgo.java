package com.loic.algo.graph;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.Set;

public interface MinSpanningTreeAlgo<T> {
    // set a graph to algo
    public void setGraph(UndirectedGraph<T, DefaultWeightedEdge> graph);

    public Set<DefaultWeightedEdge> generateMinSpanningTree();

    public double getTotalWeight();
}
