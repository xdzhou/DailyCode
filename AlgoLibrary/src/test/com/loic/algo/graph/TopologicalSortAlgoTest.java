package com.loic.algo.graph;

import java.util.List;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TopologicalSortAlgoTest
{
	private DirectedGraph<Integer, DefaultEdge> mGraph;
	private DirectedGraph<Integer, DefaultEdge> mGraphWithCycle;

	@BeforeClass
	public void beforeClass()
	{
		/*
		 * @see graph image
		 * http://my.csdn.net/uploads/201207/04/1341373589_4609.png
		 */
		mGraph = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		mGraphWithCycle = new DefaultDirectedGraph<Integer, DefaultEdge>(DefaultEdge.class);
		
		for(int i = 0; i < 13; i++)
		{
			mGraph.addVertex(i);
		}
		mGraph.addEdge(0, 6);
		mGraph.addEdge(2, 0);
		mGraph.addEdge(0, 1);
		mGraph.addEdge(0, 5);
		mGraph.addEdge(2, 3);
		mGraph.addEdge(3, 5);
		mGraph.addEdge(5, 4);
		mGraph.addEdge(6, 4);
		mGraph.addEdge(7, 6);
		mGraph.addEdge(6, 9);
		mGraph.addEdge(8, 7);
		mGraph.addEdge(9, 10);
		mGraph.addEdge(9, 11);
		mGraph.addEdge(9, 12);
		mGraph.addEdge(11, 12);
		
		mGraphWithCycle.addVertex(0);
		mGraphWithCycle.addVertex(1);
		mGraphWithCycle.addVertex(2);
		mGraphWithCycle.addEdge(0, 1);
		mGraphWithCycle.addEdge(1, 2);
		mGraphWithCycle.addEdge(2, 0);
	}

	@Test
	public void kahnAlgoTest()
	{
		TopologicalKahnAlgo algo = new TopologicalKahnAlgo();
		algo.setGraph(mGraph);
		List<Integer> results = algo.topologicalSort();
		Assert.assertEquals(13, results.size());
		
		System.out.println(results);
	}
	
	@Test
	public void kahnAlgoCycleTest()
	{
		TopologicalKahnAlgo algo = new TopologicalKahnAlgo();
		algo.setGraph(mGraphWithCycle);
		Assert.assertTrue(algo.topologicalSort() == null);
	}
	
	@Test
	public void DfsAlgoTest()
	{
		TopologicalDfsAlgo algo = new TopologicalDfsAlgo();
		algo.setGraph(mGraph);
		List<Integer> results = algo.topologicalSort();
		Assert.assertEquals(13, results.size());
		
		System.out.println(results);
	}
	
	@Test
	public void DfsAlgoCycleTest()
	{
		TopologicalDfsAlgo algo = new TopologicalDfsAlgo();
		algo.setGraph(mGraphWithCycle);
		List<Integer> results = algo.topologicalSort();
		System.out.println(results);
		Assert.assertTrue(results == null);
	}
}
