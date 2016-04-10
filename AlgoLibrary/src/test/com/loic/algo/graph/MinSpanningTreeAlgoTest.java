package com.loic.algo.graph;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.PrimMinimumSpanningTree;
import org.jgrapht.graph.AsUndirectedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MinSpanningTreeAlgoTest extends GraphTest {
	private UndirectedGraph<Integer, DefaultWeightedEdge> mUndirectedGraph;

	public void beforeClass() {
		init();
		mUndirectedGraph = new AsUndirectedGraph<>(mDirectedGraphGraph);
	}

	public void test() {
		PrimAlgo<Integer> primAlgo = new PrimAlgo<>();
		primAlgo.setGraph(mUndirectedGraph);
		PrimMinimumSpanningTree<Integer, DefaultWeightedEdge> jGraphPrimAlog = new PrimMinimumSpanningTree<>(
				mUndirectedGraph);
		Assert.assertEquals(primAlgo.getTotalWeight(), jGraphPrimAlog.getMinimumSpanningTreeTotalWeight());
	}
}
