package com.loic.algo.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.loic.algo.graph.topologicalSort.ITopologicalSort;
import com.loic.algo.graph.topologicalSort.KahnImpl;
import com.loic.algo.graph.topologicalSort.TopologicalDfsImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TopologicalSortTest {

  @Test
  void unDirectedGraphtest() {
    Graph graph = GraphBuilder.undirected().build();

    ITopologicalSort algo = new KahnImpl();
    assertTrue(algo.sort(graph).isEmpty());

    algo = new TopologicalDfsImpl();
    assertTrue(algo.sort(graph).isEmpty());
  }

  @Test
  void cycleGraphTest() {
    Graph<?> graph = GraphHepler.cycleDirectedGraph();

    ITopologicalSort algo = new KahnImpl();
    assertTrue(algo.sort(graph).isEmpty());

    algo = new TopologicalDfsImpl();
    assertTrue(algo.sort(graph).isEmpty());
  }

  @Test
  void simpleGraphTest() {
    Graph<Integer> graph = GraphHepler.directedGraph();

    ITopologicalSort algo = new TopologicalDfsImpl();
    assertEquals(algo.sort(graph).size(), 13);

    algo = new KahnImpl();
    assertEquals(algo.sort(graph).size(), 13);
  }
}
