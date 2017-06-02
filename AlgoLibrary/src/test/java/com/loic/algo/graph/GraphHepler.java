package com.loic.algo.graph;

import com.google.common.graph.Graph;
import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

public class GraphHepler {
    /*
     * @see graph image
	 * http://my.csdn.net/uploads/201207/04/1341373589_4609.png
	 */
    public static Graph<Integer> directedGraph() {
        MutableGraph<Integer> mutableGraph = GraphBuilder.directed()
            .allowsSelfLoops(false)
            .expectedNodeCount(13)
            .build();
        mutableGraph.putEdge(0, 6);
        mutableGraph.putEdge(2, 0);
        mutableGraph.putEdge(0, 1);
        mutableGraph.putEdge(0, 5);
        mutableGraph.putEdge(2, 3);
        mutableGraph.putEdge(3, 5);
        mutableGraph.putEdge(5, 4);
        mutableGraph.putEdge(6, 4);
        mutableGraph.putEdge(7, 6);
        mutableGraph.putEdge(6, 9);
        mutableGraph.putEdge(8, 7);
        mutableGraph.putEdge(9, 10);
        mutableGraph.putEdge(9, 11);
        mutableGraph.putEdge(9, 12);
        mutableGraph.putEdge(11, 12);
        return mutableGraph;
    }

    public static Graph<Integer> cycleDirectedGraph() {
        MutableGraph<Integer> mutableGraph = GraphBuilder.directed()
            .allowsSelfLoops(false)
            .expectedNodeCount(3)
            .build();
        mutableGraph.putEdge(0, 1);
        mutableGraph.putEdge(1, 2);
        mutableGraph.putEdge(2, 0);
        return mutableGraph;
    }

    public static ValueGraph<Integer, Double> valueGraph() {
        MutableValueGraph<Integer, Double> mutableValueGraph = ValueGraphBuilder.undirected()
            .allowsSelfLoops(false)
            .expectedNodeCount(6)
            .build();
        mutableValueGraph.putEdgeValue(0, 1, 1.1d);
        mutableValueGraph.putEdgeValue(2, 1, 1d);
        mutableValueGraph.putEdgeValue(0, 4, 3d);
        mutableValueGraph.putEdgeValue(2, 4, 1d);
        mutableValueGraph.putEdgeValue(2, 3, 3d);
        mutableValueGraph.putEdgeValue(4, 3, 1d);
        return mutableValueGraph;
    }
}
