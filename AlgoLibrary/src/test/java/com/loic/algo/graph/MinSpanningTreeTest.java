package com.loic.algo.graph;

import java.util.Set;

import com.google.common.graph.EndpointPair;
import com.google.common.graph.ValueGraph;
import com.loic.algo.graph.minSpanningTree.IMinSpanningTree;
import com.loic.algo.graph.minSpanningTree.PrimImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MinSpanningTreeTest {

    @Test
    public void minSpanning() {
        IMinSpanningTree algo = new PrimImpl();
        ValueGraph<Integer, Double> graph = valueGraph();
        Set<EndpointPair<Integer>> edges = algo.minSpanningTree(graph);
        double totalWeight = 0;
        for (EndpointPair<Integer> pair : edges) {
            totalWeight += graph.edgeValue(pair.nodeU(), pair.nodeV());
        }

        Assert.assertEquals(totalWeight, 0, 0);
    }

    private ValueGraph<Integer, Double> valueGraph() {

    }
}
