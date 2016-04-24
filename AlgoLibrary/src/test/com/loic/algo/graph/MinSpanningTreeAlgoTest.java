package com.loic.algo.graph;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.testng.Assert;

public class MinSpanningTreeAlgoTest extends GraphTest {
    private UndirectedGraph<Integer, DefaultWeightedEdge> mUndirectedGraph;

    public void beforeClass() {
        init();
        mUndirectedGraph = new AsUndirectedGraph<Integer, DefaultWeightedEdge>(mDirectedGraphGraph);
    }

    public void test() {
        PrimAlgo<Integer> primAlgo = new PrimAlgo<Integer>();
        primAlgo.setGraph(mUndirectedGraph);
        PrimMinimumSpanningTree<Integer, DefaultWeightedEdge> jGraphPrimAlog = new PrimMinimumSpanningTree<Integer, DefaultWeightedEdge>(
                mUndirectedGraph);
        Assert.assertEquals(primAlgo.getTotalWeight(), jGraphPrimAlog.getMinimumSpanningTreeTotalWeight());
    }
}
