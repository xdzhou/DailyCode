package com.loic.algo.graph;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

public class GraphTest {
    protected DirectedGraph<Integer, DefaultWeightedEdge> mDirectedGraphGraph;
    protected DirectedGraph<Integer, DefaultWeightedEdge> mDirectedGraphGraphWithCycle;

    public void init() {
        /*
		 * @see graph image
		 * http://my.csdn.net/uploads/201207/04/1341373589_4609.png
		 */
        mDirectedGraphGraph = new DefaultDirectedGraph<>(DefaultWeightedEdge.class);

        for (int i = 0; i < 13; i++) {
            mDirectedGraphGraph.addVertex(i);
        }
        mDirectedGraphGraph.addEdge(0, 6);
        mDirectedGraphGraph.addEdge(2, 0);
        mDirectedGraphGraph.addEdge(0, 1);
        mDirectedGraphGraph.addEdge(0, 5);
        mDirectedGraphGraph.addEdge(2, 3);
        mDirectedGraphGraph.addEdge(3, 5);
        mDirectedGraphGraph.addEdge(5, 4);
        mDirectedGraphGraph.addEdge(6, 4);
        mDirectedGraphGraph.addEdge(7, 6);
        mDirectedGraphGraph.addEdge(6, 9);
        mDirectedGraphGraph.addEdge(8, 7);
        mDirectedGraphGraph.addEdge(9, 10);
        mDirectedGraphGraph.addEdge(9, 11);
        mDirectedGraphGraph.addEdge(9, 12);
        mDirectedGraphGraph.addEdge(11, 12);

        mDirectedGraphGraphWithCycle = new DefaultDirectedGraph<>(DefaultWeightedEdge.class);
        mDirectedGraphGraphWithCycle.addVertex(0);
        mDirectedGraphGraphWithCycle.addVertex(1);
        mDirectedGraphGraphWithCycle.addVertex(2);
        mDirectedGraphGraphWithCycle.addEdge(0, 1);
        mDirectedGraphGraphWithCycle.addEdge(1, 2);
        mDirectedGraphGraphWithCycle.addEdge(2, 0);
    }
}
