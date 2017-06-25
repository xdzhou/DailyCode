package com.loic.algo.graph;

import static org.testng.Assert.assertTrue;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.loic.algo.graph.topologicalSort.ITopologicalSort;
import com.loic.algo.graph.topologicalSort.KahnImpl;
import com.loic.algo.graph.topologicalSort.TopologicalDfsImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TopologicalSortTest {

    @Test
    public void unDirectedGraphtest() {
        Graph graph = GraphBuilder.undirected().build();

        ITopologicalSort algo = new KahnImpl();
        assertTrue(algo.sort(graph).isEmpty());

        algo = new TopologicalDfsImpl();
        assertTrue(algo.sort(graph).isEmpty());
    }

    @Test
    public void cycleGraphTest() {
        Graph<?> graph = GraphHepler.cycleDirectedGraph();

        ITopologicalSort algo = new KahnImpl();
        assertTrue(algo.sort(graph).isEmpty());

        algo = new TopologicalDfsImpl();
        assertTrue(algo.sort(graph).isEmpty());
    }

    @Test
    public void simpleGraphTest() {
        Graph<Integer> graph = GraphHepler.directedGraph();

        ITopologicalSort algo = new TopologicalDfsImpl();
        Assert.assertEquals(algo.sort(graph).size(), 13);

        algo = new KahnImpl();
        Assert.assertEquals(algo.sort(graph).size(), 13);
    }
}
