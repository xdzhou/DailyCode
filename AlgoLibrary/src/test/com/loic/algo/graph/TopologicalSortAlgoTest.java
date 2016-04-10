package com.loic.algo.graph;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TopologicalSortAlgoTest extends GraphTest {

	@BeforeClass
	public void beforeClass() {
		init();
	}

	@Test
	public void kahnAlgoTest() {
		TopologicalKahnAlgo algo = new TopologicalKahnAlgo();
		algo.setGraph(mDirectedGraphGraph);
		List<Integer> results = algo.topologicalSort();
		Assert.assertEquals(13, results.size());

		System.out.println(results);
	}

	@Test
	public void kahnAlgoCycleTest() {
		TopologicalKahnAlgo algo = new TopologicalKahnAlgo();
		algo.setGraph(mDirectedGraphGraphWithCycle);
		Assert.assertTrue(algo.topologicalSort() == null);
	}

	@Test
	public void DfsAlgoTest() {
		TopologicalDfsAlgo algo = new TopologicalDfsAlgo();
		algo.setGraph(mDirectedGraphGraph);
		List<Integer> results = algo.topologicalSort();
		Assert.assertEquals(13, results.size());

		System.out.println(results);
	}

	@Test
	public void DfsAlgoCycleTest() {
		TopologicalDfsAlgo algo = new TopologicalDfsAlgo();
		algo.setGraph(mDirectedGraphGraphWithCycle);
		List<Integer> results = algo.topologicalSort();
		System.out.println(results);
		Assert.assertTrue(results == null);
	}
}
