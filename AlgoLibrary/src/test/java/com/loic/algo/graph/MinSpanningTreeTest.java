package com.loic.algo.graph;

import java.util.Set;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import com.loic.algo.graph.minSpanningTree.IMinSpanningTree;
import com.loic.algo.graph.minSpanningTree.PrimImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MinSpanningTreeTest {

    @Test
    public void minSpanning() {
        IMinSpanningTree algo = new PrimImpl();
        ValueGraph<Integer, Double> graph = valueGraph();
        Set<EndpointPair<Integer>> edges = algo.search(graph);
        double totalWeight = 0;
        for (EndpointPair<Integer> pair : edges) {
            totalWeight += graph.edgeValue(pair.nodeU(), pair.nodeV());
        }

        Assert.assertEquals(totalWeight, 39, 0);
    }

    //https://zh.wikipedia.org/wiki/%E6%99%AE%E6%9E%97%E5%A7%86%E7%AE%97%E6%B3%95#/media/File:Prim_Algorithm_7.svg
    private ValueGraph<Integer, Double> valueGraph() {
        MutableValueGraph<Integer, Double> graph =  ValueGraphBuilder.undirected().expectedNodeCount(7).build();
        graph.putEdgeValue(0, 1, 7d);
        graph.putEdgeValue(0, 3, 5d);
        graph.putEdgeValue(1, 3, 9d);
        graph.putEdgeValue(4, 3, 15d);
        graph.putEdgeValue(5, 3, 6d);
        graph.putEdgeValue(1, 2, 8d);
        graph.putEdgeValue(1, 4, 7d);
        graph.putEdgeValue(4, 5, 8d);
        graph.putEdgeValue(5, 6, 11d);
        graph.putEdgeValue(2, 4, 5d);
        graph.putEdgeValue(4, 6, 9d);
        return graph;
    }
}
